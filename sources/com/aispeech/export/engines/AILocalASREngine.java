package com.aispeech.export.engines;

import android.text.TextUtils;
import com.aispeech.AIResult;
import com.aispeech.common.Log;
import com.aispeech.common.Transformer;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import com.aispeech.export.config.AILocalASRConfig;
import com.aispeech.export.intent.AILocalASRIntent;
import com.aispeech.export.listeners.AIASRListener;
import com.aispeech.export.listeners.AIUpdateListener;
import com.aispeech.kernel.Asr;
import com.aispeech.kernel.Utils;
import com.aispeech.kernel.Vad;
import com.aispeech.lite.Languages;
import com.aispeech.lite.a.b;
import com.aispeech.lite.c;
import com.aispeech.lite.c.e;
import com.aispeech.lite.c.j;
import com.aispeech.lite.f;
import com.aispeech.lite.h.i;
import com.aispeech.lite.h.o;
import com.aispeech.lite.oneshot.OneshotCache;
import com.aispeech.lite.speech.EngineListener;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class AILocalASREngine {
    public static final String TAG = "AILocalASREngine";
    private static boolean n = false;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private Languages f = Languages.CHINESE;
    private b a = new b();
    private j b = new j();
    private e c = new e();
    private o d = new o();
    private i e = new i();
    private a m = new a((byte) 0);

    static /* synthetic */ JSONObject a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("nlu")) {
            jSONObject.put("nlu", Transformer.transGrammmer(jSONObject.optJSONObject("nlu")));
        }
        return jSONObject;
    }

    private AILocalASREngine() {
    }

    public static AILocalASREngine createInstance() {
        return new AILocalASREngine();
    }

    public static boolean checkLibValid() {
        return Asr.isAsrSoValid() && Vad.isVadSoValid() && Utils.isUtilsSoValid();
    }

    public void notifyWakeup() {
        System.currentTimeMillis();
    }

    public void setNetBin(String str) {
        this.i = str;
    }

    public void setResBin(String str) {
        this.k = str;
    }

    public void setNetBinPath(String str) {
        this.j = str;
    }

    public void setResBinPath(String str) {
        this.l = str;
    }

    public void setVadEnable(boolean z) {
        this.c.a(z);
        this.b.a(z);
        this.d.a(z);
    }

    public void setVadRes(String str) {
        this.g = str;
    }

    public void setVadResPath(String str) {
        this.h = str;
    }

    public void setPauseTime(int i) {
        this.b.a(i);
        this.d.a(i);
    }

    public void setUseFrameSplit(boolean z) {
        this.e.d(z);
    }

    public void setUseDelimiter(String str) {
        this.e.b(str);
    }

    public void setUseConf(boolean z) {
        this.e.f(z);
    }

    public void setExpandFnPath(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("illegal ExpandFn path .");
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("illegal ExpandFn path .");
        }
        this.e.j(str);
    }

    public void setUseXbnfRec(boolean z) {
        this.e.e(z);
    }

    public void setUsePinyin(boolean z) {
        this.e.g(z);
    }

    public void setLanguages(Languages languages) {
        this.f = languages;
    }

    public void setUseFiller(boolean z) {
        this.e.a(z);
    }

    public void setFillerPenaltyScore(double d) {
        this.e.a(d);
    }

    public void setUseFormat(boolean z) {
        n = z;
    }

    public void updateNetBinPath(String str, AIUpdateListener aIUpdateListener) {
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "illegal net.bin path");
            aIUpdateListener.failed();
            return;
        }
        a aVar = this.m;
        if (aVar == null) {
            throw new IllegalStateException(" init engine first ");
        }
        aVar.a(aIUpdateListener);
        this.c.c(str);
        b bVar = this.a;
        if (bVar != null) {
            bVar.b(this.c.g().toString());
        }
    }

    public void init(AIASRListener aIASRListener) {
        init(new AILocalASRConfig.Builder().setNetBin(!TextUtils.isEmpty(this.i) ? this.i : this.j).setResBin(!TextUtils.isEmpty(this.k) ? this.k : this.l).setVadRes(!TextUtils.isEmpty(this.g) ? this.g : this.h).setVadEnable(this.b.a()).setUseCustomFeed(this.b.d()).setVadPauseTime(this.d.d()).setLanguages(this.f).build(), aIASRListener);
    }

    public void init(AILocalASRConfig aILocalASRConfig, AIASRListener aIASRListener) {
        if (aILocalASRConfig == null) {
            throw new IllegalArgumentException("AILocalASRConfig can not be null!");
        }
        if (aILocalASRConfig.getVadRes().startsWith(URLUtils.URL_PATH_SEPERATOR)) {
            this.b.a(aILocalASRConfig.getVadRes());
        } else {
            j jVar = this.b;
            jVar.a(Util.getResourceDir(c.b()) + File.separator + aILocalASRConfig.getVadRes());
            this.b.a(new String[]{aILocalASRConfig.getVadRes()});
        }
        ArrayList arrayList = new ArrayList();
        if (aILocalASRConfig.getResBin().startsWith(URLUtils.URL_PATH_SEPERATOR)) {
            this.c.b(aILocalASRConfig.getResBin());
        } else {
            e eVar = this.c;
            eVar.b(Util.getResourceDir(c.b()) + File.separator + aILocalASRConfig.getResBin());
            arrayList.add(aILocalASRConfig.getResBin());
        }
        if (aILocalASRConfig.getNetBin().startsWith(URLUtils.URL_PATH_SEPERATOR)) {
            this.c.c(aILocalASRConfig.getNetBin());
        } else {
            e eVar2 = this.c;
            eVar2.c(Util.getResourceDir(c.b()) + File.separator + aILocalASRConfig.getNetBin());
            arrayList.add(aILocalASRConfig.getNetBin());
        }
        if (arrayList.size() != 0) {
            this.c.a((String[]) arrayList.toArray(new String[arrayList.size()]));
        }
        this.c.a(aILocalASRConfig.isVadEnable());
        this.b.a(aILocalASRConfig.isVadEnable());
        this.d.a(aILocalASRConfig.isVadEnable());
        this.b.a(aILocalASRConfig.getVadPauseTime());
        this.d.a(aILocalASRConfig.getVadPauseTime());
        if (aILocalASRConfig.getLanguages() != null) {
            this.c.a(aILocalASRConfig.getLanguages().getLanguage());
        }
        this.c.b(aILocalASRConfig.isUseCustomFeed());
        this.b.b(aILocalASRConfig.isUseCustomFeed());
        this.m.a(aIASRListener);
        this.a.a(this.m, this.c, this.b, "localAsr");
    }

    public void setOneshotCache(OneshotCache<byte[]> oneshotCache) {
        if (oneshotCache != null && oneshotCache.isValid()) {
            this.e.a(oneshotCache);
        }
    }

    public void setNoSpeechTimeOut(int i) {
        this.e.d(i);
        this.d.d(i);
    }

    public void setMaxSpeechTimeS(int i) {
        this.e.c(i);
        this.d.c(i);
    }

    public void setUseCustomFeed(boolean z) {
        this.c.b(z);
        this.b.b(z);
    }

    public void setSaveAudioPath(String str) {
        this.d.u(str);
        this.e.u(str);
    }

    public void start() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.b(this.e, this.d);
        }
    }

    public void start(AILocalASRIntent aILocalASRIntent) {
        if (aILocalASRIntent == null) {
            throw new IllegalArgumentException("AILocalASRIntent can not be null!");
        }
        this.e.d(aILocalASRIntent.isUseFrameSplit());
        this.e.b(aILocalASRIntent.getUseDelimiter());
        this.e.f(aILocalASRIntent.isUseConf());
        if (aILocalASRIntent.getExpandFnPath() != null) {
            File file = new File(aILocalASRIntent.getExpandFnPath());
            if (!file.exists() || file.isDirectory()) {
                throw new IllegalArgumentException("illegal ExpandFn path .");
            }
            this.e.j(aILocalASRIntent.getExpandFnPath());
        }
        this.e.e(aILocalASRIntent.isUseXbnfRec());
        this.e.g(aILocalASRIntent.isUsePinyin());
        this.e.a(aILocalASRIntent.isUseFiller());
        if (this.e.d() > 0.0d) {
            this.e.a(aILocalASRIntent.getFillerPenaltyScore());
        }
        n = aILocalASRIntent.isUseFormat();
        if (aILocalASRIntent.getOneshotCache() != null && aILocalASRIntent.getOneshotCache().isValid()) {
            this.e.a(aILocalASRIntent.getOneshotCache());
        }
        this.e.d(aILocalASRIntent.getNoSpeechTimeOut());
        this.d.d(aILocalASRIntent.getNoSpeechTimeOut());
        this.e.c(aILocalASRIntent.getMaxSpeechTimeOut());
        this.d.c(aILocalASRIntent.getMaxSpeechTimeOut());
        if (aILocalASRIntent.getSaveAudioPath() != null) {
            this.d.u(aILocalASRIntent.getSaveAudioPath());
            this.e.u(aILocalASRIntent.getSaveAudioPath());
        }
        if (aILocalASRIntent.getUseDelimiter() != null) {
            this.e.b(aILocalASRIntent.getUseDelimiter());
        }
        b bVar = this.a;
        if (bVar != null) {
            bVar.b(this.e, this.d);
        }
    }

    public void feedData(byte[] bArr) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(bArr, bArr.length);
        }
    }

    public void stopRecording() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.c();
        }
    }

    public void cancel() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.d();
        }
        a aVar = this.m;
        if (aVar != null) {
            aVar.b(f.a.MSG_CANCEL, null);
        }
    }

    public void destroy() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.e();
        }
        a aVar = this.m;
        if (aVar != null) {
            aVar.a();
            this.m = null;
        }
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends f implements com.aispeech.lite.a.c {
        private AIASRListener a;
        private AIUpdateListener b;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final void a(AIUpdateListener aIUpdateListener) {
            this.b = aIUpdateListener;
        }

        @Override // com.aispeech.lite.f
        public final void a() {
            super.a();
            if (this.a != null) {
                this.a = null;
            }
        }

        final void a(AIASRListener aIASRListener) {
            super.a((EngineListener) aIASRListener);
            this.a = aIASRListener;
        }

        @Override // com.aispeech.lite.f
        protected final void a(f.a aVar, Object obj) {
            int i = AnonymousClass1.a[aVar.ordinal()];
            if (i == 1) {
                this.a.onResults((AIResult) obj);
            } else if (i == 2) {
                this.a.onBeginningOfSpeech();
            } else if (i == 3) {
                this.a.onEndOfSpeech();
            } else if (i == 4) {
                this.a.onRmsChanged(((Float) obj).floatValue());
            } else if (i == 5) {
                if (((Integer) obj).intValue() == 0) {
                    this.b.success();
                } else {
                    this.b.failed();
                }
            }
        }

        @Override // com.aispeech.lite.a.c
        public final void a(AIResult aIResult) {
            if (AILocalASREngine.n) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("nlu", new JSONObject(aIResult.toString()));
                    aIResult.setResultObject(AILocalASREngine.a(jSONObject).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            b(f.a.MSG_RESULTS, aIResult);
        }

        @Override // com.aispeech.lite.a.c
        public final void a(float f) {
            b(f.a.MSG_RMS_CHANGED, Float.valueOf(f));
        }

        @Override // com.aispeech.lite.a.c
        public final void b() {
            b(f.a.MSG_BEGINNING_OF_SPEECH, null);
        }

        @Override // com.aispeech.lite.a.c
        public final void c() {
            b(f.a.MSG_END_OF_SPEECH, null);
        }

        @Override // com.aispeech.lite.a.c
        public final void a(int i) {
            b(f.a.MSG_UPDATE_RESULT, Integer.valueOf(i));
        }
    }

    /* renamed from: com.aispeech.export.engines.AILocalASREngine$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[f.a.values().length];

        static {
            try {
                a[f.a.MSG_RESULTS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[f.a.MSG_BEGINNING_OF_SPEECH.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[f.a.MSG_END_OF_SPEECH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[f.a.MSG_RMS_CHANGED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[f.a.MSG_UPDATE_RESULT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }
}
