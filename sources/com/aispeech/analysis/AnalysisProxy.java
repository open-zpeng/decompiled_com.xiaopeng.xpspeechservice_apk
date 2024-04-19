package com.aispeech.analysis;

import android.text.TextUtils;
import com.aispeech.analysis.IAnalysisAudio;
import com.aispeech.analysis.IAnalysisConfig;
import com.aispeech.analysis.IAnalysisMonitor;
import com.aispeech.auth.a;
import com.aispeech.common.AITimer;
import com.aispeech.common.Log;
import com.aispeech.lite.c;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import java.util.TimerTask;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class AnalysisProxy {
    private static final String TAG = "DUILite-Upload";
    private static IAnalysisAudio preWkpAnalysis;
    private static IAnalysisAudio wkpAnalysis;
    private static boolean IAnalysisAudioNotImplement = false;
    private static boolean IAnalysisMonitorNotImplement = false;
    private static boolean IAnalysisConfigNotImplement = false;
    private static volatile AnalysisProxy mInstance = null;
    private static volatile IAnalysisMonitor analysisMonitor = null;
    private static volatile IAnalysisConfig analysisConfig = null;
    private UpdateUploadConfigTask mUpdateConfigTask = null;
    private volatile boolean configGetting = false;
    private long lastUpdateTime = 0;

    static /* synthetic */ IAnalysisConfig access$000() {
        return getAnalysisConfig();
    }

    public synchronized void updateConfig() {
        if (System.currentTimeMillis() - this.lastUpdateTime < 86400000) {
            return;
        }
        if (this.configGetting) {
            return;
        }
        this.configGetting = true;
        Log.d(TAG, "start updateConfig");
        if (this.mUpdateConfigTask != null) {
            this.mUpdateConfigTask.cancel();
            this.mUpdateConfigTask = null;
        }
        this.mUpdateConfigTask = new UpdateUploadConfigTask();
        AITimer.getInstance().schedule(this.mUpdateConfigTask, 0L);
    }

    /* loaded from: classes.dex */
    class UpdateUploadConfigTask extends TimerTask {
        UpdateUploadConfigTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            String str;
            JSONObject jSONObject;
            boolean z;
            boolean z2;
            JSONObject jSONObject2;
            JSONObject jSONObject3;
            boolean init;
            JSONObject jSONObject4 = null;
            try {
                str = AnalysisProxy.access$000().getUploadConfig(c.b(), 136, a.a().d().a(), a.a().d().f());
            } catch (Throwable th) {
                str = null;
            }
            Log.d(AnalysisProxy.TAG, "config: " + str);
            if (TextUtils.isEmpty(str)) {
                jSONObject = null;
                z = false;
            } else {
                try {
                    JSONObject jSONObject5 = new JSONObject(str);
                    z2 = jSONObject5.getBoolean("isCloud");
                    try {
                        jSONObject2 = jSONObject5.getJSONObject("data");
                        jSONObject3 = jSONObject2.getJSONObject("preWakeUp");
                    } catch (Exception e) {
                        e = e;
                        z = z2;
                        jSONObject = null;
                    }
                } catch (Exception e2) {
                    e = e2;
                    jSONObject = null;
                    z = false;
                }
                try {
                    JSONObject jSONObject6 = jSONObject2.getJSONObject("wakeUp");
                    if (jSONObject3 != null) {
                        jSONObject3.put("dBName", BuildInfoUtils.BID_WAN);
                        Log.d(AnalysisProxy.TAG, "preWakeUpJson is : " + jSONObject3.toString());
                    }
                    if (jSONObject6 != null) {
                        jSONObject6.put("dBName", "2");
                        Log.d(AnalysisProxy.TAG, "wakeUpJson is : " + jSONObject6.toString());
                    }
                    jSONObject = jSONObject6;
                    jSONObject4 = jSONObject3;
                    z = z2;
                } catch (Exception e3) {
                    e = e3;
                    jSONObject = null;
                    jSONObject4 = jSONObject3;
                    z = z2;
                    e.printStackTrace();
                    AnalysisProxy.this.configGetting = false;
                    init = AnalysisProxy.this.getAnalysisAudio(1).init(jSONObject4);
                    boolean init2 = AnalysisProxy.this.getAnalysisAudio(2).init(jSONObject);
                    if (!init) {
                    }
                    AnalysisProxy.this.lastUpdateTime = System.currentTimeMillis();
                    Log.d(AnalysisProxy.TAG, "initPreWakeup " + init + " initWakeup " + init2 + " isCloud " + z);
                }
            }
            AnalysisProxy.this.configGetting = false;
            init = AnalysisProxy.this.getAnalysisAudio(1).init(jSONObject4);
            boolean init22 = AnalysisProxy.this.getAnalysisAudio(2).init(jSONObject);
            if (!init || init22 || z) {
                AnalysisProxy.this.lastUpdateTime = System.currentTimeMillis();
            }
            Log.d(AnalysisProxy.TAG, "initPreWakeup " + init + " initWakeup " + init22 + " isCloud " + z);
        }
    }

    public static AnalysisProxy getInstance() {
        if (mInstance == null) {
            synchronized (AnalysisProxy.class) {
                if (mInstance == null) {
                    mInstance = new AnalysisProxy();
                }
            }
        }
        return mInstance;
    }

    public IAnalysisAudio getAnalysisAudio(int i) {
        if (!IAnalysisAudioNotImplement) {
            try {
                if (i == 2) {
                    if (wkpAnalysis == null) {
                        synchronized (this) {
                            if (wkpAnalysis == null) {
                                wkpAnalysis = new AudioUpload();
                            }
                        }
                    }
                    return wkpAnalysis;
                } else if (i == 1) {
                    if (preWkpAnalysis == null) {
                        synchronized (this) {
                            if (preWkpAnalysis == null) {
                                preWkpAnalysis = new AudioUpload();
                            }
                        }
                    }
                    return preWkpAnalysis;
                } else {
                    Log.d(TAG, "wkType invalid");
                }
            } catch (NoClassDefFoundError e) {
                IAnalysisAudioNotImplement = true;
            }
        }
        return IAnalysisAudio.AnalysisAudioEmpty.getInstance();
    }

    public IAnalysisMonitor getAnalysisMonitor() {
        if (analysisMonitor == null && !IAnalysisMonitorNotImplement) {
            synchronized (this) {
                if (analysisMonitor == null) {
                    try {
                        analysisMonitor = new MonitorUpload();
                    } catch (NoClassDefFoundError e) {
                        IAnalysisMonitorNotImplement = true;
                    }
                }
            }
        }
        return analysisMonitor != null ? analysisMonitor : IAnalysisMonitor.IAnalysisMonitorEmpty.getInstance();
    }

    private static IAnalysisConfig getAnalysisConfig() {
        if (analysisConfig == null && !IAnalysisConfigNotImplement) {
            synchronized (AnalysisProxy.class) {
                if (analysisConfig == null) {
                    try {
                        analysisConfig = new UploadConfig();
                    } catch (NoClassDefFoundError e) {
                        IAnalysisConfigNotImplement = true;
                    }
                }
            }
        }
        return analysisConfig != null ? analysisConfig : IAnalysisConfig.IAnalysisConfigEmpty.getInstance();
    }

    public void setPreWkpAnalysisAudioUpload(boolean z) {
        preWkpAnalysis.setAudioUpload(z);
    }

    public void setWkpAnalysisAudioUpload(boolean z) {
        wkpAnalysis.setAudioUpload(z);
    }
}
