package com.aispeech.lite.h;

import android.text.TextUtils;
import com.aispeech.common.Log;
import com.aispeech.common.Util;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class j extends m implements Cloneable {
    private String a = "";
    private String c = "";
    private String d = "";

    @Override // com.aispeech.lite.h.m, com.aispeech.lite.h.b
    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (j) super.clone();
    }

    public final String d() {
        return this.a;
    }

    public final void b(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e(b, "Invalid outputPath");
        } else {
            this.a = str;
        }
    }

    public final void h(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e(b, "Invalid ebnf");
        } else {
            this.d = str;
        }
    }

    public final JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("outputPath", this.a);
            if (TextUtils.isEmpty(this.d)) {
                if (!TextUtils.isEmpty(this.c)) {
                    this.d = j(this.c);
                } else {
                    throw new IllegalArgumentException("请指定 xbnf 模板路径");
                }
            }
            jSONObject.put("ebnf", this.d);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final j g() throws CloneNotSupportedException {
        return (j) super.clone();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static String j(String str) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2 = null;
        bufferedReader = null;
        bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(new FileInputStream(str), Charset.forName(Util.UTF8)));
                    while (true) {
                        try {
                            String readLine = bufferedReader3.readLine();
                            if (readLine == null) {
                                break;
                            }
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(readLine);
                            sb2.append("\n");
                            sb.append(sb2.toString());
                            bufferedReader2 = sb2;
                        } catch (IOException e) {
                            e = e;
                            bufferedReader = bufferedReader3;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                bufferedReader = bufferedReader;
                            }
                            return sb.toString();
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader3;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader3.close();
                    bufferedReader = bufferedReader2;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return sb.toString();
    }
}
