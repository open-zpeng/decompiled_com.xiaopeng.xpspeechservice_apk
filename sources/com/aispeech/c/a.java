package com.aispeech.c;

import com.aispeech.c.b.b;
import com.aispeech.c.b.c;
import com.aispeech.c.b.d;
/* loaded from: classes.dex */
public final class a {
    private static boolean a = false;
    private static boolean b = false;

    public static d a() {
        if (!a) {
            try {
                return c.a();
            } catch (NoClassDefFoundError e) {
                a = true;
            }
        }
        return b.a();
    }

    public static com.aispeech.c.c.a a(String str, com.aispeech.c.c.b bVar) {
        if (!b) {
            try {
                return new com.aispeech.c.c.c(str, bVar);
            } catch (NoClassDefFoundError e) {
                b = true;
                return null;
            }
        }
        return null;
    }
}
