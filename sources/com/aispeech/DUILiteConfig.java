package com.aispeech;

import com.aispeech.export.config.AuthConfig;
import com.aispeech.export.config.EchoConfig;
import com.aispeech.export.config.RecorderConfig;
import com.aispeech.export.config.UploadConfig;
/* loaded from: classes.dex */
public class DUILiteConfig {
    public static final int TYPE_COMMON_CIRCLE4 = 6;
    public static final int TYPE_COMMON_CIRCLE6 = 3;
    public static final int TYPE_COMMON_DUAL = 1;
    public static final int TYPE_COMMON_ECHO = 4;
    public static final int TYPE_COMMON_FESPCAR = 5;
    public static final int TYPE_COMMON_LINE4 = 2;
    public static final int TYPE_COMMON_LINE6 = 10;
    public static final int TYPE_COMMON_MIC = 0;
    public static final int TYPE_COMMON_SHAPE_L4 = 11;
    public static final int TYPE_TINYCAP_CIRCLE4 = 9;
    public static final int TYPE_TINYCAP_DUAL = 7;
    public static final int TYPE_TINYCAP_LINE4 = 8;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private EchoConfig g;
    private RecorderConfig h;
    private UploadConfig i;
    private AuthConfig j;
    private String k;
    private int l;

    /* synthetic */ DUILiteConfig(Builder builder, byte b) {
        this(builder);
    }

    public String getApiKey() {
        return this.a;
    }

    public String getServerApiKey() {
        return this.b;
    }

    public String getProductId() {
        return this.c;
    }

    public String getProductKey() {
        return this.d;
    }

    public String getProductSecret() {
        return this.e;
    }

    public EchoConfig getEchoConfig() {
        return this.g;
    }

    public RecorderConfig getRecorderConfig() {
        return this.h;
    }

    public UploadConfig getUploadConfig() {
        return this.i;
    }

    public AuthConfig getAuthConfig() {
        return this.j;
    }

    public String getTtsCacheDir() {
        return this.k;
    }

    public int getThreadAffinity() {
        return this.l;
    }

    public boolean getCallbackInThread() {
        return this.f;
    }

    private DUILiteConfig(String str, String str2, String str3, String str4, String str5, boolean z, EchoConfig echoConfig, RecorderConfig recorderConfig, UploadConfig uploadConfig, AuthConfig authConfig, String str6, int i) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = z;
        this.g = echoConfig;
        this.h = recorderConfig;
        this.i = uploadConfig;
        this.j = authConfig;
        this.k = str6;
        this.l = i;
    }

    private DUILiteConfig(Builder builder) {
        this(builder.a, builder.b, builder.c, builder.d, builder.e, builder.f, builder.g, builder.h, builder.i, builder.j, builder.k, builder.l);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private boolean f;
        private EchoConfig g;
        private RecorderConfig h;
        private UploadConfig i;
        private AuthConfig j;
        private String k;
        private int l = 0;

        public Builder setApiKey(String str) {
            this.a = str;
            return this;
        }

        public Builder setServerApiKey(String str) {
            this.b = str;
            return this;
        }

        public Builder setProductId(String str) {
            this.c = str;
            return this;
        }

        public Builder setProductKey(String str) {
            this.d = str;
            return this;
        }

        public Builder setProductSecret(String str) {
            this.e = str;
            return this;
        }

        public Builder setCallBackInThread(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setEchoConfig(EchoConfig echoConfig) {
            this.g = echoConfig;
            return this;
        }

        public Builder setRecorderConfig(RecorderConfig recorderConfig) {
            this.h = recorderConfig;
            return this;
        }

        public Builder setUploadConfig(UploadConfig uploadConfig) {
            this.i = uploadConfig;
            return this;
        }

        public Builder setAuthConfig(AuthConfig authConfig) {
            this.j = authConfig;
            return this;
        }

        public Builder setTtsCacheDir(String str) {
            this.k = str;
            return this;
        }

        public Builder setThreadAffinity(int i) {
            this.l = i;
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x003f  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0069  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.aispeech.DUILiteConfig create() {
            /*
                r7 = this;
                java.lang.String r0 = r7.c
                java.lang.String r1 = r7.a
                java.lang.String r2 = r7.d
                java.lang.String r3 = r7.e
                boolean r4 = android.text.TextUtils.isEmpty(r0)
                r5 = 1
                r6 = 0
                if (r4 != 0) goto L25
                boolean r4 = android.text.TextUtils.isEmpty(r1)
                if (r4 != 0) goto L25
                boolean r2 = android.text.TextUtils.isEmpty(r2)
                if (r2 != 0) goto L25
                boolean r2 = android.text.TextUtils.isEmpty(r3)
                if (r2 != 0) goto L25
                r2 = r5
                goto L26
            L25:
                r2 = r6
            L26:
                if (r2 != 0) goto L3c
                boolean r0 = android.text.TextUtils.isEmpty(r0)
                if (r0 != 0) goto L36
                boolean r0 = android.text.TextUtils.isEmpty(r1)
                if (r0 != 0) goto L36
                r0 = r5
                goto L37
            L36:
                r0 = r6
            L37:
                if (r0 == 0) goto L3a
                goto L3c
            L3a:
                r0 = r6
                goto L3d
            L3c:
                r0 = r5
            L3d:
                if (r0 == 0) goto L69
                com.aispeech.export.config.EchoConfig r0 = r7.g
                com.aispeech.export.config.RecorderConfig r1 = r7.h
                if (r1 == 0) goto L58
                int r1 = r1.recorderType
                r2 = 4
                if (r1 != r2) goto L58
                if (r0 == 0) goto L56
                java.lang.String r0 = r0.getAecResource()
                boolean r0 = android.text.TextUtils.isEmpty(r0)
                if (r0 == 0) goto L58
            L56:
                r5 = r6
                goto L59
            L58:
            L59:
                if (r5 == 0) goto L61
                com.aispeech.DUILiteConfig r0 = new com.aispeech.DUILiteConfig
                r0.<init>(r7, r6)
                return r0
            L61:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r1 = "AIEchoConfig cannot be null, pls use DUILiteConfig.setEchoConfig() to set"
                r0.<init>(r1)
                throw r0
            L69:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r1 = "ProductInfo set invalid, at least one in productId|productKey|productSecret|apiKey is empty"
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.aispeech.DUILiteConfig.Builder.create():com.aispeech.DUILiteConfig");
        }
    }
}
