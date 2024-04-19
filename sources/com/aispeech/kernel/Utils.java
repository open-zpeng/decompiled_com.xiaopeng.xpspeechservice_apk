package com.aispeech.kernel;

import com.aispeech.common.Log;
import com.aispeech.lite.AISampleRate;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Utils {
    private static boolean a;
    private long b;

    public static native String get_recordid();

    public static native String get_version();

    public static native int jni_duilite_set_thread_affinity(int i);

    public static native int speex_encode_delete(long j);

    public static native int speex_encode_feed(long j, byte[] bArr, int i);

    public static native long speex_encode_new(speex_callback speex_callbackVar);

    public static native int speex_encode_start(long j, String str);

    public static native int speex_encode_stop(long j);

    static {
        a = false;
        try {
            Log.d("Utils", "before load duiutils library");
            System.loadLibrary("duiutils");
            Log.d("Utils", "after load duiutils library");
            a = true;
        } catch (UnsatisfiedLinkError e) {
            a = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libduiutils.so, and put it in your libs dir!");
        }
    }

    public static boolean isUtilsSoValid() {
        return a;
    }

    public long getSpeexEncodeId() {
        return this.b;
    }

    public long initEncode(speex_callback speex_callbackVar) {
        this.b = speex_encode_new(speex_callbackVar);
        Log.d("Utils", "speex_encode_new():" + this.b);
        return this.b;
    }

    public int startEncode(long j, int i, int i2, int i3, int i4) {
        Log.d("Utils", "speex_encode_start():" + j);
        Log.d("Utils", "params: " + j + " " + i + " " + i2 + " " + i3 + " " + i4);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("quality", i);
            jSONObject.put(AISampleRate.KEY_SAMPLE_RATE, i2);
            jSONObject.put("vbr", i3);
            jSONObject.put("complexity", i4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int speex_encode_start = speex_encode_start(j, jSONObject.toString());
        Log.d("Utils", "speex encode start end" + speex_encode_start);
        if (speex_encode_start < 0) {
            Log.e("Utils", "speex_encode_start() failed! Error code: " + speex_encode_start);
            return -1;
        }
        return speex_encode_start;
    }

    public int feedEncode(long j, byte[] bArr, int i) {
        return speex_encode_feed(j, bArr, bArr.length);
    }

    public int stopEncode(long j) {
        Log.d("Utils", "speex_encode_stop():" + j);
        return speex_encode_stop(j);
    }

    public int destroyEncode(long j) {
        Log.d("Utils", "speex_encode_cancel():" + j);
        return speex_encode_delete(j);
    }

    /* loaded from: classes.dex */
    public static class speex_callback {
        public static byte[] bufferData = new byte[3200];

        public int run(int i, byte[] bArr, int i2) {
            return 0;
        }

        public static byte[] getBufferData() {
            return bufferData;
        }
    }
}
