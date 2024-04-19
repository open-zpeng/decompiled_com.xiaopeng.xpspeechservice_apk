package com.aispeech.export.config;

import android.text.TextUtils;
import com.aispeech.lite.Languages;
/* loaded from: classes.dex */
public class AILocalASRConfig {
    private String a;
    private String b;
    private boolean c;
    private String d;
    private int e;
    private Languages f;
    private boolean g;

    public String getNetBin() {
        return this.a;
    }

    public String getResBin() {
        return this.b;
    }

    public boolean isVadEnable() {
        return this.c;
    }

    public String getVadRes() {
        return this.d;
    }

    public int getVadPauseTime() {
        return this.e;
    }

    public Languages getLanguages() {
        return this.f;
    }

    public boolean isUseCustomFeed() {
        return this.g;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private String a;
        private String b;
        private boolean c;
        private String d;
        private int e;
        private Languages f = Languages.CHINESE;
        private boolean g;

        public final Builder setNetBin(String str) {
            this.a = str;
            return this;
        }

        public final Builder setResBin(String str) {
            this.b = str;
            return this;
        }

        public final Builder setVadEnable(boolean z) {
            this.c = z;
            return this;
        }

        public final Builder setVadRes(String str) {
            this.d = str;
            return this;
        }

        public final Builder setVadPauseTime(int i) {
            this.e = i;
            return this;
        }

        public final Builder setLanguages(Languages languages) {
            this.f = languages;
            return this;
        }

        public final Builder setUseCustomFeed(boolean z) {
            this.g = z;
            return this;
        }

        public final AILocalASRConfig build() {
            if (TextUtils.isEmpty(this.b)) {
                throw new IllegalArgumentException("must set asr res!");
            }
            if (!this.c || !TextUtils.isEmpty(this.d)) {
                AILocalASRConfig aILocalASRConfig = new AILocalASRConfig();
                aILocalASRConfig.e = this.e;
                aILocalASRConfig.a = this.a;
                aILocalASRConfig.c = this.c;
                aILocalASRConfig.d = this.d;
                aILocalASRConfig.b = this.b;
                aILocalASRConfig.f = this.f;
                aILocalASRConfig.g = this.g;
                return aILocalASRConfig;
            }
            throw new IllegalArgumentException("use vad or use ssl must set vad res!");
        }
    }
}
