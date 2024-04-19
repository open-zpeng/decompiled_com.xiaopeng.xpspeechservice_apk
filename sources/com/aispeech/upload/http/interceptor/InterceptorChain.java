package com.aispeech.upload.http.interceptor;

import com.aispeech.upload.http.Request;
import com.aispeech.upload.http.Response;
import com.aispeech.upload.http.interceptor.Interceptor;
import java.util.List;
/* loaded from: classes.dex */
public class InterceptorChain implements Interceptor.Chain {
    private int index;
    private List<Interceptor> interceptorList;
    private Request request;

    public InterceptorChain(List<Interceptor> list, int i, Request request) {
        this.interceptorList = list;
        this.index = i;
        this.request = request;
    }

    @Override // com.aispeech.upload.http.interceptor.Interceptor.Chain
    public Request request() {
        return this.request;
    }

    @Override // com.aispeech.upload.http.interceptor.Interceptor.Chain
    public Response next(Request request) throws Exception {
        return this.interceptorList.get(this.index).intercept(new InterceptorChain(this.interceptorList, this.index + 1, request));
    }
}
