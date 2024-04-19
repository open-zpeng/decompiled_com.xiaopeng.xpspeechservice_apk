package com.aispeech.export.intent;

import com.aispeech.common.JSONUtil;
import com.aispeech.lite.AISampleRate;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class AICloudVprintIntent {
    private String a;
    private Audio b;
    private String c;
    private String d;
    private String e;
    private JSONObject f;
    private Env g;
    private boolean h;
    private String[] i;
    private String j;

    /* synthetic */ AICloudVprintIntent(Builder builder, byte b) {
        this(builder);
    }

    private AICloudVprintIntent(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String a;
        private Audio b;
        private String c;
        private String d;
        private String e;
        private JSONObject f;
        private Env g;
        private boolean h;
        private String[] i;
        private String j;

        public Builder setRequestId(String str) {
            this.a = str;
            return this;
        }

        public Builder setAudio(Audio audio) {
            this.b = audio;
            return this;
        }

        public Builder setUserId(String str) {
            this.c = str;
            return this;
        }

        public Builder setOrganization(String str) {
            this.d = str;
            return this;
        }

        public Builder setDomain(String str) {
            this.e = str;
            return this;
        }

        public Builder setApp(JSONObject jSONObject) {
            this.f = jSONObject;
            return this;
        }

        public Builder setEnv(Env env) {
            this.g = env;
            return this;
        }

        public Builder setSkip_saving(boolean z) {
            this.h = z;
            return this;
        }

        public Builder setGroupId(String str) {
            this.j = str;
            return this;
        }

        public Builder setUsers(String[] strArr) {
            this.i = strArr;
            return this;
        }

        public AICloudVprintIntent build() {
            return new AICloudVprintIntent(this, (byte) 0);
        }
    }

    /* loaded from: classes.dex */
    public static class Audio {
        private String a = "wav";
        private int b = 16000;
        private int c = 1;
        private int d = 2;

        public String getAudioType() {
            return this.a;
        }

        public void setAudioType(String str) {
            this.a = str;
        }

        public int getSampleRate() {
            return this.b;
        }

        public void setSampleRate(int i) {
            this.b = i;
        }

        public int getChannel() {
            return this.c;
        }

        public void setChannel(int i) {
            this.c = i;
        }

        public int getSampleBytes() {
            return this.d;
        }

        public void setSampleBytes(int i) {
            this.d = i;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            JSONUtil.putQuietly(jSONObject, "audioType", this.a);
            JSONUtil.putQuietly(jSONObject, AISampleRate.KEY_SAMPLE_RATE, Integer.valueOf(this.b));
            JSONUtil.putQuietly(jSONObject, "channel", Integer.valueOf(this.c));
            JSONUtil.putQuietly(jSONObject, "sampleBytes", Integer.valueOf(this.d));
            return jSONObject;
        }
    }

    /* loaded from: classes.dex */
    public static class Env {
        private String b;
        private float a = 0.0f;
        private boolean c = false;
        private float d = 0.4f;
        private boolean e = true;
        private boolean f = true;
        private float g = 8.67f;

        public float getAsrErrorRate() {
            return this.a;
        }

        public void setAsrErrorRate(float f) {
            this.a = f;
        }

        public String getCustomContent() {
            return this.b;
        }

        public void setCustomContent(String str) {
            this.b = str;
        }

        public boolean isEnhanceRegister() {
            return this.c;
        }

        public void setEnhanceRegister(boolean z) {
            this.c = z;
        }

        public float getMinSpeechLength() {
            return this.d;
        }

        public void setMinSpeechLength(float f) {
            this.d = f;
        }

        public boolean isEnableVad() {
            return this.e;
        }

        public void setEnableVad(boolean z) {
            this.e = z;
        }

        public boolean isEnableSpeakerAntiSpoofing() {
            return this.f;
        }

        public void setEnableSpeakerAntiSpoofing(boolean z) {
            this.f = z;
        }

        public float getSnrRate() {
            return this.g;
        }

        public void setSnrRate(float f) {
            this.g = f;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            JSONUtil.putQuietly(jSONObject, "asrErrorRate", Float.valueOf(this.a));
            JSONUtil.putQuietly(jSONObject, "customContent", this.b);
            JSONUtil.putQuietly(jSONObject, "enhanceRegister", Boolean.valueOf(this.c));
            JSONUtil.putQuietly(jSONObject, "minSpeechLength", Float.valueOf(this.d));
            JSONUtil.putQuietly(jSONObject, "enableVad", Boolean.valueOf(this.e));
            JSONUtil.putQuietly(jSONObject, "enableSpeakerAntiSpoofing", Boolean.valueOf(this.f));
            JSONUtil.putQuietly(jSONObject, "snrRate", Float.valueOf(this.g));
            return jSONObject;
        }
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        JSONUtil.putQuietly(jSONObject, "requestId", this.a);
        JSONUtil.putQuietly(jSONObject, "userId", this.c);
        JSONUtil.putQuietly(jSONObject, "organization", this.d);
        JSONUtil.putQuietly(jSONObject, "domain", this.e);
        JSONUtil.putQuietly(jSONObject, "app", this.f);
        JSONUtil.putQuietly(jSONObject, "skip_saving", Boolean.valueOf(this.h));
        if (this.g == null) {
            this.g = new Env();
        }
        JSONUtil.putQuietly(jSONObject, "env", this.g.toJson());
        if (this.b == null) {
            this.b = new Audio();
        }
        JSONUtil.putQuietly(jSONObject, "audio", this.b.toJson());
        if (this.i != null) {
            JSONArray jSONArray = new JSONArray();
            for (String str : this.i) {
                jSONArray.put(str);
            }
            JSONUtil.putQuietly(jSONObject, "users", jSONArray);
        }
        JSONUtil.putQuietly(jSONObject, "groupId", this.j);
        return jSONObject;
    }
}
