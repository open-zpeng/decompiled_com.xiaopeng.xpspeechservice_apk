package com.aispeech.lite.oneshot;

import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes.dex */
public class OneshotCache<T> extends LinkedList<T> implements Cloneable {
    private int a;

    public OneshotCache(int i) {
        this.a = i;
    }

    @Override // java.util.LinkedList, java.util.Deque, java.util.Queue
    public boolean offer(T t) {
        if (super.size() > this.a) {
            remove();
        }
        return super.offer(t);
    }

    @Override // java.util.LinkedList, java.util.Deque, java.util.Queue
    public T poll() {
        return (T) super.poll();
    }

    public boolean isValid() {
        return size() != 0;
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List, java.util.Deque
    public OneshotCache<T>.OneshotIterator iterator() {
        return new OneshotIterator();
    }

    /* loaded from: classes.dex */
    public class OneshotIterator implements Iterator<T> {
        public OneshotIterator() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return OneshotCache.this.size() != 0;
        }

        @Override // java.util.Iterator
        public T next() {
            return (T) OneshotCache.this.poll();
        }
    }

    @Override // java.util.LinkedList
    public OneshotCache clone() {
        return (OneshotCache) super.clone();
    }
}
