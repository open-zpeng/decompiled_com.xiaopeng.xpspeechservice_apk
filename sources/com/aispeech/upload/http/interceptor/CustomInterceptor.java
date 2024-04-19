package com.aispeech.upload.http.interceptor;

import com.aispeech.upload.http.Response;
import com.aispeech.upload.http.interceptor.Interceptor;
/* loaded from: classes.dex */
public class CustomInterceptor implements Interceptor {
    @Override // com.aispeech.upload.http.interceptor.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        return chain.next(chain.request());
    }
}
