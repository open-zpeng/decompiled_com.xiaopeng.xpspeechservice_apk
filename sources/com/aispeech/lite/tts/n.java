package com.aispeech.lite.tts;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.analysis.AnalysisProxy;
import com.aispeech.common.AIFileWriter;
import com.aispeech.common.ByteConvertUtil;
import com.aispeech.common.Log;
import com.aispeech.common.TtsFileWriter;
import com.aispeech.common.Util;
import com.aispeech.common.WavFileWriter;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.AISampleRate;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class n implements m {
    public static String a = "";
    private long A;
    private TtsFileWriter B;
    private l C;
    private String D;
    private boolean E;
    private Handler c;
    private Handler d;
    private HandlerThread e;
    private Looper f;
    private h g;
    private MP3Decoder h;
    private com.aispeech.lite.tts.c i;
    private com.aispeech.lite.tts.e j;
    private com.aispeech.lite.g k;
    private com.aispeech.lite.h.n l;
    private com.aispeech.lite.a m;
    private k n;
    private Context o;
    private String p;
    private long t;
    private long u;
    private long v;
    private long x;
    private com.aispeech.auth.d y;
    private com.aispeech.auth.f z;
    private e b = e.STATE_IDLE;
    private ArrayList<byte[]> q = new ArrayList<>();
    private String r = "";
    private AtomicBoolean s = new AtomicBoolean(false);
    private String w = null;

    /* loaded from: classes.dex */
    public enum e {
        STATE_IDLE,
        STATE_INITIALIZED,
        STATE_STARTED,
        STATE_STOPPED,
        STATE_PAUSED
    }

    static /* synthetic */ void a(n nVar, b bVar) {
        if (nVar.c == null) {
            return;
        }
        Message message = new Message();
        message.what = bVar.a();
        message.obj = null;
        nVar.c.sendMessageDelayed(message, 300L);
    }

    static /* synthetic */ boolean a(d dVar) {
        return (dVar == d.MSG_FEED_DATA_BY_STREAM || dVar == d.MSG_FEED_DATA_BY_CHUNK) ? false : true;
    }

    static /* synthetic */ boolean b(com.aispeech.lite.h.n nVar) {
        return nVar.e() && TextUtils.equals(nVar.d(), "cloud");
    }

    static /* synthetic */ void c(n nVar, String str) {
        File file;
        File file2;
        if (nVar.C == null) {
            nVar.C = "LocalTts".equals(nVar.r) ? l.a() : l.b();
        }
        if (!"LocalTts".equals(nVar.r)) {
            if (TextUtils.isEmpty(str)) {
                file2 = nVar.C.d();
            } else {
                file2 = new File(str);
            }
            if (file2 == null) {
                Log.d(a, "user set mp3File null");
                return;
            }
            String str2 = a;
            Log.d(str2, "user set filePath: " + file2.getAbsolutePath());
            nVar.B = AIFileWriter.createFileWriter(file2);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            file = nVar.C.c();
        } else {
            file = new File(str);
        }
        if (file == null) {
            Log.d(a, "user set wavFile null");
            return;
        }
        String str3 = a;
        Log.d(str3, "user set filePath: " + file.getAbsolutePath());
        nVar.B = WavFileWriter.createWavFileWriter(file, AISampleRate.toAISampleRate(nVar.l.b().getValue()), 1, 2);
    }

    static /* synthetic */ boolean c(n nVar) {
        nVar.E = false;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {
        private static final n a = new n();
    }

    public static n b() {
        return c.a;
    }

    public final void a(k kVar, com.aispeech.lite.a aVar, String str) {
        this.n = kVar;
        this.o = aVar.b();
        this.m = aVar;
        a = str + "Processor";
        this.r = str;
        this.y = com.aispeech.lite.c.a();
        if (TextUtils.equals(str, "LocalTts")) {
            this.z = this.y.a("tts");
        } else {
            this.z = this.y.a(com.aispeech.lite.i.a);
        }
        String str2 = a;
        Log.d(str2, "authstate: " + this.z.toString());
        if (this.z.b()) {
            if (this.d == null) {
                this.e = new HandlerThread(a + "_Thread");
                this.e.start();
                this.f = this.e.getLooper();
                this.d = new Handler(this.f) { // from class: com.aispeech.lite.tts.n.1
                    @Override // android.os.Handler
                    public final void handleMessage(Message message) {
                        com.aispeech.lite.tts.e eVar;
                        super.handleMessage(message);
                        d a2 = d.a(message.what);
                        if (n.a(a2)) {
                            Log.d(n.a, ">>>>>>Event: " + a2.name());
                            Log.d(n.a, "[Current]:" + n.this.b.name());
                        }
                        switch (AnonymousClass3.a[a2.ordinal()]) {
                            case 1:
                                if (n.this.b == e.STATE_IDLE || n.this.b == e.STATE_INITIALIZED) {
                                    n nVar = n.this;
                                    n.a(nVar, nVar.m);
                                    return;
                                }
                                n.a(n.this, a2);
                                return;
                            case 2:
                                if (n.this.b == e.STATE_INITIALIZED || n.this.b == e.STATE_STOPPED) {
                                    n.c(n.this);
                                    n.this.p = Utils.get_recordid();
                                    n.this.l = (com.aispeech.lite.h.n) message.obj;
                                    n nVar2 = n.this;
                                    nVar2.D = nVar2.l.C();
                                    if (TextUtils.isEmpty(n.this.l.n())) {
                                        n.this.a(d.MSG_ERROR, new AIError((int) AIError.ERR_TTS_INVALID_REFTEXT, AIError.ERR_DESCRIPTION_TTS_INVALID_REFTEXT));
                                        return;
                                    }
                                    n.c(n.this, n.this.l.o());
                                    if (n.this.l.B()) {
                                        Log.d(n.a, "Output real pcm audio data");
                                    }
                                    if (n.this.l.z()) {
                                        Log.d(n.a, "auto PLAY audioData");
                                        if (n.this.i == null) {
                                            n.this.i = new com.aispeech.lite.tts.c();
                                            n nVar3 = n.this;
                                            com.aispeech.lite.h.n nVar4 = nVar3.l;
                                            if ("cloud".equals(nVar4.d()) && !nVar4.e()) {
                                                eVar = new com.aispeech.lite.tts.b();
                                            } else if (!nVar4.e()) {
                                                eVar = null;
                                            } else {
                                                eVar = new com.aispeech.lite.tts.a();
                                            }
                                            nVar3.j = eVar;
                                            if (n.this.g == null) {
                                                n.this.g = new h();
                                            }
                                            n.this.g.b();
                                            n.this.j.a(n.this.g);
                                            n.this.j.a(new a());
                                            n.this.j.a(n.this.o, n.this.l.y(), n.this.l.b().getValue());
                                        }
                                        if (n.this.l.A() || Build.VERSION.SDK_INT < 23) {
                                            n.this.j.a(n.this.l.y());
                                        } else {
                                            n.this.j.a(n.this.l.x());
                                        }
                                        n nVar5 = n.this;
                                        nVar5.A = nVar5.j.a();
                                    }
                                    if (n.this.h == null && n.b(n.this.l)) {
                                        n.this.h = new MP3Decoder();
                                        n.this.h.initDecoder();
                                    }
                                    if (!n.this.l.e() && n.this.q != null) {
                                        n.this.q.clear();
                                    }
                                    n.this.s.compareAndSet(true, false);
                                    n.this.a(e.STATE_STARTED);
                                    if (n.this.l instanceof com.aispeech.lite.h.l) {
                                        if (TextUtils.isEmpty(n.this.w)) {
                                            ((com.aispeech.lite.h.l) n.this.l).h(((com.aispeech.lite.c.i) n.this.m).h());
                                        } else {
                                            ((com.aispeech.lite.h.l) n.this.l).h(n.this.w);
                                        }
                                    }
                                    File a3 = n.this.C != null ? n.this.C.a(n.this.l) : null;
                                    if (a3 == null || !a3.exists() || !a3.canRead() || a3.length() <= 0) {
                                        n.this.k.startKernel(n.this.l);
                                    } else {
                                        n.a(n.this, a3);
                                    }
                                    n.this.t = System.currentTimeMillis();
                                    Log.d(n.a, "mStartTime : " + n.this.t);
                                    Log.d(n.a, "TTS.START");
                                    return;
                                }
                                n.a(n.this, a2);
                                return;
                            case 3:
                                if (n.this.b == e.STATE_STARTED || n.this.b == e.STATE_PAUSED) {
                                    if (n.this.g != null) {
                                        n.this.g.b();
                                    }
                                    if (n.this.j != null) {
                                        n.this.j.b();
                                    }
                                    if (n.this.B != null) {
                                        n.this.B.deleteIfOpened();
                                    }
                                    if (n.this.h != null) {
                                        n.this.h.release();
                                        n.this.h = null;
                                    }
                                    if (n.this.k != null) {
                                        n.this.k.cancelKernel();
                                    }
                                    removeMessages(d.MSG_FEED_DATA_BY_STREAM.a());
                                    if (n.this.m != null && n.this.m.e()) {
                                        if (n.this.l.z()) {
                                            n.a(n.this, b.MSG_SPEECH_FINISH);
                                        } else {
                                            n.a(n.this, b.MSG_SYNTHESIZE_FINISH);
                                        }
                                    }
                                    n.this.a(e.STATE_STOPPED);
                                    return;
                                }
                                n.a(n.this, a2);
                                return;
                            case 4:
                                n.this.w = (String) message.obj;
                                if (n.this.b != e.STATE_IDLE) {
                                    n.this.k.set(n.this.w);
                                    return;
                                } else {
                                    n.a(n.this, a2);
                                    return;
                                }
                            case 5:
                                if (n.this.b == e.STATE_PAUSED) {
                                    if (n.this.j != null) {
                                        n.this.j.c();
                                    }
                                    n.this.a(e.STATE_STARTED);
                                    return;
                                }
                                n.a(n.this, a2);
                                return;
                            case 6:
                                if (n.this.b == e.STATE_STARTED) {
                                    if (n.this.j != null) {
                                        n.this.j.d();
                                    }
                                    n.this.a(e.STATE_PAUSED);
                                    return;
                                }
                                n.a(n.this, a2);
                                return;
                            case 7:
                                if (n.this.b != e.STATE_IDLE) {
                                    n.this.a(e.STATE_IDLE);
                                    return;
                                } else {
                                    n.a(n.this, a2);
                                    return;
                                }
                            case 8:
                                AIError aIError = (AIError) message.obj;
                                Log.w(n.a, "TTS.ERROR: " + aIError.toString());
                                if (n.this.b != e.STATE_IDLE) {
                                    if (n.this.j != null) {
                                        n.this.j.b();
                                    }
                                    n.this.k.cancelKernel();
                                    n.this.a(e.STATE_INITIALIZED);
                                    n.this.a(b.MSG_ERROR, message.obj);
                                    n.a(n.this, aIError);
                                    return;
                                }
                                return;
                            case 9:
                                if (n.this.b == e.STATE_STARTED || n.this.b == e.STATE_PAUSED) {
                                    byte[] bArr = (byte[]) message.obj;
                                    if (n.this.l.z()) {
                                        if (n.b(n.this.l)) {
                                            n.a(n.this, bArr);
                                        } else {
                                            n.b(n.this, bArr);
                                        }
                                    } else if (n.this.l.B() && n.this.n != null) {
                                        n.this.n.a(bArr, n.this.D);
                                    }
                                    if (n.this.B != null) {
                                        n.this.B.write(bArr);
                                    }
                                    if (bArr.length == 0) {
                                        if (n.this.B != null) {
                                            n.this.B.close();
                                            if (n.this.C != null && n.this.C.a(n.this.l, new File(n.this.B.getAbsolutePath()))) {
                                                n.this.C.e();
                                            }
                                        }
                                        if (n.this.n != null) {
                                            n.this.n.d(n.this.D);
                                        }
                                        if (!n.this.l.z()) {
                                            n.this.a(e.STATE_INITIALIZED);
                                            return;
                                        }
                                        return;
                                    }
                                    return;
                                }
                                return;
                            case 10:
                                if (n.this.b == e.STATE_STARTED || n.this.b == e.STATE_PAUSED) {
                                    byte[] bArr2 = (byte[]) message.obj;
                                    if (n.this.l.z()) {
                                        String n = n.this.l.n();
                                        if (!TextUtils.isEmpty(n) && bArr2.length != 0) {
                                            n.this.g.a(new i(n, bArr2));
                                            n.this.j.a(false);
                                        }
                                        n.this.g.a(new i(null, null));
                                        n.this.j.a(true);
                                        return;
                                    }
                                    if (n.this.n != null) {
                                        n.this.n.a(bArr2, n.this.D);
                                    }
                                    if (n.this.B != null) {
                                        n.this.B.write(bArr2);
                                        n.this.B.close();
                                        if (n.this.C != null && n.this.C.a(n.this.l, new File(n.this.B.getAbsolutePath()))) {
                                            n.this.C.e();
                                        }
                                    }
                                    if (n.this.n != null) {
                                        n.this.n.d(n.this.D);
                                    }
                                    n.this.a(e.STATE_INITIALIZED);
                                    return;
                                }
                                n.a(n.this, a2);
                                return;
                            case 11:
                                if (n.this.b == e.STATE_STARTED || n.this.b == e.STATE_PAUSED) {
                                    long longValue = ((Long) message.obj).longValue();
                                    if (longValue == n.this.A) {
                                        Log.d(n.a, "MSG_COMPLETED seesionId:" + longValue + "  mAIPlayerSessionId : " + n.this.A);
                                        String str3 = n.a;
                                        StringBuilder sb = new StringBuilder("TotalTime = ");
                                        sb.append(n.this.x);
                                        sb.append(" TTS.FIRST.END.AVERAGE.TIME.RATE : ");
                                        sb.append(n.this.x > 0 ? ((float) (n.this.v - n.this.u)) / ((float) n.this.x) : (float) n.this.x);
                                        Log.d(str3, sb.toString());
                                        n.this.a(b.MSG_SPEECH_FINISH, (Object) null);
                                        n.this.a(e.STATE_INITIALIZED);
                                        return;
                                    }
                                    Log.d(n.a, "player session expired");
                                    return;
                                }
                                n.a(n.this, a2);
                                return;
                            default:
                                return;
                        }
                    }
                };
            }
            if (this.c == null) {
                this.c = new Handler(this.o.getMainLooper()) { // from class: com.aispeech.lite.tts.n.2
                    @Override // android.os.Handler
                    public final void handleMessage(Message message) {
                        super.handleMessage(message);
                        switch (AnonymousClass3.b[b.a(message.what).ordinal()]) {
                            case 1:
                                if (n.this.n != null) {
                                    n.this.n.a(((Integer) message.obj).intValue());
                                    return;
                                }
                                return;
                            case 2:
                                if (n.this.n != null) {
                                    n.this.n.a(n.this.D);
                                    return;
                                }
                                return;
                            case 3:
                                if (n.this.n != null) {
                                    n.this.n.b(n.this.D);
                                    return;
                                }
                                return;
                            case 4:
                                if (n.this.n != null) {
                                    n.this.n.a((AIError) message.obj, n.this.D);
                                    return;
                                }
                                return;
                            case 5:
                                Object[] objArr = (Object[]) message.obj;
                                int intValue = ((Integer) objArr[0]).intValue();
                                int intValue2 = ((Integer) objArr[1]).intValue();
                                boolean booleanValue = ((Boolean) objArr[2]).booleanValue();
                                if (n.this.n != null) {
                                    n.this.n.a(intValue, intValue2, booleanValue);
                                    return;
                                }
                                return;
                            case 6:
                                if (n.this.n != null) {
                                    n.this.n.c(n.this.D);
                                    return;
                                }
                                return;
                            case 7:
                                if (n.this.B != null) {
                                    n.this.B.close();
                                }
                                if (n.this.n != null) {
                                    n.this.n.d(n.this.D);
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    }
                };
            }
            if (TextUtils.equals(str, "LocalTts")) {
                this.m = (com.aispeech.lite.c.i) aVar;
                this.k = new f(this);
            } else if (TextUtils.equals(str, "CloudTts")) {
                this.m = (com.aispeech.lite.c.d) aVar;
                this.k = new com.aispeech.lite.tts.d(this);
                this.k.setProfile(this.y);
            }
            a(d.MSG_NEW, (Object) null);
            return;
        }
        i();
    }

    private synchronized void h() {
        if (this.j != null) {
            this.j.e();
        }
        if (this.h != null) {
            this.h.release();
            this.h = null;
        }
        if (this.k != null) {
            this.k.releaseKernel();
            this.k = null;
        }
        if (this.o != null) {
            this.o = null;
        }
        if (this.B != null) {
            this.B.deleteIfOpened();
            this.B = null;
        }
        if (this.C != null) {
            this.C = null;
        }
        if (this.m != null) {
            this.m = null;
        }
        if (this.l != null) {
            this.l = null;
        }
        if (this.g != null) {
            this.g = null;
        }
        if (this.n != null) {
            this.n = null;
        }
        if (this.i != null) {
            this.i = null;
        }
        if (this.c != null) {
            this.c = null;
        }
        if (this.d != null) {
            this.d = null;
        }
    }

    public final void c() {
        String[] c2;
        Log.i(a, "=======deleteFile======");
        com.aispeech.lite.a aVar = this.m;
        if (aVar != null && (c2 = aVar.c()) != null && c2.length > 0) {
            for (String str : c2) {
                Util.deleteFile(this.m.b(), str);
            }
        }
    }

    /* renamed from: com.aispeech.lite.tts.n$3  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b = new int[b.values().length];

        static {
            try {
                b[b.MSG_INIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[b.MSG_SPEECH_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[b.MSG_SPEECH_FINISH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[b.MSG_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[b.MSG_SPEECH_PROGRESS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[b.MSG_SYNTHESIZE_START.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[b.MSG_SYNTHESIZE_FINISH.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            a = new int[d.values().length];
            try {
                a[d.MSG_NEW.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[d.MSG_START.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[d.MSG_STOP.ordinal()] = 3;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[d.MSG_SET.ordinal()] = 4;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[d.MSG_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[d.MSG_PAUSE.ordinal()] = 6;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[d.MSG_RELEASE.ordinal()] = 7;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[d.MSG_ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[d.MSG_FEED_DATA_BY_STREAM.ordinal()] = 9;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[d.MSG_FEED_DATA_BY_CHUNK.ordinal()] = 10;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[d.MSG_COMPLETED.ordinal()] = 11;
            } catch (NoSuchFieldError e18) {
            }
        }
    }

    public final void a(com.aispeech.lite.h.n nVar) {
        com.aispeech.auth.f fVar = this.z;
        if (fVar != null && fVar.b()) {
            try {
                a(d.MSG_START, nVar.clone());
                return;
            } catch (CloneNotSupportedException e2) {
                e2.printStackTrace();
                return;
            }
        }
        i();
    }

    public final void a(String str) {
        com.aispeech.auth.f fVar = this.z;
        if (fVar != null && fVar.b()) {
            a(d.MSG_SET, str);
        } else {
            i();
        }
    }

    public final void d() {
        com.aispeech.auth.f fVar = this.z;
        if (fVar != null && fVar.b()) {
            a(d.MSG_PAUSE, (Object) null);
        } else {
            i();
        }
    }

    public final void e() {
        com.aispeech.auth.f fVar = this.z;
        if (fVar != null && fVar.b()) {
            a(d.MSG_RESUME, (Object) null);
        } else {
            i();
        }
    }

    public final void f() {
        com.aispeech.auth.f fVar = this.z;
        if (fVar != null && fVar.b()) {
            a(d.MSG_STOP, (Object) null);
        } else {
            i();
        }
    }

    public final void g() {
        com.aispeech.auth.f fVar = this.z;
        if (fVar != null && fVar.b()) {
            h();
            if (this.b != e.STATE_IDLE) {
                a(e.STATE_IDLE);
            }
            a(d.MSG_RELEASE, (Object) null);
            return;
        }
        i();
    }

    /* loaded from: classes.dex */
    class a implements com.aispeech.lite.b.c {
        a() {
        }

        @Override // com.aispeech.lite.b.c
        public final void a() {
            n.this.a(b.MSG_SPEECH_START, (Object) null);
        }

        @Override // com.aispeech.lite.b.c
        public final void a(long j) {
            n.this.a(d.MSG_COMPLETED, Long.valueOf(j));
        }

        @Override // com.aispeech.lite.b.c, com.aispeech.lite.tts.k
        public final void a(AIError aIError, String str) {
            n.this.a(d.MSG_ERROR, aIError);
        }

        @Override // com.aispeech.lite.b.c, com.aispeech.lite.tts.k
        public final void a(int i, int i2, boolean z) {
            n.this.x = i2;
            n.this.a(b.MSG_SPEECH_PROGRESS, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z)});
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        MSG_INIT(0),
        MSG_SPEECH_START(1),
        MSG_SPEECH_FINISH(2),
        MSG_ERROR(3),
        MSG_BUFFER_PROGRESS(4),
        MSG_SPEECH_PROGRESS(5),
        MSG_SYNTHESIZE_START(6),
        MSG_SYNTHESIZE_FINISH(7);
        
        private int i;

        b(int i) {
            this.i = i;
        }

        public final int a() {
            return this.i;
        }

        public static b a(int i) {
            b[] values;
            for (b bVar : values()) {
                if (i == bVar.i) {
                    return bVar;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(b bVar, Object obj) {
        Message.obtain(this.c, bVar.a(), obj).sendToTarget();
    }

    @Override // com.aispeech.lite.tts.m
    public final void a(int i) {
        if (i == 0) {
            a(e.STATE_INITIALIZED);
        }
        a(b.MSG_INIT, Integer.valueOf(i));
    }

    @Override // com.aispeech.lite.tts.m
    public final void a() {
        this.E = true;
    }

    @Override // com.aispeech.lite.tts.m
    public final void a(byte[] bArr, int i) {
        a(bArr, i, "kernel");
    }

    private void a(byte[] bArr, int i, String str) {
        if (this.s.compareAndSet(false, true)) {
            this.u = System.currentTimeMillis();
            Log.d(a, "TTS.FIRST");
            String str2 = a;
            Log.d(str2, "mFirstAudioTime : " + this.u);
            String str3 = a;
            Log.d(str3, "TTS.START.FIRST.DELAY" + (this.u - this.t));
            k kVar = this.n;
            if (kVar != null) {
                kVar.c(this.D);
            }
        }
        if (i == 0 && "kernel".equals(str) && "LocalTts".equals(this.r) && System.currentTimeMillis() - this.u <= 1 && !this.E) {
            Log.e(a, "过滤掉小于等于 1ms 的打断回调！");
            return;
        }
        byte[] bArr2 = new byte[i];
        if (i > 0) {
            System.arraycopy(bArr, 0, bArr2, 0, i);
        }
        AIResult bundleResults = AIResult.bundleResults(1, this.p, bArr2);
        boolean z = i == 0;
        if (z) {
            this.v = System.currentTimeMillis();
            String str4 = a;
            Log.d(str4, "mLastAudioTime : " + this.v);
            String str5 = a;
            Log.d(str5, "TTS.START.END.DELAY ： " + (this.v - this.t));
            String str6 = a;
            Log.d(str6, "TTS.FIRST.END.DELAY ： " + (this.v - this.u));
            Log.d(a, "TTS.END");
            String str7 = a;
            Log.d(str7, "TTS.START.FIRST.AVERAGE.WORD.TIME : " + (((float) (this.u - this.t)) / this.l.n().length()));
            String str8 = a;
            Log.d(str8, "TTS.END.FIRST.AVERAGE.WORD.TIME : " + (((float) (this.v - this.t)) / this.l.n().length()));
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("totalcost", this.v - this.t);
                jSONObject.put("firstcost", this.u - this.t);
                HashMap hashMap = new HashMap();
                hashMap.put(AIError.KEY_RECORD_ID, this.p);
                hashMap.put("mode", "lite");
                if (this.r.equals("LocalTts")) {
                    hashMap.put("module", "local_cost");
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        if (this.m != null) {
                            jSONObject2.put("config", this.m.g());
                        }
                        if (this.l != null) {
                            jSONObject2.put("param", this.l.a_());
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    AnalysisProxy.getInstance().getAnalysisMonitor().cacheData("local_tts_cost", "info", "local_cost", null, jSONObject2, jSONObject, hashMap);
                } else if (this.r.equals("CloudTts")) {
                    hashMap.put("module", "cloud_cost");
                    AnalysisProxy.getInstance().getAnalysisMonitor().cacheData("cloud_tts_cost", "info", "cloud_cost", this.p, new JSONObject(((com.aispeech.lite.h.f) this.l).k()), jSONObject, hashMap);
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        bundleResults.setLast(z);
        a(bundleResults);
    }

    @Override // com.aispeech.lite.tts.m
    public final void a(AIError aIError) {
        a(d.MSG_ERROR, aIError);
    }

    private void a(AIResult aIResult) {
        com.aispeech.lite.h.n nVar = this.l;
        if (nVar == null) {
            return;
        }
        if (nVar.e()) {
            a(d.MSG_FEED_DATA_BY_STREAM, (byte[]) aIResult.getResultObject());
            return;
        }
        this.q.add((byte[]) aIResult.getResultObject());
        if (aIResult.isLast()) {
            Iterator<byte[]> it = this.q.iterator();
            int i = 0;
            while (it.hasNext()) {
                i += it.next().length;
            }
            byte[] bArr = new byte[i];
            Iterator<byte[]> it2 = this.q.iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                byte[] next = it2.next();
                System.arraycopy(next, 0, bArr, i2, next.length);
                i2 += next.length;
            }
            this.q.clear();
            a(d.MSG_FEED_DATA_BY_CHUNK, bArr);
        }
    }

    private void i() {
        AIError aIError = new AIError();
        com.aispeech.auth.f fVar = this.z;
        if (fVar == null) {
            aIError.setErrId(AIError.ERR_SDK_NOT_INIT);
            aIError.setError(AIError.ERR_DESCRIPTION_ERR_SDK_NOT_INIT);
        } else {
            aIError.setErrId(fVar.d().getId());
            aIError.setError(this.z.d().getValue());
        }
        k kVar = this.n;
        if (kVar != null) {
            kVar.a(aIError, this.D);
        }
    }

    protected final void a(e eVar) {
        String str = a;
        Log.d(str, "transfer:" + this.b + " to:" + eVar);
        this.b = eVar;
    }

    /* loaded from: classes.dex */
    public enum d {
        MSG_NEW(1),
        MSG_START(2),
        MSG_STOP(3),
        MSG_PAUSE(4),
        MSG_RESUME(5),
        MSG_RELEASE(6),
        MSG_ERROR(7),
        MSG_FEED_DATA_BY_STREAM(8),
        MSG_COMPLETED(9),
        MSG_SET(10),
        MSG_FEED_DATA_BY_CHUNK(11);
        
        private int l;

        d(int i) {
            this.l = i;
        }

        public final int a() {
            return this.l;
        }

        public static d a(int i) {
            d[] values;
            for (d dVar : values()) {
                if (i == dVar.l) {
                    return dVar;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(d dVar, Object obj) {
        Handler handler;
        if (this.f != null && (handler = this.d) != null) {
            Message.obtain(handler, dVar.a(), obj).sendToTarget();
        }
    }

    static /* synthetic */ void a(n nVar, com.aispeech.lite.a aVar) {
        Log.d(a, "initTtsKernel config=" + aVar);
        String[] c2 = aVar.c();
        if (c2 != null && c2.length > 0) {
            for (String str : c2) {
                Util.copyResource(aVar.b(), str, null);
            }
        }
        com.aispeech.lite.g gVar = nVar.k;
        if (gVar == null) {
            return;
        }
        gVar.newKernel(aVar);
    }

    static /* synthetic */ void a(n nVar, d dVar) {
        String str = a;
        Log.w(str, "Invalid State：" + nVar.b.name() + " when MSG: " + dVar.name());
    }

    static /* synthetic */ void a(n nVar, File file) {
        FileInputStream fileInputStream;
        byte[] bArr = new byte[2048];
        FileInputStream fileInputStream2 = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                    boolean z = true;
                    while (true) {
                        try {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                nVar.a(bArr, 0, "cache");
                                fileInputStream.close();
                                return;
                            } else if (z) {
                                byte[] removeWaveHeader = WavFileWriter.removeWaveHeader(bArr);
                                nVar.a(removeWaveHeader, removeWaveHeader.length, "cache");
                                z = false;
                            } else {
                                nVar.a(bArr, read, "cache");
                            }
                        } catch (Exception e2) {
                            e = e2;
                            fileInputStream2 = fileInputStream;
                            e.printStackTrace();
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            th = th;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream2;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    static /* synthetic */ void a(n nVar, AIError aIError) {
        HashMap hashMap = new HashMap();
        hashMap.put(AIError.KEY_RECORD_ID, nVar.p);
        hashMap.put("mode", "lite");
        if (!nVar.r.equals("LocalTts")) {
            if (!nVar.r.equals("CloudTts")) {
                return;
            }
            hashMap.put("module", "cloud_exception");
            AnalysisProxy.getInstance().getAnalysisMonitor().cacheData("cloud_tts_exception", "info", "cloud_exception", nVar.p, aIError.getInputJSON(), aIError.getOutputJSON(), hashMap);
            return;
        }
        hashMap.put("module", "local_exception");
        JSONObject jSONObject = new JSONObject();
        try {
            com.aispeech.lite.a aVar = nVar.m;
            if (aVar != null) {
                jSONObject.put("config", aVar.g());
            }
            com.aispeech.lite.h.n nVar2 = nVar.l;
            if (nVar2 != null) {
                jSONObject.put("param", nVar2.a_());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        AnalysisProxy.getInstance().getAnalysisMonitor().cacheData("local_tts_exception", "info", "local_exception", null, jSONObject, aIError.getOutputJSON(), hashMap);
    }

    static /* synthetic */ void a(n nVar, byte[] bArr) {
        byte[] bArr2;
        short[] sArr = new short[24096];
        short[] sArr2 = new short[4096];
        int length = (bArr.length / 800) + 1;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = length - 1;
            if (i2 < i3 && bArr.length != 0) {
                bArr2 = new byte[800];
            } else if (i2 < i3 && bArr.length == 0) {
                bArr2 = new byte[0];
            } else {
                bArr2 = new byte[bArr.length % 800];
            }
            System.arraycopy(bArr, i2 * 800, bArr2, 0, bArr2.length);
            MP3Decoder mP3Decoder = nVar.h;
            if (mP3Decoder != null) {
                if (bArr2.length != 0) {
                    i = mP3Decoder.processDecode(bArr2, bArr2.length, sArr, sArr2);
                } else {
                    i = mP3Decoder.processDecode(new byte[800], 800, sArr, sArr2);
                }
            }
            if (i > 0) {
                short[] sArr3 = new short[i];
                System.arraycopy(sArr, 0, sArr3, 0, i <= 24096 ? i : 24096);
                byte[] Shorts2Bytes = ByteConvertUtil.Shorts2Bytes(sArr3);
                if (nVar.l.n() != null) {
                    nVar.g.a(new i(nVar.l.n(), Shorts2Bytes));
                    nVar.j.a(false);
                }
            }
        }
        if (bArr.length != 0) {
            return;
        }
        MP3Decoder mP3Decoder2 = nVar.h;
        if (mP3Decoder2 != null) {
            mP3Decoder2.release();
            nVar.h = null;
        }
        nVar.g.a(new i(null, null));
        nVar.j.a(true);
    }

    static /* synthetic */ void b(n nVar, byte[] bArr) {
        if (bArr.length == 0) {
            nVar.g.a(new i(null, null));
            nVar.j.a(true);
        } else if (nVar.l.n() == null) {
        } else {
            nVar.g.a(new i(nVar.l.n(), bArr));
            nVar.j.a(false);
        }
    }
}
