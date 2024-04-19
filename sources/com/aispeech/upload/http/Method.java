package com.aispeech.upload.http;
/* loaded from: classes.dex */
public class Method {
    public static String GET = "GET";
    public static String POST = "POST";
    public static String HEAD = "HEAD";
    public static String DELETE = "DELETE";
    public static String PUT = "PUT";
    public static String TRACE = "TRACE";
    public static String PATCH = "PATCH";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean isOut(String str) {
        char c;
        switch (str.hashCode()) {
            case 79599:
                if (str.equals("PUT")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 2461856:
                if (str.equals("POST")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 75900968:
                if (str.equals("PATCH")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2012838315:
                if (str.equals("DELETE")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return c == 0 || c == 1 || c == 2 || c == 3;
    }
}
