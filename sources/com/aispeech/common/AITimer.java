package com.aispeech.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes.dex */
public class AITimer extends Timer {
    private static AITimer a;
    private static Map<String, TimerTask> b = new HashMap();

    private AITimer() {
    }

    public static Timer getInstance() {
        if (a == null) {
            a = new AITimer();
        }
        return a;
    }

    public void startTimer(TimerTask timerTask, String str, int i) {
        TimerTask timerTask2 = b.get(str);
        if (timerTask2 != null) {
            timerTask2.cancel();
            b.remove(str);
        }
        b.put(str, timerTask);
        try {
            a.schedule(timerTask, i);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void cancelTimer(String str) {
        TimerTask timerTask = b.get(str);
        if (timerTask != null) {
            timerTask.cancel();
            b.remove(str);
        }
    }
}
