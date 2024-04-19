package com.aispeech.lite.a;

import android.os.Message;
import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.analysis.AnalysisProxy;
import com.aispeech.common.AIConstant;
import com.aispeech.common.AITimer;
import com.aispeech.common.Log;
import com.aispeech.common.NetworkUtil;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.c.j;
import com.aispeech.lite.g;
import com.aispeech.lite.h;
import com.aispeech.lite.h.m;
import com.aispeech.lite.h.o;
import com.aispeech.lite.i;
import com.aispeech.lite.oneshot.OneshotCache;
import com.aispeech.lite.vad.VadKernelListener;
import com.xiaopeng.lib.utils.config.RemoteControlConfig;
import io.reactivex.annotations.SchedulerSupport;
import java.util.HashMap;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class b extends h {
    public static String t = "";
    private String A;
    private com.aispeech.lite.a.c B;
    private long C;
    private long D;
    private long E;
    private a F = null;
    private g u;
    private m v;
    private com.aispeech.lite.a w;
    private com.aispeech.lite.vad.a x;
    private o y;
    private j z;

    public final void a(com.aispeech.lite.a.c cVar, com.aispeech.lite.a aVar, j jVar, String str) {
        String str2;
        this.B = cVar;
        this.A = str;
        this.z = jVar;
        t = "localAsrProcessor";
        if (jVar.a()) {
            this.d++;
        }
        if ("cloudAsr".equals(this.A)) {
            str2 = i.a;
        } else {
            str2 = "asr";
        }
        a(cVar, aVar.b(), t, str2);
        this.w = aVar;
        if (TextUtils.equals(this.A, "cloudAsr")) {
            if (this.w != null) {
                com.aispeech.lite.c.a aVar2 = (com.aispeech.lite.c.a) aVar;
                if (aVar2.v().equals(SchedulerSupport.CUSTOM) && TextUtils.isEmpty(aVar2.w())) {
                    cVar.onError(new AIError((int) AIError.ERR_SERVICE_PARAMETERS, AIError.ERR_DESCRIPTION_ERR_SERVICE_PARAMETERS));
                    Log.e(t, "error: 设置识别服务器类型为custom须同时设置lmId");
                    return;
                }
            }
            this.u = new d(new C0000b(this, (byte) 0));
        } else {
            this.u = new e("lgram", new C0000b(this, (byte) 0));
        }
        this.u.setProfile(this.s);
        a(h.a.MSG_NEW, (Object) null);
    }

    public final void b(m mVar, o oVar) {
        if (a()) {
            this.v = mVar;
            this.y = oVar;
            a(h.a.MSG_START, (Object) null);
            return;
        }
        i();
    }

    @Override // com.aispeech.lite.h
    public final void g() {
        super.g();
        if (this.u != null) {
            this.u = null;
        }
        if (this.v != null) {
            this.v = null;
        }
        if (this.w != null) {
            this.w = null;
        }
        if (this.x != null) {
            this.x = null;
        }
        if (this.y != null) {
            this.y = null;
        }
        if (this.z != null) {
            this.z = null;
        }
        if (this.B != null) {
            this.B = null;
        }
    }

    /* renamed from: com.aispeech.lite.a.b$1  reason: invalid class name */
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
                a[h.a.MSG_UPDATE.ordinal()] = 12;
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
        com.aispeech.lite.a.c cVar;
        switch (AnonymousClass1.a[aVar.ordinal()]) {
            case 1:
                if (this.e == h.b.STATE_IDLE) {
                    a(this.w);
                    if (!this.w.d() && this.b == null && (com.aispeech.lite.c.j == 0 || com.aispeech.lite.c.j == 4)) {
                        this.b = a((com.aispeech.lite.b.d) this);
                        if (this.b == null) {
                            a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_DEVICE, AIError.ERR_DESCRIPTION_DEVICE));
                            return;
                        }
                    }
                    if (this.z.a()) {
                        a(this.z);
                        this.x = new com.aispeech.lite.vad.a("asr", new c(this, (byte) 0));
                        this.x.newKernel(this.z);
                    }
                    this.u.newKernel(this.w);
                    return;
                }
                e("new");
                return;
            case 2:
                if (this.e == h.b.STATE_NEWED) {
                    a(this.v, this.y);
                    if (this.e == h.b.STATE_NEWED) {
                        if (this.w.d()) {
                            Log.i(t, "isUseCustomFeed");
                            this.u.startKernel(this.v);
                            if (this.z.a()) {
                                a(this.v);
                                this.x.startKernel(this.y);
                            }
                            if (this.v.w() != null) {
                                OneshotCache<byte[]> w = this.v.w();
                                if (w.isValid()) {
                                    OneshotCache<byte[]>.OneshotIterator it = w.iterator();
                                    while (it.hasNext()) {
                                        byte[] next = it.next();
                                        if (next != null) {
                                            a(next, next.length);
                                        }
                                    }
                                }
                            }
                            com.aispeech.lite.a.c cVar2 = this.B;
                            if (cVar2 != null) {
                                cVar2.onReadyForSpeech();
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
                    this.u.startKernel(this.v);
                    if (this.z.a()) {
                        a(this.v);
                        this.x.startKernel(this.y);
                    }
                    if (this.v.w() != null) {
                        OneshotCache<byte[]> w2 = this.v.w();
                        if (w2.isValid()) {
                            OneshotCache<byte[]>.OneshotIterator it2 = w2.iterator();
                            while (it2.hasNext()) {
                                byte[] next2 = it2.next();
                                if (next2 != null) {
                                    a(next2, next2.length);
                                }
                            }
                        }
                    }
                    a(h.b.STATE_RUNNING);
                    return;
                }
                e("recorder start");
                return;
            case 4:
                if (this.e == h.b.STATE_RUNNING) {
                    p();
                    if (!this.z.a()) {
                        o();
                    }
                    c(this);
                    this.u.stopKernel();
                    if (this.z.a()) {
                        this.x.stopKernel();
                    }
                    a(h.b.STATE_WAITING);
                    return;
                }
                e("stop");
                return;
            case 5:
                if (this.e == h.b.STATE_RUNNING || this.e == h.b.STATE_WAITING) {
                    if (!this.w.d()) {
                        c(this);
                    }
                    this.u.cancelKernel();
                    j jVar = this.z;
                    if (jVar != null && jVar.a()) {
                        this.x.stopKernel();
                    }
                    a(h.b.STATE_NEWED);
                    return;
                }
                e("cancel");
                return;
            case 6:
                byte[] bArr = (byte[]) message.obj;
                if (this.e == h.b.STATE_RUNNING && (cVar = this.B) != null) {
                    cVar.onRawDataReceived(bArr, bArr.length);
                    return;
                }
                return;
            case 7:
                byte[] bArr2 = (byte[]) message.obj;
                if (this.e == h.b.STATE_RUNNING || this.e == h.b.STATE_WAITING) {
                    if (this.z.a()) {
                        this.x.feed(bArr2);
                    } else {
                        this.u.feed(bArr2);
                    }
                    com.aispeech.lite.a.c cVar3 = this.B;
                    if (cVar3 != null) {
                        cVar3.onResultDataReceived(bArr2, bArr2.length, 0);
                        return;
                    }
                    return;
                }
                return;
            case 8:
                byte[] bArr3 = (byte[]) message.obj;
                if (this.e == h.b.STATE_RUNNING) {
                    this.u.feed(bArr3);
                    return;
                }
                return;
            case 9:
                if (this.e == h.b.STATE_RUNNING) {
                    Log.d(t, "VAD.BEGIN");
                    this.C = System.currentTimeMillis();
                    j();
                    b(this.v);
                    com.aispeech.lite.a.c cVar4 = this.B;
                    if (cVar4 != null) {
                        cVar4.b();
                        return;
                    }
                    return;
                }
                e("VAD.BEGIN");
                return;
            case 10:
                if (this.e == h.b.STATE_RUNNING) {
                    Log.d(t, "VAD.END");
                    this.D = System.currentTimeMillis();
                    p();
                    o();
                    c(this);
                    this.u.stopKernel();
                    if (this.z.a()) {
                        this.x.stopKernel();
                    }
                    a(h.b.STATE_WAITING);
                    com.aispeech.lite.a.c cVar5 = this.B;
                    if (cVar5 != null) {
                        cVar5.c();
                        return;
                    }
                    return;
                }
                e("VAD.END");
                return;
            case 11:
                float floatValue = ((Float) message.obj).floatValue();
                if (this.e == h.b.STATE_RUNNING) {
                    com.aispeech.lite.a.c cVar6 = this.B;
                    if (cVar6 != null) {
                        cVar6.a(floatValue);
                        return;
                    }
                    return;
                }
                e("volume changed");
                return;
            case 12:
                if (this.e == h.b.STATE_NEWED) {
                    String str = (String) message.obj;
                    g gVar = this.u;
                    if (gVar != null) {
                        gVar.update(str);
                        return;
                    }
                    return;
                }
                e(AIConstant.VP_UPDATE);
                return;
            case 13:
                p();
                AIResult aIResult = (AIResult) message.obj;
                if (this.e == h.b.STATE_RUNNING || this.e == h.b.STATE_WAITING) {
                    this.E = System.currentTimeMillis();
                    String str2 = t;
                    Log.d(str2, "VAD.BEGIN.ASR.RESULT.DELAY : " + (this.E - this.C));
                    String str3 = t;
                    Log.d(str3, "VAD.END.ASR.RESULT.DELAY : " + (this.E - this.D));
                    com.aispeech.lite.a.c cVar7 = this.B;
                    if (cVar7 != null) {
                        cVar7.a(aIResult);
                    }
                    if (aIResult.isLast()) {
                        a(h.b.STATE_NEWED);
                        c(this);
                        return;
                    }
                    return;
                }
                e("result");
                return;
            case 14:
                if (this.e != h.b.STATE_IDLE) {
                    p();
                    if (this.e == h.b.STATE_RUNNING) {
                        c(this);
                        g gVar2 = this.u;
                        if (gVar2 != null) {
                            gVar2.stopKernel();
                        }
                        if (this.z.a()) {
                            this.x.stopKernel();
                        }
                    }
                    h();
                    j();
                    this.u.releaseKernel();
                    this.u = null;
                    com.aispeech.lite.vad.a aVar2 = this.x;
                    if (aVar2 != null) {
                        aVar2.releaseKernel();
                        this.x = null;
                    }
                    g();
                    a(h.b.STATE_IDLE);
                    return;
                }
                e("release");
                return;
            case RemoteControlConfig.REMOTE_ACCOUNT_FACE_BIND /* 15 */:
                p();
                AIError aIError = (AIError) message.obj;
                if (TextUtils.isEmpty(aIError.getRecordId())) {
                    aIError.setRecordId(Utils.get_recordid());
                }
                if (aIError.getErrId() == 70920) {
                    Log.w(t, aIError.toString());
                    com.aispeech.lite.a.c cVar8 = this.B;
                    if (cVar8 != null) {
                        cVar8.onError(aIError);
                    }
                    b(aIError);
                    return;
                } else if (this.e == h.b.STATE_IDLE) {
                    com.aispeech.lite.a.c cVar9 = this.B;
                    if (cVar9 != null) {
                        cVar9.onError(aIError);
                    }
                    b(aIError);
                    return;
                } else if (this.e != h.b.STATE_NEWED && this.e != h.b.STATE_IDLE) {
                    c(this);
                    if (aIError.getErrId() == 70961) {
                        this.u.cancelKernel();
                    } else {
                        this.u.stopKernel();
                    }
                    if (this.z.a()) {
                        this.x.stopKernel();
                    }
                    a(h.b.STATE_NEWED);
                    Log.w(t, aIError.toString());
                    b(aIError);
                    if (aIError.getErrId() == 70912) {
                        aIError.setErrId(AIError.ERR_NETWORK);
                        aIError.setError(AIError.ERR_DESCRIPTION_ERR_NETWORK);
                    }
                    com.aispeech.lite.a.c cVar10 = this.B;
                    if (cVar10 != null) {
                        cVar10.onError(aIError);
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

    private void b(AIError aIError) {
        String str;
        if ((aIError.getErrId() == 70911 || aIError.getErrId() == 70912) && !NetworkUtil.isNetworkConnected(com.aispeech.lite.c.b())) {
            Log.d(t, "network is not connected, ignore upload error");
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
        if (this.A.equals("cloudAsr")) {
            hashMap.put("module", "cloud_exception");
            AnalysisProxy.getInstance().getAnalysisMonitor().cacheData("cloud_asr_exception", "info", "cloud_exception", str, aIError.getInputJSON(), aIError.getOutputJSON(), hashMap);
            return;
        }
        hashMap.put("module", "local_exception");
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.w != null) {
                jSONObject.put("config", ((com.aispeech.lite.c.e) this.w).g());
            }
            if (this.v != null) {
                jSONObject.put("param", ((com.aispeech.lite.h.i) this.v).a());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AnalysisProxy.getInstance().getAnalysisMonitor().cacheData("local_asr_exception", "info", "local_exception", str, jSONObject, aIError.getOutputJSON(), hashMap);
    }

    @Override // com.aispeech.lite.h
    public final void m() {
        a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_NO_SPEECH, AIError.ERR_DESCRIPTION_NO_SPEECH));
        Log.w(t, "no speech timeout!");
    }

    @Override // com.aispeech.lite.h
    public final void n() {
        a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_MAX_SPEECH, AIError.ERR_DESCRIPTION_MAX_SPEECH));
    }

    /* renamed from: com.aispeech.lite.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0000b implements com.aispeech.lite.a.a {
        private C0000b() {
        }

        /* synthetic */ C0000b(b bVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.a.a
        public final void a(int i) {
            String str = b.t;
            Log.i(str, "MyAsrKernelListener onInit : " + i);
            b.this.a(i);
        }

        @Override // com.aispeech.lite.a.a
        public final void a(AIError aIError) {
            b.this.a(h.a.MSG_ERROR, aIError);
        }

        @Override // com.aispeech.lite.a.a
        public final void a(AIResult aIResult) {
            b.this.a(h.a.MSG_RESULT, aIResult);
        }

        @Override // com.aispeech.lite.a.a
        public final void b(int i) {
            if (b.this.B != null) {
                b.this.B.a(i);
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements VadKernelListener {
        private c() {
        }

        /* synthetic */ c(b bVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onInit(int i) {
            String str = b.t;
            Log.i(str, "MyVadKernelListener onInit : " + i);
            b.this.a(i);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadStart() {
            b.this.a(h.a.MSG_VAD_START, (Object) null);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadEnd() {
            b.this.a(h.a.MSG_VAD_END, (Object) null);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onRmsChanged(float f) {
            b.this.a(h.a.MSG_VOLUME_CHANGED, Float.valueOf(f));
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onBufferReceived(byte[] bArr) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            b.this.a(h.a.MSG_VAD_RECEIVE_DATA, bArr2);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onError(AIError aIError) {
            b.this.a(h.a.MSG_ERROR, aIError);
        }
    }

    private void o() {
        a aVar = this.F;
        if (aVar != null) {
            aVar.cancel();
            this.F = null;
        }
        int p = this.v.p();
        if (p <= 0 || !"cloudAsr".equals(this.A)) {
            return;
        }
        com.aispeech.lite.a aVar2 = this.w;
        if ((aVar2 instanceof com.aispeech.lite.c.a) && ((com.aispeech.lite.c.a) aVar2).n()) {
            return;
        }
        this.F = new a(this, (byte) 0);
        try {
            AITimer.getInstance().schedule(this.F, p);
            String str = t;
            Log.d(str, "startAsrTimeoutTimer and timeout is : " + p + "ms");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private void p() {
        a aVar = this.F;
        if (aVar != null) {
            aVar.cancel();
            this.F = null;
            Log.d(t, "cancelAsrTimeoutTimer");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends TimerTask {
        private a() {
        }

        /* synthetic */ a(b bVar, byte b) {
            this();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            if (b.this.u != null && (b.this.u instanceof d)) {
                b.this.a(h.a.MSG_ERROR, new AIError(AIError.ERR_TIMEOUT_ASR, AIError.ERR_DESCRIPTION_TIMEOUT_ASR, ((d) b.this.u).c()));
                Log.w(b.t, "AsrTimeoutTimer ERR_TIMEOUT_ASR");
                return;
            }
            Log.w(b.t, "AsrTimeoutTimer do nothing");
        }
    }
}
