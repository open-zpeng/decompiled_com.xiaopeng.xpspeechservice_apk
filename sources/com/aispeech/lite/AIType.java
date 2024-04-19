package com.aispeech.lite;
/* loaded from: classes.dex */
public enum AIType {
    DM("dm"),
    NLU("nlu"),
    ASR("asr");
    
    public String value;

    AIType(String str) {
        this.value = str;
    }
}
