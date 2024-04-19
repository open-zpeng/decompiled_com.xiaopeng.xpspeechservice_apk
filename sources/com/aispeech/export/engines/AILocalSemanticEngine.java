package com.aispeech.export.engines;

import android.text.TextUtils;
import com.aispeech.AIResult;
import com.aispeech.common.Log;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import com.aispeech.export.ASRMode;
import com.aispeech.export.Vocab;
import com.aispeech.export.config.AILocalSemanticConfig;
import com.aispeech.export.intent.AILocalASRIntent;
import com.aispeech.export.intent.AILocalSemanticIntent;
import com.aispeech.export.listeners.AIASRListener;
import com.aispeech.export.listeners.AIUpdateListener;
import com.aispeech.kernel.Asr;
import com.aispeech.kernel.Semantic;
import com.aispeech.kernel.Utils;
import com.aispeech.kernel.Vad;
import com.aispeech.lite.Languages;
import com.aispeech.lite.SemanticType;
import com.aispeech.lite.c;
import com.aispeech.lite.c.h;
import com.aispeech.lite.c.j;
import com.aispeech.lite.f;
import com.aispeech.lite.h.i;
import com.aispeech.lite.h.k;
import com.aispeech.lite.h.o;
import com.aispeech.lite.i.e;
import com.aispeech.lite.oneshot.OneshotCache;
import com.aispeech.lite.speech.EngineListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class AILocalSemanticEngine {
    public static final String TAG = "AILocalSemanticEngine";
    private Languages j = Languages.CHINESE;
    private String k = "";
    private String l = "";
    private String m = "";
    private String n = "";
    private String o = "";
    private String p = "";
    private String q = "";
    private String r = "";
    private String s = "";
    private String t = "";
    private e a = new e();
    private j b = new j();
    private com.aispeech.lite.c.e c = new com.aispeech.lite.c.e();
    private h d = new h();
    private k e = new k();
    private o f = new o();
    private i g = new i();
    private com.aispeech.lite.h.j h = new com.aispeech.lite.h.j();
    private a i = new a((byte) 0);

    private AILocalSemanticEngine() {
    }

    public static AILocalSemanticEngine createInstance() {
        return new AILocalSemanticEngine();
    }

    public static boolean checkLibValid() {
        return Asr.isAsrSoValid() && Vad.isVadSoValid() && Utils.isUtilsSoValid() && Semantic.isSemanticSoValid();
    }

    public void setAsrResBin(String str) {
        this.o = str;
    }

    public void setAsrResBinPath(String str) {
        this.p = str;
    }

    public void setAsrNetBin(String str) {
        this.m = str;
    }

    public void setAsrNetBinPath(String str) {
        this.n = str;
    }

    public void setSemResFolder(String str) {
        this.q = str;
    }

    public void setSemResFolderPath(String str) {
        this.r = str;
    }

    public void setSemLuaFolder(String str) {
        this.s = str;
    }

    public void setSemLuaFolderPath(String str) {
        this.t = str;
    }

    public void notifyWakeup() {
        System.currentTimeMillis();
    }

    public void updateNetBinPath(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("illegal net.bin path");
        }
        this.c.c(str);
        e eVar = this.a;
        if (eVar != null) {
            eVar.b(this.c.g().toString());
        }
    }

    public void updateNetBinPath(AIUpdateListener aIUpdateListener, String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "illegal net.bin path");
            aIUpdateListener.failed();
            return;
        }
        a aVar = this.i;
        if (aVar == null) {
            throw new IllegalStateException(" init engine first ");
        }
        aVar.a(aIUpdateListener);
        this.c.c(str);
        e eVar = this.a;
        if (eVar != null) {
            eVar.b(this.c.g().toString());
        }
    }

    public void setDomain(String str) {
        this.e.k(str);
    }

    public void setSkillID(String str) {
        this.e.l(str);
    }

    public void setTask(String str) {
        this.e.b(str);
    }

    public void setVadEnable(boolean z) {
        this.c.a(z);
        this.b.a(z);
    }

    public void setVadRes(String str) {
        this.k = str;
    }

    public void setVadResPath(String str) {
        this.l = str;
    }

    public void setPauseTime(int i) {
        this.b.a(i);
        this.f.a(i);
    }

    public void setUseDelimiter(String str) {
        this.g.b(str);
    }

    public void setUseConf(boolean z) {
        this.g.f(z);
    }

    public void setUseXbnfRec(boolean z) {
        this.g.e(z);
    }

    public void setExpandFnPath(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("illegal ExpandFn path .");
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("illegal ExpandFn path .");
        }
        this.g.j(str);
    }

    public void setUsePinyin(boolean z) {
        this.g.g(z);
    }

    public void setUseFormat(boolean z) {
        this.d.e(z);
    }

    public void setSelectRuleThreshold(double d) {
        this.d.a(d);
    }

    public void setUseSelectRule(boolean z) {
        this.d.f(z);
    }

    public void setNoSpeechTimeOut(int i) {
        this.g.d(i);
        this.f.d(i);
    }

    public void setMaxSpeechTimeS(int i) {
        this.g.c(i);
        this.f.c(i);
    }

    public void setUseMaxSpeechResult(boolean z) {
        this.g.c(z);
    }

    public void setUseFrameSplit(boolean z) {
        this.g.d(z);
    }

    public void setUseCustomFeed(boolean z) {
        this.c.b(z);
        this.b.b(z);
        this.d.b(z);
    }

    public void setUseRefText(boolean z) {
        this.d.d(z);
        this.b.a(!z);
    }

    public void setLanguages(Languages languages) {
        this.j = languages;
    }

    public void setOneshotCache(OneshotCache<byte[]> oneshotCache) {
        if (oneshotCache != null && oneshotCache.isValid()) {
            this.g.a(oneshotCache);
        }
    }

    public void setSaveAudioPath(String str) {
        this.f.u(str);
        this.g.u(str);
    }

    public void init(AIASRListener aIASRListener) {
        init(new AILocalSemanticConfig.Builder().setNetBin(!TextUtils.isEmpty(this.m) ? this.m : this.n).setNgramBin(!TextUtils.isEmpty(this.o) ? this.o : this.p).setVadRes(!TextUtils.isEmpty(this.k) ? this.k : this.l).setSemNaviLuaResFolder(!TextUtils.isEmpty(this.s) ? this.s : this.t).setSemNaviResFolder(!TextUtils.isEmpty(this.q) ? this.q : this.r).setUseVad(this.b.a()).setUseCustomFeed(this.b.d()).setLanguages(this.j).setUseRefText(this.d.h()).setUseFormat(this.d.i()).build(), aIASRListener);
    }

    public void init(AILocalSemanticConfig aILocalSemanticConfig, AIASRListener aIASRListener) {
        e eVar;
        this.d.d(aILocalSemanticConfig.useRefText);
        this.d.e(aILocalSemanticConfig.useFormat);
        this.d.a(aILocalSemanticConfig.semanticType);
        if (!aILocalSemanticConfig.useRefText) {
            this.c.a(aILocalSemanticConfig.languages.getLanguage());
            ArrayList arrayList = new ArrayList();
            this.c.b(aILocalSemanticConfig.useCustomFeed);
            if (!aILocalSemanticConfig.ngramBin.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                arrayList.add(aILocalSemanticConfig.ngramBin);
                this.c.b(Util.getResourceDir(c.b()) + File.separator + aILocalSemanticConfig.ngramBin);
            } else {
                this.c.b(aILocalSemanticConfig.ngramBin);
            }
            if (!aILocalSemanticConfig.netBin.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                arrayList.add(aILocalSemanticConfig.netBin);
                String str = Util.getResourceDir(c.b()) + File.separator + aILocalSemanticConfig.netBin;
                this.c.c(str);
                this.h.b(str);
            } else {
                this.c.c(aILocalSemanticConfig.netBin);
                this.h.b(aILocalSemanticConfig.netBin);
            }
            this.c.a((String[]) arrayList.toArray(new String[arrayList.size()]));
            this.b.a(aILocalSemanticConfig.useVad);
            if (aILocalSemanticConfig.useVad) {
                ArrayList arrayList2 = new ArrayList();
                if (aILocalSemanticConfig.vadRes.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                    this.b.a(aILocalSemanticConfig.vadRes);
                } else {
                    arrayList2.add(aILocalSemanticConfig.vadRes);
                    this.b.a(Util.getResourceDir(this.c.b()) + File.separator + aILocalSemanticConfig.vadRes);
                }
                this.b.a((String[]) arrayList2.toArray(new String[arrayList2.size()]));
            }
            if (aILocalSemanticConfig.asrPolicy != null && (eVar = this.a) != null) {
                eVar.a(aILocalSemanticConfig.asrPolicy);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        if ((aILocalSemanticConfig.semanticType.getType() & SemanticType.NAVI.getType()) == SemanticType.NAVI.getType()) {
            if (!aILocalSemanticConfig.semNaviResFolder.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.d.a(Util.getResourceDir(c.b()) + File.separator + aILocalSemanticConfig.semNaviResFolder);
                this.d.c(Util.getResourceDir(c.b()) + File.separator + aILocalSemanticConfig.semNaviResFolder);
                if (!arrayList3.contains(aILocalSemanticConfig.semNaviResFolder)) {
                    arrayList3.add(aILocalSemanticConfig.semNaviResFolder);
                }
            } else {
                this.d.a(aILocalSemanticConfig.semNaviResFolder);
                this.d.c(aILocalSemanticConfig.semNaviResFolder);
            }
            if (!aILocalSemanticConfig.semNaviLuaResFolder.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.d.b(Util.getResourceDir(c.b()) + File.separator + aILocalSemanticConfig.semNaviLuaResFolder);
                this.d.d(Util.getResourceDir(c.b()) + File.separator + aILocalSemanticConfig.semNaviLuaResFolder);
                if (!arrayList3.contains(aILocalSemanticConfig.semNaviLuaResFolder)) {
                    arrayList3.add(aILocalSemanticConfig.semNaviLuaResFolder);
                }
            } else {
                this.d.b(aILocalSemanticConfig.semNaviLuaResFolder);
                this.d.d(aILocalSemanticConfig.semNaviLuaResFolder);
            }
        }
        if ((aILocalSemanticConfig.semanticType.getType() & SemanticType.DUI.getType()) == SemanticType.DUI.getType()) {
            if (!aILocalSemanticConfig.semDUIResFloder.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.d.e(Util.getResourceDir(c.b()) + File.separator + aILocalSemanticConfig.semDUIResFloder);
                if (!arrayList3.contains(aILocalSemanticConfig.semDUIResFloder)) {
                    arrayList3.add(aILocalSemanticConfig.semDUIResFloder);
                }
            } else {
                this.d.e(aILocalSemanticConfig.semDUIResFloder);
            }
            if (!aILocalSemanticConfig.semDUILuaResFloder.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.d.f(Util.getResourceDir(c.b()) + File.separator + aILocalSemanticConfig.semDUILuaResFloder);
                if (!arrayList3.contains(aILocalSemanticConfig.semDUILuaResFloder)) {
                    arrayList3.add(aILocalSemanticConfig.semDUILuaResFloder);
                }
            } else {
                this.d.f(aILocalSemanticConfig.semDUILuaResFloder);
            }
            if (!aILocalSemanticConfig.semDUIResCustomFloder.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.d.g(Util.getResourceDir(c.b()) + File.separator + aILocalSemanticConfig.semDUIResCustomFloder);
                if (!arrayList3.contains(aILocalSemanticConfig.semDUIResCustomFloder)) {
                    arrayList3.add(aILocalSemanticConfig.semDUIResCustomFloder);
                }
            } else {
                this.d.g(aILocalSemanticConfig.semDUIResCustomFloder);
            }
        }
        this.d.a((String[]) arrayList3.toArray(new String[arrayList3.size()]));
        this.i.a(aIASRListener);
        e eVar2 = this.a;
        if (eVar2 != null) {
            eVar2.a(this.i, this.c, this.b, this.d);
        }
    }

    public void updateVocabs(AIUpdateListener aIUpdateListener, Vocab... vocabArr) {
        if (TextUtils.isEmpty(this.d.m())) {
            throw new IllegalArgumentException("请先设置DUI Res路径");
        }
        if (vocabArr == null) {
            throw new IllegalArgumentException("illegal vocabs!");
        }
        a aVar = this.i;
        if (aVar == null) {
            throw new IllegalStateException(" init engine first ");
        }
        aVar.a(aIUpdateListener);
        if (this.a != null) {
            for (Vocab vocab : vocabArr) {
                this.a.a(vocab);
            }
        }
    }

    public List<String> getVocab(String str) {
        if (TextUtils.isEmpty(this.d.m())) {
            throw new IllegalArgumentException("请先设置DUI Res路径");
        }
        return this.a.f(str);
    }

    public void start() {
        start(new AILocalSemanticIntent());
    }

    public void start(AILocalSemanticIntent aILocalSemanticIntent) {
        AILocalASRIntent asrIntent = aILocalSemanticIntent.getAsrIntent();
        if (asrIntent != null) {
            this.g.j(asrIntent.getExpandFnPath());
            this.g.c(asrIntent.getMaxSpeechTimeOut());
            this.g.d(asrIntent.getNoSpeechTimeOut());
            this.g.u(asrIntent.getSaveAudioPath());
            this.g.d(asrIntent.isUseFrameSplit());
            this.g.a = asrIntent.getMode().getValue();
            this.g.f(true);
            this.g.g(this.j == Languages.CHINESE);
            if (ASRMode.MODE_ASR != asrIntent.getMode() && TextUtils.isEmpty(asrIntent.getWords())) {
                asrIntent.setWords(new String[]{"FILLER"});
                Log.e(TAG, "please register dynamic list !");
            }
            this.g.h(asrIntent.getWords());
            this.g.k(asrIntent.getBlackWords());
            this.g.a(asrIntent.getCustomThreshold());
            this.g.a = asrIntent.getMode().getValue();
            this.g.b(asrIntent.getThreshold());
        }
        this.e.j(aILocalSemanticIntent.getRefText());
        this.e.h(aILocalSemanticIntent.getPinyin());
        this.e.k(aILocalSemanticIntent.getDomain());
        this.e.b(aILocalSemanticIntent.getTask());
        this.e.l(aILocalSemanticIntent.getSkillID());
        e eVar = this.a;
        if (eVar != null) {
            eVar.a(this.g, this.f, this.e);
        }
    }

    public void startWithRecording() {
        start(new AILocalSemanticIntent());
    }

    public void startWithText(String str) {
        this.e.j(str);
        e eVar = this.a;
        if (eVar != null) {
            eVar.a(this.g, this.f, this.e);
        }
    }

    public void startWithText(String str, String str2) {
        this.e.j(str);
        this.e.h(str2);
        e eVar = this.a;
        if (eVar != null) {
            eVar.a(this.g, this.f, this.e);
        }
    }

    @Deprecated
    public void feedData(byte[] bArr) {
        e eVar = this.a;
        if (eVar != null) {
            eVar.a(bArr, bArr.length);
        }
    }

    public void feedData(byte[] bArr, int i) {
        e eVar = this.a;
        if (eVar != null) {
            eVar.a(bArr, i);
        }
    }

    public void stopRecording() {
        e eVar = this.a;
        if (eVar != null) {
            eVar.c();
        }
    }

    public void cancel() {
        e eVar = this.a;
        if (eVar != null) {
            eVar.d();
        }
        a aVar = this.i;
        if (aVar != null) {
            aVar.b(f.a.MSG_CANCEL, null);
        }
    }

    public void destroy() {
        e eVar = this.a;
        if (eVar != null) {
            eVar.e();
        }
        a aVar = this.i;
        if (aVar != null) {
            aVar.a();
            this.i = null;
        }
        if (this.d != null) {
            this.d = null;
        }
        if (this.e != null) {
            this.e = null;
        }
        if (this.b != null) {
            this.b = null;
        }
        if (this.f != null) {
            this.f = null;
        }
        if (this.c != null) {
            this.c = null;
        }
        if (this.g != null) {
            this.g = null;
        }
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.s = null;
        this.t = null;
        this.q = null;
        this.r = null;
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
                int intValue = ((Integer) obj).intValue();
                Log.d(AILocalSemanticEngine.TAG, "ret = " + intValue);
                if (intValue == 0) {
                    this.b.success();
                } else {
                    this.b.failed();
                }
            }
        }

        @Override // com.aispeech.lite.a.c
        public final void a(AIResult aIResult) {
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

    /* renamed from: com.aispeech.export.engines.AILocalSemanticEngine$1  reason: invalid class name */
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
