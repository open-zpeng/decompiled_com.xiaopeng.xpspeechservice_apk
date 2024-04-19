package com.aispeech.kernel;

import android.text.TextUtils;
import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class Asr {
    private static boolean a;
    private long b;

    /* loaded from: classes.dex */
    public interface asr_callback {
        int run(int i, byte[] bArr, int i2);
    }

    public static native int dds_asr_cancel(long j);

    public static native int dds_asr_delete(long j);

    public static native int dds_asr_feed(long j, byte[] bArr, int i);

    public static native long dds_asr_new(String str, asr_callback asr_callbackVar);

    public static native int dds_asr_start(long j, String str);

    public static native int dds_asr_stop(long j);

    static {
        a = false;
        try {
            Log.d("Asr", "before load asr library");
            System.loadLibrary("asr");
            Log.d("Asr", "after load asr library");
            a = true;
        } catch (UnsatisfiedLinkError e) {
            a = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libasr.so, and put it in your libs dir!");
        }
    }

    public static boolean isAsrSoValid() {
        return a;
    }

    public long init(String str, asr_callback asr_callbackVar) {
        this.b = dds_asr_new(str, asr_callbackVar);
        Log.d("Asr", "AIEngine.new():" + this.b);
        return this.b;
    }

    public int start(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Log.d("Asr", "AIEngine.start():" + this.b);
        int dds_asr_start = dds_asr_start(this.b, str);
        if (dds_asr_start < 0) {
            Log.e("Asr", "AIEngine.start() failed! Error code: " + dds_asr_start);
            return -1;
        }
        return dds_asr_start;
    }

    public int feed(byte[] bArr) {
        return dds_asr_feed(this.b, bArr, bArr.length);
    }

    public int stop() {
        Log.d("Asr", "AIEngine.stop():" + this.b);
        return dds_asr_stop(this.b);
    }

    public int cancel() {
        Log.d("Asr", "AIEngine.cancel():" + this.b);
        return dds_asr_cancel(this.b);
    }

    public void destroy() {
        Log.d("Asr", "AIEngine.delete():" + this.b);
        dds_asr_delete(this.b);
        Log.d("Asr", "AIEngine.delete() finished:" + this.b);
        this.b = 0L;
    }
}
