package com.xiaopeng.xpspeechservice.tts.aittsengine;

import android.os.Bundle;
import android.os.Handler;
import com.xiaopeng.xpspeechservice.tts.EventType;
import com.xiaopeng.xpspeechservice.tts.ITtsEngineCallback;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
/* loaded from: classes.dex */
public class XpTtsOnlineController implements ITtsEngine {
    private static final String TAG = "XpTtsOnlineController";
    private final ITtsEngineCallback mCallback;
    private final CloudTTSEngine mEngine;
    private Handler mEventHandler;
    private TtsState mTtsState;
    private Bundle mPendingItem = null;
    private String mUid = "unset";
    private final TtsState mEngineUnInitState = new EngineUnInitState();
    private final TtsState mIdleState = new IdleState();
    private final TtsState mSynthState = new SynthState();
    private final TtsState mSynthStopState = new SynthStopState();

    public XpTtsOnlineController(ITtsEngineCallback cb, Handler handler) {
        LogUtils.v(TAG, "construct");
        this.mCallback = cb;
        this.mTtsState = this.mEngineUnInitState;
        this.mEventHandler = new Handler(handler.getLooper());
        this.mEngine = new CloudTTSEngine(new MyEngineCallback());
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void init() {
        this.mEngine.init();
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void speak(final Bundle params) {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOnlineController.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpTtsOnlineController.TAG, "speak at %s", XpTtsOnlineController.this.mTtsState.getClass().getSimpleName());
                XpTtsOnlineController.this.mTtsState.speak(params);
            }
        });
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void stop() {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOnlineController.2
            @Override // java.lang.Runnable
            public void run() {
                if (XpTtsOnlineController.this.mPendingItem != null) {
                    XpTtsOnlineController.this.mPendingItem = null;
                    LogUtils.i(XpTtsOnlineController.TAG, "stop: remove pending item");
                    return;
                }
                LogUtils.i(XpTtsOnlineController.TAG, "stop at %s", XpTtsOnlineController.this.mTtsState.getClass().getSimpleName());
                XpTtsOnlineController.this.mTtsState.stop();
            }
        });
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void reset() {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOnlineController.3
            @Override // java.lang.Runnable
            public void run() {
                if (XpTtsOnlineController.this.mTtsState != XpTtsOnlineController.this.mIdleState && XpTtsOnlineController.this.mTtsState != XpTtsOnlineController.this.mEngineUnInitState) {
                    LogUtils.i(XpTtsOnlineController.TAG, "reset");
                    XpTtsOnlineController.this.mEngine.reset();
                    XpTtsOnlineController xpTtsOnlineController = XpTtsOnlineController.this;
                    xpTtsOnlineController.setTtsState(xpTtsOnlineController.mEngineUnInitState);
                    XpTtsOnlineController.this.mUid = "unset";
                    return;
                }
                LogUtils.i(XpTtsOnlineController.TAG, "reset: already at %s", XpTtsOnlineController.this.mTtsState.getClass().getSimpleName());
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
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOnlineController.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpTtsOnlineController.TAG, "onTtsEvent %s at %s", event.name(), XpTtsOnlineController.this.mTtsState.getClass().getSimpleName());
                if (XpTtsOnlineController.this.mUid.equals((String) arg)) {
                    XpTtsOnlineController.this.mTtsState.onEvent(event, arg);
                } else {
                    LogUtils.w(XpTtsOnlineController.TAG, "uid not match, ignore");
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
            LogUtils.w(XpTtsOnlineController.TAG, "Not handled synth speak at " + getClass().getSimpleName());
            XpTtsOnlineController.this.mPendingItem = params;
        }

        public void stop() {
            LogUtils.w(XpTtsOnlineController.TAG, "Not handled synth stop at " + getClass().getSimpleName());
        }

        public void onEvent(EventType event, Object arg) {
            LogUtils.w(XpTtsOnlineController.TAG, "Not handled synth event %s at %s", event.name(), getClass().getSimpleName());
        }
    }

    /* loaded from: classes.dex */
    private class EngineUnInitState extends TtsState {
        private EngineUnInitState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOnlineController.TtsState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_ENGINE_INITED) {
                XpTtsOnlineController xpTtsOnlineController = XpTtsOnlineController.this;
                xpTtsOnlineController.setTtsState(xpTtsOnlineController.mIdleState);
            }
        }
    }

    /* loaded from: classes.dex */
    private class IdleState extends TtsState {
        private IdleState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOnlineController.TtsState
        public void speak(Bundle params) {
            String txt = params.getString("txt");
            LogUtils.i(XpTtsOnlineController.TAG, "onSynthesizeText %s length %d", txt, Integer.valueOf(txt.length()));
            XpTtsOnlineController.this.mUid = params.getString("uid");
            XpTtsOnlineController.this.mEngine.speak(params);
            XpTtsOnlineController xpTtsOnlineController = XpTtsOnlineController.this;
            xpTtsOnlineController.setTtsState(xpTtsOnlineController.mSynthState);
        }
    }

    /* loaded from: classes.dex */
    private class SynthState extends TtsState {
        private SynthState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOnlineController.TtsState
        public void stop() {
            XpTtsOnlineController.this.mEngine.stop();
            XpTtsOnlineController xpTtsOnlineController = XpTtsOnlineController.this;
            xpTtsOnlineController.setTtsState(xpTtsOnlineController.mSynthStopState);
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOnlineController.TtsState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_SYNTH_BEGIN) {
                XpTtsOnlineController.this.mCallback.onEvent(EventType.EVENT_SYNTH_BEGIN);
            } else if (event == EventType.EVENT_SYNTH_ERROR) {
                XpTtsOnlineController.this.mCallback.onEvent(EventType.EVENT_SYNTH_ERROR);
                XpTtsOnlineController xpTtsOnlineController = XpTtsOnlineController.this;
                xpTtsOnlineController.setTtsState(xpTtsOnlineController.mIdleState);
            } else if (event == EventType.EVENT_SYNTH_END) {
                XpTtsOnlineController.this.mCallback.onEvent(EventType.EVENT_SYNTH_END);
                XpTtsOnlineController xpTtsOnlineController2 = XpTtsOnlineController.this;
                xpTtsOnlineController2.setTtsState(xpTtsOnlineController2.mIdleState);
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

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsOnlineController.TtsState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_SYNTH_ERROR || event == EventType.EVENT_SYNTH_END) {
                XpTtsOnlineController xpTtsOnlineController = XpTtsOnlineController.this;
                xpTtsOnlineController.setTtsState(xpTtsOnlineController.mIdleState);
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
            XpTtsOnlineController.this.onTtsEvent(event, obj);
        }
    }
}
