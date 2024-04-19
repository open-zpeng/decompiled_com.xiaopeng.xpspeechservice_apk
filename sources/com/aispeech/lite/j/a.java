package com.aispeech.lite.j;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.common.AudioFileWriter;
import com.aispeech.common.FileUtil;
import com.aispeech.common.LimitQueue;
import com.aispeech.common.Log;
import com.aispeech.common.Util;
import com.aispeech.kernel.Wakeup;
import com.aispeech.lite.g;
import com.aispeech.lite.h.m;
import com.aispeech.lite.h.q;
import com.xiaopeng.lib.utils.config.CommonConfig;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class a extends g implements Wakeup.wakeup_callback {
    private d e;
    private Wakeup f;
    private volatile boolean g;
    private volatile boolean h;
    private q i;
    private volatile String j;
    private volatile String k;
    private FileUtil l;
    private AudioFileWriter m;
    private LimitQueue<byte[]> n;
    private int o;
    private volatile int p;
    private Wakeup.vprintcut_callback q;

    static /* synthetic */ int c(a aVar) {
        int i = aVar.p;
        aVar.p = i + 1;
        return i;
    }

    public a(d dVar) {
        super("WakeupKernel");
        this.g = true;
        this.h = false;
        this.o = 0;
        this.p = 0;
        this.q = new Wakeup.vprintcut_callback() { // from class: com.aispeech.lite.j.a.1
            @Override // com.aispeech.kernel.Wakeup.vprintcut_callback
            public final int run(int i, byte[] bArr, int i2) {
                if (a.this.i.d() || i != 0) {
                    if (a.this.e != null && a.this.c != g.a.STATE_IDLE) {
                        a.this.e.a(i, bArr, i2);
                    }
                } else {
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, 0, bArr2, 0, i2);
                    String newUTF8String = Util.newUTF8String(bArr2);
                    Log.d("WakeupKernel", "vprintStr is " + newUTF8String);
                    if (a.this.p <= 2) {
                        a.c(a.this);
                        if (a.this.e != null && a.this.c != g.a.STATE_IDLE) {
                            a.this.e.a(i, bArr, i2);
                        }
                    } else {
                        Log.w("WakeupKernel", "more than one vp, ignore");
                    }
                }
                return 0;
            }
        };
        this.e = dVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0267  */
    @Override // com.aispeech.lite.g, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 642
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.lite.j.a.run():void");
    }

    private int a(m mVar, Wakeup wakeup) {
        String mVar2 = mVar.toString();
        Log.d("WakeupKernel", "WakeupParams:\t" + mVar2);
        int startWakeup = wakeup.startWakeup(mVar2);
        Log.d("WakeupKernel", "startWakeupAIEngine end and ret: " + startWakeup);
        if (startWakeup != 0) {
            a(new com.aispeech.lite.f.a(8, new AIError((int) AIError.ERR_AI_ENGINE, AIError.ERR_DESCRIPTION_AI_ENGINE)));
            return startWakeup;
        }
        return startWakeup;
    }

    @Override // com.aispeech.kernel.Wakeup.wakeup_callback
    public final int run(int i, byte[] bArr, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        if (i == 0) {
            String trim = new String(bArr2).trim();
            Log.d("WakeupKernel", "WAKEUP.CALLBACK: " + trim);
            if (!this.i.d()) {
                try {
                    JSONObject jSONObject = new JSONObject(trim);
                    if (jSONObject.has(CommonConfig.KEY_VALUE_STATUS)) {
                        this.o = jSONObject.optInt(CommonConfig.KEY_VALUE_STATUS);
                        if (this.o == 1) {
                            if (!TextUtils.isEmpty(this.j)) {
                                return 0;
                            }
                            this.j = trim;
                            this.h = true;
                            Log.d("WakeupKernel", "real wakeup");
                        } else if (this.o == 2) {
                            if (!TextUtils.isEmpty(this.k)) {
                                return 0;
                            }
                            this.k = trim;
                            this.h = true;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            b(trim);
        }
        return 0;
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            AIResult aIResult = new AIResult();
            aIResult.setLast(true);
            aIResult.setResultType(0);
            aIResult.setResultObject(str);
            aIResult.setTimestamp(System.currentTimeMillis());
            if (this.e != null && this.c != g.a.STATE_IDLE) {
                if (!TextUtils.isEmpty(this.i.v())) {
                    try {
                        c(new JSONObject(aIResult.getResultObject().toString()).optString("wakeupWord"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                this.e.a(aIResult);
            }
        }
    }

    private void c() {
        FileUtil fileUtil = this.l;
        if (fileUtil != null) {
            fileUtil.closeFile();
            this.l = null;
        }
    }

    private void c(String str) {
        Log.d("WakeupKernel", "wake up result = " + str);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-dd-MM_HH-mm-ss", Locale.CHINA);
        if (!TextUtils.isEmpty(this.i.v())) {
            Log.d("WakeupKernel", "audio wake path = " + this.i.v());
            this.m = new AudioFileWriter();
            AudioFileWriter audioFileWriter = this.m;
            audioFileWriter.createFile(this.i.v() + "/wakeup_" + str + simpleDateFormat.format(new Date()) + ".pcm");
            while (!this.n.isEmpty()) {
                byte[] poll = this.n.poll();
                this.m.write(poll, poll.length);
            }
            this.m.close();
            this.m = null;
        }
    }
}
