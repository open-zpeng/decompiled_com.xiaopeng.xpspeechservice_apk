package com.aispeech.export.exception;
/* loaded from: classes.dex */
public class IllegalPinyinException extends IllegalArgumentException {
    public IllegalPinyinException() {
        super("Illegal pinyin,check if the parameter is legal pinyin");
    }

    public IllegalPinyinException(String str) {
        super(str);
    }
}
