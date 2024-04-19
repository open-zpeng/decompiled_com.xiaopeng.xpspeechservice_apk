package com.aispeech.c.b;

import java.io.IOException;
import java.io.InputStream;
import okhttp3.Response;
/* loaded from: classes.dex */
public final class e {
    private Response a;

    public final int a() {
        Response response = this.a;
        if (response != null) {
            return response.code();
        }
        return 0;
    }

    public final boolean b() {
        return a() >= 200 && a() < 300;
    }

    public final InputStream c() {
        Response response = this.a;
        if (response == null || response.body() == null) {
            return null;
        }
        return this.a.body().byteStream();
    }

    public final String d() {
        try {
            if (this.a == null || this.a.body() == null) {
                return null;
            }
            return this.a.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public e(Response response) {
        this.a = response;
    }
}
