package com.aispeech.export.intent;

import com.aispeech.lite.AIType;
/* loaded from: classes.dex */
public class AICloudSemanticIntent {
    private boolean a = false;
    private boolean b = false;
    private String c = "";
    private String d = "";
    private int e = 5000;
    private int f = 60;
    private int g = 300;
    private boolean h = true;
    private String i = "";
    private String j = "";
    private AIType k = AIType.NLU;
    private String l = "";
    private String m = "";
    private boolean n = false;

    public void setEnablePunctuation(boolean z) {
        this.a = z;
    }

    public void setEnableNumberConvert(boolean z) {
        this.n = z;
    }

    public void setEnableNBest(boolean z) {
        this.b = z;
    }

    public void setWakeupWords(String str) {
        this.c = str;
    }

    public void setSessionId(String str) {
        this.d = str;
    }

    public void setNoSpeechTimeOut(int i) {
        this.e = i;
    }

    public void setMaxSpeechTimeS(int i) {
        this.f = i;
    }

    public void setPauseTime(int i) {
        this.g = i;
    }

    public void setUseRealBack(boolean z) {
        this.h = z;
    }

    public void setSaveAudioPath(String str) {
        this.i = str;
    }

    public void setRefText(String str) {
        this.j = str;
    }

    public void setType(AIType aIType) {
        this.k = aIType;
    }

    public void setSkillId(String str) {
        this.l = str;
    }

    public void setTask(String str) {
        this.m = str;
    }

    public boolean isEnablePunctuation() {
        return this.a;
    }

    public boolean isEnableNBest() {
        return this.b;
    }

    public String getWakeupWords() {
        return this.c;
    }

    public String getSessionId() {
        return this.d;
    }

    public int getNoSpeechTimeOut() {
        return this.e;
    }

    public int getMaxSpeechTimeS() {
        return this.f;
    }

    public int getPauseTime() {
        return this.g;
    }

    public boolean isUseRealBack() {
        return this.h;
    }

    public String getSaveAudioPath() {
        return this.i;
    }

    public String getRefText() {
        return this.j;
    }

    public AIType getType() {
        return this.k;
    }

    public String getSkillId() {
        return this.l;
    }

    public String getTask() {
        return this.m;
    }

    public boolean isEnableNumberConvert() {
        return this.n;
    }
}
