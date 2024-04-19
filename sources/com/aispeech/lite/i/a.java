package com.aispeech.lite.i;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.common.AuthUtil;
import com.aispeech.common.DDSJSONResultParser;
import com.aispeech.common.FileUtil;
import com.aispeech.common.JSONUtil;
import com.aispeech.common.Log;
import com.aispeech.common.PcmToOgg;
import com.aispeech.common.URLUtils;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.AIType;
import com.aispeech.lite.g;
import com.danikula.videocache.BuildConfig;
import com.xiaopeng.lib.utils.config.RemoteControlConfig;
import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class a extends g implements com.aispeech.lite.g.c {
    FileUtil e;
    private com.aispeech.lite.a.a f;
    private com.aispeech.lite.g.d g;
    private com.aispeech.lite.g.d h;
    private com.aispeech.lite.c.c i;
    private com.aispeech.lite.h.e j;
    private PcmToOgg k;
    private AtomicBoolean l;
    private AtomicBoolean m;
    private volatile boolean n;
    private long o;
    private long p;
    private long q;
    private long r;
    private String s;
    private String t;
    private String u;
    private com.aispeech.lite.dm.a v;
    private volatile boolean w;
    private DDSJSONResultParser x;
    private C0005a y;

    public a(com.aispeech.lite.a.a aVar) {
        super("CloudSemanticKernel");
        this.l = new AtomicBoolean(true);
        this.m = new AtomicBoolean(false);
        this.n = false;
        this.e = new FileUtil(com.aispeech.lite.c.b());
        this.v = new com.aispeech.lite.dm.a((byte) 0);
        this.w = false;
        this.y = new C0005a((byte) 0);
        this.f = aVar;
    }

    private String a(com.aispeech.lite.c.c cVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(new Date().getTime());
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(UUID.randomUUID());
        String sb4 = sb3.toString();
        String signature = AuthUtil.getSignature(cVar.h() + sb4 + cVar.k() + sb2, this.d.g());
        return cVar.l() + cVar.j() + "?serviceType=" + cVar.i() + "&productId=" + cVar.k() + "&deviceName=" + cVar.h() + "&nonce=" + sb4 + "&sig=" + signature + "&timestamp=" + sb2;
    }

    /* loaded from: classes.dex */
    class b extends Utils.speex_callback {
        b() {
        }

        @Override // com.aispeech.kernel.Utils.speex_callback
        public final int run(int i, byte[] bArr, int i2) {
            if (a.this.g != null && a.this.n) {
                if (a.this.l.compareAndSet(false, true) && i2 != 0) {
                    Log.d("CloudSemanticKernel", "ASR.FIRST.FEED");
                    a.this.o = System.currentTimeMillis();
                    Log.d("CloudSemanticKernel", "mFirstFeedTime is " + a.this.o);
                }
                if (i2 != 0) {
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, 0, bArr2, 0, i2);
                    a.this.g.a(bArr2);
                    a.this.e.write(bArr2);
                }
            } else {
                Log.e("CloudSemanticKernel", " ERROR ERROR ERROR ERROR ");
            }
            return 0;
        }
    }

    @Override // com.aispeech.lite.g, java.lang.Runnable
    public final void run() {
        boolean z;
        com.aispeech.lite.c.c cVar;
        do {
            com.aispeech.lite.f.a b2 = b();
            if (b2 != null) {
                int i = b2.a;
                z = true;
                if (i == 1) {
                    this.i = (com.aispeech.lite.c.c) b2.b;
                    this.g = new com.aispeech.lite.g.d();
                    this.h = new com.aispeech.lite.g.d();
                    this.k = new PcmToOgg();
                    this.k.initEncode(new b());
                    this.f.a(0);
                } else if (i == 2) {
                    this.j = (com.aispeech.lite.h.e) b2.b;
                    this.s = this.j.c();
                    String v = this.j.v();
                    if (!TextUtils.isEmpty(v)) {
                        Log.d("CloudSemanticKernel", "create local ogg file at: " + v + "/cloud_semantic_" + this.s + ".ogg");
                        this.e.createFile(v + "/cloud_semantic_" + this.s + ".ogg");
                    }
                    this.j.e(this.s);
                    if (!this.w) {
                        this.t = a(this.i);
                        Log.d("CloudSemanticKernel", "connect server url: " + this.t);
                        this.g.a(this.t, this);
                    }
                    this.v.a("__webSocket", new TimerTask() { // from class: com.aispeech.lite.i.a.2
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public final void run() {
                            a.this.d();
                        }
                    });
                    this.w = true;
                    this.u = this.j.a().toString();
                    this.g.a(this.u);
                    this.n = true;
                    this.k.startEncode(8, 16000, 0, 2);
                    Log.d("CloudSemanticKernel", "ASR.BEGIN");
                    this.l.compareAndSet(true, false);
                    this.m.set(false);
                } else if (i != 3) {
                    if (i != 4) {
                        if (i == 7) {
                            Log.d("CloudSemanticKernel", "MSG_RELEASE");
                            c();
                            this.v.a();
                            com.aispeech.lite.g.d dVar = this.g;
                            if (dVar != null) {
                                dVar.b();
                            }
                            com.aispeech.lite.g.d dVar2 = this.h;
                            if (dVar2 != null) {
                                dVar2.b();
                            }
                            PcmToOgg pcmToOgg = this.k;
                            if (pcmToOgg != null) {
                                pcmToOgg.destroyEncode();
                            }
                            DDSJSONResultParser dDSJSONResultParser = this.x;
                            if (dDSJSONResultParser != null) {
                                dDSJSONResultParser.destroy();
                            }
                            com.aispeech.c.a.a().b();
                            Log.d("CloudSemanticKernel", "MSG_RELEASE END");
                            continue;
                        } else if (i == 8) {
                            this.s = "";
                            this.f.a((AIError) b2.b);
                        } else if (i == 9) {
                            byte[] bArr = (byte[]) b2.b;
                            PcmToOgg pcmToOgg2 = this.k;
                            if (pcmToOgg2 != null) {
                                pcmToOgg2.feedData(bArr, bArr.length);
                            }
                        } else {
                            switch (i) {
                                case 20:
                                    if (TextUtils.equals("disconnect", (String) b2.b)) {
                                        c();
                                        break;
                                    }
                                    break;
                                case RemoteControlConfig.REMOTE_ACCOUNT_UPDATE_DATA /* 21 */:
                                    String str = (String) b2.b;
                                    Log.i("CloudSemanticKernel", "update params : " + str);
                                    com.aispeech.lite.g.d dVar3 = this.h;
                                    if (dVar3 != null && (cVar = this.i) != null) {
                                        dVar3.a(a(cVar), this.y);
                                        this.h.a(str);
                                        break;
                                    }
                                    break;
                                case BuildConfig.VERSION_CODE /* 22 */:
                                    String str2 = (String) b2.b;
                                    Log.i("CloudSemanticKernel", "update params : " + str2);
                                    if (TextUtils.isEmpty(str2)) {
                                        Log.w("CloudSemanticKernel", "data == null ");
                                        break;
                                    } else {
                                        JSONObject build = JSONUtil.build(str2);
                                        com.aispeech.c.b.d a = com.aispeech.c.a.a();
                                        com.aispeech.lite.c.c cVar2 = this.i;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append(new Date().getTime());
                                        String sb2 = sb.toString();
                                        StringBuilder sb3 = new StringBuilder();
                                        sb3.append(UUID.randomUUID());
                                        String sb4 = sb3.toString();
                                        String signature = AuthUtil.getSignature(cVar2.h() + sb4 + cVar2.k() + sb2, this.d.g());
                                        StringBuilder sb5 = new StringBuilder();
                                        sb5.append(com.aispeech.lite.c.c.b);
                                        sb5.append(URLUtils.URL_PATH_SEPERATOR);
                                        sb5.append((String) JSONUtil.getQuietly(build, "ctype"));
                                        sb5.append(URLUtils.URL_PATH_SEPERATOR);
                                        sb5.append((String) JSONUtil.getQuietly(build, "vocabName"));
                                        sb5.append("?");
                                        sb5.append("productId=");
                                        sb5.append(cVar2.k());
                                        sb5.append("&aliasKey=");
                                        sb5.append(cVar2.j());
                                        sb5.append("&deviceName=");
                                        sb5.append(cVar2.h());
                                        sb5.append("&nonce=");
                                        sb5.append(sb4);
                                        sb5.append("&sig=");
                                        sb5.append(signature);
                                        sb5.append("&timestamp=");
                                        sb5.append(sb2);
                                        Log.i("CloudSemanticKernel", "===url==" + sb5.toString());
                                        a.a(sb5.toString(), (String) JSONUtil.getQuietly(build, "data"), new com.aispeech.c.b.a() { // from class: com.aispeech.lite.i.a.1
                                            @Override // com.aispeech.c.b.a
                                            public final void onFailure(com.aispeech.c.b.d dVar4, IOException iOException) {
                                                Log.w("CloudSemanticKernel", dVar4.toString());
                                            }

                                            @Override // com.aispeech.c.b.a
                                            public final void onResponse(com.aispeech.c.b.d dVar4, com.aispeech.c.b.e eVar) throws IOException {
                                                Log.d("CloudSemanticKernel", "http response code: " + eVar.a());
                                                if (eVar.a() == 200) {
                                                    String d = eVar.d();
                                                    Log.d("CloudSemanticKernel", "== success : " + d);
                                                }
                                            }
                                        });
                                        break;
                                    }
                            }
                        }
                    } else if (this.n) {
                        PcmToOgg pcmToOgg3 = this.k;
                        if (pcmToOgg3 != null) {
                            pcmToOgg3.stopEncode();
                        }
                        FileUtil fileUtil = this.e;
                        if (fileUtil != null) {
                            fileUtil.closeFile();
                        }
                        this.n = false;
                    }
                } else if (this.n) {
                    PcmToOgg pcmToOgg4 = this.k;
                    if (pcmToOgg4 != null) {
                        pcmToOgg4.stopEncode();
                    }
                    com.aispeech.lite.g.d dVar4 = this.g;
                    if (dVar4 != null) {
                        dVar4.a(new byte[0]);
                        Log.d("CloudSemanticKernel", "ASR.END");
                        this.q = System.currentTimeMillis();
                        Log.d("CloudSemanticKernel", "mStopTime is " + this.q);
                    }
                    FileUtil fileUtil2 = this.e;
                    if (fileUtil2 != null) {
                        fileUtil2.closeFile();
                    }
                    this.n = false;
                }
                z = false;
                continue;
            } else {
                return;
            }
        } while (!z);
        a();
    }

    private void c() {
        Log.d("CloudSemanticKernel", "disconnect server");
        if (!this.w) {
            Log.d("CloudSemanticKernel", "socket disconnected ,drop disconnect operation");
        } else {
            this.g.a();
        }
        this.w = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        a(new com.aispeech.lite.f.a(20, "disconnect"));
    }

    @Override // com.aispeech.lite.g.c
    public final void onMessage(String str) {
        if (this.f != null) {
            Log.d("CloudSemanticKernel", "ASR.RESULT: " + str);
            if (this.m.compareAndSet(false, true)) {
                Log.d("CloudSemanticKernel", "receive first result after start " + str);
                this.p = System.currentTimeMillis();
                Log.d("CloudSemanticKernel", "mAsrFirstResultTime is " + this.p);
                this.x = new DDSJSONResultParser();
            }
            DDSJSONResultParser.DDSResultParseBean parse = this.x.parse(str);
            parse.setNlu(JSONUtil.normalSemanticSlots(parse.getNlu()));
            Log.d("CloudSemanticKernel", "throws:" + parse.getJso().toString());
            AIResult bundleResults = AIResult.bundleResults(0, this.s, parse.getJso().toString());
            if (this.j.d() == AIType.DM && parse.getEof() == 1) {
                bundleResults.setLast(true);
            }
            if (parse.getNlu() != null || parse.getDm() != null || parse.getError() != null) {
                bundleResults.setLast(true);
            }
            if (bundleResults.isLast()) {
                this.r = System.currentTimeMillis();
                Log.d("CloudSemanticKernel", "mAsrLastResultTime is " + this.r);
                Log.d("CloudSemanticKernel", "ASR.RESULT.DELAY: " + (this.r - this.q) + "ms");
            }
            this.f.a(bundleResults);
        }
    }

    /* renamed from: com.aispeech.lite.i.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0005a implements com.aispeech.lite.g.c {
        private C0005a() {
        }

        /* synthetic */ C0005a(byte b) {
            this();
        }

        @Override // com.aispeech.lite.g.c
        public final void onMessage(String str) {
            Log.d("CloudSemanticKernel", "cinfo result" + str);
        }

        @Override // com.aispeech.lite.g.c
        public final void onError(String str) {
            Log.e("CloudSemanticKernel", "cinfo error" + str);
        }

        @Override // com.aispeech.lite.g.c
        public final void onOpen() {
        }

        @Override // com.aispeech.lite.g.c
        public final void onClose() {
        }
    }

    @Override // com.aispeech.lite.g.c
    public final void onError(String str) {
        AIError aIError;
        StringBuilder sb = new StringBuilder("onError : ");
        sb.append(TextUtils.isEmpty(str) ? "is null" : str);
        Log.d("CloudSemanticKernel", sb.toString());
        if (!TextUtils.isEmpty(str) && str.contains("dns")) {
            aIError = new AIError(AIError.ERR_DNS, AIError.ERR_DESCRIPTION_ERR_DNS, this.s);
        } else if (!TextUtils.isEmpty(str) && str.contains("401 Unauthorized")) {
            aIError = new AIError(AIError.ERR_RETRY_INIT, AIError.ERR_RETRY_INIT_MSG, this.s);
        } else {
            aIError = new AIError(AIError.ERR_NETWORK, AIError.ERR_DESCRIPTION_ERR_NETWORK, this.s);
        }
        try {
            JSONObject jSONObject = new JSONObject(this.u);
            jSONObject.put("url", this.t);
            aIError.setInputJson(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        d();
        this.f.a(aIError);
    }

    @Override // com.aispeech.lite.g.c
    public final void onOpen() {
    }

    @Override // com.aispeech.lite.g.c
    public final void onClose() {
    }
}
