package com.aispeech.export.intent;
/* loaded from: classes.dex */
public class AIFespCarIntent {
    private String b;
    private String c;
    private boolean a = false;
    private int d = 5000;
    private boolean e = false;

    public void setSaveAudioFilePath(String str) {
        this.b = str;
    }

    public String getSaveAudioFilePath() {
        return this.b;
    }

    public void setUseCustomFeed(boolean z) {
        this.a = z;
    }

    public boolean isUseCustomFeed() {
        return this.a;
    }

    public String getDumpWakeupAudioPath() {
        return this.c;
    }

    public void setDumpWakeupAudioPath(String str) {
        this.c = str;
    }

    public void setDumpWakeupTime(int i) {
        this.d = i;
    }

    public int getDumpWakeupTime() {
        return this.d;
    }

    public boolean isAutoHoldBeamforming() {
        return this.e;
    }

    public void setAutoHoldBeamforming(boolean z) {
        this.e = z;
    }
}
