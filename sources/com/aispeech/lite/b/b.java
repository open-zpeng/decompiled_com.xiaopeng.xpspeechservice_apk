package com.aispeech.lite.b;

import com.aispeech.common.Log;
import com.aispeech.common.Util;
import com.aispeech.lite.AISampleRate;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes.dex */
public final class b implements e {
    private static a b;
    private long c;
    private static int a = 0;
    private static Lock d = new ReentrantLock();
    private static int e = 0;

    public static b a(AISampleRate aISampleRate, int i, int i2, d dVar) {
        return new b(aISampleRate, i, i2, dVar);
    }

    private b(AISampleRate aISampleRate, int i, int i2, d dVar) {
        this.c = 1000000000L;
        d.lock();
        this.c = Util.generateRandom(8);
        if (e()) {
            b = new a(aISampleRate, i, i2, dVar);
        }
        a++;
        Log.d("AIAudioRecorderProxy", "A proxy create, TokenId: " + this.c + ", increase RefCount, current : " + a);
        d.unlock();
    }

    @Override // com.aispeech.lite.b.e
    public final long a(d dVar) {
        int i = e;
        if (i == 0) {
            e = i + 1;
        }
        return b.b(dVar);
    }

    @Override // com.aispeech.lite.b.e
    public final void b(d dVar) {
        b.c(dVar);
    }

    @Override // com.aispeech.lite.b.e
    public final void a() {
        d.lock();
        try {
            if (this.c != 1000000000) {
                a--;
                Log.d("AIAudioRecorderProxy", "A proxy release, TokenId: " + this.c + ", decrease RefCount, current : " + a);
                if (e()) {
                    Log.i("AIAudioRecorderProxy", "refCountEqualZero releaseRecorder");
                    b.b();
                    e = 0;
                }
                this.c = 1000000000L;
            }
        } finally {
            d.unlock();
        }
    }

    @Override // com.aispeech.lite.b.e
    public final AISampleRate b() {
        return b.c();
    }

    @Override // com.aispeech.lite.b.e
    public final int c() {
        return a.d();
    }

    @Override // com.aispeech.lite.b.e
    public final int d() {
        return a.e();
    }

    @Override // com.aispeech.lite.b.e
    public final boolean c(d dVar) {
        return b.a() && b.a(dVar);
    }

    private static boolean e() {
        return a == 0;
    }
}
