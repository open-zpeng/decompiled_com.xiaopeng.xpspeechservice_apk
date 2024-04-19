package com.aispeech.lite.dm.update;

import android.text.TextUtils;
import com.aispeech.common.AuthUtil;
import com.aispeech.common.Log;
import com.aispeech.export.ProductContext;
import com.aispeech.export.Setting;
import com.aispeech.export.SkillContext;
import com.aispeech.export.Vocab;
import com.aispeech.lite.c.c;
import com.aispeech.lite.g.d;
import java.util.Date;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class CInfoV2Impl implements ICInfo {
    private static final String a = CInfoV2Impl.class.getSimpleName();
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private CInfoListener g;
    private d h;
    private boolean i;

    public CInfoV2Impl(String str, String str2, String str3, String str4, String str5, boolean z, CInfoListener cInfoListener) {
        this.b = str == null ? c.a : str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = cInfoListener;
        this.i = z;
        this.h = new d();
    }

    private void a(String str) {
        d dVar = this.h;
        if (dVar == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(new Date().getTime());
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(UUID.randomUUID());
        String sb4 = sb3.toString();
        String signature = AuthUtil.getSignature(this.c + sb4 + this.e + sb2, this.d);
        StringBuilder sb5 = new StringBuilder();
        sb5.append(this.b);
        sb5.append(this.f);
        sb5.append("?serviceType=");
        sb5.append("websocket");
        sb5.append("&productId=");
        sb5.append(this.e);
        sb5.append("&deviceName=");
        sb5.append(this.c);
        sb5.append("&nonce=");
        sb5.append(sb4);
        sb5.append("&sig=");
        sb5.append(signature);
        sb5.append("&timestamp=");
        sb5.append(sb2);
        if (this.i) {
            sb5.append("&communicationType=fullDuplex");
        }
        String str2 = a;
        Log.d(str2, " url: " + sb5.toString());
        dVar.a(sb5.toString(), new com.aispeech.lite.g.c() { // from class: com.aispeech.lite.dm.update.CInfoV2Impl.1
            @Override // com.aispeech.lite.g.c
            public void onMessage(String str3) {
                String str4 = CInfoV2Impl.a;
                Log.d(str4, "upload success, text : " + str3);
                if (CInfoV2Impl.this.g != null) {
                    CInfoV2Impl.this.g.onUploadSuccess();
                }
                CInfoV2Impl.this.h.a();
            }

            @Override // com.aispeech.lite.g.c
            public void onError(String str3) {
                String str4 = CInfoV2Impl.a;
                Log.e(str4, "upload failed ,text : " + str3);
                if (CInfoV2Impl.this.g != null) {
                    CInfoV2Impl.this.g.onUploadFailed();
                }
                CInfoV2Impl.this.h.a();
            }

            @Override // com.aispeech.lite.g.c
            public void onOpen() {
            }

            @Override // com.aispeech.lite.g.c
            public void onClose() {
            }
        });
        this.h.a(str);
    }

    @Override // com.aispeech.lite.dm.update.ICInfo
    public void uploadVocabs(Vocab... vocabArr) {
        Log.e(a, "cinfo v2 not implements uploadVocabs");
    }

    @Override // com.aispeech.lite.dm.update.ICInfo
    public void uploadProductContext(ProductContext productContext) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("topic", "system.settings");
            if (TextUtils.equals("delete", productContext.getOption())) {
                jSONObject.put("option", "delete");
            }
            JSONArray jSONArray = new JSONArray();
            for (Setting setting : productContext.getSettings()) {
                jSONArray.put(setting.toJSON());
            }
            jSONObject.put("settings", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(jSONObject.toString());
    }

    @Override // com.aispeech.lite.dm.update.ICInfo
    public void uploadSkillContext(SkillContext skillContext) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("topic", "skill.settings");
            if (TextUtils.equals("delete", skillContext.getOption())) {
                jSONObject.put("option", "delete");
            }
            JSONArray jSONArray = new JSONArray();
            for (Setting setting : skillContext.getSettings()) {
                jSONArray.put(setting.toJSON());
            }
            jSONObject.put("settings", jSONArray);
            jSONObject.put("skillId", skillContext.getSkillId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(jSONObject.toString());
    }
}
