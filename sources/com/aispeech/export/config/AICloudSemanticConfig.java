package com.aispeech.export.config;

import android.text.TextUtils;
/* loaded from: classes.dex */
public class AICloudSemanticConfig {
    private boolean a;
    private String b;
    private boolean c;
    private String d;
    private String e;
    private boolean f;

    /* synthetic */ AICloudSemanticConfig(Builder builder, byte b) {
        this(builder);
    }

    public boolean isUseCustomFeed() {
        return this.a;
    }

    public String getServerAddress() {
        return this.b;
    }

    public boolean isUseVad() {
        return this.c;
    }

    public String getVadRes() {
        return this.d;
    }

    public String getAliasKey() {
        return this.e;
    }

    public boolean isUseRefText() {
        return this.f;
    }

    private AICloudSemanticConfig(boolean z, boolean z2, String str, boolean z3, String str2, String str3) {
        this.f = z;
        this.a = z2;
        this.b = str;
        this.c = z3;
        this.d = str2;
        this.e = str3;
    }

    private AICloudSemanticConfig(Builder builder) {
        this(builder.a, builder.b, builder.c, builder.d, builder.e, builder.f);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private boolean a = false;
        private boolean b = false;
        private String c = "wss://dds.dui.ai/dds/v2/";
        private boolean d = true;
        private String e = "";
        private String f = "prod";

        public Builder setServerAddress(String str) {
            this.c = str;
            return this;
        }

        public Builder setUseVad(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setVadRes(String str) {
            this.e = str;
            return this;
        }

        public Builder setAliasKey(String str) {
            this.f = str;
            return this;
        }

        public Builder setUseRefText(boolean z) {
            this.a = z;
            return this;
        }

        public Builder setUseCustomFeed(boolean z) {
            this.b = z;
            return this;
        }

        public AICloudSemanticConfig build() {
            if (this.d && TextUtils.isEmpty(this.e)) {
                throw new IllegalArgumentException("请设置vad资源!");
            }
            if (!TextUtils.isEmpty(this.c) && this.c.startsWith("ws")) {
                return new AICloudSemanticConfig(this, (byte) 0);
            }
            throw new IllegalArgumentException("非法的服务地址!");
        }
    }
}
