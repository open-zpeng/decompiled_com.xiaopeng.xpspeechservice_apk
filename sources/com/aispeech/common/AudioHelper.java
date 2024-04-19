package com.aispeech.common;

import java.lang.reflect.Array;
import java.util.Arrays;
/* loaded from: classes.dex */
public class AudioHelper {
    public static byte[] mixRawAudioBytes(byte[][] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        byte[] bArr2 = bArr[0];
        if (bArr.length == 1) {
            return bArr2;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i].length != bArr2.length) {
                Log.e("app", "column of the road of audio + " + i + " is diffrent.");
                return null;
            }
        }
        int length = bArr.length;
        int length2 = bArr2.length / 2;
        short[][] sArr = (short[][]) Array.newInstance(short.class, length, length2);
        for (int i2 = 0; i2 < length; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                int i4 = i3 << 1;
                sArr[i2][i3] = (short) ((bArr[i2][i4] & 255) | ((bArr[i2][i4 + 1] & 255) << 8));
            }
        }
        short[] sArr2 = new short[length2];
        for (int i5 = 0; i5 < length2; i5++) {
            int i6 = 0;
            for (int i7 = 0; i7 < length; i7++) {
                i6 += sArr[i7][i5];
            }
            sArr2[i5] = (short) i6;
        }
        for (int i8 = 0; i8 < length2; i8++) {
            int i9 = i8 << 1;
            bArr2[i9] = (byte) sArr2[i8];
            bArr2[i9 + 1] = (byte) (sArr2[i8] >> 8);
        }
        return bArr2;
    }

    public static byte[] splitOriginalChannel(byte[] bArr, int i, int i2) {
        if ((i2 == 2 || i2 == 4 || i2 == 6) && bArr != null && i >= 0 && i <= 5 && bArr.length > 0) {
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            byte[] bArr2 = new byte[copyOf.length / i2];
            int i3 = 0;
            int i4 = 0;
            while (i3 < copyOf.length) {
                int i5 = i4 + 1;
                int i6 = (i * 2) + i3;
                bArr2[i4] = copyOf[i6];
                i4 = i5 + 1;
                bArr2[i5] = copyOf[i6 + 1];
                i3 += i2 << 1;
            }
            return bArr2;
        }
        return null;
    }

    public static byte[] mixMultChannel(int i, byte[]... bArr) {
        int i2;
        byte[] bArr2 = new byte[bArr[0].length * i];
        int i3 = 0;
        while (i3 < bArr2.length) {
            int i4 = 0;
            while (true) {
                i2 = i << 1;
                if (i4 < i2) {
                    int i5 = i4 / 2;
                    int i6 = i3 / i;
                    bArr2[i3 + i4] = bArr[i5][i6];
                    bArr2[i3 + 1 + i4] = bArr[i5][i6 + 1];
                    i4 += 2;
                }
            }
            i3 += i2;
        }
        return bArr2;
    }

    public static byte[] rearrangeAudioData(byte[] bArr) {
        return mixMultChannel(6, splitOriginalChannel(bArr, 0, 6), splitOriginalChannel(bArr, 1, 6), splitOriginalChannel(bArr, 4, 6), splitOriginalChannel(bArr, 5, 6), splitOriginalChannel(bArr, 2, 6), splitOriginalChannel(bArr, 3, 6));
    }
}
