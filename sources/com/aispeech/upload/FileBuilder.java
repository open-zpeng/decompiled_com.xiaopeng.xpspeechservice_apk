package com.aispeech.upload;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class FileBuilder {
    private String encode;
    private String path;

    public String getPath() {
        return this.path;
    }

    public FileBuilder setPath(String str) {
        this.path = str;
        return this;
    }

    public String getEncode() {
        return this.encode;
    }

    public FileBuilder setEncode(String str) {
        this.encode = str;
        return this;
    }

    public String getJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("path", this.path);
            jSONObject.put("encode", this.encode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
