package com.aispeech.export.engines;

import android.text.TextUtils;
import com.aispeech.AIResult;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import com.aispeech.export.config.AICloudASRConfig;
import com.aispeech.export.intent.AIAsrPlusIntent;
import com.aispeech.export.intent.AICloudASRIntent;
import com.aispeech.export.listeners.AIASRListener;
import com.aispeech.kernel.Utils;
import com.aispeech.kernel.Vad;
import com.aispeech.lite.a.b;
import com.aispeech.lite.c;
import com.aispeech.lite.c.j;
import com.aispeech.lite.f;
import com.aispeech.lite.h.d;
import com.aispeech.lite.h.o;
import com.aispeech.lite.oneshot.OneshotCache;
import com.aispeech.lite.speech.EngineListener;
import java.io.File;
import org.json.JSONArray;
/* loaded from: classes.dex */
public class AICloudASREngine {
    public static final String TAG = "AICloudASREngine";
    private String g;
    private String h = "";
    private b a = new b();
    private com.aispeech.lite.c.a b = new com.aispeech.lite.c.a(c.a());
    private com.aispeech.lite.c.b c = new com.aispeech.lite.c.b();
    private d d = new d();
    private j e = new j();
    private o f = new o();
    private a i = new a((byte) 0);

    private AICloudASREngine() {
    }

    public static AICloudASREngine createInstance() {
        return new AICloudASREngine();
    }

    public static boolean checkLibValid() {
        return Vad.isVadSoValid() && Utils.isUtilsSoValid();
    }

    public void init(AIASRListener aIASRListener) {
        init(new AICloudASRConfig.Builder().setServer(this.d.l()).setUserId(this.d.h()).setLmId(this.b.w()).setLocalVadEnable(this.f.e()).setEnablePunctuation(this.b.s()).setEnableNumberConvert(this.b.u()).setSelfCustomWakeupScore(this.b.r()).setCustomWakeupWord(this.b.m()).setWakeupWordVisible(this.b.i()).setEnableTone(this.b.o()).setEnableAlignment(this.b.y()).setEnableEmotion(this.b.t()).setEnableAudioDetection(this.b.z()).setEnableLanguageClassifier(this.b.p()).setEnableSNTime(this.b.q()).setResourceType(this.b.v()).setVadResource(TextUtils.isEmpty(this.h) ? this.g : this.h).setRealBack(this.b.n()).setCloudVadEnable(this.b.a()).setNBest(this.b.x()).setVadPauseTime(this.f.d()).setUseCustomFeed(this.b.d()).build(), aIASRListener);
    }

    public void init(AICloudASRConfig aICloudASRConfig, AIASRListener aIASRListener) {
        this.i.a(aIASRListener);
        if (aICloudASRConfig == null) {
            throw new IllegalArgumentException("AICloudASRConfig can not be null!");
        }
        if (aICloudASRConfig.getServer() != null) {
            this.b.a(aICloudASRConfig.getServer());
            this.d.n(aICloudASRConfig.getServer());
        }
        if (aICloudASRConfig.getUserId() != null) {
            this.b.c(aICloudASRConfig.getUserId());
            this.d.b_(aICloudASRConfig.getUserId());
        }
        if (aICloudASRConfig.getLmid() != null) {
            this.b.g(aICloudASRConfig.getLmid());
        }
        if (aICloudASRConfig.isEnableAsrPlus()) {
            this.c.a(aICloudASRConfig.isEnableAsrPlus());
            AIAsrPlusIntent asrPlusIntent = aICloudASRConfig.getAsrPlusIntent();
            if (asrPlusIntent != null) {
                this.c.a(asrPlusIntent.getServerName());
                this.c.a(asrPlusIntent.getUsers());
                this.c.b(asrPlusIntent.getOrganization());
            }
        }
        this.b.a(this.c);
        this.e.a(aICloudASRConfig.isLocalVadEnable());
        this.f.a(aICloudASRConfig.isLocalVadEnable());
        this.b.j(aICloudASRConfig.isEnablePunctuation());
        this.b.k(aICloudASRConfig.isEnableNumberConvert());
        this.b.a(aICloudASRConfig.getSelfCustomWakeupScore());
        if (aICloudASRConfig.getCustomWakeupWord() != null) {
            this.b.a(aICloudASRConfig.getCustomWakeupWord());
        }
        this.b.d(aICloudASRConfig.isWakeupWordVisible());
        this.b.f(aICloudASRConfig.isEnableTone());
        this.b.l(aICloudASRConfig.isEnableAlignment());
        this.b.m(aICloudASRConfig.isEnableEmotion());
        this.b.n(aICloudASRConfig.isEnableAudioDetection());
        this.b.g(aICloudASRConfig.isEnableLanguageClassifier());
        this.b.h(aICloudASRConfig.isEnableSNTime());
        if (aICloudASRConfig.getResourceType() != null) {
            this.b.f(aICloudASRConfig.getResourceType());
        }
        String vadResource = aICloudASRConfig.getVadResource();
        if (vadResource != null) {
            if (vadResource.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.e.a(vadResource);
            } else {
                this.e.a(new String[]{vadResource});
                j jVar = this.e;
                jVar.a(Util.getResourceDir(c.b()) + File.separator + vadResource);
            }
        }
        this.d.b(aICloudASRConfig.getRealBack());
        this.d.a(aICloudASRConfig.isCloudVadEnable());
        this.b.e(aICloudASRConfig.getRealBack());
        this.b.i(aICloudASRConfig.isCloudVadEnable());
        this.b.b(aICloudASRConfig.getnBest());
        this.e.a(aICloudASRConfig.getVadPauseTime());
        this.f.a(aICloudASRConfig.getVadPauseTime());
        this.b.b(aICloudASRConfig.isUseCustomFeed());
        this.a.a(this.i, this.b, this.e, "cloudAsr");
    }

