package com.aispeech.upload.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.aispeech.upload.util.FileUtils;
import com.aispeech.upload.util.LogUtil;
import com.aispeech.upload.util.Tag;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class DataBaseUtil {
    private static String TAG = Tag.getTag("DataBase");
    private DatabaseHelper mHelper;
    private SQLiteDatabase mSqLiteDatabase;

    public DataBaseUtil(Context context, String str) {
        this.mHelper = new DatabaseHelper(context, str);
        createTable();
    }

    private void open() {
        try {
            this.mSqLiteDatabase = this.mHelper.getWritableDatabase();
        } catch (Exception e) {
        }
    }

    private void close() {
        try {
            this.mSqLiteDatabase.close();
        } catch (Exception e) {
        }
    }

    private void createTable() {
        open();
        try {
            try {
                this.mSqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tab_json (_id integer primary key autoincrement, _str TEXT, _type TEXT)");
                this.mSqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tab_error_num (_id integer primary key autoincrement, error_num integer)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            close();
        }
    }

    private synchronized void addJsonInfo(String str, String str2) {
        open();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_str", str);
            contentValues.put("_type", str2);
            this.mSqLiteDatabase.insert("tab_json", null, contentValues);
            contentValues.clear();
        } catch (Exception e) {
        } finally {
            close();
        }
    }

    public void addJsonInfo(String str) {
        addJsonInfo(str, "json");
    }

    public synchronized void deleJsonInfo(String str) {
        open();
        try {
            this.mSqLiteDatabase.delete("tab_json", "_id=?", new String[]{str});
            close();
        } catch (Exception e) {
            close();
        }
    }

    public void addFileInfo(String str) {
        addJsonInfo(str, "file");
    }

    public void deleFileInfo(String str) {
        deleJsonInfo(str);
    }

    public synchronized int getErrorNum() {
        int i;
        open();
        i = 0;
        try {
            Cursor query = this.mSqLiteDatabase.query("tab_error_num", new String[]{"_id", "error_num"}, "_id = ?", new String[]{BuildInfoUtils.BID_WAN}, null, null, null);
            if (query.getCount() > 0) {
                query.moveToFirst();
                i = query.getInt(query.getColumnIndex("error_num"));
            }
            query.close();
            close();
        } catch (Exception e) {
            close();
        } catch (Throwable th) {
            close();
            throw th;
        }
        return i;
    }

    private void addErrorNum() {
        Cursor query = this.mSqLiteDatabase.query("tab_error_num", new String[]{"_id", "error_num"}, "_id = ?", new String[]{BuildInfoUtils.BID_WAN}, null, null, null);
        if (query.getCount() > 0) {
            query.moveToFirst();
            ContentValues contentValues = new ContentValues();
            contentValues.put("error_num", Integer.valueOf(query.getInt(query.getColumnIndex("error_num")) + 1));
            this.mSqLiteDatabase.update("tab_error_num", contentValues, "_id=?", new String[]{BuildInfoUtils.BID_WAN});
        } else {
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("error_num", (Integer) 0);
            this.mSqLiteDatabase.insert("tab_error_num", null, contentValues2);
            contentValues2.clear();
        }
        query.close();
    }

    public synchronized void clearErrorNum() {
        open();
        try {
            Cursor query = this.mSqLiteDatabase.query("tab_error_num", new String[]{"_id", "error_num"}, "_id = ?", new String[]{BuildInfoUtils.BID_WAN}, null, null, null);
            int count = query.getCount();
            query.close();
            if (count > 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("error_num", (Integer) 0);
                this.mSqLiteDatabase.update("tab_error_num", contentValues, "_id=?", new String[]{BuildInfoUtils.BID_WAN});
                contentValues.clear();
            } else {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("error_num", (Integer) 0);
                this.mSqLiteDatabase.insert("tab_error_num", null, contentValues2);
                contentValues2.clear();
            }
            close();
        } catch (Exception e) {
            close();
        }
    }

    public synchronized List<UploadBean> getJsonInfoList() {
        ArrayList arrayList;
        open();
        arrayList = new ArrayList();
        try {
            Cursor query = this.mSqLiteDatabase.query("tab_json", new String[]{"_id", "_str", "_type"}, null, null, null, null, null);
            while (query.moveToNext()) {
                arrayList.add(new UploadBean(query.getString(query.getColumnIndex("_id")), query.getString(query.getColumnIndex("_str")), query.getString(query.getColumnIndex("_type"))));
            }
            query.close();
            close();
        } catch (Exception e) {
            close();
        }
        return arrayList;
    }

    public synchronized void checkMaxNum(int i) {
        open();
        try {
            Cursor query = this.mSqLiteDatabase.query("tab_json", new String[]{"_id", "_str", "_type"}, null, null, null, null, null);
            int count = query.getCount();
            if (count > i) {
                String str = TAG;
                LogUtil.e(str, "currentNum = " + count + ", maxCacheNums = " + i);
                LogUtil.e(TAG, "超过最大条数, 删除第一条...");
                query.moveToFirst();
                String string = query.getString(query.getColumnIndex("_id"));
                if (query.getString(query.getColumnIndex("_type")).equals("file")) {
                    FileUtils.deleteFile(query.getString(query.getColumnIndex("_str")));
                }
                this.mSqLiteDatabase.delete("tab_json", "_id=?", new String[]{string});
                addErrorNum();
            }
            query.close();
        } catch (Exception e) {
        } finally {
            close();
        }
    }

    public void release() {
        try {
            this.mHelper.close();
            this.mSqLiteDatabase.close();
        } catch (Exception e) {
        } catch (Throwable th) {
            this.mHelper = null;
            this.mSqLiteDatabase = null;
            throw th;
        }
        this.mHelper = null;
        this.mSqLiteDatabase = null;
    }
}
