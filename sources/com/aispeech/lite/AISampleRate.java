package com.aispeech.lite;

import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class AISampleRate {
    public static final String KEY_SAMPLE_RATE = "sampleRate";
    private int b;
    private static final String a = AISampleRate.class.getName();
    public static final AISampleRate SAMPLE_RATE_16K = new AISampleRate(16000);
    public static final AISampleRate SAMPLE_RATE_8K = new AISampleRate(8000);

    public static AISampleRate toAISampleRate(int i) {
        if (i == SAMPLE_RATE_16K.getValue()) {
            return SAMPLE_RATE_16K;
        }
        if (i == SAMPLE_RATE_8K.getValue()) {
            return SAMPLE_RATE_8K;
        }
        Log.w(a, "Unsupported sampleRate!");
        return null;
    }

    private AISampleRate(int i) {
        this.b = i;
    }

    public int getValue() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return false;
    }
}
