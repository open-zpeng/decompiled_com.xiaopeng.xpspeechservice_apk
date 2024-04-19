package com.aispeech.lite.i;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.common.FileUtil;
import com.aispeech.common.Log;
import com.aispeech.common.Transformer;
import com.aispeech.export.ASRMode;
import com.aispeech.export.IAsrPolicy;
import com.aispeech.export.Vocab;
import com.aispeech.lite.c.j;
import com.aispeech.lite.h;
import com.aispeech.lite.h.i;
import com.aispeech.lite.h.k;
import com.aispeech.lite.h.o;
import com.aispeech.lite.vad.VadKernelListener;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class e extends h {
    private com.aispeech.lite.c.h A;
    private k B;
    private JSONObject C;
    private String D;
    private FileUtil E;
    private com.aispeech.lite.a.c F;
    private long G;
    private long H;
    private long I;
    private long J;
    private long K;
    private IAsrPolicy L;
    private com.aispeech.lite.a.e t;
    private i u;
    private com.aispeech.lite.c.e v;
    private com.aispeech.lite.vad.a w;
    private o x;
    private j y;
    private com.aispeech.lite.i.d z;

    public final void a(com.aispeech.lite.a.c cVar, com.aispeech.lite.c.e eVar, j jVar, com.aispeech.lite.c.h hVar) {
        this.F = cVar;
        if (!hVar.h()) {
            this.y = jVar;
            this.d++;
            if (jVar.a()) {
                this.d++;
            }
            this.v = eVar;
            this.t = new com.aispeech.lite.a.e("local-sem", new a(this, (byte) 0));
            this.E = new FileUtil(com.aispeech.lite.c.b());
        }
        this.A = hVar;
        this.z = new com.aispeech.lite.i.d(new c(this, (byte) 0));
        a(cVar, hVar.b(), "SemanticProcessor", "semantic");
        a(h.a.MSG_NEW, (Object) null);
    }

    public final void a(i iVar, o oVar, k kVar) {
        if (a()) {
            this.u = iVar;
            this.x = oVar;
            this.B = kVar;
            a(h.a.MSG_START, (Object) null);
            return;
        }
        i();
    }

    @Override // com.aispeech.lite.h
    public final void g() {
        super.g();
        if (this.t != null) {
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
        if (this.A != null) {
            this.A = null;
        }
        if (this.B != null) {
            this.B = null;
        }
        if (this.C != null) {
            this.C = null;
        }
    }

    /* renamed from: com.aispeech.lite.i.e$1  reason: invalid class name */
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
                a[h.a.MSG_RESULT.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[h.a.MSG_UPDATE.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[h.a.MSG_UPDATE_VOCAB.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[h.a.MSG_ASR_RESULT.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[h.a.MSG_RELEASE.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[h.a.MSG_ERROR.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:146:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:393:? A[RETURN, SYNTHETIC] */
    @Override // com.aispeech.lite.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final void a(com.aispeech.lite.h.a r14, android.os.Message r15) {
        /*
            Method dump skipped, instructions count: 1772
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.lite.i.e.a(com.aispeech.lite.h$a, android.os.Message):void");
    }

    @Override // com.aispeech.lite.h
    public final void m() {
        a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_NO_SPEECH, AIError.ERR_DESCRIPTION_NO_SPEECH));
        Log.w("SemanticProcessor", "no speech timeout!");
    }

    @Override // com.aispeech.lite.h
    public final void n() {
        a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_MAX_SPEECH, AIError.ERR_DESCRIPTION_MAX_SPEECH));
    }

    public final void a(Vocab vocab) {
        if (a()) {
            a(h.a.MSG_UPDATE_VOCAB, vocab);
        } else {
            i();
        }
    }

    public final List<String> f(String str) {
        ArrayList arrayList = new ArrayList();
        if (a()) {
            com.aispeech.lite.i.d dVar = this.z;
            if (dVar != null) {
                return dVar.b(str);
            }
            return arrayList;
        }
        i();
        return arrayList;
    }

    private static JSONObject a(JSONObject jSONObject, String str) {
        try {
            if (jSONObject.has(str)) {
                jSONObject.put(str, Transformer.transGrammmer(jSONObject.optJSONObject(str)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject b(JSONObject jSONObject, String str) {
        JSONObject transNgram;
        try {
            if (jSONObject.has(str)) {
                JSONObject optJSONObject = jSONObject.optJSONObject(str);
                if (optJSONObject.has("post")) {
                    transNgram = Transformer.transGrammmer(optJSONObject);
                } else {
                    transNgram = Transformer.transNgram(optJSONObject, this.B.d());
                }
                jSONObject.put(str, transNgram);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject c(JSONObject jSONObject, String str) {
        Log.d("SemanticProcessor", "select() called with:  name = " + str + ",result = " + jSONObject);
        try {
            jSONObject.put("select", jSONObject.optJSONObject(str));
            this.C = jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final void a(IAsrPolicy iAsrPolicy) {
        this.L = iAsrPolicy;
    }

    /* loaded from: classes.dex */
    class b implements IAsrPolicy {
        private b() {
        }

        /* synthetic */ b(e eVar, byte b) {
            this();
        }

        private boolean a(JSONObject jSONObject) {
            double g;
            int optInt = jSONObject.optInt("forceout", 0);
            double optDouble = jSONObject.optDouble("conf");
            String optString = jSONObject.optString("rec");
            if (optInt != 1) {
                i iVar = e.this.u;
                if (!iVar.j().isEmpty() && iVar.j().containsKey(optString)) {
                    g = iVar.j().get(optString).doubleValue();
                } else {
                    g = iVar.g();
                }
                if (optDouble < g) {
                    Log.d("SemanticProcessor", "DROP CONF NOT QUALIFIED HOT WORD: " + jSONObject.toString());
                    return false;
                } else if (!TextUtils.isEmpty(optString)) {
                    return true;
                } else {
                    Log.d("SemanticProcessor", "DROP REC NOT QUALIFIED HOT WORD " + jSONObject.toString());
                    return false;
                }
            }
            Log.w("SemanticProcessor", "DROP FORCE OUT HOT WORD: " + jSONObject.toString());
            return false;
        }

        @Override // com.aispeech.export.IAsrPolicy
        public final AIResult onAsr(AIResult aIResult) {
            String str = "grammar";
            try {
                JSONObject jSONObject = new JSONObject(aIResult.getResultObject().toString());
                JSONObject optJSONObject = jSONObject.optJSONObject("ngram");
                JSONObject optJSONObject2 = jSONObject.optJSONObject("grammar");
                JSONObject optJSONObject3 = jSONObject.optJSONObject("dynamic");
                if (e.this.u.a == ASRMode.MODE_HOTWORD.getValue()) {
                    if (!jSONObject.has("dynamic") || !a(optJSONObject3)) {
                        e.this.a(h.a.MSG_ERROR, new AIError((int) AIError.ERR_DEFAULT, AIError.ERR_DESCRIPTION_DEFAULT));
                    } else {
                        jSONObject = e.this.c(jSONObject, "dynamic");
                    }
                } else {
                    if (!jSONObject.has("grammar")) {
                        str = "ngram";
                    } else {
                        int optInt = optJSONObject2.optInt("forceout", 0);
                        double optDouble = optJSONObject2.optDouble("conf");
                        if (optInt != 0 || optDouble < e.this.A.k()) {
                            str = "ngram";
                        }
                    }
                    if (e.this.u.a == ASRMode.MODE_ASR_X.getValue() && jSONObject.has("dynamic") && a(optJSONObject3)) {
                        if (!"ngram".equals(str)) {
                            optJSONObject = optJSONObject2;
                        }
                        if (Math.abs(optJSONObject3.optDouble("conf")) - Math.abs(optJSONObject.optDouble("conf")) <= 0.15000000596046448d) {
                            str = "dynamic";
                        }
                    }
                    jSONObject = e.this.c(jSONObject, str);
                }
                aIResult.setResultObject(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return aIResult;
        }
    }

    /* loaded from: classes.dex */
    class a implements com.aispeech.lite.a.a {
        private a() {
        }

        /* synthetic */ a(e eVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.a.a
        public final void a(int i) {
            Log.i("SemanticProcessor", "MyAsrKernelListener onInit : " + i);
            e.this.a(i);
        }

        @Override // com.aispeech.lite.a.a
        public final void a(AIError aIError) {
            Log.d("SemanticProcessor", "asr error :" + aIError.toString());
            e.this.a(h.a.MSG_ERROR, aIError);
        }

        @Override // com.aispeech.lite.a.a
        public final void a(AIResult aIResult) {
            e.this.a(h.a.MSG_ASR_RESULT, aIResult);
        }

        @Override // com.aispeech.lite.a.a
        public final void b(int i) {
            if (e.this.F != null) {
                e.this.F.a(i);
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements com.aispeech.lite.a.a {
        private c() {
        }

        /* synthetic */ c(e eVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.a.a
        public final void a(int i) {
            Log.i("SemanticProcessor", "MySemanticKernelListener onInit : " + i);
            e.this.a(i);
        }

        @Override // com.aispeech.lite.a.a
        public final void a(AIResult aIResult) {
            e.this.a(h.a.MSG_RESULT, aIResult);
        }

        @Override // com.aispeech.lite.a.a
        public final void a(AIError aIError) {
            e.this.a(h.a.MSG_ERROR, aIError);
        }

        @Override // com.aispeech.lite.a.a
        public final void b(int i) {
            Log.d("SemanticProcessor", "ret = " + i);
            if (e.this.F != null) {
                e.this.F.a(i);
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements VadKernelListener {
        private d() {
        }

        /* synthetic */ d(e eVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onInit(int i) {
            Log.i("SemanticProcessor", "MyVadKernelListener onInit : " + i);
            e.this.a(i);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadStart() {
            e.this.J = System.currentTimeMillis();
            e.this.a(h.a.MSG_VAD_START, (Object) null);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadEnd() {
            e.this.K = System.currentTimeMillis();
            e.this.a(h.a.MSG_VAD_END, (Object) null);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onRmsChanged(float f) {
            e.this.a(h.a.MSG_VOLUME_CHANGED, Float.valueOf(f));
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onBufferReceived(byte[] bArr) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            e.this.a(h.a.MSG_VAD_RECEIVE_DATA, bArr2);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onError(AIError aIError) {
            e.this.a(h.a.MSG_ERROR, aIError);
        }
    }
}
