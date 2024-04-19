package com.aispeech.lite.tts;

import com.aispeech.common.Log;
import com.aispeech.common.WavFileWriter;
import java.util.concurrent.LinkedBlockingQueue;
/* loaded from: classes.dex */
public final class h {
    private LinkedBlockingQueue<g> a = new LinkedBlockingQueue<>();
    private int b;

    public final void a(g gVar) {
        if (gVar instanceof i) {
            byte[] removeWaveHeader = WavFileWriter.removeWaveHeader((byte[]) gVar.b());
            gVar.a(removeWaveHeader);
            if (removeWaveHeader != null) {
                this.b += removeWaveHeader.length;
            }
        }
        this.a.add(gVar);
    }

    public final g a() {
        try {
            return this.a.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void b() {
        Log.d("SynthesizedBlockQueue", "clear all blocks");
        this.a.clear();
        this.b = 0;
    }

    public final int c() {
        return this.b;
    }
}
