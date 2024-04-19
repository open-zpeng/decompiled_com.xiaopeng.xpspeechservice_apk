package com.aispeech.upload;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.upload.util.DeviceUtils;
import com.aispeech.upload.util.NetworkUtils;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class ModelBuilder {
    private static final String[] mJsonMustFillParam = new String[0];
    private static final String[] mMsgMustFillParam = new String[0];
    private JSONObject mJsonObject;
    private JSONObject mMsgObject;

    public static ModelBuilder create() {
        return new ModelBuilder();
    }

    private ModelBuilder() {
        try {
            this.mJsonObject = new JSONObject();
            this.mMsgObject = new JSONObject();
            this.mJsonObject.put("message", this.mMsgObject);
            addDefault();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addDefault() {
        addDeviceId(null);
        addUserId(null);
        addCallerType(null);
        addCallerVersion(null);
        addAppKey(null);
        addProductId(null);
        addProductVersion(null);
        addNetType(null);
        addMno(null);
        addClientIP(null);
    }

    private void putJson(String str, Object obj) {
        try {
            if (obj != null) {
                if (!TextUtils.isEmpty(obj.toString())) {
                    this.mJsonObject.put(str, obj);
                    return;
                }
                return;
            }
            this.mJsonObject.put(str, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putMsgJson(String str, Object obj) {
        if (obj != null) {
            try {
                if (!TextUtils.isEmpty(obj.toString())) {
                    this.mMsgObject.put(str, obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ModelBuilder build() {
        String[] strArr;
        String[] strArr2;
        try {
            for (String str : mJsonMustFillParam) {
                if (TextUtils.isEmpty(this.mJsonObject.get(str).toString())) {
                    throw new RuntimeException("No value for " + str);
                }
            }
            for (String str2 : mMsgMustFillParam) {
                if (TextUtils.isEmpty(this.mMsgObject.get(str2).toString())) {
                    throw new RuntimeException("No value for " + str2);
                }
            }
            return this;
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void addDefaultParam(ConstantValue constantValue) {
        addTime(System.currentTimeMillis() / 1000);
        addLogId(constantValue.getLogId());
        addPlatform(constantValue.getPlatform());
        addPlatType(constantValue.getPlatform());
        addProject(constantValue.getProject());
        addProductId(constantValue.getProductId());
        addDeviceId(constantValue.getDeviceId());
        addProductVersion(constantValue.getProductVersion());
        addDuicoreVersion(constantValue.getDuicoreVersion());
        addVersion(constantValue.getVersion());
        addUserId(constantValue.getUserId());
        addSDKVersion(constantValue.getSdkVersion());
        addProtVersion(constantValue.getProtVersion());
        addCallerType(constantValue.getCallerType());
        addCallerVersion(constantValue.getCallerVersion());
        addNetType();
        StringBuilder sb = new StringBuilder();
        sb.append(DeviceUtils.getSDKVersion());
        addPlatVersion(sb.toString());
        Map<String, Object> msgMap = constantValue.getMsgMap();
        msgMap.entrySet();
        for (Map.Entry<String, Object> entry : msgMap.entrySet()) {
            putJson(entry.getKey(), entry.getValue());
        }
    }

    public String buildJson() {
        return this.mJsonObject.toString();
    }

    private ModelBuilder addLogId(int i) {
        putJson("logId", Integer.valueOf(i));
        return this;
    }

    private ModelBuilder addProtVersion(String str) {
        putJson("protVersion", str);
        return this;
    }

    private ModelBuilder addPlatType(String str) {
        putJson("platType", str);
        return this;
    }

    private ModelBuilder addPlatVersion(String str) {
        putJson("platVersion", str);
        return this;
    }

    private ModelBuilder addCallerType(String str) {
        putJson("callerType", str);
        return this;
    }

    private ModelBuilder addCallerVersion(String str) {
        putJson("callerVersion", str);
        return this;
    }

    public ModelBuilder addAppKey(String str) {
        putJson("appKey", str);
        return this;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void addNetType() {
        char c;
        String str;
        String networkType = NetworkUtils.getNetworkType();
        switch (networkType.hashCode()) {
            case -1967517018:
                if (networkType.equals(NetworkUtils.NetworkType.NETWORK_WIFI)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 6890022:
                if (networkType.equals(NetworkUtils.NetworkType.NETWORK_2G)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 6890053:
                if (networkType.equals(NetworkUtils.NetworkType.NETWORK_3G)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 6890084:
                if (networkType.equals(NetworkUtils.NetworkType.NETWORK_4G)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            str = "2g";
        } else if (c == 1) {
            str = "3g";
        } else if (c == 2) {
            str = "4g";
        } else {
            str = c != 3 ? "" : "wifi";
        }
        if (!TextUtils.isEmpty(str)) {
            addNetType(str);
            if (!str.equals("wifi")) {
                addMno(NetworkUtils.getNetworkOperatorName());
            }
        }
        addClientIP(NetworkUtils.getIPAddress(true));
    }

    private ModelBuilder addNetType(String str) {
        putJson("netType", str);
        return this;
    }

    private ModelBuilder addMno(String str) {
        putJson("mno", str);
        return this;
    }

    private ModelBuilder addSDKVersion(String str) {
        putJson("SDKVersion", str);
        return this;
    }

    private ModelBuilder addClientIP(String str) {
        putJson("clientIP", str);
        return this;
    }

    private ModelBuilder addProject(String str) {
        putMsgJson("project", str);
        return this;
    }

    public ModelBuilder addTag(String str) {
        putMsgJson("tag", str);
        return this;
    }

    private ModelBuilder addTime(double d) {
        putJson(AIError.KEY_TIME, Double.valueOf(d));
        putMsgJson(AIError.KEY_TIME, Double.valueOf(d));
        return this;
    }

    public ModelBuilder addLevel(String str) {
        putMsgJson("level", str);
        return this;
    }

    private ModelBuilder addPlatform(String str) {
        putMsgJson("platform", str);
        return this;
    }

    public ModelBuilder addContextId(String str) {
        putMsgJson("contextId", str);
        return this;
    }

    private ModelBuilder addVersion(String str) {
        putMsgJson("version", str);
        return this;
    }

    private ModelBuilder addProductId(String str) {
        putMsgJson("productId", str);
        putJson("productId", str);
        return this;
    }

    public ModelBuilder addSessionId(String str) {
        putMsgJson("sessionId", str);
        return this;
    }

    public ModelBuilder addModule(String str) {
        putMsgJson("module", str);
        return this;
    }

    private ModelBuilder addDeviceId(String str) {
        putMsgJson("deviceId", str);
        putJson("deviceId", str);
        return this;
    }

    private ModelBuilder addProductVersion(String str) {
        putMsgJson("productVersion", str);
        putJson("productVersion", str);
        return this;
    }

    private ModelBuilder addDuicoreVersion(String str) {
        putMsgJson("duicoreVersion", str);
        return this;
    }

    private ModelBuilder addUserId(String str) {
        putMsgJson("userId", str);
        putJson("userId", str);
        return this;
    }

    public ModelBuilder addRecordId(String str) {
        putMsgJson(AIError.KEY_RECORD_ID, str);
        return this;
    }

    public ModelBuilder addInput(JSONObject jSONObject) {
        putMsgJson("input", jSONObject);
        return this;
    }

    public ModelBuilder addOutput(JSONObject jSONObject) {
        putMsgJson("output", jSONObject);
        return this;
    }

    public ModelBuilder addJSONObject(String str, JSONObject jSONObject) {
        putMsgJson(str, jSONObject);
        return this;
    }

    public ModelBuilder addMsgObject(String str, Object obj) {
        putMsgJson(str, obj);
        return this;
    }

    public ModelBuilder addJSONArray(String str, JSONArray jSONArray) {
        putMsgJson(str, jSONArray);
        return this;
    }

    public ModelBuilder addEntry(String str, Object obj) {
        putJson(str, obj);
        return this;
    }
}
