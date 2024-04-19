package com.aispeech.common;

import java.nio.ByteOrder;
/* loaded from: classes.dex */
public class ByteConvertUtil {
    public static short getShort(byte[] bArr) {
        boolean testCPU = testCPU();
        if (bArr == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (bArr.length > 2) {
            throw new IllegalArgumentException("byte array size > 2 !");
        }
        int i = 0;
        if (testCPU) {
            short s = 0;
            while (i < bArr.length) {
                s = (short) (((short) (s << 8)) | (bArr[i] & 255));
                i++;
            }
            return s;
        }
        for (int length = bArr.length - 1; length >= 0; length--) {
            i = (short) (((short) (i << 8)) | (bArr[length] & 255));
        }
        return i == 1 ? (short) 1 : (short) 0;
    }

    public static boolean testCPU() {
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            return true;
        }
        return false;
    }

    public static short[] Bytes2Shorts(byte[] bArr, int i) {
        short[] sArr = new short[i / 2];
        for (int i2 = 0; i2 < sArr.length; i2++) {
            byte[] bArr2 = new byte[2];
            for (int i3 = 0; i3 < 2; i3++) {
                bArr2[i3] = bArr[(i2 << 1) + i3];
            }
            sArr[i2] = getShort(bArr2);
        }
        return sArr;
    }

    public static byte[] Shorts2Bytes(short[] sArr) {
        byte[] bArr = new byte[sArr.length << 1];
        for (int i = 0; i < sArr.length; i++) {
            short s = sArr[i];
            byte[] bArr2 = new byte[4];
            if (testCPU()) {
                int i2 = 3;
                int i3 = s;
                while (i2 >= 0) {
                    bArr2[i2] = (byte) i3;
                    i2--;
                    i3 >>= 8;
                }
            } else {
                int i4 = s;
                for (int i5 = 0; i5 < 4; i5++) {
                    bArr2[i5] = (byte) i4;
                    i4 >>= 8;
                }
            }
            for (int i6 = 0; i6 < 2; i6++) {
                bArr[(i << 1) + i6] = bArr2[i6];
            }
        }
        return bArr;
    }
}
