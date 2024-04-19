package com.aispeech.kernel;

import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class Vprint {
    private static boolean a;
    private long b;

    /* loaded from: classes.dex */
    public interface vprint_callback {
        byte[][] getModelBin();

        int getModelNum();

        int[] getModelSize();

        int model_run(int i, String str, byte[][] bArr, int[] iArr, int i2);

        int run(int i, byte[] bArr, int i2);
    }

    public static native int dds_vprint_delete(long j);

    public static native int dds_vprint_feed(long j, byte[] bArr, int i, int i2);

    public static native long dds_vprint_new(String str, vprint_callback vprint_callbackVar);

    public static native long dds_vprint_new2(String str, vprint_callback vprint_callbackVar);

    public static native int dds_vprint_start(long j, String str);

    public static native int dds_vprint_stop(long j);

    static {
        a = false;
        try {
            Log.d("Vprint", "before load vprint library");
            System.loadLibrary("vprint");
            Log.d("Vprint", "after load vprint library");
            a = true;
        } catch (UnsatisfiedLinkError e) {
            a = false;
            e.printStackTrace();
            Log.e("Vprint", "Please check useful libvprint.so, and put it in your libs dir!");
        }
    }

    public static boolean isVprintSoValid() {
        return a;
    }

    public long init(boolean z, String str, vprint_callback vprint_callbackVar) {
        if (z) {
            this.b = dds_vprint_new2(str, vprint_callbackVar);
        } else {
            this.b = dds_vprint_new(str, vprint_callbackVar);
        }
        Log.d("Vprint", "AIEngine.new():" + this.b);
        return this.b;
    }

    public int start(String str) {
        if (str == null || "".equals(str)) {
            return -1;
        }
        Log.d("Vprint", "AIEngine.start():" + this.b);
        int dds_vprint_start = dds_vprint_start(this.b, str);
        Log.d("Vprint", "start ret : " + dds_vprint_start);
        return dds_vprint_start;
    }

    public int feed(byte[] bArr, int i, int i2) {
        return dds_vprint_feed(this.b, bArr, i, i2);
    }

    public int stop() {
        Log.d("Vprint", "AIEngine.stop():" + this.b);
        return dds_vprint_stop(this.b);
    }

    public void destroy() {
        Log.d("Vprint", "AIEngine.delete():" + this.b);
        dds_vprint_delete(this.b);
        Log.d("Vprint", "AIEngine.delete() finished:" + this.b);
        this.b = 0L;
    }

    /* loaded from: classes.dex */
    public enum MODEL_MSG_TYPE {
        VP_UPDATE(1),
        VP_INSERT(2),
        VP_DELETE(3);
        
        private final int a;

        MODEL_MSG_TYPE(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }
    }
}
