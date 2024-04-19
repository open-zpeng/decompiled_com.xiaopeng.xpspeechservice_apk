package com.aispeech.lite.h;

import android.text.TextUtils;
import com.aispeech.common.JSONUtil;
import com.aispeech.lite.SemanticType;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class k extends m {
    private JSONObject a;
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private int h = 2;

    public k() {
        SemanticType semanticType = SemanticType.MIX_NAVI_DUI;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (k) super.clone();
    }

    public final String d() {
        return this.e;
    }

    public final k b(String str) {
        this.e = str;
        return this;
    }

    public final void h(String str) {
        this.g = str;
    }

    public final void j(String str) {
        this.c = str;
    }

    private JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("dlg_domain", this.e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final void k(String str) {
        this.d = str;
    }

    public final void l(String str) {
        this.f = str;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final JSONObject a() {
        this.a = new JSONObject();
        if (!TextUtils.isEmpty(this.c)) {
            JSONUtil.putQuietly(this.a, "refText", this.c);
        }
        if (!TextUtils.isEmpty(this.g)) {
            JSONUtil.putQuietly(this.a, "pinyin", this.g);
        }
        if (!TextUtils.isEmpty(this.d)) {
            JSONObject jSONObject = this.a;
            JSONUtil.putQuietly(jSONObject, "env", "dlg_domain=" + this.d + ";");
        }
        JSONUtil.putQuietly(this.a, "coreType", "cn.semantic");
        return this.a;
    }

    public final JSONObject e() {
        this.a = new JSONObject();
        if (!TextUtils.isEmpty(this.c)) {
            JSONUtil.putQuietly(this.a, "refText", this.c);
        }
        if (!TextUtils.isEmpty(this.g)) {
            JSONUtil.putQuietly(this.a, "pinyin", this.g);
        }
        if (!TextUtils.isEmpty(this.f)) {
            JSONUtil.putQuietly(this.a, "skillid", this.f);
            this.h = 0;
        }
        if (!TextUtils.isEmpty(this.e)) {
            JSONUtil.putQuietly(this.a, "env", g());
        }
        JSONUtil.putQuietly(this.a, "case", Integer.valueOf(this.h));
        return this.a;
    }
}
