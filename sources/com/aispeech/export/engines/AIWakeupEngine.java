package com.aispeech.export.engines;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.common.ArrayUtils;
import com.aispeech.common.DynamicParamUtils;
import com.aispeech.common.Log;
import com.aispeech.common.PinYinUtils;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import com.aispeech.export.config.AIOneshotConfig;
import com.aispeech.export.config.AIWakeupConfig;
import com.aispeech.export.exception.IllegalPinyinException;
import com.aispeech.export.intent.AIWakeupIntent;
import com.aispeech.export.listeners.AIWakeupListener;
import com.aispeech.kernel.Utils;
import com.aispeech.kernel.Wakeup;
import com.aispeech.lite.Languages;
import com.aispeech.lite.c;
import com.aispeech.lite.c.j;
import com.aispeech.lite.c.k;
import com.aispeech.lite.c.m;
import com.aispeech.lite.f;
import com.aispeech.lite.h.q;
import com.aispeech.lite.j.b;
import com.aispeech.lite.oneshot.OneshotCache;
import com.aispeech.lite.speech.EngineListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class AIWakeupEngine {
    private String d;
    private String e = "";
    private List<String> g = new ArrayList();
    private Languages h = Languages.CHINESE;
    private m b = new m();
    private q c = new q();
    private b a = new b();
    private a f = new a((byte) 0);

    private AIWakeupEngine() {
    }

    public static AIWakeupEngine createInstance() {
        return new AIWakeupEngine();
    }

    public void init(AIWakeupListener aIWakeupListener) {
        init(new AIWakeupConfig.Builder().setResBinName(TextUtils.isEmpty(this.d) ? this.e : this.d).setPreWakeupOn(this.c.e()).setUseCustomFeed(this.b.d()).setLanguages(this.h).build(), aIWakeupListener);
    }

    public void init(AIWakeupConfig aIWakeupConfig, AIWakeupListener aIWakeupListener) {
        if (aIWakeupConfig == null) {
            throw new IllegalArgumentException("AIWakeupConfig can not be null!");
        }
        if (aIWakeupConfig.getOneshotConfig() != null) {
            this.b.a(a(aIWakeupConfig.getOneshotConfig()));
        }
        this.b.b(aIWakeupConfig.isUseCustomFeed());
        this.c.b(aIWakeupConfig.isPreWakeupOn());
        if (aIWakeupConfig.getLanguages() != null) {
            this.h = aIWakeupConfig.getLanguages();
        }
        this.g.clear();
        String resBinName = aIWakeupConfig.getResBinName();
        if (!TextUtils.isEmpty(resBinName)) {
            if (resBinName.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                this.b.a(resBinName);
            } else {
                this.g.add(resBinName);
                m mVar = this.b;
                mVar.a(Util.getResourceDir(c.b()) + File.separator + resBinName);
            }
        } else {
            this.g.add("wakeup.bin");
            m mVar2 = this.b;
            mVar2.a(Util.getResourceDir(c.b()) + File.separator + "wakeup.bin");
        }
        this.b.a((String[]) this.g.toArray(new String[0]));
        this.f.a(aIWakeupListener);
        this.a.a(this.f, this.b);
    }

    public void setOneshot(AIOneshotConfig aIOneshotConfig) {
        this.b.a(a(aIOneshotConfig));
    }

    public void setWakeupWords(String[] strArr, float[] fArr, int[] iArr) throws IllegalPinyinException {
        String wakeupWordsParams = DynamicParamUtils.getWakeupWordsParams(strArr, fArr, iArr, true);
        Log.d("AIWakeupEngine", "setWakeupWords :" + wakeupWordsParams + "  mDmaspProcessor " + this.a);
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(wakeupWordsParams);
        }
    }

    private static k a(AIOneshotConfig aIOneshotConfig) {
        if (aIOneshotConfig == null) {
            throw new IllegalArgumentException("AIOneshotConfig can not be null!");
        }
        k kVar = new k();
        j jVar = new j();
        jVar.a(0);
        if (!aIOneshotConfig.getResBin().startsWith(URLUtils.URL_PATH_SEPERATOR)) {
            jVar.a(new String[]{aIOneshotConfig.getResBin()});
            jVar.a(Util.getResourceDir(c.b()) + File.separator + aIOneshotConfig.getResBin());
        } else {
            jVar.a(aIOneshotConfig.getResBin());
        }
        kVar.a(jVar);
        kVar.a(aIOneshotConfig.getCacheAudioTime());
        kVar.b(aIOneshotConfig.getMiddleTime());
        kVar.a(aIOneshotConfig.getWords());
        return kVar;
    }

    public void setInputContinuousAudio(boolean z) {
        this.c.a(z);
    }

    public void setWakeupWord(String[] strArr, String[] strArr2) {
        setWakeupWord(strArr, ArrayUtils.string2Float(strArr2));
    }

    public void setWakeupWord(String[] strArr, float[] fArr) {
        setWakeupWord(strArr, fArr, (int[]) null);
    }

    public void setWakeupWord(String[] strArr, String[] strArr2, String[] strArr3) {
        setWakeupWord(strArr, ArrayUtils.string2Float(strArr2), ArrayUtils.string2Int(strArr3));
    }

    public void setWakeupWord(String[] strArr, float[] fArr, int[] iArr) {
        if (this.h == Languages.CHINESE) {
            for (String str : strArr) {
                if (!PinYinUtils.checkPinyin(str)) {
                    throw new IllegalPinyinException();
                }
            }
        }
        if (strArr != null && strArr.length != 0) {
            this.c.a(strArr);
        }
        if (fArr != null && fArr.length != 0) {
            this.c.a(fArr);
        }
        if (iArr != null && iArr.length != 0) {
            this.c.b(iArr);
        }
    }

    public void setLanguages(Languages languages) {
        this.h = languages;
    }

    public void set(JSONObject jSONObject) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(jSONObject.toString());
        }
    }

    public void setDcheck(String[] strArr) {
        setDcheck(ArrayUtils.string2Int(strArr));
    }

    public void setDcheck(int[] iArr) {
        this.c.a(iArr);
    }

    public void setUseCustomFeed(boolean z) {
        this.b.b(z);
    }

    @Deprecated
    public void setUseRecord(boolean z) {
        this.b.b(!z);
    }

    @Deprecated
    public void feed(byte[] bArr) {
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

    public void start() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(this.c);
        }
    }

    public void start(AIWakeupIntent aIWakeupIntent) {
        if (this.a != null) {
            this.c.a(aIWakeupIntent.getWakeupWords());
            this.c.a(aIWakeupIntent.getThreshold());
            this.c.b(aIWakeupIntent.getMajors());
            this.c.a(aIWakeupIntent.getDcheck());
            this.c.a(aIWakeupIntent.isInputContinuousAudio());
            this.a.a(this.c);
        }
    }

    public void stop() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.c();
        }
    }

    public void destroy() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.e();
        }
        a aVar = this.f;
        if (aVar != null) {
            aVar.a();
            this.f = null;
        }
        if (this.b != null) {
            this.b = null;
        }
        if (this.c != null) {
            this.c = null;
        }
    }

    public void setResBin(String str) {
        this.d = str;
    }

    public void setResBinPath(String str) {
        this.e = str;
    }

    public void setPreWakeupOn(boolean z) {
        this.c.b(z);
    }

    public static boolean checkLibValid() {
        return Wakeup.isWakeupSoValid() && Utils.isUtilsSoValid();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends f implements com.aispeech.lite.j.c {
        private AIWakeupListener a;

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

        final void a(AIWakeupListener aIWakeupListener) {
            super.a((EngineListener) aIWakeupListener);
            this.a = aIWakeupListener;
        }

        @Override // com.aispeech.lite.f
        protected final void a(f.a aVar, Object obj) {
            AIWakeupListener aIWakeupListener;
            int i = AnonymousClass1.a[aVar.ordinal()];
            if (i == 1) {
                Map map = (Map) obj;
                AIWakeupListener aIWakeupListener2 = this.a;
                if (aIWakeupListener2 != null) {
                    aIWakeupListener2.onWakeup((String) map.get(AIError.KEY_RECORD_ID), ((Double) map.get("confidence")).doubleValue(), (String) map.get("wakeupWord"));
                }
            } else if (i == 2) {
                Map map2 = (Map) obj;
                AIWakeupListener aIWakeupListener3 = this.a;
                if (aIWakeupListener3 != null) {
                    aIWakeupListener3.onPreWakeup((String) map2.get(AIError.KEY_RECORD_ID), ((Double) map2.get("confidence")).doubleValue(), (String) map2.get("wakeupWord"));
                }
            } else if (i != 3) {
                if (i == 4 && (aIWakeupListener = this.a) != null) {
                    aIWakeupListener.onNotOneshot((String) obj);
                }
            } else {
                Map map3 = (Map) obj;
                AIWakeupListener aIWakeupListener4 = this.a;
                if (aIWakeupListener4 != null) {
                    aIWakeupListener4.onOneshot((String) map3.get("words"), (OneshotCache) map3.get("audio"));
                }
            }
        }

        @Override // com.aispeech.lite.j.c
        public final void a(String str, double d, String str2) {
            b(f.a.MSG_RESULTS, a(AIError.KEY_RECORD_ID, str, "confidence", Double.valueOf(d), "wakeupWord", str2));
        }

        @Override // com.aispeech.lite.j.c
        public final void b(String str, double d, String str2) {
            b(f.a.MSG_PRE_WAKEUP, a(AIError.KEY_RECORD_ID, str, "confidence", Double.valueOf(d), "wakeupWord", str2));
        }

        @Override // com.aispeech.lite.j.c
        public final void a(int i, byte[] bArr, int i2) {
            AIWakeupListener aIWakeupListener = this.a;
            if (aIWakeupListener != null) {
                aIWakeupListener.onVprintCutDataReceived(i, bArr, i2);
            }
        }

        @Override // com.aispeech.lite.j.c
        public final void a(String str, OneshotCache<byte[]> oneshotCache) {
            b(f.a.MSG_ONE_SHOT, a("words", str, "audio", oneshotCache));
        }

        @Override // com.aispeech.lite.j.c
        public final void a(String str) {
            b(f.a.MSG_NOT_ONE_SHOT, str);
        }
    }

    /* renamed from: com.aispeech.export.engines.AIWakeupEngine$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[f.a.values().length];

        static {
            try {
                a[f.a.MSG_RESULTS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[f.a.MSG_PRE_WAKEUP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[f.a.MSG_ONE_SHOT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[f.a.MSG_NOT_ONE_SHOT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
}
