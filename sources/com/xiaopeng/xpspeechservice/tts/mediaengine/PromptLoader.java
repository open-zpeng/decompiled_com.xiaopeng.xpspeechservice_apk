package com.xiaopeng.xpspeechservice.tts.mediaengine;

import android.os.SystemClock;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class PromptLoader {
    private static final String TAG = "PromptLoader";
    private static final String[] TTS_PATHS = {"/system/etc/speech/xp_tts_ai.dat", "/mnt/tts/xp_tts_ai.dat", "/sdcard/tts/xp_tts_ai.dat"};
    private volatile boolean mIsLoaded;
    private List<PromptConfig> mPromptConfigs;

    private PromptLoader() {
        this.mPromptConfigs = new ArrayList();
        this.mIsLoaded = false;
        loadConfig();
    }

    /* loaded from: classes.dex */
    private static class SingleHolder {
        private static PromptLoader instance = new PromptLoader();

        private SingleHolder() {
        }
    }

    public static PromptLoader getInstance() {
        return SingleHolder.instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getTtsPath() {
        String[] strArr;
        for (String path : TTS_PATHS) {
            File file = new File(path);
            if (file.exists()) {
                LogUtils.v(TAG, "getTtsPath " + path);
                return path;
            }
        }
        LogUtils.w(TAG, "tts prompt data not exists");
        return null;
    }

    private void loadConfig() {
        LogUtils.d(TAG, "loadConfig");
        ThreadUtils.postNormal(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.mediaengine.PromptLoader.1
            @Override // java.lang.Runnable
            public void run() {
                String path = PromptLoader.this.getTtsPath();
                if (path != null) {
                    PromptLoader.this.loadConfig(path);
                    PromptLoader.this.mIsLoaded = true;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:7:0x0089 -> B:23:0x009e). Please submit an issue!!! */
    public void loadConfig(String path) {
        InputStream in = null;
        try {
            try {
                try {
                    long startTime = SystemClock.uptimeMillis();
                    in = new FileInputStream(path);
                    byte[] headLengthArr = new byte[4];
                    in.read(headLengthArr, 0, 4);
                    int headLength = ByteUtils.bytes2Int(headLengthArr);
                    PromptConfig PromptConfig = new PromptConfig();
                    PromptConfig.path = path;
                    PromptConfig.headLength = headLength;
                    LogUtils.v(TAG, "loadConfig head length " + headLength);
                    byte[] headConfigByteArr = new byte[headLength];
                    in.read(headConfigByteArr);
                    String json = new String(headConfigByteArr, StandardCharsets.UTF_8);
                    Gson gson = new Gson();
                    HashMap<String, FileLocationBean> fileLocMap = (HashMap) gson.fromJson(json, new TypeToken<HashMap<String, FileLocationBean>>() { // from class: com.xiaopeng.xpspeechservice.tts.mediaengine.PromptLoader.2
                    }.getType());
                    PromptConfig.map = fileLocMap;
                    this.mPromptConfigs.add(PromptConfig);
                    long duration = SystemClock.uptimeMillis() - startTime;
                    LogUtils.d(TAG, "loadConfig size %d takes %dms", Integer.valueOf(fileLocMap.size()), Long.valueOf(duration));
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                LogUtils.e(TAG, "loadConfig new fail", e2);
                if (in != null) {
                    in.close();
                }
            }
        } catch (Throwable th) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    public byte[] getBytes(String text) {
        if (!this.mIsLoaded || this.mPromptConfigs.isEmpty()) {
            LogUtils.v(TAG, "prompt data not loaded");
            return null;
        }
        String text2 = text.replaceAll("[\\s\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "").toLowerCase();
        PromptConfig config = null;
        FileLocationBean fileLocation = null;
        Iterator<PromptConfig> it = this.mPromptConfigs.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PromptConfig PromptConfig = it.next();
            if (PromptConfig.map != null) {
                FileLocationBean fileLocation2 = PromptConfig.map.get(text2);
                fileLocation = fileLocation2;
                if (fileLocation != null) {
                    config = PromptConfig;
                    break;
                }
            }
        }
        if (config == null) {
            LogUtils.v(TAG, "not found text");
            return null;
        }
        LogUtils.v(TAG, "found text");
        long pos = config.headLength + 4 + fileLocation.pos;
        long length = fileLocation.length;
        File xpConfigFile = new File(config.path);
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(xpConfigFile, "r");
                randomAccessFile.seek(pos);
                byte[] buffer = new byte[(int) length];
                randomAccessFile.read(buffer);
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return buffer;
            } catch (FileNotFoundException e2) {
                LogUtils.e(TAG, "getBytes fail", e2);
                try {
                    randomAccessFile.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return null;
            } catch (IOException e4) {
                LogUtils.e(TAG, "text " + text2, e4);
                try {
                    randomAccessFile.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return null;
            }
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            throw th;
        }
    }
}
