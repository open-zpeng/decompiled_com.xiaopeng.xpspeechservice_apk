package com.aispeech.lite.tts;

import java.io.File;
/* loaded from: classes.dex */
public final class j extends g {
    private File a;

    public j() {
        super(null);
        this.a = null;
    }

    @Override // com.aispeech.lite.tts.g
    public final void a(Object obj) {
        this.a = (File) obj;
    }

    @Override // com.aispeech.lite.tts.g
    public final Object b() {
        return this.a;
    }
}
