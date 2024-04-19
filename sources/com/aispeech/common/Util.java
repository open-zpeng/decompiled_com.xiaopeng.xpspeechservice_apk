package com.aispeech.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaopeng.lib.utils.config.CommonConfig;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* loaded from: classes.dex */
public class Util {
    public static final String TAG = "DUILIte";
    public static final String UTF8 = "UTF-8";
    public static byte[] MAGIC = {80, 75, 3, 4};
    public static String uniqueID = null;

    public static boolean isAssetsDir(Context context, String str) {
        try {
            String[] list = context.getAssets().list("");
            if (list != null && list.length != 0) {
                for (String str2 : list) {
                    if (TextUtils.equals(str2, str)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static File getAvaiableAppDataDirPerInternal(Context context, String str, long j) {
        if (context == null) {
            return null;
        }
        if (getAvailableInternalMemorySize() >= j) {
            return new File(context.getFilesDir(), str);
        }
        if (getAvailableExternalMemorySize() < j) {
            return null;
        }
        return new File(getAvaiableExternalDataDir(context), str);
    }

    public static File getAvaiableAppDataDirPerInternal(Context context, String str) {
        return getAvaiableAppDataDirPerInternal(context, str, CommonConfig.MAX_LOG_LENGTH);
    }

    public static File getAvaiableExternalDataDir(Context context) {
        if (context == null || !externalMemoryAvailable()) {
            return null;
        }
        return context.getExternalFilesDir(null);
    }

    public static long getAvailableInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    public static long getTotalInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getBlockCount() * statFs.getBlockSize();
    }

    public static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        }
        return -1L;
    }

    public static long getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getBlockCount() * statFs.getBlockSize();
        }
        return -1L;
    }

    public static File getExternalCacheDir(Context context) {
        if (Build.VERSION.SDK_INT >= 8 && "mounted".equals(Environment.getExternalStorageState())) {
            return context.getExternalCacheDir();
        }
        return null;
    }

    public static File getExternalCacheDir(Context context, String str) {
        File externalCacheDir;
        if (Build.VERSION.SDK_INT >= 8 && "mounted".equals(Environment.getExternalStorageState()) && (externalCacheDir = context.getExternalCacheDir()) != null) {
            if (!externalCacheDir.exists()) {
                externalCacheDir.mkdirs();
            }
            if (TextUtils.isEmpty(str)) {
                return context.getExternalCacheDir();
            }
            File file = new File(externalCacheDir, str);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
        return null;
    }

    public static boolean isAboveAndroid22() {
        if (Build.VERSION.SDK_INT <= 7) {
            return false;
        }
        return true;
    }

    public static String getResourceDir(Context context) {
        if (context == null) {
            return null;
        }
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            filesDir = new File("/data/data/" + context.getPackageName() + "/files");
        }
        String absolutePath = filesDir.getAbsolutePath();
        Log.d("xxx", "getResourceDir : " + absolutePath);
        return absolutePath;
    }

