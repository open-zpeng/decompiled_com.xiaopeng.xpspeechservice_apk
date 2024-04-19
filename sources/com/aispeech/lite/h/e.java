package com.aispeech.lite.h;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.common.JSONUtil;
import com.aispeech.lite.AISampleRate;
import com.aispeech.lite.AIType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class e extends m {
    private String c;
    private String d;
    private String e;
    private String q;
    private String r;
    private int j = -1;
    private boolean k = true;
    private boolean l = false;
    private boolean m = true;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;
    private AIType s = AIType.DM;
    private String t = "";
    private String a = "recorder.stream.start";
    private AISampleRate g = AISampleRate.toAISampleRate(AISampleRate.SAMPLE_RATE_16K.getValue());
    private String f = "ogg";
    private int h = 1;
    private int i = 2;

    private JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        int i = this.j;
        if (i > 0) {
            JSONUtil.putQuietly(jSONObject, "vadPauseTime", Integer.valueOf(i));
        }
        JSONUtil.putQuietly(jSONObject, "realBack", Boolean.valueOf(this.k));
        JSONUtil.putQuietly(jSONObject, "enablePunctuation", Boolean.valueOf(this.l));
        JSONUtil.putQuietly(jSONObject, "enableTone", Boolean.valueOf(this.m));
        JSONUtil.putQuietly(jSONObject, "customWakeupScore", 0);
        JSONUtil.putQuietly(jSONObject, "enableConfidence", Boolean.valueOf(this.n));
        JSONUtil.putQuietly(jSONObject, "enableNumberConvert", Boolean.valueOf(this.o));
        JSONUtil.putQuietly(jSONObject, "enableRecUppercase", Boolean.valueOf(!this.o));
        JSONUtil.putQuietly(jSONObject, "enableAlignment", false);
        JSONUtil.putQuietly(jSONObject, "enableEmotion", false);
        JSONUtil.putQuietly(jSONObject, "enableAudioDetection", false);
        if (!TextUtils.isEmpty(null)) {
            try {
                JSONUtil.putQuietly(jSONObject, "phraseHints", new JSONArray((String) null));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        if (this.s != AIType.DM) {
            JSONUtil.putQuietly(jSONObject, "aiType", this.s.value);
        }
        if (!TextUtils.isEmpty(this.c)) {
            JSONUtil.putQuietly(jSONObject, AIError.KEY_RECORD_ID, this.c);
        }
        if (!TextUtils.isEmpty(this.d)) {
            JSONUtil.putQuietly(jSONObject, "sessionId", this.d);
        }
        if (!TextUtils.isEmpty(this.e)) {
            JSONUtil.putQuietly(jSONObject, "wakeupWord", this.e);
        }
        if (!TextUtils.isEmpty(this.t)) {
            JSONUtil.putQuietly(jSONObject, "refText", this.t);
        }
        JSONUtil.putQuietly(jSONObject, "topic", this.a);
        if (TextUtils.equals(this.a, "recorder.stream.start")) {
            JSONObject jSONObject2 = new JSONObject();
            JSONUtil.putQuietly(jSONObject2, "audioType", this.f);
            JSONUtil.putQuietly(jSONObject2, AISampleRate.KEY_SAMPLE_RATE, Integer.valueOf(this.g.getValue()));
            JSONUtil.putQuietly(jSONObject2, "channel", Integer.valueOf(this.h));
            JSONUtil.putQuietly(jSONObject2, "sampleBytes", Integer.valueOf(this.i));
            JSONUtil.putQuietly(jSONObject, "audio", jSONObject2);
            JSONUtil.putQuietly(jSONObject, "asrParams", e());
        }
        JSONObject jSONObject3 = new JSONObject();
        boolean z = this.p;
        if (z) {
            JSONUtil.putQuietly(jSONObject3, "enableNBest", Boolean.valueOf(z));
        }
        if (TextUtils.equals(u(), "nlu") && this.k) {
            JSONUtil.putQuietly(jSONObject3, "enableASRResult", true);
        }
        JSONUtil.putQuietly(jSONObject, "nluParams", jSONObject3);
        JSONObject jSONObject4 = new JSONObject();
        JSONObject jSONObject5 = new JSONObject();
        if (!TextUtils.isEmpty(this.q)) {
            JSONUtil.putQuietly(jSONObject5, "skillId", this.q);
        }
        if (!TextUtils.isEmpty(this.r)) {
            JSONUtil.putQuietly(jSONObject5, "task", this.r);
        }
        JSONUtil.putQuietly(jSONObject4, "skill", jSONObject5);
        JSONUtil.putQuietly(jSONObject, "context", jSONObject4);
        return jSONObject;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final String toString() {
        return "CloudSemanticParams{topic='" + this.a + "', recordId='" + this.c + "', sessionId='" + this.d + "', wakeupWord='" + this.e + "', audioType='" + this.f + "', sampleRate=" + this.g + ", channel=" + this.h + ", sampleBytes=" + this.i + ", realBack=" + this.k + ", enablePunctuation=" + this.l + ", enableTone=" + this.m + ", customWakeupScore=0, enableConfidence=" + this.n + ", enableNumberConvert=" + this.o + ", phraseHints='" + ((String) null) + "', enableNluCensor=false, enableNluNbese=" + this.p + ", skillId=" + this.q + ", task='" + this.r + "', aiType='" + this.s + "'}";
    }

    public final void b(String str) {
        this.t = str;
    }

    @Override // com.aispeech.lite.h.m
    public final void c(String str) {
        this.f = str;
    }

    @Override // com.aispeech.lite.h.m
    public final AISampleRate b() {
        return this.g;
    }

    @Override // com.aispeech.lite.h.m
    public final void d(String str) {
        this.a = str;
    }

    @Override // com.aispeech.lite.h.m
    public final String c() {
        return this.c;
    }

    @Override // com.aispeech.lite.h.m
    public final void e(String str) {
        this.c = str;
    }

    @Override // com.aispeech.lite.h.m
    public final void f(String str) {
        this.d = str;
    }

    public final void a_(String str) {
        this.e = str;
    }

    public final void a(boolean z) {
        this.k = z;
    }

    public final void b(boolean z) {
        this.l = z;
    }

    public final void c(boolean z) {
        this.o = z;
    }

    public final void d(boolean z) {
        this.p = z;
    }

    public final void h(String str) {
        this.q = str;
    }

    public final void i(String str) {
        this.r = str;
    }

    public final AIType d() {
        return this.s;
    }

    public final void a(AIType aIType) {
        this.s = aIType;
    }
}
