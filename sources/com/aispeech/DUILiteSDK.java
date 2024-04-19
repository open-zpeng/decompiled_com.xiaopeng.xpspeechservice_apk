package com.aispeech;

import android.content.Context;
import android.text.TextUtils;
import com.aispeech.analysis.AnalysisProxy;
import com.aispeech.auth.a.a;
import com.aispeech.auth.f;
import com.aispeech.common.AIConstant;
import com.aispeech.common.AuthUtil;
import com.aispeech.common.FileUtils;
import com.aispeech.common.Log;
import com.aispeech.common.URLUtils;
import com.aispeech.export.config.AuthConfig;
import com.aispeech.export.config.EchoConfig;
import com.aispeech.export.config.RecorderConfig;
import com.aispeech.export.config.UploadConfig;
import com.aispeech.kernel.Utils;
import com.aispeech.lite.c;
import com.aispeech.lite.d;
import java.util.HashMap;
import java.util.UUID;
/* loaded from: classes.dex */
public class DUILiteSDK {
    private static volatile boolean a = false;

    /* loaded from: classes.dex */
    public interface InitListener {
        void error(String str, String str2);

        void success();
    }

    static /* synthetic */ boolean a() {
        a = false;
        return false;
    }

    public static void init(Context context, DUILiteConfig dUILiteConfig, InitListener initListener) {
        boolean z;
        if (a) {
            Log.w("DUILiteSDK", " ===SDK IS INITING ====");
            return;
        }
        boolean z2 = true;
        a = true;
        if (dUILiteConfig == null) {
            throw new IllegalArgumentException("must set DUILiteConfig");
        }
        EchoConfig echoConfig = dUILiteConfig.getEchoConfig();
        RecorderConfig recorderConfig = dUILiteConfig.getRecorderConfig();
        AuthConfig authConfig = dUILiteConfig.getAuthConfig();
        UploadConfig uploadConfig = dUILiteConfig.getUploadConfig();
        c.b = echoConfig != null ? echoConfig.getAecResource() : null;
        c.d = echoConfig != null ? echoConfig.getMicNumber() : 1;
        c.c = echoConfig != null ? echoConfig.getChannels() : 2;
        c.e = echoConfig != null ? echoConfig.getRecChannel() : 1;
        c.b(echoConfig != null ? echoConfig.getSavedDirPath() : null);
        if (echoConfig != null && echoConfig.isMonitorEnable()) {
            z = true;
        } else {
            z = false;
        }
        c.f = z;
        c.g = echoConfig != null ? echoConfig.getMonitorPeriod() : 200;
        c.i = recorderConfig != null ? recorderConfig.getAudioSource() : 1;
        c.j = recorderConfig != null ? recorderConfig.recorderType : 0;
        c.h = recorderConfig != null ? recorderConfig.intervalTime : 100;
        c.k = authConfig != null ? authConfig.getAuthTimeout() : 5000;
        c.m = authConfig != null ? authConfig.getDeviceProfileDirPath() : null;
        c.l = authConfig != null ? authConfig.getAuthServer() : "https://auth.dui.ai";
        c.n = authConfig != null ? authConfig.getCustomDeviceId() : null;
        c.p = authConfig != null ? authConfig.getCustomDeviceName() : null;
        c.o = authConfig != null ? authConfig.getLicenceId() : null;
        c.q = authConfig != null ? authConfig.getType() : null;
        if (uploadConfig == null || !uploadConfig.isUploadEnable()) {
            z2 = false;
        }
        c.r = z2;
        c.s = uploadConfig != null ? uploadConfig.getUploadUrl() : "https://log.aispeech.com";
        if (uploadConfig != null) {
            uploadConfig.getCacheUploadMaxNumber();
        }
        if (uploadConfig != null) {
            uploadConfig.getUploadAudioLevel();
        }
        if (uploadConfig != null) {
            uploadConfig.getUploadAudioDelayTime();
        }
        if (uploadConfig != null) {
            uploadConfig.getUploadAudioPath();
        }
        c.t = dUILiteConfig.getTtsCacheDir();
        c.u = dUILiteConfig.getThreadAffinity();
        c.a = dUILiteConfig.getCallbackInThread();
        c.a(context);
        HashMap hashMap = new HashMap();
        hashMap.put("sdkName", "duilite_android");
        hashMap.put("sdkVersion", getSdkVersion());
        com.aispeech.auth.a.a().a(context, new a.AnonymousClass1().a(dUILiteConfig.getProductId()).b(dUILiteConfig.getProductKey()).c(dUILiteConfig.getProductSecret()).d(dUILiteConfig.getApiKey()).e(dUILiteConfig.getServerApiKey()).a(c.q).h(c.m).a(c.k).j(c.p).f(c.l).g(c.n).i(c.o).a(hashMap).a(), new a(initListener, (byte) 0));
        com.aispeech.auth.a.a().b();
        Log.d("DUILiteSDK", "SdkVersion " + getSdkVersion() + ", CoreVersion " + getCoreVersion());
    }

