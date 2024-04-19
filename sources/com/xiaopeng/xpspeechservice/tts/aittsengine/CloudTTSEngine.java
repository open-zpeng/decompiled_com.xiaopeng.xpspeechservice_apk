package com.xiaopeng.xpspeechservice.tts.aittsengine;

import android.os.Bundle;
import com.aispeech.AIError;
import com.aispeech.common.AIConstant;
import com.aispeech.export.engines.AICloudTTSEngine;
import com.aispeech.export.listeners.AITTSListener;
import com.xiaopeng.xpspeechservice.SpeechApp;
import com.xiaopeng.xpspeechservice.tts.EventType;
import com.xiaopeng.xpspeechservice.tts.ITtsEngineCallback;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
/* loaded from: classes.dex */
public class CloudTTSEngine implements ITtsEngine {
    private static final int SPEECH_STREAM_DEFAULT = 10;
    public static final String TAG = "CloudTTSEngine";
    private final ITtsEngineCallback mCallback;
    private AITTSListener mCloudListener = new AITTSListener() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.CloudTTSEngine.1
        @Override // com.aispeech.export.listeners.AITTSListener
        public void onInit(int initState) {
            LogUtils.i(CloudTTSEngine.TAG, "onInit i=" + initState);
            if (initState == 0) {
                CloudTTSEngine.this.mCallback.onEvent(EventType.EVENT_ENGINE_INITED, new String("unset"));
            }
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onError(String utteranceId, AIError error) {
            LogUtils.i(CloudTTSEngine.TAG, "onError utteranceId=" + utteranceId + " error=" + error.getError());
            CloudTTSEngine.this.mCallback.onEvent(EventType.EVENT_SYNTH_ERROR, utteranceId);
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onReady(String utteranceId) {
            LogUtils.i(CloudTTSEngine.TAG, "onReady utteranceId=" + utteranceId);
            CloudTTSEngine.this.mCallback.onEvent(EventType.EVENT_SYNTH_BEGIN, utteranceId);
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onSynthesizeStart(String utteranceId) {
            LogUtils.i(CloudTTSEngine.TAG, "onSynthesizeStart utteranceId=" + utteranceId);
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onSynthesizeDataArrived(String utteranceId, byte[] audioData) {
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onSynthesizeFinish(String utteranceId) {
            LogUtils.i(CloudTTSEngine.TAG, "onSynthesizeFinish utteranceId=" + utteranceId);
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onCompletion(String utteranceId) {
            LogUtils.i(CloudTTSEngine.TAG, "onCompletion utteranceId=" + utteranceId);
            CloudTTSEngine.this.mCallback.onEvent(EventType.EVENT_SYNTH_END, utteranceId);
        }

        @Override // com.aispeech.export.listeners.AITTSListener
        public void onProgress(int currentTime, int totalTime, boolean isRefTextTTSFinished) {
        }
    };
    private AICloudTTSEngine mEngine;

    public CloudTTSEngine(ITtsEngineCallback cb) {
        this.mCallback = cb;
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void init() {
        LogUtils.v(TAG, "init");
        this.mEngine = AICloudTTSEngine.createInstance();
        String audioPath = SpeechApp.getContext().getExternalCacheDir() + "/ttsCache";
        LogUtils.d(TAG, "audioPath " + audioPath);
        this.mEngine.setAudioPath(audioPath);
        this.mEngine.setMP3Quality(AIConstant.TTS_MP3_QUALITY_LOW);
        this.mEngine.setServer("https://tts.dui.ai/runtime/v2/synthesize");
        this.mEngine.setSpeaker("zhilingfa");
        this.mEngine.setTextType("text");
        this.mEngine.setUseStreamType(true);
        this.mEngine.setVolume("100");
        this.mEngine.init(this.mCloudListener);
    }

    @Override // com.xiaopeng.xpspeechservice.tts.aittsengine.ITtsEngine
    public void speak(Bundle params) {
        LogUtils.i(TAG, "speak");
        String txt = params.getString("txt");
        String uid = params.getString("uid");
        int streamType = params.getInt("streamType", 10);
        this.mEngine.setStreamType(streamType);
        float volume = params.getFloat("volume", 1.0f);
        int volInt = ((int) volume) * 100;
        this.mEngine.setVolume(String.valueOf(volInt));
        int rate = params.getInt("rate", 100);
        setSpeechRate(rate);
        LogUtils.v(TAG, "streamType %d volume %d rate %d", Integer.valueOf(streamType), Integer.valueOf(volInt), Integer.valueOf(rate));
        this.mEngine.speak(txt, uid);
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

    private void setSpeechRate(int rate) {
        float speed = 100.0f / rate;
        this.mEngine.setSpeed(String.valueOf(speed));
    }

    private void shutdown() {
        LogUtils.i(TAG, "destroy");
        this.mEngine.release();
        this.mEngine = null;
    }
}
