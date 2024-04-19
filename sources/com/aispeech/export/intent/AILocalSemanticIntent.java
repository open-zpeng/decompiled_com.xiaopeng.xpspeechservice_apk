package com.aispeech.export.intent;
/* loaded from: classes.dex */
public class AILocalSemanticIntent {
    private AILocalASRIntent a;
    private String b;
    private String c;
    private String d = "";
    private String e = "";
    private String f = "";

    public AILocalASRIntent getAsrIntent() {
        return this.a;
    }

    public void setAsrIntent(AILocalASRIntent aILocalASRIntent) {
        this.a = aILocalASRIntent;
    }

    public String getRefText() {
        return this.b;
    }

    public void setRefText(String str) {
        this.b = str;
    }

    public String getDomain() {
        return this.d;
    }

    public void setDomain(String str) {
        this.d = str;
    }

    public String getTask() {
        return this.e;
    }

    public void setTask(String str) {
        this.e = str;
    }

    public void setSkillID(String str) {
        this.f = str;
    }

    public String getSkillID() {
        return this.f;
    }

    public String getPinyin() {
        return this.c;
    }

    public void setPinyin(String str) {
        this.c = str;
    }
}
