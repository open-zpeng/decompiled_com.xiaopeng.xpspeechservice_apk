package com.aispeech.lite.h;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class d extends m {
    private boolean a = true;
    private boolean c = false;

    public d() {
        a("cn.asr.rec");
        t("asr");
    }

    public final void a(boolean z) {
        this.a = z;
    }

    public final void b(boolean z) {
        this.c = z;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final JSONObject a() {
        JSONObject a = super.a();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vadEnable", this.a);
            jSONObject.put("realBack", this.c);
            jSONObject.put("language", "CN");
            a.getJSONObject("request").put("asr", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a;
    }
}
