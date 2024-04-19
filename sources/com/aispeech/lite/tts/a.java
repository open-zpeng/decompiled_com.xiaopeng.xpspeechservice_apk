package com.aispeech.lite.tts;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Build;
import com.aispeech.common.Log;
import com.aispeech.common.Util;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class a implements e {
    private AudioTrack a;
    private int b;
    private int c;
    private int d;
    private h f;
    private int g;
    private int h;
    private ExecutorService i;
    private com.aispeech.lite.e j;
    private RunnableC0007a k;
    private com.aispeech.lite.b.c l;
    private Timer n;
    private boolean e = false;
    private boolean m = false;

    static /* synthetic */ void a(a aVar, long j) {
        int audioFormat = aVar.c / (aVar.d * aVar.a.getAudioFormat());
        long playbackHeadPosition = audioFormat - (aVar.a.getPlaybackHeadPosition() / aVar.d);
        Log.d("AIAudioTrack", "sleep totalFrame: " + audioFormat + "  remainFrame:" + playbackHeadPosition + " headPosition:" + aVar.a.getPlaybackHeadPosition());
        aVar.f.b();
        aVar.a.stop();
        if (playbackHeadPosition > 0) {
            if (playbackHeadPosition > 10) {
                playbackHeadPosition = 10;
            }
            try {
                StringBuilder sb = new StringBuilder("sleep ");
                long j2 = playbackHeadPosition * 50;
                sb.append(j2);
                sb.append(" ms for sync with onComplete");
                Log.d("AIAudioTrack", sb.toString());
                Thread.sleep(j2);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        int i = audioFormat * 50;
        aVar.l.a(i, i, true);
        aVar.l.a(j);
    }

    static /* synthetic */ void i(a aVar) {
        aVar.l.a();
    }

    @Override // com.aispeech.lite.tts.e
    public final void a(Context context, int i, int i2) {
        do {
            if (this.a == null) {
                this.g = i2;
                this.h = i;
                this.b = AudioTrack.getMinBufferSize(i2, 4, 2);
                this.a = new AudioTrack(i, i2, 4, 2, this.b, 1);
                this.d = (i2 / 1000) * 50;
                this.a.setPlaybackPositionUpdateListener(new b());
                this.a.setPositionNotificationPeriod(this.d);
                Log.i("AIAudioTrack", "AudioTrack Output stream is " + i + " , mMinBufferSize is:" + this.b + " , the sampleRate is : " + i2 + ",  PeriodInFrame is :" + this.d);
            }
        } while (f());
        this.m = false;
        this.j = new com.aispeech.lite.e("AIAudioTrack", 5);
        this.i = Executors.newFixedThreadPool(1, this.j);
    }

    private boolean f() {
        Log.d("AIAudioTrack", "checkReinitializeAudioTrack()");
        if (this.a == null) {
            this.m = true;
        }
        Log.d("AIAudioTrack", "AIAudioTrack.getState() state:" + this.a.getState());
        if (this.a.getState() == 0) {
            if (this.m) {
                this.m = false;
                Log.e("AIAudioTrack", "Failed to initialize audiotrack again. Don't try again");
            }
            Log.i("AIAudioTrack", "Retry initializing Audiotrack.");
            this.m = true;
        }
        return this.m;
    }

    @Override // com.aispeech.lite.tts.e
    public final long a() {
        AudioTrack audioTrack = this.a;
        if (audioTrack != null && audioTrack.getState() != 0) {
            Log.d("AIAudioTrack", "AIAudioTrack.play()");
            if (this.a.getPlayState() == 1) {
                this.e = false;
                this.a.setPlaybackPositionUpdateListener(new b());
                this.a.setPositionNotificationPeriod(this.d);
                this.a.play();
                long generateRandom = Util.generateRandom(8);
                this.k = new RunnableC0007a(this.i, generateRandom);
                this.k.b();
                return generateRandom;
            }
            Log.w("AIAudioTrack", "AudioTrack not response play() because is in PlayState:" + this.a.getPlayState());
        } else {
            Log.w("AIAudioTrack", "AudioTrack not response play() because is in state:" + this.a.getState());
        }
        return 0L;
    }

    @Override // com.aispeech.lite.tts.e
    public final void b() {
        AudioTrack audioTrack = this.a;
        if (audioTrack != null) {
            if (audioTrack.getPlayState() != 1) {
                Log.d("AIAudioTrack", "AIAudioTrack.stop()");
                RunnableC0007a runnableC0007a = this.k;
                if (runnableC0007a != null) {
                    runnableC0007a.a();
                    return;
                } else {
                    Log.d("AIAudioTrack", "mFeedTask is null");
                    return;
                }
            }
            Log.w("AIAudioTrack", "AudioTrack not response stop() because is in PlayState:" + this.a.getPlayState());
        }
    }

    @Override // com.aispeech.lite.tts.e
    public final void c() {
        AudioTrack audioTrack = this.a;
        if (audioTrack != null) {
            if (audioTrack.getPlayState() == 2) {
                Log.d("AIAudioTrack", "AIAudioTrack.resume()");
                this.a.play();
                RunnableC0007a runnableC0007a = this.k;
                if (runnableC0007a != null) {
                    runnableC0007a.c();
                    return;
                }
                return;
            }
            Log.w("AIAudioTrack", "AudioTrack not response resume() because is in PlayState:" + this.a.getPlayState());
        }
    }

    @Override // com.aispeech.lite.tts.e
    public final void d() {
        AudioTrack audioTrack = this.a;
        if (audioTrack != null) {
            if (audioTrack.getPlayState() == 3) {
                Log.d("AIAudioTrack", "AIAudioTrack.pause()");
                this.a.pause();
                return;
            }
            Log.w("AIAudioTrack", "AudioTrack not response pause() because is in PlayState:" + this.a.getPlayState());
        }
    }

    @Override // com.aispeech.lite.tts.e
    public final void e() {
        AudioTrack audioTrack = this.a;
        if (audioTrack != null) {
            audioTrack.release();
            this.a = null;
        }
        ExecutorService executorService = this.i;
        if (executorService != null) {
            executorService.shutdown();
            this.i = null;
        }
        if (this.j != null) {
            this.j = null;
        }
    }

    @Override // com.aispeech.lite.tts.e
    public final void a(boolean z) {
        this.c = this.f.c();
        Log.d("AIAudioTrack", "TotalDataSize:" + this.c);
        this.e = z;
        h();
    }

    @Override // com.aispeech.lite.tts.e
    public final void a(h hVar) {
        Log.d("AIAudioTrack", "queue  " + hVar);
        this.f = hVar;
    }

    @Override // com.aispeech.lite.tts.e
    public final void a(int i) {
        Log.d("AIAudioTrack", "streamType is: " + i);
        if (i != this.h) {
            this.h = i;
            g();
        }
    }

    @Override // com.aispeech.lite.tts.e
    @TargetApi(23)
    public final void a(AudioAttributes audioAttributes) {
        Log.d("AIAudioTrack", "setAudioAttributes usage: " + audioAttributes.getUsage() + " contentType :" + audioAttributes.getContentType());
        b(audioAttributes);
    }

    @Override // com.aispeech.lite.tts.e
    public final void a(com.aispeech.lite.b.c cVar) {
        this.l = cVar;
    }

    @TargetApi(23)
    private void b(AudioAttributes audioAttributes) {
        do {
            AudioTrack audioTrack = this.a;
            if (audioTrack != null) {
                audioTrack.release();
            }
            if (Build.VERSION.SDK_INT >= 23) {
                this.a = new AudioTrack(audioAttributes, new AudioFormat.Builder().setSampleRate(this.g).setEncoding(2).setChannelMask(4).build(), this.b, 1, 0);
            }
        } while (f());
        this.m = false;
        Log.i("AIAudioTrack", "Reloaded AudioTrack  audioAttributes output stream is " + this.h + " , mMinBufferSize is:" + this.b + " , the sampleRate is : " + this.g + ",  PeriodInFrame is :" + this.d);
    }

    private void g() {
        do {
            AudioTrack audioTrack = this.a;
            if (audioTrack != null) {
                audioTrack.release();
            }
            this.a = new AudioTrack(this.h, this.g, 4, 2, this.b, 1);
            this.a.setPlaybackPositionUpdateListener(new b());
            this.a.setPositionNotificationPeriod(this.d);
        } while (f());
        this.m = false;
        Log.i("AIAudioTrack", "Reloaded AudioTrack output stream is " + this.h + " , mMinBufferSize is:" + this.b + " , the sampleRate is : " + this.g + ",  PeriodInFrame is :" + this.d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements AudioTrack.OnPlaybackPositionUpdateListener {
        b() {
        }

        @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
        public final void onMarkerReached(AudioTrack audioTrack) {
            Log.d("AIAudioTrack", "on marker reached");
        }

        @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
        public final void onPeriodicNotification(AudioTrack audioTrack) {
            if (audioTrack.getPlayState() == 3) {
                int playbackHeadPosition = audioTrack.getPlaybackHeadPosition() / a.this.d;
                int audioFormat = a.this.c / (a.this.d * a.this.a.getAudioFormat());
                a.this.l.a(playbackHeadPosition * 50, audioFormat * 50, a.this.e);
                if (!a.this.e && playbackHeadPosition == audioFormat && a.this.k != null) {
                    a.g(a.this);
                }
            }
        }
    }

    private void h() {
        Timer timer = this.n;
        if (timer != null) {
            timer.cancel();
            this.n = null;
            Log.i("AIAudioTrack", "---cancelSynInterruptTimer()");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.aispeech.lite.tts.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0007a implements Runnable {
        private final ExecutorService d;
        private final long g;
        private boolean a = false;
        private final AtomicBoolean b = new AtomicBoolean(false);
        private Boolean c = false;
        private Object e = new Object();
        private final Semaphore f = new Semaphore(0);

        public RunnableC0007a(ExecutorService executorService, long j) {
            this.d = executorService;
            this.g = j;
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x0136, code lost:
            if (r7.h.a.getState() != 1) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x0157, code lost:
            if (r7.h.a.getState() != 1) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x0159, code lost:
            r7.h.a.flush();
            com.aispeech.common.Log.d("AIAudioTrack", "audioTrack flushed");
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 423
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.aispeech.lite.tts.a.RunnableC0007a.run():void");
        }

        public final void a() {
            synchronized (this.e) {
                if (this.c.booleanValue()) {
                    this.a = true;
                    c();
                    a.this.f.a(new i(null, null));
                    try {
                        Log.i("AIAudioTrack", "Semaphore acquire : stop start.");
                        this.f.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    a.this.f.b();
                } else {
                    Log.e("AIAudioTrack", "task is not running");
                }
            }
        }

        public final void b() {
            synchronized (this.e) {
                this.d.execute(this);
                this.c = true;
            }
        }

        public final void c() {
            synchronized (this) {
                notify();
            }
        }

        private void a(String str) {
            this.f.release();
            Log.i("AIAudioTrack", "Semaphore release : " + str);
        }
    }

    static /* synthetic */ void g(a aVar) {
        Log.i("AIAudioTrack", "---startSynInterruptTimer()");
        aVar.h();
        aVar.n = new Timer("syn_interrupt_timer");
        aVar.n.schedule(new TimerTask() { // from class: com.aispeech.lite.tts.a.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public final void run() {
                if (a.this.f.c() != 0) {
                    a.this.b();
                    a aVar2 = a.this;
                    a.a(aVar2, aVar2.k.g);
                    Log.i("AIAudioTrack", "Handling the situation of the combined en route network terminal.");
                }
            }
        }, 1000L);
    }
}
