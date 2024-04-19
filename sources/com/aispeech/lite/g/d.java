package com.aispeech.lite.g;

import com.aispeech.common.Log;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes.dex */
public final class d {
    private com.aispeech.c.c.a a;
    private c b;
    private Timer d;
    private int c = 0;
    private boolean e = false;

    static /* synthetic */ boolean d(d dVar) {
        dVar.e = false;
        return false;
    }

    public final synchronized void a(String str, c cVar) {
        this.b = cVar;
        if (this.a == null) {
            this.c = 0;
            this.e = false;
            Log.d("WebsocketClient", "new websocket");
            this.a = com.aispeech.c.a.a(str, new a());
            if (this.d != null) {
                this.d.cancel();
            }
            this.d = new Timer();
            this.d.schedule(new TimerTask() { // from class: com.aispeech.lite.g.d.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public final void run() {
                    if (d.this.a != null && d.this.b != null && d.this.c == 0) {
                        Log.d("WebsocketClient", "websocket connect timeout");
                        d.this.b.onError("network error, connect asr server timeout");
                    }
                }
            }, 3000L);
        }
    }

    public final synchronized void a(String str) {
        if (this.a != null) {
            Log.d("WebsocketClient", "sendText " + str);
            this.a.a(str);
        }
    }

    public final synchronized void a(byte[] bArr) {
        if (this.a != null) {
            Log.d("WebsocketClient", "binary length :" + bArr.length);
            this.a.a(bArr);
            return;
        }
        Log.e("WebsocketClient", "mWebSocket IS NULL");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(com.aispeech.c.c.a aVar) {
        if (aVar == this.a) {
            this.a = null;
            Log.i("WebsocketClient", "resetWebSocket");
        }
    }

    public final synchronized void a() {
        if (this.a != null) {
            Log.d("WebsocketClient", "closeWebSocket");
            this.e = true;
            this.a.b();
            this.a = null;
            this.c = 3;
        }
    }

    public final synchronized void b() {
        Log.d("WebsocketClient", "destroy");
        a();
        if (this.b != null) {
            this.b = null;
        }
    }

    /* loaded from: classes.dex */
    public class a implements com.aispeech.c.c.b {
        public a() {
        }

        @Override // com.aispeech.c.c.b
        public final void a() {
            Log.i("WebsocketClient", "onOpen");
            d.this.c = 1;
            if (d.this.b != null) {
                d.this.b.onOpen();
            }
        }

        @Override // com.aispeech.c.c.b
        public final void a(String str) {
            Log.i("WebsocketClient", "Receiving: " + str);
            try {
                if (d.this.b != null) {
                    d.this.b.onMessage(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.aispeech.c.c.b
        public final void a(com.aispeech.c.c.a aVar, int i, String str) {
            aVar.a();
            Log.i("WebsocketClient", "Closing: " + i + " " + str);
        }

        @Override // com.aispeech.c.c.b
        public final void b(com.aispeech.c.c.a aVar, int i, String str) {
            d.this.c = 2;
            Log.i("WebsocketClient", "Closed: " + i + " " + str);
            try {
                if (d.this.b != null && aVar == d.this.a && !d.this.e) {
                    d.this.b.onClose();
                }
                d.d(d.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            d.this.a(aVar);
        }

        @Override // com.aispeech.c.c.b
        public final void a(com.aispeech.c.c.a aVar, Throwable th) {
            d.this.c = 2;
            th.printStackTrace();
            Log.i("WebsocketClient", "onFailure BEFORE: ");
            try {
                if (d.this.b != null && aVar == d.this.a && !d.this.e) {
                    d.this.b.onError(th.getMessage());
                }
                d.d(d.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("WebsocketClient", "onFailure END: ");
            d.this.a(aVar);
        }
    }
}
