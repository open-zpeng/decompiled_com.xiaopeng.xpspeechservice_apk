package com.aispeech.kernel;

import android.text.TextUtils;
import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class Gram {
    private static boolean a;
    private long b;

    public static native int dds_gram_delete(long j);

    public static native long dds_gram_new(String str);

    public static native int dds_gram_start(long j, String str);

    static {
        a = false;
        try {
            Log.d("Gram", "before load gram library");
            System.loadLibrary("gram");
            Log.d("Gram", "after load gram library");
            a = true;
        } catch (UnsatisfiedLinkError e) {
            a = false;
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libgram.so, and put it in your libs dir!");
        }
    }

    public static boolean isGramSoValid() {
        return a;
    }

    public long init(String str) {
        this.b = dds_gram_new(str);
        Log.d("Gram", "AIEngine.new():" + this.b);
        return this.b;
    }

    public int start(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Log.d("Gram", "AIEngine.start():" + this.b);
        int dds_gram_start = dds_gram_start(this.b, str);
        if (dds_gram_start < 0) {
            Log.e("Gram", "AIEngine.start() failed! Error code: " + dds_gram_start);
            return -1;
        }
        return dds_gram_start;
    }

    public void destroy() {
        Log.d("Gram", "AIEngine.delete():" + this.b);
        dds_gram_delete(this.b);
        Log.d("Gram", "AIEngine.delete() finished:" + this.b);
        this.b = 0L;
    }
}
