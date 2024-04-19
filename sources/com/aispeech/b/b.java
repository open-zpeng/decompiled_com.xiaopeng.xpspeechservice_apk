package com.aispeech.b;

import com.aispeech.common.Log;
import com.aispeech.lite.e;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public abstract class b implements Runnable {
    c a;
    private ScheduledExecutorService b;
    private int c;
    private volatile boolean d = false;

    abstract void a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(c cVar, int i) {
        this.a = cVar;
        this.c = i;
        this.d = false;
        this.b = new ScheduledThreadPoolExecutor(1, new e("HealthMonitor", 10));
    }

    public final void c() {
        if (this.b != null) {
            this.d = true;
            Log.d("HealthMonitor", "start health monitor , delay: 500 , period : " + this.c);
            this.b.scheduleAtFixedRate(this, 500L, (long) this.c, TimeUnit.MILLISECONDS);
        }
    }

    public final void d() {
        this.d = false;
    }

    public void b() {
        ScheduledExecutorService scheduledExecutorService = this.b;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
        this.a = null;
        this.d = false;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!this.d) {
            return;
        }
        try {
            a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
