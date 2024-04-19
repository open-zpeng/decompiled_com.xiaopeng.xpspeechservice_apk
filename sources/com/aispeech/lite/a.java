package com.aispeech.lite;

import android.content.Context;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class a implements Cloneable {
    private String[] a;
    private boolean b = true;
    private boolean c = false;
    private boolean d = true;

    public final void a(boolean z) {
        this.b = z;
    }

    public final boolean a() {
        return this.b;
    }

    public Context b() {
        return c.b();
    }

    public final void a(String[] strArr) {
        this.a = strArr;
    }

    public final String[] c() {
        return this.a;
    }

    public final boolean d() {
        return this.c;
    }

    public final void b(boolean z) {
        this.c = z;
    }

    public final boolean e() {
        return this.d;
    }

    public final void c(boolean z) {
        this.d = z;
    }

    @Override // 
    /* renamed from: f */
    public a clone() throws CloneNotSupportedException {
        return (a) super.clone();
    }

    public JSONObject g() {
        return null;
    }
}
