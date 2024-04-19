package com.xiaopeng.xpspeechservice;

import android.app.Application;
import android.content.Context;
/* loaded from: classes.dex */
public class SpeechApp extends Application {
    private static SpeechApp sApp;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }

    public static Context getContext() {
        return sApp.getApplicationContext();
    }
}
