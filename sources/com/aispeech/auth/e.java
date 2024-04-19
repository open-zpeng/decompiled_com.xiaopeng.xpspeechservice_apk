package com.aispeech.auth;

import android.content.Context;
import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.common.AITimer;
import com.aispeech.common.AuthError;
import com.aispeech.common.AuthUtil;
import com.aispeech.common.Log;
import com.aispeech.lite.AuthType;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class e {
    private String a;
    private b b;
    private com.aispeech.auth.a.a c;
    private Context d;
    private d e;
    private f f;
    private String g;
    private String h;
    private String i;
    private a j = null;
    private volatile boolean k = false;

    static /* synthetic */ boolean h(e eVar) {
        eVar.k = true;
        return true;
    }

    public final void a(Context context, com.aispeech.auth.a.a aVar, b bVar) {
        this.d = context;
        this.c = aVar;
        this.b = bVar;
        this.e = new d(this.d, this.c);
        this.f = this.e.a((String) null);
    }

    public final void a() {
        com.aispeech.auth.a.a aVar = this.c;
        if (aVar == null) {
            Log.e("AuthProxy", "AIAuthConfig is null, please check");
            throw new RuntimeException("DUI SDK init AIAuthConfig == null");
        } else if (this.d == null) {
            Log.e("AuthProxy", "context is null, please check");
            throw new RuntimeException("DUI SDK init Context == null");
        } else if (this.b == null) {
            Log.e("AuthProxy", "AuthListener is null, please check");
            throw new RuntimeException("DUI SDK init AIAuthListener == null");
        } else if (TextUtils.isEmpty(aVar.a())) {
            throw new IllegalArgumentException("AuthConfig is invalid, lost productId");
        } else {
            if (!TextUtils.isEmpty(this.c.d())) {
                String secretCode = AuthUtil.getSecretCode(this.d);
                if (TextUtils.isEmpty(this.c.b())) {
                    Log.d("AuthProxy", "old auth type");
                    this.g = "apikey";
                    this.h = this.c.d();
                    this.i = secretCode;
                } else {
                    Log.d("AuthProxy", "new auth type");
                    this.g = "productKey";
                    this.h = this.c.b();
                    this.i = this.c.c();
                }
                if (Auth.CheckApikey(this.c.d(), secretCode)) {
                    Log.d("AuthProxy", "apiKey is ok locally");
                    this.f = this.e.a((String) null);
                    f fVar = this.f;
                    if (fVar.b()) {
                        int i = AnonymousClass4.a[fVar.a().ordinal()];
                        if (i == 1) {
                            if (this.c.i()) {
                                this.e.j();
                                e();
                                return;
                            }
                            d();
                            return;
                        } else if (i == 2) {
                            d();
                            return;
                        } else if (this.c.i()) {
                            g();
                            return;
                        } else {
                            d();
                            return;
                        }
                    }
                    Log.d("AuthProxy", "delete useless profile ret = " + this.e.k());
                    e();
                    return;
                }
                a(AuthError.AUTH_ERR_MSG.ERR_API_KEY_INVALID);
                return;
            }
            throw new IllegalArgumentException("AuthConfig is invalid, lost apiKey");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.aispeech.auth.e$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[AuthType.values().length];

        static {
            try {
                a[AuthType.TRIAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[AuthType.OFFLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(f fVar) {
        if (fVar.b()) {
            d();
        } else {
            a(fVar.d());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AuthError.AUTH_ERR_MSG auth_err_msg) {
        if (this.b != null) {
            Log.e("AuthProxy", " auth error : " + auth_err_msg.toString());
            Log.e("AuthProxy", " device info : " + AuthUtil.getDeviceData(this.d, this.c));
            this.b.a(auth_err_msg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        b bVar = this.b;
        if (bVar != null) {
            bVar.a();
        }
    }

    private void e() {
        if (TextUtils.isEmpty(this.c.b())) {
            Log.d("AuthProxy", "go to old auth");
            f();
            return;
        }
        h();
        this.a = "VERIFY";
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("apikey", this.c.d());
        linkedHashMap.put("package", this.d.getPackageName());
        linkedHashMap.put("signatuerSha256", AuthUtil.getKeyHash(this.d));
        linkedHashMap.put("buildVariant", AuthUtil.getBuildVariant(this.d));
        com.aispeech.c.a.a().a(AuthUtil.appendUrl(this.c.f() + this.c.o(), linkedHashMap), new com.aispeech.c.b.a() { // from class: com.aispeech.auth.e.1
            @Override // com.aispeech.c.b.a
            public final void onFailure(com.aispeech.c.b.d dVar, IOException iOException) {
                if (e.this.f.a() == AuthType.TRIAL) {
                    e.this.e.i();
                    e.this.c.s();
                    e eVar = e.this;
                    eVar.a(eVar.e.a((String) null));
                    return;
                }
                e.a(e.this, iOException);
            }

            @Override // com.aispeech.c.b.a
            public final void onResponse(com.aispeech.c.b.d dVar, com.aispeech.c.b.e eVar) throws IOException {
                e.d(e.this);
                if (e.this.f.a() != AuthType.TRIAL) {
                    e.a(e.this, eVar, "VERIFY");
                    return;
                }
                String d = eVar.d();
                if (eVar.a() != 200 || TextUtils.isEmpty(d)) {
                    e.this.e.i();
                    e.this.c.s();
                    e eVar2 = e.this;
                    eVar2.a(eVar2.e.a((String) null));
                    return;
                }
                Log.d("AuthProxy", "verify response->" + d);
                Log.d("AuthProxy", "first register url: " + e.this.i());
                e.this.f();
            }
        });
    }

    public final boolean b() {
        d dVar = this.e;
        return dVar != null && dVar.a((String) null).b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        h();
        this.a = "REGISTER";
        Map<String, Object> q = this.c.q();
        if (!TextUtils.isEmpty(this.c.g())) {
            q.put("deviceId", this.c.g());
        }
        if (!TextUtils.isEmpty(this.c.m())) {
            q.put("licenceId", this.c.m());
        }
        if (!TextUtils.isEmpty(this.c.p())) {
            q.put("deviceName", this.c.p());
        }
        String deviceData = AuthUtil.getDeviceData(this.d, this.c);
        Log.d("AuthProxy", "register body: " + deviceData);
        com.aispeech.c.a.a().a(i(), deviceData, new com.aispeech.c.b.a() { // from class: com.aispeech.auth.e.2
            @Override // com.aispeech.c.b.a
            public final void onFailure(com.aispeech.c.b.d dVar, IOException iOException) {
                e.d(e.this);
                if (e.this.f.a() == AuthType.TRIAL) {
                    e.this.e.i();
                    e.this.c.s();
                    e eVar = e.this;
                    eVar.a(eVar.e.a((String) null));
                    return;
                }
                e.a(e.this, iOException);
            }

            @Override // com.aispeech.c.b.a
            public final void onResponse(com.aispeech.c.b.d dVar, com.aispeech.c.b.e eVar) throws IOException {
                e.d(e.this);
                Log.d("AuthProxy", "register response code " + eVar.a());
                if (eVar.a() != 200 && e.this.f.a() == AuthType.TRIAL) {
                    e.this.e.i();
                    e.this.c.s();
                    e eVar2 = e.this;
                    eVar2.a(eVar2.e.a((String) null));
                    return;
                }
                e.a(e.this, eVar, "REGISTER");
            }
        });
    }

    private void g() {
        h();
        this.a = "LOGIN";
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("productId", this.c.a());
        linkedHashMap.put("deviceName", this.e.f());
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(UUID.randomUUID());
        String sb4 = sb3.toString();
        linkedHashMap.put(AIError.KEY_TIME, sb2);
        linkedHashMap.put("nonce", sb4);
        linkedHashMap.put("sig", AuthUtil.getSignature(this.e.f() + sb4 + this.c.a() + sb2, this.e.g()));
        com.aispeech.c.a.a().a(AuthUtil.appendUrl(this.c.f() + this.c.l(), linkedHashMap), "", new com.aispeech.c.b.a() { // from class: com.aispeech.auth.e.3
            @Override // com.aispeech.c.b.a
            public final void onFailure(com.aispeech.c.b.d dVar, IOException iOException) {
                e.d(e.this);
                Log.e("AuthProxy", "login onFailure: " + iOException.getMessage());
                e.this.d();
            }

            @Override // com.aispeech.c.b.a
            public final void onResponse(com.aispeech.c.b.d dVar, com.aispeech.c.b.e eVar) throws IOException {
                e.d(e.this);
                Log.d("AuthProxy", "login response code is " + eVar.a());
                if (e.this.f.a() != AuthType.TRIAL && eVar.a() == 401) {
                    String d = eVar.d();
                    Log.e("AuthProxy", "login failed, errMsg is " + d);
                    Log.w("AuthProxy", "current profile is invalid, need to update profile");
                    Log.d("AuthProxy", "delete useless profile, ret = " + e.this.e.k());
                    try {
                        String optString = new JSONObject(d).optString("detailErrId");
                        if (AuthError.AUTH_ERR_MSG.parseServerErrorId(optString) == AuthError.AUTH_ERR_MSG.ERR_SERVER_070659) {
                            e.this.a();
                            return;
                        } else if (AuthError.AUTH_ERR_MSG.parseServerErrorId(optString) == AuthError.AUTH_ERR_MSG.ERR_SERVER_070640) {
                            Log.e("AuthProxy", "校验码signature不正确，直接原因为秘钥错误");
                            e.this.a(AuthError.AUTH_ERR_MSG.parseServerErrorId(optString));
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                e.this.d();
                if (eVar.a() == 200) {
                    String d2 = eVar.d();
                    if ("{}".equals(d2)) {
                        Log.d("AuthProxy", "local profile is right.");
                    } else if (!TextUtils.isEmpty(d2)) {
                        Log.d("AuthProxy", "local profile have update->" + d2);
                        if (!e.this.e.b(d2)) {
                            e.this.a(AuthError.AUTH_ERR_MSG.ERR_PROFILE_SAVE);
                        }
                    }
                }
            }
        });
    }

    public final d c() {
        return this.e;
    }

    private void h() {
        Log.d("AuthProxy", "startMaxSpeechTimerTask");
        a aVar = this.j;
        if (aVar != null) {
            aVar.cancel();
            this.j = null;
        }
        if (this.c.j() > 0) {
            this.k = false;
            this.j = new a();
            try {
                Log.d("AuthProxy", "auth-connect-timeout: " + this.c.j() + "ms");
                AITimer.getInstance().schedule(this.j, this.c.j());
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends TimerTask {
        a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            Log.d("AuthProxy", "network connect timeout before");
            e.h(e.this);
            com.aispeech.c.a.a().b();
            Log.d("AuthProxy", "auth mode is " + e.this.a);
            if (e.this.f.a() != AuthType.TRIAL) {
                if (TextUtils.equals(e.this.a, "REGISTER") || TextUtils.equals(e.this.a, "VERIFY")) {
                    e.this.a(AuthError.AUTH_ERR_MSG.ERR_NET_TIMEOUT);
                    Log.d("AuthProxy", e.this.a + " timeout, invoke timeout err");
                } else if (TextUtils.equals(e.this.a, "LOGIN")) {
                    Log.d("AuthProxy", "login timeout, invoke success");
                    e.this.d();
                }
            }
            Log.d("AuthProxy", "network connect timeout after");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String i() {
        String str = this.c.f() + this.c.k();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(this.g, this.h);
        linkedHashMap.put("productId", this.c.a());
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(UUID.randomUUID());
        String sb4 = sb3.toString();
        linkedHashMap.put(AIError.KEY_TIME, sb2);
        linkedHashMap.put("nonce", sb4);
        linkedHashMap.put("sig", AuthUtil.getSignature(this.h + sb4 + this.c.a() + sb2, this.i));
        return AuthUtil.appendUrl(str, linkedHashMap);
    }

    static /* synthetic */ void a(e eVar, Exception exc) {
        String message = exc.getMessage();
        Log.d("AuthProxy", "verify onFailure: " + message);
        if (eVar.k && (message.contains("Canceled") || message.contains("closed"))) {
            return;
        }
        if (message.toLowerCase().contains("certi".toLowerCase()) || message.toLowerCase().contains("Chain validation".toLowerCase())) {
            eVar.a(AuthError.AUTH_ERR_MSG.ERR_CERTIFICATION_INVALID);
        } else {
            eVar.a(AuthError.AUTH_ERR_MSG.ERR_NET_CONNECT);
        }
    }

    static /* synthetic */ void d(e eVar) {
        Log.d("AuthProxy", "cancelTimeoutTimerTask");
        a aVar = eVar.j;
        if (aVar == null) {
            return;
        }
        aVar.cancel();
        eVar.j = null;
    }

    static /* synthetic */ void a(e eVar, com.aispeech.c.b.e eVar2, String str) {
        Log.d("AuthProxy", str + " response code is " + eVar2.a());
        int a2 = eVar2.a();
        String str2 = null;
        if (a2 != 200) {
            if (a2 != 401) {
                if (a2 == 500) {
                    eVar.a(AuthError.AUTH_ERR_MSG.ERR_SHA256_INVALID);
                    return;
                } else {
                    eVar.a(AuthError.AUTH_ERR_MSG.ERR_NET_CONNECT);
                    return;
                }
            }
            String d = eVar2.d();
            Log.d("AuthProxy", "err401: " + d);
            try {
                str2 = new JSONObject(d).getString("detailErrId");
            } catch (Exception e) {
                e.printStackTrace();
            }
            AuthError.AUTH_ERR_MSG parseServerErrorId = AuthError.AUTH_ERR_MSG.parseServerErrorId(str2);
            if (parseServerErrorId == AuthError.AUTH_ERR_MSG.ERR_SERVER_070635) {
                eVar.c.n();
                eVar.c.q().remove("licenceId");
                eVar.f();
                return;
            }
            eVar.a(parseServerErrorId);
            return;
        }
        String d2 = eVar2.d();
        if (!TextUtils.isEmpty(d2)) {
            Log.d("AuthProxy", "response->" + d2);
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1766622087) {
                if (hashCode != 72611657) {
                    if (hashCode == 92413603 && str.equals("REGISTER")) {
                        c = 1;
                    }
                } else if (str.equals("LOGIN")) {
                    c = 2;
                }
            } else if (str.equals("VERIFY")) {
                c = 0;
            }
            if (c == 0) {
                Log.d("AuthProxy", "first register url: " + eVar.i());
                eVar.f();
                return;
            } else if (c == 1) {
                if (eVar.e.b(d2)) {
                    f a3 = eVar.e.a((String) null);
                    if (a3.b()) {
                        eVar.g();
                        return;
                    } else {
                        eVar.a(a3);
                        return;
                    }
                }
                eVar.a(AuthError.AUTH_ERR_MSG.ERR_PROFILE_SAVE);
                return;
            } else {
                return;
            }
        }
        eVar.a(AuthError.AUTH_ERR_MSG.ERR_API_KEY_INVALID);
    }
}
