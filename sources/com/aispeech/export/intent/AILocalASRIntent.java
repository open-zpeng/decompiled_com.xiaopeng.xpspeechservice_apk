package com.aispeech.export.intent;

import android.text.TextUtils;
import com.aispeech.export.ASRMode;
import com.aispeech.lite.oneshot.OneshotCache;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class AILocalASRIntent {
    private boolean c;
    private String d;
    private boolean e;
    private String f;
    private boolean g;
    private boolean h;
    private boolean i;
    private double j;
    private boolean k;
    private OneshotCache<byte[]> l;
    private int m;
    private int n;
    private String o;
    private String q;
    private String r;
    private double s;
    private ASRMode a = ASRMode.MODE_ASR;
    private boolean b = true;
    private Map<String, Double> p = new HashMap();

    public boolean isUseFrameSplit() {
        return this.c;
    }

    public void setUseFrameSplit(boolean z) {
        this.c = z;
    }

    public String getUseDelimiter() {
        return this.d;
    }

    public void setUseDelimiter(String str) {
        this.d = str;
    }

    public boolean isUseConf() {
        return this.e;
    }

    public void setUseConf(boolean z) {
        this.e = z;
    }

    public String getExpandFnPath() {
        return this.f;
    }

    public void setExpandFnPath(String str) {
        this.f = str;
    }

    public boolean isUseXbnfRec() {
        return this.g;
    }

    public void setUseXbnfRec(boolean z) {
        this.g = z;
    }

    public boolean isUsePinyin() {
        return this.h;
    }

    public void setUsePinyin(boolean z) {
        this.h = z;
    }

    public boolean isUseFiller() {
        return this.i;
    }

    public void setUseFiller(boolean z) {
        this.i = z;
    }

    public double getFillerPenaltyScore() {
        return this.j;
    }

    public void setFillerPenaltyScore(double d) {
        this.j = d;
    }

    public boolean isUseFormat() {
        return this.k;
    }

    public void setUseFormat(boolean z) {
        this.k = z;
    }

    public OneshotCache<byte[]> getOneshotCache() {
        return this.l;
    }

    public void setOneshotCache(OneshotCache<byte[]> oneshotCache) {
        this.l = oneshotCache;
    }

    public int getNoSpeechTimeOut() {
        return this.m;
    }

    public void setNoSpeechTimeOut(int i) {
        this.m = i;
    }

    public int getMaxSpeechTimeOut() {
        return this.n;
    }

    public void setMaxSpeechTimeOut(int i) {
        this.n = i;
    }

    public String getSaveAudioPath() {
        return this.o;
    }

    public void setSaveAudioPath(String str) {
        this.o = str;
    }

    public ASRMode getMode() {
        return this.a;
    }

    public void setMode(ASRMode aSRMode) {
        this.a = aSRMode;
    }

    public void setUseContinuousRecognition(boolean z) {
        this.b = z;
    }

    public boolean isUseContinuousRecognition() {
        return this.b;
    }

    public void setWords(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("empty hot words");
        }
        this.q = a(strArr);
    }

    public void setWords(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("empty hot words");
        }
        if (!str.startsWith("\"") || !str.endsWith("\"")) {
            throw new IllegalArgumentException("illegal hot words");
        }
        this.q = str;
    }

    public void setBlackWords(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("empty hot words");
        }
        this.r = a(strArr);
    }

    public void setThreshold(double d) {
        this.s = d;
    }

    public void setCustomThreshold(String[] strArr, Double[] dArr) {
        if (strArr == null || dArr == null || strArr.length != dArr.length) {
            throw new IllegalArgumentException("set custom threshold data inconsistent!");
        }
        for (int i = 0; i < strArr.length; i++) {
            this.p.put(strArr[i], dArr[i]);
        }
    }

    public double getThreshold() {
        return this.s;
    }

    public String getWords() {
        return this.q;
    }

    public String getBlackWords() {
        return this.r;
    }

    public Map<String, Double> getCustomThreshold() {
        return this.p;
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
