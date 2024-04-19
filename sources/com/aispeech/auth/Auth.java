package com.aispeech.auth;

import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class Auth {
    public static native boolean CheckApikey(String str, String str2);

    public static native int DecryptProfile(String str, byte[] bArr);

    static {
        try {
            Log.d("DUI-Auth", "before load ca library");
            System.loadLibrary("liteca");
            Log.d("DUI-Auth", "after load ca library");
        } catch (Exception e) {
            Log.e("DUI-Auth", "load libliteca.so failed, please check jniLibs folder");
            try {
                Log.d("DUI-Auth", "before2 load ca library");
                System.loadLibrary("ca");
                Log.d("DUI-Auth", "after2 load ca library");
            } catch (Exception e2) {
                e.printStackTrace();
                Log.e("DUI-Auth", "load libca.so failed, please check jniLibs folder");
            }
        }
    }
}
