package com.aispeech.lite.c;

import android.text.TextUtils;
import com.aispeech.common.JSONUtil;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class f extends com.aispeech.lite.a {
    private JSONObject a;
    private String b = "";
    private int c = 2;
    private int d = 1;
    private int e = 16;
    private boolean f = false;
    private int g = 200;

    public final boolean h() {
        return this.f;
    }

    public final void d(boolean z) {
        this.f = z;
    }

    public final int i() {
        return this.g;
    }

    public final void a(int i) {
        this.g = i;
    }

    public final void b(int i) {
        this.c = i;
    }

    public final int j() {
        return this.c;
    }

    public final void c(int i) {
        this.d = i;
    }

    public final void a(String str) {
        this.b = str;
    }

    public final JSONObject k() {
        this.a = new JSONObject();
        if (!TextUtils.isEmpty(this.b)) {
            JSONUtil.putQuietly(this.a, "resBinPath", this.b);
        }
        JSONUtil.putQuietly(this.a, "channels", Integer.valueOf(this.c));
        JSONUtil.putQuietly(this.a, "micNum", Integer.valueOf(this.d));
        JSONUtil.putQuietly(this.a, "sampleFormat", Integer.valueOf(this.e));
        return this.a;
    }

    public final String toString() {
        return k().toString();
    }
}
