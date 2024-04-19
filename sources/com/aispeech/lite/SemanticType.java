package com.aispeech.lite;
/* loaded from: classes.dex */
public enum SemanticType {
    NAVI(1),
    DUI(2),
    MIX_NAVI_DUI(3);
    
    private int a;

    SemanticType(int i) {
        this.a = i;
    }

    public final int getType() {
        return this.a;
    }
}
