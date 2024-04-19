package com.xiaopeng.xpspeechservice.base;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import com.aispeech.DUILiteConfig;
import com.aispeech.DUILiteSDK;
import com.aispeech.export.config.AuthConfig;
import com.aispeech.export.config.UploadConfig;
import com.aispeech.lite.AuthType;
import com.xiaopeng.xpspeechservice.SpeechApp;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
import com.xiaopeng.xpspeechservice.utils.XpSysUtils;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class AISpeech {
    private static final int INIT_RETRY_INTERVAL = 10000;
    private static final int MSG_INIT_RETRY = 101;
    private static final String TAG = "AiSpeech";
    private static final String apiKeyDev = "308961e43844308961e438445d833e93";
    private static final String apiKeyRel = "c69e2c384ebbc69e2c384ebb5dca5b8c";
    private static final String productId = "278586031";
    private static final String productKey = "381172ce984c4df751bf2500d3a9da7e";
    private static final String productSecret = "c3b213f2020755ac39fe2843ede9da29";
    private Handler mInitHandler;
    private final State mInitedState;
    private final State mInitingState;
    private ArrayList<IInitListener> mListeners;
    private State mState;
    private final State mUnInitState;

    private AISpeech() {
        this.mListeners = new ArrayList<>();
        this.mUnInitState = new UnInitState();
        this.mInitingState = new InitingState();
        this.mInitedState = new InitedState();
        HandlerThread initThread = new HandlerThread("DUILiteInit");
        initThread.start();
        this.mInitHandler = new InitHandler(initThread.getLooper());
        this.mState = this.mUnInitState;
    }

    /* loaded from: classes.dex */
    private static class SingleHolder {
        private static final AISpeech INSTANCE = new AISpeech();

        private SingleHolder() {
        }
    }

    public static AISpeech getInstance() {
        return SingleHolder.INSTANCE;
    }

    /* loaded from: classes.dex */
    private class State {
        private State() {
        }

        public void init(IInitListener listener) {
        }

        public void onInit() {
        }
    }

    /* loaded from: classes.dex */
    private class UnInitState extends State {
        private UnInitState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.base.AISpeech.State
        public void init(IInitListener listener) {
            AISpeech.this.initDuiLite();
            if (listener != null) {
                AISpeech.this.mListeners.add(listener);
            }
            AISpeech aISpeech = AISpeech.this;
            aISpeech.mState = aISpeech.mInitingState;
        }
    }

    /* loaded from: classes.dex */
    private class InitingState extends State {
        private InitingState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.base.AISpeech.State
        public void init(IInitListener listener) {
            if (listener != null) {
                AISpeech.this.mListeners.add(listener);
            }
        }

        @Override // com.xiaopeng.xpspeechservice.base.AISpeech.State
        public void onInit() {
            Iterator it = AISpeech.this.mListeners.iterator();
            while (it.hasNext()) {
                IInitListener listener = (IInitListener) it.next();
                listener.onInit();
            }
            AISpeech.this.mListeners.clear();
            AISpeech aISpeech = AISpeech.this;
            aISpeech.mState = aISpeech.mInitedState;
        }
    }

    /* loaded from: classes.dex */
    private class InitedState extends State {
        private InitedState() {
            super();
        }

        @Override // com.xiaopeng.xpspeechservice.base.AISpeech.State
        public void init(IInitListener listener) {
            if (listener != null) {
                listener.onInit();
            }
        }
    }

    public void init(final IInitListener listener) {
        this.mInitHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.base.AISpeech.1
            @Override // java.lang.Runnable
            public void run() {
                AISpeech.this.mState.init(listener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onInit() {
        this.mInitHandler.post(new Runnable() { // from class: com.xiaopeng.xpspeechservice.base.AISpeech.2
            @Override // java.lang.Runnable
            public void run() {
                AISpeech.this.mState.onInit();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDuiLite() {
        String apiKey;
        DUILiteSDK.openLog();
        DUILiteSDK.InitListener initListener = new DUILiteSDK.InitListener() { // from class: com.xiaopeng.xpspeechservice.base.AISpeech.3
            @Override // com.aispeech.DUILiteSDK.InitListener
            public void success() {
                LogUtils.i(AISpeech.TAG, "auth succeed");
                AISpeech.this.onInit();
            }

            @Override // com.aispeech.DUILiteSDK.InitListener
            public void error(String errorCode, String errorInfo) {
                LogUtils.e(AISpeech.TAG, "auth failed, code: " + errorCode + " info: " + errorInfo);
                AISpeech.this.mInitHandler.sendEmptyMessageDelayed(AISpeech.MSG_INIT_RETRY, 10000L);
            }
        };
        String hardwareId = SystemProperties.get("persist.sys.mcu.hardwareId", "xpeng");
        LogUtils.d(TAG, "got hardwareId " + hardwareId);
        AuthConfig authConfig = new AuthConfig.Builder().setType(AuthType.ONLINE).setCustomDeviceName(hardwareId).create();
        UploadConfig uploadConfig = new UploadConfig.Builder().setUploadEnable(false).create();
        if (XpSysUtils.isDevBuild()) {
            LogUtils.v(TAG, "use dev api key");
            apiKey = apiKeyDev;
        } else {
            LogUtils.v(TAG, "use rel api key");
            apiKey = apiKeyRel;
        }
        DUILiteSDK.init(SpeechApp.getContext(), new DUILiteConfig.Builder().setApiKey(apiKey).setProductId(productId).setProductKey(productKey).setProductSecret(productSecret).setAuthConfig(authConfig).setUploadConfig(uploadConfig).create(), initListener);
    }

    /* loaded from: classes.dex */
    private class InitHandler extends Handler {
        public InitHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == AISpeech.MSG_INIT_RETRY) {
                AISpeech.this.initDuiLite();
            }
        }
    }
}
