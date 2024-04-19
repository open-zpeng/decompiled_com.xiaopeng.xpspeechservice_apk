package com.aispeech.upload.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import com.xiaopeng.lib.utils.config.RemoteControlConfig;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
/* loaded from: classes.dex */
public final class NetworkUtils {
    private static final int NETWORK_TYPE_GSM = 16;
    private static final int NETWORK_TYPE_IWLAN = 18;
    private static final int NETWORK_TYPE_TD_SCDMA = 17;
    private static Context mContext;

    /* loaded from: classes.dex */
    public interface NetworkType {
        public static final String NETWORK_2G = "NETWORK_2G";
        public static final String NETWORK_3G = "NETWORK_3G";
        public static final String NETWORK_4G = "NETWORK_4G";
        public static final String NETWORK_NO = "NETWORK_NO";
        public static final String NETWORK_UNKNOWN = "NETWORK_UNKNOWN";
        public static final String NETWORK_WIFI = "NETWORK_WIFI";
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    public static void openWirelessSettings() {
        mContext.startActivity(new Intent("android.settings.WIRELESS_SETTINGS").setFlags(268435456));
    }

    private static NetworkInfo getActiveNetworkInfo() {
        return ((ConnectivityManager) mContext.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean isConnected() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean getDataEnabled() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService("phone");
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
            if (declaredMethod != null) {
                return ((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])).booleanValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void setDataEnabled(boolean z) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService("phone");
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("setDataEnabled", Boolean.TYPE);
            if (declaredMethod != null) {
                declaredMethod.invoke(telephonyManager, Boolean.valueOf(z));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean is4G() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getSubtype() == 13;
    }

    public static boolean getWifiEnabled() {
        return ((WifiManager) mContext.getSystemService("wifi")).isWifiEnabled();
    }

    public static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService("wifi");
        if (z) {
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
        } else if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }

    public static boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService("connectivity");
        return (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null || connectivityManager.getActiveNetworkInfo().getType() != 1) ? false : true;
    }

    public static String getNetworkOperatorName() {
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return null;
    }

    public static String getNetworkType() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            if (activeNetworkInfo.getType() == 1) {
                return NetworkType.NETWORK_WIFI;
            }
            if (activeNetworkInfo.getType() == 0) {
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                        return NetworkType.NETWORK_2G;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case RemoteControlConfig.REMOTE_ACCOUNT_FACE_BIND /* 15 */:
                    case 17:
                        return NetworkType.NETWORK_3G;
                    case 13:
                    case NETWORK_TYPE_IWLAN /* 18 */:
                        return NetworkType.NETWORK_4G;
                    default:
                        String subtypeName = activeNetworkInfo.getSubtypeName();
                        return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? NetworkType.NETWORK_3G : NetworkType.NETWORK_UNKNOWN;
                }
            }
            return NetworkType.NETWORK_UNKNOWN;
        }
        return NetworkType.NETWORK_NO;
    }

    public static String getIPAddress(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp()) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (!nextElement2.isLoopbackAddress()) {
                            String hostAddress = nextElement2.getHostAddress();
                            boolean z2 = hostAddress.indexOf(58) < 0;
                            if (z) {
                                if (z2) {
                                    return hostAddress;
                                }
                            } else if (!z2) {
                                int indexOf = hostAddress.indexOf(37);
                                return indexOf < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, indexOf).toUpperCase();
                            }
                        }
                    }
                    continue;
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDomainAddress(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
}
