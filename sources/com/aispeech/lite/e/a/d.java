package com.aispeech.lite.e.a;

import com.aispeech.lite.c.l;
import com.aispeech.lite.g;
import com.aispeech.lite.h.m;
/* loaded from: classes.dex */
public final class d extends g {
    private e e;
    private b f;

    public d(e eVar) {
        super("SSLKernel");
        this.e = eVar;
        this.f = new c();
    }

    @Override // com.aispeech.lite.g, java.lang.Runnable
    public final void run() {
        super.run();
        while (true) {
            com.aispeech.lite.f.a b = b();
            if (b != null) {
                int i = b.a;
                if (i == 1) {
                    l lVar = (l) b.b;
                    b bVar = this.f;
                    if (bVar != null) {
                        bVar.a(lVar, new f(this, (byte) 0));
                    }
                } else if (i == 2) {
                    m mVar = (m) b.b;
                    b bVar2 = this.f;
                    if (bVar2 != null) {
                        bVar2.a(mVar);
                    }
                } else if (i == 3) {
                    b bVar3 = this.f;
                    if (bVar3 != null) {
                        bVar3.a();
                    }
                } else if (i == 7) {
                    b bVar4 = this.f;
                    if (bVar4 != null) {
                        bVar4.b();
                    }
                } else if (i == 9) {
                    byte[] bArr = (byte[]) b.b;
                    b bVar5 = this.f;
                    if (bVar5 != null) {
                        bVar5.a(bArr);
                    }
                }
            } else {
                return;
            }
        }
    }
}
