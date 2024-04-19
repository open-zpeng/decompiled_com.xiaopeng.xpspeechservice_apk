package com.aispeech.lite.oneshot;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.auth.f;
import com.aispeech.common.AITimer;
import com.aispeech.common.Log;
import com.aispeech.common.Util;
import com.aispeech.lite.c;
import com.aispeech.lite.c.k;
import com.aispeech.lite.e;
import com.aispeech.lite.f.b;
import com.aispeech.lite.h.o;
import com.aispeech.lite.vad.VadKernelListener;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/* loaded from: classes.dex */
public class OneshotKernel implements VadKernelListener, Runnable {
    public static final int INTERVAL = c.h;
    private b a;
    private e b;
    private ExecutorService c;
    private OneshotListener e;
    private com.aispeech.lite.vad.a f;
    private OneshotCache g;
    private String[] l;
    private String m;
    private Semaphore d = new Semaphore(0);
    private volatile EngineState i = EngineState.STATE_IDLE;
    private int j = 500;
    private a k = null;
    private volatile boolean n = false;
    private f h = c.a().a("oneshot");

    public OneshotKernel(OneshotListener oneshotListener) {
        if (this.h.b()) {
            this.a = new b();
            new com.aispeech.lite.b();
            this.b = new e("oneshot", 5);
            this.c = Executors.newSingleThreadExecutor(this.b);
            this.c.execute(this);
            this.e = oneshotListener;
            return;
        }
        a(this.h);
    }

    public void newKernel(k kVar) {
        f fVar = this.h;
        if (fVar != null && fVar.b()) {
            a(new com.aispeech.lite.f.a(0, kVar));
        } else {
            a(this.h);
        }
    }

