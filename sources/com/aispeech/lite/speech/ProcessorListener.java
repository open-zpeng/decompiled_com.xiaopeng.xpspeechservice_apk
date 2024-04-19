package com.aispeech.lite.speech;

import com.aispeech.AIError;
/* loaded from: classes.dex */
public interface ProcessorListener {
    void onCancel();

    void onError(AIError aIError);

    void onInit(int i);

    void onRawDataReceived(byte[] bArr, int i);

    void onReadyForSpeech();

    void onRecorderStopped();

    void onResultDataReceived(byte[] bArr, int i, int i2);
}
