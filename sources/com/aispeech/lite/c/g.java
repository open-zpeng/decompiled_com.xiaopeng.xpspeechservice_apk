package com.aispeech.lite.c;

import android.text.TextUtils;
import com.aispeech.common.Log;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class g extends com.aispeech.lite.a {
    private String a;

    @Override // com.aispeech.lite.a
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (g) super.clone();
    }

    @Override // com.aispeech.lite.a
    public final /* bridge */ /* synthetic */ com.aispeech.lite.a f() throws CloneNotSupportedException {
        return (g) super.clone();
    }

    public final void a(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("GrammarConfig", "Invalid ebnfFile");
        } else {
            this.a = str;
        }
    }

    @Override // com.aispeech.lite.a
    public final JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.a)) {
                jSONObject.put("resBinPath", this.a);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