    public void start() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.b(this.d, this.f);
        }
    }

    public void start(AICloudASRIntent aICloudASRIntent) {
        if (aICloudASRIntent == null) {
            throw new IllegalArgumentException("AICloudASRIntent can not be null!");
        }
        if (aICloudASRIntent.getDeviceId() != null) {
            this.d.s(aICloudASRIntent.getDeviceId());
        }
        this.d.b(aICloudASRIntent.getWaitingTimeout());
        this.d.d(aICloudASRIntent.getNoSpeechTimeOut());
        this.d.c(aICloudASRIntent.getMaxSpeechTimeS());
        if (aICloudASRIntent.getOneshotCache() != null) {
            this.d.a(aICloudASRIntent.getOneshotCache());
        }
        if (this.f.v() != null) {
            this.f.u(aICloudASRIntent.getSaveAudioPath());
            this.d.u(aICloudASRIntent.getSaveAudioPath());
        }
        if (aICloudASRIntent.getProductId() != null) {
            this.d.g(aICloudASRIntent.getProductId());
        }
        b bVar = this.a;
        if (bVar != null) {
            bVar.b(this.d, this.f);
        }
    }

    public void stopRecording() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.c();
        }
    }

    @Deprecated
    public void feedData(byte[] bArr) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(bArr, bArr.length);
        }
    }

    public void feedData(byte[] bArr, int i) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(bArr, i);
        }
    }

    public void cancel() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.d();
        }
    }

    public void destroy() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.e();
        }
        a aVar = this.i;
        if (aVar != null) {
            aVar.a();
            this.i = null;
        }
        if (this.f != null) {
            this.f = null;
        }
        if (this.e != null) {
            this.e = null;
        }
        if (this.b != null) {
            this.b = null;
        }
        if (this.d != null) {
            this.d = null;
        }
    }

    public void setWaitingTimeout(int i) {
        this.d.b(i);
    }

    public void notifyWakeup() {
        System.currentTimeMillis();
    }

    public void setServer(String str) {
        this.d.n(str);
        this.b.a(str);
    }

    public void setUserId(String str) {
        this.d.b_(str);
        this.b.c(str);
    }

    public void setLmId(String str) {
        this.b.g(str);
    }

    @Deprecated
    public void setDeviceId(String str) {
        this.d.s(str);
    }

    public void setLocalVadEnable(boolean z) {
        this.e.a(z);
        this.f.a(z);
    }

    public void setEnablePunctuation(boolean z) {
        this.b.j(z);
    }

    public void setEnableNumberConvert(boolean z) {
        this.b.k(z);
    }

    public void setSelfCustomWakeupScore(int i) {
        this.b.a(i);
    }

    public void setCustomWakeupWord(JSONArray jSONArray) {
        this.b.a(jSONArray);
    }

    public void setWakeupWordVisible(boolean z) {
        this.b.d(z);
    }

    public void setEnableTone(boolean z) {
        this.b.f(z);
    }

    public void setEnableAlignment(boolean z) {
        this.b.l(z);
    }

    public void setEnableEmotion(boolean z) {
        this.b.m(z);
    }

    public void setEnableAudioDetection(boolean z) {
        this.b.n(z);
    }

    public void setEnableLanguageClassifier(boolean z) {
        this.b.g(z);
    }

    public void setEnableSNTime(boolean z) {
        this.b.h(z);
    }

    public void setResourceType(String str) {
        this.b.f(str);
    }

    public void setNoSpeechTimeOut(int i) {
        this.d.d(i);
    }

    public void setMaxSpeechTimeS(int i) {
        this.d.c(i);
    }

    public void setVadResBinPath(String str) {
        this.g = str;
    }

    public void setVadResource(String str) {
        this.h = str;
    }

    public void setOneshotCache(OneshotCache<byte[]> oneshotCache) {
        if (oneshotCache != null && oneshotCache.isValid()) {
            this.d.a(oneshotCache);
        }
    }

    public void setRealback(boolean z) {
        this.d.b(z);
        this.b.e(z);
    }

    public void setCloudVadEnable(boolean z) {
        this.d.a(z);
        this.b.i(z);
    }

    public void setNbest(int i) {
        this.b.b(i);
    }

    public void setSaveAudioPath(String str) {
        this.f.u(str);
        this.d.u(str);
    }

    public void setPauseTime(int i) {
        this.e.a(i);
        this.f.a(i);
    }

    public void setUseCustomFeed(boolean z) {
        this.b.b(z);
    }

    @Deprecated
    public void setProductId(String str) {
        this.d.g(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends f implements com.aispeech.lite.a.c {
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
            this.a = aIASRListener;
            super.a((EngineListener) aIASRListener);
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
        }
    }

    /* renamed from: com.aispeech.export.engines.AICloudASREngine$1  reason: invalid class name */
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
