package com.aispeech.export.config;

import android.text.TextUtils;
/* loaded from: classes.dex */
public class AIOneshotConfig {
    private String a;
    private int b;
    private int c;
    private String[] d;

    /* synthetic */ AIOneshotConfig(Builder builder, byte b) {
        this(builder);
    }

    public String getResBin() {
        return this.a;
    }

    public int getCacheAudioTime() {
        return this.b;
    }

    public int getMiddleTime() {
        return this.c;
    }

    public String[] getWords() {
        return this.d;
    }

    private AIOneshotConfig(String str, int i, int i2, String[] strArr) {
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = strArr;
    }

    private AIOneshotConfig(Builder builder) {
        this(builder.getResBin(), builder.getCacheAudioTime(), builder.getMiddleTime(), builder.getWords());
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String a;
        private int b = 1200;
        private int c = 600;
        private String[] d;

        public String getResBin() {
            return this.a;
        }

        public Builder setResBin(String str) {
            this.a = str;
            return this;
        }

        public int getCacheAudioTime() {
            return this.b;
        }

        public Builder setCacheAudioTime(int i) {
            this.b = i;
            return this;
        }

        public int getMiddleTime() {
            return this.c;
        }

        public Builder setMiddleTime(int i) {
            this.c = i;
            return this;
        }

        public String[] getWords() {
            return this.d;
        }

        public Builder setWords(String[] strArr) {
            this.d = strArr;
            return this;
        }

        public AIOneshotConfig create() {
            if (TextUtils.isEmpty(getResBin())) {
                throw new IllegalArgumentException("pls set vad res bin path");
            }
            String[] strArr = this.d;
            if (strArr == null || strArr.length == 0) {
                throw new IllegalArgumentException("pls set oneshot words");
            }
            return new AIOneshotConfig(this, (byte) 0);
        }
    }
}
