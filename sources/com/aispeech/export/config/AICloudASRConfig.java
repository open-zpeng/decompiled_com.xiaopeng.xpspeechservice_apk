package com.aispeech.export.config;

import com.aispeech.export.intent.AIAsrPlusIntent;
import org.json.JSONArray;
/* loaded from: classes.dex */
public class AICloudASRConfig {
    private String a;
    private String b;
    private boolean c;
    private boolean d;
    private boolean e;
    private int f;
    private JSONArray g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private String o;
    private String p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private boolean u;
    private boolean v;
    private AIAsrPlusIntent w;

    public String getServer() {
        return this.a;
    }

    public String getUserId() {
        return this.b;
    }

    public String getLmid() {
        return null;
    }

    public boolean isLocalVadEnable() {
        return this.c;
    }

    public boolean isEnablePunctuation() {
        return this.d;
    }

    public boolean isEnableNumberConvert() {
        return this.e;
    }

    public int getSelfCustomWakeupScore() {
        return this.f;
    }

    public JSONArray getCustomWakeupWord() {
        return this.g;
    }

    public boolean isWakeupWordVisible() {
        return this.h;
    }

    public boolean isEnableTone() {
        return this.i;
    }

    public boolean isEnableAlignment() {
        return this.j;
    }

    public boolean isEnableEmotion() {
        return this.k;
    }

    public boolean isEnableAudioDetection() {
        return this.l;
    }

    public boolean isEnableLanguageClassifier() {
        return this.m;
    }

    public boolean isEnableSNTime() {
        return this.n;
    }

    public String getResourceType() {
        return this.o;
    }

    public String getVadResource() {
        return this.p;
    }

    public boolean getRealBack() {
        return this.q;
    }

    public boolean isCloudVadEnable() {
        return this.r;
    }

    public int getnBest() {
        return this.s;
    }

    public int getVadPauseTime() {
        return this.t;
    }

    public boolean isUseCustomFeed() {
        return this.u;
    }

    public boolean isEnableAsrPlus() {
        return this.v;
    }

    public AIAsrPlusIntent getAsrPlusIntent() {
        return this.w;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private String a;
        private String b;
        private boolean c;
        private boolean d;
        private boolean e;
        private int f;
        private JSONArray g;
        private boolean h;
        private boolean i;
        private boolean j;
        private boolean k;
        private boolean l;
        private boolean m;
        private boolean n;
        private String o;
        private String p;
        private boolean q;
        private boolean r;
        private int s;
        private int t;
        private boolean u;
        private boolean v;
        private AIAsrPlusIntent w;

        public final Builder setServer(String str) {
            this.a = str;
            return this;
        }

        public final Builder setUserId(String str) {
            this.b = str;
            return this;
        }

        public final Builder setLmId(String str) {
            return this;
        }

        public final Builder setLocalVadEnable(boolean z) {
            this.c = z;
            return this;
        }

        public final Builder setEnablePunctuation(boolean z) {
            this.d = z;
            return this;
        }

        public final Builder setEnableNumberConvert(boolean z) {
            this.e = z;
            return this;
        }

        public final Builder setSelfCustomWakeupScore(int i) {
            this.f = i;
            return this;
        }

        public final Builder setCustomWakeupWord(JSONArray jSONArray) {
            this.g = jSONArray;
            return this;
        }

        public final Builder setWakeupWordVisible(boolean z) {
            this.h = z;
            return this;
        }

        public final Builder setEnableTone(boolean z) {
            this.i = z;
            return this;
        }

        public final Builder setEnableAlignment(boolean z) {
            this.j = z;
            return this;
        }

        public final Builder setEnableEmotion(boolean z) {
            this.k = z;
            return this;
        }

        public final Builder setEnableAudioDetection(boolean z) {
            this.l = z;
            return this;
        }

        public final Builder setEnableLanguageClassifier(boolean z) {
            this.m = z;
            return this;
        }

        public final Builder setEnableSNTime(boolean z) {
            this.n = z;
            return this;
        }

        public final Builder setResourceType(String str) {
            this.o = str;
            return this;
        }

        public final Builder setVadResource(String str) {
            this.p = str;
            return this;
        }

        public final Builder setRealBack(boolean z) {
            this.q = z;
            return this;
        }

        public final Builder setCloudVadEnable(boolean z) {
            this.r = z;
            return this;
        }

        public final Builder setNBest(int i) {
            this.s = i;
            return this;
        }

        public final Builder setVadPauseTime(int i) {
            this.t = i;
            return this;
        }

        public final Builder setUseCustomFeed(boolean z) {
            this.u = z;
            return this;
        }

        public final Builder setEnableAsrPlus(boolean z) {
            this.v = z;
            return this;
        }

        public final Builder setAsrPlusIntent(AIAsrPlusIntent aIAsrPlusIntent) {
            this.w = aIAsrPlusIntent;
            return this;
        }

        public final AICloudASRConfig build() {
            AICloudASRConfig aICloudASRConfig = new AICloudASRConfig();
            aICloudASRConfig.s = this.s;
            aICloudASRConfig.h = this.h;
            aICloudASRConfig.d = this.d;
            aICloudASRConfig.g = this.g;
            aICloudASRConfig.j = this.j;
            aICloudASRConfig.p = this.p;
            aICloudASRConfig.q = this.q;
            aICloudASRConfig.c = this.c;
            aICloudASRConfig.f = this.f;
            aICloudASRConfig.a = this.a;
            aICloudASRConfig.o = this.o;
            aICloudASRConfig.t = this.t;
            aICloudASRConfig.n = this.n;
            aICloudASRConfig.r = this.r;
            aICloudASRConfig.i = this.i;
            aICloudASRConfig.m = this.m;
            aICloudASRConfig.l = this.l;
            aICloudASRConfig.k = this.k;
            aICloudASRConfig.u = this.u;
            aICloudASRConfig.b = this.b;
            aICloudASRConfig.e = this.e;
            aICloudASRConfig.v = this.v;
            aICloudASRConfig.w = this.w;
            return aICloudASRConfig;
        }
    }
}
