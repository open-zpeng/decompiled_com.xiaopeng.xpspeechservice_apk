package com.aispeech.export.config;
/* loaded from: classes.dex */
public class AILocalVadConfig {
    private String a;
    private int b;
    private boolean c;

    public String getVadResource() {
        return this.a;
    }

    public int getPauseTime() {
        return this.b;
    }

    public boolean isUseFullMode() {
        return this.c;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private String a;
        private int b = 300;
        private boolean c;

        public final Builder setVadResource(String str) {
            this.a = str;
            return this;
        }

        public final Builder setPauseTime(int i) {
            this.b = i;
            return this;
        }

        public final Builder setUseFullMode(boolean z) {
            this.c = z;
            return this;
        }

        public final AILocalVadConfig build() {
            AILocalVadConfig aILocalVadConfig = new AILocalVadConfig();
            aILocalVadConfig.a = this.a;
            aILocalVadConfig.b = this.b;
            aILocalVadConfig.c = this.c;
            return aILocalVadConfig;
        }
    }
}
