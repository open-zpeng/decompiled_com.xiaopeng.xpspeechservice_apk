package com.xiaopeng.xpspeechservice.tts;
/* loaded from: classes.dex */
public interface IEngineCallback {
    void begin(int i, int i2, int i3);

    void end();

    void error();

    void received(byte[] bArr);
}
