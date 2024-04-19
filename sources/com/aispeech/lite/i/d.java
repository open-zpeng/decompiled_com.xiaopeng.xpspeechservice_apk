package com.aispeech.lite.i;

import android.text.TextUtils;
import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.common.FileUtils;
import com.aispeech.common.Log;
import com.aispeech.common.Transformer;
import com.aispeech.export.Vocab;
import com.aispeech.kernel.Semantic;
import com.aispeech.kernel.SemanticDUI;
import com.aispeech.lite.SemanticType;
import com.aispeech.lite.c.h;
import com.aispeech.lite.g;
import com.aispeech.lite.h.k;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class d extends g {
    private com.aispeech.lite.a.a e;
    private h f;
    private k g;
    private Semantic h;
    private b i;
    private SemanticDUI j;
    private a k;
    private String l;
    private String m;

    public d(com.aispeech.lite.a.a aVar) {
        super("local-sem");
        this.e = aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0120  */
    @Override // com.aispeech.lite.g, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 427
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.lite.i.d.run():void");
    }

    private int c() {
        h hVar = this.f;
        if (hVar != null) {
            String jSONObject = hVar.g().toString();
            Log.d("LocalSemanticKernel", "config: " + jSONObject);
            this.i = new b(this, (byte) 0);
            this.h = new Semantic();
            if (this.h.initSemantic(jSONObject, this.i) == 0) {
                Log.e("LocalSemanticKernel", "Navi 引擎初始化失败,请检查资源文件是否在指定路径下！");
                return -1;
            }
            Log.d("LocalSemanticKernel", "Navi 引擎初始化成功");
            return 0;
        }
        Log.e("LocalSemanticKernel", "config == null");
        return -1;
    }

    private int d() {
        h hVar = this.f;
        if (hVar != null) {
            String jSONObject = hVar.o().toString();
            Log.d("LocalSemanticKernel", "initDuiSemantic: configStr = " + jSONObject);
            this.k = new a(this, (byte) 0);
            this.j = new SemanticDUI();
            if (this.j.initSemanticDUI(jSONObject, this.k) == 0) {
                Log.e("LocalSemanticKernel", "DUI 引擎初始化失败,请检查资源文件是否在指定路径下！");
                return -1;
            }
            Log.d("LocalSemanticKernel", "DUI 引擎初始化成功");
            return 0;
        }
        Log.e("LocalSemanticKernel", "config == null");
        return -1;
    }

    private static boolean c(String str) {
        return (TextUtils.isEmpty(str) || TextUtils.equals(str, "null") || !str.contains("semantics")) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        Log.d("LocalSemanticKernel", "finalSemResult = " + str);
        if (!c(str)) {
            Log.e("LocalSemanticKernel", "finalSemResult semantic is invalid!!!");
            a(new com.aispeech.lite.f.a(8, new AIError((int) AIError.ERR_NULL_SEMANTIC, AIError.ERR_DESCRIPTION_NULL_SEMANTIC)));
            return;
        }
        try {
            AIResult aIResult = new AIResult();
            JSONObject jSONObject = new JSONObject(str);
            aIResult.setResultObject(jSONObject.toString());
            aIResult.setResultType(0);
            aIResult.setTimestamp(System.currentTimeMillis());
            if (jSONObject.has("eof")) {
                aIResult.setLast(jSONObject.optInt("eof", 1) == 1);
            } else {
                aIResult.setLast(true);
            }
            if (this.e != null) {
                this.e.a(aIResult);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Semantic.semantic_callback {
        private b() {
        }

        /* synthetic */ b(d dVar, byte b) {
            this();
        }

        @Override // com.aispeech.kernel.Semantic.semantic_callback
        public final int run(int i, byte[] bArr, int i2) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            if (i == 0) {
                if (d.this.f.n() == SemanticType.NAVI) {
                    d.this.d(new String(bArr2));
                } else if (d.this.f.n() == SemanticType.MIX_NAVI_DUI) {
                    d.b(d.this, new String(bArr2));
                }
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements SemanticDUI.semantic_callback {
        private a() {
        }

        /* synthetic */ a(d dVar, byte b) {
            this();
        }

        @Override // com.aispeech.kernel.SemanticDUI.semantic_callback
        public final int run(int i, byte[] bArr, int i2) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            if (i == 0) {
                if (d.this.f.n() == SemanticType.DUI) {
                    d.this.d(new String(bArr2));
                } else if (d.this.f.n() == SemanticType.MIX_NAVI_DUI) {
                    d.c(d.this, new String(bArr2));
                }
            }
            return 0;
        }
    }

    public final List<String> b(String str) {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(new File(this.f.m() + "/ldm/" + str + ".txt")));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            arrayList.add(readLine);
                        } catch (IOException e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return arrayList;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
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
                    bufferedReader2.close();
                    bufferedReader2.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            } catch (IOException e4) {
                e = e4;
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void a(Vocab vocab) {
        if (TextUtils.isEmpty(this.f.m())) {
            throw new IllegalArgumentException("请设置DUI Res路径!");
        }
        if (TextUtils.isEmpty(vocab.getName())) {
            throw new IllegalArgumentException("请设置词库名称!");
        }
        if (FileUtils.createOrExistsFile(this.f.m() + "/ldm/" + vocab.getName() + ".txt")) {
            try {
                FileWriter fileWriter = new FileWriter(this.f.m() + "/ldm/" + vocab.getName() + ".txt");
                StringBuilder sb = new StringBuilder();
                if (vocab.getContents() != null) {
                    Iterator<String> it = vocab.getContents().iterator();
                    while (it.hasNext()) {
                        sb.append(it.next() + "\n");
                    }
                }
                if (vocab.getAction().equals(Vocab.ACTION_INSERT)) {
                    fileWriter.write(sb.toString());
                } else if (vocab.getAction().equals(Vocab.ACTION_CLEAR_AND_INSERT)) {
                    fileWriter.write("");
                    fileWriter.write(sb.toString());
                } else {
                    fileWriter.write("");
                }
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static /* synthetic */ void b(d dVar, String str) {
        Log.d("LocalSemanticKernel", "select from navi,semantic = " + str);
        try {
            if (dVar.f.i()) {
                dVar.l = Transformer.transNgram(new JSONObject(str), dVar.g.d()).toString();
            } else {
                dVar.l = str;
            }
            if (dVar.m == null) {
                Log.d("LocalSemanticKernel", "navi first come, but navi sem is invalid, waiting dui... ");
            } else if (!c(dVar.l)) {
                dVar.d(dVar.m);
            } else {
                dVar.d(dVar.l);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            dVar.a(new com.aispeech.lite.f.a(8, new AIError((int) AIError.ERR_NULL_SEMANTIC, AIError.ERR_DESCRIPTION_NULL_SEMANTIC)));
        }
    }

    static /* synthetic */ void c(d dVar, String str) {
        Log.d("LocalSemanticKernel", "select from dui,semantic = " + str);
        dVar.m = str;
        if (!c(dVar.m)) {
            String str2 = dVar.l;
            if (str2 == null) {
                Log.d("LocalSemanticKernel", "dui first come, but dui sem is empty, waiting navi... ");
                return;
            } else {
                dVar.d(str2);
                return;
            }
        }
        dVar.d(dVar.m);
    }
}
