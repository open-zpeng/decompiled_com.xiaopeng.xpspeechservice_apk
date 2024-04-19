package com.aispeech.export.engines;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.common.Log;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import com.aispeech.export.config.AILocalTTSConfig;
import com.aispeech.export.intent.AILocalTTSIntent;
import com.aispeech.export.listeners.AILocalTTSListener;
import com.aispeech.export.listeners.AITTSListener;
import com.aispeech.kernel.Cntts;
import com.aispeech.lite.c;
import com.aispeech.lite.c.i;
import com.aispeech.lite.h.l;
import com.aispeech.lite.tts.k;
import com.aispeech.lite.tts.n;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class AILocalTTSEngine {
    public static final String TAG = "AILocalTTSEngine";
    private static volatile AILocalTTSEngine l;
    private n a;
    private l b;
    private i c;
    private a d;
    private List<String> e = new ArrayList();
    private String f = "";
    private String g = "";
    private String h = "";
    private boolean i = true;
    private String[] j;
    private volatile boolean k;

    private AILocalTTSEngine() {
        this.d = null;
        if (this.a == null) {
            this.a = n.b();
        }
        if (this.b == null) {
            this.b = new l();
        }
        if (this.c == null) {
            this.c = new i();
        }
        if (this.d == null) {
            this.d = new a((byte) 0);
        }
        com.aispeech.lite.tts.l.a().a(this.i);
    }

    public static AILocalTTSEngine getInstance() {
        if (l == null) {
            synchronized (AILocalTTSEngine.class) {
                if (l == null) {
                    l = new AILocalTTSEngine();
                }
            }
        }
        return l;
    }

    public static boolean checkLibValid() {
        return Cntts.isCnttsSoValid();
    }

    public void setFrontResBin(String str) {
        this.g = str;
    }

    @Deprecated
    public void setFrontResBin(String str, String str2) {
        setFrontResBin(str);
    }

    public void setFrontResBinPath(String str) {
        this.h = str;
    }

    public void setBackResBinArray(String[] strArr) {
        this.j = strArr;
        for (String str : strArr) {
            this.e.add(str);
        }
    }

    @Deprecated
    public void setBackResBinArray(String[] strArr, String[] strArr2) {
        setBackResBinArray(strArr);
    }

    public void setBackResBin(String str) {
        n nVar = this.a;
        if (nVar != null) {
            nVar.a(Util.getResourceDir(c.b()) + File.separator + str);
        }
    }

    public void setEnableOptimization(boolean z) {
        this.c.d(z);
    }

    public void setBackResBinPath(String str) {
        n nVar = this.a;
        if (nVar != null) {
            nVar.a(str);
        }
    }

    public void deleteLocalResFile() {
        n nVar = this.a;
        if (nVar != null) {
            nVar.c();
        }
    }

    @Deprecated
    public void setRes(String str) {
    }

    @Deprecated
    public void setRes(String str, String str2) {
    }

    @Deprecated
    public void setResPath(String str) {
    }

    public void setDictDb(String str) {
        this.f = str;
    }

    @Deprecated
    public void setDictDb(String str, String str2) {
        setDictDb(str);
    }

    public void setDictDbPath(String str) {
    }

    public void setSpeechRate(float f) {
        this.b.a(f);
    }

    public void setSpeechVolume(int i) {
        this.b.a(i);
    }

    public void setUseSSML(boolean z) {
        this.b.a(z);
    }

    public void setStreamType(int i) {
        this.b.e(i);
    }

    @TargetApi(23)
    public void setAudioAttributes(AudioAttributes audioAttributes) {
        this.b.a(audioAttributes);
    }

    public void setUseStreamType(boolean z) {
        this.b.c(z);
    }

    public void setUseStopCallback(boolean z) {
        this.c.c(z);
    }

    @Deprecated
    public void init(AILocalTTSListener aILocalTTSListener) {
        init(new AILocalTTSConfig.Builder().setBackResBinArray(this.j).setDictDb(this.f).setEnableOptimization(this.c.i()).setFrontResBin(TextUtils.isEmpty(this.g) ? this.h : this.g).setUseCache(this.i).setUseStopCallback(this.c.e()).build(), aILocalTTSListener);
    }

    @Deprecated
    public void init(AILocalTTSConfig aILocalTTSConfig, AILocalTTSListener aILocalTTSListener) {
        a(aILocalTTSConfig);
        a aVar = this.d;
        aVar.a = aILocalTTSListener;
        this.a.a(aVar, this.c, "LocalTts");
        a();
    }

    public void init(AITTSListener aITTSListener) {
        init(new AILocalTTSConfig.Builder().setBackResBinArray(this.j).setDictDb(this.f).setEnableOptimization(this.c.i()).setFrontResBin(TextUtils.isEmpty(this.g) ? this.h : this.g).setUseCache(this.i).setUseStopCallback(this.c.e()).build(), aITTSListener);
    }

    public void init(AILocalTTSConfig aILocalTTSConfig, AITTSListener aITTSListener) {
        a(aILocalTTSConfig);
        this.d.b = aITTSListener;
        a();
    }

    private void a() {
        if (this.k) {
            Log.e(TAG, "Do not repeat initialize!");
            return;
        }
        this.k = true;
        this.a.a(this.d, this.c, "LocalTts");
    }

    private void a(AILocalTTSConfig aILocalTTSConfig) {
        if (aILocalTTSConfig == null) {
            throw new IllegalArgumentException("AILocalTTSConfig can not be null!");
        }
        String[] backResBinArray = aILocalTTSConfig.getBackResBinArray();
        if (backResBinArray != null) {
            for (int i = 0; i < backResBinArray.length; i++) {
                if (!backResBinArray[i].startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                    this.e.add(backResBinArray[i]);
                }
            }
            if (backResBinArray[0].startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.c.b(backResBinArray[0]);
            } else {
                this.c.b(Util.getResourceDir(this.c.b()) + File.separator + backResBinArray[0]);
            }
        }
        if (aILocalTTSConfig.getDictDb() != null) {
            if (aILocalTTSConfig.getDictDb().startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.c.c(aILocalTTSConfig.getDictDb());
            } else {
                this.e.add(aILocalTTSConfig.getDictDb());
                this.c.c(Util.getResourceDir(this.c.b()) + File.separator + aILocalTTSConfig.getDictDb());
            }
        }
        this.c.d(aILocalTTSConfig.isEnableOptimization());
        if (aILocalTTSConfig.getFrontResBin() != null) {
            if (aILocalTTSConfig.getFrontResBin().startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.c.a(aILocalTTSConfig.getFrontResBin());
            } else {
                this.e.add(aILocalTTSConfig.getFrontResBin());
                this.c.a(Util.getResourceDir(this.c.b()) + File.separator + aILocalTTSConfig.getFrontResBin());
            }
        }
        i iVar = this.c;
        List<String> list = this.e;
        iVar.a((String[]) list.toArray(new String[list.size()]));
        this.i = aILocalTTSConfig.isUseCache();
        com.aispeech.lite.tts.l.a().a(this.i);
        this.c.c(aILocalTTSConfig.isUseStopCallback());
    }

    public void parseIntent(AILocalTTSIntent aILocalTTSIntent) {
        if (aILocalTTSIntent == null) {
            throw new IllegalArgumentException("AILocalTTSIntent can not be null!");
        }
        if (!TextUtils.isEmpty(aILocalTTSIntent.getBackResBin())) {
            if (aILocalTTSIntent.getBackResBin().startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.c.b(aILocalTTSIntent.getBackResBin());
                this.a.a(aILocalTTSIntent.getBackResBin());
            } else {
                i iVar = this.c;
                iVar.b(Util.getResourceDir(this.c.b()) + File.separator + aILocalTTSIntent.getBackResBin());
                n nVar = this.a;
                if (nVar != null) {
                    nVar.a(Util.getResourceDir(c.b()) + File.separator + aILocalTTSIntent.getBackResBin());
                }
            }
        }
        if (aILocalTTSIntent.getSpeechRate() > 0.0f) {
            this.b.a(aILocalTTSIntent.getSpeechRate());
        }
        if (aILocalTTSIntent.getSpeechVolume() > 0) {
            this.b.a(aILocalTTSIntent.getSpeechVolume());
        }
        this.b.a(aILocalTTSIntent.isUseSSML());
        if (aILocalTTSIntent.getStreamType() > 0) {
            this.b.e(aILocalTTSIntent.getStreamType());
        }
        if (aILocalTTSIntent.getAudioAttributes() != null) {
            this.b.a(aILocalTTSIntent.getAudioAttributes());
        }
        this.b.c(aILocalTTSIntent.isUseStreamType());
        if (aILocalTTSIntent.getSaveAudioFileName() != null) {
            this.b.x(aILocalTTSIntent.getSaveAudioFileName());
        }
        if (aILocalTTSIntent.getSaveAudioFileName() != null) {
            this.b.x(aILocalTTSIntent.getSaveAudioFileName());
        }
    }

    public void speak(String str, String str2) {
        this.b.b(str);
        this.b.b(true);
        this.b.d(false);
        this.b.w(str2);
        this.a.a(this.b);
    }

    public void speak(String str, String str2, AILocalTTSIntent aILocalTTSIntent) {
        this.b.b(str);
        this.b.b(true);
        this.b.d(false);
        this.b.w(str2);
        parseIntent(aILocalTTSIntent);
        this.a.a(this.b);
    }

    public void pause() {
        this.a.d();
    }

    public void resume() {
        this.a.e();
    }

    public void stop() {
        this.a.f();
    }

    public void synthesize(String str, String str2) {
        this.b.b(str);
        this.b.b(false);
        this.b.d(true);
        this.b.w(str2);
        this.a.a(this.b);
    }

    public void synthesize(String str, String str2, AILocalTTSIntent aILocalTTSIntent) {
        this.b.b(str);
        this.b.b(false);
        this.b.d(true);
        this.b.w(str2);
        parseIntent(aILocalTTSIntent);
        this.a.a(this.b);
    }

    public void synthesizeToFile(String str, String str2, String str3) {
        this.b.b(str);
        this.b.b(false);
        this.b.d(false);
        this.b.x(str2);
        this.b.w(str3);
        this.a.a(this.b);
    }

    public void synthesizeToFile(String str, String str2, String str3, AILocalTTSIntent aILocalTTSIntent) {
        this.b.b(str);
        this.b.b(false);
        this.b.d(false);
        this.b.x(str2);
        this.b.w(str3);
        parseIntent(aILocalTTSIntent);
        this.a.a(this.b);
    }

    public void setSaveAudioFileName(String str) {
        this.b.x(str);
    }

    public void destroy() {
        n nVar = this.a;
        if (nVar != null) {
            nVar.g();
        }
        List<String> list = this.e;
        if (list != null) {
            list.clear();
        }
        a aVar = this.d;
        if (aVar != null) {
            aVar.a = null;
            aVar.b = null;
        }
        l = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.k = false;
    }

    public void setUseCache(boolean z) {
        this.i = z;
        com.aispeech.lite.tts.l.a().a(z);
    }

    public boolean isUseCache() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements com.aispeech.lite.b.c, k {
        AILocalTTSListener a;
        AITTSListener b;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // com.aispeech.lite.tts.k
        public final void a(int i) {
            AITTSListener aITTSListener = this.b;
            if (aITTSListener != null) {
                aITTSListener.onInit(i);
                return;
            }
            AILocalTTSListener aILocalTTSListener = this.a;
            if (aILocalTTSListener != null) {
                aILocalTTSListener.onInit(i);
            }
        }

        @Override // com.aispeech.lite.tts.k
        public final void a(String str) {
            AITTSListener aITTSListener = this.b;
            if (aITTSListener != null) {
                aITTSListener.onReady(str);
                return;
            }
            AILocalTTSListener aILocalTTSListener = this.a;
            if (aILocalTTSListener != null) {
                aILocalTTSListener.onSpeechStart(str);
            }
        }

        @Override // com.aispeech.lite.tts.k
        public final void b(String str) {
            AITTSListener aITTSListener = this.b;
            if (aITTSListener != null) {
                aITTSListener.onCompletion(str);
                return;
            }
            AILocalTTSListener aILocalTTSListener = this.a;
            if (aILocalTTSListener != null) {
                aILocalTTSListener.onSpeechFinish(str);
            }
        }

        @Override // com.aispeech.lite.b.c, com.aispeech.lite.tts.k
        public final void a(AIError aIError, String str) {
            AITTSListener aITTSListener = this.b;
            if (aITTSListener != null) {
                aITTSListener.onError(str, aIError);
                return;
            }
            AILocalTTSListener aILocalTTSListener = this.a;
            if (aILocalTTSListener != null) {
                aILocalTTSListener.onError(str, aIError);
            }
        }

        @Override // com.aispeech.lite.b.c, com.aispeech.lite.tts.k
        public final void a(int i, int i2, boolean z) {
            AITTSListener aITTSListener = this.b;
            if (aITTSListener != null) {
                aITTSListener.onProgress(i, i2, z);
                return;
            }
            AILocalTTSListener aILocalTTSListener = this.a;
            if (aILocalTTSListener != null) {
                aILocalTTSListener.onSpeechProgress(i, i2, z);
            }
        }

        @Override // com.aispeech.lite.tts.k
        public final void c(String str) {
            AITTSListener aITTSListener = this.b;
            if (aITTSListener != null) {
                aITTSListener.onSynthesizeStart(str);
                return;
            }
            AILocalTTSListener aILocalTTSListener = this.a;
            if (aILocalTTSListener != null) {
                aILocalTTSListener.onSynthesizeStart(str);
            }
        }

        @Override // com.aispeech.lite.tts.k
        public final void a(byte[] bArr, String str) {
            AITTSListener aITTSListener = this.b;
            if (aITTSListener != null) {
                aITTSListener.onSynthesizeDataArrived(str, bArr);
                return;
            }
            AILocalTTSListener aILocalTTSListener = this.a;
            if (aILocalTTSListener != null) {
                aILocalTTSListener.onSynthesizeDataArrived(str, bArr);
            }
        }

        @Override // com.aispeech.lite.tts.k
        public final void d(String str) {
            AITTSListener aITTSListener = this.b;
            if (aITTSListener != null) {
                aITTSListener.onSynthesizeFinish(str);
                return;
            }
            AILocalTTSListener aILocalTTSListener = this.a;
            if (aILocalTTSListener != null) {
                aILocalTTSListener.onSynthesizeFinish(str);
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
