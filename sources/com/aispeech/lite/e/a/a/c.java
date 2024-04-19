package com.aispeech.lite.e.a.a;

import com.aispeech.AIError;
import com.aispeech.common.Log;
import com.aispeech.lite.c.j;
import com.aispeech.lite.h.m;
import com.aispeech.lite.vad.VadKernelListener;
import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/* loaded from: classes.dex */
public final class c implements com.aispeech.lite.e.a.a.b {
    private CyclicBarrier b;
    private com.aispeech.lite.e.a.a.d c;
    private volatile boolean a = false;
    private int l = -1;
    private com.aispeech.lite.vad.a d = new com.aispeech.lite.vad.a("NVad0", new a(this, (byte) 0));
    private com.aispeech.lite.vad.a e = new com.aispeech.lite.vad.a("NVad1", new b(this, (byte) 0));
    private com.aispeech.lite.vad.a f = new com.aispeech.lite.vad.a("NVad2", new C0002c(this, (byte) 0));
    private com.aispeech.lite.vad.a g = new com.aispeech.lite.vad.a("NVad3", new d(this, (byte) 0));
    private LinkedList<byte[]> h = new LinkedList<>();
    private LinkedList<byte[]> i = new LinkedList<>();
    private LinkedList<byte[]> j = new LinkedList<>();
    private LinkedList<byte[]> k = new LinkedList<>();

    public c() {
        if (this.b == null) {
            this.b = new CyclicBarrier(4, new Runnable() { // from class: com.aispeech.lite.e.a.a.c.1
                @Override // java.lang.Runnable
                public final void run() {
                    if (c.this.c != null) {
                        if (!c.this.a) {
                            c.this.c.a(-1);
                        } else {
                            c.this.c.a(0);
                        }
                    }
                }
            });
        }
    }

    @Override // com.aispeech.lite.e.a.a.b
    public final void a(j jVar, com.aispeech.lite.e.a.a.d dVar) {
        this.c = dVar;
        com.aispeech.lite.vad.a aVar = this.d;
        if (aVar != null) {
            aVar.newKernel(jVar);
        }
        com.aispeech.lite.vad.a aVar2 = this.e;
        if (aVar2 != null) {
            aVar2.newKernel(jVar);
        }
        com.aispeech.lite.vad.a aVar3 = this.f;
        if (aVar3 != null) {
            aVar3.newKernel(jVar);
        }
        com.aispeech.lite.vad.a aVar4 = this.g;
        if (aVar4 != null) {
            aVar4.newKernel(jVar);
        }
    }

    @Override // com.aispeech.lite.e.a.a.b
    public final void a(m mVar) {
        this.l = -1;
        com.aispeech.lite.vad.a aVar = this.d;
        if (aVar != null) {
            aVar.startKernel(mVar);
        }
        com.aispeech.lite.vad.a aVar2 = this.e;
        if (aVar2 != null) {
            aVar2.startKernel(mVar);
        }
        com.aispeech.lite.vad.a aVar3 = this.f;
        if (aVar3 != null) {
            aVar3.startKernel(mVar);
        }
        com.aispeech.lite.vad.a aVar4 = this.g;
        if (aVar4 != null) {
            aVar4.startKernel(mVar);
        }
        LinkedList<byte[]> linkedList = this.h;
        if (linkedList != null) {
            linkedList.clear();
        }
        LinkedList<byte[]> linkedList2 = this.i;
        if (linkedList2 != null) {
            linkedList2.clear();
        }
        LinkedList<byte[]> linkedList3 = this.j;
        if (linkedList3 != null) {
            linkedList3.clear();
        }
        LinkedList<byte[]> linkedList4 = this.k;
        if (linkedList4 != null) {
            linkedList4.clear();
        }
    }

