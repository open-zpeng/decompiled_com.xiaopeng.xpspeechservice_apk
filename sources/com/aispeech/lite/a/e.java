package com.aispeech.lite.a;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.analysis.AnalysisProxy;
import com.aispeech.common.FileUtil;
import com.aispeech.common.Log;
import com.aispeech.export.ASRMode;
import com.aispeech.kernel.Asr;
import com.aispeech.lite.g;
import com.aispeech.lite.h.i;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class e extends g implements Asr.asr_callback {
    private String e;
    private Asr f;
    private a g;
    private com.aispeech.lite.c.e h;
    private i i;
    private volatile boolean j;
    private String k;
    private FileUtil l;
    private AtomicBoolean m;
    private long n;
    private long o;

    public e(String str, a aVar) {
        super(str + "-asr");
        this.j = true;
        this.l = new FileUtil(com.aispeech.lite.c.b());
        this.m = new AtomicBoolean(true);
        this.e = str + "-LocalAsrKernel";
        this.g = aVar;
    }

    private int a(Asr asr, String str) {
        if (!TextUtils.isEmpty(str)) {
            String str2 = this.e;
            Log.d(str2, "config: " + str);
            if (asr.init(str, this) == 0) {
                Log.e(this.e, "引擎初始化失败,请检查资源文件是否在指定路径下！");
            } else {
                Log.d(this.e, "引擎初始化成功");
                return 0;
            }
        }
        return -1;
    }

    @Override // com.aispeech.lite.g, java.lang.Runnable
    public final void run() {
        boolean z;
        Asr asr;
        super.run();
        do {
            com.aispeech.lite.f.a b = b();
            if (b != null) {
                int i = b.a;
                z = false;
                if (i == 1) {
                    this.f = new Asr();
                    com.aispeech.lite.c.e eVar = (com.aispeech.lite.c.e) b.b;
                    this.h = eVar;
                    this.g.a(a(this.f, eVar.g().toString()));
                    continue;
                } else if (i == 2) {
                    this.i = (i) b.b;
                    String jSONObject = this.i.a().toString();
                    this.k = this.i.c();
                    String v = this.i.v();
                    if (!TextUtils.isEmpty(v) && this.l != null) {
                        Log.d(this.e, "create local asr audio file at: " + v + "/local_asr_" + this.k + ".pcm");
                        this.l.createFile(v + "/local_asr_" + this.k + ".pcm");
                    }
                    Log.d(this.e, "local asr param: " + jSONObject);
                    Asr asr2 = this.f;
                    if (asr2 != null) {
                        Log.d(this.e, "engine start before");
                        int start = asr2.start(jSONObject);
                        Log.d(this.e, "engine start end");
                        Log.d(this.e, "ret:" + start);
                        if (start < 0) {
                            a(new com.aispeech.lite.f.a(8, new AIError(AIError.ERR_AI_ENGINE, AIError.ERR_DESCRIPTION_AI_ENGINE, this.k)));
                        }
                        Log.d(this.e, "LOCAL.ASR.BEGIN");
                        this.m.compareAndSet(true, false);
                        this.j = false;
                        continue;
                    } else {
                        continue;
                    }
                } else if (i == 3) {
                    if (this.f != null) {
                        Log.d(this.e, "LOCAL.ASR.END");
                        this.n = System.currentTimeMillis();
                        Log.d(this.e, "startWaitResult : " + this.n);
                        this.f.stop();
                    }
                    this.j = true;
                    FileUtil fileUtil = this.l;
                    if (fileUtil != null) {
                        fileUtil.closeFile();
                        continue;
                    } else {
                        continue;
                    }
                } else if (i == 4) {
                    if (this.f != null) {
                        Log.d(this.e, "LOCAL.ASR.CANCEL");
                        this.f.cancel();
                        Log.d(this.e, "cancel.wait.time : " + System.currentTimeMillis());
                    }
                    this.j = true;
                    FileUtil fileUtil2 = this.l;
                    if (fileUtil2 != null) {
                        fileUtil2.closeFile();
                        continue;
                    } else {
                        continue;
                    }
                } else if (i == 7) {
                    Asr asr3 = this.f;
                    if (asr3 != null) {
                        asr3.destroy();
                        this.f = null;
                    }
                    this.j = true;
                    z = true;
                    continue;
                } else if (i == 8) {
                    this.g.a((AIError) b.b);
                    continue;
                } else if (i == 9) {
                    byte[] bArr = (byte[]) b.b;
                    if (this.f != null && !this.j) {
                        if (bArr == null || bArr.length == 0) {
                            continue;
                        } else {
                            if (this.m.compareAndSet(false, true) && bArr.length != 0) {
                                Log.d(this.e, "LOCAL.ASR.FIRST.FEED");
                            }
                            this.f.feed(bArr);
                            FileUtil fileUtil3 = this.l;
                            if (fileUtil3 != null) {
                                fileUtil3.write(bArr);
                                continue;
                            } else {
                                continue;
                            }
                        }
                    }
                } else if (i == 21 && (asr = this.f) != null) {
                    asr.destroy();
                    int a = a(this.f, (String) b.b);
                    Log.d(this.e, "update asr status : " + a);
                    if (a == -1) {
                        this.g.a(new AIError((int) AIError.ERR_NET_BIN_INVALID, AIError.ERR_DESCRIPTION_INVALID_NET_BIN));
                    }
                    this.g.b(a);
                    continue;
                }
            } else {
                return;
            }
        } while (!z);
        a();
    }

    @Override // com.aispeech.lite.g
    public final void cancelKernel() {
        this.j = true;
        super.cancelKernel();
    }

    @Override // com.aispeech.kernel.Asr.asr_callback
    public final int run(int i, byte[] bArr, int i2) {
        int i3;
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        Log.d(this.e, "LOCAL.ASR.CALLBACK: " + new String(bArr2).trim());
        if (!this.j) {
            String trim = new String(bArr2).trim();
            Log.d(this.e, "LOCAL.ASR.RESULT: " + trim);
            try {
                JSONObject jSONObject = new JSONObject(trim);
                AIResult aIResult = new AIResult();
                aIResult.setResultType(0);
                aIResult.setResultObject(trim);
                aIResult.setTimestamp(System.currentTimeMillis());
                aIResult.setRecordId(this.k);
                if (jSONObject.has("eof")) {
                    i3 = jSONObject.optInt("eof", 1);
                } else if (!jSONObject.has("grammar")) {
                    i3 = 0;
                } else {
                    JSONObject optJSONObject = jSONObject.optJSONObject("grammar");
                    if (!optJSONObject.has("eof")) {
                        i3 = 0;
                    } else {
                        i3 = optJSONObject.optInt("eof", 1);
                    }
                    Log.d(this.e, "eof in grammar is: " + i3);
                }
                if (i3 == 1) {
                    aIResult.setLast(true);
                    this.o = System.currentTimeMillis();
                    Log.d(this.e, "getLastResult : " + this.o);
                    Log.d(this.e, "LOCAL.ASR.RESULT.DELAY: " + (this.o - this.n) + "ms");
                    HashMap hashMap = new HashMap();
                    hashMap.put(AIError.KEY_RECORD_ID, this.k);
                    hashMap.put("mode", "lite");
                    hashMap.put("module", "local_cost");
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        if (this.h != null) {
                            jSONObject2.put("config", this.h.g());
                        }
                        if (this.i != null) {
                            jSONObject2.put("param", this.i.a());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jSONObject3 = new JSONObject();
                    try {
                        jSONObject3.put("asrtotalcost", this.o - this.n);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    AnalysisProxy.getInstance().getAnalysisMonitor().cacheData("local_asr_cost", "info", "local_cost", this.k, jSONObject2, jSONObject3, hashMap);
                } else {
                    aIResult.setLast(false);
                }
                if (this.g != null) {
                    this.g.a(a(aIResult));
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        } else {
            Log.d(this.e, "drop already stop result ");
        }
        FileUtil fileUtil = this.l;
        if (fileUtil != null) {
            fileUtil.closeFile();
        }
        return 0;
    }

    private AIResult a(AIResult aIResult) {
        try {
            JSONObject jSONObject = new JSONObject(aIResult.getResultObject().toString());
            if (this.i.a == ASRMode.MODE_ASR.getValue()) {
                if (jSONObject.has("dynamic")) {
                    jSONObject.remove("dynamic");
                }
            } else if (this.i.a == ASRMode.MODE_HOTWORD.getValue()) {
                if (jSONObject.has("grammar")) {
                    jSONObject.remove("grammar");
                }
                if (jSONObject.has("ngram")) {
                    jSONObject.remove("ngram");
                }
            }
            aIResult.setResultObject(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aIResult;
    }
}
