package com.aispeech.lite.h;

import com.aispeech.common.AIConstant;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import com.aispeech.lite.AISampleRate;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class f extends n {
    private String j;
    private String m;
    private String n;
    private String o;
    private String p;
    private String r;
    private String c = "zhilingf";
    private String d = BuildInfoUtils.BID_WAN;
    private String e = "50";
    private String f = BuildInfoUtils.BID_WAN;
    private String g = "";
    private String h = "text";
    private boolean i = true;
    private String k = "";
    private String l = "https://tts.dui.ai/runtime/v2/synthesize";
    private String q = com.aispeech.lite.c.b().getExternalCacheDir() + File.separator + "ttsCache";
    private String s = AIConstant.TTS_AUDIO_TYPE_MP3;
    private String t = AIConstant.TTS_MP3_QUALITY_LOW;
    private int u = 16000;
    private int v = 1;
    private int w = 2;

    private JSONObject D() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("productId", this.m);
            jSONObject.put("userId", this.j);
            jSONObject.put("deviceName", this.o);
            jSONObject.put("sdkName", this.p);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final void b(String str) {
        this.h = str;
    }

    public final void a(boolean z) {
        this.i = z;
    }

    @Override // com.aispeech.lite.h.n
    public final String d() {
        return "cloud";
    }

    @Override // com.aispeech.lite.h.n
    public final boolean e() {
        return this.i;
    }

    @Override // com.aispeech.lite.h.m
    public final String f() {
        return this.m;
    }

    @Override // com.aispeech.lite.h.m
    public final void g(String str) {
        this.m = str;
    }

    public final void h(String str) {
        this.n = str;
    }

    public final String g() {
        return this.n;
    }

    @Override // com.aispeech.lite.h.m
    public final String h() {
        return this.j;
    }

    @Override // com.aispeech.lite.h.m
    public final void b_(String str) {
        this.j = str;
    }

    public final String i() {
        return this.o;
    }

    public final void j(String str) {
        this.o = str;
    }

    public final void k(String str) {
        this.p = str;
    }

    public final void l(String str) {
        this.r = str;
    }

    public final String j() {
        return this.s;
    }

    @Override // com.aispeech.lite.h.m
    public final void c(String str) {
        this.s = str;
    }

    public final void m(String str) {
        this.t = str;
    }

    private JSONObject E() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("audioType", this.s);
            jSONObject.put(AISampleRate.KEY_SAMPLE_RATE, this.u);
            if (this.s.equals(AIConstant.TTS_AUDIO_TYPE_MP3)) {
                jSONObject.put("mp3Quality", this.t);
            }
            jSONObject.put("channel", this.v);
            jSONObject.put("sampleBytes", this.w);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject F() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("requestId", this.r);
            jSONObject.put("audio", E());
            jSONObject.put("tts", G());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject G() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("useSSML", this.f);
            jSONObject.put("text", this.g);
            jSONObject.put("voiceId", this.c);
            jSONObject.put("textType", this.h);
            jSONObject.put("enableRealTimeFeedback", true);
            jSONObject.put("speed", this.d);
            jSONObject.put("volume", this.e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final String k() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("context", D());
            jSONObject.put("request", F());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    @Override // com.aispeech.lite.h.m
    public final String l() {
        return this.l;
    }

    @Override // com.aispeech.lite.h.m
    public final void n(String str) {
        this.l = str;
    }

    public final String m() {
        return this.c;
    }

    public final void o(String str) {
        this.c = str;
    }

    public final void p(String str) {
        this.d = str;
    }

    public final void q(String str) {
        this.e = str;
    }

    @Override // com.aispeech.lite.h.n
    public final String n() {
        return this.g;
    }

    public final void r(String str) {
        this.g = str;
    }

    @Override // com.aispeech.lite.h.n
    public final String o() {
        return v() + File.separator + Util.SHA1(toString()) + URLUtils.URL_DOMAIN_SEPERATOR + this.s;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final String toString() {
        return "CloudTtsParams{speaker='" + this.c + "', speed='" + this.d + "', volume='" + this.e + "', useSSML='" + this.f + "', refText='" + this.g + "', textType='" + this.h + "', enableRealTimeFeedback=" + this.i + ", userId='" + this.j + "', audioPath='" + this.k + "', server='" + this.l + "', productId='" + this.m + "', deviceName='" + this.o + "', sdkName='" + this.p + "', cachePath='" + this.q + "', streamType=" + this.a + ", requestId='" + this.r + "', audioType='" + this.s + "', mp3Quality='" + this.t + "', sampleRate=" + this.u + ", channel=" + this.v + ", sampleBytes=" + this.w + ", url='" + ((String) null) + "'}";
    }
}
