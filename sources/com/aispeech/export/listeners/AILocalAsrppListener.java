package com.aispeech.export.listeners;

import com.aispeech.AIResult;
import com.aispeech.lite.speech.EngineListener;
/* loaded from: classes.dex */
public interface AILocalAsrppListener extends EngineListener {
    void onBeginningOfSpeech();

    void onEndOfSpeech();

    void onResults(AIResult aIResult);

    void onRmsChanged(float f);
}
