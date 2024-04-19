package com.aispeech.upload.http;

import java.io.OutputStream;
import java.net.HttpURLConnection;
/* loaded from: classes.dex */
public interface RequestBody {
    void addRequestProperty(HttpURLConnection httpURLConnection);

    void onWriteBody(OutputStream outputStream);
}
