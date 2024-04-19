package com.aispeech.c.a;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.aispeech.common.Log;
import com.aispeech.lite.c;
/* loaded from: classes.dex */
public final class a {
    private static final Object a = new Object();

    public static String a(String str) {
        synchronized (a) {
            String string = c.b().getSharedPreferences("dnsCache", 0).getString(str, null);
            if (string != null && string.contains("###")) {
                String[] split = string.split("###");
                long currentTimeMillis = System.currentTimeMillis() - Long.parseLong(split[1]);
                if (currentTimeMillis >= 0 && currentTimeMillis <= 600000) {
                    String str2 = split[0];
                    if (!TextUtils.isEmpty(str2)) {
                        Log.d("DDSDnsClientManager", "hostName : " + str + " query cache ip : " + str2);
                        return str2;
                    }
                } else {
                    Log.d("DDSDnsClientManager", "hostName : " + str + " is expird, remove it ");
                    c.b().getSharedPreferences("dnsCache", 0).edit().putString(str, null).apply();
                }
            } else {
                Log.d("DDSDnsClientManager", "no cache  : " + str);
            }
            Log.d("DDSDnsClientManager", "dds_get_host_by_name start : " + str);
            String a2 = com.aispeech.auth.b.a(str);
            Log.d("DDSDnsClientManager", "dds_get_host_by_name return  : " + a2);
            if (!TextUtils.isEmpty(a2)) {
                Log.d("DDSDnsClientManager", "hostName : " + str + " ip : " + a2 + " store it to cache");
                long currentTimeMillis2 = System.currentTimeMillis();
                SharedPreferences.Editor edit = c.b().getSharedPreferences("dnsCache", 0).edit();
                edit.putString(str, a2 + "###" + currentTimeMillis2).apply();
            }
            return a2;
        }
    }
}