    /* loaded from: classes.dex */
    static class a implements InitListener {
        private InitListener a;

        /* synthetic */ a(InitListener initListener, byte b) {
            this(initListener);
        }

        private a(InitListener initListener) {
            this.a = initListener;
        }

        @Override // com.aispeech.DUILiteSDK.InitListener
        public final void success() {
            DUILiteSDK.a();
            DUILiteSDK.b();
            c.a(com.aispeech.auth.a.a().d());
            DUILiteSDK.c();
            InitListener initListener = this.a;
            if (initListener != null) {
                initListener.success();
                this.a = null;
            }
        }

        @Override // com.aispeech.DUILiteSDK.InitListener
        public final void error(String str, String str2) {
            DUILiteSDK.a();
            c.a(com.aispeech.auth.a.a().d());
            AuthUtil.logAuthFailureInfo(str, str2, c.b());
            InitListener initListener = this.a;
            if (initListener != null) {
                initListener.error(str, str2);
                this.a = null;
            }
        }
    }

    public static void openLog(String str) {
        if (!TextUtils.isEmpty(str)) {
            d.c = str;
        }
        c.a(str);
    }

    public static void openLog() {
        openLog("");
    }

    public static boolean isAuthorized(Context context) {
        c.a(context);
        return com.aispeech.auth.a.a().c();
    }

    public static f getAuthState(String str) {
        return com.aispeech.auth.a.a().d().a(str);
    }

    public static String getDeviceName() {
        return a ? "" : com.aispeech.auth.a.a().d().f();
    }

    public static String getDeviceID() {
        return a ? "" : com.aispeech.auth.a.a().d().h();
    }

    public static String getSdkVersion() {
        return AIConstant.SDK_VERSION;
    }

    public static String getCoreVersion() {
        if (Utils.isUtilsSoValid()) {
            return Utils.get_version();
        }
        return "get error because of unload libduiutils.so";
    }

    public static String getAuthParams(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(UUID.randomUUID());
        String sb4 = sb3.toString();
        String signature = AuthUtil.getSignature(getDeviceName() + sb4 + str + sb2, com.aispeech.auth.a.a().d().g());
        return "timestamp=" + sb2 + "&nonce=" + sb4 + "&sig=" + signature;
    }

    public static void setGlobalAudioSavePath(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("DUILiteSDK", "setGlobalAudioSavePath error,path is empty");
            return;
        }
        if (!str.endsWith(URLUtils.URL_PATH_SEPERATOR)) {
            str = str + URLUtils.URL_PATH_SEPERATOR;
        }
        FileUtils.createOrExistsDir(str);
        d.d = str;
    }

    public static void setGlobalAudioSaveEnable(boolean z) {
        d.e = z;
        if (!z) {
            d.d = "";
        }
    }

    public static void setGlobalLogLevel(int i) {
        Log.LOG_LEVEL = i;
    }

    public static void setPreWakeUpAudioUpload(boolean z) {
        AnalysisProxy.getInstance().setPreWkpAnalysisAudioUpload(z);
    }

    public static void setWakeUpAudioUpload(boolean z) {
        AnalysisProxy.getInstance().setWkpAnalysisAudioUpload(z);
    }

    static /* synthetic */ void b() {
        if (c.r) {
            AnalysisProxy.getInstance().getAnalysisMonitor().enableUpload();
        } else {
            AnalysisProxy.getInstance().getAnalysisMonitor().disableUpload();
        }
    }

    static /* synthetic */ void c() {
        HashMap hashMap = new HashMap();
        hashMap.put("mode", "lite");
        hashMap.put("module", "deviceInfo");
        AnalysisProxy.getInstance().getAnalysisMonitor().cacheData("duilite_deviceInfo", "info", "deviceInfo", null, AuthUtil.getDeviceInfo(c.b()), null, hashMap);
    }
}
