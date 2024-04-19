package com.aispeech.export;

import android.text.TextUtils;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Command {
    private String a;
    private JSONObject b;
    private String c;
    private String d;
    private String e;
    public String runSequence;

    /* loaded from: classes.dex */
    public static class RunSequence {
        public static final String COMMAND_FIRST = "commandFirst";
        public static final String NLG_FIRST = "nlgFirst";
    }

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

    public String getRunSequence() {
        return this.runSequence;
    }

    public void setRunSequence(String str) {
        this.runSequence = str;
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

    public String toString() {
        return "Command{api='" + this.a + "', param=" + this.b + ", runSequence='" + this.runSequence + "'}";
    }

    private Command(String str, JSONObject jSONObject, String str2, String str3, String str4, String str5) {
        this.a = str;
        this.b = jSONObject;
        this.runSequence = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
    }

    public static Command transform(JSONObject jSONObject, String str, String str2, String str3) {
        JSONObject optJSONObject = jSONObject.optJSONObject("command");
        if (optJSONObject != null) {
            String optString = optJSONObject.optString("api");
            if (!TextUtils.isEmpty(optString)) {
                return new Command(optString, optJSONObject.optJSONObject("param"), jSONObject.optString("runSequence"), str3, str, str2);
            }
            return null;
        }
        return null;
    }
}
