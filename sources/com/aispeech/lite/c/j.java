package com.aispeech.lite.c;

import android.text.TextUtils;
import com.aispeech.common.JSONUtil;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class j extends com.aispeech.lite.a {
    private String a;
    private JSONObject b;
    private int c = 300;
    private boolean d = false;
    private boolean e = false;

    public final boolean h() {
        return this.d;
    }

    public final void d(boolean z) {
        this.d = z;
    }

    public final void e(boolean z) {
        this.e = z;
    }

    public final boolean i() {
        return this.e;
    }

    public final void a(int i) {
        this.c = i;
    }

    public final void a(String str) {
        this.a = str;
    }

    public final JSONObject j() {
        this.b = new JSONObject();
        if (!TextUtils.isEmpty(this.a)) {
            JSONUtil.putQuietly(this.b, "resBinPath", this.a);
        }
        JSONUtil.putQuietly(this.b, "pauseTime", Integer.valueOf(this.c));
        if (this.e) {
            JSONUtil.putQuietly(this.b, "fullmode", 1);
        }
        return this.b;
    }

    public String toString() {
        return j().toString();
    }
}
