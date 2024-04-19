package com.aispeech.export.engines;

import com.aispeech.AIResult;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import com.aispeech.export.ProductContext;
import com.aispeech.export.Vocab;
import com.aispeech.export.config.AICloudSemanticConfig;
import com.aispeech.export.intent.AICloudSemanticIntent;
import com.aispeech.export.listeners.AIASRListener;
import com.aispeech.export.listeners.AIUpdateListener;
import com.aispeech.lite.c.c;
import com.aispeech.lite.c.j;
import com.aispeech.lite.dm.update.UpdaterImpl;
import com.aispeech.lite.f;
import com.aispeech.lite.h.e;
import com.aispeech.lite.h.o;
import com.aispeech.lite.i.b;
import com.aispeech.lite.speech.EngineListener;
import java.io.File;
import org.json.JSONArray;
/* loaded from: classes.dex */
public class AICloudSemanticEngine {
    private b a = new b();
    private c b = new c();
    private e c = new e();
    private j d = new j();
    private o e = new o();
    private com.aispeech.lite.h.c f = new com.aispeech.lite.h.c();
    private a g = new a((byte) 0);
    private UpdaterImpl h;

    private AICloudSemanticEngine() {
    }

    public static AICloudSemanticEngine createInstance() {
        return new AICloudSemanticEngine();
    }

    public void init(AICloudSemanticConfig aICloudSemanticConfig, AIASRListener aIASRListener) {
        this.g.a(aIASRListener);
        this.b.b(aICloudSemanticConfig.isUseCustomFeed());
        this.d.a(aICloudSemanticConfig.isUseVad());
        if (!aICloudSemanticConfig.getVadRes().startsWith(URLUtils.URL_PATH_SEPERATOR)) {
            this.d.a(new String[]{aICloudSemanticConfig.getVadRes()});
            j jVar = this.d;
            jVar.a(Util.getResourceDir(com.aispeech.lite.c.b()) + File.separator + aICloudSemanticConfig.getVadRes());
        } else {
            this.d.a(aICloudSemanticConfig.getVadRes());
        }
        this.b.a(aICloudSemanticConfig.getAliasKey());
        this.b.b(aICloudSemanticConfig.getServerAddress());
        this.b.d(aICloudSemanticConfig.isUseRefText());
        this.h = new UpdaterImpl.Builder().setV1Host(c.b).setWebSocketHost(c.a).setAliasKey(this.b.j()).build();
        this.a.a(this.g, this.b, this.d);
    }

    public void start(AICloudSemanticIntent aICloudSemanticIntent) {
        if (this.a == null) {
            return;
        }
        this.c.d(aICloudSemanticIntent.isEnableNBest());
        this.c.b(aICloudSemanticIntent.isEnablePunctuation());
        this.c.a(aICloudSemanticIntent.isUseRealBack());
        this.c.c(aICloudSemanticIntent.getMaxSpeechTimeS());
        this.c.d(aICloudSemanticIntent.getNoSpeechTimeOut());
        this.c.f(aICloudSemanticIntent.getSessionId());
        this.c.a_(aICloudSemanticIntent.getWakeupWords());
        this.c.u(aICloudSemanticIntent.getSaveAudioPath());
        this.e.u(aICloudSemanticIntent.getSaveAudioPath());
        this.e.a(aICloudSemanticIntent.getPauseTime());
        this.c.a(aICloudSemanticIntent.getType());
        this.c.b(aICloudSemanticIntent.getRefText());
        this.c.h(aICloudSemanticIntent.getSkillId());
        this.c.i(aICloudSemanticIntent.getTask());
        this.c.c(aICloudSemanticIntent.isEnableNumberConvert());
        this.a.b(this.c, this.e);
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
    }

    public void feedData(byte[] bArr) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(bArr, bArr.length);
        }
    }

    public void destroy() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.e();
        }
        a aVar = this.g;
        if (aVar != null) {
            aVar.a();
            this.g = null;
        }
        if (this.e != null) {
            this.e = null;
        }
        if (this.c != null) {
            this.c = null;
        }
        if (this.f != null) {
            this.f = null;
        }
        if (this.b != null) {
            this.b = null;
        }
        if (this.d != null) {
            this.d = null;
        }
        if (this.h != null) {
            this.h = null;
        }
    }

    @Deprecated
    public void updateSkillPriority(JSONArray jSONArray) {
        if (this.a != null) {
            this.a.b(com.aispeech.lite.h.c.a(jSONArray).toString());
        }
    }

    public void updateProductContext(AIUpdateListener aIUpdateListener, ProductContext productContext) {
        UpdaterImpl updaterImpl = this.h;
        if (updaterImpl != null) {
            updaterImpl.updateProductContext(aIUpdateListener, productContext);
        }
    }

    @Deprecated
    public void updateContact(boolean z, JSONArray jSONArray) {
        if (this.a != null) {
            this.a.c(com.aispeech.lite.h.c.a(z, jSONArray).toString());
        }
    }

    public void updateVocabs(AIUpdateListener aIUpdateListener, Vocab... vocabArr) {
        UpdaterImpl updaterImpl = this.h;
        if (updaterImpl != null) {
            updaterImpl.updateVocabs(aIUpdateListener, vocabArr);
        }
    }

    /* loaded from: classes.dex */
    static class a extends f implements com.aispeech.lite.i.c {
        private AIASRListener a;

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
            }
        }

        @Override // com.aispeech.lite.i.c
        public final void a(AIResult aIResult) {
            b(f.a.MSG_RESULTS, aIResult);
        }

        @Override // com.aispeech.lite.i.c
        public final void a(float f) {
            b(f.a.MSG_RMS_CHANGED, Float.valueOf(f));
        }

        @Override // com.aispeech.lite.i.c
        public final void b() {
            b(f.a.MSG_BEGINNING_OF_SPEECH, null);
        }

        @Override // com.aispeech.lite.i.c
        public final void c() {
            b(f.a.MSG_END_OF_SPEECH, null);
        }
    }

    /* renamed from: com.aispeech.export.engines.AICloudSemanticEngine$1  reason: invalid class name */
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
        }
    }
}
