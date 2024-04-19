package com.aispeech.lite.h;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.os.Build;
import org.json.JSONObject;
@TargetApi(23)
/* loaded from: classes.dex */
public abstract class n extends m implements Cloneable {
    private AudioAttributes c;
    private String h;
    protected int a = 3;
    private boolean d = true;
    private String e = "";
    private boolean f = false;
    private String g = "cloud";
    private boolean i = false;

    public abstract boolean e();

    public abstract String n();

    public String d() {
        return this.g;
    }

    public final void v(String str) {
        this.g = str;
    }

    public n() {
        if (Build.VERSION.SDK_INT >= 23) {
            this.c = new AudioAttributes.Builder().setUsage(1).setContentType(2).build();
        }
    }

    public final void e(int i) {
        this.a = i;
    }

    @TargetApi(23)
    public final void a(AudioAttributes audioAttributes) {
        this.c = audioAttributes;
    }

    @TargetApi(23)
    public final AudioAttributes x() {
        return this.c;
    }

    public final int y() {
        return this.a;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public final boolean z() {
        return this.d;
    }

    public final void b(boolean z) {
        this.d = z;
    }

    public final boolean A() {
        return this.i;
    }

    public final void c(boolean z) {
        this.i = z;
    }

    public final void d(boolean z) {
        this.f = z;
    }

    public final boolean B() {
        return this.f;
    }

    public final void w(String str) {
        this.h = str;
    }

    public final String C() {
        return this.h;
    }

    public String o() {
        return this.e;
    }

    public final void x(String str) {
        this.e = str;
    }

    public JSONObject a_() {
        return null;
    }
}
