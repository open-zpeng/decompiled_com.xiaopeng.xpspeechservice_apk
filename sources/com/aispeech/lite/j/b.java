package com.aispeech.lite.j;

import android.os.Message;
import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.analysis.AnalysisProxy;
import com.aispeech.common.Log;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.c.m;
import com.aispeech.lite.h;
import com.aispeech.lite.h.q;
import com.aispeech.lite.oneshot.OneshotCache;
import com.aispeech.lite.oneshot.OneshotKernel;
import com.aispeech.lite.oneshot.OneshotListener;
import com.xiaopeng.lib.utils.config.CommonConfig;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class b extends h {
    private static String t = "WakeupProcessor";
    private com.aispeech.lite.j.a u;
    private q v;
    private m w;
    private c x;
    private OneshotKernel y;

    public final void a(c cVar, m mVar) {
        this.d = 1;
        this.x = cVar;
        if (mVar.i() != null) {
            this.y = new OneshotKernel(new a());
            this.d++;
        }
        a(cVar, com.aispeech.lite.c.b(), t, "wakeup");
        this.f = mVar;
        this.w = mVar;
        this.u = new com.aispeech.lite.j.a(new d(this));
        a(h.a.MSG_NEW, (Object) null);
    }

    public final void a(q qVar) {
        if (a()) {
            this.v = qVar;
            a(h.a.MSG_START, (Object) null);
            return;
        }
        i();
    }

    @Override // com.aispeech.lite.h
    protected final void a(h.a aVar, Message message) {
        c cVar;
        OneshotKernel oneshotKernel;
        switch (aVar) {
            case MSG_NEW:
                if (this.e == h.b.STATE_IDLE) {
                    if (!this.w.d() && this.b == null) {
                        this.b = a((com.aispeech.lite.b.d) this);
                        if (this.b == null) {
                            a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_DEVICE, AIError.ERR_DESCRIPTION_DEVICE));
                            return;
                        }
                    }
                    if (l() && this.w.h().toLowerCase().contains("aicar")) {
                        this.o = "aicar";
                        Log.d(t, "upload scene is : " + this.o);
                    }
                    if (this.w.i() != null) {
                        this.y.newKernel(this.w.i());
                    }
                    m mVar = this.w;
                    a(mVar);
                    this.u.newKernel(mVar);
                    return;
                }
                e("new");
                return;
            case MSG_START:
                if (this.e == h.b.STATE_NEWED) {
                    this.p = false;
                    d(Utils.get_recordid());
                    this.h = 0;
                    synchronized (this.m) {
                        if (this.g != null) {
                            this.g.clear();
                        }
                    }
                    if (this.w.d()) {
                        Log.i(t, "isUseCustomFeed");
                        this.u.startKernel(this.v);
                        a(h.b.STATE_RUNNING);
                        return;
                    }
                    b((com.aispeech.lite.b.d) this);
                    return;
                }
                e("start");
                return;
            case MSG_SET:
                String str = (String) message.obj;
                if (this.e != h.b.STATE_IDLE) {
                    this.u.set(str);
                    return;
                } else {
                    e("set info");
                    return;
                }
            case MSG_RECORDER_START:
                if (this.e == h.b.STATE_NEWED || this.e == h.b.STATE_WAITING) {
                    this.u.startKernel(this.v);
                    a(h.b.STATE_RUNNING);
                    return;
                }
                e("recorder start");
                return;
            case MSG_STOP:
                if (this.e == h.b.STATE_RUNNING) {
                    c(this);
                    h();
                    this.u.stopKernel();
                    a(h.b.STATE_NEWED);
                    return;
                }
                e("stop");
                return;
            case MSG_RAW_RECEIVE_DATA:
                byte[] bArr = (byte[]) message.obj;
                if (this.e == h.b.STATE_RUNNING && (cVar = this.x) != null) {
                    cVar.onRawDataReceived(bArr, bArr.length);
                    return;
                }
                return;
            case MSG_RESULT_RECEIVE_DATA:
                byte[] bArr2 = (byte[]) message.obj;
                if (this.e == h.b.STATE_RUNNING) {
                    if (l()) {
                        synchronized (this.m) {
                            if (this.k) {
                                if (this.h > this.i) {
                                    this.h -= this.g.remove().length;
                                }
                                this.h += bArr2.length;
                                this.g.offer(bArr2);
                            }
                        }
                    }
                    c cVar2 = this.x;
                    if (cVar2 != null) {
                        cVar2.onResultDataReceived(bArr2, bArr2.length, 0);
                    }
                    this.u.feed(bArr2);
                    if (this.w.i() != null && (oneshotKernel = this.y) != null) {
                        oneshotKernel.feed(bArr2);
                        return;
                    }
                    return;
                }
                return;
            case MSG_RESULT:
                AIResult aIResult = (AIResult) message.obj;
                if (this.e == h.b.STATE_RUNNING) {
                    try {
                        JSONObject jSONObject = new JSONObject(aIResult.getResultObject().toString());
                        long currentTimeMillis = System.currentTimeMillis() - this.r;
                        this.r = System.currentTimeMillis();
                        if (jSONObject.has(CommonConfig.KEY_VALUE_STATUS)) {
                            int optInt = jSONObject.optInt(CommonConfig.KEY_VALUE_STATUS);
                            aIResult.setRecordId(b());
                            if (optInt == 4) {
                                this.p = true;
                                this.l = false;
                                if (this.v != null && this.v.e()) {
                                    this.x.b(b(), jSONObject.optDouble("confidence"), jSONObject.optString("wakeupWord"));
                                }
                            } else if (optInt == 1) {
                                this.l = true;
                                if (currentTimeMillis <= this.j && this.p) {
                                    this.q = jSONObject;
                                }
                                this.x.a(b(), jSONObject.optDouble("confidence"), jSONObject.optString("wakeupWord"));
                                if (this.w.i() != null && this.y != null) {
                                    this.y.notifyWakeup(jSONObject.optString("wakeupWord"));
                                }
                            }
                            if (optInt != 0) {
                                AnalysisProxy.getInstance().updateConfig();
                            }
                            if (l() && ((this.p || this.l) && (currentTimeMillis > this.j || !this.p || !this.l))) {
                                this.q = jSONObject;
                                Log.d(t, "upload enable, invoke upload timerTask");
                                k();
                                return;
                            }
                            Log.w(t, "upload disable or new wakeup happens within 500ms, ignore");
                            return;
                        }
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                e("result");
                return;
            case MSG_RELEASE:
                if (this.e != h.b.STATE_IDLE) {
                    if (this.e == h.b.STATE_RUNNING) {
                        c(this);
                    }
                    h();
                    this.u.releaseKernel();
                    OneshotKernel oneshotKernel2 = this.y;
                    if (oneshotKernel2 != null) {
                        oneshotKernel2.releaseKernel();
                    }
                    g();
                    a(h.b.STATE_IDLE);
                    return;
                }
                e("release");
                return;
            case MSG_ERROR:
                AIError aIError = (AIError) message.obj;
                if (TextUtils.isEmpty(aIError.getRecordId())) {
                    aIError.setRecordId(Utils.get_recordid());
                }
                Log.w(t, aIError.toString());
                if (this.e == h.b.STATE_RUNNING || this.e == h.b.STATE_WAITING) {
                    c(this);
                    this.u.stopKernel();
                    a(h.b.STATE_NEWED);
                }
                c cVar3 = this.x;
                if (cVar3 != null) {
                    cVar3.onError((AIError) message.obj);
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    if (this.w != null) {
                        jSONObject2.put("config", this.w.g());
                    }
                    if (this.v != null) {
                        jSONObject2.put("param", this.v.a());
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                HashMap hashMap = new HashMap();
                hashMap.put(AIError.KEY_RECORD_ID, b());
                hashMap.put("mode", "lite");
                hashMap.put("module", "local_exception");
                AnalysisProxy.getInstance().getAnalysisMonitor().cacheData("local_wakeup_exception", "info", "local_exception", b(), jSONObject2, aIError.getOutputJSON(), hashMap);
                return;
            default:
                return;
        }
    }

    @Override // com.aispeech.lite.h
    public final void g() {
        super.g();
        if (this.v != null) {
            this.v = null;
        }
        if (this.w != null) {
            this.w = null;
        }
        if (this.u != null) {
            this.u = null;
        }
        if (this.y != null) {
            this.y = null;
        }
    }

    @Override // com.aispeech.lite.h
    public final void m() {
    }

    @Override // com.aispeech.lite.h
    public final void n() {
    }

    /* loaded from: classes.dex */
    class a implements OneshotListener {
        a() {
        }

        @Override // com.aispeech.lite.oneshot.OneshotListener
        public final void onInit(int i) {
            b.this.a(i);
        }

        @Override // com.aispeech.lite.oneshot.OneshotListener
        public final void onError(AIError aIError) {
            b.this.a(h.a.MSG_ERROR, aIError);
        }

        @Override // com.aispeech.lite.oneshot.OneshotListener
        public final void onOneshot(String str, OneshotCache<byte[]> oneshotCache) {
            if (b.this.x != null) {
                b.this.x.a(str, oneshotCache);
            }
        }

        @Override // com.aispeech.lite.oneshot.OneshotListener
        public final void onNotOneshot(String str) {
            if (b.this.x != null) {
                b.this.x.a(str);
            }
        }
    }
}
