package com.aispeech.lite.j;

import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.lite.h;
/* loaded from: classes.dex */
public final class d {
    private /* synthetic */ b a;

    public final void a(int i) {
        this.a.a(i);
    }

    public final void a(AIError aIError) {
        this.a.a(h.a.MSG_ERROR, aIError);
    }

    public final void a(AIResult aIResult) {
        this.a.a(h.a.MSG_RESULT, aIResult);
    }

    public final void a(int i, byte[] bArr, int i2) {
        this.a.x.a(i, bArr, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(b bVar) {
        this.a = bVar;
    }
}
