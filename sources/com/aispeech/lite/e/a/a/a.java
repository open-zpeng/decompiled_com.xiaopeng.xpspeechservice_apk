package com.aispeech.lite.e.a.a;

import com.aispeech.common.LimitQueue;
import com.aispeech.common.Log;
import com.aispeech.common.Util;
/* loaded from: classes.dex */
public final class a {
    private LimitQueue<Float> a;
    private LimitQueue<Float> b;
    private LimitQueue<Float> c;
    private LimitQueue<Float> d;

    public final void a(int i) {
        Log.d("NRmsEngine", "dms queue frame number : " + i);
        if (this.a == null) {
            this.a = new LimitQueue<>(i);
        }
        if (this.b == null) {
            this.b = new LimitQueue<>(i);
        }
        if (this.c == null) {
            this.c = new LimitQueue<>(i);
        }
        if (this.d == null) {
            this.d = new LimitQueue<>(i);
        }
    }

    public final void a() {
        if (!this.a.isEmpty()) {
            this.a.clear();
        }
        if (!this.b.isEmpty()) {
            this.b.clear();
        }
        if (!this.c.isEmpty()) {
            this.c.clear();
        }
        if (!this.d.isEmpty()) {
            this.d.clear();
        }
    }

    public final void a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        LimitQueue<Float> limitQueue = this.a;
        if (limitQueue != null) {
            limitQueue.offer(Float.valueOf((float) Util.calcVolume(Util.toShortArray(bArr))));
        }
        LimitQueue<Float> limitQueue2 = this.b;
        if (limitQueue2 != null) {
            limitQueue2.offer(Float.valueOf((float) Util.calcVolume(Util.toShortArray(bArr2))));
        }
        LimitQueue<Float> limitQueue3 = this.c;
        if (limitQueue3 != null) {
            limitQueue3.offer(Float.valueOf((float) Util.calcVolume(Util.toShortArray(bArr3))));
        }
        LimitQueue<Float> limitQueue4 = this.d;
        if (limitQueue4 != null) {
            limitQueue4.offer(Float.valueOf((float) Util.calcVolume(Util.toShortArray(bArr4))));
        }
    }

    public final void b() {
        LimitQueue<Float> limitQueue = this.a;
        if (limitQueue != null) {
            limitQueue.clear();
        }
        LimitQueue<Float> limitQueue2 = this.b;
        if (limitQueue2 != null) {
            limitQueue2.clear();
        }
        LimitQueue<Float> limitQueue3 = this.c;
        if (limitQueue3 != null) {
            limitQueue3.clear();
        }
        LimitQueue<Float> limitQueue4 = this.d;
        if (limitQueue4 != null) {
            limitQueue4.clear();
        }
    }

    public final void c() {
        LimitQueue<Float> limitQueue = this.a;
        if (limitQueue != null) {
            limitQueue.clear();
            this.a = null;
        }
        LimitQueue<Float> limitQueue2 = this.b;
        if (limitQueue2 != null) {
            limitQueue2.clear();
            this.b = null;
        }
        LimitQueue<Float> limitQueue3 = this.c;
        if (limitQueue3 != null) {
            limitQueue3.clear();
            this.c = null;
        }
        LimitQueue<Float> limitQueue4 = this.d;
        if (limitQueue4 != null) {
            limitQueue4.clear();
            this.d = null;
        }
    }

    public final float b(int i) {
        LimitQueue<Float> limitQueue;
        if (i == 0) {
            limitQueue = this.a;
        } else if (i == 1) {
            limitQueue = this.b;
        } else if (i == 2) {
            limitQueue = this.c;
        } else if (i == 3) {
            limitQueue = this.d;
        } else {
            limitQueue = null;
        }
        float f = 0.0f;
        if (limitQueue == null || limitQueue.isEmpty()) {
            return 0.0f;
        }
        LimitQueue m0clone = limitQueue.m0clone();
        int size = m0clone.size();
        while (!m0clone.isEmpty()) {
            f += ((Float) m0clone.poll()).floatValue();
        }
        return f / size;
    }
}
