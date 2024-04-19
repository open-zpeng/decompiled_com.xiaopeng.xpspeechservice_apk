package com.aispeech.lite.tts;
/* loaded from: classes.dex */
public final class i extends g {
    private byte[] a;

    public i(String str, byte[] bArr) {
        super(str);
        this.a = bArr;
    }

    @Override // com.aispeech.lite.tts.g
    public final void a(Object obj) {
        this.a = (byte[]) obj;
    }

    @Override // com.aispeech.lite.tts.g
    public final Object b() {
        return this.a;
    }
}
