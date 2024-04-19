package com.aispeech.upload.http;
/* loaded from: classes.dex */
public class DzHttpClient {
    private final Dispatcher dispatcher;

    /* loaded from: classes.dex */
    public static class Builder {
        Dispatcher dispatcher = new Dispatcher();
    }

    public DzHttpClient() {
        this(new Builder());
    }

    public DzHttpClient(Builder builder) {
        this.dispatcher = builder.dispatcher;
    }

    public Call newCall(Request request) {
        return new RealCall(this, request);
    }

    public Dispatcher dispatcher() {
        return this.dispatcher;
    }
}
