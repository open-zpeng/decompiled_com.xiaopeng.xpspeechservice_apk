package com.aispeech.export.intent;

import com.aispeech.export.config.AIOneshotConfig;
/* loaded from: classes.dex */
public class AIWakeupIntent {
    private String[] a;
    private float[] b;
    private int[] c;
    private int[] d;
    private boolean e = true;
    private AIOneshotConfig f;

    public String[] getWakeupWords() {
        return this.a;
    }

    public float[] getThreshold() {
        return this.b;
    }

    public int[] getMajors() {
        return this.c;
    }

    public int[] getDcheck() {
        return this.d;
    }

    public boolean isInputContinuousAudio() {
        return this.e;
    }

    public AIOneshotConfig getAiOneshotConfig() {
        return this.f;
    }

    public void setWakeupWords(String[] strArr) {
        this.a = strArr;
    }

    public void setThreshold(float[] fArr) {
        this.b = fArr;
    }

    public void setMajors(int[] iArr) {
        this.c = iArr;
    }

    public void setDcheck(int[] iArr) {
        this.d = iArr;
    }

    public void setInputContinuousAudio(boolean z) {
        this.e = z;
    }

    public void setAiOneshotConfig(AIOneshotConfig aIOneshotConfig) {
        this.f = aIOneshotConfig;
    }
}
