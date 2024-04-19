package com.aispeech.export.config;

import com.aispeech.lite.Languages;
/* loaded from: classes.dex */
public class AIWakeupConfig {
    private String a;
    private AIOneshotConfig b;
    private boolean c = false;
    private boolean d;
    private Languages e;

    public String getResBinName() {
        return this.a;
    }

    public AIOneshotConfig getOneshotConfig() {
        return this.b;
    }

    public boolean isPreWakeupOn() {
        return this.c;
    }

    public boolean isUseCustomFeed() {
        return this.d;
    }

    public Languages getLanguages() {
        return this.e;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private String a;
        private AIOneshotConfig b;
        private boolean c = false;
        private boolean d;
        private Languages e;

        public final Builder setResBinName(String str) {
            this.a = str;
            return this;
        }

        public final Builder setOneshotConfig(AIOneshotConfig aIOneshotConfig) {
            this.b = aIOneshotConfig;
            return this;
        }

        public final Builder setPreWakeupOn(boolean z) {
            this.c = z;
            return this;
        }

        public final Builder setUseCustomFeed(boolean z) {
            this.d = z;
            return this;
        }

        public final Builder setLanguages(Languages languages) {
            this.e = languages;
            return this;
        }

        public final AIWakeupConfig build() {
            AIWakeupConfig aIWakeupConfig = new AIWakeupConfig();
            aIWakeupConfig.c = this.c;
            aIWakeupConfig.b = this.b;
            aIWakeupConfig.d = this.d;
            aIWakeupConfig.e = this.e;
            aIWakeupConfig.a = this.a;
            return aIWakeupConfig;
        }
    }
}
