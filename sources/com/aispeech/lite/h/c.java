package com.aispeech.lite.h;

import com.aispeech.common.JSONUtil;
import com.aispeech.export.Setting;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class c extends b {
    public static JSONObject a(JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        JSONUtil.putQuietly(jSONObject, "topic", "system.settings");
        JSONArray jSONArray2 = new JSONArray();
        if (jSONArray != null) {
            JSONObject jSONObject2 = new JSONObject();
            JSONUtil.putQuietly(jSONObject2, "key", Setting.SKILL_PRIORITY);
            JSONUtil.putQuietly(jSONObject2, "value", jSONArray);
            jSONArray2.put(jSONObject2);
        }
        JSONUtil.putQuietly(jSONObject, "settings", jSONArray2);
        return jSONObject;
    }

    public static JSONObject a(boolean z, JSONArray jSONArray) {
        if (jSONArray != null) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONUtil.putQuietly(jSONObject, "option", z ? "post" : "delete");
            JSONUtil.putQuietly(jSONObject, "data", jSONArray);
            JSONUtil.putQuietly(jSONObject, "type", "vocab");
            JSONUtil.putQuietly(jSONObject2, "payload", jSONObject);
            JSONUtil.putQuietly(jSONObject3, "data", jSONObject2.toString());
            JSONUtil.putQuietly(jSONObject3, "ctype", "vocabs");
            JSONUtil.putQuietly(jSONObject3, "vocabName", "sys.联系人");
            return jSONObject3;
        }
        return null;
    }

    @Override // com.aispeech.lite.h.b
    public final String toString() {
        return super.toString();
    }
}
