package com.aispeech.upload.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class FileIOUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static int sBufferSize = 8192;

    private FileIOUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream) {
        return writeFileFromIS(getFileByPath(str), inputStream, false);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, boolean z) {
        return writeFileFromIS(getFileByPath(str), inputStream, z);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream) {
        return writeFileFromIS(file, inputStream, false);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, boolean z) {
        BufferedOutputStream bufferedOutputStream;
        if (!createOrExistsFile(file) || inputStream == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z));
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[sBufferSize];
            while (true) {
                int read = inputStream.read(bArr, 0, sBufferSize);
                if (read == -1) {
                    CloseUtils.closeIO(inputStream, bufferedOutputStream);
                    return true;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e2) {
            e = e2;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            CloseUtils.closeIO(inputStream, bufferedOutputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            CloseUtils.closeIO(inputStream, bufferedOutputStream2);
            throw th;
        }
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, false);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, z);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr) {
        return writeFileFromBytesByStream(file, bArr, false);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr, boolean z) {
        BufferedOutputStream bufferedOutputStream;
        if (bArr == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z));
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedOutputStream.write(bArr);
            CloseUtils.closeIO(bufferedOutputStream);
            return true;
        } catch (IOException e2) {
            e = e2;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            CloseUtils.closeIO(bufferedOutputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            CloseUtils.closeIO(bufferedOutputStream2);
            throw th;
        }
    }

    public static boolean writeFileFromBytesByChannel(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByChannel(getFileByPath(str), bArr, false, z);
    }

    public static boolean writeFileFromBytesByChannel(String str, byte[] bArr, boolean z, boolean z2) {
        return writeFileFromBytesByChannel(getFileByPath(str), bArr, z, z2);
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByChannel(file, bArr, false, z);
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bArr, boolean z, boolean z2) {
        if (bArr == null) {
            return false;
        }
        FileChannel fileChannel = null;
        try {
            try {
                fileChannel = new FileOutputStream(file, z).getChannel();
                fileChannel.position(fileChannel.size());
                fileChannel.write(ByteBuffer.wrap(bArr));
                if (z2) {
                    fileChannel.force(true);
                }
                CloseUtils.closeIO(fileChannel);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                CloseUtils.closeIO(fileChannel);
                return false;
            }
        } catch (Throwable th) {
            CloseUtils.closeIO(fileChannel);
            throw th;
        }
    }

    public static boolean writeFileFromBytesByMap(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByMap(str, bArr, false, z);
    }

    public static boolean writeFileFromBytesByMap(String str, byte[] bArr, boolean z, boolean z2) {
        return writeFileFromBytesByMap(getFileByPath(str), bArr, z, z2);
    }

    public static boolean writeFileFromBytesByMap(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByMap(file, bArr, false, z);
    }

    public static boolean writeFileFromBytesByMap(File file, byte[] bArr, boolean z, boolean z2) {
        FileChannel fileChannel;
        if (bArr == null || !createOrExistsFile(file)) {
            return false;
        }
        FileChannel fileChannel2 = null;
        try {
            try {
                fileChannel = new FileOutputStream(file, z).getChannel();
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            fileChannel = fileChannel2;
        }
        try {
            MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, fileChannel.size(), bArr.length);
            map.put(bArr);
            if (z2) {
                map.force();
            }
            CloseUtils.closeIO(fileChannel);
            return true;
        } catch (IOException e2) {
            e = e2;
            fileChannel2 = fileChannel;
            e.printStackTrace();
            CloseUtils.closeIO(fileChannel2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            CloseUtils.closeIO(fileChannel);
            throw th;
        }
    }

    public static boolean writeFileFromString(String str, String str2) {
        return writeFileFromString(getFileByPath(str), str2, false);
    }

    public static boolean writeFileFromString(String str, String str2, boolean z) {
        return writeFileFromString(getFileByPath(str), str2, z);
    }

    public static boolean writeFileFromString(File file, String str) {
        return writeFileFromString(file, str, false);
    }

    public static boolean writeFileFromString(File file, String str, boolean z) {
        BufferedWriter bufferedWriter;
        if (file == null || str == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file, z));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            bufferedWriter.write(str);
            CloseUtils.closeIO(bufferedWriter);
            return true;
        } catch (IOException e2) {
            e = e2;
            bufferedWriter2 = bufferedWriter;
            e.printStackTrace();
            CloseUtils.closeIO(bufferedWriter2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
            CloseUtils.closeIO(bufferedWriter2);
            throw th;
        }
    }

    public static List<String> readFile2List(String str) {
        return readFile2List(getFileByPath(str), (String) null);
    }

    public static List<String> readFile2List(String str, String str2) {
        return readFile2List(getFileByPath(str), str2);
    }

    public static List<String> readFile2List(File file) {
        return readFile2List(file, 0, Integer.MAX_VALUE, (String) null);
    }

    public static List<String> readFile2List(File file, String str) {
        return readFile2List(file, 0, Integer.MAX_VALUE, str);
    }

    public static List<String> readFile2List(String str, int i, int i2) {
        return readFile2List(getFileByPath(str), i, i2, (String) null);
    }

    public static List<String> readFile2List(String str, int i, int i2, String str2) {
        return readFile2List(getFileByPath(str), i, i2, str2);
    }

    public static List<String> readFile2List(File file, int i, int i2) {
        return readFile2List(file, i, i2, (String) null);
    }

    public static List<String> readFile2List(File file, int i, int i2, String str) {
        BufferedReader bufferedReader;
        int i3;
        BufferedReader bufferedReader2 = null;
        if (!isFileExists(file) || i > i2) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            if (isSpace(str)) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                i3 = 1;
            } else {
                BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(new FileInputStream(file), str));
                i3 = 1;
                bufferedReader = bufferedReader3;
            }
            while (true) {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null || i3 > i2) {
                            break;
                        }
                        if (i <= i3 && i3 <= i2) {
                            arrayList.add(readLine);
                        }
                        i3++;
                    } catch (IOException e) {
                        e = e;
                        e.printStackTrace();
                        CloseUtils.closeIO(bufferedReader);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    CloseUtils.closeIO(bufferedReader2);
                    throw th;
                }
            }
            CloseUtils.closeIO(bufferedReader);
            return arrayList;
        } catch (IOException e2) {
            e = e2;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
            CloseUtils.closeIO(bufferedReader2);
            throw th;
        }
    }

    public static String readFile2String(String str) {
        return readFile2String(getFileByPath(str), (String) null);
    }

    public static String readFile2String(String str, String str2) {
        return readFile2String(getFileByPath(str), str2);
    }

    public static String readFile2String(File file) {
        return readFile2String(file, (String) null);
    }

    public static String readFile2String(File file, String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        if (!isFileExists(file)) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            bufferedReader = isSpace(str) ? new BufferedReader(new InputStreamReader(new FileInputStream(file))) : new BufferedReader(new InputStreamReader(new FileInputStream(file), str));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        String sb2 = sb.delete(sb.length() - LINE_SEP.length(), sb.length()).toString();
                        CloseUtils.closeIO(bufferedReader);
                        return sb2;
                    }
                    sb.append(readLine);
                    sb.append(LINE_SEP);
                } catch (Exception e) {
                    CloseUtils.closeIO(bufferedReader);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    CloseUtils.closeIO(bufferedReader2);
                    throw th;
                }
            }
        } catch (Exception e2) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static byte[] readFile2BytesByStream(String str) {
        return readFile2BytesByStream(getFileByPath(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static byte[] readFile2BytesByStream(File file) {
        FileInputStream fileInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        if (!isFileExists(file)) {
            return null;
        }
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e) {
            e = e;
            byteArrayOutputStream = null;
            fileInputStream = null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            file = null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr = new byte[sBufferSize];
                while (true) {
                    int read = fileInputStream.read(bArr, 0, sBufferSize);
                    if (read == -1) {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        CloseUtils.closeIO(fileInputStream, byteArrayOutputStream);
                        return byteArray;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                CloseUtils.closeIO(fileInputStream, byteArrayOutputStream);
                return null;
            }
        } catch (IOException e3) {
            e = e3;
            byteArrayOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            file = null;
            CloseUtils.closeIO(fileInputStream, file);
            throw th;
        }
    }

    public static byte[] readFile2BytesByChannel(String str) {
        return readFile2BytesByChannel(getFileByPath(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static byte[] readFile2BytesByChannel(File file) {
        Throwable th;
        FileChannel fileChannel;
        if (!isFileExists(file)) {
            return null;
        }
        try {
            try {
                fileChannel = new RandomAccessFile(file, "r").getChannel();
                try {
                    ByteBuffer allocate = ByteBuffer.allocate((int) fileChannel.size());
                    do {
                    } while (fileChannel.read(allocate) > 0);
                    byte[] array = allocate.array();
                    CloseUtils.closeIO(fileChannel);
                    return array;
                } catch (IOException e) {
                    e = e;
                    e.printStackTrace();
                    CloseUtils.closeIO(fileChannel);
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                CloseUtils.closeIO(file);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            fileChannel = null;
        } catch (Throwable th3) {
            th = th3;
            file = null;
            CloseUtils.closeIO(file);
            throw th;
        }
    }

    public static byte[] readFile2BytesByMap(String str) {
        return readFile2BytesByMap(getFileByPath(str));
    }

    public static byte[] readFile2BytesByMap(File file) {
        Throwable th;
        FileChannel fileChannel;
        if (!isFileExists(file)) {
            return null;
        }
        try {
            fileChannel = new RandomAccessFile(file, "r").getChannel();
            try {
                try {
                    int size = (int) fileChannel.size();
                    byte[] bArr = new byte[size];
                    fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, size).load().get(bArr, 0, size);
                    CloseUtils.closeIO(fileChannel);
                    return bArr;
                } catch (IOException e) {
                    e = e;
                    e.printStackTrace();
                    CloseUtils.closeIO(fileChannel);
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                CloseUtils.closeIO(fileChannel);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            fileChannel = null;
        } catch (Throwable th3) {
            th = th3;
            fileChannel = null;
            CloseUtils.closeIO(fileChannel);
            throw th;
        }
    }

    public static void setBufferSize(int i) {
        sBufferSize = i;
    }

    private static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    private static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(File file) {
        if (file != null) {
            return file.exists() ? file.isDirectory() : file.mkdirs();
        }
        return false;
    }

    private static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
