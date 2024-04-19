package com.aispeech.lite.vad;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.common.FileUtil;
import com.aispeech.common.Log;
import com.aispeech.common.Util;
import com.aispeech.kernel.Vad;
import com.aispeech.lite.b;
import com.aispeech.lite.c;
import com.aispeech.lite.g;
import com.aispeech.lite.h.m;
import com.xiaopeng.lib.utils.config.CommonConfig;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class a extends g {
    public String e;
    private VadKernelListener f;
    private Vad g;
    private C0009a h;
    private m i;
    private volatile boolean j;
    private volatile boolean k;
    private FileUtil l;

    public a(String str, VadKernelListener vadKernelListener) {
        super(str + "-VadKernel");
        this.j = true;
        this.k = false;
        this.l = new FileUtil(c.b());
        this.e = str + "-VadKernel";
        this.f = vadKernelListener;
    }

    @Override // com.aispeech.lite.g
    public final void stopKernel() {
        Log.d(this.e, "stopKernel");
        a(new com.aispeech.lite.f.a(3));
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x020d  */
    @Override // com.aispeech.lite.g, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 538
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.lite.vad.a.run():void");
    }

    /* renamed from: com.aispeech.lite.vad.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0009a extends Vad.vad_callback {
        private C0009a() {
        }

        /* synthetic */ C0009a(a aVar, byte b) {
            this();
        }

        @Override // com.aispeech.kernel.Vad.vad_callback
        public final int run(int i, byte[] bArr, int i2) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            if (i == 0) {
                String str = a.this.e;
                Log.d(str, "json callback:" + new String(bArr2).trim());
                String newUTF8String = Util.newUTF8String(bArr2);
                if (a.this.b == null || !b.a(newUTF8String)) {
                    int b = a.this.b(newUTF8String);
                    if (b == 1 && a.this.f != null) {
                        a.this.f.onVadStart();
                    } else if (b == 2 && a.this.f != null) {
                        a.this.f.onVadEnd();
                        a.this.k = false;
                    }
                } else {
                    a.this.a(new com.aispeech.lite.f.a(8, new AIError(Util.newUTF8String(bArr2))));
                    return 1;
                }
            }
            if (i == 1) {
                double calcVolume = Util.calcVolume(Util.toShortArray(bArr2));
                String str2 = a.this.e;
                Log.d(str2, "bin callback: data.length " + i2 + " db " + calcVolume);
                if (a.this.f != null) {
                    a.this.f.onRmsChanged((float) calcVolume);
                    a.this.f.onBufferReceived(bArr2);
                    a.this.k = true;
                }
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(CommonConfig.KEY_VALUE_STATUS)) {
                return 0;
            }
            return jSONObject.getInt(CommonConfig.KEY_VALUE_STATUS);
        } catch (Exception e) {
            Log.e(this.e, e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}
