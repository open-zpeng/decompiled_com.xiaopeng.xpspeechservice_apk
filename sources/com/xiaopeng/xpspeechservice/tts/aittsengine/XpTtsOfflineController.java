package com.xiaopeng.xpspeechservice.tts.aittsengine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaopeng.xpspeechservice.tts.EventType;
import com.xiaopeng.xpspeechservice.tts.ITtsEngineCallback;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
/* loaded from: classes.dex */
public class XpTtsOfflineController implements ITtsEngine {
    private static final int MSG_TIMEOUT = 101;
    private static final String TAG = "XpTtsOfflineController";
    private final ITtsEngineCallback mCallback;
    private final LocalTTSEngine mEngine;
    private EventHandler mEventHandler;
    private TtsState mTtsState;
    private Bundle mPendingItem = null;
    private String mUid = "unset";
    private final TtsState mUnInitState = new UnInitState();
    private final TtsState mIdleState = new IdleState();
    private final TtsState mPendingStartState = new PendingStartState();
    private final TtsState mSynthState = new SynthState();
    private final TtsState mSynthStopState = new SynthStopState();
    private final TtsState mSynthStartInterruptState = new SynthStartInterruptState();
    private final TtsState mInterruptWaitState = new InterruptWaitState();

    public XpTtsOfflineController(ITtsEngineCallback cb, Handler handler) {
        LogUtils.v(TAG, "construct");
        this.mCallback = cb;
        this.mTtsState = this.mUnInitState;
        this.mEventHandler = new EventHandler(handler.getLooper());
        this.mEngine = new LocalTTSEngine(new MyEngineCallback());
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void init() {
        this.mEngine.init();
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void speak(final Bundle params) {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpTtsOfflineController.TAG, "speak at %s", XpTtsOfflineController.this.mTtsState.getClass().getSimpleName());
                XpTtsOfflineController.this.mTtsState.speak(params);
            }
        });
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void stop() {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.2
            @Override // java.lang.Runnable
            public void run() {
                if (XpTtsOfflineController.this.mPendingItem != null) {
                    XpTtsOfflineController.this.mPendingItem = null;
                    LogUtils.i(XpTtsOfflineController.TAG, "stop: remove pending item");
                    return;
                }
                LogUtils.i(XpTtsOfflineController.TAG, "stop at %s", XpTtsOfflineController.this.mTtsState.getClass().getSimpleName());
                XpTtsOfflineController.this.mTtsState.stop();
            }
        });
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void reset() {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.3
            @Override // java.lang.Runnable
            public void run() {
                if (XpTtsOfflineController.this.mTtsState != XpTtsOfflineController.this.mIdleState && XpTtsOfflineController.this.mTtsState != XpTtsOfflineController.this.mUnInitState) {
                    LogUtils.i(XpTtsOfflineController.TAG, "reset");
                    XpTtsOfflineController.this.mEngine.reset();
                    XpTtsOfflineController xpTtsOfflineController = XpTtsOfflineController.this;
                    xpTtsOfflineController.setTtsState(xpTtsOfflineController.mUnInitState);
                    XpTtsOfflineController.this.mUid = "unset";
                    return;
                }
                LogUtils.i(XpTtsOfflineController.TAG, "reset: already at %s", XpTtsOfflineController.this.mTtsState.getClass().getSimpleName());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTtsState(TtsState state) {
        Bundle bundle;
        LogUtils.i(TAG, "tts state change %s to %s", this.mTtsState.getClass().getSimpleName(), state.getClass().getSimpleName());
        this.mTtsState = state;
        TtsState ttsState = this.mTtsState;
        if (ttsState == this.mIdleState && (bundle = this.mPendingItem) != null) {
            ttsState.speak(bundle);
            this.mPendingItem = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTtsEvent(final EventType event, final Object arg) {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpTtsOfflineController.TAG, "onTtsEvent %s at %s", event.name(), XpTtsOfflineController.this.mTtsState.getClass().getSimpleName());
                if (XpTtsOfflineController.this.mUid.equals((String) arg)) {
                    XpTtsOfflineController.this.mTtsState.onEvent(event, arg);
                } else {
                    LogUtils.w(XpTtsOfflineController.TAG, "uid not match, ignore");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class TtsState {
        public TtsState() {
        }

        public void speak(Bundle params) {
            LogUtils.w(XpTtsOfflineController.TAG, "Not handled synth speak at " + getClass().getSimpleName());
            XpTtsOfflineController.this.mPendingItem = params;
        }

        public void stop() {
            LogUtils.w(XpTtsOfflineController.TAG, "Not handled synth stop at " + getClass().getSimpleName());
        }

        public void onEvent(EventType event, Object arg) {
            LogUtils.w(XpTtsOfflineController.TAG, "Not handled synth event %s at %s", event.name(), getClass().getSimpleName());
        }
    }

    /* loaded from: classes.dex */
    private class UnInitState extends TtsState {
        private UnInitState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.TtsState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_ENGINE_INITED) {
                XpTtsOfflineController xpTtsOfflineController = XpTtsOfflineController.this;
                xpTtsOfflineController.setTtsState(xpTtsOfflineController.mIdleState);
            }
        }
    }

    /* loaded from: classes.dex */
    private class IdleState extends TtsState {
        private IdleState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.TtsState
        public void speak(Bundle params) {
            String txt = params.getString("txt");
            LogUtils.i(XpTtsOfflineController.TAG, "onSynthesizeText %s length %d", txt, Integer.valueOf(txt.length()));
            XpTtsOfflineController.this.mUid = params.getString("uid");
            XpTtsOfflineController.this.mEngine.speak(params);
            XpTtsOfflineController xpTtsOfflineController = XpTtsOfflineController.this;
            xpTtsOfflineController.setTtsState(xpTtsOfflineController.mPendingStartState);
        }
    }

    /* loaded from: classes.dex */
    private class PendingStartState extends TtsState {
        private PendingStartState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.TtsState
        public void stop() {
            XpTtsOfflineController.this.mEngine.stop();
            XpTtsOfflineController xpTtsOfflineController = XpTtsOfflineController.this;
            xpTtsOfflineController.setTtsState(xpTtsOfflineController.mSynthStartInterruptState);
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.TtsState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_SYNTH_BEGIN) {
                XpTtsOfflineController.this.mCallback.onEvent(EventType.EVENT_SYNTH_BEGIN);
                XpTtsOfflineController xpTtsOfflineController = XpTtsOfflineController.this;
                xpTtsOfflineController.setTtsState(xpTtsOfflineController.mSynthState);
            } else if (event == EventType.EVENT_SYNTH_ERROR) {
                XpTtsOfflineController.this.mCallback.onEvent(EventType.EVENT_SYNTH_ERROR);
                XpTtsOfflineController xpTtsOfflineController2 = XpTtsOfflineController.this;
                xpTtsOfflineController2.setTtsState(xpTtsOfflineController2.mIdleState);
            } else if (event == EventType.EVENT_SYNTH_END) {
                XpTtsOfflineController.this.mCallback.onEvent(EventType.EVENT_SYNTH_ERROR);
                XpTtsOfflineController xpTtsOfflineController3 = XpTtsOfflineController.this;
                xpTtsOfflineController3.setTtsState(xpTtsOfflineController3.mIdleState);
            } else {
                super.onEvent(event, arg);
            }
        }
    }

    /* loaded from: classes.dex */
    private class SynthState extends TtsState {
        private SynthState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.TtsState
        public void stop() {
            XpTtsOfflineController.this.mEngine.stop();
            XpTtsOfflineController xpTtsOfflineController = XpTtsOfflineController.this;
            xpTtsOfflineController.setTtsState(xpTtsOfflineController.mSynthStopState);
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.TtsState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_SYNTH_BEGIN) {
                XpTtsOfflineController.this.mCallback.onEvent(EventType.EVENT_SYNTH_BEGIN);
            } else if (event == EventType.EVENT_SYNTH_ERROR) {
                XpTtsOfflineController.this.mCallback.onEvent(EventType.EVENT_SYNTH_ERROR);
                XpTtsOfflineController xpTtsOfflineController = XpTtsOfflineController.this;
                xpTtsOfflineController.setTtsState(xpTtsOfflineController.mIdleState);
            } else if (event == EventType.EVENT_SYNTH_END) {
                XpTtsOfflineController.this.mCallback.onEvent(EventType.EVENT_SYNTH_END);
                XpTtsOfflineController xpTtsOfflineController2 = XpTtsOfflineController.this;
                xpTtsOfflineController2.setTtsState(xpTtsOfflineController2.mIdleState);
            } else {
                super.onEvent(event, arg);
            }
        }
    }

    /* loaded from: classes.dex */
    private class SynthStopState extends TtsState {
        private SynthStopState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.TtsState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_SYNTH_ERROR || event == EventType.EVENT_SYNTH_END) {
                XpTtsOfflineController xpTtsOfflineController = XpTtsOfflineController.this;
                xpTtsOfflineController.setTtsState(xpTtsOfflineController.mIdleState);
                return;
            }
            super.onEvent(event, arg);
        }
    }

    /* loaded from: classes.dex */
    private class SynthStartInterruptState extends TtsState {
        private SynthStartInterruptState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.TtsState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_SYNTH_ERROR || event == EventType.EVENT_SYNTH_END) {
                XpTtsOfflineController.this.mEventHandler.sendEmptyMessageDelayed(XpTtsOfflineController.MSG_TIMEOUT, 100L);
                XpTtsOfflineController xpTtsOfflineController = XpTtsOfflineController.this;
                xpTtsOfflineController.setTtsState(xpTtsOfflineController.mInterruptWaitState);
                return;
            }
            super.onEvent(event, arg);
        }
    }

    /* loaded from: classes.dex */
    private class InterruptWaitState extends TtsState {
        private InterruptWaitState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOfflineController.TtsState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_SYNTH_INTERRUPT_TIMEOUT) {
                XpTtsOfflineController xpTtsOfflineController = XpTtsOfflineController.this;
                xpTtsOfflineController.setTtsState(xpTtsOfflineController.mIdleState);
                return;
            }
            super.onEvent(event, arg);
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
            XpTtsOfflineController.this.onTtsEvent(event, obj);
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
            if (msg.what == XpTtsOfflineController.MSG_TIMEOUT) {
                LogUtils.w(XpTtsOfflineController.TAG, "synth interrupt timeout");
                XpTtsOfflineController.this.onTtsEvent(EventType.EVENT_SYNTH_INTERRUPT_TIMEOUT, XpTtsOfflineController.this.mUid);
            }
        }
    }
}
