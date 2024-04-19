package com.aispeech.lite.h;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class q extends m {
    private float[] a;
    private String[] c;
    private int[] d;
    private int[] g;
    private boolean e = true;
    private boolean f = false;
    private String h = "words=ni hao xiao chi;thresh=0.100;major=1;";

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (q) super.clone();
    }

    public final boolean d() {
        return this.e;
    }

    public final void a(boolean z) {
        this.e = z;
    }

    public q() {
        a("cn.wakeup");
        c(0);
        d(0);
    }

    public final void a(float[] fArr) {
        this.a = fArr;
    }

    public final void a(String[] strArr) {
        this.c = strArr;
    }

    public final void a(int[] iArr) {
        this.d = iArr;
    }

    public final void b(boolean z) {
        this.f = z;
    }

    public final boolean e() {
        return this.f;
    }

    public final void b(int[] iArr) {
        this.g = iArr;
    }

    private String g() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = this.c;
            if (i2 >= strArr.length) {
                break;
            }
            sb.append(strArr[i2]);
            if (i2 == this.c.length - 1) {
                sb.append(";");
            } else {
                sb.append(",");
            }
            i2++;
        }
        StringBuilder sb2 = new StringBuilder();
        int i3 = 0;
        while (true) {
            float[] fArr = this.a;
            if (i3 >= fArr.length) {
                break;
            }
            sb2.append(fArr[i3]);
            if (i3 == this.a.length - 1) {
                sb2.append(";");
            } else {
                sb2.append(",");
            }
            i3++;
        }
        if (this.d != null) {
            StringBuilder sb3 = new StringBuilder();
            while (true) {
                int[] iArr = this.d;
                if (i < iArr.length) {
                    sb3.append(iArr[i]);
                    if (i == this.d.length - 1) {
                        sb3.append(";");
                    } else {
                        sb3.append(",");
                    }
                    i++;
                } else {
                    return "words=" + sb.toString() + "thresh=" + sb2.toString() + "dcheck=" + sb3.toString() + "major=1;";
                }
            }
        } else if (this.g != null) {
            StringBuilder sb4 = new StringBuilder();
            while (true) {
                int[] iArr2 = this.g;
                if (i < iArr2.length) {
                    sb4.append(iArr2[i]);
                    if (i == this.g.length - 1) {
                        sb4.append(";");
                    } else {
                        sb4.append(",");
                    }
                    i++;
                } else {
                    return "words=" + sb.toString() + "thresh=" + sb2.toString() + "major=" + sb4.toString();
                }
            }
        } else {
            return "words=" + sb.toString() + "thresh=" + sb2.toString() + "major=1;";
        }
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.c != null) {
                jSONObject.put("env", g());
            } else {
                jSONObject.put("env", this.h);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final String toString() {
        return a().toString();
    }
}
