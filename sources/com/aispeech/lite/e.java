package com.aispeech.lite;

import java.util.concurrent.ThreadFactory;
/* loaded from: classes.dex */
public final class e implements ThreadFactory {
    private String a;
    private int b;

    public e(String str, int i) {
        this.b = 5;
        this.a = "t-" + str;
        this.b = i;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.a);
        if (thread.isDaemon()) {
            thread.setDaemon(true);
        }
        int priority = thread.getPriority();
        int i = this.b;
        if (priority != i) {
            thread.setPriority(i);
        }
        return thread;
    }
}
