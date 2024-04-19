package com.aispeech.export.intent;

import android.media.AudioAttributes;
/* loaded from: classes.dex */
public class AILocalTTSIntent {
    private String a;
    private float b;
    private int c;
    private boolean d;
    private int e;
    private AudioAttributes f;
    private boolean g;
    private String h;

    public String getBackResBin() {
        return this.a;
    }

    public void setBackResBin(String str) {
        this.a = str;
    }

    public float getSpeechRate() {
        return this.b;
    }

    public void setSpeechRate(float f) {
        this.b = f;
    }

    public int getSpeechVolume() {
        return this.c;
    }

    public void setSpeechVolume(int i) {
        this.c = i;
    }

    public boolean isUseSSML() {
        return this.d;
    }

    public void setUseSSML(boolean z) {
        this.d = z;
    }

    public int getStreamType() {
        return this.e;
    }

    public void setStreamType(int i) {
        this.e = i;
    }

    public AudioAttributes getAudioAttributes() {
        return this.f;
    }

    public void setAudioAttributes(AudioAttributes audioAttributes) {
        this.f = audioAttributes;
    }

    public boolean isUseStreamType() {
        return this.g;
    }

    public void setUseStreamType(boolean z) {
        this.g = z;
    }

    public String getSaveAudioFileName() {
        return this.h;
    }

    public void setSaveAudioFileName(String str) {
        this.h = str;
    }
}
