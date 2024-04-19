package com.aispeech.export.intent;

import com.aispeech.common.Log;
import com.aispeech.lite.oneshot.OneshotCache;
/* loaded from: classes.dex */
public class AICloudDMIntent {
    private String l;
    private OneshotCache a = null;
    private boolean b = false;
    private String[] c = new String[0];
    private int d = 5000;
    private int e = 60;
    private int f = -1;
    private int g = 300;
    private boolean h = true;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private boolean m = false;
    private String n = "";

    public String getRefText() {
        return this.n;
    }

    public void setRefText(String str) {
        this.n = str;
    }

    public void setEnableNumberConvert(boolean z) {
        this.m = z;
    }

    public void setOneshotCache(OneshotCache oneshotCache) {
        if (oneshotCache != null && oneshotCache.isValid()) {
            this.a = oneshotCache;
        } else {
            Log.e("AICloudDMIntent", " drop invalid oneshot cache ");
        }
    }

    public void setEnablePunctuation(boolean z) {
        this.b = z;
    }

    public void setWakeupWords(String[] strArr) {
        this.c = strArr;
    }

    public void setNoSpeechTimeOut(int i) {
        this.d = i;
    }

    public void setMaxSpeechTimeS(int i) {
        this.e = i;
    }

    public void setPauseTime(int i) {
        this.g = i;
    }

    public void setUseRealback(boolean z) {
        this.h = z;
    }

    public void setEnableAlignment(boolean z) {
        this.i = z;
    }

    public void setEnableEmotion(boolean z) {
        this.j = z;
    }

    public void setEnableAudioDetection(boolean z) {
        this.k = z;
    }

    public void setSaveAudioPath(String str) {
        this.l = str;
    }

    public OneshotCache getOneshotCache() {
        return this.a;
    }

    public boolean isEnablePunctuation() {
        return this.b;
    }

    public String[] getWakeupWords() {
        return this.c;
    }

    public String getStrWakeupWords() {
        String[] wakeupWords = getWakeupWords();
        StringBuilder sb = new StringBuilder();
        for (String str : wakeupWords) {
            sb.append(str + ",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public int getNoSpeechTimeOut() {
        return this.d;
    }

    public int getMaxSpeechTimeS() {
        return this.e;
    }

    public int getPauseTime() {
        return this.g;
    }

    public boolean isUseRealback() {
        return this.h;
    }

    public boolean isEnableAlignment() {
        return this.i;
    }

    public boolean isEnableEmotion() {
        return this.j;
    }

    public boolean isEnableAudioDetection() {
        return this.k;
    }

    public String getSaveAudioPath() {
        return this.l;
    }

    public boolean isEnableNumberConvert() {
        return this.m;
    }

    public int getCloudVadPauseTime() {
        return this.f;
    }

    public void setCloudVadPauseTime(int i) {
        this.f = i;
    }
}
