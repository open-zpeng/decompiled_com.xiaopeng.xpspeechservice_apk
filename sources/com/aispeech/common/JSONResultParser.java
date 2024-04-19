package com.aispeech.common;

import android.text.TextUtils;
import com.aispeech.AIError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class JSONResultParser {
    private JSONObject a;
    private String b;
    private String c;
    private String d;
    private String e;
    private JSONArray f;
    private String g;
    private String h;
    private int i;
    private int j;

    public JSONResultParser(String str) {
        this(str, true);
    }

    public JSONResultParser(String str, boolean z) {
        this.b = null;
        this.c = null;
        this.d = "";
        this.e = "";
        this.f = null;
        this.g = "";
        this.h = "";
        this.i = -1;
        this.j = 1;
        try {
            this.a = new JSONObject(str);
            this.d = this.a.optString("sessionId");
            this.e = this.a.optString(AIError.KEY_RECORD_ID);
            JSONObject jSONObject = this.a.getJSONObject("result");
            this.j = this.a.optInt("eof", 1);
            if (jSONObject.has("rec")) {
                if (z) {
                    this.c = jSONObject.optString("rec").trim().replace(" ", "");
                } else {
                    this.c = jSONObject.optString("rec").trim();
                }
            }
            if (jSONObject.has("var")) {
                if (z) {
                    this.b = jSONObject.optString("var").trim().replace(" ", "");
                } else {
                    this.b = jSONObject.optString("var").trim();
                }
            }
            if (jSONObject.has("pinyin")) {
                this.h = jSONObject.optString("pinyin");
            }
            if (jSONObject.has("speakerLabels")) {
                this.f = jSONObject.optJSONArray("speakerLabels");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean haveVprintInfo() {
        return this.f != null;
    }

    public JSONObject getJSON() {
        return this.a;
    }

    public String getVar() {
        return this.b;
    }

    public int getEof() {
        return this.j;
    }

    public String getSessionId() {
        return this.d;
    }

    public String getRecordId() {
        return this.e;
    }

    public int getErrId() {
        return this.i;
    }

    public String getError() {
        return this.g;
    }

    public String getText() {
        return this.c;
    }

    public String getPinyin() {
        return this.h;
    }

    public String toString() {
        JSONObject jSONObject = this.a;
        return jSONObject != null ? jSONObject.toString() : "";
    }

    public void setRecPinyinWhenLast(String str, String str2) {
        if (1 == this.j && !TextUtils.isEmpty(str) && TextUtils.isEmpty(this.c)) {
            try {
                JSONObject jSONObject = this.a.getJSONObject("result");
                jSONObject.put("rec", str);
                jSONObject.put("pinyin", str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
