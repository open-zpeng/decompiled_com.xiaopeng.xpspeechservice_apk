package com.aispeech.common;

import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
/* loaded from: classes.dex */
public class DeviceUtil {
    private static String[] a(long j) {
        String str;
        if (j < com.xiaopeng.lib.utils.FileUtils.SIZE_1KB) {
            str = "";
        } else {
            j /= com.xiaopeng.lib.utils.FileUtils.SIZE_1KB;
            if (j < com.xiaopeng.lib.utils.FileUtils.SIZE_1KB) {
                str = "KB";
            } else {
                j /= com.xiaopeng.lib.utils.FileUtils.SIZE_1KB;
                str = "MB";
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setGroupingSize(3);
        return new String[]{decimalFormat.format(j), str};
    }

    public static String getRAMInfo(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        String[] a = a(memoryInfo.availMem);
        String[] a2 = a(a());
        return "RAM " + a[0] + a[1] + URLUtils.URL_PATH_SEPERATOR + a2[0] + a2[1];
    }

    private static long a() {
        long j = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String readLine = bufferedReader.readLine();
            Log.i("DeviceUtil", readLine);
            j = Long.valueOf(readLine.split("\\s+")[1]).intValue() << 10;
            bufferedReader.close();
            return j;
        } catch (IOException e) {
            e.printStackTrace();
            return j;
        }
    }

    public static String getROMInfo() {
        StatFs statFs;
        long blockSize = new StatFs(Environment.getDataDirectory().getPath()).getBlockSize();
        String[] a = a(statFs.getBlockCount() * blockSize);
        String[] a2 = a(statFs.getAvailableBlocks() * blockSize);
        return "ROM " + a2[0] + a2[1] + URLUtils.URL_PATH_SEPERATOR + a[0] + a[1];
    }

    public static String getSDInfo() {
        StatFs statFs;
        if (Environment.getExternalStorageState().equals("mounted")) {
            long blockSize = new StatFs(Environment.getExternalStorageDirectory().getPath()).getBlockSize();
            String[] a = a(statFs.getBlockCount() * blockSize);
            String[] a2 = a(statFs.getAvailableBlocks() * blockSize);
            return "SD " + a2[0] + a2[1] + URLUtils.URL_PATH_SEPERATOR + a[0] + a[1];
        }
        return "SD CARD 已删除";
    }

    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null || TextUtils.isEmpty(telephonyManager.getDeviceId())) {
            telephonyManager = (TelephonyManager) context.getSystemService("phone1");
        }
        String str = null;
        if (telephonyManager != null) {
            str = telephonyManager.getDeviceId();
        }
        return TextUtils.isEmpty(str) ? "" : str.trim();
    }

    public static String getWlanMac(Context context) {
        String str;
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getMacAddress())) {
            str = connectionInfo.getMacAddress().trim();
        } else {
            str = null;
        }
        if (str != null) {
            return str.replace(":", "");
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getEthMac() {
        /*
            java.lang.String r0 = ""
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.io.IOException -> L34
            java.lang.String r2 = "cat /sys/class/net/eth0/address"
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.io.IOException -> L34
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.io.IOException -> L34
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.io.IOException -> L34
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.io.IOException -> L34
            r3.<init>(r1)     // Catch: java.io.IOException -> L34
            r2.<init>(r3)     // Catch: java.io.IOException -> L34
            java.lang.String r1 = r2.readLine()     // Catch: java.io.IOException -> L34
            java.lang.String r2 = "DeviceUtil"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L32
            java.lang.String r4 = "MAC : "
            r3.<init>(r4)     // Catch: java.io.IOException -> L32
            r3.append(r1)     // Catch: java.io.IOException -> L32
            java.lang.String r3 = r3.toString()     // Catch: java.io.IOException -> L32
            com.aispeech.common.Log.d(r2, r3)     // Catch: java.io.IOException -> L32
            goto L39
        L32:
            r2 = move-exception
            goto L36
        L34:
            r2 = move-exception
            r1 = r0
        L36:
            r2.printStackTrace()
        L39:
            if (r1 == 0) goto L41
            java.lang.String r2 = ":"
            java.lang.String r1 = r1.replace(r2, r0)
        L41:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.common.DeviceUtil.getEthMac():java.lang.String");
    }

    public static String getMacAddress(Context context) {
        String ethMac = getEthMac();
        if (TextUtils.isEmpty(ethMac)) {
            return getWlanMac(context);
        }
        return ethMac;
    }
}