    @Override // com.aispeech.lite.e.a.a.b
    public final void a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        com.aispeech.lite.vad.a aVar = this.d;
        if (aVar != null) {
            aVar.feed(bArr);
        }
        com.aispeech.lite.vad.a aVar2 = this.e;
        if (aVar2 != null) {
            aVar2.feed(bArr2);
        }
        com.aispeech.lite.vad.a aVar3 = this.f;
        if (aVar3 != null) {
            aVar3.feed(bArr3);
        }
        com.aispeech.lite.vad.a aVar4 = this.g;
        if (aVar4 != null) {
            aVar4.feed(bArr4);
        }
    }

    @Override // com.aispeech.lite.e.a.a.b
    public final void a() {
        com.aispeech.lite.vad.a aVar = this.d;
        if (aVar != null) {
            aVar.stopKernel();
        }
        com.aispeech.lite.vad.a aVar2 = this.e;
        if (aVar2 != null) {
            aVar2.stopKernel();
        }
        com.aispeech.lite.vad.a aVar3 = this.f;
        if (aVar3 != null) {
            aVar3.stopKernel();
        }
        com.aispeech.lite.vad.a aVar4 = this.g;
        if (aVar4 != null) {
            aVar4.stopKernel();
        }
        this.l = -1;
    }

    @Override // com.aispeech.lite.e.a.a.b
    public final void b() {
        com.aispeech.lite.vad.a aVar = this.d;
        if (aVar != null) {
            aVar.releaseKernel();
            this.d = null;
        }
        com.aispeech.lite.vad.a aVar2 = this.e;
        if (aVar2 != null) {
            aVar2.releaseKernel();
            this.e = null;
        }
        com.aispeech.lite.vad.a aVar3 = this.f;
        if (aVar3 != null) {
            aVar3.releaseKernel();
            this.f = null;
        }
        com.aispeech.lite.vad.a aVar4 = this.g;
        if (aVar4 != null) {
            aVar4.releaseKernel();
            this.g = null;
        }
        if (this.b != null) {
            this.b = null;
        }
        if (this.c != null) {
            this.c = null;
        }
        LinkedList<byte[]> linkedList = this.h;
        if (linkedList != null) {
            linkedList.clear();
            this.h = null;
        }
        LinkedList<byte[]> linkedList2 = this.i;
        if (linkedList2 != null) {
            linkedList2.clear();
            this.i = null;
        }
        LinkedList<byte[]> linkedList3 = this.j;
        if (linkedList3 != null) {
            linkedList3.clear();
            this.j = null;
        }
        LinkedList<byte[]> linkedList4 = this.k;
        if (linkedList4 != null) {
            linkedList4.clear();
            this.k = null;
        }
    }

    @Override // com.aispeech.lite.e.a.a.b
    public final void a(int i) {
        LinkedList<byte[]> linkedList;
        synchronized (this) {
            if (i == 0) {
                linkedList = this.h;
            } else if (i == 1) {
                linkedList = this.i;
            } else if (i != 2) {
                if (i == 3) {
                    linkedList = this.k;
                } else {
                    linkedList = null;
                }
            } else {
                linkedList = this.j;
            }
            while (linkedList != null && !linkedList.isEmpty()) {
                if (this.c != null) {
                    this.c.a(linkedList.poll());
                }
            }
            this.l = i;
        }
        if (i == 0) {
            com.aispeech.lite.vad.a aVar = this.e;
            if (aVar != null) {
                aVar.stopKernel();
            }
            com.aispeech.lite.vad.a aVar2 = this.f;
            if (aVar2 != null) {
                aVar2.stopKernel();
            }
            com.aispeech.lite.vad.a aVar3 = this.g;
            if (aVar3 == null) {
                return;
            }
            aVar3.stopKernel();
        } else if (i == 1) {
            com.aispeech.lite.vad.a aVar4 = this.d;
            if (aVar4 != null) {
                aVar4.stopKernel();
            }
            com.aispeech.lite.vad.a aVar5 = this.f;
            if (aVar5 != null) {
                aVar5.stopKernel();
            }
            com.aispeech.lite.vad.a aVar6 = this.g;
            if (aVar6 == null) {
                return;
            }
            aVar6.stopKernel();
        } else if (i != 2) {
            if (i != 3) {
                return;
            }
            com.aispeech.lite.vad.a aVar7 = this.d;
            if (aVar7 != null) {
                aVar7.stopKernel();
            }
            com.aispeech.lite.vad.a aVar8 = this.e;
            if (aVar8 != null) {
                aVar8.stopKernel();
            }
            com.aispeech.lite.vad.a aVar9 = this.f;
            if (aVar9 == null) {
                return;
            }
            aVar9.stopKernel();
        } else {
            com.aispeech.lite.vad.a aVar10 = this.d;
            if (aVar10 != null) {
                aVar10.stopKernel();
            }
            com.aispeech.lite.vad.a aVar11 = this.e;
            if (aVar11 != null) {
                aVar11.stopKernel();
            }
            com.aispeech.lite.vad.a aVar12 = this.g;
            if (aVar12 == null) {
                return;
            }
            aVar12.stopKernel();
        }
    }

    /* loaded from: classes.dex */
    class a implements VadKernelListener {
        private a() {
        }

        /* synthetic */ a(c cVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onInit(int i) {
            c.a(c.this, i);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadStart() {
            if (c.this.c != null) {
                c.this.c.b(0);
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadEnd() {
            if (c.this.l == 0 && c.this.c != null) {
                c.this.c.a();
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onRmsChanged(float f) {
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onBufferReceived(byte[] bArr) {
            if (c.this.l == 0) {
                if (c.this.c != null) {
                    c.this.c.a(bArr);
                }
            } else if (-1 == c.this.l) {
                if (c.this.h != null) {
                    c.this.h.offer(bArr);
                } else {
                    Log.e("NVadEngine", "vad cache is null!!!");
                }
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onError(AIError aIError) {
            if (c.this.c != null) {
                c.this.c.a(aIError);
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements VadKernelListener {
        private b() {
        }

        /* synthetic */ b(c cVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onInit(int i) {
            c.a(c.this, i);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadStart() {
            if (c.this.c != null) {
                c.this.c.b(1);
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadEnd() {
            if (1 == c.this.l && c.this.c != null) {
                c.this.c.a();
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onRmsChanged(float f) {
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onBufferReceived(byte[] bArr) {
            if (1 == c.this.l) {
                if (c.this.c != null) {
                    c.this.c.a(bArr);
                }
            } else if (-1 == c.this.l) {
                if (c.this.i != null) {
                    c.this.i.offer(bArr);
                } else {
                    Log.e("NVadEngine", "vad cache is null!!!");
                }
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onError(AIError aIError) {
            if (c.this.c != null) {
                c.this.c.a(aIError);
            }
        }
    }

    /* renamed from: com.aispeech.lite.e.a.a.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0002c implements VadKernelListener {
        private C0002c() {
        }

        /* synthetic */ C0002c(c cVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onInit(int i) {
            c.a(c.this, i);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadStart() {
            if (c.this.c != null) {
                c.this.c.b(2);
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadEnd() {
            if (2 == c.this.l && c.this.c != null) {
                c.this.c.a();
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onRmsChanged(float f) {
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onBufferReceived(byte[] bArr) {
            if (2 == c.this.l) {
                if (c.this.c != null) {
                    c.this.c.a(bArr);
                }
            } else if (-1 == c.this.l) {
                if (c.this.j != null) {
                    c.this.j.offer(bArr);
                } else {
                    Log.e("NVadEngine", "vad cache is null!!!");
                }
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onError(AIError aIError) {
            if (c.this.c != null) {
                c.this.c.a(aIError);
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements VadKernelListener {
        private d() {
        }

        /* synthetic */ d(c cVar, byte b) {
            this();
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onInit(int i) {
            c.a(c.this, i);
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadStart() {
            if (c.this.c != null) {
                c.this.c.b(3);
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onVadEnd() {
            if (3 == c.this.l && c.this.c != null) {
                c.this.c.a();
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onRmsChanged(float f) {
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onBufferReceived(byte[] bArr) {
            if (3 == c.this.l) {
                if (c.this.c != null) {
                    c.this.c.a(bArr);
                }
            } else if (-1 == c.this.l) {
                if (c.this.k != null) {
                    c.this.k.offer(bArr);
                } else {
                    Log.e("NVadEngine", "vad cache is null!!!");
                }
            }
        }

        @Override // com.aispeech.lite.vad.VadKernelListener
        public final void onError(AIError aIError) {
            if (c.this.c != null) {
                c.this.c.a(aIError);
            }
        }
    }

    static /* synthetic */ void a(c cVar, int i) {
        if (i == 0) {
            cVar.a = true;
        } else {
            cVar.a = false;
        }
        CyclicBarrier cyclicBarrier = cVar.b;
        if (cyclicBarrier == null) {
            return;
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e2) {
            e2.printStackTrace();
        }
    }
}
