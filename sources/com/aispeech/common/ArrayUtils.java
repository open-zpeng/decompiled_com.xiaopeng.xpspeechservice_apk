package com.aispeech.common;
/* loaded from: classes.dex */
public class ArrayUtils {
    public static float[] string2Float(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        float[] fArr = new float[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            fArr[i] = Float.parseFloat(strArr[i]);
        }
        return fArr;
    }

    public static int[] string2Int(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            iArr[i] = Integer.parseInt(strArr[i]);
        }
        return iArr;
    }
}
