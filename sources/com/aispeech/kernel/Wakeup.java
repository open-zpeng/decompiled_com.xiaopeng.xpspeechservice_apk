package com.aispeech.kernel;

import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class Wakeup {
    private static boolean b;
    private long a;

    /* loaded from: classes.dex */
    public interface wakeup_callback {
        int run(int i, byte[] bArr, int i2);
    }

    public static native int dds_wakeup_cancel(long j);

    public static native int dds_wakeup_delete(long j);

    public static native int dds_wakeup_feed(long j, byte[] bArr, int i);

    public static native long dds_wakeup_new(String str, wakeup_callback wakeup_callbackVar);

    public static native int dds_wakeup_set(long j, String str);

    public static native int dds_wakeup_setvprintcutcb(long j, vprintcut_callback vprintcut_callbackVar);

    public static native int dds_wakeup_start(long j, String str);

    public static native int dds_wakeup_stop(long j);

    static {
        b = false;
        try {
            Log.d("Wakeup", "before load wakeup library");
            System.loadLibrary("wakeup");
            Log.d("Wakeup", "after load wakeup library");
            b = true;
        } catch (UnsatisfiedLinkError e) {
            b = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libwakeup.so, and put it in your libs dir!");
        }
    }

    public static boolean isWakeupSoValid() {
        return b;
    }

    public long initWakeup(String str, wakeup_callback wakeup_callbackVar) {
        Log.d("Wakeup", "dds_wakeup_new");
        this.a = dds_wakeup_new(str, wakeup_callbackVar);
        return this.a;
    }

    public int startWakeup(String str) {
        int dds_wakeup_start = dds_wakeup_start(this.a, str);
        if (dds_wakeup_start < 0) {
            Log.e("Wakeup", "dds_wakeup_start() failed! Error code: " + dds_wakeup_start);
            return -1;
        }
        return dds_wakeup_start;
    }

    public int setWakeup(String str) {
        Log.d("Wakeup", "dds_wakeup_set :" + str);
        return dds_wakeup_set(this.a, str);
    }

    public int setVprintcutcb(vprintcut_callback vprintcut_callbackVar) {
        int dds_wakeup_setvprintcutcb = dds_wakeup_setvprintcutcb(this.a, vprintcut_callbackVar);
        Log.d("Wakeup", "dds_wakeup_setvprintcutcb :" + dds_wakeup_setvprintcutcb);
        return dds_wakeup_setvprintcutcb;
    }

    public int feedWakeupData(byte[] bArr, int i) {
        return dds_wakeup_feed(this.a, bArr, i);
    }

    public int stopWakeup() {
        Log.d("Wakeup", "dds_wakeup_stop");
        return dds_wakeup_stop(this.a);
    }

    public int cancelWakeup() {
        Log.d("Wakeup", "dds_wakeup_cancel before");
        int dds_wakeup_cancel = dds_wakeup_cancel(this.a);
        Log.d("Wakeup", "dds_wakeup_cancel after");
        return dds_wakeup_cancel;
    }

    public int destroyWakeup() {
        Log.d("Wakeup", "dds_wakeup_delete before");
        int dds_wakeup_delete = dds_wakeup_delete(this.a);
        Log.d("Wakeup", "dds_wakeup_delete after");
        return dds_wakeup_delete;
    }

    /* loaded from: classes.dex */
    public static class vprintcut_callback {
        public static byte[] bufferData = new byte[3200];

        public int run(int i, byte[] bArr, int i2) {
            return 0;
        }

        public static byte[] getBufferData() {
            return bufferData;
        }
    }
}
