package com.aispeech.common;

import java.util.LinkedList;
import java.util.Queue;
/* loaded from: classes.dex */
public class NotifyLimitQueue<E> {
    private Queue<E> a = new LinkedList();
    private Listener b;
    private int c;

    /* loaded from: classes.dex */
    public interface Listener<E> {
        void onPop(E e);
    }

    public NotifyLimitQueue(int i, Listener<E> listener) {
        this.c = i;
        this.b = listener;
    }

    public boolean offer(E e) {
        Listener listener;
        if (this.a.size() == this.c && (listener = this.b) != null) {
            listener.onPop(this.a.poll());
        }
        return this.a.offer(e);
    }

    public int size() {
        Queue<E> queue = this.a;
        if (queue == null) {
            return 0;
        }
        return queue.size();
    }

    public void clear() {
        Queue<E> queue = this.a;
        if (queue != null) {
            queue.clear();
        }
    }
}
