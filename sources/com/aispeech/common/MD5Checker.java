package com.aispeech.common;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes.dex */
public class MD5Checker {
    public static final int ID_ERROR = -1;
    public static final int ID_MD5_SAME = 0;
    private String a = getClass().getSimpleName();
    private volatile int b = 0;

    public int copyResource(Context context, String str, boolean z, String str2) {
        String str3;
        if (context == null) {
            return -1;
        }
        File file = new File(Util.getResourceDir(context), str);
        InputStream a = a(context, str);
        InputStream inputStream = null;
        if (a == null) {
            if (a != null) {
                try {
                    a.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return -1;
        }
        int i = 0;
        try {
            try {
                if (str2 == null) {
                    String str4 = this.a;
                    Log.i(str4, "there is no md5 file of : " + str);
                    if (z && a(a, file)) {
                        try {
                            a.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        return 0;
                    }
                    saveDestFile(context, a, str);
                    if (Util.isZipFile(c(context, str))) {
                        Util.unZip(context, file);
                    }
                    File file2 = new File(Util.getResourceDir(context), str);
                    if (z && a(a, file2)) {
                        this.b = 0;
                    } else {
                        i = a(context, str, z, str2, file2);
                    }
                    try {
                        a.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return i;
                }
                String str5 = this.a;
                Log.i(str5, "there is md5 file of : " + str);
                InputStream b = b(context, str2);
                if (b != null) {
                    byte[] bArr = new byte[b.available()];
                    b.read(bArr);
                    str3 = new String(bArr);
                } else {
                    str3 = "";
                }
                if (z && a(str3, file)) {
                    try {
                        a.close();
                        if (b != null) {
                            b.close();
                        }
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    return 0;
                }
                String str6 = this.a;
                Log.i(str6, "md5sum file in assets and data drectory bin md5 string is not same : " + str);
                saveDestFile(context, a, str);
                saveDestFile(context, b, str2);
                if (Util.isZipFile(c(context, str))) {
                    Util.unZip(context, file);
                }
                File file3 = new File(Util.getResourceDir(context), str);
                if (z && a(str3, file3)) {
                    this.b = 0;
                } else {
                    i = a(context, str, z, str2, file3);
                }
                try {
                    a.close();
                    if (b != null) {
                        b.close();
                    }
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return i;
            } catch (Throwable th) {
                try {
                    a.close();
                    if (0 != 0) {
                        inputStream.close();
                    }
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                throw th;
            }
        } catch (IOException e7) {
            e7.printStackTrace();
            try {
                a.close();
                if (0 != 0) {
                    inputStream.close();
                }
            } catch (IOException e8) {
                e8.printStackTrace();
            }
            return -1;
        }
    }

    private int a(Context context, String str, boolean z, String str2, File file) {
        if (this.b <= 0) {
            Log.w(this.a, "copy and md5 not the same,delete and retry time: " + this.b);
            if (file.exists()) {
                boolean delete = file.delete();
                Log.i(this.a, "copy file del: " + file.getName() + " , suc : " + delete);
            }
            this.b++;
            return copyResource(context, str, z, str2);
        }
        Log.e(this.a, "retry fail,please check your system or try reboot process");
        return -1;
    }

    public void deleteFile(Context context, String str) {
        String str2 = this.a;
        Log.i(str2, "=======deleteFile : " + str);
        File file = new File(Util.getResourceDir(context), str);
        if (file.exists()) {
            boolean delete = file.delete();
            String str3 = this.a;
            Log.i(str3, "copy file del: " + file.getName() + " , suc : " + delete);
        }
    }

    private InputStream a(Context context, String str) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(str);
            if (inputStream != null) {
                inputStream.read(new byte[1]);
                inputStream.reset();
            }
        } catch (IOException e) {
            e.printStackTrace();
            String str2 = this.a;
            Log.e(str2, "file " + str + " not found in assest folder, Did you forget add it? or file should be one of the suffix below to avoid be compressed in assets floder.“.jpg”, “.jpeg”, “.png”, “.gif”, “.wav”, “.mp2″, “.mp3″, “.ogg”, “.aac”, “.mpg”, “.mpeg”, “.mid”, “.midi”, “.smf”, “.jet”, “.rtttl”, “.imy”, “.xmf”, “.mp4″, “.m4a”, “.m4v”, “.3gp”, “.3gpp”, “.3g2″, “.3gpp2″, “.amr”, “.awb”, “.wma”, “.wmv”");
        }
        return inputStream;
    }

    private InputStream b(Context context, String str) {
        try {
            return context.getAssets().open(str);
        } catch (IOException e) {
            String str2 = this.a;
            Log.e(str2, "file " + str + " not found in assest floder, Did you forget add it?");
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v8, types: [boolean] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0062 -> B:43:0x0071). Please submit an issue!!! */
    private boolean a(String str, File file) {
        FileInputStream fileInputStream;
        if (file.exists()) {
            FileInputStream fileInputStream2 = null;
            try {
                try {
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                    }
                } catch (FileNotFoundException e) {
                    e = e;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                fileInputStream2 = fileInputStream2;
            }
            try {
                String byteArrayToHex = byteArrayToHex(a(fileInputStream));
                String str2 = this.a;
                Log.i(str2, "copy file md5 string = " + byteArrayToHex);
                ?? isEmpty = TextUtils.isEmpty(str);
                FileInputStream fileInputStream3 = isEmpty;
                if (isEmpty == 0) {
                    ?? isEmpty2 = TextUtils.isEmpty(byteArrayToHex);
                    fileInputStream3 = isEmpty2;
                    if (isEmpty2 == 0) {
                        fileInputStream3 = "\r|\n";
                        if (TextUtils.equals(str.replaceAll("\r|\n", ""), byteArrayToHex)) {
                            try {
                                fileInputStream.close();
                                return true;
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                return true;
                            }
                        }
                    }
                }
                fileInputStream.close();
                fileInputStream2 = fileInputStream3;
            } catch (FileNotFoundException e4) {
                e = e4;
                fileInputStream2 = fileInputStream;
                e.printStackTrace();
                fileInputStream2 = fileInputStream2;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                    fileInputStream2 = fileInputStream2;
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
            return false;
        }
        return false;
    }

    private static boolean a(InputStream inputStream, File file) {
        FileInputStream fileInputStream;
        boolean z = false;
        if (file.exists()) {
            FileInputStream fileInputStream2 = null;
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
            }
            try {
                byte[] a = a(fileInputStream);
                byte[] a2 = a(inputStream);
                int length = a.length > a2.length ? a2.length : a.length;
                try {
                    for (int i = 0; i < length; i++) {
                        if (a[i] == a2[i]) {
                        }
                        break;
                    }
                    break;
                    fileInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                z = true;
                return z;
            } catch (FileNotFoundException e3) {
                e = e3;
                fileInputStream2 = fileInputStream;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return false;
    }

    private static byte[] a(InputStream inputStream) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bArr = new byte[10240];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    messageDigest.update(bArr, 0, read);
                } else {
                    return messageDigest.digest();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return new byte[0];
        }
    }

    private static File c(Context context, String str) {
        File file = new File(context.getFilesDir() + File.separator + str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        return file;
    }

    public void saveDestFile(Context context, InputStream inputStream, String str) {
        FileOutputStream fileOutputStream;
        String str2 = this.a;
        Log.i(str2, "save to data : " + str);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    inputStream.reset();
                    fileOutputStream = new FileOutputStream(c(context, str));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e = e;
            }
            try {
                byte[] bArr = new byte[10240];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.close();
                        return;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public String byteArrayToHex(byte[] bArr) {
        if (bArr == null) {
            Log.w(this.a, "file byteArray is null");
            return "";
        }
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[bArr.length << 1];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b >>> 4) & 15];
            i = i2 + 1;
            cArr2[i2] = cArr[b & 15];
        }
        return new String(cArr2);
    }
}
