package com.aispeech.export.widget.callback;

import org.json.JSONObject;
/* loaded from: classes.dex */
public class CustomCallbackWidget extends CallbackWidget {
    private JSONObject a;

    /* JADX INFO: Access modifiers changed from: protected */
    public CustomCallbackWidget(JSONObject jSONObject, int i, String str, String str2, String str3) {
        super(jSONObject, i, str, str2, str3);
        setWidget(jSONObject);
    }

    public JSONObject getWidget() {
        return this.a;
    }

    public void setWidget(JSONObject jSONObject) {
        this.a = jSONObject;
    }
}
