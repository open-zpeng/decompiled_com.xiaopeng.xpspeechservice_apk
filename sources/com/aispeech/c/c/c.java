package com.aispeech.c.c;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
/* loaded from: classes.dex */
public class c implements com.aispeech.c.c.a {
    private static OkHttpClient a;
    private WebSocket b;

    public c(String str, b bVar) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new OkHttpClient().newBuilder().pingInterval(0L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).connectTimeout(30L, TimeUnit.SECONDS).dns(new com.aispeech.c.a.b()).build();
                }
            }
        }
        this.b = a.newWebSocket(new Request.Builder().url(str).build(), new a(this, bVar));
    }

    @Override // com.aispeech.c.c.a
    public final boolean a(String str) {
        WebSocket webSocket = this.b;
        if (webSocket == null) {
            return false;
        }
        return webSocket.send(str);
    }

    @Override // com.aispeech.c.c.a
    public final boolean a(byte[] bArr) {
        WebSocket webSocket = this.b;
        if (webSocket == null) {
            return false;
        }
        return webSocket.send(ByteString.of(bArr));
    }

    @Override // com.aispeech.c.c.a
    public final boolean a() {
        WebSocket webSocket = this.b;
        if (webSocket == null) {
            return false;
        }
        return webSocket.close(1000, null);
    }

    @Override // com.aispeech.c.c.a
    public final void b() {
        WebSocket webSocket = this.b;
        if (webSocket != null) {
            webSocket.cancel();
        }
    }

    /* loaded from: classes.dex */
    static class a extends WebSocketListener {
        private b a;
        private com.aispeech.c.c.a b;

        public a(com.aispeech.c.c.a aVar, b bVar) {
            this.b = aVar;
            this.a = bVar;
        }

        @Override // okhttp3.WebSocketListener
        public final void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            b bVar = this.a;
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // okhttp3.WebSocketListener
        public final void onMessage(WebSocket webSocket, String str) {
            super.onMessage(webSocket, str);
            b bVar = this.a;
            if (bVar != null) {
                bVar.a(str);
            }
        }

        @Override // okhttp3.WebSocketListener
        public final void onMessage(WebSocket webSocket, ByteString byteString) {
            super.onMessage(webSocket, byteString);
        }

        @Override // okhttp3.WebSocketListener
        public final void onClosing(WebSocket webSocket, int i, String str) {
            super.onClosing(webSocket, i, str);
            b bVar = this.a;
            if (bVar != null) {
                bVar.a(this.b, i, str);
            }
        }

        @Override // okhttp3.WebSocketListener
        public final void onClosed(WebSocket webSocket, int i, String str) {
            super.onClosed(webSocket, i, str);
            b bVar = this.a;
            if (bVar != null) {
                bVar.b(this.b, i, str);
            }
        }

        @Override // okhttp3.WebSocketListener
        public final void onFailure(WebSocket webSocket, Throwable th, Response response) {
            super.onFailure(webSocket, th, response);
            b bVar = this.a;
            if (bVar != null) {
                bVar.a(this.b, th);
            }
        }
    }
}
