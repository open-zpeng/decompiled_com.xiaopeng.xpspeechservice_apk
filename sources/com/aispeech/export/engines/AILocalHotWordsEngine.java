package com.aispeech.export.engines;

import com.aispeech.AIResult;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import com.aispeech.export.config.AILocalHotWordConfig;
import com.aispeech.export.intent.AILocalHotWordIntent;
import com.aispeech.export.listeners.AILocalHotWordsListener;
import com.aispeech.kernel.Asr;
import com.aispeech.lite.c.e;
import com.aispeech.lite.c.l;
import com.aispeech.lite.e.b;
import com.aispeech.lite.f;
import com.aispeech.lite.h.i;
import com.aispeech.lite.h.o;
import com.aispeech.lite.speech.EngineListener;
import java.io.File;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class AILocalHotWordsEngine {
    private l a = new l();
    private e b = new e();
    private o c = new o();
    private i d = new i();
    private b e = new b();
    private a f = new a((byte) 0);

    private AILocalHotWordsEngine() {
    }

    public static AILocalHotWordsEngine createInstance() {
        return new AILocalHotWordsEngine();
    }

    public static boolean checkLibValid() {
        return Asr.isAsrSoValid();
    }

    public void feedData(byte[] bArr) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.a(bArr, bArr.length);
        }
    }

    public void init(AILocalHotWordConfig aILocalHotWordConfig, AILocalHotWordsListener aILocalHotWordsListener) {
        this.c.a(0);
        this.b.a(aILocalHotWordConfig.languages.getLanguage());
        this.a.a(aILocalHotWordConfig.useVad);
        this.a.d(aILocalHotWordConfig.useSSL);
        this.b.b(aILocalHotWordConfig.useCustomFeed);
        if (aILocalHotWordConfig.useVad) {
            ArrayList arrayList = new ArrayList();
            if (aILocalHotWordConfig.vadRes.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.a.a(aILocalHotWordConfig.vadRes);
            } else {
                arrayList.add(aILocalHotWordConfig.vadRes);
                l lVar = this.a;
                lVar.a(Util.getResourceDir(this.b.b()) + File.separator + aILocalHotWordConfig.vadRes);
            }
            this.a.a((String[]) arrayList.toArray(new String[arrayList.size()]));
        }
        ArrayList arrayList2 = new ArrayList();
        if (aILocalHotWordConfig.asrRes.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
            this.b.b(aILocalHotWordConfig.asrRes);
        } else {
            arrayList2.add(aILocalHotWordConfig.asrRes);
            e eVar = this.b;
            eVar.b(Util.getResourceDir(this.b.b()) + File.separator + aILocalHotWordConfig.asrRes);
        }
        this.b.a((String[]) arrayList2.toArray(new String[arrayList2.size()]));
        this.f.a(aILocalHotWordsListener);
        this.e.a(this.f, this.b, this.a);
    }

    public void start(AILocalHotWordIntent aILocalHotWordIntent) {
        this.d.b(aILocalHotWordIntent.isUseContinuousRecognition());
        this.d.c(aILocalHotWordIntent.getMaxSpeechTime());
        this.c.u(aILocalHotWordIntent.getSaveAudioPath());
        this.d.u(aILocalHotWordIntent.getSaveAudioPath());
        this.d.a(aILocalHotWordIntent.getCustomThreshold());
        this.d.h(aILocalHotWordIntent.getWords());
        this.d.k(aILocalHotWordIntent.getBlackWords());
        this.d.b(aILocalHotWordIntent.getThreshold());
        this.d.d(aILocalHotWordIntent.getNoSpeechTime());
        b bVar = this.e;
        if (bVar != null) {
            bVar.a(this.d, this.c);
        }
    }

    public void cancel() {
        b bVar = this.e;
        if (bVar != null) {
            bVar.d();
        }
        a aVar = this.f;
        if (aVar != null) {
            aVar.b(f.a.MSG_CANCEL, null);
        }
    }

    @Deprecated
    public void stop() {
        if (this.a.a()) {
            throw new IllegalArgumentException("not allowed method when vad enable");
        }
        b bVar = this.e;
        if (bVar != null) {
            bVar.c();
        }
    }

    public void destroy() {
        b bVar = this.e;
        if (bVar != null) {
            bVar.e();
        }
        a aVar = this.f;
        if (aVar != null) {
            aVar.a();
            this.f = null;
        }
    }

    /* loaded from: classes.dex */
    static class a extends f implements com.aispeech.lite.e.a {
        private AILocalHotWordsListener a;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // com.aispeech.lite.f
        public final void a() {
            super.a();
            if (this.a != null) {
                this.a = null;
            }
        }

        final void a(AILocalHotWordsListener aILocalHotWordsListener) {
            super.a((EngineListener) aILocalHotWordsListener);
            this.a = aILocalHotWordsListener;
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
                this.a.onDoa(((Integer) obj).intValue());
            }
        }

        @Override // com.aispeech.lite.e.a
        public final void a(AIResult aIResult) {
            b(f.a.MSG_RESULTS, aIResult);
        }

        @Override // com.aispeech.lite.e.a
        public final void a(float f) {
            b(f.a.MSG_RMS_CHANGED, Float.valueOf(f));
        }

        @Override // com.aispeech.lite.e.a
        public final void b() {
            b(f.a.MSG_BEGINNING_OF_SPEECH, null);
        }

        @Override // com.aispeech.lite.e.a
        public final void c() {
            b(f.a.MSG_END_OF_SPEECH, null);
        }

        @Override // com.aispeech.lite.e.a
        public final void a(int i) {
            b(f.a.MSG_DOA_RESULT, Integer.valueOf(i));
        }
    }

    /* renamed from: com.aispeech.export.engines.AILocalHotWordsEngine$1  reason: invalid class name */
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
                a[f.a.MSG_DOA_RESULT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }
}
