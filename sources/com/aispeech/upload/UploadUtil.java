package com.aispeech.upload;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.aispeech.upload.util.LogUtil;
import com.aispeech.upload.util.Tag;
/* loaded from: classes.dex */
public class UploadUtil {
    private static String TAG = Tag.getTag(null);
    private static final int UPLOAD_FILE = 2;
    private static final int UPLOAD_JSON = 1;
    private static final int UPLOAD_START = 3;
    private static final int UPLOAD_STOP = 4;
    private Handler mHandler;
    private HandlerThread mHandlerThread = new HandlerThread("upload");
    private volatile boolean mIsInit;
    private UploadProxy mProxy;

    private UploadUtil(Context context, InitParams initParams, EncodeCallback encodeCallback) {
        this.mIsInit = false;
        this.mProxy = new UploadProxy(context, initParams, encodeCallback);
        String str = TAG;
        LogUtil.d(str, "init = " + initParams.getConstantValue().toString());
        initHandlerThread();
        this.mIsInit = true;
    }

    private void initHandlerThread() {
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.aispeech.upload.UploadUtil.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    UploadUtil.this.mProxy.addJsonInfo(message.getData().getString("UploadJson"));
                } else if (i == 2) {
                    UploadUtil.this.mProxy.addFileInfo(message.getData().getString("UploadFile"));
                } else if (i == 3) {
                    UploadUtil.this.mProxy.start();
                } else if (i == 4) {
                    UploadUtil.this.mProxy.stop();
                }
            }
        };
    }

    public static UploadUtil init(Context context, InitParams initParams) {
        return new UploadUtil(context, initParams, null);
    }

    public static UploadUtil init(Context context, InitParams initParams, EncodeCallback encodeCallback) {
        return new UploadUtil(context, initParams, encodeCallback);
    }

    public synchronized void cacheData(ModelBuilder modelBuilder) {
        if (!this.mIsInit) {
            LogUtil.e(TAG, "must init first...");
            return;
        }
        String json = this.mProxy.getJson(modelBuilder);
        String str = TAG;
        LogUtil.d(str, "cacheData jsonStr = " + json);
        Message message = new Message();
        message.what = 1;
        Bundle bundle = new Bundle();
        bundle.putString("UploadJson", json);
        message.setData(bundle);
        this.mHandler.sendMessage(message);
    }

    public synchronized void cacheFile(FileBuilder fileBuilder) {
        if (!this.mIsInit) {
            LogUtil.e(TAG, "must init first...");
            return;
        }
        String json = fileBuilder.getJson();
        String str = TAG;
        LogUtil.d(str, "cacheFile fileBuilder = " + json);
        Message message = new Message();
        message.what = 2;
        Bundle bundle = new Bundle();
        bundle.putString("UploadFile", json);
        message.setData(bundle);
        this.mHandler.sendMessage(message);
    }

    public synchronized void cacheFile(String str) {
        FileBuilder fileBuilder = new FileBuilder();
        fileBuilder.setPath(str);
        cacheFile(fileBuilder);
    }

    public synchronized void start() {
        if (!this.mIsInit) {
            LogUtil.e(TAG, "must init first...");
            return;
        }
        LogUtil.d(TAG, "start...");
        this.mProxy.stop();
        this.mHandler.sendEmptyMessage(3);
    }

    public synchronized void stop() {
        if (!this.mIsInit) {
            LogUtil.e(TAG, "must init first...");
            return;
        }
        LogUtil.d(TAG, "stop...");
        this.mProxy.stop();
    }

    public synchronized void release() {
        LogUtil.d(TAG, "release...");
        this.mIsInit = false;
        try {
            this.mHandlerThread.quit();
            this.mProxy.stop();
            this.mProxy.release();
            this.mProxy = null;
            this.mHandlerThread = null;
        } catch (Exception e) {
            e.printStackTrace();
            this.mProxy = null;
            this.mHandlerThread = null;
        }
        this.mHandler = null;
    }

    public static String getConfigInfo(Context context, ConfigRequestBean configRequestBean) {
        LogUtil.openLog(null);
        String configInfo = UploadProxy.getConfigInfo(context, configRequestBean);
        String str = TAG;
        LogUtil.d(str, "getConfigInfo = " + configInfo);
        return configInfo;
    }
}
