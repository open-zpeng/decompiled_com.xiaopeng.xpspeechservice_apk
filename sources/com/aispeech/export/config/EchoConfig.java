package com.aispeech.export.config;
/* loaded from: classes.dex */
public class EchoConfig {
    private String a;
    private int b;
    private int c;
    private int d;
    private String e;
    private boolean f;
    private int g;

    /* synthetic */ EchoConfig(Builder builder, byte b) {
        this(builder);
    }

    public boolean isMonitorEnable() {
        return this.f;
    }

    public int getMonitorPeriod() {
        return this.g;
    }

    public String getAecResource() {
        return this.a;
    }

    public int getChannels() {
        return this.b;
    }

    public int getMicNumber() {
        return this.c;
    }

    public int getRecChannel() {
        return this.d;
    }

    public String getSavedDirPath() {
        return this.e;
    }

    private EchoConfig(String str, int i, int i2, int i3, String str2, boolean z, int i4) {
        this.a = null;
        this.b = 2;
        this.c = 1;
        this.d = 1;
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = str2;
        this.f = z;
        this.g = i4;
    }

    private EchoConfig(Builder builder) {
        this(builder.a, builder.b, builder.c, builder.d, builder.e, builder.f, builder.g);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String a = null;
        private int b = 2;
        private int c = 1;
        private int d = 1;
        private String e = null;
        private boolean f = false;
        private int g = 200;

        public Builder setMonitorEnable(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setMonitorPeriod(int i) {
            this.g = i;
            return this;
        }

        public Builder setAecResource(String str) {
            this.a = str;
            return this;
        }

        public Builder setChannels(int i) {
            this.b = i;
            return this;
        }

        public Builder setMicNumber(int i) {
            this.c = i;
            return this;
        }

        public Builder setRecChannel(int i) {
            this.d = i;
            return this;
        }

        public Builder setSavedDirPath(String str) {
            this.e = str;
            return this;
        }

        public EchoConfig create() {
            return new EchoConfig(this, (byte) 0);
        }
    }
}
