package com.aispeech.kernel;

import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class Asrpp {
    private static boolean a;
    private long b;

    /* loaded from: classes.dex */
    public interface asrpp_callback {
        int run(int i, byte[] bArr, int i2);
    }

    public static native int dds_asrpp_cancel(long j);

    public static native int dds_asrpp_delete(long j);

    public static native int dds_asrpp_feed(long j, byte[] bArr, int i);

    public static native long dds_asrpp_new(String str, asrpp_callback asrpp_callbackVar);

    public static native int dds_asrpp_start(long j, String str);

    public static native int dds_asrpp_stop(long j);

    static {
        a = false;
        try {
            Log.d("Asrpp", "before load Asrpp library");
            System.loadLibrary("asrpp");
            Log.d("Asrpp", "after load Asrpp library");
            a = true;
        } catch (UnsatisfiedLinkError e) {
            a = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libasrpp.so, and put it in your libs dir!");
        }
    }

    public static boolean isAsrppSoValid() {
        return a;
    }

    public long init(String str, asrpp_callback asrpp_callbackVar) {
        this.b = dds_asrpp_new(str, asrpp_callbackVar);
        Log.d("Asrpp", "AIEngine.new():" + this.b);
        return this.b;
    }

    public int start(String str) {
        Log.d("Asrpp", "AIEngine.start():" + this.b);
        int dds_asrpp_start = dds_asrpp_start(this.b, str);
        if (dds_asrpp_start < 0) {
            Log.e("Asrpp", "AIEngine.start() failed! Error code: " + dds_asrpp_start);
            return -1;
        }
        return dds_asrpp_start;
    }

    public int feed(byte[] bArr, int i) {
        return dds_asrpp_feed(this.b, bArr, i);
    }

    public int stop() {
        Log.d("Asrpp", "AIEngine.stop():" + this.b);
        return dds_asrpp_stop(this.b);
    }

    public int cancel() {
        Log.d("Asrpp", "AIEngine.cancel():" + this.b);
        return dds_asrpp_cancel(this.b);
    }

    public void destroy() {
        Log.d("Asrpp", "AIEngine.delete():" + this.b);
        dds_asrpp_delete(this.b);
        Log.d("Asrpp", "AIEngine.delete() finished:" + this.b);
        this.b = 0L;
    }
}
