package com.aispeech.lite.dm;

import com.aispeech.common.AITimer;
import com.aispeech.common.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
/* loaded from: classes.dex */
public final class a {
    private Map<String, TimerTask> a;
    private int b;

    public a() {
        this.b = 10000;
        this.a = new HashMap(16);
    }

    public a(byte b) {
        this();
        this.b = 59000;
    }

    public final void a(String str, TimerTask timerTask) {
        if (this.a != null) {
            a(str);
            this.a.put(str, timerTask);
            Log.d("Timer", "start task alias:" + str);
            AITimer.getInstance().schedule(timerTask, (long) this.b);
        }
    }

    private void a(String str) {
        TimerTask timerTask = this.a.get(str);
        if (timerTask != null) {
            Log.d("Timer", "cancel task alias:" + str);
            timerTask.cancel();
            this.a.remove(str);
        }
    }

    public final void a() {
        Map<String, TimerTask> map = this.a;
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, TimerTask> entry : this.a.entrySet()) {
                a(entry.getKey());
            }
        }
    }
}
