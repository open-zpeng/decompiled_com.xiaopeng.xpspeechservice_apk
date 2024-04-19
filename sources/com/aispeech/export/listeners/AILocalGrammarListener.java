package com.aispeech.export.listeners;

import com.aispeech.AIError;
import com.aispeech.lite.speech.EngineListener;
/* loaded from: classes.dex */
public interface AILocalGrammarListener extends EngineListener {
    void onBuildCompleted(String str);

    @Override // com.aispeech.lite.speech.EngineListener
    void onError(AIError aIError);

    @Override // com.aispeech.lite.speech.EngineListener
    void onInit(int i);
}
