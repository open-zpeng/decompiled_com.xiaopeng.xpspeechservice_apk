package com.aispeech.export.config;

import android.text.TextUtils;
/* loaded from: classes.dex */
public class AILocalAsrppConfig {
    private String a;
    private boolean b;
    private String c;
    private int d;
    private boolean e;

    public String getAsrppResBin() {
        return this.a;
    }

    public boolean isVadEnable() {
        return this.b;
    }

    public String getVadRes() {
        return this.c;
    }

    public int getVadPauseTime() {
        return this.d;
    }

    public boolean isUseCustomFeed() {
        return this.e;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private String a;
        private boolean b;
        private String c;
        private int d;
        private boolean e;

        public final Builder setAsrppResBin(String str) {
            this.a = str;
            return this;
        }

        public final Builder setVadEnable(boolean z) {
            this.b = z;
            return this;
        }

        public final Builder setVadRes(String str) {
            this.c = str;
            return this;
        }

        public final Builder setVadPauseTime(int i) {
            this.d = i;
            return this;
        }

        public final Builder setUseCustomFeed(boolean z) {
            this.e = z;
            return this;
        }

        public final AILocalAsrppConfig build() {
            if (TextUtils.isEmpty(this.a)) {
                throw new IllegalArgumentException("must set asr res!");
            }
            if (!this.b || !TextUtils.isEmpty(this.c)) {
                AILocalAsrppConfig aILocalAsrppConfig = new AILocalAsrppConfig();
                aILocalAsrppConfig.d = this.d;
                aILocalAsrppConfig.b = this.b;
                aILocalAsrppConfig.c = this.c;
                aILocalAsrppConfig.a = this.a;
                aILocalAsrppConfig.e = this.e;
                return aILocalAsrppConfig;
            }
            throw new IllegalArgumentException("use vad or use ssl must set vad res!");
        }
    }
}
