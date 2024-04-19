package com.aispeech.upload.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class Response {
    private InputStream inputStream;
    private byte[] responseBody;
    private int responseCode;
    private Map<String, List<String>> responseHeaders;

    public Response(int i, InputStream inputStream, Map<String, List<String>> map) {
        this.responseCode = i;
        this.responseHeaders = map;
        this.inputStream = inputStream;
    }

    public byte[] getResponseBody() {
        return this.responseBody;
    }

    public String string() {
        try {
            String convertStream2String = Util.convertStream2String(this.inputStream);
            Util.close(this.inputStream);
            this.inputStream = null;
            return convertStream2String;
        } catch (IOException e) {
            Util.close(this.inputStream);
            this.inputStream = null;
            return "";
        } catch (Throwable th) {
            Util.close(this.inputStream);
            this.inputStream = null;
            throw th;
        }
    }

    public long getContentLength() {
        return Long.parseLong(this.responseHeaders.get("Content-Length").get(0));
    }

    public int getResponseCode() {
        return this.responseCode;
    }
}
