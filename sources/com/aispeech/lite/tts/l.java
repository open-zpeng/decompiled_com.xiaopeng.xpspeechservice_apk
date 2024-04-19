package com.aispeech.lite.tts;

import android.text.TextUtils;
import com.aispeech.common.FileUtils;
import com.aispeech.common.Log;
import com.aispeech.common.URLUtils;
import com.aispeech.common.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.collections4.map.LRUMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class l {
    private static l a = null;
    private static l b = null;
    private String d;
    private File f;
    private LRUMap<String, String> c = new LRUMap<>(20);
    private boolean e = true;

    private l() {
        this.f = null;
        if (this.f != null) {
            return;
        }
        this.f = new File(TextUtils.isEmpty(com.aispeech.lite.c.t) ? com.aispeech.lite.c.b().getExternalCacheDir() : new File(com.aispeech.lite.c.t), "ttsCache");
        if (!this.f.exists() || !this.f.isDirectory()) {
            this.f.mkdirs();
        }
        Log.d("TTSCache", "cache dir is " + this.f.getAbsolutePath());
    }

    public static l a() {
        if (a == null) {
            synchronized (l.class) {
                if (a == null) {
                    l lVar = new l();
                    a = lVar;
                    lVar.d = "TTSCache_local";
                }
            }
        }
        return a;
    }

    public static l b() {
        if (b == null) {
            synchronized (l.class) {
                if (b == null) {
                    l lVar = new l();
                    b = lVar;
                    lVar.d = "TTSCache_cloud";
                }
            }
        }
        return b;
    }

    public final boolean a(com.aispeech.lite.h.n nVar, File file) {
        String str;
        if (this.e && nVar != null && file.exists() && file.isFile()) {
            String md5 = Util.md5(nVar.toString());
            if (this.c.containsKey(md5)) {
                String str2 = this.c.get(md5);
                if (!TextUtils.isEmpty(str2)) {
                    File file2 = new File(str2);
                    if (file2.exists() && file2.isFile() && file2.getParentFile().equals(this.f)) {
                        file2.delete();
                        Log.d("TTSCache", "delete cache file " + file2.getAbsolutePath());
                    }
                }
            } else if (this.c.isFull()) {
                LRUMap<String, String> lRUMap = this.c;
                File file3 = new File(lRUMap.remove(lRUMap.lastKey()));
                if (file3.exists() && file3.isFile() && file3.getParentFile().equals(this.f)) {
                    file3.delete();
                    Log.d("TTSCache", "delete cache file " + file3.getAbsolutePath());
                }
            }
            if (file.getParentFile().equals(this.f)) {
                str = file.getAbsolutePath();
            } else {
                String name = file.getName();
                String str3 = this.f.getAbsolutePath() + File.separator + System.currentTimeMillis() + name.substring(name.lastIndexOf(URLUtils.URL_DOMAIN_SEPERATOR));
                FileUtils.copyFile(file.getAbsolutePath(), str3);
                str = str3;
            }
            Log.d("TTSCache", "add cache file " + str);
            this.c.put(md5, str);
            return true;
        }
        return false;
    }

    public final File a(com.aispeech.lite.h.n nVar) {
        if (this.e && nVar != null) {
            String md5 = Util.md5(nVar.toString());
            String str = this.c.get(md5);
            if (TextUtils.isEmpty(str)) {
                this.c.remove(md5);
                return null;
            }
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                return file;
            }
            return null;
        }
        return null;
    }

    public final synchronized File c() {
        File file;
        if (this.e) {
            File file2 = this.f;
            file = new File(file2, System.currentTimeMillis() + ".wav");
        } else {
            file = null;
        }
        return file;
    }

    public final synchronized File d() {
        File file;
        if (this.e) {
            File file2 = this.f;
            file = new File(file2, System.currentTimeMillis() + ".mp3");
        } else {
            file = null;
        }
        return file;
    }

    public final void e() {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    if (this.c != null && !this.c.isEmpty()) {
                        HashMap hashMap = new HashMap(this.c);
                        JSONObject jSONObject = new JSONObject();
                        for (String str : hashMap.keySet()) {
                            try {
                                jSONObject.put(str, hashMap.get(str));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        String jSONObject2 = jSONObject.toString();
                        FileOutputStream fileOutputStream2 = new FileOutputStream(new File(this.f, this.d));
                        try {
                            fileOutputStream2.write(jSONObject2.getBytes());
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                        } catch (Exception e2) {
                            e = e2;
                            fileOutputStream = fileOutputStream2;
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(boolean r6) {
        /*
            r5 = this;
            r5.e = r6
            if (r6 == 0) goto La0
        L5:
            java.io.File r6 = new java.io.File
            java.io.File r0 = r5.f
            java.lang.String r1 = r5.d
            r6.<init>(r0, r1)
            boolean r0 = r6.exists()
            if (r0 == 0) goto L9f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42 java.io.FileNotFoundException -> L4c
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42 java.io.FileNotFoundException -> L4c
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42 java.io.FileNotFoundException -> L4c
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42 java.io.FileNotFoundException -> L4c
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42 java.io.FileNotFoundException -> L4c
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42 java.io.FileNotFoundException -> L4c
        L29:
            java.lang.String r6 = r2.readLine()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L3a java.io.FileNotFoundException -> L3d
            if (r6 == 0) goto L33
            r0.append(r6)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L3a java.io.FileNotFoundException -> L3d
            goto L29
        L33:
            r2.close()     // Catch: java.io.IOException -> L56
            goto L55
        L37:
            r6 = move-exception
            r1 = r2
            goto L94
        L3a:
            r6 = move-exception
            r1 = r2
            goto L43
        L3d:
            r6 = move-exception
            r1 = r2
            goto L4d
        L40:
            r6 = move-exception
            goto L94
        L42:
            r6 = move-exception
        L43:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L5b
            r1.close()     // Catch: java.io.IOException -> L56
            goto L55
        L4c:
            r6 = move-exception
        L4d:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L5b
            r1.close()     // Catch: java.io.IOException -> L56
        L55:
            goto L5b
        L56:
            r6 = move-exception
            r6.printStackTrace()
            goto L55
        L5b:
            java.lang.String r6 = r0.toString()
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 != 0) goto L9f
            org.apache.commons.collections4.map.LRUMap<java.lang.String, java.lang.String> r0 = r5.c
            r0.clear()
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L8a org.json.JSONException -> L8f
            r0.<init>(r6)     // Catch: java.lang.Exception -> L8a org.json.JSONException -> L8f
            java.util.Iterator r6 = r0.keys()     // Catch: java.lang.Exception -> L8a org.json.JSONException -> L8f
        L73:
            boolean r1 = r6.hasNext()     // Catch: java.lang.Exception -> L8a org.json.JSONException -> L8f
            if (r1 == 0) goto L89
            java.lang.Object r1 = r6.next()     // Catch: java.lang.Exception -> L8a org.json.JSONException -> L8f
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L8a org.json.JSONException -> L8f
            java.lang.String r2 = r0.getString(r1)     // Catch: java.lang.Exception -> L8a org.json.JSONException -> L8f
            org.apache.commons.collections4.map.LRUMap<java.lang.String, java.lang.String> r3 = r5.c     // Catch: java.lang.Exception -> L8a org.json.JSONException -> L8f
            r3.put(r1, r2)     // Catch: java.lang.Exception -> L8a org.json.JSONException -> L8f
            goto L73
        L89:
            return
        L8a:
            r6 = move-exception
            r6.printStackTrace()
            goto L9f
        L8f:
            r6 = move-exception
            r6.printStackTrace()
            return
        L94:
            if (r1 == 0) goto L9e
            r1.close()     // Catch: java.io.IOException -> L9a
            goto L9e
        L9a:
            r0 = move-exception
            r0.printStackTrace()
        L9e:
            throw r6
        L9f:
            return
        La0:
            org.apache.commons.collections4.map.LRUMap<java.lang.String, java.lang.String> r6 = r5.c
            r6.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.lite.tts.l.a(boolean):void");
    }
}
