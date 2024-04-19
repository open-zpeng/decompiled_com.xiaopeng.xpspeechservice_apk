package com.xiaopeng.xpspeechservice.tts.mediaengine;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.xiaopeng.xpspeechservice.tts.EventType;
import com.xiaopeng.xpspeechservice.tts.IEngineCallback;
import com.xiaopeng.xpspeechservice.tts.ITtsEngineCallback;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
/* loaded from: classes.dex */
public class XpMediaController {
    private static final String TAG = "XpMediaController";
    private IEngineCallback mCallback;
    private final EventHandler mEventHandler;
    private MediaState mMediaState;
    private PendingItem mPendingItem;
    private XpTtsMediaPlayer mXpTtsMediaPlayer;
    private boolean mIsPendingInit = false;
    private boolean mIsPendingShutdown = false;
    private final MediaState mUnInitState = new UnInitState();
    private final MediaState mMediaIdleState = new MediaIdleState();
    private final MediaState mMediaPlayState = new MediaPlayState();
    private final HandlerThread mEventThread = new HandlerThread("MediaEventThread");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PendingItem {
        public IEngineCallback cb;
        public Object obj;
        public Bundle params;
        public SourceType type;

        public PendingItem(SourceType type, Bundle params, Object obj, IEngineCallback cb) {
            this.type = type;
            this.params = params;
            this.obj = obj;
            this.cb = cb;
        }
    }

    public XpMediaController() {
        this.mEventThread.start();
        this.mEventHandler = new EventHandler(this.mEventThread.getLooper());
        this.mMediaState = this.mUnInitState;
    }

