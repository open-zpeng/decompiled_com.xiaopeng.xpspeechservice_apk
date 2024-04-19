package com.aispeech.lite.tts;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
/* loaded from: classes.dex */
public interface e {
    long a();

    void a(int i);

    void a(Context context, int i, int i2);

    @TargetApi(23)
    void a(AudioAttributes audioAttributes);

    void a(com.aispeech.lite.b.c cVar);

    void a(h hVar);

    void a(boolean z);

    void b();

    void c();

    void d();

    void e();
}
