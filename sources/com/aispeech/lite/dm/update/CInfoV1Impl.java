package com.aispeech.lite.dm.update;

import com.aispeech.AIError;
import com.aispeech.c.a;
import com.aispeech.c.b.d;
import com.aispeech.c.b.e;
import com.aispeech.common.AuthUtil;
import com.aispeech.common.Log;
import com.aispeech.common.URLUtils;
import com.aispeech.export.ProductContext;
import com.aispeech.export.SkillContext;
import com.aispeech.export.Vocab;
import com.aispeech.lite.c.c;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class CInfoV1Impl implements ICInfo {
    private static final String a = CInfoV1Impl.class.getSimpleName();
    private String b;
    private String c;
    private String d;
    private CInfoListener e;
    private String f;
    private String g;

    public CInfoV1Impl(String str, String str2, String str3, String str4, String str5, CInfoListener cInfoListener) {
        this.b = str == null ? c.b : str;
        this.c = str2;
        this.g = str3;
        this.d = str4;
        this.e = cInfoListener;
        this.f = str5;
    }

    /* loaded from: classes.dex */
    enum OPTION {
        POST("post"),
        DELETE("delete");
        
        String a;

        OPTION(String str) {
            this.a = str;
        }
    }

    /* loaded from: classes.dex */
    enum MODE {
        MERGE("merge"),
        OVERRIDE("override");
        
        String a;

        MODE(String str) {
            this.a = str;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.aispeech.lite.dm.update.ICInfo
    public void uploadVocabs(Vocab... vocabArr) {
        for (Vocab vocab : vocabArr) {
            try {
                final String name = vocab.getName();
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", "vocab");
                JSONArray jSONArray = new JSONArray();
                String action = vocab.getAction();
                char c = 65535;
                switch (action.hashCode()) {
                    case -1595115294:
                        if (action.equals(Vocab.ACTION_INSERT)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1446662746:
                        if (action.equals(Vocab.ACTION_CLEAR_ALL)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1345933651:
                        if (action.equals(Vocab.ACTION_REMOVE)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1373894268:
                        if (action.equals(Vocab.ACTION_CLEAR_AND_INSERT)) {
                            c = 1;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    jSONObject2.put("mode", MODE.OVERRIDE.a);
                } else if (c == 1) {
                    jSONObject2.put("mode", MODE.OVERRIDE.a);
                    jSONObject2.put("option", OPTION.POST.a);
                    for (String str : vocab.getContents()) {
                        jSONArray.put(str);
                    }
                } else if (c == 2) {
                    jSONObject2.put("mode", MODE.MERGE.a);
                    jSONObject2.put("option", OPTION.POST.a);
                    for (String str2 : vocab.getContents()) {
                        jSONArray.put(str2);
                    }
                } else if (c == 3) {
                    jSONObject2.put("mode", MODE.MERGE.a);
                    jSONObject2.put("option", OPTION.DELETE.a);
                    for (String str3 : vocab.getContents()) {
                        jSONArray.put(str3);
                    }
                }
                jSONObject2.put("data", jSONArray);
                jSONObject.put("payload", jSONObject2);
                Log.d(a, "action :" + vocab.getAction() + " , payload:" + jSONObject);
                String jSONObject3 = jSONObject.toString();
                d a2 = a.a();
                StringBuilder sb = new StringBuilder();
                sb.append(new Date().getTime());
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(UUID.randomUUID());
                String sb4 = sb3.toString();
                String signature = AuthUtil.getSignature(this.c + sb4 + this.d + sb2, this.g);
                StringBuilder sb5 = new StringBuilder();
                sb5.append(this.b);
                sb5.append("/vocabs");
                sb5.append(URLUtils.URL_PATH_SEPERATOR + name);
                sb5.append("?productId=");
                sb5.append(this.d);
                sb5.append("&aliasKey=");
                sb5.append(this.f);
                sb5.append("&deviceName=");
                sb5.append(this.c);
                sb5.append("&nonce=");
                sb5.append(sb4);
                sb5.append("&sig=");
                sb5.append(signature);
                sb5.append("&timestamp=");
                sb5.append(sb2);
                Log.i(a, "url :" + sb5.toString());
                a2.a(sb5.toString(), jSONObject3, new com.aispeech.c.b.a() { // from class: com.aispeech.lite.dm.update.CInfoV1Impl.1
                    @Override // com.aispeech.c.b.a
                    public void onFailure(d dVar, IOException iOException) {
                        String str4 = CInfoV1Impl.a;
                        Log.e(str4, "vocabs " + name + " upload failed , name : " + name);
                        if (CInfoV1Impl.this.e != null) {
                            CInfoV1Impl.this.e.onUploadFailed();
                        }
                    }

                    @Override // com.aispeech.c.b.a
                    public void onResponse(d dVar, e eVar) {
                        if (eVar.b()) {
                            String str4 = CInfoV1Impl.a;
                            Log.d(str4, "vocabs " + name + " upload success");
                            try {
                                if (CInfoV1Impl.this.e != null && new JSONObject(eVar.d()).optInt(AIError.KEY_CODE) == 0) {
                                    CInfoV1Impl.this.e.onUploadSuccess();
                                    return;
                                }
                                return;
                            } catch (JSONException e) {
                                e.printStackTrace();
                                if (CInfoV1Impl.this.e != null) {
                                    CInfoV1Impl.this.e.onUploadFailed();
                                    return;
                                }
                                return;
                            }
                        }
                        String str5 = CInfoV1Impl.a;
                        Log.e(str5, "vocabs " + name + " upload failed , http code : " + eVar.a());
                        if (CInfoV1Impl.this.e != null) {
                            CInfoV1Impl.this.e.onUploadFailed();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.aispeech.lite.dm.update.ICInfo
    public void uploadProductContext(ProductContext productContext) {
        Log.e(a, "cinfo v1 not implements uploadProductContext");
    }

    @Override // com.aispeech.lite.dm.update.ICInfo
    public void uploadSkillContext(SkillContext skillContext) {
        Log.e(a, "cinfo v1 not implements uploadSkillContext");
    }
}
