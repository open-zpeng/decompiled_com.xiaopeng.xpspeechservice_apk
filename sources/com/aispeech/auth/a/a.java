package com.aispeech.auth.a;

import com.aispeech.lite.AuthType;
import java.util.Map;
/* loaded from: classes.dex */
public final class a {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private boolean m;
    private long n;
    private String o;
    private Map<String, Object> p;
    private AuthType q;

    /* synthetic */ a(AnonymousClass1 anonymousClass1, byte b) {
        this(anonymousClass1);
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }

    public final String f() {
        return this.i;
    }

    public final String g() {
        return this.f;
    }

    public final String h() {
        return this.h;
    }

    public final boolean i() {
        return this.m;
    }

    public final long j() {
        return this.n;
    }

    public final String k() {
        return this.j;
    }

    public final String l() {
        return this.k;
    }

    public final String m() {
        return this.o;
    }

    public final void n() {
        this.o = null;
    }

    public final String o() {
        return this.l;
    }

    public final String p() {
        return this.g;
    }

    public final Map<String, Object> q() {
        return this.p;
    }

    public final AuthType r() {
        return this.q;
    }

    public final a s() {
        this.m = false;
        return this;
    }

    private a(AnonymousClass1 anonymousClass1) {
        this.j = "/auth/device/register";
        this.k = "/auth/device/login";
        this.l = "/auth/apikey/verify";
        this.m = true;
        this.n = 5000L;
        this.a = anonymousClass1.a;
        this.b = anonymousClass1.b;
        this.c = anonymousClass1.c;
        this.d = anonymousClass1.d;
        this.e = anonymousClass1.e;
        this.i = anonymousClass1.f;
        this.f = anonymousClass1.g;
        this.p = anonymousClass1.m;
        this.n = anonymousClass1.j;
        this.m = anonymousClass1.i;
        this.h = anonymousClass1.h;
        this.o = anonymousClass1.k;
        this.g = anonymousClass1.l;
        this.q = anonymousClass1.n;
    }

    public final String toString() {
        return "AIAuthConfig{productId='" + this.a + "', productKey='" + this.b + "', productSecret='" + this.c + "', apiKey='" + this.d + "', serverApiKey='" + this.e + "', customDeviceId='" + this.f + "', customSHA256='" + ((String) null) + "', profilePath='" + this.h + "', offlineProfileName='" + ((String) null) + "', authServer='" + this.i + "', registerPath='" + this.j + "', loginPath='" + this.k + "', verifyPath='" + this.l + "', needReplaceProfile=" + this.m + ", authTimeout=" + this.n + ", deviceInfoMap=" + this.p + ", licenceId=" + this.o + ", customDeviceName=" + this.g + '}';
    }

    /* renamed from: com.aispeech.auth.a.a$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String g;
        private String h;
        private String k;
        private String l;
        private Map<String, Object> m;
        private String f = "https://auth.dui.ai";
        private boolean i = true;
        private long j = 5000;
        private AuthType n = AuthType.ONLINE;

        public final AnonymousClass1 a(AuthType authType) {
            this.n = authType;
            return this;
        }

        public final AnonymousClass1 a(String str) {
            this.a = str;
            return this;
        }

        public final AnonymousClass1 b(String str) {
            this.b = str;
            return this;
        }

        public final AnonymousClass1 c(String str) {
            this.c = str;
            return this;
        }

        public final AnonymousClass1 d(String str) {
            this.d = str;
            return this;
        }

        public final AnonymousClass1 e(String str) {
            this.e = str;
            return this;
        }

        public final AnonymousClass1 f(String str) {
            this.f = str;
            return this;
        }

        public final AnonymousClass1 g(String str) {
            this.g = str;
            return this;
        }

        public final AnonymousClass1 a(Map<String, Object> map) {
            this.m = map;
            return this;
        }

        public final AnonymousClass1 h(String str) {
            this.h = str;
            return this;
        }

        public final AnonymousClass1 a(long j) {
            this.j = j;
            return this;
        }

        public final AnonymousClass1 i(String str) {
            this.k = str;
            return this;
        }

        public final AnonymousClass1 j(String str) {
            this.l = str;
            return this;
        }

        public final a a() {
            return new a(this, (byte) 0);
        }
    }
}
