package com.aispeech.auth;

import android.content.Context;
import android.text.TextUtils;
import com.aispeech.common.AuthError;
import com.aispeech.common.AuthUtil;
import com.aispeech.common.FileUtils;
import com.aispeech.common.Log;
import com.aispeech.common.Util;
import com.aispeech.lite.AuthType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class d {
    private com.aispeech.auth.a.a a;
    private Context b;
    private String c;
    private String d;
    private volatile AuthType e;

    public d(Context context, com.aispeech.auth.a.a aVar) {
        this.b = context;
        this.a = aVar;
        this.e = aVar.r();
        if (!TextUtils.isEmpty(null)) {
            Log.d("AIProfile", "need to copy assets files to destPath");
            Log.d("AIProfile", ((String) null) + "   " + l());
            int copyFilesFromAssets = Util.copyFilesFromAssets(this.b, null, l());
            StringBuilder sb = new StringBuilder("copy ret is ");
            sb.append(copyFilesFromAssets);
            Log.d("AIProfile", sb.toString());
        }
        if (!FileUtils.isFileExists(this.a.h() + ".temp")) {
            return;
        }
        Log.d("AIProfile", "temp profile is exist, exception happened before, revert trial Profile");
        i();
    }

    public final String a() {
        return this.a.a();
    }

    public final String b() {
        return this.a.b();
    }

    public final String c() {
        return this.a.d();
    }

    public final String d() {
        return this.a.e();
    }

    public final String e() {
        return this.a.c();
    }

    private String l() {
        String str = ".profile";
        if (!TextUtils.isEmpty(this.a.h())) {
            File file = new File(this.a.h());
            if (this.a.r() == AuthType.TRIAL) {
                return this.a.h();
            }
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!FileUtils.isFileExists(this.a.h() + File.separator + AuthUtil.getDeviceId(this.a))) {
                if (FileUtils.isFileExists(this.a.h() + File.separator + "auth_profile.zip")) {
                    long currentTimeMillis = System.currentTimeMillis();
                    a(new File(this.a.h() + File.separator + "auth_profile.zip"), new File(this.a.h() + File.separator + AuthUtil.getDeviceId(this.a)), AuthUtil.getDeviceId(this.a));
                    String deviceId = AuthUtil.getDeviceId(this.a);
                    Log.d("AIProfile", "finished ，useTime ：" + (System.currentTimeMillis() - currentTimeMillis) + " ms");
                    str = deviceId;
                }
            } else {
                str = AuthUtil.getDeviceId(this.a);
                Log.d("AIProfile", "offline profile " + this.a.h() + File.separator + str);
            }
            return this.a.h() + File.separator + str;
        }
        return this.b.getFilesDir() + File.separator + ".profile";
    }

    public final String f() {
        return a((String) null).b() ? this.c : "";
    }

    public final String g() {
        return a((String) null).b() ? this.d : "";
    }

    public final f a(String str) {
        f fVar = new f(this.e);
        if (this.b == null) {
            fVar.a(AuthError.AUTH_ERR_MSG.ERR_SDK_NO_INIT);
            return fVar;
        }
        String c = c(l());
        if (TextUtils.isEmpty(c)) {
            Log.w("AIProfile", "profile content is null , mAuthType : " + this.e);
            if (this.e == AuthType.OFFLINE) {
                Log.e("AIProfile", "delete invalid offline profile and ret " + FileUtils.deleteFile(l()));
            }
            fVar.a(AuthError.AUTH_ERR_MSG.ERR_PROFILE_NO_EXIST);
            return fVar;
        }
        return a(fVar, c, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0093 A[Catch: JSONException -> 0x018e, TryCatch #0 {JSONException -> 0x018e, blocks: (B:3:0x0002, B:6:0x001c, B:8:0x0027, B:10:0x0030, B:11:0x0039, B:13:0x0040, B:15:0x0047, B:17:0x0069, B:23:0x0093, B:25:0x0099, B:27:0x009f, B:29:0x00a5, B:33:0x00ba, B:18:0x006f, B:20:0x0079, B:35:0x00c0, B:37:0x00c6, B:39:0x00d1, B:41:0x0116, B:47:0x0145, B:49:0x0150, B:51:0x0156, B:53:0x0161, B:55:0x016a, B:57:0x0175, B:42:0x012e, B:44:0x0135), top: B:62:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0099 A[Catch: JSONException -> 0x018e, TryCatch #0 {JSONException -> 0x018e, blocks: (B:3:0x0002, B:6:0x001c, B:8:0x0027, B:10:0x0030, B:11:0x0039, B:13:0x0040, B:15:0x0047, B:17:0x0069, B:23:0x0093, B:25:0x0099, B:27:0x009f, B:29:0x00a5, B:33:0x00ba, B:18:0x006f, B:20:0x0079, B:35:0x00c0, B:37:0x00c6, B:39:0x00d1, B:41:0x0116, B:47:0x0145, B:49:0x0150, B:51:0x0156, B:53:0x0161, B:55:0x016a, B:57:0x0175, B:42:0x012e, B:44:0x0135), top: B:62:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.aispeech.auth.f a(com.aispeech.auth.f r9, java.lang.String r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.auth.d.a(com.aispeech.auth.f, java.lang.String, java.lang.String):com.aispeech.auth.f");
    }

    public final boolean b(String str) {
        FileWriter fileWriter;
        File file = new File(l());
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        FileWriter fileWriter2 = null;
        try {
            try {
                fileWriter = new FileWriter(file);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            fileWriter.write(str);
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            FileUtils.deleteFile(l() + ".temp");
            return true;
        } catch (IOException e4) {
            e = e4;
            fileWriter2 = fileWriter;
            e.printStackTrace();
            if (fileWriter2 != null) {
                try {
                    fileWriter2.flush();
                    fileWriter2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileWriter2 = fileWriter;
            if (fileWriter2 != null) {
                try {
                    fileWriter2.flush();
                    fileWriter2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    public final String h() {
        return AuthUtil.getDeviceId(this.a);
    }

    private static boolean a(JSONObject jSONObject) {
        if (jSONObject.isNull("expire")) {
            return false;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(jSONObject.getString("expire")).getTime() < System.currentTimeMillis();
        } catch (ParseException | JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean b(JSONObject jSONObject) {
        if (jSONObject.isNull("bindApiKey")) {
            return true;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("bindApiKey");
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (this.a.d().equals(jSONArray.getString(i))) {
                    return true;
                }
            }
            return false;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String c(java.lang.String r4) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            boolean r4 = r0.exists()
            r1 = 0
            if (r4 != 0) goto Ld
            return r1
        Ld:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
        L1d:
            java.lang.String r0 = r2.readLine()     // Catch: java.io.IOException -> L67 java.lang.Throwable -> L7d
            if (r0 == 0) goto L27
            r4.append(r0)     // Catch: java.io.IOException -> L67 java.lang.Throwable -> L7d
            goto L1d
        L27:
            r2.close()     // Catch: java.io.IOException -> L2b
            goto L30
        L2b:
            r0 = move-exception
            r0.printStackTrace()
        L30:
            java.lang.String r0 = r4.toString()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L3b
            return r1
        L3b:
            r0 = 2048(0x800, float:2.87E-42)
            byte[] r0 = new byte[r0]
            java.lang.String r4 = r4.toString()
            int r4 = com.aispeech.auth.Auth.DecryptProfile(r4, r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "DecryptProfile: "
            r2.<init>(r3)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "AIProfile"
            com.aispeech.common.Log.d(r3, r2)
            java.lang.String r2 = new java.lang.String
            r2.<init>(r0)
            java.lang.String r0 = r2.trim()
            if (r4 == 0) goto L66
            return r1
        L66:
            return r0
        L67:
            r4 = move-exception
            goto L6d
        L69:
            r4 = move-exception
            goto L7f
        L6b:
            r4 = move-exception
            r2 = r1
        L6d:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L7d
            if (r2 == 0) goto L7b
            r2.close()     // Catch: java.io.IOException -> L76
            goto L7b
        L76:
            r4 = move-exception
            r4.printStackTrace()
            goto L7c
        L7b:
        L7c:
            return r1
        L7d:
            r4 = move-exception
            r1 = r2
        L7f:
            if (r1 == 0) goto L8a
            r1.close()     // Catch: java.io.IOException -> L85
            goto L8a
        L85:
            r0 = move-exception
            r0.printStackTrace()
            goto L8b
        L8a:
        L8b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.auth.d.c(java.lang.String):java.lang.String");
    }

    public final void i() {
        Log.d("AIProfile", "reset trail profile before");
        String l = l();
        Log.d("AIProfile", "trail profile is: " + l);
        if (FileUtils.isFile(l)) {
            boolean deleteFile = FileUtils.deleteFile(l);
            Log.d("AIProfile", "trail profile is exist, delete first and deleteOK = " + deleteFile);
        }
        boolean moveFile = FileUtils.moveFile(l + ".temp", l);
        StringBuilder sb = new StringBuilder("reset trail profile end and isOk = ");
        sb.append(moveFile);
        Log.d("AIProfile", sb.toString());
    }

    public final void j() {
        Log.d("AIProfile", "copy trial profile before");
        String l = l();
        if (FileUtils.isFile(l + ".temp")) {
            boolean deleteFile = FileUtils.deleteFile(l + ".temp");
            StringBuilder sb = new StringBuilder("temp trial profile is exist, delete first and deleteOK = ");
            sb.append(deleteFile);
            Log.d("AIProfile", sb.toString());
        }
        Log.d("AIProfile", "trial profile is: " + l);
        boolean moveFile = FileUtils.moveFile(l, l + ".temp");
        StringBuilder sb2 = new StringBuilder("copy trail profile end and isOk = ");
        sb2.append(moveFile);
        Log.d("AIProfile", sb2.toString());
    }

    public final boolean k() {
        return FileUtils.deleteFile(l());
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0041, code lost:
        if (r7.exists() != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
        r7.createNewFile();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
        r6 = r1.getInputStream(r0);
        r8 = new java.io.FileOutputStream(r7);
        r7 = new byte[1024];
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0053, code lost:
        r0 = r6.read(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0058, code lost:
        if (r0 == (-1)) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005a, code lost:
        r8.write(r7, 0, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005f, code lost:
        r8.close();
        r6.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(java.io.File r6, java.io.File r7, java.lang.String r8) {
        /*
            boolean r0 = r6.exists()
            if (r0 != 0) goto L7
            return
        L7:
            r0 = 0
            java.util.zip.ZipFile r1 = new java.util.zip.ZipFile     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L78
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L78
            java.util.Enumeration r6 = r1.entries()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
        L11:
            boolean r0 = r6.hasMoreElements()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            if (r0 == 0) goto L67
            java.lang.Object r0 = r6.nextElement()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            java.util.zip.ZipEntry r0 = (java.util.zip.ZipEntry) r0     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            java.lang.String r2 = r0.getName()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            boolean r3 = r0.isDirectory()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            if (r3 != 0) goto L11
            boolean r3 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            if (r3 != 0) goto L66
            boolean r2 = r8.equals(r2)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            if (r2 == 0) goto L66
            long r2 = r0.getSize()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L66
            boolean r6 = r7.exists()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            if (r6 != 0) goto L46
            r7.createNewFile()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
        L46:
            java.io.InputStream r6 = r1.getInputStream(r0)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r8.<init>(r7)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
        L53:
            int r0 = r6.read(r7)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r2 = -1
            if (r0 == r2) goto L5f
            r2 = 0
            r8.write(r7, r2, r0)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            goto L53
        L5f:
            r8.close()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r6.close()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            goto L67
        L66:
            goto L11
        L67:
            r1.close()     // Catch: java.io.IOException -> L6b
            return
        L6b:
            r6 = move-exception
            r6.printStackTrace()
            return
        L70:
            r6 = move-exception
            goto L82
        L72:
            r6 = move-exception
            r0 = r1
            goto L79
        L75:
            r6 = move-exception
            r1 = r0
            goto L82
        L78:
            r6 = move-exception
        L79:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto L81
            r0.close()     // Catch: java.io.IOException -> L6b
        L81:
            return
        L82:
            if (r1 == 0) goto L8d
            r1.close()     // Catch: java.io.IOException -> L88
            goto L8d
        L88:
            r7 = move-exception
            r7.printStackTrace()
            goto L8e
        L8d:
        L8e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.auth.d.a(java.io.File, java.io.File, java.lang.String):void");
    }

    public final String toString() {
        return "AIProfile{mConfig=" + this.a + ", mAuthType=" + this.e + ", mDeviceName='" + this.c + "', mDeviceSecret='" + this.d + "'}";
    }
}
