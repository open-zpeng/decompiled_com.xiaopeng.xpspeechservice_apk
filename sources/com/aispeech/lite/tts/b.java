package com.aispeech.lite.tts;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import com.aispeech.AIError;
import com.aispeech.common.AITimer;
import com.aispeech.common.Log;
import com.aispeech.common.Util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class b implements com.aispeech.lite.tts.e {
    private Context a;
    private MediaPlayer b;
    private h c;
    private a d;
    private com.aispeech.lite.b.c e;
    private long g;
    private boolean f = false;
    private e h = null;

    @Override // com.aispeech.lite.tts.e
    public final void a(Context context, int i, int i2) {
        this.a = context;
        if (this.b == null) {
            this.b = new MediaPlayer();
            this.b.setOnCompletionListener(new C0008b());
            this.b.setOnErrorListener(new c());
            this.b.setOnPreparedListener(new d());
            this.b.setAudioStreamType(i);
        }
        if (this.d == null) {
            this.d = new a();
        }
    }

    @Override // com.aispeech.lite.tts.e
    public final long a() {
        MediaPlayer mediaPlayer = this.b;
        if (mediaPlayer != null) {
            if (!mediaPlayer.isPlaying()) {
                Log.d("AIMediaPlayer", "AIMediaPlayer.play()");
                a aVar = this.d;
                if (aVar != null) {
                    aVar.c();
                }
                f();
                this.g = Util.generateRandom(8);
            } else {
                Log.w("AIMediaPlayer", "MediaPlayer not response play() because is in playing!");
            }
        }
        return this.g;
    }

    @Override // com.aispeech.lite.tts.e
    public final void b() {
        if (this.b != null && this.f) {
            g();
            Log.d("AIMediaPlayer", "AIMediaPlayer.stop()");
            this.b.stop();
            this.f = false;
            a aVar = this.d;
            if (aVar != null) {
                aVar.b();
                return;
            }
            return;
        }
        Log.d("AIMediaPlayer", "media player not initialized , so not response to stop");
    }

    @Override // com.aispeech.lite.tts.e
    public final void c() {
        if (this.b != null && this.f) {
            Log.d("AIMediaPlayer", "AIMediaPlayer.resume()");
            if (!this.b.isPlaying()) {
                Log.d("AIMediaPlayer", "Duration:" + this.b.getDuration());
                if (this.b.getDuration() > 0) {
                    this.b.start();
                    f();
                    return;
                }
                return;
            }
            return;
        }
        Log.d("AIMediaPlayer", "media player not initialized , so not response to resume");
    }

    @Override // com.aispeech.lite.tts.e
    public final void d() {
        if (this.b != null && this.f) {
            Log.d("AIMediaPlayer", "AIMediaPlayer.pause()");
            if (this.b.isPlaying()) {
                this.b.pause();
            }
            g();
            return;
        }
        Log.d("AIMediaPlayer", "media player not initialized , so not response to pause");
    }

    @Override // com.aispeech.lite.tts.e
    public final void e() {
        g();
        Log.d("AIMediaPlayer", "AIMediaPlayer.release()");
        MediaPlayer mediaPlayer = this.b;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        a aVar = this.d;
        if (aVar != null) {
            aVar.destroy();
        }
        File cacheDir = this.a.getCacheDir();
        File file = new File(cacheDir, "AISpeech_tts_" + hashCode() + ".cache");
        if (file.exists()) {
            file.delete();
        }
        if (this.a != null) {
            this.a = null;
        }
    }

    @Override // com.aispeech.lite.tts.e
    public final void a(h hVar) {
        this.c = hVar;
    }

    @Override // com.aispeech.lite.tts.e
    public final void a(com.aispeech.lite.b.c cVar) {
        this.e = cVar;
    }

    @Override // com.aispeech.lite.tts.e
    public final void a(boolean z) {
        Log.d("AIMediaPlayer", "TotalDataSize:" + this.c.c());
    }

    @Override // com.aispeech.lite.tts.e
    public final void a(int i) {
        if (this.b != null) {
            Log.d("AIMediaPlayer", "streamType is: " + i);
            this.b.setAudioStreamType(i);
        }
    }

    @Override // com.aispeech.lite.tts.e
    @TargetApi(23)
    public final void a(AudioAttributes audioAttributes) {
        if (this.b != null) {
            Log.d("AIMediaPlayer", "usage : " + audioAttributes.getUsage() + "contentType" + audioAttributes.getContentType());
            this.b.setAudioAttributes(audioAttributes);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File a(byte[] bArr) {
        File cacheDir = this.a.getCacheDir();
        File file = new File(cacheDir, "AISpeech_tts_" + hashCode() + ".cache");
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bufferedOutputStream.write(bArr);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return file;
    }

    /* renamed from: com.aispeech.lite.tts.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0008b implements MediaPlayer.OnCompletionListener {
        C0008b() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public final void onCompletion(MediaPlayer mediaPlayer) {
            int duration = mediaPlayer.getDuration();
            b.this.e.a(duration, duration, true);
            b.this.d.a();
        }
    }

    /* loaded from: classes.dex */
    class c implements MediaPlayer.OnErrorListener {
        c() {
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            b.this.f = false;
            com.aispeech.lite.b.c cVar = b.this.e;
            cVar.a(new AIError((int) AIError.ERR_TTS_MEDIAPLAYER, "合成MediaPlayer播放器错误:what(" + i + ")  extra(" + i2 + ")"), "");
            return false;
        }
    }

    /* loaded from: classes.dex */
    class d implements MediaPlayer.OnPreparedListener {
        d() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public final void onPrepared(MediaPlayer mediaPlayer) {
            b.this.f = true;
            mediaPlayer.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends Thread {
        private boolean a;
        private boolean b;
        private boolean c;
        private AtomicBoolean d;

        public a() {
            super("FeedTask Thread");
            this.a = false;
            this.b = false;
            this.c = false;
            this.d = new AtomicBoolean(false);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Log.d("AIMediaPlayer", "Feed Task begin!");
            while (true) {
                g a = b.this.c.a();
                if (a != null) {
                    if (a.a() != null) {
                        if (this.d.compareAndSet(false, true)) {
                            b.this.e.a();
                        }
                        File file = null;
                        if (a instanceof j) {
                            file = (File) a.b();
                        } else if (a instanceof i) {
                            file = b.this.a((byte[]) a.b());
                        }
                        b.a(b.this, file);
                        this.b = true;
                        if (this.b) {
                            synchronized (this) {
                                try {
                                    Log.d("AIMediaPlayer", "Feed Task stopped for waiting play completion!");
                                    wait();
                                    Log.d("AIMediaPlayer", "Feed Task restared!");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        try {
                            b.this.b();
                        } catch (IllegalStateException e2) {
                            e2.printStackTrace();
                        }
                        b.this.e.a(b.this.g);
                        b();
                    }
                    if (this.c) {
                        Log.d("AIMediaPlayer", "Feed Task terminated!");
                        return;
                    } else if (this.a) {
                        this.d.set(false);
                        synchronized (this) {
                            try {
                                Log.d("AIMediaPlayer", "Feed Task stopped!");
                                wait();
                                Log.d("AIMediaPlayer", "Feed Task stared!");
                            } catch (InterruptedException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }

        public final void a() {
            this.b = false;
            synchronized (this) {
                notifyAll();
            }
        }

        public final void b() {
            this.a = true;
            b.this.c.b();
            if (this.b) {
                a();
            }
        }

        public final void c() {
            this.a = false;
            if (!isAlive()) {
                super.start();
            }
            synchronized (this) {
                notifyAll();
            }
        }

        @Override // java.lang.Thread
        public final void destroy() {
            this.c = true;
            b();
            b.this.c.a(new j());
            c();
        }
    }

    private void f() {
        g();
        this.h = new e(this, (byte) 0);
        try {
            AITimer.getInstance().schedule(this.h, 0L, 50L);
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
    }

    private void g() {
        e eVar = this.h;
        if (eVar != null) {
            eVar.cancel();
            this.h = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends TimerTask {
        private e() {
        }

        /* synthetic */ e(b bVar, byte b) {
            this();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            try {
                if (b.this.b.isPlaying()) {
                    b.this.e.a(b.this.b.getCurrentPosition(), b.this.b.getDuration(), true);
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
                b.this.f = false;
            }
        }
    }

    static /* synthetic */ void a(b bVar, File file) {
        FileInputStream fileInputStream;
        try {
            bVar.b.reset();
        } catch (IllegalStateException e2) {
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (IOException | IllegalArgumentException | IllegalStateException e3) {
                    e = e3;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                bVar.b.setDataSource(fileInputStream.getFD());
                bVar.b.prepareAsync();
                fileInputStream.close();
            } catch (IOException | IllegalArgumentException | IllegalStateException e4) {
                e = e4;
                fileInputStream2 = fileInputStream;
                bVar.f = false;
                bVar.e.a(new AIError((int) AIError.ERR_TTS_MEDIAPLAYER, AIError.ERR_DESCRIPTION_TTS_MEDIAPLAYER), "");
                e.printStackTrace();
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }
}
