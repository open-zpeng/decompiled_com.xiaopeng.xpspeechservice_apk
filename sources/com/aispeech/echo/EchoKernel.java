package com.aispeech.echo;

import android.content.Context;
import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.auth.d;
import com.aispeech.common.FileUtil;
import com.aispeech.common.Log;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import com.aispeech.export.config.EchoConfig;
import com.aispeech.kernel.Echo;
import com.aispeech.lite.c;
import com.aispeech.lite.c.f;
import com.aispeech.lite.g;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class EchoKernel extends g {
    private EchoKernelListener e;
    private Echo f;
    private volatile boolean g;
    private Context h;
    private FileUtil i;
    private FileUtil j;
    private FileUtil k;
    private b l;
    private a m;
    private f n;
    private com.aispeech.auth.f o;
    private com.aispeech.b.a p;

    public EchoKernel(EchoKernelListener echoKernelListener) {
        this(c.a(), echoKernelListener);
    }

    private EchoKernel(d dVar, EchoKernelListener echoKernelListener) {
        super("EchoKernel");
        this.g = true;
        this.i = null;
        this.j = null;
        this.k = null;
        this.p = null;
        this.e = echoKernelListener;
        this.h = c.b();
        this.d = dVar;
        this.o = this.d.a("echo");
        Log.d("EchoKernel", "authstate: " + this.o.toString());
        if (this.o.b()) {
            this.l = new b(this, (byte) 0);
            this.m = new a(this, (byte) 0);
            return;
        }
        a(this.o);
    }

    public void setAIEchoConfig(EchoConfig echoConfig) {
        if (echoConfig != null) {
            Log.d("EchoKernel", "setAIEchoConfig" + echoConfig.getAecResource());
            c.b = echoConfig.getAecResource();
            c.c = echoConfig.getChannels();
            c.d = echoConfig.getMicNumber();
            c.f = echoConfig.isMonitorEnable();
            c.g = echoConfig.getMonitorPeriod();
        }
    }

    private void a(com.aispeech.auth.f fVar) {
        AIError aIError = new AIError();
        if (fVar == null) {
            aIError.setErrId(AIError.ERR_SDK_NOT_INIT);
            aIError.setError(AIError.ERR_DESCRIPTION_ERR_SDK_NOT_INIT);
        } else {
            aIError.setErrId(fVar.d().getId());
            aIError.setError(fVar.d().getValue());
        }
        EchoKernelListener echoKernelListener = this.e;
        if (echoKernelListener != null) {
            echoKernelListener.onError(aIError);
        }
    }

    /* loaded from: classes.dex */
    class b extends Echo.echo_callback {
        private b() {
        }

        /* synthetic */ b(EchoKernel echoKernel, byte b) {
            this();
        }

        @Override // com.aispeech.kernel.Echo.echo_callback
        public final int run(int i, byte[] bArr, int i2) {
            if (i == 1) {
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, 0, bArr2, 0, i2);
                if (EchoKernel.this.p != null) {
                    EchoKernel.this.p.a(bArr2);
                }
                if (EchoKernel.this.e != null) {
                    EchoKernel.this.e.onResultBufferReceived(bArr2);
                    if (!TextUtils.isEmpty(c.c()) && EchoKernel.this.j != null) {
                        EchoKernel.this.j.write(bArr2);
                    }
                }
            }
            return 0;
        }
    }

    /* loaded from: classes.dex */
    class a extends Echo.echo_voip_callback {
        private a() {
        }

        /* synthetic */ a(EchoKernel echoKernel, byte b) {
            this();
        }

        @Override // com.aispeech.kernel.Echo.echo_voip_callback
        public final int run(int i, byte[] bArr, int i2) {
            if (i == 1) {
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, 0, bArr2, 0, i2);
                if (EchoKernel.this.e != null) {
                    EchoKernel.this.e.onVoipBufferReceived(bArr2);
                }
                if (!TextUtils.isEmpty(c.c()) && EchoKernel.this.k != null) {
                    EchoKernel.this.k.write(bArr2);
                }
            }
            return 0;
        }
    }

    public void newKernel() {
        com.aispeech.auth.f fVar = this.o;
        if (fVar != null && fVar.b()) {
            Log.d("EchoKernel", "newKernel");
            a(new com.aispeech.lite.f.a(1));
            return;
        }
        a(this.o);
    }

    public void startKernel() {
        com.aispeech.auth.f fVar = this.o;
        if (fVar != null && fVar.b()) {
            Log.d("EchoKernel", "startKernel");
            a(new com.aispeech.lite.f.a(2));
            return;
        }
        a(this.o);
    }

    @Override // com.aispeech.lite.g
    public void feed(byte[] bArr) {
        com.aispeech.auth.f fVar = this.o;
        if (fVar != null && fVar.b()) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            super.feed(bArr2);
            return;
        }
        a(this.o);
    }

    @Override // com.aispeech.lite.g
    public void cancelKernel() {
        com.aispeech.auth.f fVar = this.o;
        if (fVar != null && fVar.b()) {
            super.cancelKernel();
        } else {
            a(this.o);
        }
    }

    @Override // com.aispeech.lite.g
    public void stopKernel() {
        com.aispeech.auth.f fVar = this.o;
        if (fVar != null && fVar.b()) {
            super.stopKernel();
        } else {
            a(this.o);
        }
    }

    @Override // com.aispeech.lite.g
    public void releaseKernel() {
        com.aispeech.auth.f fVar = this.o;
        if (fVar != null && fVar.b()) {
            super.releaseKernel();
        } else {
            a(this.o);
        }
    }

    /* loaded from: classes.dex */
    public enum TTSFlag {
        PLAYING(1),
        ENDING(0);
        
        int a;

        TTSFlag(int i) {
            this.a = 0;
            this.a = i;
        }
    }

    @Override // com.aispeech.lite.g
    @Deprecated
    public void set(String str) {
        com.aispeech.auth.f fVar = this.o;
        if (fVar != null && fVar.b()) {
            super.set(str);
        } else {
            a(this.o);
        }
    }

    public void set(TTSFlag tTSFlag) {
        com.aispeech.auth.f fVar = this.o;
        if (fVar != null && fVar.b()) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ttsFlag", tTSFlag.a);
                super.set(jSONObject.toString());
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        a(this.o);
    }

    @Override // com.aispeech.lite.g, java.lang.Runnable
    public void run() {
        boolean z;
        super.run();
        do {
            com.aispeech.lite.f.a b2 = b();
            if (b2 != null) {
                int i = b2.a;
                z = false;
                if (i == 1) {
                    this.n = new f();
                    if (TextUtils.isEmpty(c.b)) {
                        throw new IllegalArgumentException("echoResources cannot be null, pls use setAIEchoConfig to set");
                    }
                    if (c.b.startsWith(URLUtils.URL_PATH_SEPERATOR)) {
                        this.n.a(c.b);
                    } else {
                        this.n.a(new String[]{c.b});
                        this.n.a(Util.getResourceDir(this.n.b()) + File.separator + c.b);
                    }
                    this.n.b(c.c);
                    this.n.c(c.d);
                    this.n.d(c.f);
                    this.n.a(c.g);
                    this.f = new Echo();
                    f fVar = this.n;
                    Echo echo = this.f;
                    int i2 = -1;
                    if (fVar != null) {
                        a(fVar);
                        String jSONObject = fVar.k().toString();
                        Log.d("EchoKernel", "config" + jSONObject);
                        long init = echo.init(jSONObject, this.l);
                        Log.d("EchoKernel", "echo create return " + init + URLUtils.URL_DOMAIN_SEPERATOR);
                        if (init == 0) {
                            Log.d("EchoKernel", "引擎初始化失败");
                        } else {
                            Log.d("EchoKernel", "引擎初始化成功");
                        }
                        int callback = echo.setCallback(this.m);
                        if (callback == 0) {
                            i2 = 0;
                        } else if (callback == -9892) {
                            Log.e("EchoKernel", "setCallback failed");
                        }
                    }
                    this.e.onInit(i2);
                    if (this.n.h()) {
                        this.p = new com.aispeech.b.a();
                        this.p.a(new com.aispeech.b.c() { // from class: com.aispeech.echo.EchoKernel.1
                            @Override // com.aispeech.b.c
                            public final void a() {
                                Log.d("EchoKernel", "echo health monitor notify ...");
                                EchoKernel.this.a(new com.aispeech.lite.f.a(16));
                            }
                        }, this.n.j(), this.n.i());
                        continue;
                    } else {
                        continue;
                    }
                } else if (i == 2) {
                    if (!TextUtils.isEmpty(c.c())) {
                        this.i = new FileUtil(c.b());
                        this.j = new FileUtil(c.b());
                        this.k = new FileUtil(c.b());
                        long currentTimeMillis = System.currentTimeMillis();
                        this.i.createFile(c.c() + "/echo_in_" + currentTimeMillis + ".pcm");
                        this.j.createFile(c.c() + "/echo_out_" + currentTimeMillis + ".pcm");
                        this.k.createFile(c.c() + "/voip_out_" + currentTimeMillis + ".pcm");
                    }
                    Echo echo2 = this.f;
                    if (echo2 != null) {
                        echo2.start("");
                        this.g = false;
                    }
                    com.aispeech.b.a aVar = this.p;
                    if (aVar != null) {
                        aVar.c();
                        continue;
                    } else {
                        continue;
                    }
                } else if (i == 3) {
                    if (!TextUtils.isEmpty(c.c())) {
                        FileUtil fileUtil = this.i;
                        if (fileUtil != null && this.j != null) {
                            fileUtil.closeFile();
                            this.j.closeFile();
                            this.i = null;
                            this.j = null;
                        }
                        FileUtil fileUtil2 = this.k;
                        if (fileUtil2 != null) {
                            fileUtil2.closeFile();
                            this.k = null;
                        }
                    }
                    Echo echo3 = this.f;
                    if (echo3 != null) {
                        echo3.stop();
                    }
                    this.g = true;
                    com.aispeech.b.a aVar2 = this.p;
                    if (aVar2 != null) {
                        aVar2.d();
                        continue;
                    } else {
                        continue;
                    }
                } else if (i == 7) {
                    if (!TextUtils.isEmpty(c.c())) {
                        FileUtil fileUtil3 = this.i;
                        if (fileUtil3 != null && this.j != null) {
                            fileUtil3.closeFile();
                            this.j.closeFile();
                            this.i = null;
                            this.j = null;
                        }
                        FileUtil fileUtil4 = this.k;
                        if (fileUtil4 != null) {
                            fileUtil4.closeFile();
                            this.k = null;
                        }
                    }
                    Echo echo4 = this.f;
                    if (echo4 != null) {
                        echo4.destroy();
                        this.f = null;
                    }
                    if (this.n != null) {
                        this.n = null;
                    }
                    if (this.l != null) {
                        this.l = null;
                    }
                    if (this.m != null) {
                        this.m = null;
                    }
                    this.g = true;
                    com.aispeech.b.a aVar3 = this.p;
                    if (aVar3 != null) {
                        aVar3.b();
                    }
                    z = true;
                    continue;
                } else if (i == 8) {
                    this.e.onError((AIError) b2.b);
                    continue;
                } else if (i == 9) {
                    byte[] bArr = (byte[]) b2.b;
                    if (!TextUtils.isEmpty(c.c()) && this.i != null && !this.g) {
                        this.i.write(bArr);
                    }
                    if (c.e == 1) {
                        if (this.f != null && !this.g) {
                            this.f.feed(bArr);
                            continue;
                        }
                    } else if (c.e == 2) {
                        byte[] recChannelData = Util.getRecChannelData(bArr);
                        if (this.f != null && !this.g) {
                            this.f.feed(recChannelData);
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else if (i != 16) {
                    if (i != 19) {
                        continue;
                    } else {
                        String str = (String) b2.b;
                        if (this.f != null) {
                            Log.d("EchoKernel", "set echo : " + str);
                            this.f.set(str);
                            continue;
                        } else {
                            continue;
                        }
                    }
                } else if (this.f != null) {
                    Log.d("EchoKernel", "reset echo engine ...");
                    this.f.start("");
                    continue;
                } else {
                    continue;
                }
            } else {
                return;
            }
        } while (!z);
        a();
    }

    private int a(com.aispeech.lite.a aVar) {
        String[] c = aVar.c();
        if (c != null && c.length > 0) {
            for (String str : c) {
                if (Util.copyResource(this.h, str, null) == -1) {
                    Log.e("EchoKernel", "file " + str + " not found in assest folder, Did you forget add it?");
                    return 0;
                }
            }
        }
        return 0;
    }
}
