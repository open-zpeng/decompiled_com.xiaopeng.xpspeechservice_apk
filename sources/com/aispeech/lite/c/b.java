package com.aispeech.lite.c;

import android.text.TextUtils;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class b {
    private String a;
    private String b;
    private List<String> c;
    private boolean d = true;
    private boolean e = false;

    public final void a(String str) {
        this.a = str;
    }

    public final void b(String str) {
        this.b = str;
    }

    public final boolean a() {
        return this.e;
    }

    public final void a(boolean z) {
        this.e = z;
    }

    public final void a(List<String> list) {
        this.c = list;
    }

    public final JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("serverName", this.a);
            jSONObject.put("organization", this.b);
            boolean z = true;
            boolean z2 = (this.c == null || this.c.isEmpty()) ? false : true;
            if (z2) {
                JSONArray jSONArray = new JSONArray();
                for (String str : this.c) {
                    jSONArray.put(str);
                }
                jSONObject.put("users", jSONArray);
            }
            if (TextUtils.isEmpty(this.a) || !z2) {
                z = false;
            }
            jSONObject.put("enableAsrPlus", z);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("enableVAD", this.d);
            jSONObject.put("env", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
