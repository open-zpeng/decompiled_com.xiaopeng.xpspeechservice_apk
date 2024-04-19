package com.aispeech.lite;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.aispeech.AIError;
import com.aispeech.analysis.AnalysisProxy;
import com.aispeech.common.AITimer;
import com.aispeech.common.FileUtil;
import com.aispeech.common.Log;
import com.aispeech.common.Util;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.h.m;
import com.aispeech.lite.h.o;
import com.aispeech.lite.speech.ProcessorListener;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
/* loaded from: classes.dex */
public abstract class h implements com.aispeech.lite.b.d {
    private ScheduledExecutorService A;
    private com.aispeech.auth.f B;
    private e C;
    private c D;
    protected com.aispeech.lite.b.e b;
    protected com.aispeech.lite.a f;
    protected JSONObject q;
    protected com.aispeech.auth.d s;
    private ProcessorListener t;
    private Handler u;
    private HandlerThread v;
    private Looper w;
    private Context x;
    private CyclicBarrier y;
    private String z;
    public String a = "BaseProcessor";
    protected boolean c = false;
    protected int d = 1;
    protected volatile b e = b.STATE_IDLE;
    protected Queue<byte[]> g = new LinkedList();
    protected int h = 0;
    protected int i = 192000;
    protected int j = 500;
    protected volatile boolean k = true;
    protected volatile boolean l = true;
    protected Object m = new Object();
    protected String n = BuildInfoUtils.BID_WAN;
    protected String o = "aicar";
    protected volatile boolean p = false;
    protected long r = 0;

    /* loaded from: classes.dex */
    public enum b {
        STATE_IDLE,
        STATE_NEWED,
        STATE_RUNNING,
        STATE_WAITING,
        STATE_ERROR,
        STATE_CANCELED
    }

    protected abstract void a(a aVar, Message message);

    public abstract void m();

    public abstract void n();

