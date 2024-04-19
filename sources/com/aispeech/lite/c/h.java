package com.aispeech.lite.c;

import android.text.TextUtils;
import com.aispeech.common.Log;
import com.aispeech.lite.SemanticType;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class h extends com.aispeech.lite.a {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private boolean h = false;
    private boolean i = true;
    private boolean j = false;
    private double k = 0.63d;
    private SemanticType l = SemanticType.MIX_NAVI_DUI;

    @Override // com.aispeech.lite.a
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (h) super.clone();
    }

    @Override // com.aispeech.lite.a
    public final /* bridge */ /* synthetic */ com.aispeech.lite.a f() throws CloneNotSupportedException {
        return (h) super.clone();
    }

    public final boolean h() {
        return this.h;
    }

    public final void d(boolean z) {
        this.h = z;
    }

    public final boolean i() {
        return this.i;
    }

    public final void e(boolean z) {
        this.i = z;
    }

    public final boolean j() {
        return this.j;
    }

    public final void f(boolean z) {
        this.j = z;
    }

    public final void a(double d) {
        this.k = d;
    }

    public final double k() {
        return this.k;
    }

    public final void a(String str) {
        Log.d("SemanticConfig", "naviResPath :" + str);
        if (TextUtils.isEmpty(str)) {
            Log.e("SemanticConfig", "Invalid navi ebnfFile");
        } else {
            this.a = str;
        }
    }

    public final void b(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("SemanticConfig", "Invalid navi luaFile");
        } else {
            this.b = str;
        }
    }

    public final void c(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("SemanticConfig", "Invalid navi vocabFile");
        } else {
            this.c = str;
        }
    }

    public final String l() {
        return this.d + "/navi_skill.conf";
    }

    public final void d(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("SemanticConfig", "Invalid skill conf path");
        } else {
            this.d = str;
        }
    }

    public final String m() {
        return this.e;
    }

    public final void e(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("SemanticConfig", "Invalid DUI ebnfFile");
        } else {
            this.e = str;
        }
    }

    public final void f(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("SemanticConfig", "Invalid DUI luaFile");
        } else {
            this.f = str;
        }
    }

    public final void g(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("SemanticConfig", "Invalid DUI custom path");
        } else {
            this.g = str;
        }
    }

    public final void a(SemanticType semanticType) {
        this.l = semanticType;
    }

    public final SemanticType n() {
        return this.l;
    }

    @Override // com.aispeech.lite.a
    public final JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            Log.d("SemanticConfig", "naviResPath :" + this.a);
            if (!TextUtils.isEmpty(this.a)) {
                jSONObject.put("resPath", this.a + "/semantic");
            }
            if (!TextUtils.isEmpty(this.b)) {
                jSONObject.put("luaPath", this.b + "/semantic.lub," + this.b + "/res.lub," + this.b + "/core.lub");
            }
            if (!TextUtils.isEmpty(this.c)) {
                jSONObject.put("vocabPath", this.c + "/semantic/lex/vocabs");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final JSONObject o() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.e)) {
                jSONObject.put("resPath", this.e);
            }
            if (!TextUtils.isEmpty(this.g)) {
                jSONObject.put("resCustomPath", this.g);
            }
            if (!TextUtils.isEmpty(this.f)) {
                jSONObject.put("luaPath", this.f + "/semantic_dui.lub," + this.f + "/core_dui.lub");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final String toString() {
        return "LocalSemanticConfig{semanticType='" + this.l.getType() + "',resPath='" + this.a + "', luaPath='" + this.b + "', vocabPath='" + this.c + "', duiResPath='" + this.e + "', duiLuaPath='" + this.f + "'}";
    }
}
