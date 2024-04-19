package com.aispeech.export.listeners;

import com.aispeech.lite.oneshot.OneshotCache;
import com.aispeech.lite.speech.EngineListener;
/* loaded from: classes.dex */
public interface AIFespCarListener extends EngineListener {
    void onDoaResult(int i);

    void onNotOneshot(String str);

    void onOneshot(String str, OneshotCache<byte[]> oneshotCache);

    void onVprintCutDataReceived(int i, byte[] bArr, int i2);

    void onWakeup(double d, String str);
}
