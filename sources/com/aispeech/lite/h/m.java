package com.aispeech.lite.h;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.common.FileUtils;
import com.aispeech.common.JSONUtil;
import com.aispeech.common.URLUtils;
import com.aispeech.lite.AISampleRate;
import com.aispeech.lite.oneshot.OneshotCache;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class m extends b implements Cloneable {
    public static final String b = m.class.getCanonicalName();
    private String l;
    private OneshotCache<byte[]> n;
    private int c = 60;
    private int d = 5000;
    private boolean e = true;
    private String f = "";
    private String g = "";
    private String h = "";
    private String i = "wss://asr.dui.ai/runtime/v2/recognize";
    private String j = "asr";
    private String k = "";
    private int m = 5000;
    private a a = new a();

    public final int p() {
        return this.m;
    }

    public final void b(int i) {
        this.m = i;
    }

    public final void c(int i) {
        this.c = i;
    }

    public final int q() {
        return this.c;
    }

    public final int r() {
        return this.d;
    }

    public final void d(int i) {
        this.d = i;
    }

    public void c(String str) {
        this.a.a(str);
    }

    public AISampleRate b() {
        return this.a.a();
    }

    public final int s() {
        return this.a.b();
    }

    public final void t() {
        this.e = false;
    }

    public String h() {
        return this.f;
    }

    public void b_(String str) {
        this.f = str;
    }

    public final void s(String str) {
        this.g = str;
    }

    public String f() {
        return this.h;
    }

    public void g(String str) {
        this.h = str;
    }

    public String l() {
        return this.i;
    }

    public void n(String str) {
        this.i = str;
    }

    public final String u() {
        return this.j;
    }

    public final void t(String str) {
        this.j = str;
    }

    public void d(String str) {
    }

    public String c() {
        return this.k;
    }

    public void e(String str) {
        this.k = str;
    }

    public void f(String str) {
    }

    public final String v() {
        if (!TextUtils.isEmpty(com.aispeech.lite.d.d)) {
            this.l = com.aispeech.lite.d.d;
            if (!this.l.endsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.l += URLUtils.URL_PATH_SEPERATOR;
            }
            if (this instanceof i) {
                this.l += "localAsr";
            }
            if (this instanceof o) {
                this.l += "vad";
            }
            if (this instanceof d) {
                this.l += "cloudAsr";
            }
            if (this instanceof e) {
                this.l += "cloudsSemantic";
            }
            if (this instanceof f) {
                this.l += "cloudTts";
            }
            if (this instanceof p) {
                this.l += "vprintCut";
            }
            if (this instanceof h) {
                this.l += "fespCar";
            }
            if (this instanceof q) {
                this.l += "wakeup";
            }
            FileUtils.createOrExistsDir(this.l);
        }
        return this.l;
    }

    public final void u(String str) {
        this.l = str;
    }

    public final OneshotCache<byte[]> w() {
        return this.n;
    }

    public final void a(OneshotCache<byte[]> oneshotCache) {
        this.n = oneshotCache;
    }

    @Override // com.aispeech.lite.h.b
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        if (this.e) {
            JSONUtil.putQuietly(jSONObject, "audio", this.a.c());
        }
        JSONObject jSONObject2 = new JSONObject();
        JSONUtil.putQuietly(jSONObject2, "productId", this.h);
        JSONUtil.putQuietly(jSONObject2, "userId", this.f);
        JSONUtil.putQuietly(jSONObject2, "deviceName", this.g);
        JSONUtil.putQuietly(jSONObject2, "sdkName", "");
        JSONUtil.putQuietly(jSONObject, "context", jSONObject2.toString());
        JSONObject jSONObject3 = new JSONObject();
        if (this.e) {
            JSONUtil.putQuietly(jSONObject3, "audio", this.a.c());
        }
        JSONUtil.putQuietly(jSONObject, "request", jSONObject2.toString());
        JSONUtil.putQuietly(jSONObject, AIError.KEY_RECORD_ID, this.k);
        return jSONObject;
    }

    @Override // com.aispeech.lite.h.b
    public String toString() {
        return a().toString();
    }

    @Override // com.aispeech.lite.h.b
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
