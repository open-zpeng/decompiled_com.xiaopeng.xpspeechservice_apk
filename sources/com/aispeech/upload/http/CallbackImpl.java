package com.aispeech.upload.http;

import com.aispeech.upload.util.LogUtil;
import com.aispeech.upload.util.Tag;
/* loaded from: classes.dex */
public class CallbackImpl implements Callback {
    private static final String TAG = Tag.getTag("HTTP");

    @Override // com.aispeech.upload.http.Callback
    public void onFailure(Call call, Exception exc) {
        try {
            String message = exc.getMessage();
            String str = TAG;
            LogUtil.d(str, "upload failed --> " + message);
        } catch (Exception e) {
        }
    }

    @Override // com.aispeech.upload.http.Callback
    public void onResponse(Call call, Response response) {
        try {
            String str = TAG;
            LogUtil.d(str, "upload success -> " + response.string());
        } catch (Exception e) {
        }
    }

    @Override // com.aispeech.upload.http.Callback
    public void onStarted() {
    }
}
