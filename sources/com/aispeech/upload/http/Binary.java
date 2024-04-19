package com.aispeech.upload.http;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes.dex */
public interface Binary {
    String fileName();

    long getContentLength();

    String mimeType();

    void writeBinary(OutputStream outputStream) throws IOException;
}
