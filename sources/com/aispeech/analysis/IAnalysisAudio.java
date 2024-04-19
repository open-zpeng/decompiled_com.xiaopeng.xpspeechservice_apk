package com.aispeech.analysis;

import com.aispeech.common.Log;
import com.aispeech.upload.FileBuilder;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public interface IAnalysisAudio {
    void cacheData(String str, String str2, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, Map<String, Object> map);

    void cacheFile(String str);

    void cacheFileBuilder(FileBuilder fileBuilder);

    String getAudioMode();

    int getUploadDelayTime();

    boolean init(JSONObject jSONObject);

    boolean isUploadEnable();

    void release();

    void setAudioUpload(boolean z);

    void start();

    void startUploadImmediately();

    void stop();

    /* loaded from: classes.dex */
    public static class AnalysisAudioEmpty implements IAnalysisAudio {
        private static volatile IAnalysisAudio instance = null;

        private AnalysisAudioEmpty() {
            Log.d("AnalysisAudioEmpty", "No Implement IAnalysisAudio, use default Empty");
        }

        public static IAnalysisAudio getInstance() {
            if (instance == null) {
                synchronized (AnalysisAudioEmpty.class) {
                    if (instance == null) {
                        instance = new AnalysisAudioEmpty();
                    }
                }
            }
            return instance;
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public String getAudioMode() {
            return "forbidden";
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public boolean init(JSONObject jSONObject) {
            return true;
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public boolean isUploadEnable() {
            return false;
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public void setAudioUpload(boolean z) {
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public void cacheData(String str, String str2, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, Map<String, Object> map) {
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public void cacheFile(String str) {
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public void cacheFileBuilder(FileBuilder fileBuilder) {
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public void start() {
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public void stop() {
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public void release() {
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public void startUploadImmediately() {
        }

        @Override // com.aispeech.analysis.IAnalysisAudio
        public int getUploadDelayTime() {
            return 0;
        }
    }
}
