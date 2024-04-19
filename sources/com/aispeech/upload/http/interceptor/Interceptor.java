package com.aispeech.upload.http.interceptor;

import com.aispeech.upload.http.Request;
import com.aispeech.upload.http.Response;
/* loaded from: classes.dex */
public interface Interceptor {

    /* loaded from: classes.dex */
    public interface Chain {
        Response next(Request request) throws Exception;

        Request request();
    }

    Response intercept(Chain chain) throws Exception;
}
