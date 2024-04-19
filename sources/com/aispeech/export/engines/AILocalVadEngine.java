package com.aispeech.export.engines;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.auth.f;
import com.aispeech.common.Log;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import com.aispeech.export.config.AILocalVadConfig;
import com.aispeech.export.listeners.AILocalVadListener;
import com.aispeech.kernel.Vad;
import com.aispeech.lite.c;
import com.aispeech.lite.c.j;
import com.aispeech.lite.h.o;
import com.aispeech.lite.vad.a;
/* loaded from: classes.dex */
public class AILocalVadEngine {
    public static final String TAG = "AILocalVadEngine";
    private a a;
    private AILocalVadListener f;
    private f g;
    private String d = "";
    private String e = "";
    private j b = new j();
    private o c = new o();

    private AILocalVadEngine() {
    }

    public static AILocalVadEngine createInstance() {
        return new AILocalVadEngine();
    }

    public static boolean checkLibValid() {
        return Vad.isVadSoValid();
    }

    public void init(AILocalVadListener aILocalVadListener) {
        init(new AILocalVadConfig.Builder().setVadResource(TextUtils.isEmpty(this.e) ? this.d : this.e).setPauseTime(this.c.d()).setUseFullMode(this.b.i()).build(), aILocalVadListener);
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [com.aispeech.export.engines.AILocalVadEngine$1] */
    public void init(final AILocalVadConfig aILocalVadConfig, AILocalVadListener aILocalVadListener) {
        if (aILocalVadConfig == null) {
            throw new IllegalArgumentException("AILocalVadConfig can not be null");
        }
        this.f = aILocalVadListener;
        this.g = c.a().a("vad");
        Log.d(TAG, "authstate: " + this.g.toString());
        if (this.g.b()) {
            this.a = new a("VadEngine", aILocalVadListener);
            new Thread() { // from class: com.aispeech.export.engines.AILocalVadEngine.1
                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    String vadResource = aILocalVadConfig.getVadResource();
                    if (TextUtils.isEmpty(vadResource)) {
                        if (AILocalVadEngine.this.f != null) {
                            AILocalVadEngine.this.f.onError(new AIError((int) AIError.ERR_RES_PREPARE_FAILED, AIError.ERR_DESCRIPTION_RES_PREPARE_FAILED));
                            return;
                        }
                        return;
                    }
                    if (vadResource.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                        AILocalVadEngine.this.b.a(vadResource);
                    } else if (Util.copyResource(c.b(), vadResource) == -1) {
                        if (AILocalVadEngine.this.f != null) {
                            AILocalVadEngine.this.f.onInit(-1);
                            AILocalVadEngine.this.f.onError(new AIError((int) AIError.ERR_RES_PREPARE_FAILED, AIError.ERR_DESCRIPTION_RES_PREPARE_FAILED));
                            return;
                        }
                        return;
                    } else {
                        j jVar = AILocalVadEngine.this.b;
                        jVar.a("/data/data/" + c.b().getPackageName() + "/files/" + vadResource);
                    }
                    AILocalVadEngine.this.b.e(aILocalVadConfig.isUseFullMode());
                    AILocalVadEngine.this.b.a(aILocalVadConfig.getPauseTime());
                    AILocalVadEngine.this.a.newKernel(AILocalVadEngine.this.b);
                }
            }.start();
            return;
        }
        a(this.g);
    }

    public void start() {
        f fVar = this.g;
        if (fVar != null && fVar.b()) {
            a aVar = this.a;
            if (aVar != null) {
                aVar.startKernel(this.c);
                return;
            }
            return;
        }
        a(this.g);
    }

    public void feedData(byte[] bArr, int i) {
        f fVar = this.g;
        if (fVar != null && fVar.b()) {
            if (this.a != null) {
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                this.a.feed(bArr2);
                return;
            }
            return;
        }
        a(this.g);
    }

    public void stop() {
        f fVar = this.g;
        if (fVar != null && fVar.b()) {
            a aVar = this.a;
            if (aVar != null) {
                aVar.stopKernel();
                return;
            }
            return;
        }
        a(this.g);
    }

    public void destroy() {
        f fVar = this.g;
        if (fVar != null && fVar.b()) {
            a aVar = this.a;
            if (aVar != null) {
                aVar.releaseKernel();
                return;
            }
            return;
        }
        a(this.g);
    }

    public void setVadResBinPath(String str) {
        this.d = str;
    }

    public void setVadResource(String str) {
        this.e = str;
    }

    public void setPauseTime(int i) {
        this.b.a(i);
        this.c.a(i);
    }

    public void setUseFullMode(boolean z) {
        this.b.e(z);
    }

    private void a(f fVar) {
        AIError aIError = new AIError();
        if (fVar == null) {
            aIError.setErrId(AIError.ERR_SDK_NOT_INIT);
            aIError.setError(AIError.ERR_DESCRIPTION_ERR_SDK_NOT_INIT);
        } else {
            aIError.setErrId(fVar.d().getId());
            aIError.setError(fVar.d().getValue());
        }
        AILocalVadListener aILocalVadListener = this.f;
        if (aILocalVadListener != null) {
            aILocalVadListener.onError(aIError);
        }
    }
}
