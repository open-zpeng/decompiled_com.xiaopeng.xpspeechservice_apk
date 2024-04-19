package com.aispeech.export.config;

import android.text.TextUtils;
import com.aispeech.lite.AuthType;
/* loaded from: classes.dex */
public class AuthConfig {
    private int a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private AuthType g;

    /* synthetic */ AuthConfig(Builder builder, byte b) {
        this(builder);
    }

    public AuthType getType() {
        return this.g;
    }

    public String getCustomDeviceName() {
        return this.f;
    }

    public int getAuthTimeout() {
        return this.a;
    }

    public String getAuthServer() {
        return this.b;
    }

    public String getDeviceProfileDirPath() {
        return this.c;
    }

    public String getCustomDeviceId() {
        return this.d;
    }

    public String getLicenceId() {
        return this.e;
    }

    private AuthConfig(AuthType authType, String str, String str2, int i, String str3, String str4, String str5) {
        this.a = 5000;
        this.b = "https://auth.dui.ai";
        this.g = authType;
        this.f = str;
        this.d = str2;
        this.a = i;
        this.b = str3;
        this.c = str4;
        this.e = str5;
    }

    private AuthConfig(Builder builder) {
        this(builder.g, builder.f, builder.a, builder.b, builder.c, builder.d, builder.e);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String a;
        private String d;
        private String e;
        private String f;
        private int b = 5000;
        private String c = "https://auth.dui.ai";
        private AuthType g = AuthType.ONLINE;

        public Builder setType(AuthType authType) {
            this.g = authType;
            return this;
        }

        @Deprecated
        public Builder setCustomDeviceId(String str) {
            this.a = str;
            return this;
        }

        public Builder setAuthTimeout(int i) {
            this.b = i;
            return this;
        }

        @Deprecated
        public Builder setAuthServer(String str) {
            this.c = str;
            return this;
        }

        public Builder setDeviceProfileDirPath(String str) {
            this.d = str;
            return this;
        }

        public Builder setLicenceId(String str) {
            this.e = str;
            return this;
        }

        public Builder setCustomDeviceName(String str) {
            this.f = str;
            return this;
        }

        public AuthConfig create() {
            check();
            return new AuthConfig(this, (byte) 0);
        }

        public void check() {
            if (this.g == AuthType.TRIAL) {
                if (TextUtils.isEmpty(this.d)) {
                    throw new IllegalArgumentException("offline auth must set deviceProfileDirPath.");
                }
            } else if (this.g == AuthType.OFFLINE) {
                if (TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.a)) {
                    throw new IllegalArgumentException("offline auth must set deviceProfileDirPath && customDeviceId .");
                }
            } else if (TextUtils.isEmpty(this.f)) {
                throw new IllegalArgumentException("online auth must set customDeviceName .");
            }
        }
    }
}
