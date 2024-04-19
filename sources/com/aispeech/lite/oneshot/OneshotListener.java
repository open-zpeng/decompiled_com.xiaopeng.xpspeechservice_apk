package com.aispeech.lite.oneshot;

import com.aispeech.AIError;
/* loaded from: classes.dex */
public interface OneshotListener {
    void onError(AIError aIError);

    void onInit(int i);

    void onNotOneshot(String str);

    void onOneshot(String str, OneshotCache<byte[]> oneshotCache);
}
