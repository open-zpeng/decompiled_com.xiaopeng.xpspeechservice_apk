package com.aispeech.common;

import com.aispeech.lite.AISampleRate;
import com.aispeech.lite.b.e;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
/* loaded from: classes.dex */
public class WavFileWriter implements TtsFileWriter {
    private static final String a = WavFileWriter.class.getCanonicalName();
    private RandomAccessFile b = null;
    private File c = null;

    public static synchronized WavFileWriter createWavFileWriter(File file, AISampleRate aISampleRate, int i, int i2) {
        WavFileWriter createWavFileWriter;
        synchronized (WavFileWriter.class) {
            createWavFileWriter = createWavFileWriter(file, aISampleRate, i, i2, false);
        }
        return createWavFileWriter;
    }

    public static synchronized WavFileWriter createWavFileWriter(File file, AISampleRate aISampleRate, int i, int i2, boolean z) {
        synchronized (WavFileWriter.class) {
            Log.d(a, "create WavFileWriter.");
            if (file == null) {
                return null;
            }
            try {
                return new WavFileWriter(file, aISampleRate, i, i2, z);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static WavFileWriter createWavFileWriter(File file, e eVar) {
        return createWavFileWriter(file, eVar, false);
    }

    public static synchronized WavFileWriter createWavFileWriter(File file, e eVar, boolean z) {
        synchronized (WavFileWriter.class) {
            if (file == null) {
                return null;
            }
            try {
                return new WavFileWriter(file, eVar, z);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private WavFileWriter(File file, e eVar, boolean z) throws IOException {
        a(file, eVar.b(), eVar.c(), eVar.d(), z);
    }

    private WavFileWriter(File file, AISampleRate aISampleRate, int i, int i2, boolean z) throws IOException {
        a(file, aISampleRate, i, i2, z);
    }

    private void a(File file, AISampleRate aISampleRate, int i, int i2, boolean z) throws IOException {
        this.c = file;
        close();
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
        }
        boolean exists = file.exists();
        if (!z && exists && file.length() > 44) {
            file.delete();
        }
        this.b = new RandomAccessFile(file, "rw");
        if (z) {
            if (exists && file.length() > 44) {
                this.b.seek(file.length());
                return;
            }
            this.b.seek(0L);
        }
        Log.d(a, "writer header to Wav File.");
        int i3 = i * i2;
        this.b.writeBytes("RIFF");
        this.b.writeInt(0);
        this.b.writeBytes("WAVE");
        this.b.writeBytes("fmt ");
        this.b.writeInt(Integer.reverseBytes(16));
        this.b.writeShort(Short.reverseBytes((short) 1));
        this.b.writeShort(Short.reverseBytes((short) i));
        this.b.writeInt(Integer.reverseBytes(aISampleRate.getValue()));
        this.b.writeInt(Integer.reverseBytes(aISampleRate.getValue() * i3));
        this.b.writeShort(Short.reverseBytes((short) i3));
        this.b.writeShort(Short.reverseBytes((short) (i2 << 3)));
        this.b.writeBytes("data");
        this.b.writeInt(0);
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
            Log.d(a, "close wav File.");
            try {
                int length = (int) this.b.length();
                this.b.seek(4L);
                this.b.writeInt(Integer.reverseBytes(length - 8));
                this.b.seek(40L);
                this.b.writeInt(Integer.reverseBytes(length - 44));
                try {
                    this.b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                Log.e(a, e2.getMessage() == null ? "unknown exception in close" : e2.getMessage());
                try {
                    this.b.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
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
            if (this.c != null && this.c.exists()) {
                this.c.delete();
            }
        }
    }

    @Override // com.aispeech.common.TtsFileWriter
    public String getAbsolutePath() {
        File file = this.c;
        if (file == null) {
            return null;
        }
        return file.getAbsolutePath();
    }

    public static byte[] removeWaveHeader(byte[] bArr) {
        if (bArr != null && bArr.length > 44 && bArr[0] == 82 && bArr[1] == 73 && bArr[2] == 70 && bArr[3] == 70) {
            byte[] bArr2 = new byte[bArr.length - 44];
            System.arraycopy(bArr, 44, bArr2, 0, bArr2.length);
            Log.d(a, "remove wav header!");
            return bArr2;
        }
        return bArr;
    }
}
