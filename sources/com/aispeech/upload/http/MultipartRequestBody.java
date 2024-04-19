package com.aispeech.upload.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes.dex */
public class MultipartRequestBody implements RequestBody {
    public static final String FORM = "multipart/form-data";
    private String type;
    private String boundary = createBoundary();
    private String startBoundary = "--" + this.boundary;
    private String endBoundary = this.startBoundary + "--";
    final Map<String, Object> params = new HashMap();

    public MultipartRequestBody setType(String str) {
        this.type = str;
        return this;
    }

    public MultipartRequestBody addFormDataPart(String str, String str2) {
        this.params.put(str, str2);
        return this;
    }

    public MultipartRequestBody addFormDataPart(String str, Binary binary) {
        this.params.put(str, binary);
        return this;
    }

    final Map<String, Object> getParams() {
        return this.params;
    }

    public String getContentType() {
        return this.type + "; boundary=" + this.boundary;
    }

    public static Binary create(final File file) {
        if (file == null) {
            throw new NullPointerException("content == null");
        }
        return new Binary() { // from class: com.aispeech.upload.http.MultipartRequestBody.1
            @Override // com.aispeech.upload.http.Binary
            public final String mimeType() {
                String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(file.getAbsolutePath());
                if (contentTypeFor == null) {
                    return "application/octet-stream";
                }
                return contentTypeFor;
            }

            @Override // com.aispeech.upload.http.Binary
            public final String fileName() {
                return file.getName();
            }

            @Override // com.aispeech.upload.http.Binary
            public final void writeBinary(OutputStream outputStream) throws IOException {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        outputStream.write(bArr, 0, read);
                    } else {
                        fileInputStream.close();
                        return;
                    }
                }
            }

            @Override // com.aispeech.upload.http.Binary
            public final long getContentLength() {
                return file.length();
            }
        };
    }

    public long getContentLength() {
        long length;
        long length2;
        long j = 0;
        for (Map.Entry<String, Object> entry : this.params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Binary) {
                Binary binary = (Binary) value;
                length = j + (this.startBoundary + "\r\nContent-Disposition: form-data; name = \"" + key + "\"; filename = \"" + binary.fileName() + "\"\r\nContent-Type: " + binary.mimeType() + "\r\n\r\n").getBytes().length;
                length2 = binary.getContentLength();
            } else {
                length = j + (this.startBoundary + "\r\nContent-Disposition: form-data; name = \"" + key + "\"\r\nContent-Type: text/plain\r\n\r\n").getBytes().length;
                length2 = (long) ((String) value).getBytes().length;
            }
            j = length + length2 + "\r\n".getBytes().length;
        }
        if (this.params.size() > 0) {
            return j + this.endBoundary.getBytes().length;
        }
        return j;
    }

    @Override // com.aispeech.upload.http.RequestBody
    public void addRequestProperty(HttpURLConnection httpURLConnection) {
    }

    @Override // com.aispeech.upload.http.RequestBody
    public void onWriteBody(OutputStream outputStream) {
        try {
            for (Map.Entry<String, Object> entry : this.params.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof Binary) {
                    writeFromBinary(key, (Binary) value, outputStream);
                } else {
                    writeFromString(key, (String) value, outputStream);
                }
                outputStream.write("\r\n".getBytes());
            }
            if (this.params.size() > 0) {
                outputStream.write(this.endBoundary.getBytes());
            }
        } catch (IOException e) {
        }
    }

    public void writeFromString(String str, String str2, OutputStream outputStream) throws IOException {
        outputStream.write((this.startBoundary + "\r\nContent-Disposition: form-data; name = \"" + str + "\"\r\nContent-Type: text/plain\r\n\r\n").getBytes());
        outputStream.write(str2.getBytes());
    }

    public void writeFromBinary(String str, Binary binary, OutputStream outputStream) throws IOException {
        outputStream.write((this.startBoundary + "\r\nContent-Disposition: form-data; name = \"" + str + "\"; filename = \"" + binary.fileName() + "\"\r\nContent-Type: " + binary.mimeType() + "\r\n\r\n").getBytes());
        binary.writeBinary(outputStream);
    }

    private String createBoundary() {
        return "OkHttp" + UUID.randomUUID();
    }
}
