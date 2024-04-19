package com.aispeech.export.listeners;

import com.aispeech.AIError;
/* loaded from: classes.dex */
public interface AITTSListener {
    void onCompletion(String str);

    void onError(String str, AIError aIError);

    void onInit(int i);

    void onProgress(int i, int i2, boolean z);

    void onReady(String str);

    void onSynthesizeDataArrived(String str, byte[] bArr);

    void onSynthesizeFinish(String str);

    void onSynthesizeStart(String str);
}
