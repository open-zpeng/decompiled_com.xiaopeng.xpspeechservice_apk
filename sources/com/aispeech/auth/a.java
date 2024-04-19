package com.aispeech.auth;

import android.content.Context;
import com.aispeech.DUILiteSDK;
/* loaded from: classes.dex */
public class a {
    private static volatile a a = null;
    private c b = new c();
    private b c = new b(this, (byte) 0);
    private DUILiteSDK.InitListener d;

    private a() {
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    public final void a(Context context, com.aispeech.auth.a.a aVar, DUILiteSDK.InitListener initListener) {
        this.d = initListener;
        this.b.a(context, aVar, this.c);
    }

    public final void b() {
        this.b.a();
    }

    public final boolean c() {
        return this.b.b();
    }

    public final d d() {
        return this.b.c();
    }
}
