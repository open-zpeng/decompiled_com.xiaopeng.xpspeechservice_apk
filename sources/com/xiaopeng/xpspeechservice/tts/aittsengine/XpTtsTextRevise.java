package com.xiaopeng.xpspeechservice.tts.aittsengine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.xpspeechservice.SpeechApp;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
/* loaded from: classes.dex */
public class XpTtsTextRevise {
    private static final String TAG = "XpTtsTextRevise";
    private static final String TEXT_TRANS_PATH = "tts/text_trans.json";
    private volatile boolean mIsDataLoaded;
    private List<TextTransBean> mTransList;

    /* loaded from: classes.dex */
    private static class SingleHolder {
        private static final XpTtsTextRevise INSTANCE = new XpTtsTextRevise();

        private SingleHolder() {
        }
    }

    public static XpTtsTextRevise getInstance() {
        return SingleHolder.INSTANCE;
    }

    private XpTtsTextRevise() {
        this.mIsDataLoaded = false;
        new Thread(new Runnable() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsTextRevise.1
            @Override // java.lang.Runnable
            public void run() {
                XpTtsTextRevise.this.loadData();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadData() {
        LogUtils.d(TAG, "loadData");
        try {
            InputStream is = SpeechApp.getContext().getResources().getAssets().open(TEXT_TRANS_PATH);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            try {
                StringBuilder data = new StringBuilder();
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    data.append(line);
                }
                parseConfig(data.toString());
                this.mIsDataLoaded = true;
                $closeResource(null, reader);
                if (is != null) {
                    $closeResource(null, is);
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    $closeResource(th, reader);
                    throw th2;
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "loadData fail", e);
        }
    }

    private static /* synthetic */ void $closeResource(Throwable x0, AutoCloseable x1) {
        if (x0 == null) {
            x1.close();
            return;
        }
        try {
            x1.close();
        } catch (Throwable th) {
            x0.addSuppressed(th);
        }
    }

    private void parseConfig(String jsonData) {
        Gson gson = new Gson();
        this.mTransList = (List) gson.fromJson(jsonData, new TypeToken<List<TextTransBean>>() { // from class: com.xiaopeng.xpspeechservice.tts.aittsengine.XpTtsTextRevise.2
        }.getType());
        for (TextTransBean trans : this.mTransList) {
            LogUtils.v(TAG, trans.toString());
        }
    }

    public String reviseText(String txt) {
        if (!this.mIsDataLoaded) {
            return txt;
        }
        for (TextTransBean trans : this.mTransList) {
            if (txt.indexOf(trans.getOrigin()) > -1) {
                String newTxt = txt.replace(trans.getOrigin(), trans.getTrans());
                LogUtils.d(TAG, "text changed to %s", newTxt);
                return newTxt;
            }
        }
        return txt;
    }
}
