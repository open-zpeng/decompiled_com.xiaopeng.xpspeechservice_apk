package com.xiaopeng.xpspeechservice.tts.aittsengine;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.xiaopeng.xpspeechservice.base.AISpeech;
import com.xiaopeng.xpspeechservice.base.IInitListener;
import com.xiaopeng.xpspeechservice.tts.EventType;
import com.xiaopeng.xpspeechservice.tts.IEngine;
import com.xiaopeng.xpspeechservice.tts.IEngineCallback;
import com.xiaopeng.xpspeechservice.tts.ITtsEngineCallback;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
/* loaded from: classes.dex */
public class XpAiTtsEngine implements IEngine {
    private static final int CHANNEL_COUNT = 1;
    private static final int FORMAT = 2;
    private static final int INTERVAL_PER_WORD = 600;
    private static final int INTERVAL_STATIC = 500;
    private static final int MSG_TIMEOUT = 101;
    private static final int SAMPLE_RATE = 16000;
    private static final String TAG = "XpAiTtsEngine";
    private IEngineCallback mCallback;
    private int mDefaultMode;
    private ITtsEngine mEngine;
    private EventHandler mEventHandler;
    private HandlerThread mEventThread;
    private final TtsState mIdleState;
    private ITtsEngine mOfflineEngine;
    private PendingItem mPendingItem;
    private IInitListener mSDKInitListener;
    private final TtsState mSDKUnInitedState;
    private final TtsState mSynthState;
    private int mTtsMode;
    private TtsState mTtsState;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PendingItem {
        public IEngineCallback callback;
        public Bundle params;

        public PendingItem(Bundle bundle, IEngineCallback cb) {
            this.params = bundle;
            this.callback = cb;
        }
    }

    /* loaded from: classes.dex */
    private static class SingleHolder {
        private static final XpAiTtsEngine INSTANCE = new XpAiTtsEngine();

        private SingleHolder() {
        }
    }

    public static XpAiTtsEngine getInstance() {
        return SingleHolder.INSTANCE;
    }

