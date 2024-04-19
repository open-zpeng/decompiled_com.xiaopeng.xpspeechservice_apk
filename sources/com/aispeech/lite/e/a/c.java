package com.aispeech.lite.e.a;

import com.aispeech.AIError;
import com.aispeech.common.AITimer;
import com.aispeech.common.Log;
import com.aispeech.lite.c.l;
import com.aispeech.lite.h.m;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
/* loaded from: classes.dex */
public final class c implements com.aispeech.lite.e.a.b {
    private f d;
    private b f;
    private boolean e = false;
    private com.aispeech.lite.e.a.a.b a = new com.aispeech.lite.e.a.a.c();
    private com.aispeech.lite.e.a.a.a b = new com.aispeech.lite.e.a.a.a();
    private com.aispeech.lite.e.a.a c = new com.aispeech.lite.e.a.a();

    static /* synthetic */ void c(c cVar) {
        b bVar = cVar.f;
        if (bVar != null) {
            bVar.cancel();
            cVar.f = null;
        }
        cVar.f = new b(cVar, (byte) 0);
        AITimer.getInstance().schedule(cVar.f, 300L);
    }

    @Override // com.aispeech.lite.e.a.b
    public final void a(l lVar, f fVar) {
        this.d = fVar;
        com.aispeech.lite.e.a.a.b bVar = this.a;
        if (bVar != null) {
            bVar.a(lVar, new a(this, (byte) 0));
        }
        com.aispeech.lite.e.a.a.a aVar = this.b;
        if (aVar != null) {
            aVar.a((int) Math.ceil(600.0d / lVar.k()));
        }
    }

    @Override // com.aispeech.lite.e.a.b
    public final void a(m mVar) {
        com.aispeech.lite.e.a.a.b bVar = this.a;
        if (bVar != null) {
            bVar.a(mVar);
        }
        com.aispeech.lite.e.a.a.a aVar = this.b;
        if (aVar != null) {
            aVar.a();
        }
        com.aispeech.lite.e.a.a aVar2 = this.c;
        if (aVar2 != null) {
            aVar2.b();
        }
        this.e = true;
    }

    @Override // com.aispeech.lite.e.a.b
    public final void a() {
        com.aispeech.lite.e.a.a.b bVar = this.a;
        if (bVar != null) {
            bVar.a();
        }
        com.aispeech.lite.e.a.a.a aVar = this.b;
        if (aVar != null) {
            aVar.b();
        }
        com.aispeech.lite.e.a.a aVar2 = this.c;
        if (aVar2 != null) {
            aVar2.b();
        }
        this.e = false;
    }

    @Override // com.aispeech.lite.e.a.b
    public final void a(byte[] bArr) {
        if (this.e) {
            byte[] bArr2 = new byte[bArr.length / 4];
            byte[] bArr3 = new byte[bArr.length / 4];
            byte[] bArr4 = new byte[bArr.length / 4];
            byte[] bArr5 = new byte[bArr.length / 4];
            for (int i = 0; i < 4; i++) {
                byte[] bArr6 = new byte[bArr.length / 4];
                for (int i2 = 0; i2 < bArr.length / 8; i2++) {
                    int i3 = i2 * 2;
                    int i4 = ((i2 << 2) + i) * 2;
                    bArr6[i3] = bArr[i4];
                    bArr6[i3 + 1] = bArr[i4 + 1];
                }
                if (i == 0) {
                    bArr5 = bArr6;
                } else if (i == 1) {
                    bArr4 = bArr6;
                } else if (i == 2) {
                    bArr3 = bArr6;
                } else {
                    bArr2 = bArr6;
                }
            }
            com.aispeech.lite.e.a.a.b bVar = this.a;
            if (bVar != null) {
                bVar.a(bArr5, bArr4, bArr3, bArr2);
            }
            com.aispeech.lite.e.a.a.a aVar = this.b;
            if (aVar != null) {
                aVar.a(bArr5, bArr4, bArr3, bArr2);
                return;
            }
            return;
        }
        Log.e("SSLByVadEngine", "drop not begin feed ...");
    }

    @Override // com.aispeech.lite.e.a.b
    public final void b() {
        com.aispeech.lite.e.a.a.b bVar = this.a;
        if (bVar != null) {
            bVar.b();
            this.a = null;
        }
        com.aispeech.lite.e.a.a.a aVar = this.b;
        if (aVar != null) {
            aVar.c();
            this.b = null;
        }
        com.aispeech.lite.e.a.a aVar2 = this.c;
        if (aVar2 != null) {
            aVar2.b();
            this.c = null;
        }
    }

    /* loaded from: classes.dex */
    class a implements com.aispeech.lite.e.a.a.d {
        private a() {
        }

        /* synthetic */ a(c cVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.e.a.a.d
        public final void a(int i) {
            if (c.this.d != null) {
                c.this.d.a(i);
            }
        }

        @Override // com.aispeech.lite.e.a.a.d
        public final void b(int i) {
            synchronized (this) {
                Log.d("SSLByVadEngine", "vad  begin index " + i);
                if (c.this.c.c() == 0) {
                    if (c.this.d != null) {
                        c.this.d.a();
                    }
                    c.this.c.a(i);
                    c.c(c.this);
                } else {
                    c.this.c.b(i);
                }
            }
        }

        @Override // com.aispeech.lite.e.a.a.d
        public final void a() {
            if (c.this.d != null) {
                c.this.d.b();
            }
        }

        @Override // com.aispeech.lite.e.a.a.d
        public final void a(byte[] bArr) {
            if (c.this.d != null) {
                c.this.d.a(bArr);
            }
        }

        @Override // com.aispeech.lite.e.a.a.d
        public final void a(AIError aIError) {
            if (c.this.d != null) {
                c.this.d.a(aIError);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends TimerTask {
        private b() {
        }

        /* synthetic */ b(c cVar, byte b) {
            this();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            c.this.c.a();
            int[] d = c.this.c.d();
            int length = d.length;
            HashMap hashMap = new HashMap(length);
            for (int i = 0; i < length; i++) {
                int i2 = d[i];
                double b = c.this.b.b(i2) * 0.9d;
                double d2 = (9.999999999999998d / length) * (length - i);
                Log.d("SSLByVadEngine", "index : " + i2 + " ,orderWeight: " + d2 + " ,dmsWeight: " + b);
                hashMap.put(Integer.valueOf(i2), Double.valueOf(d2 + b));
            }
            int a = a(hashMap);
            Log.d("SSLByVadEngine", "choice ssl:" + a);
            if (c.this.a != null) {
                c.this.a.a(a);
            }
            if (c.this.d != null) {
                c.this.d.b(a);
            }
        }

        private static int a(Map<Integer, Double> map) {
            int i = 0;
            double d = 0.0d;
            for (Map.Entry<Integer, Double> entry : map.entrySet()) {
                if (entry.getValue().doubleValue() > d) {
                    i = entry.getKey().intValue();
                    d = entry.getValue().doubleValue();
                }
            }
            return i;
        }
    }
}
