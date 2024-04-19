package com.aispeech.export.config;
/* loaded from: classes.dex */
public class RecorderConfig {
    public static final int TYPE_COMMON_ECHO = 4;
    public static final int TYPE_COMMON_MIC = 0;
    public int audioSource;
    public int intervalTime;
    public int recorderType;

    /* synthetic */ RecorderConfig(Builder builder, byte b) {
        this(builder);
    }

    public int getIntervalTime() {
        return this.intervalTime;
    }

    public int getAudioSource() {
        return this.audioSource;
    }

    public int getRecorderType() {
        return this.recorderType;
    }

    private RecorderConfig(int i, int i2, int i3) {
        this.intervalTime = i;
        this.audioSource = i2;
        this.recorderType = i3;
    }

    private RecorderConfig(Builder builder) {
        this(builder.intervalTime, builder.audioSource, builder.recorderType);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        public int intervalTime = 100;
        public int audioSource = 1;
        public int recorderType = 0;

        public Builder setIntervalTime(int i) {
            this.intervalTime = i;
            return this;
        }

        public Builder setAudioSource(int i) {
            this.audioSource = i;
            return this;
        }

        public Builder setRecorderType(int i) {
            this.recorderType = i;
            return this;
        }

        public RecorderConfig create() {
            return new RecorderConfig(this, (byte) 0);
        }
    }
}
