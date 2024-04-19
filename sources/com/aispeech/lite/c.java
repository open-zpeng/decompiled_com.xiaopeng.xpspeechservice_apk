package com.aispeech.lite;

import android.content.Context;
import android.text.TextUtils;
import com.aispeech.common.Log;
import com.aispeech.common.URLUtils;
/* loaded from: classes.dex */
public final class c {
    public static boolean a;
    public static String m;
    public static String n;
    public static String o;
    public static String p;
    public static AuthType q;
    private static Context v;
    private static com.aispeech.auth.d w;
    public static String b = null;
    public static int c = 2;
    public static int d = 1;
    public static int e = 1;
    private static String x = null;
    public static boolean f = false;
    public static int g = 200;
    public static int h = 100;
    public static int i = 1;
    public static int j = 0;
    public static int k = 5000;
    public static String l = "https://auth.dui.ai";
    public static boolean r = false;
    public static String s = "https://log.aispeech.com";
    public static String t = null;
    public static int u = 0;

    public static com.aispeech.auth.d a() {
        com.aispeech.auth.d dVar = w;
        if (dVar == null) {
            throw new IllegalStateException("please init sdk first!");
        }
        return dVar;
    }

    public static void a(com.aispeech.auth.d dVar) {
        w = dVar;
    }

    public static int a(Context context) {
        v = context.getApplicationContext();
        return 0;
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            d.b = true;
            Log.LOGFILE_DEBUGABLE = true;
        }
        Log.LOGCAT_DEBUGABLE = true;
        d.a = true;
        Log.init(3, str);
    }

    public static Context b() {
        return v;
    }

    public static String c() {
        if (!TextUtils.isEmpty(d.d)) {
            String str = d.d;
            x = str;
            if (!str.endsWith(URLUtils.URL_PATH_SEPERATOR)) {
                x += URLUtils.URL_PATH_SEPERATOR;
            }
            x += "echo";
        }
        return x;
    }

    public static void b(String str) {
        x = str;
    }
}
