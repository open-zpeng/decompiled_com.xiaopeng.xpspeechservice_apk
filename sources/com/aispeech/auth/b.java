package com.aispeech.auth;

import com.aispeech.DDSDnsClient;
import com.aispeech.common.AuthError;
import com.aispeech.common.Log;
/* loaded from: classes.dex */
public final class b {
    private /* synthetic */ a a;

    public final void a(AuthError.AUTH_ERR_MSG auth_err_msg) {
        if (a.a(this.a) != null) {
            a.a(this.a).error(auth_err_msg.getId(), auth_err_msg.getValue());
        }
    }

    public final void a() {
        if (a.a(this.a) != null) {
            a.a(this.a).success();
        }
    }

    private b(a aVar) {
        this.a = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ b(a aVar, byte b) {
        this(aVar);
    }

    public static String a(String str) {
        try {
            return DDSDnsClient.dds_get_host_by_name(str, 2, 3, null);
        } catch (NoClassDefFoundError e) {
            Log.d("DDSDnsClientJniHelper", "dds_get_host_by_name  " + e);
            return "";
        }
    }
}
