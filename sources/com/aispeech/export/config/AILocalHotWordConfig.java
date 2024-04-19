package com.aispeech.export.config;

import android.text.TextUtils;
import com.aispeech.lite.Languages;
/* loaded from: classes.dex */
public class AILocalHotWordConfig {
    public String asrRes;
    public Languages languages;
    public boolean useCustomFeed;
    public boolean useSSL;
    public boolean useVad;
    public String vadRes;

    /* synthetic */ AILocalHotWordConfig(Builder builder, byte b) {
        this(builder);
    }

    private AILocalHotWordConfig(boolean z, String str, String str2, boolean z2, Languages languages, boolean z3) {
        this.useVad = z;
        this.vadRes = str;
        this.asrRes = str2;
        this.useCustomFeed = z2;
        this.languages = languages;
        this.useSSL = z3;
    }

    private AILocalHotWordConfig(Builder builder) {
        this(builder.useVad, builder.a, builder.b, builder.c, builder.d, builder.useSSL);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        String a;
        String b;
        public boolean useVad = true;
        public boolean useSSL = false;
        boolean c = false;
        Languages d = Languages.CHINESE;

        public Builder setUseVad(boolean z) {
            this.useVad = z;
            return this;
        }

        public Builder setUseSSL(boolean z) {
            this.useSSL = z;
            return this;
        }

        public Builder setVadRes(String str) {
            this.a = str;
            return this;
        }

        public Builder setAsrRes(String str) {
            this.b = str;
            return this;
        }

        public Builder setUseCustomFeed(boolean z) {
            this.c = z;
            return this;
        }

        public Builder setLanguages(Languages languages) {
            this.d = languages;
            return this;
        }

        public AILocalHotWordConfig build() {
            if (TextUtils.isEmpty(this.b)) {
                throw new IllegalArgumentException("must set asr res!");
            }
            if (this.useSSL && !this.c) {
                throw new IllegalArgumentException("use ssl must custom feed!");
            }
            if ((!this.useVad && !this.useSSL) || !TextUtils.isEmpty(this.a)) {
                return new AILocalHotWordConfig(this, (byte) 0);
            }
            throw new IllegalArgumentException("use vad or use ssl must set vad res!");
        }
    }
}
