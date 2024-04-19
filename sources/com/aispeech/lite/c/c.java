package com.aispeech.lite.c;
/* loaded from: classes.dex */
public final class c extends com.aispeech.lite.a {
    public static String a = "wss://dds.dui.ai/dds/v2/";
    public static String b = "http://dds.dui.ai/cinfo/v1";
    private String c = "websocket";
    private String d = "prod";
    private int f = 10000;
    private String g = "dui-lite";
    private String l = a;
    private String m = b;
    private boolean n = false;
    private String h = com.aispeech.lite.c.a().a();
    private String i = com.aispeech.lite.c.a().c();
    private String j = com.aispeech.lite.c.a().e();
    private String k = com.aispeech.lite.c.a().b();
    private String e = com.aispeech.lite.c.a().f();

    public final String h() {
        return this.e;
    }

    public final String i() {
        return this.c;
    }

    public final String j() {
        return this.d;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final String k() {
        return this.h;
    }

    public final String l() {
        return this.l;
    }

    public final c b(String str) {
        this.l = str;
        return this;
    }

    public final boolean m() {
        return this.n;
    }

    public final c d(boolean z) {
        this.n = z;
        return this;
    }

    public final String toString() {
        return "CloudDMConfig{serviceType='" + this.c + "', aliasKey='" + this.d + "', deviceName='" + this.e + "', nativeApiTimeout=" + this.f + ", userId='" + this.g + "', productId='" + this.h + "', apiKey='" + this.i + "', secret='" + this.j + "', productKey='" + this.k + "', isDMRoute=false, cInfoServerAddress=" + this.m + '}';
    }
}
