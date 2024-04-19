package com.aispeech.export.intent;

import com.aispeech.lite.oneshot.OneshotCache;
/* loaded from: classes.dex */
public class AICloudASRIntent {
    private int a;
    private int b;
    private int c;
    private OneshotCache<byte[]> d;
    private String e;
    private String f;
    private String g;

    public int getWaitingTimeout() {
        return this.a;
    }

    public int getNoSpeechTimeOut() {
        return this.b;
    }

    public int getMaxSpeechTimeS() {
        return this.c;
    }

    public OneshotCache<byte[]> getOneshotCache() {
        return this.d;
    }

    public String getSaveAudioPath() {
        return this.e;
    }

    public String getDeviceId() {
        return this.f;
    }

    public String getProductId() {
        return this.g;
    }

    public void setWaitingTimeout(int i) {
        this.a = i;
    }

    public void setNoSpeechTimeOut(int i) {
        this.b = i;
    }

    public void setMaxSpeechTimeS(int i) {
        this.c = i;
    }

    public void setOneshotCache(OneshotCache<byte[]> oneshotCache) {
        this.d = oneshotCache;
    }

    public void setSaveAudioPath(String str) {
        this.e = str;
    }

    public void setDeviceId(String str) {
        this.f = str;
    }

    public void setProductId(String str) {
        this.g = str;
    }
}
