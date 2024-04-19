package com.aispeech.kernel;

import android.util.Log;
/* loaded from: classes.dex */
public abstract class Abs {
    protected long a = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean a(String str) {
        if (this.a == 0) {
            Log.e("Opus", "core is null when call " + str);
            return false;
        }
        return true;
    }
}
