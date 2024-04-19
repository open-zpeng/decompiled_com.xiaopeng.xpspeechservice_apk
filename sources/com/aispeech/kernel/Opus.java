package com.aispeech.kernel;

import com.aispeech.common.Log;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Opus extends Abs {
    public static final int OPUS_TO_PCM = 0;
    public static final int PCM_TO_OPUS = 1;
    private static boolean b;
    private long c;
    private int d = 0;

    public static native int opus_decode_dec2(long j, byte[] bArr, byte[] bArr2);

    public static native int opus_decode_del2(long j);

    public static native int opus_decode_delete(long j);

    public static native int opus_decode_feed(long j, byte[] bArr);

    public static native long opus_decode_new(int i, opus_callback opus_callbackVar);

    public static native long opus_decode_new2(int i, String str);

    public static native int opus_decode_start(long j, String str);

    public static native int opus_decode_stop(long j);

    public static native int opus_encode_del2(long j);

    public static native int opus_encode_delete(long j);

    public static native int opus_encode_enc2(long j, byte[] bArr, byte[] bArr2);

    public static native int opus_encode_feed(long j, byte[] bArr);

    public static native long opus_encode_new(int i, opus_callback opus_callbackVar);

    public static native long opus_encode_new2(int i, String str);

    public static native int opus_encode_start(long j, String str);

    public static native int opus_encode_stop(long j);

    static {
        try {
            Log.d("Opus", "before load opusogg library");
            System.loadLibrary("opusogg");
            Log.d("Opus", "after load opusogg library");
            b = true;
        } catch (UnsatisfiedLinkError e) {
            b = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libopusogg.so, and put it in your libs dir!");
        }
    }

    public static boolean isSoValid() {
        return b;
    }

    public long init(boolean z, opus_callback opus_callbackVar) {
        this.c = opus_encode_new(!z ? 1 : 0, opus_callbackVar);
        Log.d("Opus", "init:" + this.c);
        return this.c;
    }

    public int start(int i, int i2, int i3, int i4, int i5) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("channels", i);
            jSONObject.put("samplerate", i2);
            jSONObject.put("bitrate", i3);
            jSONObject.put("complexity", i4);
            jSONObject.put("framesize", i5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("Opus", "start:" + this.c);
        int opus_encode_start = opus_encode_start(this.c, jSONObject.toString());
        if (opus_encode_start < 0) {
            Log.e("Opus", "start failed! Error code: " + opus_encode_start);
            return -1;
        }
        Log.d("Opus", "start ret:" + opus_encode_start);
        return opus_encode_start;
    }

    public int feed(byte[] bArr) {
        return opus_encode_feed(this.c, bArr);
    }

    public int stop() {
        Log.d("Opus", "stop:" + this.c);
        return opus_encode_stop(this.c);
    }

    public void destroy() {
        Log.d("Opus", "destroy:" + this.c);
        opus_encode_delete(this.c);
        Log.d("Opus", "destroy finished:" + this.c);
        this.c = 0L;
    }

    /* loaded from: classes.dex */
    public static class opus_callback {
        public int enc(long j, byte[] bArr, long j2) {
            return 0;
        }

        public int dec(long j, byte[] bArr, long j2) {
            return 0;
        }
    }

    public long ddsInit(int i, String str) {
        return ddsInit(false, i, str);
    }

    public long ddsInit(boolean z, int i, String str) {
        if (!isSoValid()) {
            android.util.Log.e("Opus", "load libopusogg library error! ddsInit -> return!");
            return 0L;
        }
        int i2 = !z ? 1 : 0;
        this.d = i;
        int i3 = this.d;
        if (i3 != 0) {
            if (i3 == 1) {
                this.a = opus_encode_new2(i2, str);
            }
        } else {
            this.a = opus_decode_new2(i2, str);
        }
        new StringBuilder("ddsInit result = ").append(this.a);
        return this.a;
    }

    public int ddsFeed(byte[] bArr, int i, byte[] bArr2) {
        if (!a("ddsFeed")) {
            return -1;
        }
        int i2 = this.d;
        if (i2 != 0) {
            if (i2 != 1) {
                return 0;
            }
            return opus_encode_enc2(this.a, bArr, bArr2);
        }
        return opus_decode_dec2(this.a, bArr, bArr2);
    }

    public void ddsDestroy() {
        if (!a("ddsDestroy")) {
            return;
        }
        new StringBuilder("ddsDestroy..., type = ").append(this.d);
        int i = this.d;
        if (i == 0) {
            opus_decode_del2(this.a);
        } else if (i == 1) {
            opus_encode_del2(this.a);
        }
    }
}
