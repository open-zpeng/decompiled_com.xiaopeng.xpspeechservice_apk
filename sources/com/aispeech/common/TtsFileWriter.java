package com.aispeech.common;
/* loaded from: classes.dex */
public interface TtsFileWriter {
    void close();

    void deleteIfOpened();

    String getAbsolutePath();

    void write(byte[] bArr);
}
