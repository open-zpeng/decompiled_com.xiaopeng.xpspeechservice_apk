package com.aispeech.lite.a;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.common.AuthUtil;
import com.aispeech.common.FileUtil;
import com.aispeech.common.JSONResultParser;
import com.aispeech.common.Log;
import com.aispeech.common.PcmToOgg;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.g;
import com.aispeech.lite.h.m;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class d extends g implements com.aispeech.lite.g.c {
    public static String e = "CloudAsrKernel";
    FileUtil f;
    private com.aispeech.lite.a.a g;
    private m h;
    private volatile boolean i;
    private com.aispeech.lite.g.d j;
    private PcmToOgg k;
    private String l;
    private String m;
    private com.aispeech.lite.c.a n;
    private AtomicBoolean o;
    private AtomicBoolean p;
    private String q;
    private long r;
    private long s;
    private long t;
    private long u;
    private JSONResultParser v;
    private boolean w;
    private final List<byte[]> x;

    static /* synthetic */ void a(d dVar, byte[] bArr) {
        synchronized (dVar.x) {
            byte[] bArr2 = null;
            if (!dVar.x.isEmpty()) {
                for (byte[] bArr3 : dVar.x) {
                    if (bArr3.length == 0) {
                        bArr2 = bArr3;
                    } else {
                        dVar.j.a(bArr3);
                    }
                }
                dVar.x.clear();
            }
            dVar.j.a(bArr);
            if (bArr2 != null) {
                dVar.j.a(bArr2);
                Log.d(e, "ASR.END real");
            }
        }
    }

    public d(com.aispeech.lite.a.a aVar) {
        super(e);
        this.i = false;
        this.f = new FileUtil(com.aispeech.lite.c.b());
        this.o = new AtomicBoolean(true);
        this.p = new AtomicBoolean(false);
        this.w = false;
        this.x = new ArrayList();
        this.g = aVar;
    }

    @Override // com.aispeech.lite.g, java.lang.Runnable
    public final void run() {
        boolean z;
        super.run();
        do {
            com.aispeech.lite.f.a b = b();
            if (b != null) {
                int i = b.a;
                z = true;
                if (i == 1) {
                    this.n = (com.aispeech.lite.c.a) b.b;
                    this.j = new com.aispeech.lite.g.d();
                    this.k = new PcmToOgg();
                    this.k.initEncode(new a());
                    this.g.a(0);
                } else if (i == 2) {
                    com.aispeech.lite.g.d dVar = this.j;
                    if (dVar != null) {
                        dVar.a();
                    }
                    this.h = (m) b.b;
                    com.aispeech.lite.c.a aVar = this.n;
                    StringBuilder sb = new StringBuilder();
                    sb.append(new Date().getTime());
                    String sb2 = sb.toString();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(UUID.randomUUID());
                    String sb4 = sb3.toString();
                    String str = aVar.h() + "?productId=" + aVar.k() + "&apikey=" + this.d.c() + "&res=" + aVar.v() + "&deviceName=" + aVar.l() + "&timestamp=" + sb2 + "&nonce=" + sb4 + "&sig=" + AuthUtil.getSignature(aVar.l() + sb4 + aVar.k() + sb2, this.d.g());
                    this.m = str;
                    Log.d(e, "url: " + str);
                    this.l = this.h.c();
                    String v = this.h.v();
                    if (!TextUtils.isEmpty(v)) {
                        Log.d(e, "create local ogg file at: " + v + "/cloud_asr_" + this.l + ".ogg");
                        this.f.createFile(v + "/cloud_asr_" + this.l + ".ogg");
                    }
                    this.j.a(str, this);
                    this.n.e(this.l);
                    com.aispeech.lite.c.a aVar2 = this.n;
                    aVar2.b(this.d.a());
                    aVar2.d(this.d.f());
                    String j = aVar2.j();
                    this.q = j;
                    com.aispeech.lite.g.d dVar2 = this.j;
                    if (dVar2 != null) {
                        dVar2.a(j);
                    }
                    this.i = true;
                    PcmToOgg pcmToOgg = this.k;
                    if (pcmToOgg != null) {
                        pcmToOgg.startEncode(8, 16000, 0, 2);
                    }
                    Log.d(e, "ASR.BEGIN");
                    this.o.compareAndSet(true, false);
                    this.p.set(false);
                    this.w = false;
                    this.x.clear();
                } else if (i != 3) {
                    if (i == 4) {
                        if (this.i) {
                            PcmToOgg pcmToOgg2 = this.k;
                            if (pcmToOgg2 != null) {
                                pcmToOgg2.stopEncode();
                            }
                            FileUtil fileUtil = this.f;
                            if (fileUtil != null) {
                                fileUtil.closeFile();
                            }
                            this.i = false;
                        }
                        com.aispeech.lite.g.d dVar3 = this.j;
                        if (dVar3 != null) {
                            dVar3.a();
                        }
                    } else if (i == 7) {
                        Log.d(e, "MSG_RELEASE");
                        com.aispeech.lite.g.d dVar4 = this.j;
                        if (dVar4 != null) {
                            dVar4.b();
                        }
                        PcmToOgg pcmToOgg3 = this.k;
                        if (pcmToOgg3 != null) {
                            pcmToOgg3.destroyEncode();
                        }
                        Log.d(e, "MSG_RELEASE END");
                        continue;
                    } else if (i == 8) {
                        this.g.a((AIError) b.b);
                    } else if (i == 9) {
                        byte[] bArr = (byte[]) b.b;
                        PcmToOgg pcmToOgg4 = this.k;
                        if (pcmToOgg4 != null) {
                            pcmToOgg4.feedData(bArr, bArr.length);
                        }
                    }
                } else if (this.i) {
                    PcmToOgg pcmToOgg5 = this.k;
                    if (pcmToOgg5 != null) {
                        pcmToOgg5.stopEncode();
                    }
                    com.aispeech.lite.g.d dVar5 = this.j;
                    if (dVar5 != null) {
                        dVar5.a(new byte[0]);
                        Log.d(e, "ASR.END");
                        this.t = System.currentTimeMillis();
                        Log.d(e, "mStopTime is " + this.t);
                    }
                    FileUtil fileUtil2 = this.f;
                    if (fileUtil2 != null) {
                        fileUtil2.closeFile();
                    }
                    this.i = false;
                }
                z = false;
                continue;
            } else {
                return;
            }
        } while (!z);
        a();
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x01cf A[Catch: JSONException -> 0x01e4, TryCatch #0 {JSONException -> 0x01e4, blocks: (B:75:0x01c7, B:77:0x01cf, B:78:0x01d9), top: B:85:0x01c7 }] */
    @Override // com.aispeech.lite.g.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMessage(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.lite.a.d.onMessage(java.lang.String):void");
    }

    @Override // com.aispeech.lite.g.c
    public final void onError(String str) {
        AIError aIError;
        String str2 = e;
        StringBuilder sb = new StringBuilder("onError : ");
        sb.append(TextUtils.isEmpty(str) ? "is null" : str);
        Log.d(str2, sb.toString());
        if (!TextUtils.isEmpty(str) && str.contains("dns")) {
            aIError = new AIError(AIError.ERR_DNS, AIError.ERR_DESCRIPTION_ERR_DNS, this.l);
        } else {
            aIError = new AIError(AIError.ERR_NETWORK, AIError.ERR_DESCRIPTION_ERR_NETWORK, this.l);
        }
        try {
            JSONObject jSONObject = new JSONObject(this.q);
            jSONObject.put("url", this.m);
            aIError.setInputJson(jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        a(new com.aispeech.lite.f.a(8, aIError));
    }

    @Override // com.aispeech.lite.g.c
    public final void onOpen() {
    }

    @Override // com.aispeech.lite.g.c
    public final void onClose() {
    }

    /* loaded from: classes.dex */
    class a extends Utils.speex_callback {
        a() {
        }

        @Override // com.aispeech.kernel.Utils.speex_callback
        public final int run(int i, byte[] bArr, int i2) {
            if (d.this.j != null && d.this.i) {
                if (d.this.o.compareAndSet(false, true) && i2 != 0) {
                    Log.d(d.e, "ASR.FIRST.FEED");
                    d.this.r = System.currentTimeMillis();
                    String str = d.e;
                    Log.d(str, "mFirstFeedTime is " + d.this.r);
                }
                if (i2 != 0) {
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, 0, bArr2, 0, i2);
                    if (!d.this.n.A().a()) {
                        d.this.j.a(bArr2);
                    } else {
                        d.a(d.this, bArr2);
                    }
                    d.this.f.write(bArr2);
                }
            } else {
                Log.e(d.e, " ERROR ERROR ERROR ERROR ");
            }
            return 0;
        }
    }

    public final String c() {
        return this.l;
    }
}