    private XpAiTtsEngine() {
        this.mDefaultMode = 1;
        this.mTtsMode = this.mDefaultMode;
        this.mPendingItem = null;
        this.mSDKInitListener = new IInitListener() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpAiTtsEngine.1
            @Override // com.xiaopeng.xpspeechservice.base.IInitListener
            public void onInit() {
                XpAiTtsEngine.this.onTtsEvent(EventType.EVENT_SDK_INITED);
            }
        };
        this.mSDKUnInitedState = new SDKUnInitedState();
        this.mIdleState = new IdleState();
        this.mSynthState = new SynthState();
        LogUtils.i(TAG, "XpAiTtsEngine v20220606");
        this.mTtsState = this.mSDKUnInitedState;
        this.mEventThread = new HandlerThread("TtsEventThread");
        this.mEventThread.start();
        this.mEventHandler = new EventHandler(this.mEventThread.getLooper());
        MyEngineCallback engineCallback = new MyEngineCallback();
        this.mOfflineEngine = new XpTtsOfflineController(engineCallback, this.mEventHandler);
        AISpeech.getInstance().init(this.mSDKInitListener);
    }

    @Override // com.xiaopeng.xpspeechservice.tts.IEngine
    public int speak(final Bundle params, final IEngineCallback cb) {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpAiTtsEngine.2
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpAiTtsEngine.TAG, "speak at %s", XpAiTtsEngine.this.mTtsState.getClass().getSimpleName());
                XpAiTtsEngine.this.mTtsState.speak(params, cb);
            }
        });
        return 0;
    }

    @Override // com.xiaopeng.xpspeechservice.tts.IEngine
    public void stop() {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpAiTtsEngine.3
            @Override // java.lang.Runnable
            public void run() {
                if (XpAiTtsEngine.this.mPendingItem != null) {
                    XpAiTtsEngine.this.mPendingItem = null;
                    LogUtils.i(XpAiTtsEngine.TAG, "stop: remove pending item");
                    return;
                }
                LogUtils.i(XpAiTtsEngine.TAG, "stop at %s", XpAiTtsEngine.this.mTtsState.getClass().getSimpleName());
                XpAiTtsEngine.this.mTtsState.stop();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTtsEvent(EventType event) {
        onTtsEvent(event, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTtsEvent(final EventType event, final Object arg) {
        this.mEventHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpAiTtsEngine.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.i(XpAiTtsEngine.TAG, "onTtsEvent %s at %s", event.name(), XpAiTtsEngine.this.mTtsState.getClass().getSimpleName());
                XpAiTtsEngine.this.mTtsState.onEvent(event, arg);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTtsState(TtsState state) {
        LogUtils.i(TAG, "tts state change %s to %s", this.mTtsState.getClass().getSimpleName(), state.getClass().getSimpleName());
        this.mTtsState = state;
        if (this.mTtsState == this.mIdleState) {
            this.mEventHandler.removeMessages(MSG_TIMEOUT);
            PendingItem pendingItem = this.mPendingItem;
            if (pendingItem != null) {
                this.mTtsState.speak(pendingItem.params, this.mPendingItem.callback);
                this.mPendingItem = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class TtsState {
        public TtsState() {
        }

        public void speak(Bundle params, IEngineCallback cb) {
            LogUtils.w(XpAiTtsEngine.TAG, "Not handled synth speak at " + getClass().getSimpleName());
            XpAiTtsEngine xpAiTtsEngine = XpAiTtsEngine.this;
            xpAiTtsEngine.mPendingItem = new PendingItem(params, cb);
        }

        public void stop() {
            LogUtils.w(XpAiTtsEngine.TAG, "Not handled synth stop at " + getClass().getSimpleName());
        }

        public void onEvent(EventType event, Object arg) {
            LogUtils.w(XpAiTtsEngine.TAG, "Not handled synth event %s at %s", event.name(), getClass().getSimpleName());
        }
    }

    /* loaded from: classes.dex */
    private class SDKUnInitedState extends TtsState {
        private SDKUnInitedState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpAiTtsEngine.TtsState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_SDK_INITED) {
                XpAiTtsEngine.this.mOfflineEngine.init();
                XpAiTtsEngine xpAiTtsEngine = XpAiTtsEngine.this;
                xpAiTtsEngine.setTtsState(xpAiTtsEngine.mIdleState);
            }
        }
    }

    /* loaded from: classes.dex */
    private class IdleState extends TtsState {
        private IdleState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpAiTtsEngine.TtsState
        public void speak(Bundle params, IEngineCallback cb) {
            XpAiTtsEngine.this.mCallback = cb;
            XpAiTtsEngine xpAiTtsEngine = XpAiTtsEngine.this;
            xpAiTtsEngine.mEngine = xpAiTtsEngine.mOfflineEngine;
            String txt = params.getString("txt");
            int rate = params.getInt("rate", 100);
            int timeOut = (((txt.length() * XpAiTtsEngine.INTERVAL_PER_WORD) * 100) / rate) + XpAiTtsEngine.INTERVAL_STATIC;
            XpAiTtsEngine.this.mEventHandler.sendEmptyMessageDelayed(XpAiTtsEngine.MSG_TIMEOUT, timeOut);
            XpAiTtsEngine.this.mEngine.speak(params);
            XpAiTtsEngine xpAiTtsEngine2 = XpAiTtsEngine.this;
            xpAiTtsEngine2.setTtsState(xpAiTtsEngine2.mSynthState);
        }
    }

    /* loaded from: classes.dex */
    private class SynthState extends TtsState {
        private SynthState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpAiTtsEngine.TtsState
        public void stop() {
            XpAiTtsEngine.this.mEngine.stop();
            XpAiTtsEngine xpAiTtsEngine = XpAiTtsEngine.this;
            xpAiTtsEngine.setTtsState(xpAiTtsEngine.mIdleState);
        }

        @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.XpAiTtsEngine.TtsState
        public void onEvent(EventType event, Object arg) {
            if (event == EventType.EVENT_SYNTH_BEGIN) {
                XpAiTtsEngine.this.mCallback.begin(XpAiTtsEngine.SAMPLE_RATE, 2, 1);
            } else if (event == EventType.EVENT_SYNTH_END) {
                XpAiTtsEngine.this.mCallback.end();
                XpAiTtsEngine xpAiTtsEngine = XpAiTtsEngine.this;
                xpAiTtsEngine.setTtsState(xpAiTtsEngine.mIdleState);
            } else if (event == EventType.EVENT_SYNTH_ERROR) {
                XpAiTtsEngine.this.mCallback.error();
                XpAiTtsEngine xpAiTtsEngine2 = XpAiTtsEngine.this;
                xpAiTtsEngine2.setTtsState(xpAiTtsEngine2.mIdleState);
            } else if (event == EventType.EVENT_SYNTH_TIMEOUT) {
                XpAiTtsEngine.this.mCallback.error();
                XpAiTtsEngine.this.mEngine.reset();
                XpAiTtsEngine xpAiTtsEngine3 = XpAiTtsEngine.this;
                xpAiTtsEngine3.setTtsState(xpAiTtsEngine3.mIdleState);
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
            if (msg.what == XpAiTtsEngine.MSG_TIMEOUT) {
                LogUtils.w(XpAiTtsEngine.TAG, "tts timeout");
                XpAiTtsEngine.this.onTtsEvent(EventType.EVENT_SYNTH_TIMEOUT);
            }
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
            XpAiTtsEngine.this.onTtsEvent(event, obj);
        }
    }
}
