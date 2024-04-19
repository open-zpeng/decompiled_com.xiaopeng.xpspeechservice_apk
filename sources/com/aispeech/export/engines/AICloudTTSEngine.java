package com.aispeech.export.engines;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import com.aispeech.AIError;
import com.aispeech.export.config.AICloudTTSConfig;
import com.aispeech.export.intent.AICloudTTSIntent;
import com.aispeech.export.listeners.AITTSListener;
import com.aispeech.lite.c;
import com.aispeech.lite.c.d;
import com.aispeech.lite.h.f;
import com.aispeech.lite.tts.k;
import com.aispeech.lite.tts.l;
import com.aispeech.lite.tts.n;
/* loaded from: classes.dex */
public class AICloudTTSEngine {
    public static final String TAG = "AICloudTTSEngine";
    private boolean e = true;
    private n a = new n();
    private f c = new f();
    private d b = new d();
    private a d = new a();

    private AICloudTTSEngine() {
        l.b().a(this.e);
    }

    public static AICloudTTSEngine createInstance() {
        return new AICloudTTSEngine();
    }

    public void init(AITTSListener aITTSListener) {
        init(new AICloudTTSConfig.Builder().setUseCache(this.e).setUseStopCallback(this.b.e()).build(), aITTSListener);
    }

    public void init(AICloudTTSConfig aICloudTTSConfig, AITTSListener aITTSListener) {
        if (aICloudTTSConfig == null) {
            throw new IllegalArgumentException("AICloudTTSConfig can not be null!");
        }
        this.e = aICloudTTSConfig.isUseCache();
        l.b().a(aICloudTTSConfig.isUseCache());
        this.b.c(aICloudTTSConfig.isUseStopCallback());
        a aVar = this.d;
        aVar.a = aITTSListener;
        this.a.a(aVar, this.b, "CloudTts");
    }

    public void speak(String str, String str2) {
        this.c.r(str);
        this.c.b(true);
        this.c.d(false);
        this.c.v("cloud");
        this.c.g(c.a().a());
        this.c.h(c.a().d());
        this.c.b_("1001");
        this.c.j(c.a().f());
        this.c.k("DUI-lite-android-sdk-CAR_V1.4.5");
        this.c.w(str2);
        this.a.a(this.c);
    }

    public void speak(String str, String str2, AICloudTTSIntent aICloudTTSIntent) {
        this.c.r(str);
        this.c.b(true);
        this.c.d(false);
        this.c.v("cloud");
        this.c.g(c.a().a());
        this.c.h(c.a().d());
        this.c.b_("1001");
        this.c.j(c.a().f());
        this.c.k("DUI-lite-android-sdk-CAR_V1.4.5");
        this.c.w(str2);
        a(aICloudTTSIntent);
        this.a.a(this.c);
    }

    private void a(AICloudTTSIntent aICloudTTSIntent) {
        if (aICloudTTSIntent == null) {
            throw new IllegalArgumentException("AICloudTTSIntent can not be null!");
        }
        this.c.a(aICloudTTSIntent.isRealBack());
        if (this.c.m() != null) {
            this.c.o(aICloudTTSIntent.getSpeaker());
        }
        if (this.c.j() != null) {
            this.c.c(aICloudTTSIntent.getAudioType());
        }
        if (aICloudTTSIntent.getMp3Quality() != null) {
            this.c.m(aICloudTTSIntent.getMp3Quality());
        }
        if (aICloudTTSIntent.getTextType() != null) {
            this.c.b(aICloudTTSIntent.getTextType());
        }
        f fVar = this.c;
        StringBuilder sb = new StringBuilder();
        sb.append(aICloudTTSIntent.getVolume());
        fVar.q(sb.toString());
        f fVar2 = this.c;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(aICloudTTSIntent.getSpeed());
        fVar2.p(sb2.toString());
        if (aICloudTTSIntent.getSaveAudioPath() != null) {
            this.c.u(aICloudTTSIntent.getSaveAudioPath());
        }
        if (aICloudTTSIntent.getServer() != null) {
            this.c.n(aICloudTTSIntent.getServer());
        }
        this.c.e(aICloudTTSIntent.getStreamType());
        if (aICloudTTSIntent.getAudioAttributes() != null) {
            this.c.a(aICloudTTSIntent.getAudioAttributes());
        }
        this.c.c(aICloudTTSIntent.isUseStreamType());
    }

