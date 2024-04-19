package com.aispeech.common;

import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Debug {
    private long a;
    private long b;
    private long c;
    private long d;
    private long e;
    private long f;
    private long g;
    private JSONArray h;

    public void setEnable(boolean z) {
        if (z) {
            reset();
        }
    }

    public void reset() {
        this.d = -1L;
        this.c = -1L;
        this.b = -1L;
        this.a = -1L;
        this.e = -1L;
        this.g = -1L;
        this.f = -1L;
        this.h = new JSONArray();
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("start_listening", this.a);
            jSONObject.put("ready_for_speech", this.b);
            jSONObject.put("beginning_of_speech", this.c);
            jSONObject.put("performs", this.h);
            jSONObject.put("vad_idle", this.e);
            jSONObject.put("vad_start", this.f);
            jSONObject.put("vad_end", this.g);
            jSONObject.put("end_of_speech", this.d);
            jSONObject.put("payload_size", 0);
        } catch (Exception e) {
        }
        return jSONObject;
    }

    public String toString() {
        return toJSON().toString();
    }
}
