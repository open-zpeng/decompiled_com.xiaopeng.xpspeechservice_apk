package com.aispeech.export.config;
/* loaded from: classes.dex */
public class AILocalTTSConfig {
    private String a;
    private String[] b;
    private boolean c;
    private String d;
    private boolean e;
    private boolean f;

    public boolean isUseCache() {
        return this.e;
    }

    public boolean isUseStopCallback() {
        return this.f;
    }

    public String getFrontResBin() {
        return this.a;
    }

    public String[] getBackResBinArray() {
        return this.b;
    }

    public boolean isEnableOptimization() {
        return this.c;
    }

    public String getDictDb() {
        return this.d;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private String a;
        private String[] b;
        private boolean c = true;
        private String d;
        private boolean e;
        private boolean f;

        public final Builder setFrontResBin(String str) {
            this.a = str;
            return this;
        }

        public final Builder setBackResBinArray(String[] strArr) {
            this.b = strArr;
            return this;
        }

        public final Builder setEnableOptimization(boolean z) {
            this.c = z;
            return this;
        }

        public final Builder setDictDb(String str) {
            this.d = str;
            return this;
        }

        public final Builder setUseCache(boolean z) {
            this.e = z;
            return this;
        }

        public final Builder setUseStopCallback(boolean z) {
            this.f = z;
            return this;
        }

        public final AILocalTTSConfig build() {
            AILocalTTSConfig aILocalTTSConfig = new AILocalTTSConfig();
            aILocalTTSConfig.d = this.d;
            aILocalTTSConfig.e = this.e;
            aILocalTTSConfig.c = this.c;
            aILocalTTSConfig.a = this.a;
            aILocalTTSConfig.b = this.b;
            aILocalTTSConfig.f = this.f;
            return aILocalTTSConfig;
        }
    }
}
