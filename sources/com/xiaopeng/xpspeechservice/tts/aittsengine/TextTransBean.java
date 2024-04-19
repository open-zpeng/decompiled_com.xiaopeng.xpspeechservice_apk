package com.xiaopeng.xpspeechservice.tts.aittsengine;
/* loaded from: classes.dex */
public class TextTransBean {
    private String origin;
    private String trans;

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTrans() {
        return this.trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String toString() {
        return "TextTransBean origin " + this.origin + " trans " + this.trans;
    }
}
