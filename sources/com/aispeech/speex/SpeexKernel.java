package com.aispeech.speex;

import android.text.TextUtils;
import com.aispeech.common.FileUtil;
import com.aispeech.common.Log;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.g;
/* loaded from: classes.dex */
public class SpeexKernel extends g {
    public static final String TAG = "SpeexKernel";
    private Utils e;
    private SpeexKernelListener f;
    private a g;
    private volatile boolean h;
    private FileUtil i;
    private FileUtil j;
    private long k;
    private int l;
    private int m;
    private int n;
    private String o;

    public SpeexKernel(SpeexKernelListener speexKernelListener) {
        super(TAG);
        this.h = true;
        this.i = null;
        this.j = null;
        this.l = 8;
        this.m = 16000;
        this.n = 2;
        this.o = "";
        this.f = speexKernelListener;
    }

    public static boolean checkLibValid() {
        return Utils.isUtilsSoValid();
    }

    public void setSpeexSavedPath(String str) {
        this.o = str;
    }

    public void setQuality(int i) {
        this.l = i;
    }

    public void setSampleRate(int i) {
        this.m = i;
    }

    public void setComplexity(int i) {
        this.n = i;
    }

    /* loaded from: classes.dex */
    class a extends Utils.speex_callback {
        private a() {
        }

        /* synthetic */ a(SpeexKernel speexKernel, byte b) {
            this();
        }

        @Override // com.aispeech.kernel.Utils.speex_callback
        public final int run(int i, byte[] bArr, int i2) {
            if (i2 != 0) {
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, 0, bArr2, 0, i2);
                if (SpeexKernel.this.f != null) {
                    SpeexKernel.this.f.onResultBufferReceived(bArr2, i2);
                    if (!TextUtils.isEmpty(SpeexKernel.this.o) && SpeexKernel.this.j != null) {
                        SpeexKernel.this.j.write(bArr2);
                    }
                }
            }
            return 0;
        }
    }

    public void newKernel() {
        Log.d(TAG, "newKernel");
        a(new com.aispeech.lite.f.a(1));
    }

    public void startKernel() {
        Log.d(TAG, "startKernel");
        a(new com.aispeech.lite.f.a(2));
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x0199  */
    @Override // com.aispeech.lite.g, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.speex.SpeexKernel.run():void");
    }
}
