package com.aispeech;

import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class AIAudioRecord {
    public static final String TAG = "JNI-AIAudioRecord";

    public final native int _native_read_in_byte_array(byte[] bArr, int i, int i2);

    public final native int _native_setup(int i, int i2, int i3);

    public final native int _native_start();

    public final native int _native_stop();

    static {
        try {
            Log.d(TAG, "before load aispeechaudio library");
            System.loadLibrary("aispeechaudio");
            Log.d(TAG, "after load aispeechaudio library");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libaispeechaudio.so, and put it in your libs dir!");
        }
    }
}
