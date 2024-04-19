package com.aispeech.export;

import android.text.TextUtils;
import com.aispeech.AIError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class SkillIntent {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    public SkillIntent() {
    }

    public SkillIntent(String str, String str2, String str3, String str4) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public String getSkillId() {
        return this.a;
    }

    public void setSkillId(String str) {
        this.a = str;
    }

    public String getTaskName() {
        return this.b;
    }

    public void setTaskName(String str) {
        this.b = str;
    }

    public String getIntentName() {
        return this.c;
    }

    public void setIntentName(String str) {
        this.c = str;
    }

    public String getSlots() {
        return this.d;
    }

    public void setSlots(String str) {
        this.d = str;
    }

    public void setSlots(Slots slots) {
        this.d = slots.toJSON();
    }

    public String getRecordId() {
        return this.e;
    }

    public void setRecordId(String str) {
        this.e = str;
    }

    public String getSessionId() {
        return this.f;
    }

    public void setSessionId(String str) {
        this.f = str;
    }

    public String getSkill() {
        return this.g;
    }

    public void setSkill(String str) {
        this.g = str;
    }

    public String getInput() {
        return this.h;
    }

    public void setInput(String str) {
        this.h = str;
    }

    public String toString() {
        return toJson().toString();
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("skillId", this.a);
            jSONObject.put("task", this.b);
            jSONObject.put("intent", this.c);
            if (!TextUtils.isEmpty(this.d)) {
                if (this.d.startsWith("[")) {
                    jSONObject.put("slots", new JSONArray(this.d));
                } else {
                    jSONObject.put("slots", new JSONObject(this.d));
                }
            }
            if (!TextUtils.isEmpty(this.e)) {
                jSONObject.put(AIError.KEY_RECORD_ID, this.e);
            }
            if (!TextUtils.isEmpty(this.f)) {
                jSONObject.put("sessionId", this.f);
            }
            if (!TextUtils.isEmpty(this.g)) {
                jSONObject.put("skill", this.g);
            }
            if (!TextUtils.isEmpty(this.h)) {
                jSONObject.put("input", this.h);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
