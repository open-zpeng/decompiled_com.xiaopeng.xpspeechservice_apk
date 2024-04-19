package com.aispeech.export;
/* loaded from: classes.dex */
public enum ASRMode {
    MODE_ASR(1),
    MODE_HOTWORD(2),
    MODE_ASR_X(3);
    
    private final int a;

    ASRMode(int i) {
        this.a = i;
    }

    public final int getValue() {
        return this.a;
    }
}
