package com.aispeech.lite.tts;

import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class MP3Decoder {
    public static native int decode(byte[] bArr, int i, short[] sArr, short[] sArr2);

    public static native void destroy();

    public static native void init();

    static {
        try {
            System.loadLibrary("lame");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MP3Decoder", "Please check useful liblame.so, and put it in your libs dir!");
        }
    }

    public synchronized void initDecoder() {
        Log.d("MP3Decoder", "init");
        init();
    }

    public synchronized int processDecode(byte[] bArr, int i, short[] sArr, short[] sArr2) {
        Log.d("MP3Decoder", "decode");
        return decode(bArr, i, sArr, sArr2);
    }

    public synchronized void release() {
        Log.d("MP3Decoder", "release");
        destroy();
    }
}
