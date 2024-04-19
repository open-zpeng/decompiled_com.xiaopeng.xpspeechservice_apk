package com.xiaopeng.xpspeechservice.tts.mediaengine;

import android.net.Uri;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.aispeech.common.URLUtils;
import com.danikula.videocache.HttpProxyCacheServer;
import com.xiaopeng.xpspeechservice.SpeechApp;
import com.xiaopeng.xpspeechservice.tts.IEngine;
import com.xiaopeng.xpspeechservice.tts.IEngineCallback;
import com.xiaopeng.xpspeechservice.utils.LogUtils;
import java.io.File;
/* loaded from: classes.dex */
public class XpMediaEngine implements IEngine {
    private static final long CACHE_MAX_SIZE = 134217728;
    private static final File CACHE_PATH = SpeechApp.getContext().getCacheDir();
    private static final int MSG_INIT = 101;
    private static final String TAG = "XpMediaEngine";
    private PromptLoader mPromptFile;
    private HttpProxyCacheServer mProxyCacheServer;
    private XpMediaController mXpMediaController;

    /* loaded from: classes.dex */
    private static class SingleHolder {
        private static final XpMediaEngine INSTANCE = new XpMediaEngine();

        private SingleHolder() {
        }
    }

    public static XpMediaEngine getInstance() {
        return SingleHolder.INSTANCE;
    }

    private XpMediaEngine() {
        LogUtils.i(TAG, "construct");
        this.mXpMediaController = new XpMediaController();
    }

    public void initEngine() {
        LogUtils.i(TAG, "initEngine");
        this.mXpMediaController.initEngine();
        this.mPromptFile = PromptLoader.getInstance();
        initHttpProxyCacheServer();
    }

    @Override // com.xiaopeng.xpspeechservice.tts.IEngine
    public int speak(Bundle params, IEngineCallback cb) {
        try {
            String txt = params.getString("txt");
            if (isTxtUrl(txt)) {
                LogUtils.v(TAG, "isCached " + this.mProxyCacheServer.isCached(txt));
                String url = this.mProxyCacheServer.getProxyUrl(txt);
                LogUtils.v(TAG, "getProxyUrl " + url);
                Uri uri = Uri.parse(url);
                this.mXpMediaController.start(SourceType.SOURCE_URI, params, uri, cb);
                return 0;
            }
            boolean isPromptEnable = SystemProperties.getBoolean("sys.xiaopeng.tts.prompt_enable", true);
            if (!isPromptEnable) {
                LogUtils.v(TAG, "prompt disabled");
                return -1;
            }
            byte[] buffer = this.mPromptFile.getBytes(txt);
            if (buffer != null) {
                this.mXpMediaController.start(SourceType.SOURCE_BUFFER, params, buffer, cb);
                return 0;
            }
            return -1;
        } catch (Exception e) {
            LogUtils.e(TAG, "speak fail " + e);
            return -1;
        }
    }

    @Override // com.xiaopeng.xpspeechservice.tts.IEngine
    public void stop() {
        LogUtils.i(TAG, "stop");
        this.mXpMediaController.stop();
    }

    public void shutdown() {
        LogUtils.i(TAG, "shutdown");
        this.mXpMediaController.shutdown();
        this.mPromptFile = null;
        this.mProxyCacheServer = null;
    }

    private void initHttpProxyCacheServer() {
        this.mProxyCacheServer = new HttpProxyCacheServer.Builder(SpeechApp.getContext()).maxCacheSize(CACHE_MAX_SIZE).cacheDirectory(new File(CACHE_PATH, "audio-cache")).build();
    }

    private boolean isTxtUrl(String text) {
        return !TextUtils.isEmpty(text) && (text.startsWith("http://") || text.startsWith(URLUtils.HTTPS_PROTOCOL_HEAD));
    }
}
