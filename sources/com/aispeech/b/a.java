package com.aispeech.b;

import com.aispeech.common.LimitQueue;
import com.aispeech.common.Log;
import java.util.Iterator;
/* loaded from: classes.dex */
public final class a extends b {
    private volatile LimitQueue<byte[]> b;

    public final void a(c cVar, int i, int i2) {
        super.a(cVar, i2);
        this.b = new LimitQueue<>((int) Math.ceil(3.0d / (3200.0d / ((i / 2.0d) * 32000.0d))));
    }

    public final void a(byte[] bArr) {
        if (this.b != null) {
            this.b.offer(bArr);
        }
    }

    @Override // com.aispeech.b.b
    final void a() {
        if (this.b != null && this.b.size() != 0) {
            if (b(this.b.getLast())) {
                Log.d("EchoMonitor", "find unhealthy point , start health check");
                double d = 0.0d;
                Iterator<byte[]> it = this.b.iterator();
                while (it.hasNext()) {
                    if (b(it.next())) {
                        d += 1.0d;
                    }
                }
                if (d / this.b.getLimit() >= 0.4d) {
                    this.b.clear();
                    if (this.a != null) {
                        this.a.a();
                        return;
                    }
                    return;
                }
                return;
            }
            this.b.clear();
        }
    }

    @Override // com.aispeech.b.b
    public final void b() {
        super.b();
        if (this.b != null) {
            this.b.clear();
            this.b = null;
        }
    }

    private static boolean b(byte[] bArr) {
        double d = 0.0d;
        for (int i = 0; i < bArr.length; i += 2) {
            if (Math.abs((int) ((short) (bArr[i] | (bArr[i + 1] << 8)))) >= 32760) {
                d += 1.0d;
            }
        }
        return d / ((double) (bArr.length / 2)) >= 0.4d;
    }
}
