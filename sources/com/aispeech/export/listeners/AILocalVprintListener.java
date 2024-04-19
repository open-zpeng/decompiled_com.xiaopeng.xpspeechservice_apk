package com.aispeech.export.listeners;

import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.lite.speech.EngineListener;
/* loaded from: classes.dex */
public interface AILocalVprintListener extends EngineListener {
    @Override // com.aispeech.lite.speech.EngineListener
    void onError(AIError aIError);

    @Override // com.aispeech.lite.speech.EngineListener
    void onInit(int i);

    void onResults(AIResult aIResult);
}
