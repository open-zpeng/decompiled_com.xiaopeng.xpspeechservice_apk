package com.aispeech.export;

import org.json.JSONObject;
/* loaded from: classes.dex */
public class NativeApi {
    private String a;
    private JSONObject b;
    private String c;
    private String d;
    private String e;

    public String getApi() {
        return this.a;
    }

    public void setApi(String str) {
        this.a = str;
    }

    public JSONObject getParam() {
        return this.b;
    }

    public void setParam(JSONObject jSONObject) {
        this.b = jSONObject;
    }

    public String getIntentName() {
        return this.c;
    }

    public void setIntentName(String str) {
        this.c = str;
    }

    public String getSkillId() {
        return this.d;
    }

    public void setSkillId(String str) {
        this.d = str;
    }

    public String getTaskName() {
        return this.e;
    }

    public void setTaskName(String str) {
        this.e = str;
    }

    private NativeApi(String str, JSONObject jSONObject, String str2, String str3, String str4) {
        this.a = str;
        this.b = jSONObject;
        this.c = str2;
        this.d = str3;
        this.e = str4;
    }

    public static NativeApi transform(JSONObject jSONObject, String str, String str2, String str3) {
        return new NativeApi(jSONObject.optString("api"), jSONObject.optJSONObject("param"), str3, str, str2);
    }

    public String toString() {
        return "NativeApi{api='" + this.a + "', param=" + this.b + ", intentName='" + this.c + "', skillId='" + this.d + "', taskName='" + this.e + "'}";
    }
}
