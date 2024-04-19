package com.aispeech.common;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class VprintUtils {
    public static List<String> getRegisterList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        try {
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getJSONObject(i).optString("name"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static List<String> getWordList(JSONArray jSONArray, String str) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || TextUtils.isEmpty(str)) {
            return arrayList;
        }
        JSONArray jSONArray2 = null;
        try {
            if (jSONArray.length() > 0) {
                int i = 0;
                while (true) {
                    if (i >= jSONArray.length()) {
                        break;
                    }
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (!TextUtils.equals(str, jSONObject.optString("name"))) {
                        i++;
                    } else {
                        jSONArray2 = jSONObject.getJSONArray("wordlist");
                        break;
                    }
                }
                if (jSONArray2.length() > 0) {
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        arrayList.add(jSONArray2.getJSONObject(i2).optString("word"));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
