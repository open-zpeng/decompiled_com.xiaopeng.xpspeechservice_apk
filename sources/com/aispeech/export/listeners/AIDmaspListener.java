package com.aispeech.export.listeners;

import com.aispeech.lite.speech.EngineListener;
/* loaded from: classes.dex */
public interface AIDmaspListener extends EngineListener {
    void onDoaResult(int i);

    void onResultDataReceived(byte[] bArr, int i);

    void onWakeup(String str, double d, double d2, String str2, int i);
}
