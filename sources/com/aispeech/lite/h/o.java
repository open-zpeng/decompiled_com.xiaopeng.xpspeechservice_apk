package com.aispeech.lite.h;

import com.aispeech.common.JSONUtil;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class o extends m {
    private int a = 300;
    private boolean c = true;
    private JSONObject d;

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (o) super.clone();
    }

    public o() {
        t();
    }

    public final int d() {
        return this.a;
    }

    public final void a(int i) {
        this.a = i;
    }

    public final void a(boolean z) {
        this.c = z;
    }

    public final boolean e() {
        return this.c;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final JSONObject a() {
        this.d = new JSONObject();
        JSONUtil.putQuietly(this.d, "pauseTime", Integer.valueOf(this.a));
        return this.d;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final String toString() {
        return a().toString();
    }
}