    public void initEngine() {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.mediaengine.XpMediaController.1
            @Override // java.lang.Runnable
            public void run() {
                if (XpMediaController.this.mIsPendingShutdown) {
                    XpMediaController.this.mIsPendingShutdown = false;
                    LogUtils.i(XpMediaController.TAG, "init: remove pending shutdown");
                    return;
                }
                LogUtils.i(XpMediaController.TAG, "init at %s", XpMediaController.this.mMediaState.getClass().getSimpleName());
                XpMediaController.this.mMediaState.init();
            }
        });
    }

    public void shutdown() {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.mediaengine.XpMediaController.2
            @Override // java.lang.Runnable
            public void run() {
                if (XpMediaController.this.mIsPendingInit) {
                    XpMediaController.this.mIsPendingInit = false;
                    LogUtils.i(XpMediaController.TAG, "shutdown: remove pending init");
                    return;
                }
                LogUtils.i(XpMediaController.TAG, "shutdown at %s", XpMediaController.this.mMediaState.getClass().getSimpleName());
                XpMediaController.this.mMediaState.shutdown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutdownInternal() {
        this.mXpTtsMediaPlayer.destroy();
        this.mXpTtsMediaPlayer = null;
        this.mPendingItem = null;
        this.mCallback = null;
    }

    public void start(final SourceType type, final Bundle params, final Object obj, final IEngineCallback cb) {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.mediaengine.XpMediaController.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpMediaController.TAG, "speak");
                XpMediaController.this.mMediaState.start(type, params, obj, cb);
            }
        });
    }

    public void stop() {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.mediaengine.XpMediaController.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpMediaController.TAG, "stop");
                if (XpMediaController.this.mPendingItem != null) {
                    XpMediaController.this.mPendingItem = null;
                    LogUtils.v(XpMediaController.TAG, "remove pending item");
                    return;
                }
                XpMediaController.this.mMediaState.stop();
            }
        });
    }

    private void onMediaEvent(EventType event) {
        onMediaEvent(event, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMediaEvent(final EventType event, final Object arg) {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.mediaengine.XpMediaController.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpMediaController.TAG, "onMediaEvent %s at %s", event.name(), XpMediaController.this.mMediaState.getClass().getSimpleName());
                XpMediaController.this.mMediaState.onEvent(event, arg);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMediaState(MediaState state) {
        LogUtils.i(TAG, "synth state change %s to %s", this.mMediaState.getClass().getSimpleName(), state.getClass().getSimpleName());
        this.mMediaState = state;
        MediaState mediaState = this.mMediaState;
        MediaState mediaState2 = this.mMediaIdleState;
        if (mediaState == mediaState2) {
            if (this.mIsPendingShutdown) {
                this.mIsPendingShutdown = false;
                shutdownInternal();
                this.mMediaState = this.mUnInitState;
                return;
            }
            PendingItem pendingItem = this.mPendingItem;
            if (pendingItem != null) {
                mediaState2.start(pendingItem.type, this.mPendingItem.params, this.mPendingItem.obj, this.mPendingItem.cb);
                this.mPendingItem = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MediaState {
        public MediaState() {
        }

        public void init() {
            LogUtils.w(XpMediaController.TAG, "Not handled init at " + getClass().getSimpleName());
            XpMediaController.this.mIsPendingInit = true;
        }

        public void shutdown() {
            LogUtils.w(XpMediaController.TAG, "Not handled shutdown at " + getClass().getSimpleName());
            XpMediaController.this.mIsPendingShutdown = true;
        }

        public void start(SourceType type, Bundle params, Object obj, IEngineCallback cb) {
            LogUtils.w(XpMediaController.TAG, "Not handled synth speak at " + getClass().getSimpleName());
            XpMediaController xpMediaController = XpMediaController.this;
            xpMediaController.mPendingItem = new PendingItem(type, params, obj, cb);
        }

        public void stop() {
            LogUtils.w(XpMediaController.TAG, "Not handled synth stop at " + getClass().getSimpleName());
        }

        public void onEvent(EventType event, Object arg) {
            LogUtils.w(XpMediaController.TAG, "Not handled synth event %s at %s", event.name(), getClass().getSimpleName());
        }
    }

    /* loaded from: classes.dex */
    private class UnInitState extends MediaState {
        private UnInitState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.mediaengine.XpMediaController.MediaState
        public void init() {
            XpMediaController xpMediaController = XpMediaController.this;
            xpMediaController.mXpTtsMediaPlayer = new XpTtsMediaPlayer(new MyEngineCallback());
            XpMediaController xpMediaController2 = XpMediaController.this;
            xpMediaController2.setMediaState(xpMediaController2.mMediaIdleState);
        }
    }

    /* loaded from: classes.dex */
    private class MediaIdleState extends MediaState {
        private MediaIdleState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.mediaengine.XpMediaController.MediaState
        public void start(SourceType type, Bundle params, Object obj, IEngineCallback cb) {
            XpMediaController.this.mCallback = cb;
            XpMediaController.this.mXpTtsMediaPlayer.prepare(type, params, obj);
            XpMediaController xpMediaController = XpMediaController.this;
            xpMediaController.setMediaState(xpMediaController.mMediaPlayState);
        }

        @Override // com.xiaopeng.xpspeechservice.tts.mediaengine.XpMediaController.MediaState
        public void shutdown() {
            XpMediaController.this.shutdownInternal();
            XpMediaController xpMediaController = XpMediaController.this;
            xpMediaController.setMediaState(xpMediaController.mUnInitState);
        }
    }

    /* loaded from: classes.dex */
    private class MediaPlayState extends MediaState {
        private MediaPlayState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.mediaengine.XpMediaController.MediaState
        public void stop() {
            XpMediaController.this.mXpTtsMediaPlayer.stop();
            XpMediaController xpMediaController = XpMediaController.this;
            xpMediaController.setMediaState(xpMediaController.mMediaIdleState);
        }

        @Override // com.xiaopeng.xpspeechservice.tts.mediaengine.XpMediaController.MediaState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_PLAY_PREPARED) {
                XpMediaController.this.mXpTtsMediaPlayer.start();
                XpMediaController.this.mCallback.begin(16000, 2, 1);
            } else if (event == EventType.EVENT_PLAY_ERROR) {
                XpMediaController.this.mCallback.error();
                XpMediaController xpMediaController = XpMediaController.this;
                xpMediaController.setMediaState(xpMediaController.mMediaIdleState);
            } else if (event == EventType.EVENT_PLAY_END) {
                XpMediaController.this.mCallback.end();
                XpMediaController xpMediaController2 = XpMediaController.this;
                xpMediaController2.setMediaState(xpMediaController2.mMediaIdleState);
            } else {
                super.onEvent(event, arg);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class EventHandler extends Handler {
        public EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
        }
    }

    /* loaded from: classes.dex */
    private class MyEngineCallback implements ITtsEngineCallback {
        private MyEngineCallback() {
        }

        @Override // com.xiaopeng.xpspeechservice.tts.ITtsEngineCallback
        public void onEvent(EventType event) {
            onEvent(event, null);
        }

        @Override // com.xiaopeng.xpspeechservice.tts.ITtsEngineCallback
        public void onEvent(EventType event, Object obj) {
            XpMediaController.this.onMediaEvent(event, obj);
        }
    }
}
