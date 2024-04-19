package com.aispeech.upload.http;

import android.text.TextUtils;
import com.aispeech.upload.EncodeCallback;
import com.aispeech.upload.FileBuilder;
import com.aispeech.upload.util.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class FileRequestBody implements RequestBody {
    private EncodeCallback mEncodeCallback;
    private String mFileJson;
    private String type;
    private final Map<String, Object> params = new HashMap();
    private String boundary = createBoundary();
    private final String NEWLINE = "\r\n";
    private final String PREFIX = "--";

    public FileRequestBody setEncodeCallback(EncodeCallback encodeCallback) {
        this.mEncodeCallback = encodeCallback;
        return this;
    }

    public FileRequestBody setType(String str) {
        this.type = str;
        return this;
    }

    public FileRequestBody setFileJson(String str) {
        this.mFileJson = str;
        return this;
    }

    public FileRequestBody addFormDataPart(String str, String str2) {
        this.params.put(str, str2);
        return this;
    }

    public FileRequestBody addFile(File file) {
        return addFile(file, null);
    }

    public FileRequestBody addFile(File file, String str) {
        if (!FileUtils.isFile(file)) {
            return this;
        }
        Binary create = create(file);
        if (TextUtils.isEmpty(str)) {
            str = file.getName();
        }
        this.params.put(str, create);
        return this;
    }

    final Map<String, Object> getParams() {
        return this.params;
    }

    private String getContentType() {
        return this.type + "; boundary=" + this.boundary;
    }

    private Binary create(final File file) {
        if (file == null) {
            throw new NullPointerException("file == null");
        }
        return new Binary() { // from class: com.aispeech.upload.http.FileRequestBody.1
            @Override // com.aispeech.upload.http.Binary
            public String mimeType() {
                return "";
            }

            @Override // com.aispeech.upload.http.Binary
            public String fileName() {
                return file.getName();
            }

            @Override // com.aispeech.upload.http.Binary
            public void writeBinary(OutputStream outputStream) {
                FileInputStream fileInputStream;
                FileInputStream fileInputStream2 = null;
                try {
                    try {
                        fileInputStream = new FileInputStream(file);
                        try {
                            byte[] bArr = new byte[2048];
                            if (FileRequestBody.this.mEncodeCallback == null) {
                                while (true) {
                                    int read = fileInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    outputStream.write(bArr, 0, read);
                                }
                            } else {
                                FileBuilder fileBuilder = new FileBuilder();
                                if (!TextUtils.isEmpty(FileRequestBody.this.mFileJson) && FileRequestBody.this.mFileJson.startsWith("{")) {
                                    JSONObject jSONObject = new JSONObject(FileRequestBody.this.mFileJson);
                                    fileBuilder.setPath(jSONObject.optString("path"));
                                    fileBuilder.setEncode(jSONObject.optString("encode"));
                                }
                                FileRequestBody.this.mEncodeCallback.onStart(fileBuilder);
                                while (fileInputStream.read(bArr) != -1) {
                                    byte[] onEncode = FileRequestBody.this.mEncodeCallback.onEncode(bArr, fileBuilder);
                                    if (onEncode != null && onEncode.length > 0) {
                                        outputStream.write(onEncode, 0, onEncode.length);
                                    }
                                }
                                List<byte[]> onStop = FileRequestBody.this.mEncodeCallback.onStop(fileBuilder);
                                if (onStop != null) {
                                    for (byte[] bArr2 : onStop) {
                                        outputStream.write(bArr2, 0, bArr2.length);
                                    }
                                }
                            }
                            fileInputStream.close();
                        } catch (Exception e) {
                            fileInputStream2 = fileInputStream;
                            fileInputStream2.close();
                        } catch (Throwable th) {
                            th = th;
                            try {
                                fileInputStream.close();
                            } catch (Exception e2) {
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                    }
                } catch (Exception e4) {
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = null;
                }
            }

            @Override // com.aispeech.upload.http.Binary
            public long getContentLength() {
                return file.length();
            }
        };
    }

    @Override // com.aispeech.upload.http.RequestBody
    public void addRequestProperty(HttpURLConnection httpURLConnection) {
        httpURLConnection.addRequestProperty("Content-Type", getContentType());
    }

    @Override // com.aispeech.upload.http.RequestBody
    public void onWriteBody(OutputStream outputStream) {
        try {
            for (Map.Entry<String, Object> entry : this.params.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof Binary) {
                    writeFromBinary("file", (Binary) value, outputStream);
                } else {
                    writeFromString(key, (String) value, outputStream);
                }
                outputStream.write("\r\n".getBytes());
            }
            if (this.params.size() > 0) {
                outputStream.write(("--" + this.boundary + "--").getBytes());
                outputStream.write("\r\n".getBytes());
            }
        } catch (IOException e) {
        }
    }

    private void writeFromString(String str, String str2, OutputStream outputStream) throws IOException {
        outputStream.write(("--" + this.boundary + "\r\nContent-Disposition: form-data; name = \"" + str + "\";\r\nContent-Type: text/plain\r\n\r\n").getBytes());
        outputStream.write(str2.getBytes());
    }

    private void writeFromBinary(String str, Binary binary, OutputStream outputStream) throws IOException {
        outputStream.write(("--" + this.boundary + "\r\nContent-Disposition: form-data; name = \"" + str + "\";filename = \"" + binary.fileName() + "\"\r\nContent-Type: " + binary.mimeType() + "\r\n\r\n").getBytes());
        binary.writeBinary(outputStream);
    }

    private String createBoundary() {
        return "dzHttp" + UUID.randomUUID();
    }
}
