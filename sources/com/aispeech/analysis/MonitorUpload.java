package com.aispeech.analysis;

import android.content.Context;
import android.text.TextUtils;
import com.aispeech.analysis.AnalysisParam;
import com.aispeech.auth.a;
import com.aispeech.common.AIConstant;
import com.aispeech.common.Log;
import com.aispeech.lite.c;
import com.aispeech.lite.d;
import com.aispeech.upload.EncodeCallback;
import com.aispeech.upload.FileBuilder;
import com.aispeech.upload.InitParams;
import com.aispeech.upload.ModelBuilder;
import com.aispeech.upload.UploadUtil;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class MonitorUpload implements IAnalysisMonitor {
    private static final int LOGID = 129;
    private static final String TAG = "MonitorUpload";
    private final String callerType;
    private final Context context;
    private final String deviceId;
    private boolean enabled;
    private final int logID;
    private final boolean logcatDebugable;
    private final String logfilePath;
    private EncodeCallback mCallback;
    public UploadUtil mUploadUtil;
    private final Map<String, Object> map;
    private final int maxCacheNum;
    private final String productId;
    private final String project;
    private final String sdkVersion;
    private final boolean uploadImmediately;
    private final String uploadUrl;

    public MonitorUpload() {
        this(new AnalysisParam.Builder().setContext(c.b()).setUploadUrl(c.s).setLogID(LOGID).setProject("duilite_master_monitor").setCallerType("duilite").setProductId(a.a().d().a()).setDeviceId(a.a().d().f()).setSdkVersion(AIConstant.SDK_VERSION).setLogcatDebugable(d.a).setLogfilePath(d.c).setUploadImmediately(true).setMaxCacheNum(100).create(), null);
    }

    public MonitorUpload(AnalysisParam analysisParam, EncodeCallback encodeCallback) {
        this.enabled = true;
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
        this.uploadImmediately = analysisParam.isUploadImmediately();
        this.maxCacheNum = analysisParam.getMaxCacheNum();
        this.map = analysisParam.getMap();
        Log.d(TAG, "AnalysisParam " + analysisParam.toString());
    }

    @Override // com.aispeech.analysis.IAnalysisMonitor
    public synchronized void startUploadImmediately() {
        if (this.mUploadUtil != null) {
            this.mUploadUtil.start();
        }
    }

    @Override // com.aispeech.analysis.IAnalysisMonitor
    public synchronized void stop() {
        if (this.mUploadUtil != null) {
            this.mUploadUtil.stop();
        }
    }

    private void init() {
        InitParams maxCacheNum = new InitParams(this.logID).setProject(this.project).setProductId(this.productId).setDeviceId(this.deviceId).setVersion(this.sdkVersion).setCallerType(this.callerType).setCallerVersion(this.sdkVersion).setHttpUrl(this.uploadUrl).setMaxCacheNum(this.maxCacheNum);
        if (this.uploadImmediately) {
            maxCacheNum.setUploadImmediately();
        }
        if (this.logcatDebugable) {
            maxCacheNum.openLog(this.logfilePath);
        }
        Map<String, Object> map = this.map;
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : this.map.entrySet()) {
                maxCacheNum.addEntry(entry.getKey(), entry.getValue());
            }
        }
        Log.d(TAG, "init upload : " + maxCacheNum.toString());
        UploadUtil uploadUtil = this.mUploadUtil;
        if (uploadUtil != null) {
            uploadUtil.release();
            this.mUploadUtil = null;
        }
        this.mUploadUtil = UploadUtil.init(this.context, maxCacheNum, this.mCallback);
    }

    @Override // com.aispeech.analysis.IAnalysisMonitor
    public synchronized void disableUpload() {
        this.enabled = false;
        if (this.mUploadUtil != null) {
            this.mUploadUtil.release();
            this.mUploadUtil = null;
        }
    }

    @Override // com.aispeech.analysis.IAnalysisMonitor
    public synchronized void enableUpload() {
        this.enabled = true;
        init();
    }

    private void cacheData(ModelBuilder modelBuilder) {
        UploadUtil uploadUtil = this.mUploadUtil;
        if (uploadUtil != null && this.enabled) {
            uploadUtil.cacheData(modelBuilder);
        }
    }

    @Override // com.aispeech.analysis.IAnalysisMonitor
    public void cacheData(String str, String str2, String str3, JSONObject jSONObject) {
        cacheData(str, str2, str3, null, jSONObject, null, null);
    }

    @Override // com.aispeech.analysis.IAnalysisMonitor
    public void cacheData(String str, String str2, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, Map<String, Object> map) {
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
            Log.d(TAG, "msgObject: " + map.toString());
            for (String str5 : map.keySet()) {
                if (TextUtils.equals(str5, "upload_entry")) {
                    Map map2 = (Map) map.get(str5);
                    for (String str6 : map2.keySet()) {
                        create.addEntry(str6, map2.get(str6));
                    }
                } else {
                    create.addMsgObject(str5, map.get(str5));
                }
            }
        }
        cacheData(create.build());
    }

    @Override // com.aispeech.analysis.IAnalysisMonitor
    public synchronized void release() {
        if (this.mUploadUtil != null) {
            this.mUploadUtil.release();
            this.mUploadUtil = null;
        }
    }

    @Override // com.aispeech.analysis.IAnalysisMonitor
    public void cacheFile(String str) {
        UploadUtil uploadUtil = this.mUploadUtil;
        if (uploadUtil != null && this.enabled) {
            uploadUtil.cacheFile(str);
        }
    }

    @Override // com.aispeech.analysis.IAnalysisMonitor
    public void cacheFileBuilder(FileBuilder fileBuilder) {
        UploadUtil uploadUtil = this.mUploadUtil;
        if (uploadUtil != null && this.enabled) {
            uploadUtil.cacheFile(fileBuilder);
        }
    }
}
