package com.aispeech.lite.c;

import android.text.TextUtils;
import com.aispeech.lite.Languages;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class e extends com.aispeech.lite.a {
    private String a;
    private String b;
    private String c = Languages.CHINESE.getLanguage();

    @Override // com.aispeech.lite.a
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (e) super.clone();
    }

    @Override // com.aispeech.lite.a
    public final /* bridge */ /* synthetic */ com.aispeech.lite.a f() throws CloneNotSupportedException {
        return (e) super.clone();
    }

    public final String h() {
        return this.c;
    }

    public final void a(String str) {
        this.c = str;
    }

    public final void b(String str) {
        this.a = str;
    }

    public final void c(String str) {
        this.b = str;
    }

    @Override // com.aispeech.lite.a
    public final JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.a)) {
                jSONObject.put("resBinPath", this.a);
            }
            if (!TextUtils.isEmpty(this.b)) {
                jSONObject.put("netBinPath", this.b);
            }
            if (!TextUtils.isEmpty(this.c)) {
                jSONObject.put("scope", this.c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
