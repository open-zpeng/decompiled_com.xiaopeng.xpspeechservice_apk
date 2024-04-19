package com.aispeech.common;

import android.os.Environment;
import android.text.TextUtils;
import com.aispeech.lite.d;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes.dex */
public class Log {
    public static final String ERROR_TAG = "AISpeech Error";
    public static final String WARNING_TAG = "AISpeech Warning";
    private static boolean a = false;
    public static boolean LOGCAT_DEBUGABLE = d.a;
    public static boolean LOGFILE_DEBUGABLE = d.b;
    public static int LOG_LEVEL = 5;
    private static FileWriter b = null;
    private static SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static Date d = new Date();

    public static void init(int i, String str) {
        LOG_LEVEL = i;
        if (LOGFILE_DEBUGABLE) {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    parentFile.mkdirs();
                    if (!parentFile.exists()) {
                        return;
                    }
                }
                try {
                    d("DUILite", "LogFilePath: " + file.getAbsolutePath());
                    FileWriter fileWriter = new FileWriter(file, true);
                    b = fileWriter;
                    fileWriter.write("*****************************************************\n");
                    d.setTime(System.currentTimeMillis());
                    b.write(d.toString());
                    b.write("*****************************************************\n");
                    b.flush();
                    a = true;
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    a();
                    return;
                }
            }
            return;
        }
        d("DUILite", "only print log!!");
    }

    public static void v(String str, String str2) {
        if (LOGFILE_DEBUGABLE && a && 2 >= LOG_LEVEL) {
            a("DEBUG", str, str2);
        }
        if (!LOGCAT_DEBUGABLE || 2 < LOG_LEVEL) {
            return;
        }
        android.util.Log.println(2, str, str2);
    }

    public static void d(String str, String str2) {
        if (LOGFILE_DEBUGABLE && a && 3 >= LOG_LEVEL) {
            a("DEBUG", str, str2);
        }
        if (!LOGCAT_DEBUGABLE || 3 < LOG_LEVEL) {
            return;
        }
        android.util.Log.println(3, str, str2);
    }

    public static void i(String str, String str2) {
        if (LOGFILE_DEBUGABLE && a && 4 >= LOG_LEVEL) {
            a("INFO", str, str2);
        }
        if (!LOGCAT_DEBUGABLE || 4 < LOG_LEVEL) {
            return;
        }
        android.util.Log.println(4, str, str2);
    }

    public static void w(String str, String str2) {
        if (LOGFILE_DEBUGABLE && a && 5 >= LOG_LEVEL) {
            a("WARN", str, str2);
        }
        if (5 < LOG_LEVEL) {
            return;
        }
        android.util.Log.println(5, str, str2);
    }

    public static void e(String str, String str2) {
        if (LOGFILE_DEBUGABLE && a && 6 >= LOG_LEVEL) {
            a("ERROR", str, str2);
        }
        if (6 < LOG_LEVEL) {
            return;
        }
        android.util.Log.println(6, str, str2);
    }

    private static synchronized void a(String str, String str2, String str3) {
        synchronized (Log.class) {
            if (b != null && Environment.getExternalStorageState().equals("mounted")) {
                try {
                    d.setTime(System.currentTimeMillis());
                    FileWriter fileWriter = b;
                    fileWriter.write(c.format(d) + ": " + str + URLUtils.URL_PATH_SEPERATOR + str2 + ": " + str3 + "\n");
                    b.flush();
                } catch (Exception e) {
                    a();
                }
            }
        }
    }

    private static void a() {
        if (b != null && Environment.getExternalStorageState().equals("mounted")) {
            try {
                b.close();
            } catch (Exception e) {
            }
        }
        b = null;
    }
}
