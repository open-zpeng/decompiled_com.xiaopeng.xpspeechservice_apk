package com.aispeech.lite.c;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class m extends com.aispeech.lite.a {
    private String a;
    private k b;

    public final String h() {
        return this.a;
    }

    public final void a(String str) {
        this.a = str;
    }

    @Override // com.aispeech.lite.a
    public final Context b() {
        return com.aispeech.lite.c.b();
    }

    public final k i() {
        return this.b;
    }

    public final void a(k kVar) {
        this.b = kVar;
    }

    public final String toString() {
        return g().toString();
    }

    @Override // com.aispeech.lite.a
    public final JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resBinPath", this.a);
            jSONObject.put("useOutputBoundary", 0);
            jSONObject.put("continusWakeupEnable", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