    public h() {
        new LinkedList();
        new Object();
        this.A = Executors.newSingleThreadScheduledExecutor();
        this.s = com.aispeech.lite.c.a();
        this.C = null;
        this.D = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean a() {
        com.aispeech.auth.f fVar = this.B;
        return fVar != null && fVar.b();
    }

    public final void a(ProcessorListener processorListener, Context context, String str, String str2) {
        this.a = str;
        String str3 = this.a;
        Log.i(str3, "new " + this.a);
        this.c = false;
        this.t = processorListener;
        this.x = context;
        this.B = this.s.a(str2);
        String str4 = this.a;
        Log.d(str4, "auth state: " + this.B.toString());
        if (this.B.b()) {
            String str5 = this.a;
            Log.d(str5, "threadCount: " + this.d);
            if (this.y == null) {
                this.y = new CyclicBarrier(this.d, new d());
            }
            if (this.u != null) {
                return;
            }
            this.v = new HandlerThread(this.a + "_Thread");
            this.v.start();
            this.w = this.v.getLooper();
            this.u = new Handler(this.w) { // from class: com.aispeech.lite.h.1
                @Override // android.os.Handler
                public final void handleMessage(Message message) {
                    super.handleMessage(message);
                    a a2 = a.a(message.what);
                    if ((a2 == a.MSG_RAW_RECEIVE_DATA || a2 == a.MSG_RESULT_RECEIVE_DATA || a2 == a.MSG_VOLUME_CHANGED || a2 == a.MSG_VAD_RECEIVE_DATA || a2 == a.MSG_SET || a2 == a.MSG_ECHO_RECEIVE_DATA) ? false : true) {
                        String str6 = h.this.a;
                        Log.d(str6, ">>>>>>Event: " + a2.name());
                        String str7 = h.this.a;
                        Log.d(str7, "[Current]:" + h.this.e.name());
                    }
                    h.this.a(a2, message);
                }
            };
            return;
        }
        i();
    }

    public final void a(String str) {
        if (a()) {
            a(a.MSG_SET, str);
        } else {
            i();
        }
    }

    public final void b(String str) {
        if (a()) {
            a(a.MSG_UPDATE, str);
        } else {
            i();
        }
    }

    public final void c(String str) {
        if (a()) {
            a(a.MSG_UPDATE_VOCAB, str);
        } else {
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String b() {
        return this.z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void d(String str) {
        a(str, (m) null, (o) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(m mVar, o oVar) {
        a(Utils.get_recordid(), mVar, oVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(String str, m mVar, o oVar) {
        String str2 = this.a;
        Log.d(str2, "sync recorderId : " + str);
        this.z = str;
        if (mVar != null) {
            mVar.e(str);
        }
        if (oVar != null) {
            oVar.e(str);
        }
    }

    public final void a(byte[] bArr, int i) {
        if (a()) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            if (this.a.equals("FespCarProcessor") || this.a.equals("AecAndFdmProcessor")) {
                a(a.MSG_RAW_RECEIVE_DATA, bArr2);
                return;
            } else {
                a(a.MSG_RESULT_RECEIVE_DATA, bArr2);
                return;
            }
        }
        i();
    }

    public final void c() {
        if (a()) {
            a(a.MSG_STOP, (Object) null);
        } else {
            i();
        }
    }

    public final void d() {
        if (a()) {
            a(a.MSG_CANCEL, (Object) null);
        } else {
            i();
        }
    }

    public final void e() {
        if (a()) {
            a(a.MSG_RELEASE, (Object) null);
        } else {
            i();
        }
    }

    @Override // com.aispeech.lite.b.d
    public final void f() {
        a(a.MSG_RECORDER_START, (Object) null);
        this.t.onReadyForSpeech();
    }

    @Override // com.aispeech.lite.b.d
    public final void b(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        a(a.MSG_RAW_RECEIVE_DATA, bArr2);
    }

    @Override // com.aispeech.lite.b.d
    public final void c(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        a(a.MSG_RESULT_RECEIVE_DATA, bArr2);
    }

    @Override // com.aispeech.lite.b.d
    public final void a(AIError aIError) {
        a(a.MSG_ERROR, aIError);
    }

    /* loaded from: classes.dex */
    public enum a {
        MSG_NEW(1),
        MSG_START(2),
        MSG_RECORDER_START(3),
        MSG_RAW_RECEIVE_DATA(4),
        MSG_VAD_RECEIVE_DATA(5),
        MSG_STOP(6),
        MSG_CANCEL(7),
        MSG_RELEASE(8),
        MSG_ERROR(9),
        MSG_VAD_START(10),
        MSG_VAD_END(11),
        MSG_VOLUME_CHANGED(12),
        MSG_RESULT(13),
        MSG_RESULT_RECEIVE_DATA(14),
        MSG_DOA(15),
        MSG_SET(16),
        MSG_VPRINT_RESULT(17),
        MSG_GENDER_RESULT(18),
        MSG_WAKEUP_DATA(19),
        MSG_ASR_RESULT(20),
        MSG_ECHO_RECEIVE_DATA(21),
        MSG_UPDATE(22),
        MSG_UPDATE_VOCAB(23),
        MSG_VPRINT_DATA(24),
        MSG_NLG_END(25),
        MSG_CLOSE(26),
        MSG_FEEDBACK(27),
        MSG_TRIGGER_INTENT(28),
        MSG_VPRINT_NOTIFY(29),
        MSG_VPRINT_TLV(30),
        MSG_ASYNC(31),
        MSG_FEEDBACK_2_PRIV_CLOUD(32);
        
        private int G;

        a(int i) {
            this.G = i;
        }

        public final int a() {
            return this.G;
        }

        public static a a(int i) {
            a[] values;
            for (a aVar : values()) {
                if (i == aVar.G) {
                    return aVar;
                }
            }
            return null;
        }
    }

    public void g() {
        if (this.B != null) {
            this.B = null;
        }
        ScheduledExecutorService scheduledExecutorService = this.A;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            this.A = null;
        }
        if (this.b != null) {
            this.b = null;
        }
        if (this.x != null) {
            this.x = null;
        }
        Looper looper = this.w;
        if (looper != null) {
            looper.quit();
            this.w = null;
        }
        if (this.u != null) {
            this.u = null;
        }
        HandlerThread handlerThread = this.v;
        if (handlerThread != null) {
            handlerThread.quit();
            this.v = null;
        }
        if (this.y != null) {
            this.y = null;
        }
        if (this.C != null) {
            this.C = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int a(com.aispeech.lite.a aVar) {
        String[] c2 = aVar.c();
        if (c2 != null && c2.length > 0) {
            for (String str : c2) {
                Log.d(this.a, "resname = " + str);
                if (Util.copyResource(this.x, str, null) == -1) {
                    Log.e(this.a, "file " + str + " not found in assest folder, Did you forget add it?");
                    return 0;
                }
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(a aVar, Object obj) {
        Handler handler;
        if (this.w != null && (handler = this.u) != null) {
            Message.obtain(handler, aVar.a(), obj).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void e(String str) {
        String str2 = this.a;
        Log.w(str2, "Invalid State：" + this.e.name() + " when MSG: " + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(b bVar) {
        String str = this.a;
        Log.d(str, "transfer:" + this.e + " to:" + bVar);
        this.e = bVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.aispeech.lite.b.e a(com.aispeech.lite.b.d dVar) {
        Log.i(this.a, "createRecorder");
        m mVar = new m();
        return com.aispeech.lite.b.b.a(mVar.b(), mVar.s(), mVar.q(), dVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(com.aispeech.lite.b.d dVar) {
        Log.i(this.a, "startRecorder");
        com.aispeech.lite.b.e eVar = this.b;
        if (eVar != null) {
            eVar.a(dVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void h() {
        com.aispeech.lite.b.e eVar = this.b;
        if (eVar != null) {
            eVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c(com.aispeech.lite.b.d dVar) {
        Log.i(this.a, "unRegisterRecorderIfIsRecording");
        c cVar = this.D;
        if (cVar != null) {
            cVar.cancel();
            this.D = null;
        }
        j();
        com.aispeech.lite.b.e eVar = this.b;
        if (eVar != null && eVar.c(dVar)) {
            Log.d(this.a, "detect recording , stop recorder!");
            this.b.b(dVar);
        }
    }

    public final void a(int i) {
        if (i == -1) {
            this.c = true;
        }
        try {
            if (this.y != null) {
                this.y.await();
            }
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        } catch (BrokenBarrierException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void i() {
        AIError aIError = new AIError();
        com.aispeech.auth.f fVar = this.B;
        if (fVar == null || fVar.d() == null) {
            aIError.setErrId(AIError.ERR_SDK_NOT_INIT);
            aIError.setError(AIError.ERR_DESCRIPTION_ERR_SDK_NOT_INIT);
        } else {
            aIError.setErrId(this.B.d().getId());
            aIError.setError(this.B.d().getValue());
        }
        ProcessorListener processorListener = this.t;
        if (processorListener != null) {
            processorListener.onError(aIError);
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            String str = h.this.a;
            Log.d(str, "SET_THREAD_AFFINITY cpuId is : " + com.aispeech.lite.c.u);
            if (com.aispeech.lite.c.u > 0) {
                Utils.jni_duilite_set_thread_affinity(com.aispeech.lite.c.u);
            }
            if (h.this.c) {
                h.this.a(b.STATE_IDLE);
                h.this.t.onInit(-1);
                return;
            }
            h.this.a(b.STATE_NEWED);
            h.this.t.onInit(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(m mVar) {
        e eVar = this.C;
        if (eVar != null) {
            eVar.cancel();
            this.C = null;
        }
        this.C = new e();
        try {
            if (mVar.r() > 0) {
                Log.d(this.a, "VAD.TIMEOUT");
                String str = this.a;
                Log.d(str, "start no Speech timeout task time is set to:" + mVar.r());
                AITimer.getInstance().schedule(this.C, (long) mVar.r());
            }
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void j() {
        e eVar = this.C;
        if (eVar != null) {
            eVar.cancel();
            this.C = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends TimerTask {
        e() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            h.this.m();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(m mVar) {
        c cVar = this.D;
        if (cVar != null) {
            cVar.cancel();
            this.D = null;
        }
        if (mVar.q() > 0) {
            this.D = new c();
            try {
                AITimer.getInstance().schedule(this.D, mVar.q() * 1000);
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* loaded from: classes.dex */
    class c extends TimerTask {
        c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            h.this.n();
            Log.w(h.this.a, "max speech timeout");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void k() {
        try {
            this.A.schedule(new f(), 500L, TimeUnit.MILLISECONDS);
            Log.d(this.a, "need to wait 500ms");
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean l() {
        return AnalysisProxy.getInstance().getAnalysisAudio(1).isUploadEnable() || AnalysisProxy.getInstance().getAnalysisAudio(2).isUploadEnable();
    }

    /* loaded from: classes.dex */
    class f extends TimerTask {
        f() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            h.this.z = Utils.get_recordid();
            int i = 2;
            if (!h.this.l && AnalysisProxy.getInstance().getAnalysisAudio(1).isUploadEnable()) {
                Log.d(h.this.a, "pre wakeup happened, prepare to upload");
                i = 1;
            } else if (h.this.l && AnalysisProxy.getInstance().getAnalysisAudio(2).isUploadEnable()) {
                Log.d(h.this.a, "real wakeup happened, prepare to upload");
            } else {
                Log.d(h.this.a, "invalid upload mode, ignore upload");
                return;
            }
            h.this.k = false;
            HashMap hashMap = new HashMap();
            hashMap.put("mic_matrix", h.this.n);
            hashMap.put("scene", h.this.o);
            hashMap.put("wakeup_log_type", Integer.valueOf(i));
            HashMap hashMap2 = new HashMap();
            hashMap2.put("wakeupType", Integer.valueOf(i));
            hashMap2.put("audioUrl", h.this.z + ".pcm");
            hashMap2.put(AIError.KEY_RECORD_ID, h.this.z);
            hashMap2.put("mode", "lite");
            hashMap2.put("module", "local_wakeup");
            hashMap.put("upload_entry", hashMap2);
            AnalysisProxy.getInstance().getAnalysisAudio(i).cacheData("local_wakeup_input_output", "info", "local_wakeup", h.this.z, h.this.f.g(), h.this.q, hashMap);
            h hVar = h.this;
            h.a(hVar, i, hVar.z);
            AnalysisProxy.getInstance().getAnalysisAudio(i).start();
        }
    }

    static /* synthetic */ void a(h hVar, int i, String str) {
        String str2 = com.aispeech.lite.c.b().getExternalCacheDir().getPath() + File.separator + "upload" + File.separator + (i == 2 ? "wakeup" : "preWakeup") + File.separator + str + ".pcm";
        FileUtil fileUtil = new FileUtil(com.aispeech.lite.c.b());
        fileUtil.createFile(str2);
        synchronized (hVar.m) {
            while (hVar.g.peek() != null) {
                fileUtil.write(hVar.g.poll());
            }
            hVar.g.clear();
            hVar.h = 0;
        }
        Log.d(hVar.a, "wakeupAudio prepared ok!");
        hVar.k = true;
        fileUtil.closeFile();
        AnalysisProxy.getInstance().getAnalysisAudio(i).cacheFile(str2);
    }
}
