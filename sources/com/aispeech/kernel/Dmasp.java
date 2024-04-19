package com.aispeech.kernel;

import android.text.TextUtils;
import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class Dmasp {
    private static boolean a;
    private long b;

    public static native int dds_dmasp_cancel(long j);

    public static native int dds_dmasp_delete(long j);

    public static native int dds_dmasp_feed(long j, byte[] bArr, int i);

    public static native int dds_dmasp_get_ssp_flag(long j);

    public static native long dds_dmasp_new(String str, dmasp_callback dmasp_callbackVar);

    public static native int dds_dmasp_set(long j, String str);

    public static native int dds_dmasp_start(long j, String str);

    public static native int dds_dmasp_stop(long j);

    static {
        a = false;
        try {
            Log.d("Dmasp", "before load dmasp library");
            System.loadLibrary("dmasp");
            Log.d("Dmasp", "after load dmasp library");
            a = true;
        } catch (UnsatisfiedLinkError e) {
            a = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libdmasp.so, and put it in your libs dir!");
        }
    }

    public static boolean isDmaspSoValid() {
        return a;
    }

    public long init(String str, dmasp_callback dmasp_callbackVar) {
        Log.d("Dmasp", "cfg = " + str);
        this.b = dds_dmasp_new(str, dmasp_callbackVar);
        Log.d("Dmasp", "dds_dmasp_new():" + this.b);
        return this.b;
    }

    public int start(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("Dmasp", "dds_dmasp_start param is NULL, no start.");
            return -1;
        }
        long j = this.b;
        if (j != 0) {
            dds_dmasp_stop(j);
        }
        Log.d("Dmasp", "dds_dmasp_start():" + this.b);
        Log.e("Dmasp", "wakeupParams = " + str);
        int dds_dmasp_start = dds_dmasp_start(this.b, str);
        if (dds_dmasp_start < 0) {
            Log.e("Dmasp", "dds_dmasp_start() failed! Error code: " + dds_dmasp_start);
            return -1;
        }
        return dds_dmasp_start;
    }

    public int set(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("Dmasp", "dds_dmasp_set param is NULL, no set.");
            return -1;
        }
        int dds_dmasp_set = dds_dmasp_set(this.b, str);
        Log.d("Dmasp", "dds_dmasp_set():" + str + "   ret " + dds_dmasp_set);
        return dds_dmasp_set;
    }

    public int feed(byte[] bArr) {
        return dds_dmasp_feed(this.b, bArr, bArr.length);
    }

    public int stop() {
        Log.d("Dmasp", "dds_dmasp_stop():" + this.b);
        return dds_dmasp_stop(this.b);
    }

    public int cancel() {
        Log.d("Dmasp", "dds_dmasp_cancel():" + this.b);
        return dds_dmasp_cancel(this.b);
    }

    public void destroy() {
        Log.d("Dmasp", "dds_dmasp_delete():" + this.b);
        dds_dmasp_delete(this.b);
        Log.d("Dmasp", "dds_dmasp_delete() finished:" + this.b);
        this.b = 0L;
    }

    public boolean isWakeupSsp() {
        Log.d("Dmasp", "dds_dmasp_get_ssp_flag():");
        return dds_dmasp_get_ssp_flag(this.b) == 1;
    }

    /* loaded from: classes.dex */
    public static class dmasp_callback {
        public static byte[] bufferData = new byte[3200];

        public int run(int i, byte[] bArr, int i2) {
            return 0;
        }

        public static byte[] getBufferData() {
            return bufferData;
        }
    }
}
