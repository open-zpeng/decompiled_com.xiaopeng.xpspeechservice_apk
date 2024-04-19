package com.aispeech.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
/* loaded from: classes.dex */
public class AudioFileWriter {
    private File a = null;
    private FileOutputStream b = null;
    private FileWriter c;
    private BufferedWriter d;

    public synchronized void createFile(String str) {
        this.a = new File(str);
        if (!this.a.exists()) {
            try {
                this.a.createNewFile();
                this.b = new FileOutputStream(this.a);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void createTextFile(String str) {
        this.a = new File(str);
        if (!this.a.exists()) {
            try {
                this.a.createNewFile();
                this.c = new FileWriter(this.a);
                this.d = new BufferedWriter(this.c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void write(byte[] bArr, int i) {
        if (this.b != null) {
            try {
                this.b.write(bArr, 0, i);
                this.b.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void writeString(String str) {
        if (this.d != null) {
            try {
                BufferedWriter bufferedWriter = this.d;
                bufferedWriter.append((CharSequence) (str + "\r\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void writeBytesAsString(byte[] bArr, int i) {
        if (this.d != null) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            writeString(byteArrayToHexStr(bArr2));
        }
    }

    public static String byteArrayToHexStr(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] charArray = "0123456789ABCDEF".toCharArray();
        char[] cArr = new char[bArr.length << 1];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i << 1;
            cArr[i3] = charArray[i2 >>> 4];
            cArr[i3 + 1] = charArray[i2 & 15];
        }
        return new String(cArr);
    }

    public synchronized void close() {
        if (this.b != null) {
            try {
                this.b.flush();
                this.b.close();
                this.b = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (this.d != null) {
            try {
                this.d.close();
                this.d = null;
                this.c = null;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
