package com.aispeech.lite;

import com.aispeech.common.Log;
import com.aispeech.export.Vocab;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.h.m;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/* loaded from: classes.dex */
public class g implements Runnable {
    protected com.aispeech.auth.d d;
    private String e;
    private e f;
    private ExecutorService g;
    private Semaphore h = new Semaphore(0);
    protected volatile a c = a.STATE_IDLE;
    protected com.aispeech.lite.f.b a = new com.aispeech.lite.f.b();
    protected b b = new b();

    /* loaded from: classes.dex */
    public enum a {
        STATE_IDLE,
        STATE_NEWED,
        STATE_RUNNING,
        STATE_WAITING,
        STATE_ERROR,
        STATE_CANCELED
    }

    public g(String str) {
        this.e = "BaseKernel";
        this.e = str;
        this.f = new e(this.e, 5);
        this.g = Executors.newSingleThreadExecutor(this.f);
        this.g.execute(this);
    }

    public void startKernel(m mVar) {
        Log.d(this.e, "startKernel");
        try {
            a(new com.aispeech.lite.f.a(2, mVar.clone()));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void newKernel(com.aispeech.lite.a aVar) {
        Log.d(this.e, "newKernel");
        try {
            a(new com.aispeech.lite.f.a(1, aVar.clone()));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void set(String str) {
        a(new com.aispeech.lite.f.a(19, str));
    }

    public void update(String str) {
        Log.d(this.e, "updateKernel");
        a(new com.aispeech.lite.f.a(21, str));
    }

    public void close() {
        Log.d(this.e, "close");
        a(new com.aispeech.lite.f.a(24));
    }

    public void feedback$66b2602d(com.aispeech.auth.c cVar) {
        Log.d(this.e, "feedback");
        a(new com.aispeech.lite.f.a(23, cVar));
    }

    public void feedback2PRIVCloud(com.aispeech.lite.h.g gVar) {
        Log.d(this.e, "feedback2PRIVCloud");
        a(new com.aispeech.lite.f.a(27, gVar));
    }

    public void triggerIntent$26e49d42(com.aispeech.auth.c cVar) {
        Log.d(this.e, "triggerIntent");
        a(new com.aispeech.lite.f.a(25, cVar));
    }

    public void async$20047b9e(com.aispeech.auth.c cVar) {
        Log.d(this.e, "async");
        a(new com.aispeech.lite.f.a(26, cVar));
    }

    public void updateVocab(String str) {
        Log.d(this.e, "updateVocab");
        a(new com.aispeech.lite.f.a(22, str));
    }

    public void updateVocab(Vocab vocab) {
        Log.d(this.e, "updateVocab");
        a(new com.aispeech.lite.f.a(22, vocab));
    }

    public void feed(byte[] bArr) {
        a(new com.aispeech.lite.f.a(9, bArr));
    }

    public void cancelKernel() {
        Log.d(this.e, "clear message in queue");
        com.aispeech.lite.f.b bVar = this.a;
        if (bVar != null) {
            bVar.b();
        }
        Log.d(this.e, "cancelKernel");
        a(new com.aispeech.lite.f.a(4));
    }

    public void stopKernel() {
        Log.d(this.e, "stopKernel");
        a(new com.aispeech.lite.f.a(3));
    }

    public void releaseKernel() {
        Log.d(this.e, "releaseKernel");
        a(new com.aispeech.lite.f.a(7));
        try {
            Log.i(this.e, "Semaphore acquire before");
            this.h.acquire();
            Log.i(this.e, "Semaphore acquire end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.g.shutdown();
        this.g = null;
        if (this.f != null) {
            this.f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a() {
        com.aispeech.lite.f.b bVar = this.a;
        if (bVar != null) {
            bVar.b();
            this.a = null;
        }
        this.h.release();
        Log.i(this.e, "Semaphore release");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(com.aispeech.lite.f.a aVar) {
        com.aispeech.lite.f.b bVar = this.a;
        if (bVar != null) {
            bVar.a(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.aispeech.lite.f.a b() {
        com.aispeech.lite.f.b bVar = this.a;
        if (bVar != null) {
            return bVar.a();
        }
        return null;
    }

    public synchronized int getValueOf(String str) {
        return -1;
    }

    public String getNewConf() {
        return "";
    }

    public String getStartConf() {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(String str) {
        String str2 = this.e;
        Log.w(str2, "Invalid State：" + this.c.name() + " when MSG: " + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(a aVar) {
        String str = this.e;
        Log.d(str, "transfer:" + this.c + " to:" + aVar);
        this.c = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = c.u;
        String str = this.e;
        Log.d(str, "SET_THREAD_AFFINITY cpuId is : " + i);
        if (i > 0) {
            Utils.jni_duilite_set_thread_affinity(i);
        }
    }

    public com.aispeech.auth.d getProfile() {
        return this.d;
    }

    public void setProfile(com.aispeech.auth.d dVar) {
        this.d = dVar;
    }
}
