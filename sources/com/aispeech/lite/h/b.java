package com.aispeech.lite.h;

import com.aispeech.common.JSONUtil;
import org.json.JSONObject;
/* loaded from: classes.dex */
public abstract class b implements Cloneable {
    private JSONObject a = new JSONObject();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(String str) {
        JSONUtil.putQuietly(this.a, "coreType", str);
    }

    public JSONObject a() {
        return this.a;
    }

    public String toString() {
        return a().toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
