package com.aispeech.export.config;
/* loaded from: classes.dex */
public class AICloudTTSConfig {
    private boolean a;
    private boolean b;

    public boolean isUseCache() {
        return this.a;
    }

    public boolean isUseStopCallback() {
        return this.b;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private boolean a;
        private boolean b = true;

        public final Builder setUseCache(boolean z) {
            this.a = z;
            return this;
        }

        public final Builder setUseStopCallback(boolean z) {
            this.b = z;
            return this;
        }

        public final AICloudTTSConfig build() {
            AICloudTTSConfig aICloudTTSConfig = new AICloudTTSConfig();
            aICloudTTSConfig.b = this.b;
            aICloudTTSConfig.a = this.a;
            return aICloudTTSConfig;
        }
    }
}
