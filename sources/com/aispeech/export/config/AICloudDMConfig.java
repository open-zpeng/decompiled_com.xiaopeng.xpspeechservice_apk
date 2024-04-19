package com.aispeech.export.config;

import android.text.TextUtils;
import com.aispeech.lite.c.c;
/* loaded from: classes.dex */
public class AICloudDMConfig {
    private int a;
    private boolean b;
    private String c;
    private String d;
    private boolean e;
    private boolean f;
    private String g;
    private String h;
    private boolean i;
    private boolean j;
    private String[] k;
    private String[] l;

    /* synthetic */ AICloudDMConfig(Builder builder, byte b) {
        this(builder);
    }

    private AICloudDMConfig(int i, boolean z, String str, boolean z2, boolean z3, String str2, String str3, boolean z4, boolean z5, String str4, String[] strArr, String[] strArr2) {
        this.i = false;
        this.a = i;
        this.b = z;
        this.c = str;
        this.d = str4;
        this.e = z2;
        this.f = z3;
        this.g = str2;
        this.h = str3;
        this.i = z4;
        this.j = z5;
        this.k = strArr;
        this.l = strArr2;
    }

    private AICloudDMConfig(Builder builder) {
        this(builder.a, builder.b, builder.c, builder.e, builder.f, builder.g, builder.h, builder.i, builder.j, builder.d, builder.k, builder.l);
    }

    public int getNativeApiTimeout() {
        return this.a;
    }

    public boolean isUseCustomFeed() {
        return this.b;
    }

    public String getServerAddress() {
        return this.c;
    }

    public String getCInfoServerAddress() {
        return this.d;
    }

    public boolean isRoute() {
        return this.e;
    }

    public boolean isUseVad() {
        return this.f;
    }

    public String getVadRes() {
        return this.g;
    }

    public String getAliasKey() {
        return this.h;
    }

    public boolean isUseFullDuplex() {
        return this.i;
    }

    public boolean isUseRefText() {
        return this.j;
    }

    public String[] getKeys() {
        return this.k;
    }

    public String[] getValues() {
        return this.l;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        int a = 10000;
        boolean b = false;
        String c = c.a;
        String d = c.b;
        boolean e = false;
        boolean f = false;
        String g = "";
        String h = "prod";
        boolean i = false;
        private boolean j = false;
        private String[] k;
        private String[] l;

        public Builder setUseRefText(boolean z) {
            this.j = z;
            return this;
        }

        public Builder setNativeApiTimeout(int i) {
            this.a = i;
            return this;
        }

        public Builder setUseCustomFeed(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setServerAddress(String str) {
            this.c = str;
            return this;
        }

        public Builder setCInfoServerAddress(String str) {
            this.d = str;
            return this;
        }

        public Builder setRoute(boolean z) {
            this.e = z;
            return this;
        }

        public Builder setUseVad(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setVadRes(String str) {
            this.g = str;
            return this;
        }

        public Builder setAliasKey(String str) {
            this.h = str;
            return this;
        }

        public Builder setUseFullDuplex(boolean z) {
            this.i = z;
            return this;
        }

        public Builder setCustomParams(String[] strArr, String[] strArr2) {
            this.k = strArr;
            this.l = strArr2;
            return this;
        }

        public AICloudDMConfig build() {
            if (this.f && TextUtils.isEmpty(this.g)) {
                throw new IllegalArgumentException("请设置vad资源!");
            }
            if (!TextUtils.isEmpty(this.c) && this.c.startsWith("ws")) {
                return new AICloudDMConfig(this, (byte) 0);
            }
            throw new IllegalArgumentException("非法的服务地址!");
        }
    }
}
