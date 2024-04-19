package com.aispeech.export.intent;
/* loaded from: classes.dex */
public class AIDmaspIntent {
    private String a;
    private String b;
    private int c = 5000;

    public void setSaveAudioFilePath(String str) {
        this.a = str;
    }

    public String getSaveAudioFilePath() {
        return this.a;
    }

    public String getDumpWakeupAudioPath() {
        return this.b;
    }

    public void setDumpWakeupAudioPath(String str) {
        this.b = str;
    }

    public void setDumpWakeupTime(int i) {
        this.c = i;
    }

    public int getDumpWakeupTime() {
        return this.c;
    }
}
