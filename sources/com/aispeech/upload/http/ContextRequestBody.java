package com.aispeech.upload.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
/* loaded from: classes.dex */
public class ContextRequestBody implements RequestBody {
    private String mContext;

    public ContextRequestBody setContent(String str) {
        this.mContext = str;
        return this;
    }

    public long getContentLength() {
        return this.mContext.getBytes().length;
    }

    @Override // com.aispeech.upload.http.RequestBody
    public void addRequestProperty(HttpURLConnection httpURLConnection) {
        StringBuilder sb = new StringBuilder();
        sb.append(getContentLength());
        httpURLConnection.addRequestProperty("Content-Length", sb.toString());
    }

    @Override // com.aispeech.upload.http.RequestBody
    public void onWriteBody(OutputStream outputStream) {
        try {
            outputStream.write(this.mContext.getBytes());
        } catch (IOException e) {
        }
    }
}
