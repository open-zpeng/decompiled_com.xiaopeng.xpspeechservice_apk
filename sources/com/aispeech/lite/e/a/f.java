package com.aispeech.lite.e.a;

import com.aispeech.AIError;
/* loaded from: classes.dex */
public final class f {
    private /* synthetic */ d a;

    public final void a(int i) {
        if (d.a(this.a) != null) {
            d.a(this.a).onInit(i);
        }
    }

    public final void a(AIError aIError) {
        if (d.a(this.a) != null) {
            d.a(this.a).onError(aIError);
        }
    }

    public final void a(byte[] bArr) {
        if (d.a(this.a) != null) {
            d.a(this.a).onBufferReceived(bArr);
        }
    }

    public final void a() {
        if (d.a(this.a) != null) {
            d.a(this.a).onVadStart();
        }
    }

    public final void b(int i) {
        if (d.a(this.a) != null) {
            d.a(this.a).a(i);
        }
    }

    public final void b() {
        if (d.a(this.a) != null) {
            d.a(this.a).onVadEnd();
        }
    }

    private f(d dVar) {
        this.a = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ f(d dVar, byte b) {
        this(dVar);
    }
}
