package com.aispeech.export.config;

import com.aispeech.common.ArrayUtils;
import com.aispeech.common.PinYinUtils;
import com.aispeech.export.exception.IllegalPinyinException;
/* loaded from: classes.dex */
public class AIDmaspConfig {
    private WakeupWord a;
    private String b;
    private String c;

    /* synthetic */ AIDmaspConfig(Builder builder, byte b) {
        this(builder);
    }

    public WakeupWord getWakeupWord() {
        return this.a;
    }

    public String getWakeupResource() {
        return this.b;
    }

    public String getDmaspResource() {
        return this.c;
    }

    private AIDmaspConfig(WakeupWord wakeupWord, String str, String str2) {
        this.a = wakeupWord;
        this.b = str;
        this.c = str2;
    }

    private AIDmaspConfig(Builder builder) {
        this(builder.getWakeupWord(), builder.getWakeupResource(), builder.getDmaspResource());
    }

    /* loaded from: classes.dex */
    public static class WakeupWord {
        public int[] dcheck;
        public int[] majors;
        public String[] pinyin;
        public float[] threshold;

        public WakeupWord(String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4) {
            this.pinyin = strArr;
            this.threshold = ArrayUtils.string2Float(strArr2);
            this.majors = ArrayUtils.string2Int(strArr3);
            this.dcheck = ArrayUtils.string2Int(strArr4);
        }

        public WakeupWord(String[] strArr, float[] fArr, int[] iArr, int[] iArr2) {
            this.pinyin = strArr;
            this.threshold = fArr;
            this.majors = iArr;
            this.dcheck = iArr2;
        }

        public WakeupWord(String[] strArr, String[] strArr2, String[] strArr3) {
            this.pinyin = strArr;
            this.threshold = ArrayUtils.string2Float(strArr2);
            this.majors = ArrayUtils.string2Int(strArr3);
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private WakeupWord a;
        private String b;
        private String c;

        public WakeupWord getWakeupWord() {
            return this.a;
        }

        public Builder setWakeupWord(WakeupWord wakeupWord) {
            this.a = wakeupWord;
            return this;
        }

        public String getDmaspResource() {
            return this.c;
        }

        public Builder setDmaspResource(String str) {
            this.c = str;
            return this;
        }

        public String getWakeupResource() {
            return this.b;
        }

        public Builder setWakeupResource(String str) {
            this.b = str;
            return this;
        }

        public AIDmaspConfig create() {
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
            for (String str : wakeupWord.pinyin) {
                if (!PinYinUtils.checkPinyin(str)) {
                    throw new IllegalPinyinException();
                }
            }
            return new AIDmaspConfig(this, (byte) 0);
        }
    }
}
