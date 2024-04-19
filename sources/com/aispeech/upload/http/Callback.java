package com.aispeech.upload.http;
/* loaded from: classes.dex */
public interface Callback {
    void onFailure(Call call, Exception exc);

    void onResponse(Call call, Response response) throws Exception;

    void onStarted();
}
