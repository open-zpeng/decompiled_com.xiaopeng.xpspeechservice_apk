package com.aispeech.echo;

import com.aispeech.AIError;
/* loaded from: classes.dex */
public interface EchoKernelListener {
    void onError(AIError aIError);

    void onInit(int i);

    void onResultBufferReceived(byte[] bArr);

    void onVoipBufferReceived(byte[] bArr);
}
