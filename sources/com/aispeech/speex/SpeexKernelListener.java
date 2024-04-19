package com.aispeech.speex;

import com.aispeech.AIError;
/* loaded from: classes.dex */
public interface SpeexKernelListener {
    void onError(AIError aIError);

    void onInit(int i);

    void onResultBufferReceived(byte[] bArr, int i);
}
