package com.aispeech.kernel;

import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class NR {
    private static boolean a;
    private long b;

    public static native int dds_nr_delete(long j);

    public static native int dds_nr_feed(long j, byte[] bArr, int i);

    public static native long dds_nr_new(String str, nr_callback nr_callbackVar);

    public static native int dds_nr_start(long j, String str);

    public static native int dds_nr_stop(long j);

    static {
        a = false;
        try {
            Log.d("NR", "before load nr library");
            System.loadLibrary("nr");
            Log.d("NR", "after load nr library");
            a = true;
        } catch (UnsatisfiedLinkError e) {
            a = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libnr.so, and put it in your libs dir!");
        }
    }

    public static boolean isNrSoValid() {
        return a;
    }

    public long init(String str, nr_callback nr_callbackVar) {
        this.b = dds_nr_new(str, nr_callbackVar);
        Log.d("NR", "AIEngine.new():" + this.b);
        return this.b;
    }

    public int start(String str) {
        Log.d("NR", "AIEngine.start():" + this.b);
        int dds_nr_start = dds_nr_start(this.b, str);
        if (dds_nr_start < 0) {
            Log.e("NR", "AIEngine.start() failed! Error code: " + dds_nr_start);
            return -1;
        }
        return dds_nr_start;
    }

    public int feed(byte[] bArr) {
        return dds_nr_feed(this.b, bArr, bArr.length);
    }

    public int stop() {
        Log.d("NR", "AIEngine.stop():" + this.b);
        return dds_nr_stop(this.b);
    }

    public void destroy() {
        Log.d("NR", "AIEngine.delete():" + this.b);
        dds_nr_delete(this.b);
        Log.d("NR", "AIEngine.delete() finished:" + this.b);
        this.b = 0L;
    }

    /* loaded from: classes.dex */
    public static class nr_callback {
        public static byte[] bufferData = new byte[3200];

        public int run(int i, byte[] bArr, int i2) {
            return 0;
        }

        public static byte[] getBufferData() {
            return bufferData;
        }
    }
}
