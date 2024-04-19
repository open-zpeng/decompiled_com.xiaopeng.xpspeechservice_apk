package com.aispeech.upload.http;

import com.aispeech.upload.ConfigRequestBean;
import com.aispeech.upload.EncodeCallback;
import com.aispeech.upload.database.DataBaseUtil;
import com.aispeech.upload.database.UploadBean;
import com.aispeech.upload.http.Request;
import com.aispeech.upload.util.FileUtils;
import com.aispeech.upload.util.LogUtil;
import com.aispeech.upload.util.Tag;
import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class HttpManager {
    public static volatile CopyOnWriteArrayList<String> mRealUploadFileJsonList;
    private DataBaseUtil mDataBaseUtil;
    private EncodeCallback mEncodeCallback;
    private DzHttpClient mHttpClient = new DzHttpClient();
    private HttpUrlValue mHttpUrlValue;
    private static final String TAG = Tag.getTag("HTTP");
    private static String mUrl = null;
    private static boolean mDeleFileWhenNetError = false;

    public HttpManager() {
        mRealUploadFileJsonList = new CopyOnWriteArrayList<>();
    }

    public void setHttpUrlValue(HttpUrlValue httpUrlValue) {
        this.mHttpUrlValue = httpUrlValue;
    }

    public void setEncodeCallback(EncodeCallback encodeCallback) {
        this.mEncodeCallback = encodeCallback;
    }

    public void setDataBaseUtil(DataBaseUtil dataBaseUtil) {
        this.mDataBaseUtil = dataBaseUtil;
    }

    public void setHttpUrl(String str) {
        mUrl = str;
    }

    public void setDeleFileWhenNetError(boolean z) {
        mDeleFileWhenNetError = z;
    }

    private boolean executeHttp(Response response) {
        if (response != null) {
            if (response.getResponseCode() == 200 || (response.getResponseCode() >= 400 && response.getResponseCode() < 500)) {
                String str = TAG;
                LogUtil.d(str, "upload success -> " + response.string());
                return true;
            }
            String str2 = TAG;
            LogUtil.d(str2, "upload error -> " + response.getResponseCode());
        } else {
            LogUtil.d(TAG, "upload error -> response is null!");
        }
        return false;
    }

    public void sendData(UploadBean uploadBean) {
        LogUtil.d(TAG, "sendData...");
        try {
            if (executeHttp(this.mHttpClient.newCall(new Request.Builder().url(this.mHttpUrlValue.getJsonUrl(mUrl)).tag(this).addHeader("Content-Type", "application/json").post(new ContextRequestBody().setContent(uploadBean.getJsonStr())).build()).execute())) {
                this.mDataBaseUtil.deleJsonInfo(uploadBean.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getConfigInfo(ConfigRequestBean configRequestBean) {
        try {
            Response execute = new DzHttpClient().newCall(new Request.Builder().url(HttpUrlValue.getConfigUrl(configRequestBean)).tag("ConfigInfo").timeOut(configRequestBean.getTimeOut()).addHeader("Content-Type", "application/json").build()).execute();
            if (execute != null) {
                if (execute.getResponseCode() == 200) {
                    String string = execute.string();
                    String str = TAG;
                    LogUtil.d(str, "getInifInfo success -> " + string);
                    return string;
                }
                String str2 = TAG;
                LogUtil.d(str2, "getInifInfo error -> " + execute.getResponseCode());
                return null;
            }
            LogUtil.d(TAG, "uploadJson error -> response is null!");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendErrorData(String str) {
        try {
            if (executeHttp(this.mHttpClient.newCall(new Request.Builder().url(this.mHttpUrlValue.getErrorUrl(mUrl)).tag(this).addHeader("Content-Type", "application/json").post(new ContextRequestBody().setContent(str)).build()).execute())) {
                this.mDataBaseUtil.clearErrorNum();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadFile(UploadBean uploadBean) {
        LogUtil.d(TAG, "uploadFile...");
        try {
            File file = new File(uploadBean.getFilePath());
            if (!FileUtils.isFile(file)) {
                LogUtil.e(TAG, "uploadFile file is null, drop...");
            } else if (mRealUploadFileJsonList.contains(uploadBean.getJsonStr())) {
                LogUtil.e(TAG, "uploadFile file is duplicate, drop...");
            } else {
                mRealUploadFileJsonList.add(uploadBean.getJsonStr());
                if (executeHttp(this.mHttpClient.newCall(new Request.Builder().url(this.mHttpUrlValue.getUpfileUrl(mUrl)).tag(this).post(new FileRequestBody().setType(MultipartRequestBody.FORM).addFile(file).setFileJson(uploadBean.getJsonStr()).setEncodeCallback(this.mEncodeCallback)).build()).execute())) {
                    LogUtil.d(TAG, "uploadFile success");
                    this.mDataBaseUtil.deleFileInfo(uploadBean.getId());
                    if (uploadBean.isAutoDeleFile()) {
                        String str = TAG;
                        LogUtil.d(str, "uploadFile success, dele file = " + uploadBean.getFilePath());
                        FileUtils.deleteFile(uploadBean.getFilePath());
                    }
                    mRealUploadFileJsonList.remove(uploadBean.getJsonStr());
                    return;
                }
                LogUtil.d(TAG, "uploadFile error");
                if (mDeleFileWhenNetError) {
                    this.mDataBaseUtil.deleFileInfo(uploadBean.getId());
                    if (uploadBean.isAutoDeleFile()) {
                        String str2 = TAG;
                        LogUtil.d(str2, "uploadFile error, dele file = " + uploadBean.getFilePath());
                        FileUtils.deleteFile(uploadBean.getFilePath());
                    }
                }
                mRealUploadFileJsonList.remove(uploadBean.getJsonStr());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        try {
            mRealUploadFileJsonList.clear();
            this.mHttpClient.dispatcher().cancelAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void release() {
        cancel();
        this.mHttpClient = null;
        this.mDataBaseUtil = null;
        mRealUploadFileJsonList = null;
    }
}
