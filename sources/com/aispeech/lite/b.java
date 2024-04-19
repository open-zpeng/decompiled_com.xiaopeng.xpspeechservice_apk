package com.aispeech.lite;

import android.text.TextUtils;
import com.aispeech.AIError;
import java.util.Locale;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class b {
    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        try {
            if (TextUtils.isEmpty(lowerCase)) {
                return false;
            }
            JSONObject jSONObject = new JSONObject(lowerCase);
            if (!jSONObject.has("errid") && !jSONObject.has(AIError.KEY_TEXT)) {
                JSONObject optJSONObject = jSONObject.optJSONObject("result");
                if (optJSONObject != null) {
                    if (!optJSONObject.has("errid")) {
                        if (!optJSONObject.has("errorid")) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
