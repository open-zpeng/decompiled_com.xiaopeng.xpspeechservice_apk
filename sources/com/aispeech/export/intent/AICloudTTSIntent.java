package com.aispeech.export.intent;

import android.media.AudioAttributes;
/* loaded from: classes.dex */
public class AICloudTTSIntent {
    private boolean a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private float g;
    private String h;
    private String i;
    private int j;
    private AudioAttributes k;
    private boolean l;

    public boolean isRealBack() {
        return this.a;
    }

    public String getSpeaker() {
        return this.b;
    }

    public String getAudioType() {
        return this.c;
    }

    public String getMp3Quality() {
        return this.d;
    }

    public String getTextType() {
        return this.e;
    }

    public int getVolume() {
        return this.f;
    }

    public float getSpeed() {
        return this.g;
    }

    public String getSaveAudioPath() {
        return this.h;
    }

    public String getServer() {
        return this.i;
    }

    public int getStreamType() {
        return this.j;
    }

    public AudioAttributes getAudioAttributes() {
        return this.k;
    }

    public boolean isUseStreamType() {
        return this.l;
    }

    public void setRealBack(boolean z) {
        this.a = z;
    }

    public void setSpeaker(String str) {
        this.b = str;
    }

    public void setAudioType(String str) {
        this.c = str;
    }

    public void setMp3Quality(String str) {
        this.d = str;
    }

    public void setTextType(String str) {
        this.e = str;
    }

    public void setVolume(int i) {
        this.f = i;
    }

    public void setSpeed(float f) {
        this.g = f;
    }

    public void setSaveAudioPath(String str) {
        this.h = str;
    }

    public void setServer(String str) {
        this.i = str;
    }

    public void setStreamType(int i) {
        this.j = i;
    }

    public void setAudioAttributes(AudioAttributes audioAttributes) {
        this.k = audioAttributes;
    }

    public void setUseStreamType(boolean z) {
        this.l = z;
    }
}
