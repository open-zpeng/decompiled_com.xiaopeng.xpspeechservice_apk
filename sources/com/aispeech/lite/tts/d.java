package com.aispeech.lite.tts;

import com.aispeech.AIError;
import com.aispeech.common.Log;
import com.aispeech.kernel.Utils;
/* loaded from: classes.dex */
public final class d extends com.aispeech.lite.g {
    private String e;
    private com.aispeech.lite.h.f f;
    private m g;
    private a h;
    private volatile boolean i;
    private com.aispeech.lite.g.a j;

    public d(m mVar) {
        super("CloudTtsKernel");
        this.i = false;
        this.g = mVar;
    }

    @Override // com.aispeech.lite.g, java.lang.Runnable
    public final void run() {
        boolean z;
        super.run();
        do {
            com.aispeech.lite.f.a b = b();
            if (b != null) {
                int i = b.a;
                z = true;
                if (i == 1) {
                    this.j = new com.aispeech.lite.g.a();
                    this.j.a(this.d.g());
                    this.h = new a(this, (byte) 0);
                    this.g.a(0);
                } else if (i == 2) {
                    this.f = (com.aispeech.lite.h.f) b.b;
                    this.e = Utils.get_recordid();
                    this.f.l(this.e);
                    this.j.a(this.f, this.h);
                    this.i = true;
                } else if (i != 3) {
                    if (i == 4) {
                        if (this.i) {
                            this.i = false;
                        }
                        com.aispeech.lite.g.a aVar = this.j;
                        if (aVar != null) {
                            aVar.a();
                        }
                    } else if (i == 7) {
                        Log.d("CloudTtsKernel", "MSG_RELEASE");
                        com.aispeech.lite.g.a aVar2 = this.j;
                        if (aVar2 != null) {
                            aVar2.b();
                            this.j = null;
                        }
                        if (this.h != null) {
                            this.h = null;
                        }
                        Log.d("CloudTtsKernel", "MSG_RELEASE END");
                        continue;
                    } else if (i == 8) {
                        this.g.a((AIError) b.b);
                    } else if (i != 9) {
                    }
                } else if (this.i) {
                    this.i = false;
                }
                z = false;
                continue;
            } else {
                return;
            }
        } while (!z);
        a();
    }

    /* loaded from: classes.dex */
    class a implements com.aispeech.lite.g.b {
        private a() {
        }

        /* synthetic */ a(d dVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.g.b
        public final void a(byte[] bArr, int i) {
            if (d.this.g != null) {
                d.this.g.a(bArr, i);
            }
        }

        @Override // com.aispeech.lite.g.b
        public final void a(AIError aIError) {
            d.this.a(new com.aispeech.lite.f.a(8, aIError));
        }
    }
}
