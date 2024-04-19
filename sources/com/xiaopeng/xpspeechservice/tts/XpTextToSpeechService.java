package com.xiaopeng.xpspeechservice.tts;

import android.content.Intent;
import android.os.Bundle;
import com.aispeech.AIError;
import com.xiaopeng.speech.tts.XpSynthesisCallback;
import com.xiaopeng.speech.tts.XpSynthesisRequest;
import com.xiaopeng.speech.tts.XpTextToSpeechServiceBase;
import com.xiaopeng.xpspeechservice.tts.aittsengine.XpAiTtsEngine;
import com.xiaopeng.xpspeechservice.tts.mediaengine.XpMediaEngine;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
import java.util.Locale;
/* loaded from: classes.dex */
public class XpTextToSpeechService extends XpTextToSpeechServiceBase {
    private static final String TAG = "XpTextToSpeechService";
    private volatile XpSynthesisCallback mCallback;
    private volatile String[] mCurrentLanguage = null;
    private volatile IEngine mEngine;
    private XpAiTtsEngine mTtsEngine;
    private XpMediaEngine mXpMediaEngine;

    @Override // com.xiaopeng.speech.tts.XpTextToSpeechServiceBase, android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, "onCreate");
        this.mTtsEngine = XpAiTtsEngine.getInstance();
        this.mXpMediaEngine = XpMediaEngine.getInstance();
        this.mXpMediaEngine.initEngine();
        afterOnCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    @Override // com.xiaopeng.speech.tts.XpTextToSpeechServiceBase, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy+++");
        this.mTtsEngine = null;
        this.mXpMediaEngine.shutdown();
        this.mXpMediaEngine = null;
        LogUtils.i(TAG, "onDestroy---");
    }

    @Override // com.xiaopeng.speech.tts.XpTextToSpeechServiceBase
    protected int onIsLanguageAvailable(String lang, String country, String variant) {
        if (Locale.SIMPLIFIED_CHINESE.getISO3Language().equals(lang) || Locale.US.getISO3Language().equals(lang)) {
            if (Locale.SIMPLIFIED_CHINESE.getISO3Country().equals(country) || Locale.US.getISO3Country().equals(country)) {
                return 1;
            }
            return 0;
        }
        return -2;
    }

    @Override // com.xiaopeng.speech.tts.XpTextToSpeechServiceBase
    protected String[] onGetLanguage() {
        return this.mCurrentLanguage;
    }

    @Override // com.xiaopeng.speech.tts.XpTextToSpeechServiceBase
    protected int onLoadLanguage(String lang, String country, String variant) {
        this.mCurrentLanguage = new String[]{lang, country, ""};
        return onIsLanguageAvailable(lang, country, variant);
    }

    @Override // com.xiaopeng.speech.tts.XpTextToSpeechServiceBase
    protected void onSynthesizeText(XpSynthesisRequest request, XpSynthesisCallback callback) {
        String refText = request.getCharSequenceText().toString();
        LogUtils.i(TAG, "onSynthesizeText " + refText);
        if (callback == null) {
            return;
        }
        this.mCallback = callback;
        Bundle params = request.getParams();
        IEngineCallback cb = new MyEngineCallback(callback);
        int ret = 0;
        if (this.mXpMediaEngine.speak(params, cb) == 0) {
            this.mEngine = this.mXpMediaEngine;
        } else {
            ret = this.mTtsEngine.speak(params, cb);
            this.mEngine = this.mTtsEngine;
        }
        if (ret != 0) {
            this.mCallback.error(-3);
        }
    }

    @Override // com.xiaopeng.speech.tts.XpTextToSpeechServiceBase
    protected void onStop(Bundle params) {
        LogUtils.i(TAG, "onStop");
        if (this.mEngine != null) {
            this.mEngine.stop();
            this.mEngine = null;
        }
    }

    @Override // com.xiaopeng.speech.tts.XpTextToSpeechServiceBase
    protected boolean isUseServicePlayback() {
        return false;
    }

    /* loaded from: classes.dex */
    private class MyEngineCallback implements IEngineCallback {
        private XpSynthesisCallback mCallback;

        public MyEngineCallback(XpSynthesisCallback cb) {
            this.mCallback = cb;
        }

        @Override // com.xiaopeng.xpspeechservice.tts.IEngineCallback
        public void begin(int sampleRate, int format, int channelCount) {
            LogUtils.i(XpTextToSpeechService.TAG, "beginning %d %d %d", Integer.valueOf(sampleRate), Integer.valueOf(format), Integer.valueOf(channelCount));
            this.mCallback.start(sampleRate, format, channelCount);
        }

        @Override // com.xiaopeng.xpspeechservice.tts.IEngineCallback
        public void received(byte[] audioData) {
            if (audioData.length != 0) {
                this.mCallback.audioAvailable(audioData, 0, audioData.length);
            }
        }

        @Override // com.xiaopeng.xpspeechservice.tts.IEngineCallback
        public void end() {
            LogUtils.i(XpTextToSpeechService.TAG, "end");
            this.mCallback.done();
        }

        @Override // com.xiaopeng.xpspeechservice.tts.IEngineCallback
        public void error() {
            LogUtils.i(XpTextToSpeechService.TAG, AIError.KEY_TEXT);
            this.mCallback.error();
            this.mCallback.done();
        }
    }
}
