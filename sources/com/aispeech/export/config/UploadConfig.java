package com.aispeech.export.config;
/* loaded from: classes.dex */
public class UploadConfig {
    public static final int UPLOAD_AUDIO_LEVEL_ALL = 17;
    public static final int UPLOAD_AUDIO_LEVEL_NONE = 0;
    public static final byte UPLOAD_AUDIO_LEVEL_PREWAKEUP = 1;
    public static final int UPLOAD_AUDIO_LEVEL_WAKEUP = 16;
    private int a;
    private int b;
    private String c;
    private int d;
    private String e;
    private boolean f;

    /* synthetic */ UploadConfig(Builder builder, byte b) {
        this(builder);
    }

    public int getCacheUploadMaxNumber() {
        return this.a;
    }

    public int getUploadAudioLevel() {
        return this.b;
    }

    public String getUploadAudioPath() {
        return this.c;
    }

    public int getUploadAudioDelayTime() {
        return this.d;
    }

    public String getUploadUrl() {
        return this.e;
    }

    public boolean isUploadEnable() {
        return this.f;
    }

    private UploadConfig(boolean z, int i, int i2, String str, int i3, String str2) {
        this.f = z;
        this.a = i;
        this.b = i2;
        this.c = str;
        this.d = i3;
        this.e = str2;
    }

    private UploadConfig(Builder builder) {
        this(builder.a, builder.b, builder.c, builder.d, builder.e, builder.f);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private boolean a = true;
        private int b = 100;
        private int c = 0;
        private String d = null;
        private int e = 300000;
        private String f = "https://log.aispeech.com";

        public Builder setUploadEnable(boolean z) {
            this.a = z;
            return this;
        }

        public Builder setCacheUploadMaxNumber(int i) {
            this.b = i;
            return this;
        }

        public Builder setUploadAudioLevel(int i) {
            this.c = i;
            return this;
        }

        public Builder setUploadAudioPath(String str) {
            this.d = str;
            return this;
        }

        public Builder setUploadAudioDelayTime(int i) {
            this.e = i;
            return this;
        }

        public Builder setUploadUrl(String str) {
            this.f = str;
            return this;
        }

        public UploadConfig create() {
            return new UploadConfig(this, (byte) 0);
        }
    }
}
