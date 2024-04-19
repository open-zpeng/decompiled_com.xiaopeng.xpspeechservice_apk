package com.aispeech.upload.http;

import com.aispeech.upload.http.RealCall;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class Dispatcher {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int maxRequests = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private ExecutorService executorService;
    private final Deque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque();
    private final Deque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque();
    private final Deque<RealCall> runningSyncCalls = new ArrayDeque();

    public Dispatcher() {
    }

    public Dispatcher(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public synchronized ExecutorService executorService() {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return this.executorService;
    }

    synchronized void executed(RealCall realCall) {
        this.runningSyncCalls.add(realCall);
    }

    public void enqueue(RealCall.AsyncCall asyncCall) {
        if (this.runningAsyncCalls.size() < maxRequests) {
            this.runningAsyncCalls.add(asyncCall);
            executorService().execute(asyncCall);
            return;
        }
        this.readyAsyncCalls.add(asyncCall);
    }

    private void promoteCalls() {
        try {
            if (this.runningAsyncCalls.size() < maxRequests && !this.readyAsyncCalls.isEmpty()) {
                Iterator<RealCall.AsyncCall> it = this.readyAsyncCalls.iterator();
                while (it.hasNext()) {
                    RealCall.AsyncCall next = it.next();
                    it.remove();
                    this.runningAsyncCalls.add(next);
                    executorService().execute(next);
                    if (this.runningAsyncCalls.size() >= maxRequests) {
                        return;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void finished(RealCall.AsyncCall asyncCall) {
        synchronized (this) {
            try {
                this.runningAsyncCalls.remove(asyncCall);
                promoteCalls();
            } catch (Exception e) {
            }
        }
    }

    public synchronized int runningCallsCount() {
        return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
    }

    public void cancelAll() {
        try {
            for (RealCall.AsyncCall asyncCall : this.runningAsyncCalls) {
                asyncCall.get().cancel();
            }
            for (RealCall.AsyncCall asyncCall2 : this.readyAsyncCalls) {
                asyncCall2.get().cancel();
            }
        } catch (Exception e) {
        } catch (Throwable th) {
            this.readyAsyncCalls.clear();
            this.runningAsyncCalls.clear();
            throw th;
        }
        this.readyAsyncCalls.clear();
        this.runningAsyncCalls.clear();
    }
}
