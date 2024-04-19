package com.aispeech.export.listeners;

import com.aispeech.AIError;
import com.aispeech.lite.oneshot.OneshotCache;
import com.aispeech.lite.speech.EngineListener;
/* loaded from: classes.dex */
public interface AIWakeupListener extends EngineListener {
    @Override // com.aispeech.lite.speech.EngineListener
    void onError(AIError aIError);

    @Override // com.aispeech.lite.speech.EngineListener
    void onInit(int i);

    void onNotOneshot(String str);

    void onOneshot(String str, OneshotCache<byte[]> oneshotCache);

    void onPreWakeup(String str, double d, String str2);

    @Override // com.aispeech.lite.speech.EngineListener
    void onRawDataReceived(byte[] bArr, int i);

    @Override // com.aispeech.lite.speech.EngineListener
    void onReadyForSpeech();

    void onVprintCutDataReceived(int i, byte[] bArr, int i2);

    void onWakeup(String str, double d, String str2);
}
