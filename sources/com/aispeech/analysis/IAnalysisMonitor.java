package com.aispeech.analysis;

import com.aispeech.upload.FileBuilder;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public interface IAnalysisMonitor {
    void cacheData(String str, String str2, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, Map<String, Object> map);

    void cacheData(String str, String str2, String str3, JSONObject jSONObject);

    void cacheFile(String str);

    void cacheFileBuilder(FileBuilder fileBuilder);

    void disableUpload();

    void enableUpload();

    void release();

    void startUploadImmediately();

    void stop();

    /* loaded from: classes.dex */
    public static class IAnalysisMonitorEmpty implements IAnalysisMonitor {
        private static volatile IAnalysisMonitor instance = null;

        private IAnalysisMonitorEmpty() {
        }

        public static IAnalysisMonitor getInstance() {
            if (instance == null) {
                synchronized (IAnalysisMonitorEmpty.class) {
                    if (instance == null) {
                        instance = new IAnalysisMonitorEmpty();
                    }
                }
            }
            return instance;
        }

        @Override // com.aispeech.analysis.IAnalysisMonitor
        public void disableUpload() {
        }

        @Override // com.aispeech.analysis.IAnalysisMonitor
        public void enableUpload() {
        }

        @Override // com.aispeech.analysis.IAnalysisMonitor
        public void cacheData(String str, String str2, String str3, JSONObject jSONObject) {
        }

        @Override // com.aispeech.analysis.IAnalysisMonitor
        public void cacheData(String str, String str2, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, Map<String, Object> map) {
        }

        @Override // com.aispeech.analysis.IAnalysisMonitor
        public void cacheFileBuilder(FileBuilder fileBuilder) {
        }

        @Override // com.aispeech.analysis.IAnalysisMonitor
        public void cacheFile(String str) {
        }

        @Override // com.aispeech.analysis.IAnalysisMonitor
        public void startUploadImmediately() {
        }

        @Override // com.aispeech.analysis.IAnalysisMonitor
        public void stop() {
        }

        @Override // com.aispeech.analysis.IAnalysisMonitor
        public void release() {
        }
    }
}
