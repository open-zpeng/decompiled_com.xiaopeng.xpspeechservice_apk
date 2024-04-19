package com.aispeech.upload.http;

import com.aispeech.upload.http.interceptor.InterceptorChain;
import com.aispeech.upload.http.interceptor.ServiceIntercepor;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class RealCall implements Call {
    private final DzHttpClient client;
    private boolean executed;
    private boolean isCancel;
    Request originalRequest;

    public RealCall(DzHttpClient dzHttpClient, Request request) {
        this.isCancel = false;
        this.isCancel = false;
        this.client = dzHttpClient;
        this.originalRequest = request;
    }

    @Override // com.aispeech.upload.http.Call
    public Request request() {
        return this.originalRequest;
    }

    @Override // com.aispeech.upload.http.Call
    public Response execute() throws IOException {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        return getResponseWithInterceptorChain();
    }

    @Override // com.aispeech.upload.http.Call
    public void enqueue(Callback callback) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        this.client.dispatcher().enqueue(new AsyncCall(callback));
    }

    @Override // com.aispeech.upload.http.Call
    public void cancel() {
        this.isCancel = true;
    }

    @Override // com.aispeech.upload.http.Call
    public boolean isExecuted() {
        return this.executed;
    }

    @Override // com.aispeech.upload.http.Call
    public boolean isCanceled() {
        return this.isCancel;
    }

    private Response getResponseWithInterceptorChain() throws IOException {
        Request request = request();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ServiceIntercepor());
        InterceptorChain interceptorChain = new InterceptorChain(arrayList, 0, request);
        try {
            if (isCanceled()) {
                return null;
            }
            return interceptorChain.next(request);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    HttpUrl redactedUrl() {
        return this.originalRequest.url().resolve("/...");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class AsyncCall extends NamedRunnable {
        private final Callback responseCallback;

        private AsyncCall(Callback callback) {
            super("OkHttp %s", RealCall.this.redactedUrl().toString());
            this.responseCallback = callback;
        }

        final Request request() {
            return RealCall.this.originalRequest;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final RealCall get() {
            return RealCall.this;
        }

        @Override // com.aispeech.upload.http.NamedRunnable
        protected final void execute() {
            if (RealCall.this.isCanceled()) {
                return;
            }
            Request request = request();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new ServiceIntercepor());
            InterceptorChain interceptorChain = new InterceptorChain(arrayList, 0, request);
            try {
                try {
                } catch (Exception e) {
                    this.responseCallback.onFailure(RealCall.this, e);
                }
                if (RealCall.this.isCanceled()) {
                    return;
                }
                this.responseCallback.onStarted();
                Response next = interceptorChain.next(request);
                if (next == null) {
                    this.responseCallback.onFailure(RealCall.this, new Exception("response == null"));
                } else if (next.getResponseCode() == 200) {
                    this.responseCallback.onResponse(RealCall.this, next);
                } else if (next.getResponseCode() < 400 || next.getResponseCode() >= 500) {
                    this.responseCallback.onFailure(RealCall.this, new Exception(next.string()));
                } else {
                    this.responseCallback.onResponse(RealCall.this, next);
                }
            } finally {
                RealCall.this.client.dispatcher().finished(this);
            }
        }
    }
}
