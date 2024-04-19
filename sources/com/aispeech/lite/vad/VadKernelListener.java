package com.aispeech.lite.vad;

import com.aispeech.AIError;
/* loaded from: classes.dex */
public interface VadKernelListener {
    void onBufferReceived(byte[] bArr);

    void onError(AIError aIError);

    void onInit(int i);

    void onRmsChanged(float f);

    void onVadEnd();

    void onVadStart();
}
