package com.xiaopeng.xpspeechservice.tts.aittsengine;

import android.os.Bundle;
/* loaded from: classes.dex */
public interface ITtsEngine {
    void init();

    void reset();

    void speak(Bundle bundle);

    void stop();
}
