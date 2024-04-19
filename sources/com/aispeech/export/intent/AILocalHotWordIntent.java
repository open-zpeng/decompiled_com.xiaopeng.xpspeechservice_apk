package com.aispeech.export.intent;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class AILocalHotWordIntent {
    private String d;
    private String f;
    private String g;
    private double h;
    private boolean a = true;
    private int b = 10;
    private int c = 0;
    private Map<String, Double> e = new HashMap();

    public void setUseContinuousRecognition(boolean z) {
        this.a = z;
    }

    public void setMaxSpeechTime(int i) {
        this.b = i;
    }

    public void setNoSpeechTime(int i) {
        this.c = i;
    }

    public void setSaveAudioPath(String str) {
        this.d = str;
    }

    public void setWords(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("empty hot words");
        }
        this.f = a(strArr);
    }

    public void setWords(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("empty hot words");
        }
        if (!str.startsWith("\"") || !str.endsWith("\"")) {
            throw new IllegalArgumentException("illegal hot words");
        }
        this.f = str;
    }

    public void setBlackWords(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("empty hot words");
        }
        this.g = a(strArr);
    }

    public void setThreshold(double d) {
        this.h = d;
    }

    public void setCustomThreshold(String[] strArr, Double[] dArr) {
        if (strArr == null || dArr == null || strArr.length != dArr.length) {
            throw new IllegalArgumentException("set custom threshold data inconsistent!");
        }
        for (int i = 0; i < strArr.length; i++) {
            this.e.put(strArr[i], dArr[i]);
        }
    }

    public int getNoSpeechTime() {
        return this.c;
    }

    public double getThreshold() {
        return this.h;
    }

    public int getMaxSpeechTime() {
        return this.b;
    }

    public String getWords() {
        return this.f;
    }

    public String getSaveAudioPath() {
        return this.d;
    }

    public boolean isUseContinuousRecognition() {
        return this.a;
    }

    public String getBlackWords() {
        return this.g;
    }

    public Map<String, Double> getCustomThreshold() {
        return this.e;
    }

    private static String a(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                sb.append(str + ",");
            }
        }
        sb.delete(sb.lastIndexOf(","), sb.length());
        sb.append("\"");
        return sb.toString();
    }
}
