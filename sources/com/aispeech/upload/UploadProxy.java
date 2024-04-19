package com.aispeech.upload;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.aispeech.upload.database.DataBaseUtil;
import com.aispeech.upload.database.UploadBean;
import com.aispeech.upload.http.HttpManager;
import com.aispeech.upload.http.HttpUrlValue;
import com.aispeech.upload.util.FileUtils;
import com.aispeech.upload.util.LogUtil;
import com.aispeech.upload.util.NetworkUtils;
import com.aispeech.upload.util.Tag;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class UploadProxy {
    private static String TAG = Tag.getTag(null);
    private ConstantValue mConstantValue;
    private Context mContext;
    private DataBaseUtil mDataBaseUtil;
    private Handler mHandler;
    private volatile boolean isCancel = false;
    private HandlerThread mHandlerThread = new HandlerThread("proxy");
    private HttpManager mHttpManager = new HttpManager();

    /* JADX INFO: Access modifiers changed from: package-private */
    public UploadProxy(Context context, InitParams initParams, EncodeCallback encodeCallback) {
        this.mContext = context;
        this.mConstantValue = initParams.getConstantValue();
        this.mHttpManager.setHttpUrlValue(new HttpUrlValue(this.mConstantValue.getLogId(), this.mConstantValue.getProductId()));
        NetworkUtils.setContext(this.mContext);
        this.mHttpManager.setHttpUrl(this.mConstantValue.getBaseUrl());
        this.mHttpManager.setDeleFileWhenNetError(this.mConstantValue.isDeleFileWhenNetError());
        this.mDataBaseUtil = new DataBaseUtil(context, this.mConstantValue.getDbName());
        this.mHttpManager.setDataBaseUtil(this.mDataBaseUtil);
        this.mHttpManager.setEncodeCallback(encodeCallback);
        initHandlerThread();
    }

    private void initHandlerThread() {
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.aispeech.upload.UploadProxy.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 0) {
                    try {
                        UploadProxy.this.startInner();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    private void innerStart() {
        if (this.mConstantValue.isUploadImmediately()) {
            start();
        }
    }

    public void addJsonInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.mDataBaseUtil.addJsonInfo(str);
            checkMaxNum();
            innerStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFileInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            boolean z = false;
            Iterator<UploadBean> it = this.mDataBaseUtil.getJsonInfoList().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (str.equals(it.next().getJsonStr())) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                this.mDataBaseUtil.addFileInfo(str);
                checkMaxNum();
            }
            innerStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            this.mHandler.sendEmptyMessage(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void startInner() {
        if (!NetworkUtils.isConnected()) {
            LogUtil.e(TAG, "Network is not connected, return...");
            return;
        }
        this.isCancel = false;
        int errorNum = this.mDataBaseUtil.getErrorNum();
        List<UploadBean> jsonInfoList = this.mDataBaseUtil.getJsonInfoList();
        if (errorNum > 0) {
            this.mHttpManager.sendErrorData(ErrorJson.getErrorJson(this.mConstantValue.getLogId(), errorNum));
        }
        if (jsonInfoList != null && jsonInfoList.size() > 0) {
            for (UploadBean uploadBean : jsonInfoList) {
                if (this.isCancel) {
                    return;
                }
                if (uploadBean.getType().equals("json")) {
                    this.mHttpManager.sendData(uploadBean);
                } else {
                    String jsonStr = uploadBean.getJsonStr();
                    String filePath = uploadBean.getFilePath();
                    if (TextUtils.isEmpty(filePath)) {
                        LogUtil.e(TAG, "realFilePath null, return...");
                        return;
                    } else if (!HttpManager.mRealUploadFileJsonList.contains(jsonStr)) {
                        if (!FileUtils.isFile(filePath)) {
                            this.mDataBaseUtil.deleFileInfo(uploadBean.getId());
                        } else {
                            uploadBean.setAutoDeleFile(this.mConstantValue.isAutoDeleFile());
                            this.mHttpManager.uploadFile(uploadBean);
                        }
                    }
                }
                SystemClock.sleep(50L);
            }
        }
    }

    public void stop() {
        try {
            this.isCancel = true;
            this.mHttpManager.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkMaxNum() {
        this.mDataBaseUtil.checkMaxNum(this.mConstantValue.getMaxCacheNums());
    }

    public String getJson(ModelBuilder modelBuilder) {
        modelBuilder.addDefaultParam(this.mConstantValue);
        return modelBuilder.buildJson();
    }

    private static String addLocalParams(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.put("isCloud", z);
                return jSONObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
                return str;
            }
        }
        return str;
    }

    public static String getConfigInfo(Context context, final ConfigRequestBean configRequestBean) {
        try {
            NetworkUtils.setContext(context);
            SharedPreferences sharedPreferences = context.getSharedPreferences("UploadConfig", 0);
            if (!NetworkUtils.isConnected()) {
                return addLocalParams(sharedPreferences.getString("config", ""), false);
            }
            FutureTask futureTask = new FutureTask(new Callable<String>() { // from class: com.aispeech.upload.UploadProxy.2
                @Override // java.util.concurrent.Callable
                public final String call() throws Exception {
                    return HttpManager.getConfigInfo(ConfigRequestBean.this);
                }
            });
            new Thread(futureTask).start();
            String str = (String) futureTask.get();
            if (!TextUtils.isEmpty(str)) {
                sharedPreferences.edit().putString("config", str).apply();
                return addLocalParams(str, true);
            }
            return addLocalParams(sharedPreferences.getString("config", ""), false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.aispeech.upload.http.HttpManager, android.content.Context, android.os.HandlerThread, com.aispeech.upload.ConstantValue, android.os.Handler, com.aispeech.upload.database.DataBaseUtil] */
    public void release() {
        try {
            try {
                this.mHandlerThread.quit();
                this.mDataBaseUtil.release();
                this.mHttpManager.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            this.mHttpManager = null;
            this.mDataBaseUtil = null;
            this.mHandlerThread = null;
            this.mHandler = null;
            this.mConstantValue = null;
            this.mContext = null;
        }
    }
}
