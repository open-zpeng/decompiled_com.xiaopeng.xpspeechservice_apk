package com.aispeech.analysis;

import android.content.Context;
import android.text.TextUtils;
import com.aispeech.analysis.AnalysisParam;
import com.aispeech.auth.a;
import com.aispeech.common.AIConstant;
import com.aispeech.common.AITimer;
import com.aispeech.common.Log;
import com.aispeech.lite.c;
import com.aispeech.lite.d;
import com.aispeech.upload.EncodeCallback;
import com.aispeech.upload.FileBuilder;
import com.aispeech.upload.InitParams;
import com.aispeech.upload.ModelBuilder;
import com.aispeech.upload.UploadUtil;
import java.util.Map;
import java.util.TimerTask;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class AudioUpload implements IAnalysisAudio {
    protected static final int LOGID = 136;
    private static final int UPLOAD_AUDIO_DELAY_TIME_DEFAULT = 300000;
    private String TAG;
    private final String callerType;
    private final Context context;
    private final String deviceId;
    private long haveDelayTimeMillis;
    private long lastTimeMillis;
    private final int logID;
    private final boolean logcatDebugable;
    private final String logfilePath;
    private EncodeCallback mCallback;
    private String mDBName;
    private boolean mDeleteFileWhenNetError;
    private int mMaxCacheNum;
    private int mUploadDelayTime;
    private String mUploadMode;
    private UploadUtil mUploadUtil;
    private final Map<String, Object> map;
    private final String productId;
    private final String project;
    private RealWakeupUploadTask realWakeupUploadTask;
    private final String sdkVersion;
    private final String uploadUrl;

    public AudioUpload() {
        this(new AnalysisParam.Builder().setContext(c.b()).setUploadUrl(c.s).setLogID(LOGID).setProject("duilite_master_audio").setCallerType("duilite").setProductId(a.a().d().a()).setDeviceId(a.a().d().f()).setSdkVersion(AIConstant.SDK_VERSION).setLogcatDebugable(d.a).setLogfilePath(d.c).setUploadImmediately(false).setMaxCacheNum(100).create(), null);
    }

    public AudioUpload(AnalysisParam analysisParam, EncodeCallback encodeCallback) {
        this.TAG = "AudioUpload";
        this.mUploadMode = "forbidden";
        this.mMaxCacheNum = 100;
        this.mUploadDelayTime = UPLOAD_AUDIO_DELAY_TIME_DEFAULT;
        this.mDeleteFileWhenNetError = false;
        this.realWakeupUploadTask = null;
        this.lastTimeMillis = 0L;
        this.haveDelayTimeMillis = 0L;
        this.mCallback = encodeCallback;
        this.context = analysisParam.getContext();
        this.uploadUrl = analysisParam.getUploadUrl();
        this.logID = analysisParam.getLogID();
        this.project = analysisParam.getProject();
        this.callerType = analysisParam.getCallerType();
        this.productId = analysisParam.getProductId();
        this.deviceId = analysisParam.getDeviceId();
        this.sdkVersion = analysisParam.getSdkVersion();
        this.logcatDebugable = analysisParam.isLogcatDebugable();
        this.logfilePath = analysisParam.getLogfilePath();
        this.map = analysisParam.getMap();
        String str = this.TAG;
        Log.d(str, "AnalysisParam " + analysisParam.toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.aispeech.analysis.IAnalysisAudio
    public synchronized boolean init(JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject == null) {
            return false;
        }
        String optString = jSONObject.optString("uploadMode");
        String optString2 = jSONObject.optString("dBName");
        int i = 100;
        int i2 = UPLOAD_AUDIO_DELAY_TIME_DEFAULT;
        char c = 65535;
        switch (optString.hashCode()) {
            case -1880493590:
                if (optString.equals("full-retry")) {
                    c = 0;
                    break;
                }
                break;
            case -909675094:
                if (optString.equals("sample")) {
                    c = 2;
                    break;
                }
                break;
            case 3154575:
                if (optString.equals("full")) {
                    c = 1;
                    break;
                }
                break;
            case 1503566841:
                if (optString.equals("forbidden")) {
                    c = 3;
                    break;
                }
                break;
        }
        if (c != 0 && c != 1) {
            if (c == 2) {
                try {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("param");
                    if (jSONObject2 != null) {
                        i = jSONObject2.optInt("number");
                        i2 = jSONObject2.getInt("period");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (c == 3) {
                i = 0;
            } else {
                Log.d(this.TAG, "uploadMode is error: " + optString);
                return false;
            }
        }
        boolean z2 = TextUtils.equals(optString, this.mUploadMode) && TextUtils.equals(optString2, this.mDBName);
        if (z2) {
            z2 = this.mMaxCacheNum == i && this.mUploadDelayTime == i2;
        }
        if (z2) {
            Log.d(this.TAG, "init same: " + this.mUploadMode);
            return false;
        }
        this.mUploadMode = optString;
        this.mDBName = optString2;
        this.mMaxCacheNum = i;
        this.mUploadDelayTime = i2;
        if ("full".equals(this.mUploadMode) || "sample".equals(this.mUploadMode)) {
            z = true;
        }
        this.mDeleteFileWhenNetError = z;
        this.TAG = "AudioUpload-" + this.mDBName;
        Log.d(this.TAG, "upload audio mode is: " + this.mUploadMode + " MaxCacheNum " + this.mMaxCacheNum + " UploadDelayTime " + this.mUploadDelayTime);
        if (this.mUploadUtil != null) {
            this.mUploadUtil.release();
            this.mUploadUtil = null;
        }
        if (isUploadEnable()) {
            init();
        }
        return true;
    }

    private void init() {
        String str = this.TAG;
        Log.d(str, "maxCacheNum is: " + this.mMaxCacheNum);
        String str2 = this.TAG;
        Log.d(str2, "dbName is " + this.mDBName);
        InitParams maxCacheNum = new InitParams(this.logID).setProject(this.project).setProductId(this.productId).setDeviceId(this.deviceId).setVersion(this.sdkVersion).setCallerType(this.callerType).setCallerVersion(this.sdkVersion).setDBName(this.mDBName).setHttpUrl(this.uploadUrl).setDeleFileWhenNetError(this.mDeleteFileWhenNetError).setMaxCacheNum(this.mMaxCacheNum << 1);
        if (this.logcatDebugable) {
            maxCacheNum.openLog(this.logfilePath);
        }
        Map<String, Object> map = this.map;
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : this.map.entrySet()) {
                maxCacheNum.addEntry(entry.getKey(), entry.getValue());
            }
        }
        String str3 = this.TAG;
        Log.d(str3, "init upload : " + maxCacheNum.toString());
        this.mUploadUtil = UploadUtil.init(this.context, maxCacheNum, this.mCallback);
    }

    @Override // com.aispeech.analysis.IAnalysisAudio
    public String getAudioMode() {
        return this.mUploadMode;
    }

    @Override // com.aispeech.analysis.IAnalysisAudio
    public boolean isUploadEnable() {
        return !TextUtils.equals(this.mUploadMode, "forbidden");
    }

    @Override // com.aispeech.analysis.IAnalysisAudio
    public void setAudioUpload(boolean z) {
        if (z) {
            this.mUploadMode = "allow";
        } else {
            this.mUploadMode = "forbidden";
        }
        String str = this.TAG;
        Log.e(str, "mUploadMode: " + this.mUploadMode);
    }

    private void cacheData(ModelBuilder modelBuilder) {
        UploadUtil uploadUtil = this.mUploadUtil;
        if (uploadUtil != null) {
            uploadUtil.cacheData(modelBuilder);
        }
    }

    @Override // com.aispeech.analysis.IAnalysisAudio
    public synchronized void cacheData(String str, String str2, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, Map<String, Object> map) {
        ModelBuilder create = ModelBuilder.create();
        if (str != null) {
            create.addTag(str);
        }
        if (str2 != null) {
            create.addLevel(str2);
        }
        if (str3 != null) {
            create.addModule(str3);
        }
        if (str4 != null) {
            create.addRecordId(str4);
        }
        if (jSONObject != null) {
            create.addInput(jSONObject);
        }
        if (jSONObject2 != null) {
            create.addOutput(jSONObject2);
        }
        if (map != null && !map.isEmpty()) {
            String str5 = this.TAG;
            Log.d(str5, "msgObject: " + map.toString());
            for (String str6 : map.keySet()) {
                if (TextUtils.equals(str6, "upload_entry")) {
                    Map map2 = (Map) map.get(str6);
                    for (String str7 : map2.keySet()) {
                        create.addEntry(str7, map2.get(str7));
                    }
                } else {
                    create.addMsgObject(str6, map.get(str6));
                }
            }
        }
        cacheData(create.build());
    }

    @Override // com.aispeech.analysis.IAnalysisAudio
    public synchronized void cacheFile(String str) {
        if (this.mUploadUtil != null) {
            this.mUploadUtil.cacheFile(str);
        }
    }

    @Override // com.aispeech.analysis.IAnalysisAudio
    public synchronized void cacheFileBuilder(FileBuilder fileBuilder) {
        if (this.mUploadUtil != null) {
            this.mUploadUtil.cacheFile(fileBuilder);
        }
    }

    @Override // com.aispeech.analysis.IAnalysisAudio
    public synchronized void stop() {
        if (this.mUploadUtil != null) {
            this.mUploadUtil.stop();
        }
    }

    @Override // com.aispeech.analysis.IAnalysisAudio
    public synchronized void release() {
        if (this.mUploadUtil != null) {
            this.mUploadUtil.release();
            this.mUploadUtil = null;
        }
    }

    @Override // com.aispeech.analysis.IAnalysisAudio
    public synchronized void start() {
        if (this.mUploadUtil != null) {
            startRealWakeupUploadTask();
        }
    }

    @Override // com.aispeech.analysis.IAnalysisAudio
    public void startUploadImmediately() {
        if (this.mUploadUtil != null) {
            RealWakeupUploadTask realWakeupUploadTask = this.realWakeupUploadTask;
            if (realWakeupUploadTask != null) {
                realWakeupUploadTask.cancel();
                this.realWakeupUploadTask = null;
            }
            this.mUploadUtil.start();
        }
    }

    @Override // com.aispeech.analysis.IAnalysisAudio
    public int getUploadDelayTime() {
        return this.mUploadDelayTime;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRealWakeupUploadTask() {
        if (this.mUploadDelayTime <= 0) {
            UploadUtil uploadUtil = this.mUploadUtil;
            if (uploadUtil != null) {
                uploadUtil.start();
            }
            Log.d(this.TAG, "startRealWakeupUploadTask mUploadUtil.start()");
            return;
        }
        this.lastTimeMillis = System.currentTimeMillis();
        String str = this.TAG;
        Log.d(str, "startRealWakeupUploadTask lastTimeMillis " + this.lastTimeMillis);
        if (this.realWakeupUploadTask == null) {
            this.realWakeupUploadTask = new RealWakeupUploadTask();
            int i = this.mUploadDelayTime;
            long j = this.haveDelayTimeMillis;
            long j2 = ((long) i) - j > 0 ? i - j : 0L;
            AITimer.getInstance().schedule(this.realWakeupUploadTask, j2);
            String str2 = this.TAG;
            Log.d(str2, "AITimer schedule realWakeupUploadTask realDaley " + j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class RealWakeupUploadTask extends TimerTask {
        private RealWakeupUploadTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            AudioUpload.this.realWakeupUploadTask = null;
            long currentTimeMillis = System.currentTimeMillis() - AudioUpload.this.lastTimeMillis;
            AudioUpload.this.haveDelayTimeMillis += currentTimeMillis;
            long j = AudioUpload.this.mUploadDelayTime > 60000 ? AudioUpload.this.mUploadDelayTime / 60 : AudioUpload.this.mUploadDelayTime > 10000 ? 600 : 200;
            Log.d(AudioUpload.this.TAG, "haveDelayTimeMillis " + AudioUpload.this.haveDelayTimeMillis + " uploadDelayTime " + AudioUpload.this.mUploadDelayTime + " rang " + j);
            if (AudioUpload.this.haveDelayTimeMillis < AudioUpload.this.mUploadDelayTime - j) {
                Log.d(AudioUpload.this.TAG, "restart upload task");
                AudioUpload.this.haveDelayTimeMillis = currentTimeMillis;
                AudioUpload.this.startRealWakeupUploadTask();
                return;
            }
            AudioUpload.this.haveDelayTimeMillis = 0L;
            AudioUpload.this.lastTimeMillis = System.currentTimeMillis();
            if (AudioUpload.this.mUploadUtil != null) {
                AudioUpload.this.mUploadUtil.start();
                Log.d(AudioUpload.this.TAG, "mUploadUtil start");
                return;
            }
            Log.d(AudioUpload.this.TAG, "mUploadUtil not start");
        }
    }
}
