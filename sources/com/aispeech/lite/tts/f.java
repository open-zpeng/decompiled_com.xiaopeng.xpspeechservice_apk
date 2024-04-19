package com.aispeech.lite.tts;

import com.aispeech.AIError;
import com.aispeech.common.Log;
import com.aispeech.kernel.Cntts;
/* loaded from: classes.dex */
public final class f extends com.aispeech.lite.g {
    private Cntts e;
    private a f;
    private m g;
    private volatile boolean h;

    static /* synthetic */ boolean b(f fVar) {
        fVar.h = false;
        return false;
    }

    public f(m mVar) {
        super("LocalTtsKernel");
        this.h = false;
        this.g = mVar;
    }

    @Override // com.aispeech.lite.g, java.lang.Runnable
    public final void run() {
        boolean z;
        int i;
        super.run();
        do {
            com.aispeech.lite.f.a b = b();
            if (b != null) {
                int i2 = b.a;
                z = true;
                if (i2 == 1) {
                    this.e = new Cntts();
                    this.f = new a(this, (byte) 0);
                    if (this.e.initCntts(((com.aispeech.lite.c.i) b.b).g().toString(), this.f) == 0) {
                        Log.e("LocalTtsKernel", "引擎初始化失败,请检查资源文件是否在指定路径下！");
                        i = -1;
                    } else {
                        Log.d("LocalTtsKernel", "引擎初始化成功");
                        i = 0;
                    }
                    this.g.a(i);
                } else if (i2 == 2) {
                    com.aispeech.lite.h.l lVar = (com.aispeech.lite.h.l) b.b;
                    String jSONObject = lVar.a_().toString();
                    Log.d("LocalTtsKernel", "cntts param: " + jSONObject);
                    Cntts cntts = this.e;
                    if (cntts != null) {
                        if (!cntts.startCntts(jSONObject)) {
                            this.a.a(new com.aispeech.lite.f.a(8, new AIError((int) AIError.ERR_AI_ENGINE, AIError.ERR_DESCRIPTION_AI_ENGINE)));
                        } else {
                            String n = lVar.n();
                            Log.d("LocalTtsKernel", "refText : " + n);
                            this.h = false;
                            m mVar = this.g;
                            if (mVar != null) {
                                mVar.a();
                            }
                            this.e.feedCntts(n);
                        }
                    }
                } else if (i2 == 7) {
                    Cntts cntts2 = this.e;
                    if (cntts2 != null) {
                        cntts2.destroyCntts();
                        this.e = null;
                    }
                    if (this.f != null) {
                        this.f = null;
                        continue;
                    } else {
                        continue;
                    }
                } else if (i2 == 8) {
                    this.g.a((AIError) b.b);
                } else if (i2 == 19) {
                    String str = (String) b.b;
                    Log.d("LocalTtsKernel", "set backBinPath is: " + str);
                    Cntts cntts3 = this.e;
                    if (cntts3 != null) {
                        cntts3.setBackBinPath(str);
                    }
                }
                z = false;
                continue;
            } else {
                return;
            }
        } while (!z);
        a();
    }

    @Override // com.aispeech.lite.g
    public final void startKernel(com.aispeech.lite.h.m mVar) {
        super.startKernel(mVar);
        if (this.h) {
            Log.e("LocalTtsKernel", "mIsCancelled:" + this.h);
        }
        this.h = false;
    }

    @Override // com.aispeech.lite.g
    public final synchronized void cancelKernel() {
        Log.d("LocalTtsKernel", "cancelKernel");
        this.h = true;
    }

    /* loaded from: classes.dex */
    class a extends Cntts.cntts_callback {
        private a() {
        }

        /* synthetic */ a(f fVar, byte b) {
            this();
        }

        @Override // com.aispeech.kernel.Cntts.cntts_callback
        public final int run(int i, byte[] bArr, int i2) {
            if (f.this.h) {
                f.b(f.this);
                return -1;
            }
            if (i == 1) {
                f.this.g.a(bArr, i2);
            }
            if (i2 == 0) {
                Log.d("LocalTtsKernel", "size=0");
                return 0;
            }
            return 0;
        }
    }
}
