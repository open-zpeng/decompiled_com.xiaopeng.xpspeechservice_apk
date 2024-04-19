package com.aispeech.export.config;
/* loaded from: classes.dex */
public class AICloudVoiceCopyConfig {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;

    /* synthetic */ AICloudVoiceCopyConfig(Builder builder, byte b) {
        this(builder);
    }

    private AICloudVoiceCopyConfig(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
    }

    public String getProductId() {
        return this.a;
    }

    public String getApiKey() {
        return this.b;
    }

    public String getHost() {
        return this.c;
    }

    public String getToken() {
        return this.d;
    }

    public String getRememberToken() {
        return this.e;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;

        public Builder setProductId(String str) {
            this.a = str;
            return this;
        }

        public Builder setApiKey(String str) {
            this.b = str;
            return this;
        }

        public Builder setHost(String str) {
            this.c = str;
            return this;
        }

        public Builder setRememberToken(String str) {
            this.e = str;
            return this;
        }

        public Builder setToken(String str) {
            this.d = str;
            return this;
        }

        public AICloudVoiceCopyConfig create() {
            return new AICloudVoiceCopyConfig(this, (byte) 0);
        }
    }
}
