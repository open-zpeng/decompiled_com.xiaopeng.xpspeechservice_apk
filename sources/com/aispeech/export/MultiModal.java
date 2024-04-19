package com.aispeech.export;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class MultiModal {
    private String a;
    private String b;
    private String c;
    private String d;
    private Slots e;
    private String f;

    public String getSessionId() {
        return this.a;
    }

    public void setSessionId(String str) {
        this.a = str;
    }

    public String getSkillId() {
        return this.b;
    }

    public void setSkillId(String str) {
        this.b = str;
    }

    public String getTask() {
        return this.c;
    }

    public void setTask(String str) {
        this.c = str;
    }

    public String getIntent() {
        return this.d;
    }

    public void setIntent(String str) {
        this.d = str;
    }

    public Slots getSlots() {
        return this.e;
    }

    public void setSlots(Slots slots) {
        this.e = slots;
    }

    public void setPlayerState(String str) {
        this.f = str;
    }

    public String getPlayerState() {
        return this.f;
    }

    public String toString() {
        return toJson().toString();
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sessionId", this.a);
            jSONObject.put("skillId", this.b);
            JSONObject jSONObject2 = new JSONObject();
            if (this.e != null) {
                jSONObject2.put("task", this.c);
                jSONObject2.put("intent", this.d);
                jSONObject2.put("slots", new JSONArray(this.e.toJSON()));
            }
            if (!TextUtils.isEmpty(this.f)) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("playerState", this.f);
                jSONObject2.put("dispatchEvent", jSONObject3);
            }
            jSONObject.put("async", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
