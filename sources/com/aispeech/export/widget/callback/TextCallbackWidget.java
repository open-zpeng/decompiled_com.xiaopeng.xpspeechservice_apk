package com.aispeech.export.widget.callback;

import org.json.JSONObject;
/* loaded from: classes.dex */
public class TextCallbackWidget extends CallbackWidget {
    private String a;

    public TextCallbackWidget(JSONObject jSONObject, int i, String str, String str2, String str3) {
        super(jSONObject, i, str, str2, str3);
        setText(jSONObject.optString("text"));
    }

    public String getText() {
        return this.a;
    }

    public void setText(String str) {
        this.a = str;
    }
}
