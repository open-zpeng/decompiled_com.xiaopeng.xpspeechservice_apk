package com.aispeech.export.widget.callback;

import org.json.JSONObject;
/* loaded from: classes.dex */
public class WebCallbackWidget extends CallbackWidget {
    public static final String URL = "url";
    private String a;

    public WebCallbackWidget(JSONObject jSONObject, int i, String str, String str2, String str3) {
        super(jSONObject, i, str, str2, str3);
        setUrl(jSONObject.optString("url"));
    }

    public String getUrl() {
        return this.a;
    }

    public void setUrl(String str) {
        this.a = str;
    }
}
