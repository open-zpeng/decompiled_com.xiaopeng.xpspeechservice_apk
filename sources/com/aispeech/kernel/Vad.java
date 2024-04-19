package com.aispeech.kernel;

import android.text.TextUtils;
import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class Vad {
    private static boolean a;
    private long b;

    public static native int dds_vad_cancel(long j);

    public static native int dds_vad_delete(long j);

    public static native int dds_vad_feed(long j, byte[] bArr, int i);

    public static native long dds_vad_new(String str, vad_callback vad_callbackVar);

    public static native int dds_vad_start(long j, String str);

    public static native int dds_vad_stop(long j);

    static {
        a = false;
        try {
            Log.d("Vad", "before load vad library");
            System.loadLibrary("vad");
            Log.d("Vad", "after load vad library");
            a = true;
        } catch (UnsatisfiedLinkError e) {
            a = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libvad.so, and put it in your libs dir!");
        }
    }

    public static boolean isVadSoValid() {
        return a;
    }

    public long init(String str, vad_callback vad_callbackVar) {
        this.b = dds_vad_new(str, vad_callbackVar);
        Log.d("Vad", "AIEngine.new():" + this.b);
        return this.b;
    }

    public int start(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Log.d("Vad", "AIEngine.start():" + this.b);
        int dds_vad_start = dds_vad_start(this.b, str);
        if (dds_vad_start < 0) {
            Log.e("Vad", "AIEngine.start() failed! Error code: " + dds_vad_start);
            return -1;
        }
        return dds_vad_start;
    }

    public int feed(byte[] bArr) {
        return dds_vad_feed(this.b, bArr, bArr.length);
    }

    public int stop() {
        Log.d("Vad", "AIEngine.stop():" + this.b);
        return dds_vad_stop(this.b);
    }

    public int cancel() {
        Log.d("Vad", "AIEngine.cancel():" + this.b);
        return dds_vad_cancel(this.b);
    }

    public void destroy() {
        Log.d("Vad", "AIEngine.delete():" + this.b);
        dds_vad_delete(this.b);
        Log.d("Vad", "AIEngine.delete() finished:" + this.b);
        this.b = 0L;
    }

    /* loaded from: classes.dex */
    public static class vad_callback {
        public static byte[] bufferData = new byte[3200];

        public int run(int i, byte[] bArr, int i2) {
            return 0;
        }

        public static byte[] getBufferData() {
            return bufferData;
        }
    }
}
