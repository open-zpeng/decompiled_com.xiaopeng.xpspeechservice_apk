package com.aispeech.lite.speech;

import com.aispeech.AIError;
/* loaded from: classes.dex */
public interface EngineListener {
    void onError(AIError aIError);

    void onInit(int i);

    void onRawDataReceived(byte[] bArr, int i);

    void onReadyForSpeech();

    void onResultDataReceived(byte[] bArr, int i, int i2);
}
