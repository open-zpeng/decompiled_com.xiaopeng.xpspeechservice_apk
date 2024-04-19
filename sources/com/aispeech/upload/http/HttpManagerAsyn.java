package com.aispeech.upload.http;

import com.aispeech.upload.database.DataBaseUtil;
import com.aispeech.upload.database.UploadBean;
import com.aispeech.upload.http.Request;
import com.aispeech.upload.util.FileUtils;
import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class HttpManagerAsyn {
    private DataBaseUtil mDataBaseUtil;
    private DzHttpClient mHttpClient = new DzHttpClient();
    private HttpUrlValue mHttpUrlValue;
    private static String mUrl = null;
    public static volatile CopyOnWriteArrayList<String> mRealUploadFilePathList = new CopyOnWriteArrayList<>();

    public HttpManagerAsyn() {
        mRealUploadFilePathList.clear();
    }

    public void setDataBaseUtil(DataBaseUtil dataBaseUtil) {
        this.mDataBaseUtil = dataBaseUtil;
    }

    public void setHttpUrlValue(HttpUrlValue httpUrlValue) {
        this.mHttpUrlValue = httpUrlValue;
    }

    public void setHttpUrl(String str) {
        mUrl = str;
    }

    public void sendData(final UploadBean uploadBean) {
        this.mHttpClient.newCall(new Request.Builder().url(this.mHttpUrlValue.getJsonUrl(mUrl)).tag(this).addHeader("Content-Type", "application/json").post(new ContextRequestBody().setContent(uploadBean.getJsonStr())).build()).enqueue(new CallbackImpl() { // from class: com.aispeech.upload.http.HttpManagerAsyn.1
            @Override // com.aispeech.upload.http.CallbackImpl, com.aispeech.upload.http.Callback
            public void onFailure(Call call, Exception exc) {
                super.onFailure(call, exc);
            }

            @Override // com.aispeech.upload.http.CallbackImpl, com.aispeech.upload.http.Callback
            public void onResponse(Call call, Response response) {
                super.onResponse(call, response);
                HttpManagerAsyn.this.mDataBaseUtil.deleJsonInfo(uploadBean.getId());
            }
        });
    }

    public void sendErrorData(String str) {
        this.mHttpClient.newCall(new Request.Builder().url(this.mHttpUrlValue.getErrorUrl(mUrl)).tag(this).addHeader("Content-Type", "application/json").post(new ContextRequestBody().setContent(str)).build()).enqueue(new CallbackImpl() { // from class: com.aispeech.upload.http.HttpManagerAsyn.2
            @Override // com.aispeech.upload.http.CallbackImpl, com.aispeech.upload.http.Callback
            public void onFailure(Call call, Exception exc) {
                super.onFailure(call, exc);
            }

            @Override // com.aispeech.upload.http.CallbackImpl, com.aispeech.upload.http.Callback
            public void onResponse(Call call, Response response) {
                super.onResponse(call, response);
                HttpManagerAsyn.this.mDataBaseUtil.clearErrorNum();
            }
        });
    }

    public void uploadFile(final UploadBean uploadBean) {
        File file = new File(uploadBean.getJsonStr());
        if (!FileUtils.isFile(file) || mRealUploadFilePathList.contains(uploadBean.getJsonStr())) {
            return;
        }
        mRealUploadFilePathList.add(uploadBean.getJsonStr());
        this.mHttpClient.newCall(new Request.Builder().url(this.mHttpUrlValue.getUpfileUrl(mUrl)).tag(this).post(new FileRequestBody().setType(MultipartRequestBody.FORM).addFile(file)).build()).enqueue(new CallbackImpl() { // from class: com.aispeech.upload.http.HttpManagerAsyn.3
            @Override // com.aispeech.upload.http.CallbackImpl, com.aispeech.upload.http.Callback
            public void onStarted() {
            }

            @Override // com.aispeech.upload.http.CallbackImpl, com.aispeech.upload.http.Callback
            public void onFailure(Call call, Exception exc) {
                super.onFailure(call, exc);
                HttpManagerAsyn.mRealUploadFilePathList.remove(uploadBean.getJsonStr());
            }

            @Override // com.aispeech.upload.http.CallbackImpl, com.aispeech.upload.http.Callback
            public void onResponse(Call call, Response response) {
                super.onResponse(call, response);
                HttpManagerAsyn.this.mDataBaseUtil.deleFileInfo(uploadBean.getId());
                if (uploadBean.isAutoDeleFile()) {
                    FileUtils.deleteFile(uploadBean.getJsonStr());
                }
                HttpManagerAsyn.mRealUploadFilePathList.remove(uploadBean.getJsonStr());
            }
        });
    }

    public void cancel() {
        mRealUploadFilePathList.clear();
        this.mHttpClient.dispatcher().cancelAll();
    }
}
