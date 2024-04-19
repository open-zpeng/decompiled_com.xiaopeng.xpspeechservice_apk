package com.aispeech.analysis;

import android.content.Context;
import com.aispeech.common.Log;
/* loaded from: classes.dex */
public interface IAnalysisConfig {
    String getUploadConfig(Context context, int i, String str, String str2);

    /* loaded from: classes.dex */
    public static class IAnalysisConfigEmpty implements IAnalysisConfig {
        private static volatile IAnalysisConfigEmpty instance = null;

        private IAnalysisConfigEmpty() {
            Log.d("IAnalysisConfigEmpty", "No Implement IAnalysisConfig, use default Empty");
        }

        @Override // com.aispeech.analysis.IAnalysisConfig
        public String getUploadConfig(Context context, int i, String str, String str2) {
            return null;
        }

        public static IAnalysisConfig getInstance() {
            if (instance == null) {
                synchronized (IAnalysisConfigEmpty.class) {
                    if (instance == null) {
                        instance = new IAnalysisConfigEmpty();
                    }
                }
            }
            return instance;
        }
    }
}
