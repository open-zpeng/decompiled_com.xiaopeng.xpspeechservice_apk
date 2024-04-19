package com.aispeech.export.engines;

import android.text.TextUtils;
import com.aispeech.common.Log;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import com.aispeech.export.config.AILocalGrammarConfig;
import com.aispeech.export.intent.AILocalGrammarIntent;
import com.aispeech.export.listeners.AILocalGrammarListener;
import com.aispeech.kernel.Gram;
import com.aispeech.lite.c;
import com.aispeech.lite.c.g;
import com.aispeech.lite.d.b;
import com.aispeech.lite.f;
import com.aispeech.lite.h.j;
import com.aispeech.lite.speech.EngineListener;
import java.io.File;
/* loaded from: classes.dex */
public class AILocalGrammarEngine {
    public static final String TAG = "AILocalGrammarEngine";
    private String e = "";
    private String f = "";
    private com.aispeech.lite.d.a a = new com.aispeech.lite.d.a();
    private j b = new j();
    private g c = new g();
    private a d = new a((byte) 0);

    private AILocalGrammarEngine() {
    }

    public static boolean checkLibValid() {
        return Gram.isGramSoValid();
    }

    public static AILocalGrammarEngine createInstance() {
        return new AILocalGrammarEngine();
    }

    public void setRes(String str) {
        this.e = str;
    }

    public void setResPath(String str) {
        this.f = str;
    }

    public void init(AILocalGrammarListener aILocalGrammarListener) {
        init(new AILocalGrammarConfig.Builder().setRes(TextUtils.isEmpty(this.e) ? this.f : this.e).build(), aILocalGrammarListener);
    }

    public void init(AILocalGrammarConfig aILocalGrammarConfig, AILocalGrammarListener aILocalGrammarListener) {
        this.d.a(aILocalGrammarListener);
        if (aILocalGrammarConfig == null) {
            throw new IllegalArgumentException("AILocalGrammarConfig should not be null");
        }
        String res = aILocalGrammarConfig.getRes();
        if (TextUtils.isEmpty(res)) {
            Log.e(TAG, "vadRes not found int asserts!!");
            this.c.a("OFF");
        } else if (res.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
            this.c.a(res);
        } else {
            this.c.a(new String[]{res});
            g gVar = this.c;
            gVar.a(Util.getResourceDir(c.b()) + File.separator + res);
        }
        this.a.a(this.d, this.c);
    }

    public void setOutputPath(String str) {
        this.b.b(str);
    }

    public void buildGrammar(String str) {
        if (this.a != null) {
            this.b.h(str);
            this.a.a(this.b);
        }
    }

    public void buildGrammar(AILocalGrammarIntent aILocalGrammarIntent, String str) {
        if (!TextUtils.isEmpty(aILocalGrammarIntent.getOutputPath())) {
            this.b.b(aILocalGrammarIntent.getOutputPath());
        }
        if (this.a != null) {
            this.b.h(str);
            this.a.a(this.b);
        }
    }

    public void destroy() {
        com.aispeech.lite.d.a aVar = this.a;
        if (aVar != null) {
            aVar.e();
        }
        a aVar2 = this.d;
        if (aVar2 != null) {
            aVar2.a();
            this.d = null;
        }
        if (this.c != null) {
            this.c = null;
        }
        if (this.b != null) {
            this.b = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends f implements b {
        private AILocalGrammarListener a;

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

        final void a(AILocalGrammarListener aILocalGrammarListener) {
            super.a((EngineListener) aILocalGrammarListener);
            this.a = aILocalGrammarListener;
        }

        @Override // com.aispeech.lite.f
        protected final void a(f.a aVar, Object obj) {
            AILocalGrammarListener aILocalGrammarListener;
            if (AnonymousClass1.a[aVar.ordinal()] == 1 && (aILocalGrammarListener = this.a) != null) {
                aILocalGrammarListener.onBuildCompleted((String) obj);
            }
        }

        @Override // com.aispeech.lite.d.b
        public final void a(String str) {
            b(f.a.MSG_GRAMMAR_SUCCESS, str);
        }
    }

    /* renamed from: com.aispeech.export.engines.AILocalGrammarEngine$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[f.a.values().length];

        static {
            try {
                a[f.a.MSG_GRAMMAR_SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }
}
