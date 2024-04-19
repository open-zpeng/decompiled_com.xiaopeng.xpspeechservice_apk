package com.aispeech.common;

import com.aispeech.kernel.Utils;
/* loaded from: classes.dex */
public class PcmToOgg {
    public static final String TAG = "PcmToOgg";
    private Utils a = new Utils();
    private long b;

    public synchronized void initEncode(Utils.speex_callback speex_callbackVar) {
        Log.d(TAG, "encode init");
        if (this.a != null) {
            this.b = this.a.initEncode(speex_callbackVar);
        }
        Log.d(TAG, "encode id:" + this.b);
    }

    public synchronized void startEncode(int i, int i2, int i3, int i4) {
        Log.d(TAG, "encode start");
        Log.d(TAG, "encode id:" + this.b);
        if (this.a != null) {
            this.a.startEncode(this.b, i, i2, i3, i4);
        }
    }

    public synchronized void feedData(byte[] bArr, int i) {
        if (this.a != null) {
            this.a.feedEncode(this.b, bArr, i);
        }
    }

    public synchronized void stopEncode() {
        if (this.a != null) {
            Log.d(TAG, "encode stop before");
            this.a.stopEncode(this.b);
            Log.d(TAG, "encode stop after");
        }
    }

    public synchronized void destroyEncode() {
        if (this.a != null) {
            Log.d(TAG, "encode destroy before");
            this.a.destroyEncode(this.b);
            Log.d(TAG, "encode destroy after");
        }
    }
}
