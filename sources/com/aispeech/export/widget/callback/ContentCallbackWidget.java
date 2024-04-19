package com.aispeech.export.widget.callback;

import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class ContentCallbackWidget extends CallbackWidget {
    public static final String BUTTONS = "buttons";
    public static final String IMAGE_URL = "imageUrl";
    public static final String LABEL = "label";
    public static final String LINK_URL = "linkUrl";
    public static final String SUB_TITLE = "subTitle";
    public static final String TITLE = "title";
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private JSONArray f;

    public ContentCallbackWidget(JSONObject jSONObject, int i, String str, String str2, String str3) {
        super(jSONObject, i, str, str2, str3);
        setTitle(jSONObject.optString("title"));
        setSubTitle(jSONObject.optString("subTitle"));
        setImageUrl(jSONObject.optString("imageUrl"));
        setLabel(jSONObject.optString("label"));
        setLinkUrl(jSONObject.optString("linkUrl"));
        setButtons(jSONObject.optJSONArray(BUTTONS));
    }

    public String getTitle() {
        return this.a;
    }

    public String getSubTitle() {
        return this.b;
    }

    public String getImageUrl() {
        return this.c;
    }

    public String getLabel() {
        return this.d;
    }

    public String getLinkUrl() {
        return this.e;
    }

    public JSONArray getButtons() {
        return this.f;
    }

    public void setTitle(String str) {
        this.a = str;
    }

    public void setSubTitle(String str) {
        this.b = str;
    }

    public void setImageUrl(String str) {
        this.c = str;
    }

    public void setLabel(String str) {
        this.d = str;
    }

    public void setLinkUrl(String str) {
        this.e = str;
    }

    public void setButtons(JSONArray jSONArray) {
        this.f = jSONArray;
    }
}
