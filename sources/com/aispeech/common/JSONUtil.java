package com.aispeech.common;

import android.os.Bundle;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class JSONUtil {
    public static JSONObject build(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void putQuietly(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void removeQuietly(JSONObject jSONObject, String str) {
        try {
            jSONObject.remove(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void putQuietly(JSONObject jSONObject, Map<String, Object> map) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            putQuietly(jSONObject, entry.getKey(), entry.getValue());
        }
    }

    public static Object getQuietly(JSONObject jSONObject, String str) {
        try {
            return jSONObject.get(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object optQuietly(JSONObject jSONObject, String str) {
        return jSONObject.opt(str);
    }

    public static JSONObject bundleToJSON(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            putQuietly(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    public static JSONObject pairToJSON(String str, Object obj) {
        JSONObject jSONObject = new JSONObject();
        putQuietly(jSONObject, str, obj);
        return jSONObject;
    }

    public static JSONObject mapToJSON(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        putQuietly(jSONObject, map);
        return jSONObject;
    }

    public static String decode(String str) {
        return str.replaceAll("\\\\\"", "\"").replaceAll("\\\\", "").replaceAll("\"\\{", "{").replaceAll("\\}\"", "}");
    }

    public static JSONObject normalSemanticSlots(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        if (jSONObject != null && jSONObject.has("semantics") && (optJSONObject = jSONObject.optJSONObject("semantics")) != null && optJSONObject.has("request") && (optJSONObject2 = optJSONObject.optJSONObject("request")) != null && optJSONObject2.has("slots")) {
            try {
                if (optJSONObject2.get("slots") instanceof JSONObject) {
                    optJSONObject2.remove("slots");
                    optJSONObject2.put("slots", new JSONArray());
                    optJSONObject.put("request", optJSONObject2);
                    jSONObject.put("semantics", optJSONObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }
}
