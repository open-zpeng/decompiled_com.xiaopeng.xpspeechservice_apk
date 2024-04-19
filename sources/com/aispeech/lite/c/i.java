package com.aispeech.lite.c;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class i extends com.aispeech.lite.a {
    private static String a = "frontBinPath";
    private static String b = "backBinPath";
    private static String c = "dictPath";
    private static String d = "optimization";
    private String e;
    private String f;
    private String g;
    private boolean h = true;

    @Override // com.aispeech.lite.a
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (i) super.clone();
    }

    @Override // com.aispeech.lite.a
    public final /* bridge */ /* synthetic */ com.aispeech.lite.a f() throws CloneNotSupportedException {
        return (i) super.clone();
    }

    public final void a(String str) {
        this.e = str;
    }

    public final String h() {
        return this.f;
    }

    public final void b(String str) {
        this.f = str;
    }

    public final void c(String str) {
        this.g = str;
    }

    public final boolean i() {
        return this.h;
    }

    public final void d(boolean z) {
        this.h = z;
    }

    @Override // com.aispeech.lite.a
    public final JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.e)) {
                jSONObject.put(a, this.e);
            }
            if (!TextUtils.isEmpty(this.f)) {
                jSONObject.put(b, this.f);
            }
            if (!TextUtils.isEmpty(this.g)) {
                jSONObject.put(c, this.g);
            }
            jSONObject.put(d, this.h ? 1 : 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
