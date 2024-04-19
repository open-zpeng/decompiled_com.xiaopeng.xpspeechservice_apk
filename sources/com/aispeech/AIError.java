package com.aispeech;

import android.os.Parcel;
import android.os.Parcelable;
import com.aispeech.common.JSONUtil;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class AIError extends Exception implements Parcelable {
    public static final Parcelable.Creator<AIError> CREATOR = new Parcelable.Creator<AIError>() { // from class: com.aispeech.AIError.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ AIError[] newArray(int i) {
            return new AIError[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ AIError createFromParcel(Parcel parcel) {
            return new AIError(parcel, (byte) 0);
        }
    };
    public static final int ERR_AI_ENGINE = 70902;
    public static final int ERR_ASR_PLUS_SERVER_ERR = 72151;
    public static final int ERR_CONNECT_TIMEOUT = 70914;
    public static final int ERR_DEFAULT = 70940;
    public static final String ERR_DESCRIPTION_AI_ENGINE = "无法启动引擎!";
    public static final String ERR_DESCRIPTION_CONNECT_TIMEOUT = "连接服务器超时";
    public static final String ERR_DESCRIPTION_DEFAULT = "未知错误";
    public static final String ERR_DESCRIPTION_DEVICE = "无法获取录音设备!";
    public static final String ERR_DESCRIPTION_DEVICE_ID_CONFLICT = "deviceId 冲突导致认证失败";
    public static final String ERR_DESCRIPTION_ERR_DNS = "没有网络或者dns解析失败";
    public static final String ERR_DESCRIPTION_ERR_FDM_NOT_INIT = "未init成功fdm或语音引擎模块";
    public static final String ERR_DESCRIPTION_ERR_GRAMMAR_FAILED = "本地语法文件编译失败，请检查xbnf文件路径或文本是否合法";
    public static final String ERR_DESCRIPTION_ERR_INVALID_DM_RESULT = "无效的对话结果";
    public static final String ERR_DESCRIPTION_ERR_NETWORK = "网络错误";
    public static final String ERR_DESCRIPTION_ERR_QUEUE_FULL = "播放队列已满";
    public static final String ERR_DESCRIPTION_ERR_SDK_NOT_INIT = "SDK尚未初始化，请初始化并授权成功后使用";
    public static final String ERR_DESCRIPTION_ERR_SERVICE_PARAMETERS = "设置识别服务器类型为custom须同时设置lmId";
    public static final String ERR_DESCRIPTION_ERR_TTS_CACHE = "音频缓存失败";
    public static final String ERR_DESCRIPTION_INVALID_DM_RECORDER_ID = "对话服务返回recorderId不一致";
    public static final String ERR_DESCRIPTION_INVALID_NET_BIN = "netbin文件异常";
    public static final String ERR_DESCRIPTION_INVALID_RECORDER_TYPE = "无效的麦克风类型";
    public static final String ERR_DESCRIPTION_KEYWORD_UNMATCH = "请到安静环境下再说一遍唤醒词";
    public static final String ERR_DESCRIPTION_MAX_SPEECH = "音频时长超出阈值";
    public static final String ERR_DESCRIPTION_NATIVE_API_TIMEOUT = "NativeApi响应超时";
    public static final String ERR_DESCRIPTION_NO_REGISTERED_WORD = "该用户未注册过该文本";
    public static final String ERR_DESCRIPTION_NO_SPEAKER = "该用户尚未注册";
    public static final String ERR_DESCRIPTION_NO_SPEECH = "没有检测到语音";
    public static final String ERR_DESCRIPTION_NULL_SEMANTIC = "语义为空";
    public static final String ERR_DESCRIPTION_NULL_SEMANTIC_INPUT = "语义输入文本空";
    public static final String ERR_DESCRIPTION_RECORDING = "录音失败!";
    public static final String ERR_DESCRIPTION_REGISTER_MODEL_EXPIRED = "模型不兼容需要重新注册";
    public static final String ERR_DESCRIPTION_REGISTER_SPK_FULL = "注册用户数量已满";
    public static final String ERR_DESCRIPTION_REGISTER_UPDATE_MODEL_FINISHED = "升级模型结束";
    public static final String ERR_DESCRIPTION_RES_PREPARE_FAILED = "资源准备失败，请检查是否存放在assets目录下";
    public static final String ERR_DESCRIPTION_SIGNAL_PROCESSING_NOT_STARTED = "信号处理引擎还没有启动，请先启动信号处理引擎，再启动识别引擎";
    public static final String ERR_DESCRIPTION_SNR_LOW = "信噪比过低，请安静场景下再试一次";
    public static final String ERR_DESCRIPTION_SO_INVALID = "so动态库加载失败";
    public static final String ERR_DESCRIPTION_SPEECH_CLIPPING = "音频已截幅，请距离远一点再试一下";
    public static final String ERR_DESCRIPTION_SPEECH_SPEED_FAST = "请慢点清晰的再说一次";
    public static final String ERR_DESCRIPTION_SPEECH_SPEED_SLOW = "请加快语速再说一次";
    public static final String ERR_DESCRIPTION_SPK_REGISTERED_WORD = "该用户已经注册过该文本";
    public static final String ERR_DESCRIPTION_SPK_REGISTER_SPEECH_LACK = "用户的注册的音频条数不够";
    public static final String ERR_DESCRIPTION_TIMEOUT_ASR = "等待识别结果超时";
    public static final String ERR_DESCRIPTION_TTS_INVALID_REFTEXT = "无效的合成文本";
    public static final String ERR_DESCRIPTION_TTS_MEDIAPLAYER = "合成MediaPlayer播放器错误:";
    public static final String ERR_DESCRIPTION_UNSUPPORT_GENDER = "不支持该性别";
    public static final String ERR_DESCRIPTION_UNSUPPORT_WORD = "不支持该词语";
    public static final String ERR_DESCRIPTION_VOICE_LOW = "没听清，请再大声一点";
    public static final int ERR_DEVICE = 70901;
    public static final int ERR_DEVICE_ID_CONFLICT_ASR = 72150;
    public static final int ERR_DEVICE_ID_CONFLICT_TTS = 73101;
    public static final int ERR_DNS = 70912;
    public static final int ERR_FDM_NO_INIT = 70906;
    public static final int ERR_GRAMMAR_FAILED = 70927;
    public static final int ERR_INVALID_DM_RECORDER_ID = 709503;
    public static final int ERR_INVALID_DM_RESULT = 709502;
    public static final int ERR_INVALID_RECORDER_TYPE = 70913;
    public static final int ERR_KEYWORD_UNMATCH = 70951;
    public static final int ERR_MAX_SPEECH = 70905;
    public static final int ERR_NATIVE_API_TIMEOUT = 709501;
    public static final int ERR_NETWORK = 70911;
    public static final int ERR_NET_BIN_INVALID = 70992;
    public static final int ERR_NO_REGISTERED_WORD = 70942;
    public static final int ERR_NO_SPEECH = 70904;
    public static final int ERR_NO_SPKEAKER = 70941;
    public static final int ERR_NULL_SEMANTIC = 70931;
    public static final int ERR_NULL_SEMANTIC_INPUT = 70929;
    public static final int ERR_QUEUE_FULL = 70918;
    public static final int ERR_RECORDING = 70903;
    public static final int ERR_REGISTER_SPK_FULL = 70944;
    public static final int ERR_RES_PREPARE_FAILED = 70920;
    public static final int ERR_RETRY_INIT = 70930;
    public static final String ERR_RETRY_INIT_MSG = "服务401 Unauthorized，请重新尝试init授权...";
    public static final int ERR_SDK_NOT_INIT = 70900;
    public static final int ERR_SERVICE_PARAMETERS = 70926;
    public static final int ERR_SIGNAL_PROCESSING_NOT_STARTED = 70928;
    public static final int ERR_SNR_LOW = 70949;
    public static final int ERR_SO_INVALID = 70991;
    public static final int ERR_SPEECH_CLIPPING = 70950;
    public static final int ERR_SPEECH_SPEED_FAST = 70948;
    public static final int ERR_SPEECH_SPEED_SLOW = 70947;
    public static final int ERR_SPK_REGISTERED_WORD = 70943;
    public static final int ERR_TIMEOUT_ASR = 70961;
    public static final int ERR_TTS_CACHE = 70915;
    public static final int ERR_TTS_INVALID_REFTEXT = 72203;
    public static final int ERR_TTS_MEDIAPLAYER = 72204;
    public static final int ERR_UNSUPPORT_GENDER = 70945;
    public static final int ERR_UNSUPPORT_WORD = 70946;
    public static final int ERR_VOICE_LOW = 70939;
    public static final String KEY_CODE = "errId";
    public static final String KEY_EXT = "ext";
    public static final String KEY_RECORD_ID = "recordId";
    public static final String KEY_TEXT = "error";
    public static final String KEY_TIME = "timestamp";
    private int a;
    private String b;
    private String c;
    private long d;
    private String e;
    private Map<Object, Object> f;
    private JSONObject g;

    /* synthetic */ AIError(Parcel parcel, byte b) {
        this(parcel);
    }

    public AIError(String str) {
        this(str, (String) null);
    }

    public AIError setEventMap(Map<Object, Object> map) {
        this.f = map;
        return this;
    }

    public Map<Object, Object> getEventMap() {
        return this.f;
    }

    public AIError(String str, String str2) {
        this.a = ERR_SDK_NOT_INIT;
        this.d = -1L;
        this.e = null;
        stringToJSON(str);
        setRecordId(str2);
        setTimestamp(Long.valueOf(System.currentTimeMillis()));
    }

    public AIError(int i) {
        this(i, (String) null);
    }

    public AIError(int i, String str) {
        this(i, str, null);
    }

    public AIError(int i, String str, String str2) {
        this(i, str, str2, System.currentTimeMillis());
    }

    public AIError(int i, String str, String str2, long j) {
        super(str);
        this.a = ERR_SDK_NOT_INIT;
        this.d = -1L;
        this.e = null;
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = j;
    }

    public boolean isNetWorkError() {
        return Integer.toString(this.a).startsWith("706");
    }

    public String getError() {
        return this.b;
    }

    public void setError(String str) {
        this.b = str;
    }

    public int getErrId() {
        return this.a;
    }

    public void setErrId(int i) {
        this.a = i;
    }

    public void setErrId(String str) {
        try {
            this.a = Integer.parseInt(str);
        } catch (Exception e) {
        }
    }

    public void stringToJSON(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(KEY_CODE)) {
                    this.a = jSONObject.getInt(KEY_CODE);
                    if (jSONObject.has(KEY_TEXT)) {
                        this.b = jSONObject.getString(KEY_TEXT);
                        return;
                    }
                    return;
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("result");
                if (optJSONObject != null && optJSONObject.has(KEY_CODE)) {
                    this.a = optJSONObject.optInt(KEY_CODE);
                }
                if (optJSONObject != null && optJSONObject.has(KEY_TEXT)) {
                    this.b = optJSONObject.getString(KEY_TEXT);
                }
            } catch (JSONException e) {
            }
        }
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        JSONUtil.putQuietly(jSONObject, KEY_CODE, Integer.valueOf(this.a));
        JSONUtil.putQuietly(jSONObject, KEY_TEXT, this.b);
        String str = this.c;
        if (str != null) {
            JSONUtil.putQuietly(jSONObject, KEY_RECORD_ID, str);
        }
        long j = this.d;
        if (j > 0) {
            JSONUtil.putQuietly(jSONObject, KEY_TIME, Long.valueOf(j));
        }
        String str2 = this.e;
        if (str2 != null) {
            JSONUtil.putQuietly(jSONObject, KEY_EXT, str2.toString());
        }
        return jSONObject;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return toJSON().toString();
    }

    public JSONObject getOutputJSON() {
        JSONObject jSONObject = new JSONObject();
        JSONUtil.putQuietly(jSONObject, KEY_CODE, Integer.valueOf(this.a));
        JSONUtil.putQuietly(jSONObject, KEY_TEXT, this.b);
        return jSONObject;
    }

    public void setInputJson(JSONObject jSONObject) {
        this.g = jSONObject;
    }

    public JSONObject getInputJSON() {
        return this.g;
    }

    public Object getExt() {
        return this.e;
    }

    public void setExt(String str) {
        this.e = str;
    }

    public String getRecordId() {
        return this.c;
    }

    public void setRecordId(String str) {
        this.c = str;
    }

    public Long getTimestamp() {
        return Long.valueOf(this.d);
    }

    public void setTimestamp(Long l) {
        this.d = l.longValue();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.e);
        parcel.writeString(this.c);
        parcel.writeLong(this.d);
    }

    private AIError(Parcel parcel) {
        this.a = ERR_SDK_NOT_INIT;
        this.d = -1L;
        this.e = null;
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.e = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readLong();
    }

    public AIError() {
        this.a = ERR_SDK_NOT_INIT;
        this.d = -1L;
        this.e = null;
    }
}
