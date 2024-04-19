package com.aispeech.upload;

import java.util.List;
/* loaded from: classes.dex */
public interface EncodeCallback {
    byte[] onEncode(byte[] bArr, FileBuilder fileBuilder);

    void onStart(FileBuilder fileBuilder);

    List<byte[]> onStop(FileBuilder fileBuilder);
}