    public static String getResPath(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "file res " + str + " not found");
            return null;
        }
        return getResourceDir(context) + File.separator + str;
    }

    public static int copyResource(Context context, String str, String str2) {
        return copyResource(context, str, true, str2);
    }

    public static int copyResource(Context context, String str) {
        return copyResource(context, str, true, null);
    }

    private static File a(Context context, String str) {
        File file = new File(context.getFilesDir() + File.separator + str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        return file;
    }

    public static synchronized int copyResource(Context context, String str, boolean z, String str2) {
        synchronized (Util.class) {
            if (context == null) {
                return -1;
            }
            try {
                if (TextUtils.isEmpty(str)) {
                    Log.e(Log.ERROR_TAG, "resName is null，Please check whether you have added?");
                    return -1;
                }
                InputStream open = context.getAssets().open(str);
                try {
                    open.read(new byte[1]);
                    open.reset();
                    File file = new File(getResourceDir(context), str);
                    if (str2 == null) {
                        Log.i("speech", "there is no md5 file of : " + str);
                        if (z && a(open, file)) {
                            Log.i("speech", "md5 is same : " + str);
                            try {
                                open.close();
                                return 0;
                            } catch (IOException e) {
                                e.printStackTrace();
                                return -1;
                            }
                        }
                        saveDestFile(context, open, str);
                        if (isZipFile(a(context, str))) {
                            unZip(context, file);
                        }
                        return 1;
                    }
                    Log.i("speech", "there is md5 file of : " + str);
                    try {
                        InputStream open2 = context.getAssets().open(str2);
                        File file2 = new File(getResourceDir(context), str2);
                        if (z && a(open2, file2)) {
                            Log.i("speech", " md5 file in assets and data drectory is same : " + str);
                            try {
                                open2.close();
                                open.close();
                                return 0;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return -1;
                            }
                        }
                        Log.i("speech", " md5 file in assets and data drectory is not same : " + str);
                        saveDestFile(context, open, str);
                        saveDestFile(context, open2, str2);
                        if (isZipFile(a(context, str))) {
                            unZip(context, file);
                        }
                        return 1;
                    } catch (IOException e3) {
                        Log.e(Log.ERROR_TAG, "file " + str2 + " not found in assest floder, Did you forget add it?");
                        e3.printStackTrace();
                        return -1;
                    }
                } catch (IOException e4) {
                    Log.e(Log.ERROR_TAG, "file" + str + "should be one of the suffix below to avoid be compressed in assets floder.“.jpg”, “.jpeg”, “.png”, “.gif”, “.wav”, “.mp2″, “.mp3″, “.ogg”, “.aac”, “.mpg”, “.mpeg”, “.mid”, “.midi”, “.smf”, “.jet”, “.rtttl”, “.imy”, “.xmf”, “.mp4″, “.m4a”, “.m4v”, “.3gp”, “.3gpp”, “.3g2″, “.3gpp2″, “.amr”, “.awb”, “.wma”, “.wmv”");
                    return -1;
                }
            } catch (Exception e5) {
                Log.e(Log.ERROR_TAG, "file " + str + " not found in assest folder, Did you forget add it?");
                return -1;
            }
        }
    }

    public static int copyFilesFromAssets(Context context, String str, String str2) {
        if (context == null) {
            return -1;
        }
        try {
            String[] list = context.getAssets().list(str);
            if (list.length > 0) {
                new File(str2).mkdirs();
                for (String str3 : list) {
                    copyFilesFromAssets(context, str + URLUtils.URL_PATH_SEPERATOR + str3, str2 + URLUtils.URL_PATH_SEPERATOR + str3);
                }
            } else {
                InputStream open = context.getAssets().open(str);
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                open.close();
                fileOutputStream.close();
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void saveDestFile(Context context, InputStream inputStream, String str) {
        Log.i("speech", "save to data : " + str);
        try {
            inputStream.reset();
            FileOutputStream fileOutputStream = new FileOutputStream(a(context, str));
            byte[] bArr = new byte[10240];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    inputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isZipFile(File file) {
        boolean z;
        byte[] bArr = new byte[MAGIC.length];
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.readFully(bArr);
            int i = 0;
            while (true) {
                if (i >= MAGIC.length) {
                    z = true;
                    break;
                } else if (bArr[i] == MAGIC[i]) {
                    i++;
                } else {
                    z = false;
                    break;
                }
            }
            randomAccessFile.close();
            return z;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean a(InputStream inputStream, File file) {
        boolean z;
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] a = a(fileInputStream);
                byte[] a2 = a(inputStream);
                int length = a.length > a2.length ? a2.length : a.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = true;
                        break;
                    } else if (a[i] == a2[i]) {
                        i++;
                    } else {
                        z = false;
                        break;
                    }
                }
                fileInputStream.close();
                return z;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static void unZip(Context context, File file) {
        byte[] bArr = new byte[10240];
        try {
            ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry nextElement = entries.nextElement();
                if (nextElement.isDirectory()) {
                    new File(getResourceDir(context), nextElement.getName()).mkdirs();
                } else {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(nextElement));
                    Log.d("unzip", nextElement.getName());
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(getResourceDir(context), nextElement.getName())), 10240);
                    while (true) {
                        int read = bufferedInputStream.read(bArr, 0, 10240);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                }
            }
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String SHA1(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() < 2) {
                    sb.append(0);
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
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

    public static String md5(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String newUTF8String(byte[] bArr) {
        try {
            return new String(bArr, UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new String(bArr);
        }
    }

    public static byte[] getUTF8Bytes(String str) {
        try {
            return str.getBytes(UTF8);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return telephonyManager.getDeviceId() != null ? telephonyManager.getDeviceId() : "";
    }

    public static String generateDeviceId32(Context context) {
        String str;
        String str2;
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String deviceId = telephonyManager.getDeviceId() != null ? telephonyManager.getDeviceId() : "hello world aispeech";
            String str3 = telephonyManager.getSimSerialNumber();
            UUID uuid = new UUID((str2.hashCode() << 32) | deviceId.hashCode(), (str.hashCode() << 32) | str3.hashCode());
            StringBuilder sb = new StringBuilder();
            sb.append(uuid.version());
            Log.i("version", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append((Settings.Secure.getString(context.getContentResolver(), "android_id")).hashCode());
            Log.i("android_id", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(deviceId.hashCode());
            Log.i("tmDevice", sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append(("35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10)).hashCode());
            Log.i("m_szDevIDShort", sb4.toString());
            Log.i("UUID", uuid.toString());
            return uuid.toString().replaceAll("-", "");
        } catch (Exception e) {
            Log.e("", "Did you forget add android.permission.READ_PHONE_STATE permission in your application? Add it now to fix this bug!");
            return null;
        }
    }

    public static String generateDeviceId16(Context context) {
        String str;
        String str2;
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (Build.VERSION.SDK_INT <= 9) {
            str = null;
        } else {
            str = Build.SERIAL;
        }
        try {
            str2 = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception e) {
            Log.e("", "Did you forget add android.permission.READ_PHONE_STATE permission in your application? Add it now to fix this bug!");
            str2 = null;
        }
        if (!TextUtils.isEmpty(string) && !TextUtils.equals(string, "9774d56d682e549c")) {
            str2 = string;
        } else if (TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str)) {
                str2 = "";
            } else {
                str2 = str;
            }
        }
        if (str2.length() < 8) {
            str2 = "";
        }
        return str2.toLowerCase();
    }

    public static void setDid(Context context, String str) {
    }

    public static String getDisplayInfo(Context context) {
        if (context == null) {
            return null;
        }
        int i = context.getResources().getDisplayMetrics().heightPixels;
        int i2 = context.getResources().getDisplayMetrics().widthPixels;
        return i2 + "x" + i;
    }

    public static void logThread(String str) {
        Thread currentThread = Thread.currentThread();
        Log.d(str, "<" + currentThread.getName() + ">id: " + currentThread.getId() + ", Priority: " + currentThread.getPriority() + ", Group: " + currentThread.getThreadGroup().getName());
    }

    public static String getNetWorkType(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            int type = activeNetworkInfo.getType();
            if (type == 0) {
                switch (telephonyManager.getNetworkType()) {
                    case 1:
                        return "GPRS";
                    case 2:
                        return "EDGE";
                    case 3:
                        return "UMTS";
                    case 4:
                        return "CDMA";
                    case 5:
                        return "EVDO_0";
                    case 6:
                        return "EVDO_A";
                    case 7:
                        return "1xRTT";
                    case 8:
                        return "HSDPA";
                    case 9:
                        return "HSUPA";
                    case 10:
                        return "HSPA";
                    case 11:
                        return "IDEN";
                }
            } else if (type == 1) {
                return "WIFI";
            }
            return "unknown_type";
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Did you forget add android.permission.ACCESS_NETWORK_STATE permission in your application? Add it to fix this bug!");
            return null;
        }
    }

    public static int getNetworkQuality(Context context) {
        String netWorkType = getNetWorkType(context);
        return (netWorkType == null || netWorkType.equals("EDGE") || netWorkType.equals("GPRS") || netWorkType.equals("1xRTT") || netWorkType.equals("IDEN")) ? 3 : 8;
    }

    public static boolean isUnitTesting() {
        return Thread.currentThread().getName().contains("android.test.InstrumentationTestRunner");
    }

    public static void executeRunnableInMainOrTestThread(Context context, Runnable runnable) {
        if (context != null) {
            HandlerThread handlerThread = null;
            boolean isUnitTesting = isUnitTesting();
            if (isUnitTesting) {
                handlerThread = new HandlerThread("TestHandlerThread");
                handlerThread.start();
            }
            new Handler(isUnitTesting ? handlerThread.getLooper() : context.getMainLooper()).post(runnable);
        }
    }

    public static String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-sss", Locale.CHINA).format(new Date());
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public static long generateRandom(int i) {
        Random random = new Random();
        char[] cArr = new char[i];
        cArr[0] = (char) (random.nextInt(9) + 49);
        for (int i2 = 1; i2 < i; i2++) {
            cArr[i2] = (char) (random.nextInt(10) + 48);
        }
        return Long.parseLong(new String(cArr));
    }

    public static byte[] toByteArray(short[] sArr) {
        byte[] bArr = new byte[sArr.length << 1];
        for (int i = 0; i < sArr.length; i++) {
            int i2 = i << 1;
            bArr[i2] = (byte) sArr[i];
            bArr[i2 + 1] = (byte) (sArr[i] >> 8);
        }
        return bArr;
    }

    public static short[] toShortArray(byte[] bArr) {
        int length = bArr.length / 2;
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            sArr[i] = (short) ((bArr[i << 1] & 255) | ((bArr[(i * 2) + 1] & 255) << 8));
        }
        return sArr;
    }

    public static byte[] getRecChannelData(byte[] bArr) {
        short[] shortArray = toShortArray(bArr);
        for (int i = 0; i < bArr.length / 2; i += 2) {
            short s = shortArray[i];
            int i2 = i + 1;
            shortArray[i] = shortArray[i2];
            shortArray[i2] = s;
        }
        return toByteArray(shortArray);
    }

    public static double calcVolume(short[] sArr) {
        double d = 0.0d;
        for (short s : sArr) {
            double d2 = s / 32768.0d;
            d += d2 * d2;
        }
        double sqrt = Math.sqrt(d / sArr.length);
        if (sqrt <= 0.001d) {
            return 0.0d;
        }
        return 100.0d + (Math.log10(sqrt) * 20.0d);
    }

    public static String getEthMacAddress() {
        try {
            NetworkInterface byName = NetworkInterface.getByName("eth0");
            if (byName == null) {
                return "";
            }
            byte[] hardwareAddress = byName.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (byte b : hardwareAddress) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getWIFIMacAddress(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress().replace(":", "");
    }

    public static synchronized int copyResourceNew(Context context, String str, String str2) {
        int copyResource;
        synchronized (Util.class) {
            copyResource = new MD5Checker().copyResource(context, str, true, str2);
        }
        return copyResource;
    }

    public static synchronized void deleteFile(Context context, String str) {
        synchronized (Util.class) {
            new MD5Checker().deleteFile(context, str);
        }
    }
}
