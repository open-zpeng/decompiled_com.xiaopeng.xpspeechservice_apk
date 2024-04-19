package com.aispeech.lite.e.a;

import com.aispeech.AIError;
/* loaded from: classes.dex */
public interface e {
    void a(int i);

    void onBufferReceived(byte[] bArr);

    void onError(AIError aIError);

    void onInit(int i);

    void onVadEnd();

    void onVadStart();
}
