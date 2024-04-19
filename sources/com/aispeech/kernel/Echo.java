package com.aispeech.kernel;

import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class Echo {
    private static boolean a;
    private long b;

    public static native int dds_echo_cancel(long j);

    public static native int dds_echo_delete(long j);

    public static native int dds_echo_feed(long j, byte[] bArr, int i);

    public static native long dds_echo_new(String str, echo_callback echo_callbackVar);

    public static native int dds_echo_set(long j, String str);

    public static native int dds_echo_setvoipcb(long j, echo_voip_callback echo_voip_callbackVar);

    public static native int dds_echo_start(long j, String str);

    public static native int dds_echo_stop(long j);

    static {
        a = false;
        try {
            Log.d("Echo", "before load echo library");
            System.loadLibrary("echo");
            Log.d("Echo", "after load echo library");
            a = true;
        } catch (UnsatisfiedLinkError e) {
            a = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libecho.so, and put it in your libs dir!");
        }
    }

    public static boolean isEchoSoValid() {
        return a;
    }

    public long init(String str, echo_callback echo_callbackVar) {
        this.b = dds_echo_new(str, echo_callbackVar);
        Log.d("Echo", "dds_echo_new():" + this.b);
        return this.b;
    }

    public int setCallback(echo_voip_callback echo_voip_callbackVar) {
        int dds_echo_setvoipcb = dds_echo_setvoipcb(this.b, echo_voip_callbackVar);
        Log.d("Echo", "setCallback() ret:" + dds_echo_setvoipcb);
        return dds_echo_setvoipcb;
    }

    public int start(String str) {
        Log.d("Echo", "dds_echo_start():" + this.b);
        int dds_echo_start = dds_echo_start(this.b, str);
        if (dds_echo_start < 0) {
            Log.e("Echo", "dds_echo_start() failed! Error code: " + dds_echo_start);
            return -1;
        }
        return dds_echo_start;
    }

    public int feed(byte[] bArr) {
        return dds_echo_feed(this.b, bArr, bArr.length);
    }

    public int set(String str) {
        int dds_echo_set = dds_echo_set(this.b, str);
        Log.d("Echo", "dds_echo_set() ret " + dds_echo_set);
        return dds_echo_set;
    }

    public int stop() {
        Log.d("Echo", "dds_echo_stop():" + this.b);
        return dds_echo_stop(this.b);
    }

    public int cancel() {
        Log.d("Echo", "dds_echo_cancel():" + this.b);
        return dds_echo_cancel(this.b);
    }

    public void destroy() {
        Log.d("Echo", "dds_echo_delete():" + this.b);
        dds_echo_delete(this.b);
        Log.d("Echo", "dds_echo_delete() finished:" + this.b);
        this.b = 0L;
    }

    /* loaded from: classes.dex */
    public static class echo_callback {
        public static byte[] bufferData = new byte[3200];

        public int run(int i, byte[] bArr, int i2) {
            return 0;
        }

        public static byte[] getBufferData() {
            return bufferData;
        }
    }

    /* loaded from: classes.dex */
    public static class echo_voip_callback {
        public static byte[] bufferData = new byte[3200];

        public int run(int i, byte[] bArr, int i2) {
            return 0;
        }

        public static byte[] getBufferData() {
            return bufferData;
        }
    }
}
