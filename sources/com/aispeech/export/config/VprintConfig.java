package com.aispeech.export.config;

import android.text.TextUtils;
/* loaded from: classes.dex */
public class VprintConfig {
    private String a;
    private String b;
    private String c;
    private boolean d;
    private String e;

    /* synthetic */ VprintConfig(Builder builder, byte b) {
        this(builder);
    }

    private VprintConfig(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        if (TextUtils.isEmpty(this.a)) {
            throw new IllegalArgumentException("Vprint config is invalid, lost vprintResBin");
        }
        if (TextUtils.isEmpty(this.b)) {
            throw new IllegalArgumentException("Vprint config is invalid, lost asrppResBin");
        }
        if (!this.d && TextUtils.isEmpty(this.c)) {
            throw new IllegalArgumentException("Vprint config is invalid, lost vprintModelPath");
        }
        if (this.d && TextUtils.isEmpty(this.e)) {
            throw new IllegalArgumentException("Vprint config is invalid, lost vprintDatabasePath");
        }
    }

    public String getVprintResBin() {
        return this.a;
    }

    public String getAsrppResBin() {
        return this.b;
    }

    public String getVprintModelPath() {
        return this.c;
    }

    public boolean isUseDatabaseStorage() {
        return this.d;
    }

    public String getVprintDatabasePath() {
        return this.e;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String a;
        private String b;
        private String c;
        private boolean d = false;
        private String e = null;

        public Builder setVprintResBin(String str) {
            this.a = str;
            return this;
        }

        public Builder setAsrppResBin(String str) {
            this.b = str;
            return this;
        }

        public Builder setVprintModelPath(String str) {
            this.c = str;
            return this;
        }

        public Builder setUseDatabaseStorage(boolean z, String str) {
            this.d = z;
            this.e = str;
            return this;
        }

        public VprintConfig create() {
            return new VprintConfig(this, (byte) 0);
        }
    }
}
