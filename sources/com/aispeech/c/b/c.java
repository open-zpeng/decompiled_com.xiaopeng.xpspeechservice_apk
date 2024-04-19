package com.aispeech.c.b;

import com.aispeech.common.Log;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/* loaded from: classes.dex */
public class c implements d {
    private static final MediaType a = MediaType.parse("application/json; charset=utf-8");
    private static d b;
    private OkHttpClient c = new OkHttpClient().newBuilder().pingInterval(30, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).dns(new com.aispeech.c.a.b()).sslSocketFactory(new com.aispeech.a(), com.aispeech.a.a).build();
    private Call d;

    private c() {
    }

    public static d a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    @Override // com.aispeech.c.b.d
    public final void a(String str, final a aVar) {
        Log.d("HttpImpl", "GET " + str);
        this.d = this.c.newCall(new Request.Builder().url(str).get().build());
        this.d.enqueue(new Callback() { // from class: com.aispeech.c.b.c.1
            @Override // okhttp3.Callback
            public final void onFailure(Call call, IOException iOException) {
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.onFailure(c.this, iOException);
                }
            }

            @Override // okhttp3.Callback
            public final void onResponse(Call call, Response response) throws IOException {
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.onResponse(c.this, new e(response));
                }
            }
        });
    }

    @Override // com.aispeech.c.b.d
    public final void a(String str, String str2, final a aVar) {
        Log.d("HttpImpl", "POST json " + str + "  " + str2);
        this.d = this.c.newCall(new Request.Builder().url(str).addHeader("Content-Type", "application/json").post(RequestBody.create(a, str2)).build());
        this.d.enqueue(new Callback() { // from class: com.aispeech.c.b.c.2
            @Override // okhttp3.Callback
            public final void onFailure(Call call, IOException iOException) {
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.onFailure(c.this, iOException);
                }
            }

            @Override // okhttp3.Callback
            public final void onResponse(Call call, Response response) throws IOException {
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.onResponse(c.this, new e(response));
                }
            }
        });
    }

    @Override // com.aispeech.c.b.d
    public final void b() {
        Call call = this.d;
        if (call != null && call.isExecuted() && !this.d.isCanceled()) {
            Log.d("HttpImpl", "cancel");
            this.d.cancel();
        }
    }
}
