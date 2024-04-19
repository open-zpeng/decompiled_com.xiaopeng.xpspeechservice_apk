package com.aispeech.kernel;

import com.aispeech.common.Log;
import com.aispeech.kernel.Fespx;
/* loaded from: classes.dex */
public class fespCar extends Fespx {
    private static boolean a;
    private long b;

    public static native int dds_fespCar_delete(long j);

    public static native int dds_fespCar_feed(long j, byte[] bArr, int i);

    public static native int dds_fespCar_get(long j, String str);

    public static native int dds_fespCar_getDriveMode(long j);

    public static native long dds_fespCar_new(String str);

    public static native int dds_fespCar_set(long j, String str);

    public static native int dds_fespCar_setbeamformingcb(long j, Fespx.beamforming_callback beamforming_callbackVar);

    public static native int dds_fespCar_setdoacb(long j, Fespx.doa_callback doa_callbackVar);

    public static native int dds_fespCar_setvprintcutcb(long j, Fespx.vprintcut_callback vprintcut_callbackVar);

    public static native int dds_fespCar_setwakeupcb(long j, Fespx.wakeup_callback wakeup_callbackVar);

    public static native int dds_fespCar_start(long j, String str);

    public static native int dds_fespCar_stop(long j);

    static {
        try {
            Log.d("fespCar", "before load fespcar library");
            System.loadLibrary("fespcar");
            Log.d("fespCar", "after load fespcar library");
            a = true;
        } catch (UnsatisfiedLinkError e) {
            a = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libfespcar.so, and put it in your libs dir!");
        }
    }

    public static boolean isFespxSoValid() {
        return a;
    }

    public long init(String str) {
        this.b = dds_fespCar_new(str);
        Log.d("fespCar", "dds_fespCar_new():" + this.b);
        return this.b;
    }

    public int destroy() {
        Log.d("fespCar", "dds_fespCar_delete():" + this.b);
        int dds_fespCar_delete = dds_fespCar_delete(this.b);
        Log.d("fespCar", "dds_fespCar_delete() finished:" + this.b);
        this.b = 0L;
        return dds_fespCar_delete;
    }

    public int get(String str) {
        int dds_fespCar_get = dds_fespCar_get(this.b, str);
        Log.d("fespCar", "getFespx " + str + ":" + dds_fespCar_get);
        return dds_fespCar_get;
    }

    public int set(String str) {
        Log.d("fespCar", "dds_fespCar_set():" + this.b);
        int dds_fespCar_set = dds_fespCar_set(this.b, str);
        if (dds_fespCar_set < 0) {
            Log.e("fespCar", "dds_fespCar_set() failed! Error code: " + dds_fespCar_set);
            return -1;
        }
        return dds_fespCar_set;
    }

    public int stop() {
        Log.d("fespCar", "dds_fespCar_stop():" + this.b);
        return dds_fespCar_stop(this.b);
    }

    public int start() {
        Log.d("fespCar", "dds_fespCar_start():" + this.b);
        int dds_fespCar_start = dds_fespCar_start(this.b, "");
        if (dds_fespCar_start < 0) {
            Log.e("fespCar", "dds_fespCar_start() failed! Error code: " + dds_fespCar_start);
            return -1;
        }
        return dds_fespCar_start;
    }

    public int feed(byte[] bArr, int i) {
        return dds_fespCar_feed(this.b, bArr, i);
    }

    public int getDriveMode() {
        int dds_fespCar_get = dds_fespCar_get(this.b, "driveMode");
        Log.d("fespCar", "dds_fespCar_get():" + dds_fespCar_get);
        return dds_fespCar_get;
    }

    public int setFespxWakeupcb(Fespx.wakeup_callback wakeup_callbackVar) {
        int dds_fespCar_setwakeupcb = dds_fespCar_setwakeupcb(this.b, wakeup_callbackVar);
        Log.d("fespCar", "dds_fespCar_setwakeupcb ret : " + dds_fespCar_setwakeupcb);
        return dds_fespCar_setwakeupcb;
    }

    public int setFespxVprintCutcb(Fespx.vprintcut_callback vprintcut_callbackVar) {
        int dds_fespCar_setvprintcutcb = dds_fespCar_setvprintcutcb(this.b, vprintcut_callbackVar);
        Log.d("fespCar", "dds_fespCar_setvprintcutcb ret : " + dds_fespCar_setvprintcutcb);
        return 0;
    }

    public int setFespxDoacb(Fespx.doa_callback doa_callbackVar) {
        int dds_fespCar_setdoacb = dds_fespCar_setdoacb(this.b, doa_callbackVar);
        Log.d("fespCar", "dds_fespCar_setdoacb ret : " + dds_fespCar_setdoacb);
        return dds_fespCar_setdoacb;
    }

    public int setFespxBeamformingcb(Fespx.beamforming_callback beamforming_callbackVar) {
        int dds_fespCar_setbeamformingcb = dds_fespCar_setbeamformingcb(this.b, beamforming_callbackVar);
        Log.d("fespCar", "dds_fespCar_setbeamformingcb ret : " + dds_fespCar_setbeamformingcb);
        return dds_fespCar_setbeamformingcb;
    }
}
