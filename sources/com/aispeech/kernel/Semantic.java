package com.aispeech.kernel;

import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class Semantic {
    private static boolean b;
    private long a;

    /* loaded from: classes.dex */
    public interface semantic_callback {
        int run(int i, byte[] bArr, int i2);
    }

    public static native int dds_semantic_delete(long j);

    public static native long dds_semantic_new(String str, semantic_callback semantic_callbackVar);

    public static native int dds_semantic_start(long j, String str);

    static {
        b = false;
        try {
            Log.d("Semantic", "before load semantic library");
            System.loadLibrary("semantic");
            Log.d("Semantic", "after load semantic library");
            b = true;
        } catch (UnsatisfiedLinkError e) {
            b = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libsemantic.so, and put it in your libs dir!");
        }
    }

    public static boolean isSemanticSoValid() {
        return b;
    }

    public long initSemantic(String str, semantic_callback semantic_callbackVar) {
        Log.d("Semantic", "dds_semantic_new");
        this.a = dds_semantic_new(str, semantic_callbackVar);
        return this.a;
    }

    public int startSemantic(String str) {
        Log.d("Semantic", "dds_semantic_start, param: " + str);
        int dds_semantic_start = dds_semantic_start(this.a, str);
        if (dds_semantic_start < 0) {
            Log.e("Semantic", "dds_semantic_start() failed! Error code: " + dds_semantic_start);
            return -1;
        }
        return dds_semantic_start;
    }

    public int destroySemantic() {
        Log.d("Semantic", "dds_semantic_delete before");
        int dds_semantic_delete = dds_semantic_delete(this.a);
        Log.d("Semantic", "dds_semantic_delete after");
        return dds_semantic_delete;
    }
}
