package com.aispeech.export.config;

import android.text.TextUtils;
import com.aispeech.export.IAsrPolicy;
import com.aispeech.lite.Languages;
import com.aispeech.lite.SemanticType;
/* loaded from: classes.dex */
public class AILocalSemanticConfig {
    public IAsrPolicy asrPolicy;
    public Languages languages;
    public String netBin;
    public String ngramBin;
    public String semDUILuaResFloder;
    public String semDUIResCustomFloder;
    public String semDUIResFloder;
    public String semNaviLuaResFolder;
    public String semNaviResFolder;
    public SemanticType semanticType;
    public boolean useCustomFeed;
    public boolean useFormat;
    public boolean useRefText;
    public boolean useVad;
    public String vadRes;

    /* synthetic */ AILocalSemanticConfig(Builder builder, byte b) {
        this(builder);
    }

    private AILocalSemanticConfig(Builder builder) {
        this.semanticType = SemanticType.NAVI;
        this.useCustomFeed = builder.a;
        this.useVad = builder.b;
        this.vadRes = builder.c;
        this.semNaviResFolder = builder.d;
        this.semNaviLuaResFolder = builder.e;
        this.semDUIResFloder = builder.semDUIResFloder;
        this.semDUILuaResFloder = builder.semDUILuaResFloder;
        this.semDUIResCustomFloder = builder.semDUIResCustomFloder;
        this.ngramBin = builder.f;
        this.netBin = builder.g;
        this.languages = builder.h;
        this.useRefText = builder.i;
        this.useFormat = builder.useFormat;
        this.asrPolicy = builder.j;
        this.semanticType = builder.k;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private IAsrPolicy j;
        public String semDUILuaResFloder;
        public String semDUIResCustomFloder;
        public String semDUIResFloder;
        private boolean a = false;
        private boolean b = true;
        private Languages h = Languages.CHINESE;
        private boolean i = false;
        public boolean useFormat = true;
        private SemanticType k = SemanticType.NAVI;

        public Builder setUseCustomFeed(boolean z) {
            this.a = z;
            return this;
        }

        public Builder setUseVad(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setVadRes(String str) {
            this.c = str;
            return this;
        }

        public Builder setSemNaviResFolder(String str) {
            this.d = str;
            return this;
        }

        public Builder setSemNaviLuaResFolder(String str) {
            this.e = str;
            return this;
        }

        public Builder setSemDUIResFolder(String str) {
            this.semDUIResFloder = str;
            return this;
        }

        public Builder setSemDUIResCustomFolder(String str) {
            this.semDUIResCustomFloder = str;
            return this;
        }

        public Builder setSemDUILuaResFolder(String str) {
            this.semDUILuaResFloder = str;
            return this;
        }

        public Builder setNgramBin(String str) {
            this.f = str;
            return this;
        }

        public Builder setNetBin(String str) {
            this.g = str;
            return this;
        }

        public Builder setLanguages(Languages languages) {
            this.h = languages;
            return this;
        }

        public Builder setUseRefText(boolean z) {
            this.i = z;
            return this;
        }

        public Builder setUseFormat(boolean z) {
            this.useFormat = z;
            return this;
        }

        public Builder setAsrPolicy(IAsrPolicy iAsrPolicy) {
            this.j = iAsrPolicy;
            return this;
        }

        public Builder setSemanticType(SemanticType semanticType) {
            this.k = semanticType;
            return this;
        }

        public AILocalSemanticConfig build() {
            if (this.b && TextUtils.isEmpty(this.c)) {
                throw new IllegalArgumentException("请设置vad资源");
            }
            if (!this.i) {
                if (TextUtils.isEmpty(this.g)) {
                    throw new IllegalArgumentException("请设置net.bin识别资源");
                }
                if (TextUtils.isEmpty(this.f)) {
                    throw new IllegalArgumentException("请设置ngram.bin识别资源");
                }
            }
            if ((this.k.getType() & SemanticType.MIX_NAVI_DUI.getType()) == SemanticType.DUI.getType()) {
                if (TextUtils.isEmpty(this.semDUIResFloder)) {
                    throw new IllegalArgumentException("请设置离线DUI语义资源文件名路径");
                }
                if (TextUtils.isEmpty(this.semDUILuaResFloder)) {
                    throw new IllegalArgumentException("请设置离线DUI语义内置lua资源文件路径");
                }
            }
            if ((this.k.getType() & SemanticType.MIX_NAVI_DUI.getType()) == SemanticType.NAVI.getType()) {
                if (TextUtils.isEmpty(this.d)) {
                    throw new IllegalArgumentException("请设置离线导航语义资源文件名路径");
                }
                if (TextUtils.isEmpty(this.e)) {
                    throw new IllegalArgumentException("请设置离线导航语义内置lua资源文件路径");
                }
            }
            return new AILocalSemanticConfig(this, (byte) 0);
        }
    }
}
