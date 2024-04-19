package com.aispeech.lite.h;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class l extends n {
    private String e;
    private String g;
    private float c = 1.0f;
    private int d = 80;
    private int f = 3;
    private boolean h = false;

    @Override // com.aispeech.lite.h.n, com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (l) super.clone();
    }

    public final void a(float f) {
        this.c = f;
    }

    public final void a(int i) {
        this.d = i;
    }

    @Override // com.aispeech.lite.h.n
    public final String n() {
        return this.e;
    }

    public final void b(String str) {
        this.e = str;
    }

    @Override // com.aispeech.lite.h.n
    public final boolean e() {
        return true;
    }

    @Override // com.aispeech.lite.h.n
    public final String d() {
        return "native";
    }

    public final void h(String str) {
        this.g = str;
    }

    public final void a(boolean z) {
        this.h = z;
    }

    @Override // com.aispeech.lite.h.n
    public final JSONObject a_() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("speed", this.c);
            jSONObject.put("volume", this.d);
            jSONObject.put("lmargin", 0);
            jSONObject.put("rmargin", 0);
            if (this.h) {
                jSONObject.put("useSSML", 1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final String toString() {
        return "LocalTtsParams{speed=" + this.c + ", volume=" + this.d + ", lmargin=0, rmargin=0, refText='" + this.e + "', streamType=" + this.f + ", backBin='" + this.g + "', useSSML=" + this.h + '}';
    }
}
