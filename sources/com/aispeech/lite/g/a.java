package com.aispeech.lite.g;

import com.aispeech.AIError;
import com.aispeech.c.b.e;
import com.aispeech.common.AITimer;
import com.aispeech.common.AuthUtil;
import com.aispeech.common.Log;
import com.aispeech.common.NetworkUtil;
import com.aispeech.lite.h.f;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.TimerTask;
import java.util.UUID;
/* loaded from: classes.dex */
public final class a {
    private com.aispeech.lite.g.b a;
    private String d;
    private boolean b = false;
    private boolean c = false;
    private b e = null;

    static /* synthetic */ boolean a(a aVar) {
        aVar.c = false;
        return false;
    }

    static /* synthetic */ boolean e(a aVar) {
        aVar.b = true;
        return true;
    }

    public final synchronized void a(f fVar, com.aispeech.lite.g.b bVar) {
        this.a = bVar;
        this.b = false;
        this.c = false;
        Log.d("CloudTTSHttpClient", "CTTS.POST: " + fVar.k());
        String str = this.d;
        StringBuilder sb = new StringBuilder();
        sb.append(new Date().getTime());
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(UUID.randomUUID());
        String sb4 = sb3.toString();
        String str2 = fVar.l() + "?productId=" + fVar.f() + "&apikey=" + fVar.g() + "&deviceName=" + fVar.i() + "&timestamp=" + sb2 + "&nonce=" + sb4 + "&sig=" + AuthUtil.getSignature(fVar.i() + sb4 + fVar.f() + sb2, str);
        Log.d("CloudTTSHttpClient", "CTTS.URL: " + str2);
        if (NetworkUtil.isNetworkConnected(com.aispeech.lite.c.b())) {
            com.aispeech.c.a.a().a(str2, fVar.k(), new C0004a(this, (byte) 0));
            Log.d("CloudTTSHttpClient", "CTTS.START");
            c();
            this.e = new b();
            try {
                AITimer.getInstance().schedule(this.e, 3000L);
                return;
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d("CloudTTSHttpClient", "CTTS.ERROR: 网络连接错误");
        if (this.a != null && !this.c) {
            this.a.a(new AIError((int) AIError.ERR_NETWORK, AIError.ERR_DESCRIPTION_ERR_NETWORK));
        }
    }

    public final synchronized void a() {
        com.aispeech.c.a.a().b();
        Log.d("CloudTTSHttpClient", "closeHttp");
        this.c = true;
        c();
    }

    public final synchronized void b() {
        Log.d("CloudTTSHttpClient", "destroy");
        a();
        if (this.a != null) {
            this.a = null;
        }
    }

    /* renamed from: com.aispeech.lite.g.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0004a implements com.aispeech.c.b.a {
        private C0004a() {
        }

        /* synthetic */ C0004a(a aVar, byte b) {
            this();
        }

        @Override // com.aispeech.c.b.a
        public final void onFailure(com.aispeech.c.b.d dVar, IOException iOException) {
            AIError aIError;
            Log.d("CloudTTSHttpClient", "CTTS.ERROR onFailure: " + iOException.toString());
            if (iOException instanceof UnknownHostException) {
                aIError = new AIError((int) AIError.ERR_DNS, AIError.ERR_DESCRIPTION_ERR_DNS);
            } else {
                aIError = new AIError((int) AIError.ERR_NETWORK, AIError.ERR_DESCRIPTION_ERR_NETWORK);
            }
            String localizedMessage = iOException.getLocalizedMessage();
            if (!"Socket closed".equals(localizedMessage) && !"Canceled".equals(localizedMessage)) {
                if (a.this.a != null && !a.this.c) {
                    a.this.a.a(aIError);
                }
                a.a(a.this);
                return;
            }
            a.a(a.this);
        }

        @Override // com.aispeech.c.b.a
        public final void onResponse(com.aispeech.c.b.d dVar, e eVar) throws IOException {
            if (eVar.b()) {
                if (!a.this.b) {
                    a.e(a.this);
                }
                InputStream c = eVar.c();
                byte[] bArr = new byte[1024];
                if (c != null) {
                    while (true) {
                        int read = c.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        Log.d("CloudTTSHttpClient", "response CTTS data length: " + read);
                        if (a.this.a != null) {
                            a.this.a.a(bArr, read);
                        }
                    }
                    Log.d("CloudTTSHttpClient", "response CTTS data length: 0");
                    if (a.this.a != null) {
                        a.this.a.a(bArr, 0);
                        return;
                    }
                    return;
                }
                Log.e("CloudTTSHttpClient", "response is null.");
                return;
            }
            Log.d("CloudTTSHttpClient", "CTTS.ERROR response code : " + eVar.a());
            if (a.this.a != null && !a.this.c) {
                if (eVar.a() == 401) {
                    a.this.a.a(new AIError((int) AIError.ERR_DEVICE_ID_CONFLICT_TTS, AIError.ERR_DESCRIPTION_DEVICE_ID_CONFLICT));
                } else {
                    a.this.a.a(new AIError((int) AIError.ERR_NETWORK, AIError.ERR_DESCRIPTION_ERR_NETWORK));
                }
            }
            a.a(a.this);
        }
    }

    public final void a(String str) {
        this.d = str;
    }

    private void c() {
        b bVar = this.e;
        if (bVar != null) {
            bVar.cancel();
            this.e = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends TimerTask {
        b() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            if (a.this.a != null && !a.this.b) {
                Log.e("CloudTTSHttpClient", "connect timeout");
                if (a.this.a != null && !a.this.c) {
                    a.this.a.a(new AIError((int) AIError.ERR_CONNECT_TIMEOUT, AIError.ERR_DESCRIPTION_CONNECT_TIMEOUT));
                }
                a.a(a.this);
                a.this.a();
            }
        }
    }
}