    public void synthesize(String str, String str2) {
        this.c.r(str);
        this.c.b(false);
        this.c.d(true);
        this.c.v("cloud");
        this.c.g(c.a().a());
        this.c.b_("1001");
        this.c.j(c.a().f());
        this.c.k("DUI-lite-android-sdk-CAR_V1.4.5");
        this.c.w(str2);
        this.a.a(this.c);
    }

    public void synthesize(String str, String str2, AICloudTTSIntent aICloudTTSIntent) {
        this.c.r(str);
        this.c.b(false);
        this.c.d(true);
        this.c.v("cloud");
        this.c.g(c.a().a());
        this.c.b_("1001");
        this.c.j(c.a().f());
        this.c.k("DUI-lite-android-sdk-CAR_V1.4.5");
        this.c.w(str2);
        a(aICloudTTSIntent);
        this.a.a(this.c);
    }

    public void stop() {
        this.a.f();
    }

    public void pause() {
        this.a.d();
    }

    public void resume() {
        this.a.e();
    }

    public void release() {
        this.a.g();
    }

    public void setRealBack(boolean z) {
        this.c.a(z);
    }

    public void setUseCache(boolean z) {
        this.e = z;
        l.b().a(z);
    }

    public boolean isUseCache() {
        return this.e;
    }

    public void setSpeaker(String str) {
        this.c.o(str);
    }

    public void setAudioType(String str) {
        this.c.c(str);
    }

    public void setMP3Quality(String str) {
        this.c.m(str);
    }

    public void setTextType(String str) {
        this.c.b(str);
    }

    public void setVolume(String str) {
        this.c.q(str);
    }

    public void setSpeed(String str) {
        this.c.p(str);
    }

    public void setAudioPath(String str) {
        this.c.u(str);
    }

    public void setServer(String str) {
        this.c.n(str);
    }

    public void setStreamType(int i) {
        this.c.e(i);
    }

    @TargetApi(23)
    public void setAudioAttributes(AudioAttributes audioAttributes) {
        this.c.a(audioAttributes);
    }

    public void setUseStreamType(boolean z) {
        this.c.c(z);
    }

    public void setUseStopCallback(boolean z) {
        this.b.c(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements com.aispeech.lite.b.c, k {
        AITTSListener a = null;

        @Override // com.aispeech.lite.tts.k
        public final void a(int i) {
            AITTSListener aITTSListener = this.a;
            if (aITTSListener != null) {
                aITTSListener.onInit(i);
            }
        }

        @Override // com.aispeech.lite.tts.k
        public final void a(String str) {
            AITTSListener aITTSListener = this.a;
            if (aITTSListener != null) {
                aITTSListener.onReady(str);
            }
        }

        @Override // com.aispeech.lite.tts.k
        public final void b(String str) {
            AITTSListener aITTSListener = this.a;
            if (aITTSListener != null) {
                aITTSListener.onCompletion(str);
            }
        }

        @Override // com.aispeech.lite.b.c, com.aispeech.lite.tts.k
        public final void a(AIError aIError, String str) {
            AITTSListener aITTSListener = this.a;
            if (aITTSListener != null) {
                aITTSListener.onError(str, aIError);
            }
        }

        @Override // com.aispeech.lite.b.c, com.aispeech.lite.tts.k
        public final void a(int i, int i2, boolean z) {
            AITTSListener aITTSListener = this.a;
            if (aITTSListener != null) {
                aITTSListener.onProgress(i, i2, z);
            }
        }

        @Override // com.aispeech.lite.tts.k
        public final void c(String str) {
            AITTSListener aITTSListener = this.a;
            if (aITTSListener != null) {
                aITTSListener.onSynthesizeStart(str);
            }
        }

        @Override // com.aispeech.lite.tts.k
        public final void a(byte[] bArr, String str) {
            AITTSListener aITTSListener = this.a;
            if (aITTSListener != null) {
                aITTSListener.onSynthesizeDataArrived(str, bArr);
            }
        }

        @Override // com.aispeech.lite.tts.k
        public final void d(String str) {
            AITTSListener aITTSListener = this.a;
            if (aITTSListener != null) {
                aITTSListener.onSynthesizeFinish(str);
            }
        }

        @Override // com.aispeech.lite.b.c
        public final void a() {
        }

        @Override // com.aispeech.lite.b.c
        public final void a(long j) {
        }
    }
}
