package com.aispeech.export;

import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Slots {
    private JSONArray a = new JSONArray();

    private Slots() {
    }

    public static Slots createInstance() {
        return new Slots();
    }

    public Slots add(JSONObject jSONObject) {
        this.a.put(jSONObject);
        return this;
    }

    public boolean isEmpty() {
        JSONArray jSONArray = this.a;
        return jSONArray == null || jSONArray.length() == 0;
    }

    public String toJSON() {
        return this.a.toString();
    }
}
