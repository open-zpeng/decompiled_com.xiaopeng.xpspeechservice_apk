package com.aispeech.lite;
/* loaded from: classes.dex */
public enum Languages {
    CHINESE("cn", "汉语"),
    ENGLISH("en", "英语"),
    PORTUGUESE("pt", "葡语"),
    SPANISH("es", "西班牙语"),
    GERMAN("de", "德语"),
    RUSSIAN("ru", "俄语"),
    JAPANESE("jp", "日语"),
    THAI("th", "泰语"),
    VIETNAMESE("vn", "越南语"),
    ARABIC("arb", "阿拉伯语"),
    INDIA("in", "印度语"),
    TURKEY("other", "土耳其语"),
    FRENCH("other", "法语");
    
    private String a;
    private String b;

    Languages(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final String getLanguage() {
        return this.a;
    }

    public final String getTips() {
        return this.b;
    }
}
