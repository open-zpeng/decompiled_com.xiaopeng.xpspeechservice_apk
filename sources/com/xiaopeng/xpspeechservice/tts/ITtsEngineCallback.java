package com.xiaopeng.xpspeechservice.tts;
/* loaded from: classes.dex */
public interface ITtsEngineCallback {
    void onEvent(EventType eventType);

    void onEvent(EventType eventType, Object obj);
}
