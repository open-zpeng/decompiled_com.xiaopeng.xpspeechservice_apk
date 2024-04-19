package com.aispeech.lite.e.a;

import com.aispeech.common.Log;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class a {
    private AtomicInteger a;
    private volatile boolean c = false;
    private volatile int[] b = new int[4];

    public a() {
        e();
        this.a = new AtomicInteger(0);
    }

    public final void a(int i) {
        synchronized (this) {
            this.c = true;
            b(i);
        }
    }

    public final void a() {
        this.c = false;
    }

    public final void b(int i) {
        if (this.c) {
            this.b[this.a.get()] = i;
            this.a.incrementAndGet();
        }
    }

    public final void b() {
        e();
        this.a.set(0);
    }

    public final int c() {
        return this.a.get();
    }

    public final int[] d() {
        int[] iArr = new int[this.a.get()];
        for (int i = 0; i < this.a.get(); i++) {
            if (this.b[i] >= 0) {
                iArr[i] = this.b[i];
            } else {
                Log.e("Counter", "drop illegal index! :" + this.b[i]);
            }
        }
        return iArr;
    }

    private void e() {
        for (int i = 0; i < this.b.length; i++) {
            this.b[i] = -1;
        }
    }
}
