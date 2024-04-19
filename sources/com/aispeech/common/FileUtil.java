package com.aispeech.common;

import android.content.Context;
import com.aispeech.lite.d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public class FileUtil {
    public static final String TAG = "FileUtil";
    private File a = null;
    private FileOutputStream b = null;

    public FileUtil(Context context) {
    }

    public synchronized void createFile(String str) {
        if (!d.e) {
            Log.w(TAG, "createFile cancel,global audio save is not enable");
            return;
        }
        Log.d(TAG, "createFile() called with:realPath = " + str);
        this.a = new File(str);
        File parentFile = this.a.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (!this.a.exists()) {
            try {
                this.a.createNewFile();
                this.b = new FileOutputStream(this.a, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005e A[Catch: IOException -> 0x0032, TRY_ENTER, TryCatch #7 {IOException -> 0x0032, blocks: (B:7:0x0022, B:9:0x002e, B:34:0x005e, B:36:0x0063, B:38:0x0068, B:40:0x006d), top: B:60:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0063 A[Catch: IOException -> 0x0032, TryCatch #7 {IOException -> 0x0032, blocks: (B:7:0x0022, B:9:0x002e, B:34:0x005e, B:36:0x0063, B:38:0x0068, B:40:0x006d), top: B:60:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0068 A[Catch: IOException -> 0x0032, TryCatch #7 {IOException -> 0x0032, blocks: (B:7:0x0022, B:9:0x002e, B:34:0x005e, B:36:0x0063, B:38:0x0068, B:40:0x006d), top: B:60:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006d A[Catch: IOException -> 0x0032, TRY_LEAVE, TryCatch #7 {IOException -> 0x0032, blocks: (B:7:0x0022, B:9:0x002e, B:34:0x005e, B:36:0x0063, B:38:0x0068, B:40:0x006d), top: B:60:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0080 A[Catch: IOException -> 0x007c, TryCatch #9 {IOException -> 0x007c, blocks: (B:45:0x0078, B:49:0x0080, B:51:0x0085, B:53:0x008a), top: B:61:0x0078 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0085 A[Catch: IOException -> 0x007c, TryCatch #9 {IOException -> 0x007c, blocks: (B:45:0x0078, B:49:0x0080, B:51:0x0085, B:53:0x008a), top: B:61:0x0078 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x008a A[Catch: IOException -> 0x007c, TRY_LEAVE, TryCatch #9 {IOException -> 0x007c, blocks: (B:45:0x0078, B:49:0x0080, B:51:0x0085, B:53:0x008a), top: B:61:0x0078 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void copyFile(java.io.File r9, java.io.File r10) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L55
            r1.<init>(r9)     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L55
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4b
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4b
            java.nio.channels.FileChannel r10 = r1.getChannel()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            java.nio.channels.FileChannel r0 = r9.getChannel()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
            r3 = 0
            long r5 = r10.size()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
            r2 = r10
            r7 = r0
            r2.transferTo(r3, r5, r7)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
            r1.close()     // Catch: java.io.IOException -> L32
            r10.close()     // Catch: java.io.IOException -> L32
            r9.close()     // Catch: java.io.IOException -> L32
            if (r0 == 0) goto L31
            r0.close()     // Catch: java.io.IOException -> L32
        L31:
            return
        L32:
            r9 = move-exception
            r9.printStackTrace()
            return
        L37:
            r2 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
            goto L76
        L3c:
            r2 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L59
        L41:
            r2 = move-exception
            r10 = r0
            goto L76
        L44:
            r2 = move-exception
            r10 = r0
            goto L4e
        L47:
            r2 = move-exception
            r9 = r0
            r10 = r9
            goto L76
        L4b:
            r2 = move-exception
            r9 = r0
            r10 = r9
        L4e:
            r0 = r1
            goto L58
        L50:
            r2 = move-exception
            r9 = r0
            r10 = r9
            r1 = r10
            goto L76
        L55:
            r2 = move-exception
            r9 = r0
            r10 = r9
        L58:
            r1 = r10
        L59:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L61
            r0.close()     // Catch: java.io.IOException -> L32
        L61:
            if (r10 == 0) goto L66
            r10.close()     // Catch: java.io.IOException -> L32
        L66:
            if (r9 == 0) goto L6b
            r9.close()     // Catch: java.io.IOException -> L32
        L6b:
            if (r1 == 0) goto L70
            r1.close()     // Catch: java.io.IOException -> L32
        L70:
            return
        L71:
            r2 = move-exception
            r8 = r0
            r0 = r10
            r10 = r1
            r1 = r8
        L76:
            if (r1 == 0) goto L7e
            r1.close()     // Catch: java.io.IOException -> L7c
            goto L7e
        L7c:
            r9 = move-exception
            goto L8e
        L7e:
            if (r0 == 0) goto L83
            r0.close()     // Catch: java.io.IOException -> L7c
        L83:
            if (r9 == 0) goto L88
            r9.close()     // Catch: java.io.IOException -> L7c
        L88:
            if (r10 == 0) goto L92
            r10.close()     // Catch: java.io.IOException -> L7c
            goto L92
        L8e:
            r9.printStackTrace()
            goto L93
        L92:
        L93:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.common.FileUtil.copyFile(java.io.File, java.io.File):void");
    }

    public synchronized void write(byte[] bArr) {
        try {
            if (this.b != null) {
                this.b.write(bArr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void closeFile() {
        if (this.b != null) {
            try {
                this.b.flush();
                this.b.close();
                this.b = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFilesFassets(Context context, String str, String str2) throws IOException {
        String[] list = context.getAssets().list(str);
        if (list.length > 0) {
            new File(str2).mkdirs();
            for (String str3 : list) {
                copyFilesFassets(context, str + URLUtils.URL_PATH_SEPERATOR + str3, str2 + URLUtils.URL_PATH_SEPERATOR + str3);
            }
            return;
        }
        InputStream open = context.getAssets().open(str);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
        byte[] bArr = new byte[1024];
        while (true) {
            int read = open.read(bArr);
            if (read != -1) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                open.close();
                fileOutputStream.close();
                return;
            }
        }
    }

    public synchronized void createFileDir(String str) {
        this.a = new File(str);
        if (!this.a.exists()) {
            this.a.mkdirs();
        }
        this.a = null;
    }
}
