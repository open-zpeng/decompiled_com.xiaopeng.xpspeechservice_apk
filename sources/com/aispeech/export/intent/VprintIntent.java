package com.aispeech.export.intent;

import android.text.TextUtils;
import com.aispeech.common.AIConstant;
import java.util.Arrays;
/* loaded from: classes.dex */
public class VprintIntent {
    private int a;
    private int b;
    private int c;
    private Action d;
    private String[] e;
    private String f;
    private int g;
    private float h;
    private float i;
    private String j;
    private String k;
    private int l;

    /* synthetic */ VprintIntent(Builder builder, byte b) throws IllegalArgumentException {
        this(builder);
    }

    /* loaded from: classes.dex */
    public enum Action {
        REGISTER(AIConstant.VP_REGISTER),
        UPDATE(AIConstant.VP_UPDATE),
        APPEND("append"),
        TEST(AIConstant.VP_TEST),
        UNREGISTER(AIConstant.VP_UNREGISTER),
        UNREGISTER_ALL(AIConstant.VP_UNREGISTER_ALL);
        
        private String a;

        Action(String str) {
            this.a = str;
        }

        public final String getValue() {
            return this.a;
        }

        public static Action getActionByValue(String str) {
            Action[] values;
            for (Action action : values()) {
                if (str == action.a) {
                    return action;
                }
            }
            return null;
        }
    }

    private VprintIntent(Builder builder) throws IllegalArgumentException {
        String[] strArr;
        this.g = 4;
        this.h = Float.MAX_VALUE;
        this.i = 8.67f;
        this.l = 0;
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.l = builder.l;
        this.f = builder.f;
        this.g = builder.g;
        this.i = builder.i;
        this.h = builder.h;
        this.j = builder.j;
        this.k = builder.k;
        Action action = this.d;
        if (action == null) {
            throw new IllegalArgumentException("Vprint intent is invalid, lost action");
        }
        if (action != Action.UNREGISTER_ALL) {
            if (this.d != Action.TEST && ((strArr = this.e) == null || strArr.length == 0)) {
                throw new IllegalArgumentException("Vprint intent is invalid, lost vpirntWord");
            }
            if (this.d != Action.TEST && TextUtils.isEmpty(this.f)) {
                throw new IllegalArgumentException("Vprint intent is invalid, lost userId");
            }
        }
    }

    public int getBfChannelNum() {
        return this.a;
    }

    public int getAecChannelNum() {
        return this.b;
    }

    public int getOutChannelNum() {
        return this.c;
    }

    public Action getAction() {
        return this.d;
    }

    public String[] getVprintWord() {
        return this.e;
    }

    public int getSensitivityLevel() {
        return this.l;
    }

    public String getUserId() {
        return this.f;
    }

    public int getTrainNum() {
        return this.g;
    }

    public float getSnrThresh() {
        return this.i;
    }

    public float getThresh() {
        return this.h;
    }

    public String getSaveAudioPath() {
        return this.j;
    }

    public String getVprintCutSaveDir() {
        return this.k;
    }

    public void setBfChannelNum(int i) {
        this.a = i;
    }

    public void setAecChannelNum(int i) {
        this.b = i;
    }

    public void setOutChannelNum(int i) {
        this.c = i;
    }

    public String toString() {
        return "VprintIntent{bfChannelNum=" + this.a + ", aecChannelNum=" + this.b + ", outChannelNum=" + this.c + ", action=" + this.d + ", vprintWord=" + Arrays.toString(this.e) + ", userId='" + this.f + "', trainNum=" + this.g + ", thresh=" + this.h + ", snrThresh=" + this.i + ", saveAudioPath='" + this.j + "', vprintCutSaveDir='" + this.k + "', sensitivityLevel=" + this.l + '}';
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private int a;
        private int b;
        private int c;
        private Action d;
        private String[] e;
        private String f;
        private int g;
        private float i;
        private String j;
        private String k;
        private float h = Float.MAX_VALUE;
        private int l = 0;

        public Builder setBfChannelNum(int i) {
            this.a = i;
            return this;
        }

        public Builder setAecChannelNum(int i) {
            this.b = i;
            return this;
        }

        public Builder setOutChannelNum(int i) {
            this.c = i;
            return this;
        }

        public Builder setAction(Action action) {
            this.d = action;
            return this;
        }

        public Builder setVprintWord(String str) {
            if (TextUtils.isEmpty(str)) {
                this.e = null;
            } else {
                this.e = new String[]{str};
            }
            return this;
        }

        public Builder setVprintWord(String... strArr) {
            if (strArr != null && strArr.length > 0) {
                this.e = strArr;
            } else {
                this.e = null;
            }
            return this;
        }

        public Builder setSensitivityLevel(int i) {
            this.l = i;
            return this;
        }

        public Builder setUserId(String str) {
            this.f = str;
            return this;
        }

        public Builder setTrainNum(int i) {
            this.g = i;
            return this;
        }

        public Builder setSnrThresh(float f) {
            this.i = f;
            return this;
        }

        public Builder setThresh(float f) {
            this.h = f;
            return this;
        }

        public Builder setSaveAudioPath(String str) {
            this.j = str;
            return this;
        }

        public Builder setVprintCutSaveDir(String str) {
            this.k = str;
            return this;
        }

        public VprintIntent create() throws IllegalArgumentException {
            return new VprintIntent(this, (byte) 0);
        }
    }
}
