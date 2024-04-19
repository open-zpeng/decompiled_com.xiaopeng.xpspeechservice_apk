package com.xiaopeng.xpspeechservice.tts.aittsengine;

import android.os.Bundle;
import android.os.SystemProperties;
import com.aispeech.AIError;
import com.aispeech.export.engines.AILocalTTSEngine;
import com.aispeech.export.listeners.AITTSListener;
import com.xiaopeng.xpspeechservice.tts.EventType;
import com.xiaopeng.xpspeechservice.tts.ITtsEngineCallback;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
/* loaded from: classes.dex */
public class LocalTTSEngine implements ITtsEngine {
    private static final int SPEECH_STREAM_DEFAULT = 10;
    private static final String TAG = "LocalTTSEngine";
    private static final String TTS_DICT_RES = "tts/aitts_sent_dict_local.db";
    private static final String TTS_FRONT_RES = "tts/local_front.bin";
    private final ITtsEngineCallback mCallback;
    private AILocalTTSEngine mEngine;
    private AITTSListener mLocalListener = new AITTSListener() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.LocalTTSEngine.1
        @Override // com.aispeech.export.listeners.AITTSListener
        public void onInit(int initState) {
            LogUtils.i(LocalTTSEngine.TAG, "onInit i=" + initState);
            if (initState == 0) {
                LocalTTSEngine.this.mCallback.onEvent(EventType.EVENT_ENGINE_INITED, new String("unset"));
            } else if (initState == -1) {
                LocalTTSEngine.this.mEngine.deleteLocalResFile();
            }
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onError(String utteranceId, AIError error) {
            LogUtils.i(LocalTTSEngine.TAG, "onError utteranceId=" + utteranceId + " error=" + error.getError());
            LocalTTSEngine.this.mCallback.onEvent(EventType.EVENT_SYNTH_ERROR, utteranceId);
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onReady(String utteranceId) {
            LogUtils.i(LocalTTSEngine.TAG, "onReady utteranceId=" + utteranceId);
            LocalTTSEngine.this.mCallback.onEvent(EventType.EVENT_SYNTH_BEGIN, utteranceId);
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onSynthesizeStart(String utteranceId) {
            LogUtils.i(LocalTTSEngine.TAG, "onSynthesizeStart utteranceId=" + utteranceId);
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onSynthesizeDataArrived(String utteranceId, byte[] audioData) {
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onSynthesizeFinish(String utteranceId) {
            LogUtils.i(LocalTTSEngine.TAG, "onSynthesizeFinish utteranceId=" + utteranceId);
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onCompletion(String utteranceId) {
            LogUtils.i(LocalTTSEngine.TAG, "onCompletion utteranceId=" + utteranceId);
            LocalTTSEngine.this.mCallback.onEvent(EventType.EVENT_SYNTH_END, utteranceId);
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onProgress(int currentTime, int totalTime, boolean isRefTextTTSFinished) {
        }
    };
    private XpTtsTextRevise mTextRevise;
    private static final String TTS_BACK_RES_ZHILING = "tts/zhilingf_common_back_ce_local.v2.1.0.bin";
    private static final String[] mBackResBinArray = {TTS_BACK_RES_ZHILING};

    public LocalTTSEngine(ITtsEngineCallback cb) {
        this.mCallback = cb;
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void init() {
        LogUtils.v(TAG, "initEngine");
        this.mEngine = AILocalTTSEngine.getInstance();
        this.mEngine.setBackResBinArray(mBackResBinArray);
        this.mEngine.setDictDb(TTS_DICT_RES);
        this.mEngine.setFrontResBin(TTS_FRONT_RES);
        this.mEngine.setSpeechVolume(100);
        this.mEngine.setUseCache(false);
        this.mEngine.setUseStreamType(true);
        this.mEngine.init(this.mLocalListener);
        this.mTextRevise = XpTtsTextRevise.getInstance();
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void speak(Bundle params) {
        LogUtils.i(TAG, "speak");
        String txt = params.getString("txt");
        String uid = params.getString("uid");
        int streamType = params.getInt("streamType", 10);
        this.mEngine.setStreamType(streamType);
        float volume = params.getFloat("volume", 1.0f);
        int baseVolume = 100;
        if (streamType == 9) {
            baseVolume = 60;
        }
        int volInt = SystemProperties.getInt("sys.xiaopeng.tts.volume", (int) (baseVolume * volume));
        this.mEngine.setSpeechVolume(volInt);
        int rate = params.getInt("rate", 100);
        setSpeechRate(rate);
        LogUtils.v(TAG, "streamType %d volume %d rate %d", Integer.valueOf(streamType), Integer.valueOf(volInt), Integer.valueOf(rate));
        this.mEngine.speak(this.mTextRevise.reviseText(txt), uid);
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void stop() {
        LogUtils.i(TAG, "stop");
        this.mEngine.stop();
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void reset() {
        LogUtils.i(TAG, "reset");
        shutdown();
        init();
    }

    private void shutdown() {
        LogUtils.i(TAG, "destroy");
        this.mEngine.destroy();
        this.mEngine = null;
    }

    private void setSpeechRate(int rate) {
        float fRate = 100.0f / rate;
        this.mEngine.setSpeechRate(fRate);
    }
}
