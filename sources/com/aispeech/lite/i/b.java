package com.aispeech.lite.i;

import android.os.Message;
import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.common.Log;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.c.j;
import com.aispeech.lite.g;
import com.aispeech.lite.h;
import com.aispeech.lite.h.m;
import com.aispeech.lite.h.o;
import com.aispeech.lite.i;
import com.aispeech.lite.vad.VadKernelListener;
import com.xiaopeng.lib.utils.config.RemoteControlConfig;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class b extends h {
    private long A;
    private long B;
    private long C;
    private long D;
    private long E;
    private g t;
    private m u;
    private com.aispeech.lite.c.c v;
    private g w;
    private o x;
    private j y;
    private c z;

    public final void a(c cVar, com.aispeech.lite.a aVar, j jVar) {
        this.z = cVar;
        this.v = (com.aispeech.lite.c.c) aVar;
        this.y = jVar;
        if (!this.v.m() && jVar.a()) {
            this.d++;
        }
        a(cVar, aVar.b(), "CloudSemanticProcessor", i.a);
        if (this.t == null) {
            this.t = new com.aispeech.lite.i.a(new a(this, (byte) 0));
            this.t.setProfile(this.s);
        }
        a(h.a.MSG_NEW, (Object) null);
    }

    public final void b(m mVar, o oVar) {
        if (a()) {
            this.u = mVar;
            this.x = oVar;
            a(h.a.MSG_START, (Object) null);
            return;
        }
        i();
    }

    @Override // com.aispeech.lite.h
    public final void g() {
        super.g();
        g gVar = this.t;
        if (gVar != null) {
            gVar.releaseKernel();
            this.t = null;
        }
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
    }

    /* renamed from: com.aispeech.lite.i.b$1  reason: invalid class name */
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
                a[h.a.MSG_UPDATE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[h.a.MSG_UPDATE_VOCAB.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[h.a.MSG_ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[h.a.MSG_RAW_RECEIVE_DATA.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[h.a.MSG_RESULT_RECEIVE_DATA.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[h.a.MSG_VAD_RECEIVE_DATA.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[h.a.MSG_VAD_START.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[h.a.MSG_VAD_END.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[h.a.MSG_VOLUME_CHANGED.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[h.a.MSG_RESULT.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[h.a.MSG_RELEASE.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
        }
    }

    @Override // com.aispeech.lite.h
    protected final void a(h.a aVar, Message message) {
        c cVar;
        switch (AnonymousClass1.a[aVar.ordinal()]) {
            case 1:
                if (this.e == h.b.STATE_IDLE) {
                    if (!this.v.m()) {
                        if (!this.v.d() && this.b == null && (com.aispeech.lite.c.j == 0 || com.aispeech.lite.c.j == 4)) {
                            this.b = a((com.aispeech.lite.b.d) this);
                            if (this.b == null) {
                                a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_DEVICE, AIError.ERR_DESCRIPTION_DEVICE));
                                return;
                            }
                        }
                        if (this.y.a()) {
                            a(this.y);
                            this.w = new com.aispeech.lite.vad.a("csem", new C0006b(this, (byte) 0));
                            this.w.newKernel(this.y);
                        }
                    }
                    this.t.newKernel(this.v);
                    return;
                }
                e("new");
                return;
            case 2:
                if (this.e == h.b.STATE_NEWED) {
                    this.C = System.currentTimeMillis();
                    a(this.u, this.x);
                    if (!this.v.m()) {
                        if (this.v.d()) {
                            Log.i("CloudSemanticProcessor", "isUseCustomFeed");
                            if (this.y.a()) {
                                this.w.startKernel(this.x);
                                a(this.u);
                            }
                            this.t.startKernel(this.u);
                            a(h.b.STATE_RUNNING);
                            return;
                        }
                        b((com.aispeech.lite.b.d) this);
                        return;
                    }
                    this.u.d("nlu.input.text");
                    this.t.startKernel(this.u);
                    a(h.b.STATE_RUNNING);
                    return;
                }
                e("start");
                return;
            case 3:
                if (this.e == h.b.STATE_NEWED || this.e == h.b.STATE_WAITING) {
                    this.t.startKernel(this.u);
                    if (this.y.a()) {
                        a(this.u);
                        this.w.startKernel(this.x);
                    }
                    a(h.b.STATE_RUNNING);
                    return;
                }
                e("recorder start");
                return;
            case 4:
                if (this.e == h.b.STATE_RUNNING) {
                    c(this);
                    this.t.stopKernel();
                    if (this.y.a()) {
                        this.w.stopKernel();
                    }
                    a(h.b.STATE_WAITING);
                    return;
                }
                e("stop");
                return;
            case 5:
                if (this.e == h.b.STATE_RUNNING || this.e == h.b.STATE_WAITING || this.e == h.b.STATE_NEWED) {
                    c(this);
                    this.t.cancelKernel();
                    j jVar = this.y;
                    if (jVar != null && jVar.a()) {
                        this.w.stopKernel();
                    }
                    a(h.b.STATE_NEWED);
                    return;
                }
                e("cancel");
                return;
            case 6:
                if (this.e != h.b.STATE_IDLE) {
                    String str = (String) message.obj;
                    if (!TextUtils.isEmpty(str)) {
                        this.t.update(str);
                        return;
                    } else {
                        Log.e("CloudSemanticProcessor", "illegal param!");
                        return;
                    }
                }
                e("update info");
                return;
            case 7:
                if (this.e != h.b.STATE_IDLE) {
                    String str2 = (String) message.obj;
                    if (!TextUtils.isEmpty(str2)) {
                        this.t.updateVocab(str2);
                        return;
                    } else {
                        Log.e("CloudSemanticProcessor", "illegal param!");
                        return;
                    }
                }
                e("update vocab info");
                return;
            case 8:
                AIError aIError = (AIError) message.obj;
                if (TextUtils.isEmpty(aIError.getRecordId())) {
                    aIError.setRecordId(Utils.get_recordid());
                }
                if (aIError.getErrId() == 70920) {
                    Log.w("CloudSemanticProcessor", aIError.toString());
                    c cVar2 = this.z;
                    if (cVar2 != null) {
                        cVar2.onError(aIError);
                        return;
                    }
                    return;
                } else if (this.e == h.b.STATE_IDLE) {
                    c cVar3 = this.z;
                    if (cVar3 != null) {
                        cVar3.onError(aIError);
                        return;
                    }
                    return;
                } else if (this.e != h.b.STATE_NEWED && this.e != h.b.STATE_IDLE) {
                    c(this);
                    this.t.stopKernel();
                    if (this.y.a()) {
                        this.w.stopKernel();
                    }
                    a(h.b.STATE_NEWED);
                    Log.w("CloudSemanticProcessor", aIError.toString());
                    if (aIError.getErrId() == 70912) {
                        aIError.setErrId(AIError.ERR_NETWORK);
                        aIError.setError(AIError.ERR_DESCRIPTION_ERR_NETWORK);
                    }
                    c cVar4 = this.z;
                    if (cVar4 != null) {
                        cVar4.onError((AIError) message.obj);
                        return;
                    }
                    return;
                } else {
                    e(AIError.KEY_TEXT);
                    return;
                }
            case 9:
                byte[] bArr = (byte[]) message.obj;
                if (this.e == h.b.STATE_RUNNING && (cVar = this.z) != null) {
                    cVar.onRawDataReceived(bArr, bArr.length);
                    return;
                }
                return;
            case 10:
                byte[] bArr2 = (byte[]) message.obj;
                if (this.e == h.b.STATE_RUNNING) {
                    if (this.y.a()) {
                        this.w.feed(bArr2);
                    } else {
                        this.t.feed(bArr2);
                    }
                    c cVar5 = this.z;
                    if (cVar5 != null) {
                        cVar5.onResultDataReceived(bArr2, bArr2.length, 0);
                        return;
                    }
                    return;
                }
                e("feed");
                return;
            case 11:
                byte[] bArr3 = (byte[]) message.obj;
                if (this.e == h.b.STATE_RUNNING) {
                    this.t.feed(bArr3);
                    return;
                }
                return;
            case 12:
                if (this.e == h.b.STATE_RUNNING) {
                    Log.d("CloudSemanticProcessor", "VAD.BEGIN");
                    j();
                    b(this.u);
                    c cVar6 = this.z;
                    if (cVar6 != null) {
                        cVar6.b();
                        return;
                    }
                    return;
                }
                e("VAD.BEGIN");
                return;
            case 13:
                if (this.e == h.b.STATE_RUNNING) {
                    Log.d("CloudSemanticProcessor", "VAD.END");
                    c(this);
                    this.t.stopKernel();
                    if (this.y.a()) {
                        this.w.stopKernel();
                    }
                    a(h.b.STATE_WAITING);
                    c cVar7 = this.z;
                    if (cVar7 != null) {
                        cVar7.c();
                        return;
                    }
                    return;
                }
                e("VAD.END");
                return;
            case 14:
                float floatValue = ((Float) message.obj).floatValue();
                if (this.e == h.b.STATE_RUNNING) {
                    c cVar8 = this.z;
                    if (cVar8 != null) {
                        cVar8.a(floatValue);
                        return;
                    }
                    return;
                }
                e("volume changed");
                return;
            case RemoteControlConfig.REMOTE_ACCOUNT_FACE_BIND /* 15 */:
                AIResult aIResult = (AIResult) message.obj;
                if (this.e == h.b.STATE_RUNNING || this.e == h.b.STATE_WAITING) {
                    c cVar9 = this.z;
                    if (cVar9 != null) {
                        cVar9.a(aIResult);
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(aIResult.getResultObject().toString());
                        if (!jSONObject.has("dm") && !jSONObject.has("nlu")) {
                            if (aIResult.isLast()) {
                                this.E = System.currentTimeMillis();
                                return;
                            }
                            return;
                        }
                        this.D = System.currentTimeMillis();
                        Log.d("CloudSemanticProcessor", "ASR.START.SEMANTIC.RESULT.DELAY : " + (this.D - this.C));
                        Log.d("CloudSemanticProcessor", "FINAL.ASR.RESULT.SEMANTIC.RESULT.DELAY : " + (this.D - this.E));
                        Log.d("CloudSemanticProcessor", "VAD.BEGIN.SEMANTIC.RESULT.DELAY : " + (this.D - this.A));
                        Log.d("CloudSemanticProcessor", "VAD.END.SEMANTIC.RESULT.DELAY : " + (this.D - this.B));
                        a(h.b.STATE_NEWED);
                        c(this);
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                e("result");
                return;
            case 16:
                if (this.e != h.b.STATE_IDLE) {
                    if (this.e == h.b.STATE_RUNNING) {
                        c(this);
                    }
                    h();
                    j();
                    this.t.releaseKernel();
                    this.t = null;
                    g gVar = this.w;
                    if (gVar != null) {
                        gVar.releaseKernel();
                        this.w = null;
                    }
                    g();
                    a(h.b.STATE_IDLE);
                    return;
                }
                e("release");
                return;
            default:
                return;
        }
    }

    @Override // com.aispeech.lite.h
    public final void m() {
        a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_NO_SPEECH, AIError.ERR_DESCRIPTION_NO_SPEECH));
        Log.w("CloudSemanticProcessor", "no speech timeout!");
    }

    @Override // com.aispeech.lite.h
    public final void n() {
        a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_MAX_SPEECH, AIError.ERR_DESCRIPTION_MAX_SPEECH));
    }

    /* renamed from: com.aispeech.lite.i.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0006b implements VadKernelListener {
        private C0006b() {
        }

        /* synthetic */ C0006b(b bVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onInit(int i) {
            Log.i("CloudSemanticProcessor", "MyVadKernelListener onInit : " + i);
            b.this.a(i);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadStart() {
            b.this.A = System.currentTimeMillis();
            b.this.a(h.a.MSG_VAD_START, (Object) null);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadEnd() {
            b.this.B = System.currentTimeMillis();
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

    /* loaded from: classes.dex */
    class a implements com.aispeech.lite.a.a {
        private a() {
        }

        /* synthetic */ a(b bVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.a.a
        public final void a(int i) {
            Log.i("CloudSemanticProcessor", "DDSCloudAsrListener onInit : " + i);
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
        }
    }
}
