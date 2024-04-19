package com.aispeech.upload.database;

import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class UploadBean implements Serializable {
    boolean autoDeleFile;
    String id;
    String jsonStr;
    String type;

    public UploadBean(String str, String str2, String str3) {
        this.id = str;
        this.jsonStr = str2;
        this.type = str3;
    }

    public String getId() {
        return this.id;
    }

    public String getJsonStr() {
        return this.jsonStr;
    }

    public String getFilePath() {
        String str = this.jsonStr;
        if (!TextUtils.isEmpty(str) && this.jsonStr.startsWith("{")) {
            try {
                return new JSONObject(this.jsonStr).optString("path");
            } catch (JSONException e) {
                e.printStackTrace();
                return str;
            }
        }
        return str;
    }

    public String getType() {
        return this.type;
    }

    public boolean isAutoDeleFile() {
        return this.autoDeleFile;
    }

    public void setAutoDeleFile(boolean z) {
        this.autoDeleFile = z;
    }
}
