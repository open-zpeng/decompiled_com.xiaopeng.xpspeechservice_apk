package com.aispeech.export;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Setting {
    public static final String DISPATCH_SKILL_LIST = "dispatchSkillList";
    public static final String FILTER_SKILL_LIST = "filterSkillList";
    public static final String FULL_DUPLEX_FILTER_SWITCH = "filterSwitch";
    public static final String FULL_DUPLEX_SESSION_TIMEOUT = "fullduplexSessionTimeout";
    public static final String LOCATION = "location";
    public static final String SKILL_PRIORITY = "skillPriority";
    private String a;
    private Object b;

    public Setting(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public Setting(String str, int i) {
        this.a = str;
        this.b = Integer.valueOf(i);
    }

    public Setting(String str, double d) {
        this.a = str;
        this.b = Double.valueOf(d);
    }

    public Setting(String str, boolean z) {
        this.a = str;
        this.b = Boolean.valueOf(z);
    }

    public Setting(String str, float f) {
        this.a = str;
        this.b = Float.valueOf(f);
    }

    public Setting(String str, long j) {
        this.a = str;
        this.b = Long.valueOf(j);
    }

    public Setting(String str, JSONObject jSONObject) {
        this.a = str;
        this.b = jSONObject;
    }

    public Setting(String str, JSONArray jSONArray) {
        this.a = str;
        this.b = jSONArray;
    }

    public String getKey() {
        return this.a;
    }

    public Object getValue() {
        return this.b;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", this.a);
            jSONObject.put("value", this.b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
