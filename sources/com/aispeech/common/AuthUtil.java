package com.aispeech.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.aispeech.auth.a.a;
import com.aispeech.lite.AuthType;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;
/* loaded from: classes.dex */
public class AuthUtil {
    public static final String TAG = "AuthUtil";

    public static String appendUrl(String str, Map<String, Object> map) {
        if (map != null && map.size() > 0) {
            if (!str.contains("?")) {
                str = str + "?";
            }
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(value);
                sb.append("&");
            }
            String sb2 = sb.toString();
            if (sb2.endsWith("&")) {
                sb2 = sb2.substring(0, sb2.length() - 1);
            }
            str = str + sb2;
        }
        Log.d(TAG, "appendUrl is " + str);
        return str;
    }

    public static String getSecretCode(Context context) {
        return context.getPackageName() + ":" + getKeyHash(context);
    }

    public static String getKeyHash(Context context) {
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            if (signatureArr.length > 0) {
                Signature signature = signatureArr[0];
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(signature.toByteArray());
                return byte2HexFormatted(messageDigest.digest());
            }
            return "";
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        } catch (NoSuchAlgorithmException e2) {
            return "";
        }
    }

    public static String byte2HexFormatted(byte[] bArr) {
        return byte2HexFormatted(bArr, true);
    }

    public static String byte2HexFormatted(byte[] bArr, boolean z) {
        StringBuilder sb = new StringBuilder(bArr.length << 1);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (z && i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static String getApplicationName(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
            }
        } catch (PackageManager.NameNotFoundException e2) {
            packageManager = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    public static String getDisplayMatrix(Context context) {
        int[] screenResolution = getScreenResolution(context);
        if (screenResolution[0] > screenResolution[1]) {
            return screenResolution[1] + Marker.ANY_MARKER + screenResolution[0];
        }
        return screenResolution[0] + Marker.ANY_MARKER + screenResolution[1];
    }

    public static int[] getScreenResolution(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0014, code lost:
        if (android.text.TextUtils.isEmpty(r0.getDeviceId()) != false) goto L3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getImei(android.content.Context r3) {
        /*
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r3.getSystemService(r0)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            r1 = 0
            if (r0 == 0) goto L16
            java.lang.String r2 = r0.getDeviceId()     // Catch: java.lang.SecurityException -> L27
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.SecurityException -> L27
            if (r2 == 0) goto L1f
        L16:
            java.lang.String r0 = "phone1"
            java.lang.Object r3 = r3.getSystemService(r0)     // Catch: java.lang.SecurityException -> L27
            r0 = r3
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch: java.lang.SecurityException -> L27
        L1f:
            if (r0 == 0) goto L26
            java.lang.String r3 = r0.getDeviceId()     // Catch: java.lang.SecurityException -> L27
            r1 = r3
        L26:
            goto L2b
        L27:
            r3 = move-exception
            r3.printStackTrace()
        L2b:
            if (r1 == 0) goto L32
            java.lang.String r3 = r1.trim()
            return r3
        L32:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.common.AuthUtil.getImei(android.content.Context):java.lang.String");
    }

    public static String getDeviceId(a aVar) {
        return aVar.r() == AuthType.OFFLINE ? aVar.g() : aVar.p();
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDeviceData(Context context, a aVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("applicationLabel", getApplicationName(context));
            jSONObject.put("applicationVersion", getVersionName(context));
            jSONObject.put("buildDevice", Build.DEVICE);
            jSONObject.put("buildManufacture", Build.MANUFACTURER);
            jSONObject.put("buildModel", Build.MODEL);
            jSONObject.put("buildSdkInt", String.valueOf(Build.VERSION.SDK_INT));
            jSONObject.put("displayMatrix", getDisplayMatrix(context));
            jSONObject.put("packageName", context.getPackageName());
            jSONObject.put("deviceId", getDeviceId(aVar));
            jSONObject.put("platform", "android");
            jSONObject.put("buildVariant", getBuildVariant(context));
            jSONObject.put("imei", getImei(context));
            jSONObject.put("mac", DeviceUtil.getMacAddress(context));
            jSONObject.put("androidId", Settings.Secure.getString(context.getContentResolver(), "android_id"));
            if (aVar.q() != null && aVar.q().size() > 0) {
                for (Map.Entry<String, Object> entry : aVar.q().entrySet()) {
                    Log.d(TAG, "custom key and value " + entry.getKey() + "\t" + entry.getValue());
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static JSONObject getDeviceInfo(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("buildModel", Build.MODEL);
            jSONObject.put("buildManufacture", Build.MANUFACTURER);
            jSONObject.put("buildDevice", Build.DEVICE);
            jSONObject.put("buildSdkInt", String.valueOf(Build.VERSION.SDK_INT));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static void logAuthFailureInfo(String str, String str2, Context context) {
        Log.e("DUI-Auth", "\n==================================================\n====================授权失败=======================\n============apk 版本 -> " + getBuildVariant(context) + "\n============apk SHA256-> " + getKeyHash(context) + "\n============apk packageName -> " + context.getPackageName() + "\n============errorId -> " + str + "\n============errorInfo -> " + str2 + "\n==================================================");
    }

    public static String hexByte(byte b) {
        String str;
        return ("000000" + Integer.toHexString(b)).substring(str.length() - 2);
    }

    public static String readMetaData(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(str);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getBuildVariant(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0 ? "debug" : "release";
        } catch (Exception e) {
            return "release";
        }
    }

    public static String getSignature(String str, String str2) {
        if (str == null || str2 == null) {
            return "";
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacSHA1");
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKeySpec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        byte[] doFinal = mac.doFinal(str.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : doFinal) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            sb.append(new String(new char[]{cArr[(b >>> 4) & 15], cArr[b & 15]}));
        }
        return sb.toString();
    }

    private static String a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            return str + File.separator + ".record";
        }
        return context.getFilesDir() + File.separator + ".record";
    }

    public static synchronized int getUsedTimes(Context context, String str, String str2) {
        int i;
        synchronized (AuthUtil.class) {
            String b = b(FileIOUtils.readFile2String(a(context, str2)));
            i = 0;
            if (!TextUtils.isEmpty(b)) {
                try {
                    JSONArray jSONArray = new JSONObject(b).getJSONArray("trail");
                    int i2 = 0;
                    for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i3);
                        if (TextUtils.equals(jSONObject.optString("scope"), str)) {
                            i2 = jSONObject.optInt("usedTimes");
                        }
                    }
                    i = i2;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d(TAG, "record file not exist");
            }
        }
        return i;
    }

    public static synchronized void updateUsedTimes(Context context, String str, String str2) {
        synchronized (AuthUtil.class) {
            File file = new File(a(context, str));
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    FileIOUtils.writeFileFromString(a(context, str), a(new JSONObject("{\"trail\":[]}").toString()));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            try {
                JSONObject jSONObject = new JSONObject(b(FileIOUtils.readFile2String(a(context, str))));
                JSONArray jSONArray = jSONObject.getJSONArray("trail");
                int usedTimes = getUsedTimes(context, str2, str);
                if (usedTimes == 0) {
                    Log.d(TAG, "no this scope info or file not exist");
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("scope", str2);
                    jSONObject2.put("usedTimes", usedTimes + 1);
                    jSONArray.put(jSONObject2);
                } else {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                        if (TextUtils.equals(jSONObject3.optString("scope"), str2)) {
                            usedTimes++;
                            jSONObject3.put("usedTimes", usedTimes);
                        }
                    }
                }
                jSONObject.put("trail", jSONArray);
                FileIOUtils.writeFileFromString(a(context, str), a(jSONObject.toString()));
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] + 2);
        }
        return new String(bytes);
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] - 2);
        }
        return new String(bytes);
    }
}