    public void feed(byte[] bArr) {
        f fVar = this.h;
        if (fVar != null && fVar.b()) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            a(new com.aispeech.lite.f.a(1, bArr2));
            return;
        }
        a(this.h);
    }

    public void notifyWakeup(String str) {
        f fVar = this.h;
        if (fVar == null || !fVar.b()) {
            a(this.h);
            return;
        }
        String[] strArr = this.l;
        boolean z = false;
        if (strArr != null && strArr.length != 0) {
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (!TextUtils.equals(strArr[i], str)) {
                    i++;
                } else {
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            a(new com.aispeech.lite.f.a(4, str));
            return;
        }
        Log.d("oneshot", "drop illegal notify oneshot word : " + str);
    }

    public void releaseKernel() {
        f fVar = this.h;
        if (fVar != null && fVar.b()) {
            Log.d("oneshot", "releaseKernel");
            a(new com.aispeech.lite.f.a(7));
            try {
                Log.i("oneshot", "Semaphore acquire before");
                this.d.acquire();
                Log.i("oneshot", "Semaphore acquire end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.c.shutdown();
            this.c = null;
            if (this.b != null) {
                this.b = null;
                return;
            }
            return;
        }
        a(this.h);
    }

    @Override // java.lang.Runnable
    public void run() {
        com.aispeech.lite.f.a aVar;
        com.aispeech.lite.vad.a aVar2;
        while (true) {
            b bVar = this.a;
            if (bVar != null) {
                aVar = bVar.a();
            } else {
                aVar = null;
            }
            if (aVar != null) {
                int i = aVar.a;
                if (i != -1) {
                    int i2 = 0;
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 4) {
                                if (i != 5) {
                                    if (i != 6) {
                                        if (i == 7) {
                                            if (this.i != EngineState.STATE_IDLE) {
                                                com.aispeech.lite.vad.a aVar3 = this.f;
                                                if (aVar3 != null) {
                                                    aVar3.releaseKernel();
                                                    this.f = null;
                                                }
                                                OneshotCache oneshotCache = this.g;
                                                if (oneshotCache != null) {
                                                    oneshotCache.clear();
                                                    this.g = null;
                                                }
                                            } else {
                                                a("release");
                                            }
                                        }
                                    } else if (this.i == EngineState.STATE_WAITING) {
                                        OneshotListener oneshotListener = this.e;
                                        if (oneshotListener != null) {
                                            oneshotListener.onNotOneshot(this.m);
                                        }
                                        a();
                                        this.n = false;
                                        com.aispeech.lite.vad.a aVar4 = this.f;
                                        if (aVar4 != null) {
                                            aVar4.stopKernel();
                                        }
                                        a(EngineState.STATE_NEWED);
                                    } else {
                                        a("timeout");
                                    }
                                } else if (this.i == EngineState.STATE_WAITING) {
                                    OneshotListener oneshotListener2 = this.e;
                                    if (oneshotListener2 != null) {
                                        oneshotListener2.onOneshot(this.m, this.g);
                                    }
                                    a();
                                    this.n = false;
                                    com.aispeech.lite.vad.a aVar5 = this.f;
                                    if (aVar5 != null) {
                                        aVar5.stopKernel();
                                    }
                                    a(EngineState.STATE_NEWED);
                                } else {
                                    a("vad.begin");
                                }
                            } else if (this.i == EngineState.STATE_RUNNING) {
                                String str = (String) aVar.b;
                                Log.d("oneshot", "notify wakeup word :" + str);
                                this.m = str;
                                this.n = true;
                                a aVar6 = this.k;
                                if (aVar6 != null) {
                                    aVar6.cancel();
                                    this.k = null;
                                }
                                this.k = new a();
                                AITimer.getInstance().schedule(this.k, this.j);
                                if (this.f != null) {
                                    o oVar = new o();
                                    oVar.a(0);
                                    this.f.startKernel(oVar);
                                }
                                a(EngineState.STATE_WAITING);
                            } else {
                                a("notify.wakeup");
                            }
                        } else if (this.i != EngineState.STATE_IDLE) {
                            byte[] bArr = (byte[]) aVar.b;
                            OneshotCache oneshotCache2 = this.g;
                            if (oneshotCache2 != null) {
                                oneshotCache2.offer(bArr);
                            }
                            if (this.n && (aVar2 = this.f) != null) {
                                aVar2.feed(bArr);
                            }
                            if (this.i == EngineState.STATE_NEWED) {
                                a(EngineState.STATE_RUNNING);
                            }
                        } else {
                            a("feed");
                        }
                    } else if (this.i == EngineState.STATE_IDLE) {
                        k kVar = (k) aVar.b;
                        String[] c = kVar.d().c();
                        if (c != null && c.length > 0) {
                            int length = c.length;
                            while (true) {
                                if (i2 >= length) {
                                    break;
                                }
                                String str2 = c[i2];
                                if (Util.copyResource(c.b(), str2, null) != -1) {
                                    i2++;
                                } else {
                                    Log.e("oneshot", "file " + str2 + " not found in assest folder, Did you forget add it?");
                                    break;
                                }
                            }
                        }
                        Log.d("oneshot", "oneshot config : " + kVar.toString());
                        this.l = kVar.a();
                        Log.d("oneshot", "oneshot wakeup word : " + this.l);
                        this.j = kVar.c();
                        Log.d("oneshot", "oneshot middle time : " + this.j);
                        int b = kVar.b() / INTERVAL;
                        Log.d("oneshot", "set cache size : " + b);
                        this.f = new com.aispeech.lite.vad.a("oneshot", this);
                        this.f.newKernel(kVar.d());
                        this.g = new OneshotCache(b);
                    } else {
                        a("new");
                    }
                } else {
                    OneshotListener oneshotListener3 = this.e;
                    if (oneshotListener3 != null) {
                        oneshotListener3.onError((AIError) aVar.b);
                    }
                }
            } else {
                return;
            }
        }
    }

    private void a() {
        a aVar = this.k;
        if (aVar != null) {
            aVar.cancel();
            this.k = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends TimerTask {
        a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            Log.d("oneshot", "oneshot check timer execute");
            if (OneshotKernel.this.f != null) {
                OneshotKernel.this.f.stopKernel();
            }
            OneshotKernel.this.a(new com.aispeech.lite.f.a(6));
        }
    }

    /* loaded from: classes.dex */
    public enum EngineState {
        STATE_IDLE(0),
        STATE_NEWED(1),
        STATE_RUNNING(2),
        STATE_WAITING(3),
        STATE_ERROR(4);
        
        private int a;

        EngineState(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }
    }

    private void a(String str) {
        Log.w("oneshot", "Invalid State：" + this.i.name() + " when MSG: " + str);
    }

    private void a(EngineState engineState) {
        Log.d("oneshot", "transfer:" + this.i + " to:" + engineState);
        this.i = engineState;
    }

    @Override // com.aispeech.lite.vad.VadKernelListener
    public void onInit(int i) {
        Log.d("oneshot", "oneshot kernel init status : " + i);
        if (i == 0) {
            a(EngineState.STATE_NEWED);
        }
        OneshotListener oneshotListener = this.e;
        if (oneshotListener != null) {
            oneshotListener.onInit(i);
        }
    }

    @Override // com.aispeech.lite.vad.VadKernelListener
    public void onVadStart() {
        Log.d("oneshot", "vad.begin");
        a(new com.aispeech.lite.f.a(5));
    }

    @Override // com.aispeech.lite.vad.VadKernelListener
    public void onVadEnd() {
        Log.d("oneshot", "vad.end");
    }

    @Override // com.aispeech.lite.vad.VadKernelListener
    public void onRmsChanged(float f) {
    }

    @Override // com.aispeech.lite.vad.VadKernelListener
    public void onBufferReceived(byte[] bArr) {
    }

    @Override // com.aispeech.lite.vad.VadKernelListener
    public void onError(AIError aIError) {
        Log.e("oneshot", "vad onError : " + aIError.toString());
        a(new com.aispeech.lite.f.a(-1, aIError));
    }

    private void a(f fVar) {
        AIError aIError = new AIError();
        if (fVar == null) {
            aIError.setErrId(AIError.ERR_SDK_NOT_INIT);
            aIError.setError(AIError.ERR_DESCRIPTION_ERR_SDK_NOT_INIT);
        } else {
            aIError.setErrId(fVar.d().getId());
            aIError.setError(fVar.d().getValue());
        }
        a(new com.aispeech.lite.f.a(-1, aIError));
    }

    protected final void a(com.aispeech.lite.f.a aVar) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(aVar);
        }
    }
}
