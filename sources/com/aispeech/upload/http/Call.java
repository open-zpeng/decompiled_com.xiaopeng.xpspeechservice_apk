package com.aispeech.upload.http;

import java.io.IOException;
/* loaded from: classes.dex */
public interface Call {

    /* loaded from: classes.dex */
    public interface Factory {
        Call newCall(Request request);
    }

    void cancel();

    void enqueue(Callback callback);

    Response execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    Request request();
}
