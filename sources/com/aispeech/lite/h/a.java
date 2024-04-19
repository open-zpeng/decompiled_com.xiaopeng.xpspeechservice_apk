package com.aispeech.lite.h;

import com.aispeech.common.JSONUtil;
import com.aispeech.lite.AISampleRate;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class a {
    private JSONObject a = new JSONObject();
    private int b = 100;
    private AISampleRate c;

    public a() {
        JSONUtil.putQuietly(this.a, "channel", 1);
        a("ogg");
        JSONUtil.putQuietly(this.a, "sampleBytes", 2);
        AISampleRate aISampleRate = AISampleRate.SAMPLE_RATE_16K;
        this.c = aISampleRate;
        JSONUtil.putQuietly(this.a, AISampleRate.KEY_SAMPLE_RATE, Integer.valueOf(aISampleRate.getValue()));
    }

    public final void a(String str) {
        JSONUtil.putQuietly(this.a, "audioType", str);
    }

    public final AISampleRate a() {
        return this.c;
    }

    public final int b() {
        return this.b;
    }

    public final JSONObject c() {
        return this.a;
    }

    public final String toString() {
        return this.a.toString();
    }
}
