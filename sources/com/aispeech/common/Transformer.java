package com.aispeech.common;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Transformer {
    private static Set<String> a = new HashSet<String>() { // from class: com.aispeech.common.Transformer.1
        {
            add("task");
            add("action");
        }
    };
    private static Set<String> b = new HashSet<String>() { // from class: com.aispeech.common.Transformer.2
        {
            add("domain");
            add("skill");
        }
    };
    private static List<Map<String, String[]>> c = new ArrayList();

    public static void load(String str) {
        Log.d("Transformer", " load path " + str);
        try {
            JSONArray optJSONArray = new JSONObject(FileUtils.read2Str(str)).optJSONArray("地图");
            if (optJSONArray == null || optJSONArray.length() == 0) {
                throw new IllegalArgumentException("地图产品配置资源无效，请检查 navi_skill.conf 文件.");
            }
            c.clear();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    final String next = keys.next();
                    JSONArray jSONArray = optJSONObject.getJSONArray(next);
                    final String[] strArr = new String[jSONArray.length()];
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        strArr[i2] = jSONArray.optString(i2);
                    }
                    c.add(new HashMap<String, String[]>() { // from class: com.aispeech.common.Transformer.3
                        {
                            put(next, strArr);
                        }
                    });
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Transformer", " load skill conf failed,check skill conf path . ");
        }
    }

    public static JSONObject transGrammmer(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject jSONObject3 = new JSONObject();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (TextUtils.equals("rec", next)) {
                    jSONObject2.put("input", jSONObject.opt(next));
                } else if (TextUtils.equals("post", next)) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("post");
                    if (optJSONObject.has("sem")) {
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("sem");
                        Iterator<String> keys2 = optJSONObject2.keys();
                        JSONArray jSONArray = new JSONArray();
                        int i = 0;
                        while (keys2.hasNext()) {
                            String next2 = keys2.next();
                            String string = optJSONObject2.getString(next2);
                            if (b.contains(next2)) {
                                jSONObject2.put(next2, string);
                            } else if (a.contains(next2)) {
                                jSONObject3.put(next2, string);
                            } else {
                                JSONObject jSONObject4 = new JSONObject();
                                jSONObject4.put("name", next2);
                                jSONObject4.put("value", string.replaceAll("\\s*", ""));
                                jSONArray.put(jSONObject4);
                                i++;
                            }
                        }
                        jSONObject3.put("slots", jSONArray);
                        jSONObject3.put("slotcount", i);
                        jSONObject3.put("confidence", 1);
                    }
                } else {
                    jSONObject2.put(next, jSONObject.opt(next));
                }
            }
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("request", jSONObject3);
            jSONObject2.put("semantics", jSONObject5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject2;
    }

    public static JSONObject transNgram(JSONObject jSONObject, String str) {
        String a2;
        JSONObject jSONObject2 = new JSONObject();
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject2.put(next, jSONObject.opt(next));
            }
            if (jSONObject2.has("semantics")) {
                JSONObject optJSONObject = jSONObject2.optJSONObject("semantics");
                if (optJSONObject.has("request")) {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("request");
                    if (optJSONObject2.has("param")) {
                        JSONObject optJSONObject3 = optJSONObject2.optJSONObject("param");
                        Iterator<String> keys2 = optJSONObject3.keys();
                        JSONArray jSONArray = new JSONArray();
                        int i = 0;
                        while (keys2.hasNext()) {
                            String next2 = keys2.next();
                            String string = optJSONObject3.getString(next2);
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("name", next2);
                            jSONObject3.put("value", string);
                            jSONArray.put(jSONObject3);
                            i++;
                            if (TextUtils.equals(next2, "intent")) {
                                if (c.isEmpty()) {
                                    a2 = "";
                                } else {
                                    a2 = a(str, string);
                                    if (TextUtils.isEmpty(a2)) {
                                        a2 = a(string);
                                    }
                                }
                                if (!TextUtils.isEmpty(a2)) {
                                    optJSONObject2.put("task", a2);
                                }
                            }
                        }
                        optJSONObject2.put("slots", jSONArray);
                        optJSONObject2.put("slotcount", i);
                        optJSONObject2.put("confidence", 1);
                        optJSONObject2.remove("param");
                    }
                    if (optJSONObject2.has("domain")) {
                        jSONObject2.put("skill", optJSONObject2.optString("domain"));
                        optJSONObject2.remove("domain");
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject2;
    }

    private static String a(String str, String str2) {
        int i;
        String[] strArr;
        Iterator<Map<String, String[]>> it = c.iterator();
        while (true) {
            if (it.hasNext()) {
                Map<String, String[]> next = it.next();
                if (next.containsKey(str)) {
                    strArr = next.get(str);
                    break;
                }
            } else {
                strArr = new String[0];
                break;
            }
        }
        if (strArr != null && strArr.length != 0) {
            for (String str3 : strArr) {
                if (TextUtils.equals(str3, str2)) {
                    return str;
                }
            }
            return "";
        }
        return "";
    }

    private static String a(String str) {
        for (Map<String, String[]> map : c) {
            for (String str2 : map.keySet()) {
                for (String str3 : map.get(str2)) {
                    if (TextUtils.equals(str3, str)) {
                        return str2;
                    }
                }
            }
        }
        return "";
    }
}
