package com.aispeech.c.b;

import android.os.Handler;
import android.os.Looper;
/* loaded from: classes.dex */
public final class b implements d {
    private static d a = new b();
    private Handler b = new Handler(Looper.getMainLooper());

    private b() {
    }

    public static d a() {
        return a;
    }

    @Override // com.aispeech.c.b.d
    public final void a(String str, final a aVar) {
        this.b.postDelayed(new Runnable() { // from class: com.aispeech.c.b.b.1
            @Override // java.lang.Runnable
            public final void run() {
                a.this.onFailure(b.a, new f("NoImplementHttpException"));
            }
        }, 200L);
    }

    @Override // com.aispeech.c.b.d
    public final void a(String str, String str2, final a aVar) {
        this.b.postDelayed(new Runnable() { // from class: com.aispeech.c.b.b.2
            @Override // java.lang.Runnable
            public final void run() {
                a.this.onFailure(b.a, new f("NoImplementHttpException"));
            }
        }, 200L);
    }

    @Override // com.aispeech.c.b.d
    public final void b() {
    }
}
