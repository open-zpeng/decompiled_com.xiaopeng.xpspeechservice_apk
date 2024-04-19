package com.aispeech.common;

import android.annotation.SuppressLint;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class FileUtils {
    private static final String a = System.getProperty("line.separator");
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private FileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static File getFileByPath(String str) {
        if (a(str)) {
            return null;
        }
        return new File(str);
    }

    public static boolean isFileExists(String str) {
        return isFileExists(getFileByPath(str));
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static boolean rename(String str, String str2) {
        return rename(getFileByPath(str), str2);
    }

    public static boolean rename(File file, String str) {
        if (file == null || !file.exists() || a(str)) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        return !file2.exists() && file.renameTo(file2);
    }

    public static boolean isDir(String str) {
        return isDir(getFileByPath(str));
    }

    public static boolean isDir(File file) {
        return isFileExists(file) && file.isDirectory();
    }

    public static boolean isFile(String str) {
        return isFile(getFileByPath(str));
    }

    public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }

    public static boolean createOrExistsDir(String str) {
        return createOrExistsDir(getFileByPath(str));
    }

    public static boolean createOrExistsDir(File file) {
        if (file != null) {
            return file.exists() ? file.isDirectory() : file.mkdirs();
        }
        return false;
    }

    public static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    public static boolean createOrExistsFile(File file) {
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

    public static boolean createFileByDeleteOldFile(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && !file.delete()) || !createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean a(File file, File file2, boolean z) {
        File[] listFiles;
        if (file == null || file2 == null) {
            return false;
        }
        String str = file2.getPath() + File.separator;
        if (str.contains(file.getPath() + File.separator) || !file.exists() || !file.isDirectory() || !createOrExistsDir(file2)) {
            return false;
        }
        for (File file3 : file.listFiles()) {
            File file4 = new File(str + file3.getName());
            if (file3.isFile()) {
                if (!b(file3, file4, z)) {
                    return false;
                }
            } else if (file3.isDirectory() && !a(file3, file4, z)) {
                return false;
            }
        }
        return !z || deleteDir(file);
    }

    private static boolean b(File file, File file2, boolean z) {
        if (file == null || file2 == null || !file.exists() || !file.isFile()) {
            return false;
        }
        if ((!file2.exists() || !file2.isFile()) && createOrExistsDir(file2.getParentFile())) {
            try {
                if (FileIOUtils.writeFileFromIS(file2, (InputStream) new FileInputStream(file), false)) {
                    if (!z) {
                        return true;
                    }
                    if (deleteFile(file)) {
                        return true;
                    }
                }
                return false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean copyDir(String str, String str2) {
        return copyDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean copyDir(File file, File file2) {
        return a(file, file2, false);
    }

    public static boolean copyFile(String str, String str2) {
        return copyFile(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean copyFile(File file, File file2) {
        return b(file, file2, false);
    }

    public static boolean moveDir(String str, String str2) {
        return moveDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean moveDir(File file, File file2) {
        return a(file, file2, true);
    }

    public static boolean moveFile(String str, String str2) {
        return moveFile(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean moveFile(File file, File file2) {
        return b(file, file2, true);
    }

    public static boolean deleteDir(String str) {
        return deleteDir(getFileByPath(str));
    }

    public static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            if (!file.isDirectory()) {
                return false;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    if (file2.isFile()) {
                        if (!file2.delete()) {
                            return false;
                        }
                    } else if (file2.isDirectory() && !deleteDir(file2)) {
                        return false;
                    }
                }
            }
            return file.delete();
        }
        return true;
    }

    public static boolean deleteFile(String str) {
        return deleteFile(getFileByPath(str));
    }

    public static boolean deleteFile(File file) {
        if (file != null) {
            if (file.exists()) {
                return file.isFile() && file.delete();
            }
            return true;
        }
        return false;
    }

    public static boolean deleteFilesInDir(String str) {
        return deleteFilesInDir(getFileByPath(str));
    }

    public static boolean deleteFilesInDir(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            if (!file.isDirectory()) {
                return false;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    if (file2.isFile()) {
                        if (!file2.delete()) {
                            return false;
                        }
                    } else if (file2.isDirectory() && !deleteDir(file2)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return true;
    }

    public static List<File> listFilesInDir(String str, boolean z) {
        return listFilesInDir(getFileByPath(str), z);
    }

    public static List<File> listFilesInDir(File file, boolean z) {
        if (isDir(file)) {
            if (z) {
                return listFilesInDir(file);
            }
            ArrayList arrayList = new ArrayList();
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                Collections.addAll(arrayList, listFiles);
            }
            return arrayList;
        }
        return null;
    }

    public static List<File> listFilesInDir(String str) {
        return listFilesInDir(getFileByPath(str));
    }

    public static List<File> listFilesInDir(File file) {
        List<File> listFilesInDir;
        if (isDir(file)) {
            ArrayList arrayList = new ArrayList();
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    arrayList.add(file2);
                    if (file2.isDirectory() && (listFilesInDir = listFilesInDir(file2)) != null) {
                        arrayList.addAll(listFilesInDir);
                    }
                }
            }
            return arrayList;
        }
        return null;
    }

    public static List<File> listFoldersInDir(String str) throws Exception {
        ArrayList arrayList = new ArrayList();
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    arrayList.add(listFiles[i]);
                }
            }
            return arrayList;
        }
        Log.e("FILE", "files is not exist at " + str);
        throw new Exception("file is not exist");
    }

    public static List<File> listFilesInDirWithFilter(String str, String str2, boolean z) {
        return listFilesInDirWithFilter(getFileByPath(str), str2, z);
    }

    public static List<File> listFilesInDirWithFilter(File file, String str, boolean z) {
        if (z) {
            return listFilesInDirWithFilter(file, str);
        }
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                if (file2.getName().toUpperCase().endsWith(str.toUpperCase())) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDirWithFilter(String str, String str2) {
        return listFilesInDirWithFilter(getFileByPath(str), str2);
    }

    public static List<File> listFilesInDirWithFilter(File file, String str) {
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                if (file2.getName().toUpperCase().endsWith(str.toUpperCase())) {
                    arrayList.add(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.addAll(listFilesInDirWithFilter(file2, str));
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDirWithFilter(String str, FilenameFilter filenameFilter, boolean z) {
        return listFilesInDirWithFilter(getFileByPath(str), filenameFilter, z);
    }

    public static List<File> listFilesInDirWithFilter(File file, FilenameFilter filenameFilter, boolean z) {
        if (z) {
            return listFilesInDirWithFilter(file, filenameFilter);
        }
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                if (filenameFilter.accept(file2.getParentFile(), file2.getName())) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDirWithFilter(String str, FilenameFilter filenameFilter) {
        return listFilesInDirWithFilter(getFileByPath(str), filenameFilter);
    }

    public static List<File> listFilesInDirWithFilter(File file, FilenameFilter filenameFilter) {
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                if (filenameFilter.accept(file2.getParentFile(), file2.getName())) {
                    arrayList.add(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.addAll(listFilesInDirWithFilter(file2, filenameFilter));
                }
            }
        }
        return arrayList;
    }

    public static List<File> searchContainsFileInDir(String str, String str2) {
        return searchContainsFileInDir(getFileByPath(str), str2);
    }

    public static List<File> searchContainsFileInDir(File file, String str) {
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                if (file2.getName().toUpperCase().contains(str.toUpperCase())) {
                    arrayList.add(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.addAll(searchFileInDir(file2, str));
                }
            }
        }
        return arrayList;
    }

    public static List<File> searchFileInDir(String str, String str2) {
        return searchFileInDir(getFileByPath(str), str2);
    }

    public static List<File> searchFileInDir(File file, String str) {
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                if (file2.getName().toUpperCase().equals(str.toUpperCase())) {
                    arrayList.add(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.addAll(searchFileInDir(file2, str));
                }
            }
        }
        return arrayList;
    }

    public static long getFileLastModified(String str) {
        return getFileLastModified(getFileByPath(str));
    }

    public static long getFileLastModified(File file) {
        if (file == null) {
            return -1L;
        }
        return file.lastModified();
    }

    public static String getFileCharsetSimple(String str) {
        return getFileCharsetSimple(getFileByPath(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004e A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getFileCharsetSimple(java.io.File r5) {
        /*
            r0 = 1
            r1 = 0
            r2 = 0
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
            int r5 = r3.read()     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L25
            int r5 = r5 << 8
            int r2 = r3.read()     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L25
            int r5 = r5 + r2
            java.io.Closeable[] r0 = new java.io.Closeable[r0]
            r0[r1] = r3
            com.aispeech.common.CloseUtils.closeIO(r0)
            goto L36
        L22:
            r5 = move-exception
            r2 = r3
            goto L51
        L25:
            r5 = move-exception
            r2 = r3
            goto L2b
        L28:
            r5 = move-exception
            goto L51
        L2a:
            r5 = move-exception
        L2b:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L28
            java.io.Closeable[] r5 = new java.io.Closeable[r0]
            r5[r1] = r2
            com.aispeech.common.CloseUtils.closeIO(r5)
            r5 = r1
        L36:
            r0 = 61371(0xefbb, float:8.5999E-41)
            if (r5 == r0) goto L4e
            r0 = 65279(0xfeff, float:9.1475E-41)
            if (r5 == r0) goto L4b
            r0 = 65534(0xfffe, float:9.1833E-41)
            if (r5 == r0) goto L48
            java.lang.String r5 = "GBK"
            return r5
        L48:
            java.lang.String r5 = "Unicode"
            return r5
        L4b:
            java.lang.String r5 = "UTF-16BE"
            return r5
        L4e:
            java.lang.String r5 = "UTF-8"
            return r5
        L51:
            java.io.Closeable[] r0 = new java.io.Closeable[r0]
            r0[r1] = r2
            com.aispeech.common.CloseUtils.closeIO(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.common.FileUtils.getFileCharsetSimple(java.io.File):java.lang.String");
    }

    public static int getFileLines(String str) {
        return getFileLines(getFileByPath(str));
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x0036: MOVE  (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:19:0x0035 */
    public static int getFileLines(File file) {
        BufferedInputStream bufferedInputStream;
        int i;
        Closeable[] closeableArr;
        int i2;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    try {
                        byte[] bArr = new byte[1024];
                        try {
                            try {
                                if (!a.endsWith("\n")) {
                                    i = 1;
                                    while (true) {
                                        int read = bufferedInputStream.read(bArr, 0, 1024);
                                        if (read == -1) {
                                            break;
                                        }
                                        int i3 = i;
                                        for (int i4 = 0; i4 < read; i4++) {
                                            if (bArr[i4] == 13) {
                                                i3++;
                                            }
                                        }
                                        i = i3;
                                    }
                                } else {
                                    i = 1;
                                    while (true) {
                                        int read2 = bufferedInputStream.read(bArr, 0, 1024);
                                        if (read2 == -1) {
                                            break;
                                        }
                                        int i5 = i;
                                        for (int i6 = 0; i6 < read2; i6++) {
                                            if (bArr[i6] == 10) {
                                                i5++;
                                            }
                                        }
                                        i = i5;
                                    }
                                }
                                closeableArr = new Closeable[]{bufferedInputStream};
                            } catch (IOException e) {
                                e = e;
                                bufferedInputStream2 = bufferedInputStream;
                                e.printStackTrace();
                                closeableArr = new Closeable[]{bufferedInputStream2};
                                CloseUtils.closeIO(closeableArr);
                                return i;
                            }
                        } catch (IOException e2) {
                            e = e2;
                            bufferedInputStream2 = bufferedInputStream;
                            i = i2;
                            e.printStackTrace();
                            closeableArr = new Closeable[]{bufferedInputStream2};
                            CloseUtils.closeIO(closeableArr);
                            return i;
                        }
                    } catch (Throwable th) {
                        th = th;
                        CloseUtils.closeIO(bufferedInputStream);
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                    i = 1;
                }
            } catch (IOException e4) {
                e = e4;
                i = 1;
            }
            CloseUtils.closeIO(closeableArr);
            return i;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = bufferedInputStream2;
        }
    }

    public static String read2Str(String str) {
        File fileByPath = getFileByPath(str);
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileByPath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            byte[] bArr = new byte[1024];
            while (bufferedInputStream.available() != 0) {
                bufferedInputStream.read(bArr);
                sb.append(new String(bArr));
            }
            bufferedInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getDirSize(String str) {
        return getDirSize(getFileByPath(str));
    }

    public static String getDirSize(File file) {
        long dirLength = getDirLength(file);
        return dirLength == -1 ? "" : a(dirLength);
    }

    public static String getFileSize(String str) {
        return getFileSize(getFileByPath(str));
    }

    public static String getFileSize(File file) {
        long fileLength = getFileLength(file);
        return fileLength == -1 ? "" : a(fileLength);
    }

    public static long getDirLength(String str) {
        return getDirLength(getFileByPath(str));
    }

    public static long getDirLength(File file) {
        long length;
        if (isDir(file)) {
            long j = 0;
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        length = getDirLength(file2);
                    } else {
                        length = file2.length();
                    }
                    j += length;
                }
            }
            return j;
        }
        return -1L;
    }

    public static long getFileLength(String str) {
        return getFileLength(getFileByPath(str));
    }

    public static long getFileLength(File file) {
        if (isFile(file)) {
            return file.length();
        }
        return -1L;
    }

    public static String getFileMD5ToString(String str) {
        return getFileMD5ToString(a(str) ? null : new File(str));
    }

    public static byte[] getFileMD5(String str) {
        return getFileMD5(a(str) ? null : new File(str));
    }

    public static String getFileMD5ToString(File file) {
        int length;
        byte[] fileMD5 = getFileMD5(file);
        if (fileMD5 == null || (length = fileMD5.length) <= 0) {
            return null;
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = b;
            cArr[i] = cArr2[(fileMD5[i2] >>> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[fileMD5[i2] & 15];
        }
        return new String(cArr);
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0044: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:24:0x0044 */
    public static byte[] getFileMD5(File file) {
        DigestInputStream digestInputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        if (file == null) {
            return null;
        }
        try {
            try {
                digestInputStream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
                try {
                    do {
                    } while (digestInputStream.read(new byte[262144]) > 0);
                    byte[] digest = digestInputStream.getMessageDigest().digest();
                    CloseUtils.closeIO(digestInputStream);
                    return digest;
                } catch (IOException | NoSuchAlgorithmException e) {
                    e = e;
                    e.printStackTrace();
                    CloseUtils.closeIO(digestInputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                closeable2 = closeable;
                CloseUtils.closeIO(closeable2);
                throw th;
            }
        } catch (IOException | NoSuchAlgorithmException e2) {
            e = e2;
            digestInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            CloseUtils.closeIO(closeable2);
            throw th;
        }
    }

    public static String getDirName(File file) {
        if (file == null) {
            return null;
        }
        return getDirName(file.getPath());
    }

    public static String getDirName(String str) {
        if (a(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? "" : str.substring(0, lastIndexOf + 1);
    }

    public static String getFileName(File file) {
        if (file == null) {
            return null;
        }
        return getFileName(file.getPath());
    }

    public static String getFileName(String str) {
        int lastIndexOf;
        return (a(str) || (lastIndexOf = str.lastIndexOf(File.separator)) == -1) ? str : str.substring(lastIndexOf + 1);
    }

    public static String getFileNameNoExtension(File file) {
        if (file == null) {
            return null;
        }
        return getFileNameNoExtension(file.getPath());
    }

    public static String getFileNameNoExtension(String str) {
        if (a(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf2 == -1) {
            return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
        } else if (lastIndexOf == -1 || lastIndexOf2 > lastIndexOf) {
            return str.substring(lastIndexOf2 + 1);
        } else {
            return str.substring(lastIndexOf2 + 1, lastIndexOf);
        }
    }

    public static String getFileExtension(File file) {
        if (file == null) {
            return null;
        }
        return getFileExtension(file.getPath());
    }

    public static String getFileExtension(String str) {
        if (a(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf == -1 || lastIndexOf2 >= lastIndexOf) {
            return "";
        }
        return str.substring(lastIndexOf + 1);
    }

    @SuppressLint({"DefaultLocale"})
    private static String a(long j) {
        if (j < 0) {
            return "shouldn't be less than zero!";
        }
        return j < com.xiaopeng.lib.utils.FileUtils.SIZE_1KB ? String.format("%.3fB", Double.valueOf(j + 5.0E-4d)) : j < com.xiaopeng.lib.utils.FileUtils.SIZE_1MB ? String.format("%.3fKB", Double.valueOf((j / 1024.0d) + 5.0E-4d)) : j < com.xiaopeng.lib.utils.FileUtils.SIZE_1GB ? String.format("%.3fMB", Double.valueOf((j / 1048576.0d) + 5.0E-4d)) : String.format("%.3fGB", Double.valueOf((j / 1.073741824E9d) + 5.0E-4d));
    }

    private static boolean a(String str) {
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
