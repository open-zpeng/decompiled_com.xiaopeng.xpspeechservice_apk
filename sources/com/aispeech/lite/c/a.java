package com.aispeech.lite.c;

import android.text.TextUtils;
import com.aispeech.lite.AISampleRate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class a extends com.aispeech.lite.a {
    private String b;
    private String d;
    private b f;
    private String g;
    private JSONArray l;
    private String a = "wss://asr.dui.ai/runtime/v2/recognize";
    private String e = "DUI-lite-android-sdk-CAR_V1.4.5";
    private String h = "ogg";
    private int i = 16000;
    private int j = 1;
    private int k = 2;
    private boolean m = false;
    private boolean n = false;
    private boolean o = true;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = true;
    private int w = 0;
    private String x = "zh-cn";
    private String y = "comm";
    private String z = "";
    private int A = 0;
    private boolean B = false;
    private boolean C = false;
    private String c = "1001";

    public final String h() {
        return this.a;
    }

    public final void a(String str) {
        this.a = str;
    }

    public a(com.aispeech.auth.d dVar) {
        this.b = dVar.a();
        this.d = dVar.f();
    }

    private Object B() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("productId", this.b);
            jSONObject.put("userId", this.c);
            jSONObject.put("deviceName", this.d);
            jSONObject.put("sdkName", this.e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private Object C() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("audioType", this.h);
            jSONObject.put(AISampleRate.KEY_SAMPLE_RATE, this.i);
            jSONObject.put("channel", this.j);
            jSONObject.put("sampleBytes", this.k);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final boolean i() {
        return this.m;
    }

    public final void d(boolean z) {
        this.m = z;
    }

    private Object D() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.l != null) {
                jSONObject.put("wakeupWord", this.m);
                jSONObject.put("customWakeupWord", this.l);
            }
            jSONObject.put("enableAudioDetection", this.C);
            jSONObject.put("enableEmotion", this.q);
            jSONObject.put("enableAlignment", this.B);
            jSONObject.put("enableRealTimeFeedback", this.n);
            jSONObject.put("enableVAD", this.o);
            jSONObject.put("enablePunctuation", this.p);
            jSONObject.put("enableEmotion", this.q);
            jSONObject.put("enableNumberConvert", this.r);
            jSONObject.put("enableRecUppercase", !this.r);
            jSONObject.put("enableTone", this.s);
            jSONObject.put("enableLanguageClassifier", this.t);
            jSONObject.put("enableSNTime", this.u);
            jSONObject.put("enableConfidence", this.v);
            jSONObject.put("selfCustomWakeupScore", this.w);
            jSONObject.put("language", this.x);
            jSONObject.put("res", this.y);
            if (!TextUtils.isEmpty(this.z)) {
                jSONObject.put("lmId", this.z);
            }
            if (this.A > 0) {
                jSONObject.put("nbest", this.A);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject E() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("requestId", this.g);
            jSONObject.put("audio", C());
            jSONObject.put("asr", D());
            if (this.f != null && this.f.a()) {
                jSONObject.put("asrPlus", this.f.b());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jSONObject.toString();
        return jSONObject;
    }

    public final String j() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("context", B());
            jSONObject.put("request", E());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public final String k() {
        return this.b;
    }

    public final void b(String str) {
        this.b = str;
    }

    public final void c(String str) {
        this.c = str;
    }

    public final String l() {
        return this.d;
    }

    public final void d(String str) {
        this.d = str;
    }

    public final void e(String str) {
        this.g = str;
    }

    public final JSONArray m() {
        return this.l;
    }

    public final void a(JSONArray jSONArray) {
        this.l = jSONArray;
    }

    public final boolean n() {
        return this.n;
    }

    public final void e(boolean z) {
        this.n = z;
    }

    public final boolean o() {
        return this.s;
    }

    public final void f(boolean z) {
        this.s = z;
    }

    public final boolean p() {
        return this.t;
    }

    public final void g(boolean z) {
        this.t = z;
    }

    public final boolean q() {
        return this.u;
    }

    public final void h(boolean z) {
        this.u = z;
    }

    public final int r() {
        return this.w;
    }

    public final void a(int i) {
        this.w = i;
    }

    public final void i(boolean z) {
        this.o = z;
    }

    public final boolean s() {
        return this.p;
    }

    public final void j(boolean z) {
        this.p = z;
    }

    public final boolean t() {
        return this.q;
    }

    public final boolean u() {
        return this.r;
    }

    public final void k(boolean z) {
        this.r = z;
    }

    public final String v() {
        return this.y;
    }

    public final void f(String str) {
        this.y = str;
    }

    public final void g(String str) {
        this.z = str;
    }

    public final String w() {
        return this.z;
    }

    public final int x() {
        return this.A;
    }

    public final void b(int i) {
        this.A = i;
    }

    public final a l(boolean z) {
        this.B = z;
        return this;
    }

    public final a m(boolean z) {
        this.q = z;
        return this;
    }

    public final a n(boolean z) {
        this.C = z;
        return this;
    }

    public final boolean y() {
        return this.B;
    }

    public final boolean z() {
        return this.C;
    }

    public final b A() {
        return this.f;
    }

    public final void a(b bVar) {
        this.f = bVar;
    }
}
