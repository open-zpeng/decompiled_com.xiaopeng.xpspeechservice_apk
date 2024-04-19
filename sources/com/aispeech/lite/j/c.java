package com.aispeech.lite.j;

import com.aispeech.lite.oneshot.OneshotCache;
import com.aispeech.lite.speech.ProcessorListener;
/* loaded from: classes.dex */
public interface c extends ProcessorListener {
    void a(int i, byte[] bArr, int i2);

    void a(String str);

    void a(String str, double d, String str2);

    void a(String str, OneshotCache<byte[]> oneshotCache);

    void b(String str, double d, String str2);
}
