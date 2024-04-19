package com.aispeech.lite.h;

import android.text.TextUtils;
import com.aispeech.common.AIConstant;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class p extends m {
    private String d;
    private float a = Float.MAX_VALUE;
    private float c = 8.69f;
    private int e = 4;

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (p) super.clone();
    }

    @Override // com.aispeech.lite.h.m
    public final String h() {
        return this.d;
    }

    @Override // com.aispeech.lite.h.m
    public final void b_(String str) {
        this.d = str;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final JSONObject a() {
        StringBuilder sb;
        JSONObject jSONObject = new JSONObject();
        try {
            sb = new StringBuilder();
            sb.append("op=" + ((String) null) + ";");
            if (!TextUtils.isEmpty(v())) {
                sb.append("logpath=" + v() + ";");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!TextUtils.equals(null, AIConstant.VP_REGISTER) && !TextUtils.equals(null, AIConstant.VP_UPDATE) && !TextUtils.equals(null, "append")) {
            if (TextUtils.equals(null, AIConstant.VP_UNREGISTER)) {
                StringBuilder sb2 = new StringBuilder();
                sb.append("word=" + ((Object) sb2) + ";");
                sb.append("name=" + this.d + ";");
            } else if (TextUtils.equals(null, AIConstant.VP_TEST)) {
                if (this.a != Float.MAX_VALUE) {
                    sb.append("thresh=" + this.a + ";");
                }
                sb.append("sensitivity_level=0;");
                jSONObject.put("env", sb.toString());
                return jSONObject;
            }
            jSONObject.put("env", sb.toString());
            return jSONObject;
        }
        if (this.a != Float.MAX_VALUE) {
            sb.append("thresh=" + this.a + ";");
        }
        StringBuilder sb3 = new StringBuilder();
        sb.append("word=" + sb3.toString() + ";");
        sb.append("name=" + this.d + ";");
        sb.append("audionum=" + this.e + ";");
        sb.append("snr=" + this.c + ";");
        jSONObject.put("env", sb.toString());
        return jSONObject;
    }
}
