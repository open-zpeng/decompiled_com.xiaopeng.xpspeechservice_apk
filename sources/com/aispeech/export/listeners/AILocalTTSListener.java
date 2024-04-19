package com.aispeech.export.listeners;

import com.aispeech.AIError;
@Deprecated
/* loaded from: classes.dex */
public interface AILocalTTSListener {
    void onError(String str, AIError aIError);

    void onInit(int i);

    void onSpeechFinish(String str);

    void onSpeechProgress(int i, int i2, boolean z);

    void onSpeechStart(String str);

    void onSynthesizeDataArrived(String str, byte[] bArr);

    void onSynthesizeFinish(String str);

    void onSynthesizeStart(String str);
}
