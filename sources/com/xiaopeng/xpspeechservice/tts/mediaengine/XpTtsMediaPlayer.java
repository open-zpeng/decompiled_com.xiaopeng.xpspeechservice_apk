package com.xiaopeng.xpspeechservice.tts.mediaengine;

import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import com.xiaopeng.xpspeechservice.SpeechApp;
import com.xiaopeng.xpspeechservice.tts.EventType;
import com.xiaopeng.xpspeechservice.tts.ITtsEngineCallback;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
import java.io.IOException;
/* loaded from: classes.dex */
public class XpTtsMediaPlayer implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private static final String TAG = "XpTtsMediaPlayer";
    private final ITtsEngineCallback mCallback;
    private final Handler mDecodeHandler;
    private final HandlerThread mDecodeThread;
    private final MediaPlayer mMediaPlayer;

    public XpTtsMediaPlayer(ITtsEngineCallback cb) {
        LogUtils.i(TAG, "construct");
        this.mCallback = cb;
        this.mDecodeThread = new HandlerThread("ttsPlayer");
        this.mDecodeThread.start();
        this.mDecodeHandler = new Handler(this.mDecodeThread.getLooper());
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setOnPreparedListener(this);
        this.mMediaPlayer.setOnCompletionListener(this);
        this.mMediaPlayer.setOnErrorListener(this);
    }

    public void prepare(final SourceType type, final Bundle params, final Object obj) {
        this.mDecodeHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.mediaengine.XpTtsMediaPlayer.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpTtsMediaPlayer.TAG, "prepare " + type.name());
                int streamType = params.getInt("streamType", 10);
                XpTtsMediaPlayer.this.mMediaPlayer.setAudioStreamType(streamType);
                float volume = params.getFloat("volume", 1.0f);
                XpTtsMediaPlayer.this.mMediaPlayer.setVolume(volume);
                try {
                    if (type == SourceType.SOURCE_URI) {
                        Uri uri = (Uri) obj;
                        LogUtils.v(XpTtsMediaPlayer.TAG, "prepare uri " + uri);
                        XpTtsMediaPlayer.this.mMediaPlayer.setDataSource(SpeechApp.getContext(), uri);
                    } else if (type == SourceType.SOURCE_BUFFER) {
                        LogUtils.v(XpTtsMediaPlayer.TAG, "prepare buffer");
                        MyMediaDataSource dataSource = new MyMediaDataSource((byte[]) obj);
                        XpTtsMediaPlayer.this.mMediaPlayer.setDataSource(dataSource);
                    }
                    XpTtsMediaPlayer.this.mMediaPlayer.prepareAsync();
                } catch (Exception e) {
                    LogUtils.e(XpTtsMediaPlayer.TAG, "prepare error", e);
                    XpTtsMediaPlayer.this.mCallback.onEvent(EventType.EVENT_PLAY_ERROR);
                    XpTtsMediaPlayer.this.mMediaPlayer.reset();
                }
            }
        });
    }

    public void start() {
        this.mDecodeHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.mediaengine.XpTtsMediaPlayer.2
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpTtsMediaPlayer.TAG, "start");
                XpTtsMediaPlayer.this.mMediaPlayer.start();
            }
        });
    }

    public void stop() {
        this.mDecodeHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.mediaengine.XpTtsMediaPlayer.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpTtsMediaPlayer.TAG, "stop");
                XpTtsMediaPlayer.this.mMediaPlayer.stop();
                XpTtsMediaPlayer.this.mMediaPlayer.reset();
            }
        });
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mp) {
        LogUtils.i(TAG, "onPrepared");
        this.mCallback.onEvent(EventType.EVENT_PLAY_PREPARED);
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mp) {
        LogUtils.i(TAG, "onCompletion");
        this.mCallback.onEvent(EventType.EVENT_PLAY_END);
        mp.reset();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mp, int what, int extra) {
        LogUtils.e(TAG, "onError %d %d", Integer.valueOf(what), Integer.valueOf(extra));
        this.mCallback.onEvent(EventType.EVENT_PLAY_ERROR);
        mp.reset();
        return true;
    }

    public void destroy() {
        this.mDecodeHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.mediaengine.XpTtsMediaPlayer.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.v(XpTtsMediaPlayer.TAG, "destroy");
                XpTtsMediaPlayer.this.mMediaPlayer.release();
                XpTtsMediaPlayer.this.mDecodeHandler.removeCallbacksAndMessages(null);
                XpTtsMediaPlayer.this.mDecodeThread.quitSafely();
                LogUtils.v(XpTtsMediaPlayer.TAG, "destroy ---");
            }
        });
    }

    /* loaded from: classes.dex */
    private class MyMediaDataSource extends MediaDataSource {
        private byte[] mBuffer;

        public MyMediaDataSource(byte[] buffer) {
            this.mBuffer = buffer;
        }

        @Override // android.media.MediaDataSource
        public int readAt(long position, byte[] buffer, int offset, int size) throws IOException {
            int len = this.mBuffer.length;
            if (len <= position) {
                return -1;
            }
            if (len - ((int) position) < size) {
                size = len - ((int) position);
            }
            System.arraycopy(this.mBuffer, (int) position, buffer, offset, size);
            return size;
        }

        @Override // android.media.MediaDataSource
        public long getSize() throws IOException {
            return this.mBuffer.length;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }
    }
}
