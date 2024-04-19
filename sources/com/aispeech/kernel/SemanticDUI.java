package com.aispeech.kernel;

import com.aispeech.common.Log;
import org.json.JSONException;
/* loaded from: classes.dex */
public class SemanticDUI {
    private static boolean b;
    private long a;

    /* loaded from: classes.dex */
    public interface semantic_callback {
        int run(int i, byte[] bArr, int i2) throws JSONException;
    }

    public static native int dds_semantic_delete(long j);

    public static native long dds_semantic_new(String str, semantic_callback semantic_callbackVar);

    public static native int dds_semantic_start(long j, String str);

    static {
        b = false;
        try {
            Log.d("SemanticDUI", "before load semanticdui library");
            System.loadLibrary("semanticdui");
            Log.d("SemanticDUI", "after load semanticdui library");
            b = true;
        } catch (UnsatisfiedLinkError e) {
            b = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libsemanticdui.so, and put it in your libs dir!");
        }
    }

    public static boolean isSemanticSoValid() {
        return b;
    }

    public long initSemanticDUI(String str, semantic_callback semantic_callbackVar) {
        Log.d("SemanticDUI", "dds_semantic_dui_new");
        this.a = dds_semantic_new(str, semantic_callbackVar);
        return this.a;
    }

    public int startSemanticDUI(String str) {
        Log.d("SemanticDUI", "dds_semantic_dui_start, param: " + str);
        int dds_semantic_start = dds_semantic_start(this.a, str);
        if (dds_semantic_start < 0) {
            Log.e("SemanticDUI", "dds_semantic_dui_start() failed! Error code: " + dds_semantic_start);
            return -1;
        }
        return dds_semantic_start;
    }

    public int destroySemanticDUI() {
        Log.d("SemanticDUI", "dds_semantic_dui_delete before");
        int dds_semantic_delete = dds_semantic_delete(this.a);
        Log.d("SemanticDUI", "dds_semantic_dui_delete after");
        return dds_semantic_delete;
    }
}
