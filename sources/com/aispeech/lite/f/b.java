package com.aispeech.lite.f;

import java.util.concurrent.LinkedBlockingQueue;
/* loaded from: classes.dex */
public final class b {
    private LinkedBlockingQueue<a> a = new LinkedBlockingQueue<>();

    public final void a(a aVar) {
        try {
            this.a.put(aVar);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final a a() {
        try {
            return this.a.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void b() {
        this.a.clear();
    }
}
