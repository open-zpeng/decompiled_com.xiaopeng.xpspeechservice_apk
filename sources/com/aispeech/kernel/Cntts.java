package com.aispeech.kernel;

import com.aispeech.common.Log;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Cntts {
    private static boolean b;
    private long a;

    public static native int dds_cntts_delete(long j);

    public static native int dds_cntts_feed(long j, String str);

    public static native long dds_cntts_new(String str, cntts_callback cntts_callbackVar);

    public static native int dds_cntts_set(long j, String str);

    public static native int dds_cntts_start(long j, String str);

    static {
        b = false;
        try {
            Log.d("Cntts", "before load cntts library");
            System.loadLibrary("cntts");
            Log.d("Cntts", "after load cntts library");
            b = true;
        } catch (UnsatisfiedLinkError e) {
            b = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libcntts.so, and put it in your libs dir!");
        }
    }

    public static boolean isCnttsSoValid() {
        return b;
    }

    public long initCntts(String str, cntts_callback cntts_callbackVar) {
        Log.d("Cntts", "init Cntts " + str);
        this.a = dds_cntts_new(str, cntts_callbackVar);
        Log.d("Cntts", "init Cntts return " + this.a);
        return this.a;
    }

    public boolean startCntts(String str) {
        Log.d("Cntts", "start Cntts");
        int dds_cntts_start = dds_cntts_start(this.a, str);
        Log.d("Cntts", "start Cntts return " + dds_cntts_start);
        return dds_cntts_start == 0;
    }

    public boolean setBackBinPath(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("backBinPath", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Cntts", "set Cntts:" + jSONObject.toString());
        return dds_cntts_set(this.a, jSONObject.toString()) == 0;
    }

    public boolean feedCntts(String str) {
        Log.d("Cntts", "feed Cntts");
        int dds_cntts_feed = dds_cntts_feed(this.a, str);
        Log.d("Cntts", "feed Cntts return " + dds_cntts_feed);
        return dds_cntts_feed == 0;
    }

    public boolean destroyCntts() {
        Log.d("Cntts", "destroy Cntts");
        int dds_cntts_delete = dds_cntts_delete(this.a);
        Log.d("Cntts", "destroy Cntts return " + dds_cntts_delete);
        return dds_cntts_delete == 0;
    }

    /* loaded from: classes.dex */
    public static class cntts_callback {
        public static byte[] bufferData = new byte[3200];

        public int run(int i, byte[] bArr, int i2) {
            return 0;
        }

        public static byte[] getBufferData() {
            return bufferData;
        }
    }
}
