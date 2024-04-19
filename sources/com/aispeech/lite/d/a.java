package com.aispeech.lite.d;

import com.aispeech.kernel.Gram;
import com.aispeech.lite.c.g;
import com.aispeech.lite.h;
import com.aispeech.lite.h.j;
/* loaded from: classes.dex */
public final class a extends h {
    private Gram t;
    private g u;
    private j v;
    private b w;

    public final void a(b bVar, g gVar) {
        a(bVar, gVar.b(), "AIGrammarProcessor", "grammar");
        this.w = bVar;
        this.t = new Gram();
        a(h.a.MSG_NEW, gVar);
    }

    public final void a(j jVar) {
        if (a()) {
            try {
                a(h.a.MSG_START, jVar.g());
                return;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return;
            }
        }
        i();
    }

    /* renamed from: com.aispeech.lite.d.a$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[h.a.values().length];

        static {
            try {
                a[h.a.MSG_NEW.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[h.a.MSG_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[h.a.MSG_RELEASE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[h.a.MSG_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    @Override // com.aispeech.lite.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final void a(com.aispeech.lite.h.a r10, android.os.Message r11) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.lite.d.a.a(com.aispeech.lite.h$a, android.os.Message):void");
    }

    @Override // com.aispeech.lite.h
    public final void m() {
    }

    @Override // com.aispeech.lite.h
    public final void n() {
    }
}
