package com.aispeech.auth;

import com.aispeech.common.AuthError;
import com.aispeech.lite.AuthType;
/* loaded from: classes.dex */
public final class f {
    private AuthError.AUTH_ERR_MSG b;
    private AuthType d;
    private boolean a = false;
    private int c = -1;

    public f(AuthType authType) {
        this.d = AuthType.ONLINE;
        this.d = authType;
    }

    public final AuthType a() {
        return this.d;
    }

    public final boolean b() {
        return this.a;
    }

    public final f c() {
        this.a = true;
        return this;
    }

    public final AuthError.AUTH_ERR_MSG d() {
        return this.b;
    }

    public final f a(AuthError.AUTH_ERR_MSG auth_err_msg) {
        this.b = auth_err_msg;
        return this;
    }

    public final int e() {
        return this.c;
    }

    public final f a(int i) {
        this.c = i;
        return this;
    }

    public final String toString() {
        return "ProfileState{valid=" + this.a + ", authErrMsg=" + this.b + ", timesLimit=" + this.c + '}';
    }
}
