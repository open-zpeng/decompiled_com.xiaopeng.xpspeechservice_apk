package com.aispeech.export;

import android.text.TextUtils;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Speaker {
    public String nlg;
    public String speakUrl;

    public String getNlg() {
        return this.nlg;
    }

    public void setNlg(String str) {
        this.nlg = str;
    }

    public String getSpeakUrl() {
        return this.speakUrl;
    }

    public void setSpeakUrl(String str) {
        this.speakUrl = str;
    }

    public static Speaker transform(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("dm");
        if (optJSONObject.has("nlg")) {
            Speaker speaker = new Speaker();
            if (jSONObject.has("speakUrl")) {
                speaker.setSpeakUrl(jSONObject.optString("speakUrl"));
            }
            speaker.setNlg(optJSONObject.optString("nlg"));
            return speaker;
        }
        return null;
    }

    public String toString() {
        return "Speaker{nlg='" + this.nlg + "', speakUrl='" + this.speakUrl + "'}";
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.nlg) && TextUtils.isEmpty(this.speakUrl);
    }
}
