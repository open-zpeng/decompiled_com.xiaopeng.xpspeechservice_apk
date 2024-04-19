package com.aispeech.upload.util;

import android.os.Environment;
import android.text.TextUtils;
import com.aispeech.common.URLUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes.dex */
public class Log {
    private static boolean INITIALIZED = false;
    public static boolean LOGCAT_DEBUGABLE = true;
    public static boolean LOGFILE_DEBUGABLE = true;
    private static int LOG_LEVEL = 5;
    private static FileWriter writer = null;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static Date datetime = new Date();
    private static boolean IS_COOLPAD = false;

    public static void init(int i, String str) {
        if (LOGFILE_DEBUGABLE) {
            LOG_LEVEL = i;
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
                    d("Upload", "LogFilePath: " + file.getAbsolutePath());
                    FileWriter fileWriter = new FileWriter(file, true);
                    writer = fileWriter;
                    fileWriter.write("*****************************************************\n");
                    datetime.setTime(System.currentTimeMillis());
                    writer.write(datetime.toString());
                    writer.write("*****************************************************\n");
                    writer.flush();
                    INITIALIZED = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    closeFileWriter();
                }
            }
        }
    }

    public static void v(String str, String str2) {
        if (LOGFILE_DEBUGABLE && INITIALIZED && 2 >= LOG_LEVEL) {
            write("DEBUG", str, str2);
        }
        if (LOGCAT_DEBUGABLE) {
            println(2, str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (LOGFILE_DEBUGABLE && INITIALIZED && 3 >= LOG_LEVEL) {
            write("DEBUG", str, str2);
        }
        if (LOGCAT_DEBUGABLE) {
            println(3, str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (LOGFILE_DEBUGABLE && INITIALIZED && 4 >= LOG_LEVEL) {
            write("INFO", str, str2);
        }
        if (LOGCAT_DEBUGABLE) {
            println(4, str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (LOGFILE_DEBUGABLE && INITIALIZED && 5 >= LOG_LEVEL) {
            write("WARN", str, str2);
        }
        println(5, str, str2);
    }

    public static void e(String str, String str2) {
        if (LOGFILE_DEBUGABLE && INITIALIZED && 6 >= LOG_LEVEL) {
            write("ERROR", str, str2);
        }
        println(6, str, str2);
    }

    private static void println(int i, String str, String str2) {
        if (IS_COOLPAD && i < 5) {
            i = 5;
        }
        android.util.Log.println(i, str, str2);
    }

    private static void write(String str, String str2, String str3) {
        write(str, str2, str3, true);
    }

    private static synchronized void write(String str, String str2, String str3, boolean z) {
        synchronized (Log.class) {
            if (writer != null && Environment.getExternalStorageState().equals("mounted")) {
                try {
                    datetime.setTime(System.currentTimeMillis());
                    FileWriter fileWriter = writer;
                    fileWriter.write(sdf.format(datetime) + ": " + str + URLUtils.URL_PATH_SEPERATOR + str2 + ": " + str3 + "\n");
                    if (z) {
                        writer.flush();
                    }
                } catch (Exception e) {
                    closeFileWriter();
                }
            }
        }
    }

    private static void closeFileWriter() {
        if (writer != null && Environment.getExternalStorageState().equals("mounted")) {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
        writer = null;
    }
}
