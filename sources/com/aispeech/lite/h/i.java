package com.aispeech.lite.h;

import android.text.TextUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class i extends m {
    public int a;
    private Map<String, Double> n;
    private boolean c = false;
    private double d = 1.5d;
    private boolean e = true;
    private boolean f = true;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private String j = "";
    private String k = "";
    private double l = 0.6d;
    private boolean m = true;
    private String o = "";
    private String p = "";

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (i) super.clone();
    }

    public final void a(boolean z) {
        this.c = z;
    }

    public final double d() {
        return this.d;
    }

    public final void a(double d) {
        this.d = d;
    }

    public final void b(String str) {
        this.p = str;
    }

    public final boolean e() {
        return this.m;
    }

    public final void b(boolean z) {
        this.m = z;
    }

    public final double g() {
        return this.l;
    }

    public final void b(double d) {
        this.l = d;
    }

    public final void h(String str) {
        this.j = str;
    }

    public final boolean i() {
        return this.i;
    }

    public final void c(boolean z) {
        this.i = z;
    }

    public final void d(boolean z) {
        this.h = z;
    }

    public final void j(String str) {
        this.o = str;
    }

    public final void e(boolean z) {
        this.e = z;
    }

    public final void f(boolean z) {
        this.f = z;
    }

    public final void g(boolean z) {
        this.g = z;
    }

    public final void k(String str) {
        this.k = str;
    }

    public final Map<String, Double> j() {
        return this.n;
    }

    public final void a(Map<String, Double> map) {
        this.n = map;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder("use_xbnf_rec=");
            int i = 1;
            sb2.append(this.e ? 1 : 0);
            sb2.append(";");
            sb.append(sb2.toString());
            StringBuilder sb3 = new StringBuilder("use_conf_dnn=");
            sb3.append(this.f ? 1 : 0);
            sb3.append(";");
            sb.append(sb3.toString());
            StringBuilder sb4 = new StringBuilder("use_pinyin=");
            sb4.append(this.g ? 1 : 0);
            sb4.append(";");
            sb.append(sb4.toString());
            StringBuilder sb5 = new StringBuilder("use_frame_split=");
            if (!this.h) {
                i = 0;
            }
            sb5.append(i);
            sb5.append(";");
            sb.append(sb5.toString());
            if (this.c) {
                sb.append("use_filler=1;");
                sb.append("filler_penalty_score=" + this.d + ";");
            }
            if (!TextUtils.isEmpty(this.j)) {
                sb.append("dynamic_list=" + this.j + ";");
            }
            if (!TextUtils.isEmpty(this.p)) {
                sb.append("rec_wrd_sep=\"" + this.p + "\";");
            }
            if (!TextUtils.isEmpty(this.k)) {
                sb.append("blacklist=" + this.k + ";");
            }
            if (!TextUtils.isEmpty(this.o)) {
                sb.append("expand_fn=" + this.o + ";");
            }
            jSONObject.put("env", sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
