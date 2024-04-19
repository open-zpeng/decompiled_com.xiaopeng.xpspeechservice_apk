package com.aispeech.lite.c;
/* loaded from: classes.dex */
public final class k {
    private int a = 1200;
    private int b = 400;
    private String[] c;
    private j d;

    public final String[] a() {
        return this.c;
    }

    public final void a(String[] strArr) {
        this.c = strArr;
    }

    public final int b() {
        return this.a;
    }

    public final void a(int i) {
        this.a = i;
    }

    public final int c() {
        return this.b;
    }

    public final void b(int i) {
        this.b = i;
    }

    public final j d() {
        return this.d;
    }

    public final void a(j jVar) {
        this.d = jVar;
    }

    public final String toString() {
        return "AIOneshotConfig{cacheAudioTime=" + this.a + ", middleTime=" + this.b + ", wakeupWord=" + this.c + ", vadConfig=" + this.d.j() + ", saveAudioPath = " + ((String) null) + '}';
    }
}
