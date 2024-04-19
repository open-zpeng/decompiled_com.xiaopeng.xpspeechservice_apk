package com.aispeech.export.config;

import com.aispeech.common.PinYinUtils;
import com.aispeech.export.exception.IllegalPinyinException;
import com.aispeech.lite.Languages;
/* loaded from: classes.dex */
public class AIFespCarConfig {
    private WakeupWord a;
    private String b;
    private String c;
    private AIOneshotConfig d;
    private int e;
    private int f;
    private Languages g;

    /* synthetic */ AIFespCarConfig(Builder builder, byte b) {
        this(builder);
    }

    public void setLanguage(Languages languages) {
        this.g = languages;
    }

    public Languages getLanguage() {
        return this.g;
    }

    public WakeupWord getWakeupWord() {
        return this.a;
    }

    public String getWakeupResource() {
        return this.b;
    }

    public String getBeamformingResource() {
        return this.c;
    }

    public AIOneshotConfig getOneshotConfig() {
        return this.d;
    }

    public int getStateFrame() {
        return this.e;
    }

    public int getRightMarginFrame() {
        return this.f;
    }

    private AIFespCarConfig(WakeupWord wakeupWord, String str, String str2, AIOneshotConfig aIOneshotConfig, int i, int i2, Languages languages) {
        this.e = 20;
        this.f = 10;
        this.g = Languages.CHINESE;
        this.a = wakeupWord;
        this.b = str;
        this.c = str2;
        this.d = aIOneshotConfig;
        this.e = i;
        this.f = i2;
        this.g = languages;
    }

    private AIFespCarConfig(Builder builder) {
        this(builder.getWakeupWord(), builder.getWakeupResource(), builder.getBeamformingResource(), builder.getOneshotConfig(), builder.getStateFrame(), builder.getRightMarginFrame(), builder.getLanguage());
    }

    /* loaded from: classes.dex */
    public static class WakeupWord {
        public int[] dcheck;
        public int[] majors;
        public String[] pinyin;
        public float[] threshold;

        public WakeupWord(String[] strArr, float[] fArr, int[] iArr, int[] iArr2) {
            this.pinyin = strArr;
            this.threshold = fArr;
            this.majors = iArr;
            this.dcheck = iArr2;
        }

        public WakeupWord(String[] strArr, float[] fArr, int[] iArr) {
            this.pinyin = strArr;
            this.threshold = fArr;
            this.majors = iArr;
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private WakeupWord a;
        private String b;
        private String c;
        private AIOneshotConfig d;
        private int e = 20;
        private int f = 10;
        private Languages g = Languages.CHINESE;

        public void setLanguage(Languages languages) {
            this.g = languages;
        }

        public Languages getLanguage() {
            return this.g;
        }

        public WakeupWord getWakeupWord() {
            return this.a;
        }

        public Builder setWakeupWord(WakeupWord wakeupWord) {
            this.a = wakeupWord;
            return this;
        }

        public String getWakeupResource() {
            return this.b;
        }

        public Builder setWakeupResource(String str) {
            this.b = str;
            return this;
        }

        public String getBeamformingResource() {
            return this.c;
        }

        public Builder setBeamformingResource(String str) {
            this.c = str;
            return this;
        }

        public AIOneshotConfig getOneshotConfig() {
            return this.d;
        }

        public Builder setOneshotConfig(AIOneshotConfig aIOneshotConfig) {
            this.d = aIOneshotConfig;
            return this;
        }

        public int getStateFrame() {
            return this.e;
        }

        public Builder setStateFrame(int i) {
            this.e = i;
            return this;
        }

        public int getRightMarginFrame() {
            return this.f;
        }

        public Builder setRightMarginFrame(int i) {
            this.f = i;
            return this;
        }

        public AIFespCarConfig create() {
            WakeupWord wakeupWord = getWakeupWord();
            boolean z = true;
            if (wakeupWord.dcheck == null) {
                String[] strArr = wakeupWord.pinyin;
                float[] fArr = wakeupWord.threshold;
                int[] iArr = wakeupWord.majors;
                if (strArr.length != fArr.length || strArr.length != iArr.length) {
                    z = false;
                }
            } else {
                String[] strArr2 = wakeupWord.pinyin;
                float[] fArr2 = wakeupWord.threshold;
                int[] iArr2 = wakeupWord.majors;
                int[] iArr3 = wakeupWord.dcheck;
                if (strArr2.length != fArr2.length || strArr2.length != iArr2.length || strArr2.length != iArr3.length) {
                    z = false;
                }
            }
            if (!z) {
                throw new IllegalArgumentException(" invalid length ! ");
            }
            if (this.g == Languages.CHINESE) {
                for (String str : wakeupWord.pinyin) {
                    if (!PinYinUtils.checkPinyin(str)) {
                        throw new IllegalPinyinException();
                    }
                }
            }
            return new AIFespCarConfig(this, (byte) 0);
        }
    }
}
