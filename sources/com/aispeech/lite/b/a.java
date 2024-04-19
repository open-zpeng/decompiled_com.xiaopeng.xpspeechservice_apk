package com.aispeech.lite.b;

import android.media.AudioRecord;
import com.aispeech.AIAudioRecord;
import com.aispeech.AIError;
import com.aispeech.common.Log;
import com.aispeech.common.Util;
import com.aispeech.echo.EchoKernel;
import com.aispeech.echo.EchoKernelListener;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.AISampleRate;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes.dex */
public final class a {
    private static int a = com.aispeech.lite.c.h;
    private static int b = com.aispeech.lite.c.i;
    private static int c = 2;
    private static int d = 16;
    private AISampleRate e;
    private EchoKernel f;
    private C0001a g;
    private volatile int h;
    private volatile AudioRecord i;
    private volatile AIAudioRecord j;
    private ExecutorService l;
    private com.aispeech.lite.e m;
    private long n;
    private d r;
    private Semaphore k = new Semaphore(0);
    private volatile Boolean o = false;
    private Lock p = new ReentrantLock();
    private CopyOnWriteArrayList<d> q = new CopyOnWriteArrayList<>();

    static /* synthetic */ void a(a aVar) {
        AISampleRate aISampleRate = aVar.e;
        int i = a;
        int value = aISampleRate.getValue();
        Integer valueOf = Integer.valueOf((((d * value) * c) * i) / 1000);
        Log.d("AIAudioRecorder", "[SampleRate = " + value + ", ReadBufferSize = " + valueOf + "]");
        int intValue = valueOf.intValue();
        byte[] bArr = new byte[intValue];
        int i2 = aVar.h;
        Log.d("AIAudioRecorder", "MAX Record Size: " + ((long) (d * aVar.e.getValue() * c * i2)));
        try {
            try {
                Log.d("AIAudioRecorder", "AIAudioRecord.read()...");
                int i3 = 0;
                while (aVar.o.booleanValue()) {
                    if (aVar.i != null) {
                        i3 = aVar.i.read(bArr, 0, intValue);
                    } else {
                        AIAudioRecord aIAudioRecord = aVar.j;
                    }
                    if (i3 > 0) {
                        byte[] bArr2 = new byte[i3];
                        System.arraycopy(bArr, 0, bArr2, 0, i3);
                        if (com.aispeech.lite.c.j != 0) {
                            if (com.aispeech.lite.c.j == 4) {
                                aVar.f.feed(bArr2);
                            }
                            aVar.a(bArr2, i3);
                        } else {
                            aVar.b(bArr2, i3);
                        }
                    } else {
                        Log.e("AIAudioRecorder", "recorder error read size : " + i3);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            aVar.a("stop end.");
        }
    }

    static /* synthetic */ void a(a aVar, byte[] bArr, int i) {
        Iterator<d> it = aVar.q.iterator();
        while (it.hasNext()) {
            it.next().c(bArr, i);
        }
    }

    public a(AISampleRate aISampleRate, int i, int i2, d dVar) {
        this.h = 0;
        this.e = aISampleRate;
        this.r = dVar;
        this.h = i2;
        Log.d("AIAudioRecorder", "audioSampleRate: " + aISampleRate.getValue() + "\tintervalTime: " + i + "\tmaxRecordSec: " + i2);
        if (b == 0) {
            if (com.aispeech.lite.c.j == 0 || com.aispeech.lite.c.j == 4) {
                b = 1;
            } else {
                b = 6;
            }
        }
        Log.d("AIAudioRecorder", "audioSourceType: " + b);
        g();
    }

    public final synchronized boolean a() {
        return this.o.booleanValue();
    }

    public final synchronized boolean a(d dVar) {
        boolean z;
        if (dVar != null) {
            z = this.q.contains(dVar) ? true : true;
        }
        z = false;
        return z;
    }

    private synchronized void d(d dVar) {
        Log.d("AIAudioRecorder", "registerListener " + dVar.toString());
        if (!this.q.contains(dVar)) {
            Log.d("AIAudioRecorder", "add listener " + dVar.toString());
            this.q.add(dVar);
        }
    }

    private synchronized void e(d dVar) {
        if (dVar != null) {
            if (this.q.contains(dVar)) {
                Log.d("AIAudioRecorder", "remove listener " + dVar.toString());
                this.q.remove(dVar);
            }
        }
    }

    private synchronized void a(AIError aIError) {
        Iterator<d> it = this.q.iterator();
        while (it.hasNext()) {
            it.next().a(aIError);
        }
    }

    private synchronized void f() {
        Iterator<d> it = this.q.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
    }

    private synchronized void a(byte[] bArr, int i) {
        Iterator<d> it = this.q.iterator();
        while (it.hasNext()) {
            it.next().b(bArr, i);
        }
    }

    private synchronized void b(byte[] bArr, int i) {
        Iterator<d> it = this.q.iterator();
        while (it.hasNext()) {
            it.next().c(bArr, i);
        }
    }

    public final long b(d dVar) {
        Log.i("AIAudioRecorder", "start");
        if (this.i == null) {
            AIAudioRecord aIAudioRecord = this.j;
            Log.e("AIAudioRecorder", "recorder new failed");
            return -1L;
        }
        this.p.lock();
        try {
            d(dVar);
            if (this.o.booleanValue()) {
                Log.w("AIAudioRecorder", "AudioRecorder has been started!");
                dVar.f();
                return this.n;
            }
            this.n = Util.generateRandom(10);
            this.o = Boolean.valueOf(h());
            this.l.execute(new b());
            if (com.aispeech.lite.c.j == 4) {
                this.f.startKernel();
            }
            return this.n;
        } finally {
            this.p.unlock();
        }
    }

    public final void c(d dVar) {
        Log.i("AIAudioRecorder", "stop");
        if (this.i == null) {
            AIAudioRecord aIAudioRecord = this.j;
            Log.e("AIAudioRecorder", "recorder new failed");
            return;
        }
        this.p.lock();
        try {
            if (!this.o.booleanValue()) {
                Log.w("AIAudioRecorder", "AudioRecorder has been stopped!");
            } else if (dVar == null) {
                Log.d("AIAudioRecorder", "stop recorder");
                this.o = false;
                try {
                    Log.i("AIAudioRecorder", "Semaphore acquire before: stop start.");
                    this.k.tryAcquire(600L, TimeUnit.MILLISECONDS);
                    Log.i("AIAudioRecorder", "Semaphore acquire end: stop start.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("AIAudioRecorder", "AudioRecord.stop() before");
                if (this.i != null) {
                    this.i.stop();
                } else {
                    AIAudioRecord aIAudioRecord2 = this.j;
                }
                if (com.aispeech.lite.c.j == 4 && this.f != null) {
                    this.f.stopKernel();
                }
                Log.d("AIAudioRecorder", "AudioRecord.stop() end");
            } else if (!this.q.contains(dVar)) {
                Log.d("AIAudioRecorder", "the listener has been unRegistered");
            } else {
                e(dVar);
            }
        } finally {
            this.p.unlock();
        }
    }

    public final void b() {
        EchoKernel echoKernel;
        Log.i("AIAudioRecorder", "release");
        if (this.i == null) {
            AIAudioRecord aIAudioRecord = this.j;
            Log.e("AIAudioRecorder", "recorder new failed");
            return;
        }
        Log.d("AIAudioRecorder", "clearListener");
        this.q.clear();
        c(null);
        Log.d("AIAudioRecorder", "AudioRecord.release() before");
        if (this.i != null) {
            this.i.release();
            this.i = null;
        } else {
            AIAudioRecord aIAudioRecord2 = this.j;
        }
        if (com.aispeech.lite.c.j == 4 && (echoKernel = this.f) != null) {
            echoKernel.releaseKernel();
            this.f = null;
        }
        Log.d("AIAudioRecorder", "AudioRecord.release() after");
        Log.d("AIAudioRecorder", "Release AIAudioRecord, AudioRecord = null");
        ExecutorService executorService = this.l;
        if (executorService != null) {
            executorService.shutdown();
            this.l = null;
        }
        if (this.m != null) {
            this.m = null;
        }
        if (this.r != null) {
            this.r = null;
        }
    }

    private void g() {
        int i = 0;
        while (true) {
            try {
                if (com.aispeech.lite.c.j != 0) {
                    if (com.aispeech.lite.c.j == 4) {
                        this.i = new AudioRecord(b, 16000, 12, 2, 192000);
                        d = 2;
                    }
                } else {
                    this.i = new AudioRecord(b, 16000, 2, 2, 192000);
                    d = 1;
                }
                Log.d("AIAudioRecorder", "audio_channel_num is : " + d);
                if (this.i == null) {
                    AIAudioRecord aIAudioRecord = this.j;
                    throw new AIError((int) AIError.ERR_DEVICE, AIError.ERR_DESCRIPTION_DEVICE);
                }
                Log.d("AIAudioRecorder", "recorder.new() retry count: " + i);
                if (com.aispeech.lite.c.j == 4) {
                    if (this.g == null) {
                        this.g = new C0001a(this, (byte) 0);
                    }
                    if (this.f == null) {
                        this.f = new EchoKernel(this.g);
                    }
                    this.f.newKernel();
                }
                this.m = new com.aispeech.lite.e("AIRecorder", 10);
                this.l = Executors.newCachedThreadPool(this.m);
                return;
            } catch (AIError e) {
                e.printStackTrace();
                if (i < 4) {
                    try {
                        Thread.sleep(40L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    i++;
                } else {
                    a(e);
                    return;
                }
            }
        }
    }

    private boolean h() {
        int i = 0;
        while (true) {
            try {
                Log.d("AIAudioRecorder", "recorder.startRecording()");
            } catch (AIError e) {
                e.printStackTrace();
                if (i < 4) {
                    try {
                        Thread.sleep(40L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    i++;
                } else {
                    a(e);
                    return false;
                }
            }
            if (this.i != null) {
                this.i.startRecording();
                if (this.i.getRecordingState() != 3) {
                    throw new AIError((int) AIError.ERR_RECORDING, AIError.ERR_DESCRIPTION_RECORDING);
                }
                f();
                Log.d("AIAudioRecorder", "recorder.start() retry count: " + i);
                return true;
            }
            AIAudioRecord aIAudioRecord = this.j;
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = com.aispeech.lite.c.u;
            Log.d("AIAudioRecorder", "SET_THREAD_AFFINITY cpuId is : " + i);
            if (i > 0) {
                Utils.jni_duilite_set_thread_affinity(i);
            }
            Log.d("AIAudioRecorder", "Read Buffer Task run...");
            a.a(a.this);
            Log.d("AIAudioRecorder", "Read Buffer Task end...");
        }
    }

    public final AISampleRate c() {
        return this.e;
    }

    public static int d() {
        return d;
    }

    public static int e() {
        return c;
    }

    private void a(String str) {
        Log.i("AIAudioRecorder", "Semaphore release before: " + str);
        this.k.release();
        Log.i("AIAudioRecorder", "Semaphore release end: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.aispeech.lite.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0001a implements EchoKernelListener {
        private C0001a() {
        }

        /* synthetic */ C0001a(a aVar, byte b) {
            this();
        }

        @Override // com.aispeech.echo.EchoKernelListener
        public final void onInit(int i) {
            if (i == -1) {
                a.this.r.a(new AIError("new echo kernel failed"));
            }
        }

        @Override // com.aispeech.echo.EchoKernelListener
        public final void onResultBufferReceived(byte[] bArr) {
            a.a(a.this, bArr, bArr.length);
        }

        @Override // com.aispeech.echo.EchoKernelListener
        public final void onVoipBufferReceived(byte[] bArr) {
        }

        @Override // com.aispeech.echo.EchoKernelListener
        public final void onError(AIError aIError) {
            if (a.this.r != null) {
                a.this.r.a(aIError);
            }
        }
    }
}
