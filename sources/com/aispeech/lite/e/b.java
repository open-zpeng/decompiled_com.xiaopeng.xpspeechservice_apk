package com.aispeech.lite.e;

import android.os.Message;
import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.analysis.AnalysisProxy;
import com.aispeech.common.JSONUtil;
import com.aispeech.common.Log;
import com.aispeech.common.NetworkUtil;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.a.e;
import com.aispeech.lite.c;
import com.aispeech.lite.c.l;
import com.aispeech.lite.e.a.d;
import com.aispeech.lite.h;
import com.aispeech.lite.h.i;
import com.aispeech.lite.h.m;
import com.aispeech.lite.h.o;
import com.aispeech.lite.vad.VadKernelListener;
import com.xiaopeng.lib.utils.config.RemoteControlConfig;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class b extends h {
    private i A;
    private long B;
    private long C;
    private long D;
    private com.aispeech.lite.e.a t;
    private com.aispeech.lite.a u;
    private l v;
    private e w;
    private com.aispeech.lite.vad.a x;
    private d y;
    private o z;

    public final void a(com.aispeech.lite.e.a aVar, com.aispeech.lite.c.e eVar, l lVar) {
        this.t = aVar;
        this.u = eVar;
        this.v = lVar;
        if (lVar.a() || lVar.h()) {
            this.d++;
        }
        a(aVar, eVar.b(), "HotWordsProcessor", "asr-" + eVar.h());
        this.w = new e("hotword", new a(this, (byte) 0));
        this.w.setProfile(this.s);
        a(h.a.MSG_NEW, (Object) null);
    }

    public final void a(i iVar, o oVar) {
        if (a()) {
            this.A = iVar;
            this.z = oVar;
            a(h.a.MSG_START, (Object) null);
            return;
        }
        i();
    }

    /* renamed from: com.aispeech.lite.e.b$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[h.a.values().length];

        static {
            try {
                a[h.a.MSG_NEW.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[h.a.MSG_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[h.a.MSG_RECORDER_START.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[h.a.MSG_STOP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[h.a.MSG_CANCEL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[h.a.MSG_RAW_RECEIVE_DATA.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[h.a.MSG_RESULT_RECEIVE_DATA.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[h.a.MSG_VAD_RECEIVE_DATA.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[h.a.MSG_VAD_START.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[h.a.MSG_VAD_END.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[h.a.MSG_VOLUME_CHANGED.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[h.a.MSG_DOA.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[h.a.MSG_RESULT.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[h.a.MSG_RELEASE.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[h.a.MSG_ERROR.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    @Override // com.aispeech.lite.h
    protected final void a(h.a aVar, Message message) {
        com.aispeech.lite.e.a aVar2;
        com.aispeech.lite.e.a aVar3;
        switch (AnonymousClass1.a[aVar.ordinal()]) {
            case 1:
                if (this.e == h.b.STATE_IDLE) {
                    a(this.u);
                    if (!this.u.d() && this.b == null && (c.j == 0 || c.j == 4)) {
                        this.b = a((com.aispeech.lite.b.d) this);
                        if (this.b == null) {
                            a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_DEVICE, AIError.ERR_DESCRIPTION_DEVICE));
                            return;
                        }
                    }
                    if (this.v.h()) {
                        a(this.v);
                        this.y = new d(new C0003b(this, (byte) 0));
                        this.y.newKernel(this.v);
                    } else if (this.v.a()) {
                        a(this.v);
                        this.x = new com.aispeech.lite.vad.a("hotWord", new C0003b(this, (byte) 0));
                        this.x.newKernel(this.v);
                    }
                    this.w.newKernel(this.u);
                    return;
                }
                e("new");
                return;
            case 2:
                if (this.e == h.b.STATE_NEWED) {
                    a((m) this.A, this.z);
                    if (this.e == h.b.STATE_NEWED) {
                        if (this.u.d()) {
                            Log.i("HotWordsProcessor", "isUseCustomFeed");
                            this.w.startKernel(this.A);
                            if (this.v.h()) {
                                a(this.A);
                                this.y.startKernel(this.z);
                            } else if (this.v.a()) {
                                a(this.A);
                                this.x.startKernel(this.z);
                            }
                            a(h.b.STATE_RUNNING);
                            return;
                        }
                        b((com.aispeech.lite.b.d) this);
                        return;
                    }
                    return;
                }
                e("start");
                return;
            case 3:
                if (this.e == h.b.STATE_NEWED || this.e == h.b.STATE_WAITING) {
                    this.w.startKernel(this.A);
                    if (this.v.h()) {
                        a(this.A);
                        this.y.startKernel(this.z);
                    } else if (this.v.a()) {
                        a(this.A);
                        this.x.startKernel(this.z);
                    }
                    a(h.b.STATE_RUNNING);
                    return;
                }
                e("recorder start");
                return;
            case 4:
                if (this.e == h.b.STATE_RUNNING) {
                    c(this);
                    this.w.stopKernel();
                    if (this.v.h()) {
                        this.y.stopKernel();
                    } else if (this.v.a()) {
                        this.x.stopKernel();
                    }
                    a(h.b.STATE_WAITING);
                    return;
                }
                e("stop");
                return;
            case 5:
                if (this.e == h.b.STATE_RUNNING || this.e == h.b.STATE_WAITING) {
                    if (!this.u.d()) {
                        c(this);
                    }
                    this.w.cancelKernel();
                    if (this.v.h()) {
                        this.y.stopKernel();
                    } else if (this.v.a()) {
                        this.x.stopKernel();
                    }
                    a(h.b.STATE_NEWED);
                    return;
                }
                e("cancel");
                return;
            case 6:
                byte[] bArr = (byte[]) message.obj;
                if (this.e == h.b.STATE_RUNNING && (aVar2 = this.t) != null) {
                    aVar2.onRawDataReceived(bArr, bArr.length);
                    return;
                }
                return;
            case 7:
                byte[] bArr2 = (byte[]) message.obj;
                if (this.e == h.b.STATE_RUNNING || this.e == h.b.STATE_WAITING) {
                    if (this.v.h()) {
                        this.y.feed(bArr2);
                    } else if (this.v.a()) {
                        this.x.feed(bArr2);
                    } else {
                        this.w.feed(bArr2);
                    }
                    com.aispeech.lite.e.a aVar4 = this.t;
                    if (aVar4 != null) {
                        aVar4.onResultDataReceived(bArr2, bArr2.length, 0);
                        return;
                    }
                    return;
                }
                return;
            case 8:
                byte[] bArr3 = (byte[]) message.obj;
                if (this.e == h.b.STATE_RUNNING) {
                    this.w.feed(bArr3);
                    return;
                }
                return;
            case 9:
                if (this.e == h.b.STATE_RUNNING) {
                    Log.d("HotWordsProcessor", "VAD.BEGIN");
                    this.B = System.currentTimeMillis();
                    j();
                    b(this.A);
                    com.aispeech.lite.e.a aVar5 = this.t;
                    if (aVar5 != null) {
                        aVar5.b();
                        return;
                    }
                    return;
                }
                e("VAD.BEGIN");
                return;
            case 10:
                if (this.e == h.b.STATE_RUNNING) {
                    Log.d("HotWordsProcessor", "VAD.END");
                    this.C = System.currentTimeMillis();
                    c(this);
                    this.w.stopKernel();
                    if (this.v.h()) {
                        this.y.stopKernel();
                    } else if (this.v.a()) {
                        this.x.stopKernel();
                    }
                    a(h.b.STATE_WAITING);
                    com.aispeech.lite.e.a aVar6 = this.t;
                    if (aVar6 != null) {
                        aVar6.c();
                        return;
                    }
                    return;
                }
                e("VAD.END");
                return;
            case 11:
                float floatValue = ((Float) message.obj).floatValue();
                if (this.e == h.b.STATE_RUNNING) {
                    com.aispeech.lite.e.a aVar7 = this.t;
                    if (aVar7 != null) {
                        aVar7.a(floatValue);
                        return;
                    }
                    return;
                }
                e("volume changed");
                return;
            case 12:
                int intValue = ((Integer) message.obj).intValue();
                if (this.e == h.b.STATE_RUNNING && (aVar3 = this.t) != null) {
                    aVar3.a(intValue);
                    return;
                }
                return;
            case 13:
                AIResult aIResult = (AIResult) message.obj;
                if (this.e == h.b.STATE_RUNNING || this.e == h.b.STATE_WAITING) {
                    this.D = System.currentTimeMillis();
                    Log.d("HotWordsProcessor", "VAD.END.HOTWORDS.RESULT : " + (this.D - this.C) + "ms");
                    Log.d("HotWordsProcessor", "VAD.BEGIN.HOTWORDS.RESULT : " + (this.D - this.B) + "ms");
                    com.aispeech.lite.e.a aVar8 = this.t;
                    if (aVar8 != null) {
                        aVar8.a(aIResult);
                    }
                    if (aIResult.isLast()) {
                        a(h.b.STATE_NEWED);
                        c(this);
                    }
                    if (this.A.e()) {
                        o();
                        return;
                    }
                    return;
                }
                e("result");
                return;
            case 14:
                if (this.e != h.b.STATE_IDLE) {
                    if (this.e == h.b.STATE_RUNNING) {
                        c(this);
                        e eVar = this.w;
                        if (eVar != null) {
                            eVar.stopKernel();
                        }
                        if (this.v.h()) {
                            this.y.stopKernel();
                        } else if (this.v.a()) {
                            this.x.stopKernel();
                        }
                    }
                    h();
                    j();
                    this.w.releaseKernel();
                    this.w = null;
                    com.aispeech.lite.vad.a aVar9 = this.x;
                    if (aVar9 != null) {
                        aVar9.releaseKernel();
                        this.x = null;
                    }
                    d dVar = this.y;
                    if (dVar != null) {
                        dVar.releaseKernel();
                        this.y = null;
                    }
                    g();
                    a(h.b.STATE_IDLE);
                    return;
                }
                e("release");
                return;
            case RemoteControlConfig.REMOTE_ACCOUNT_FACE_BIND /* 15 */:
                AIError aIError = (AIError) message.obj;
                if (TextUtils.isEmpty(aIError.getRecordId())) {
                    aIError.setRecordId(Utils.get_recordid());
                }
                if (aIError.getErrId() == 70920) {
                    Log.w("HotWordsProcessor", aIError.toString());
                    com.aispeech.lite.e.a aVar10 = this.t;
                    if (aVar10 != null) {
                        aVar10.onError(aIError);
                    }
                    b(aIError);
                    return;
                } else if (this.e == h.b.STATE_IDLE) {
                    com.aispeech.lite.e.a aVar11 = this.t;
                    if (aVar11 != null) {
                        aVar11.onError(aIError);
                    }
                    b(aIError);
                    return;
                } else if (this.e != h.b.STATE_NEWED && this.e != h.b.STATE_IDLE) {
                    c(this);
                    if (aIError.getErrId() == 70961) {
                        this.w.cancelKernel();
                    } else {
                        this.w.stopKernel();
                    }
                    if (this.v.h()) {
                        this.y.stopKernel();
                    } else if (this.v.a()) {
                        this.x.stopKernel();
                    }
                    a(h.b.STATE_NEWED);
                    Log.w("HotWordsProcessor", aIError.toString());
                    b(aIError);
                    if (aIError.getErrId() == 70912) {
                        aIError.setErrId(AIError.ERR_NETWORK);
                        aIError.setError(AIError.ERR_DESCRIPTION_ERR_NETWORK);
                    }
                    com.aispeech.lite.e.a aVar12 = this.t;
                    if (aVar12 != null) {
                        aVar12.onError(aIError);
                        return;
                    }
                    return;
                } else {
                    e(AIError.KEY_TEXT);
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.aispeech.lite.h
    public final void m() {
        a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_NO_SPEECH, AIError.ERR_DESCRIPTION_NO_SPEECH));
    }

    @Override // com.aispeech.lite.h
    public final void n() {
        a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_MAX_SPEECH, AIError.ERR_DESCRIPTION_MAX_SPEECH));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        a(h.b.STATE_NEWED);
        a(h.a.MSG_START, (Object) null);
    }

    private void b(AIError aIError) {
        String str;
        if ((aIError.getErrId() == 70911 || aIError.getErrId() == 70912) && !NetworkUtil.isNetworkConnected(c.b())) {
            Log.d("HotWordsProcessor", "network is not connected, ignore upload error");
            return;
        }
        String recordId = aIError.getRecordId();
        if (!TextUtils.isEmpty(recordId)) {
            str = recordId;
        } else {
            str = Utils.get_recordid();
        }
        HashMap hashMap = new HashMap();
        hashMap.put(AIError.KEY_RECORD_ID, str);
        hashMap.put("mode", "lite");
        hashMap.put("module", "local_exception");
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.u != null) {
                jSONObject.put("config", this.u.g());
            }
            if (this.A != null) {
                jSONObject.put("param", this.A.a());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AnalysisProxy.getInstance().getAnalysisMonitor().cacheData("local_asr_exception", "info", "local_exception", str, jSONObject, aIError.getOutputJSON(), hashMap);
    }

    /* renamed from: com.aispeech.lite.e.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0003b implements com.aispeech.lite.e.a.e, VadKernelListener {
        private C0003b() {
        }

        /* synthetic */ C0003b(b bVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.e.a.e, com.aispeech.lite.vad.VadKernelListener
        public final void onInit(int i) {
            Log.i("HotWordsProcessor", "VadListenerImpl onInit : " + i);
            b.this.a(i);
        }

        @Override // com.aispeech.lite.e.a.e, com.aispeech.lite.vad.VadKernelListener
        public final void onVadStart() {
            b.this.a(h.a.MSG_VAD_START, (Object) null);
        }

        @Override // com.aispeech.lite.e.a.e, com.aispeech.lite.vad.VadKernelListener
        public final void onVadEnd() {
            b.this.a(h.a.MSG_VAD_END, (Object) null);
        }

        @Override // com.aispeech.lite.e.a.e
        public final void a(int i) {
            b.this.a(h.a.MSG_DOA, Integer.valueOf(i));
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onRmsChanged(float f) {
            b.this.a(h.a.MSG_VOLUME_CHANGED, Float.valueOf(f));
        }

        @Override // com.aispeech.lite.e.a.e, com.aispeech.lite.vad.VadKernelListener
        public final void onBufferReceived(byte[] bArr) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            b.this.a(h.a.MSG_VAD_RECEIVE_DATA, bArr2);
        }

        @Override // com.aispeech.lite.e.a.e, com.aispeech.lite.vad.VadKernelListener
        public final void onError(AIError aIError) {
            b.this.a(h.a.MSG_ERROR, aIError);
        }
    }

    /* loaded from: classes.dex */
    class a implements com.aispeech.lite.a.a {
        private a() {
        }

        /* synthetic */ a(b bVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.a.a
        public final void a(int i) {
            Log.i("HotWordsProcessor", "AsrKernelListener onInit : " + i);
            b.this.a(i);
        }

        @Override // com.aispeech.lite.a.a
        public final void a(AIError aIError) {
            b.this.a(h.a.MSG_ERROR, aIError);
        }

        @Override // com.aispeech.lite.a.a
        public final void a(AIResult aIResult) {
            double g;
            try {
                JSONObject build = JSONUtil.build(aIResult.getResultObject().toString());
                Object optQuietly = JSONUtil.optQuietly(build, "forceout");
                Object quietly = JSONUtil.getQuietly(build, "conf");
                Object quietly2 = JSONUtil.getQuietly(build, "rec");
                String obj = quietly2 != null ? quietly2.toString() : "";
                if (optQuietly != null && Integer.parseInt(optQuietly.toString()) == 1) {
                    Log.d("HotWordsProcessor", "DROP FORCE OUT HOT WORD " + aIResult.getResultObject());
                    b.this.o();
                    return;
                }
                if (quietly != null) {
                    double parseDouble = Double.parseDouble(quietly.toString());
                    i iVar = b.this.A;
                    if (!iVar.j().isEmpty() && iVar.j().containsKey(obj)) {
                        g = iVar.j().get(obj).doubleValue();
                    } else {
                        g = iVar.g();
                    }
                    if (parseDouble < g) {
                        Log.d("HotWordsProcessor", "DROP CONF NOT QUALIFIED HOT WORD " + aIResult.getResultObject());
                        b.this.o();
                        return;
                    }
                }
                if (!TextUtils.isEmpty(obj)) {
                    b.this.a(h.a.MSG_RESULT, aIResult);
                    return;
                }
                Log.d("HotWordsProcessor", "DROP REC NOT QUALIFIED HOT WORD " + aIResult.getResultObject());
                b.this.o();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("HotWordsProcessor", "DROP NO CONF HOT WORD " + aIResult.getResultObject());
                b.this.o();
            }
        }

        @Override // com.aispeech.lite.a.a
        public final void b(int i) {
        }
    }
}
