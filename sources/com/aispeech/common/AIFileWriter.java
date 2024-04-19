package com.aispeech.common;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
/* loaded from: classes.dex */
public class AIFileWriter implements TtsFileWriter {
    private static final String a = AIFileWriter.class.getCanonicalName();
    private RandomAccessFile b;
    private String c;

    public static synchronized AIFileWriter createFileWriter(File file) {
        synchronized (AIFileWriter.class) {
            Log.d(a, "create FileWriter.");
            if (file == null) {
                return null;
            }
            try {
                return new AIFileWriter(file);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private AIFileWriter(File file) throws IOException {
        this.b = null;
        this.c = null;
        close();
        this.c = file.getAbsolutePath();
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            if (parentFile.exists()) {
                if (parentFile.isFile()) {
                    parentFile.delete();
                    parentFile.mkdirs();
                }
            } else {
                parentFile.mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            this.b = new RandomAccessFile(file, "rw");
        }
    }

    @Override // com.aispeech.common.TtsFileWriter
    public void write(byte[] bArr) {
        RandomAccessFile randomAccessFile = this.b;
        if (randomAccessFile != null) {
            try {
                randomAccessFile.write(bArr, 0, bArr.length);
            } catch (IOException e) {
                Log.e(a, e.getMessage() == null ? "unknown exception in write" : e.getMessage());
                close();
            }
        }
    }

    @Override // com.aispeech.common.TtsFileWriter
    public synchronized void close() {
        if (this.b != null) {
            Log.d(a, "close File.");
            try {
                this.b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.b = null;
        }
    }

    @Override // com.aispeech.common.TtsFileWriter
    public synchronized void deleteIfOpened() {
        if (!(this.b == null)) {
            try {
                this.b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.aispeech.common.TtsFileWriter
    public String getAbsolutePath() {
        return this.c;
    }
}
