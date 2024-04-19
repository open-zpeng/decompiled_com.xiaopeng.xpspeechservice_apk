package com.aispeech.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
/* loaded from: classes.dex */
public class LimitQueue<E> implements Cloneable, Queue<E> {
    private int a;
    private LinkedList<E> b = new LinkedList<>();

    public LimitQueue(int i) {
        this.a = i;
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
        if (this.b.size() >= this.a) {
            this.b.poll();
        }
        return this.b.offer(e);
    }

    @Override // java.util.Queue
    public E poll() {
        return this.b.poll();
    }

    public Queue<E> getQueue() {
        return this.b;
    }

    public int getLimit() {
        return this.a;
    }

    public E getLast() {
        return this.b.getLast();
    }

    public E getFirst() {
        return this.b.getFirst();
    }

    @Override // java.util.Queue, java.util.Collection
    public boolean add(E e) {
        return this.b.add(e);
    }

    @Override // java.util.Queue
    public E element() {
        return this.b.element();
    }

    @Override // java.util.Queue
    public E peek() {
        return this.b.peek();
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.b.size() == 0;
    }

    @Override // java.util.Collection
    public int size() {
        return this.b.size();
    }

    @Override // java.util.Queue
    public E remove() {
        return this.b.remove();
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        return this.b.addAll(collection);
    }

    @Override // java.util.Collection
    public void clear() {
        this.b.clear();
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return this.b.contains(obj);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.b.containsAll(collection);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return this.b.iterator();
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        return this.b.remove(obj);
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return this.b.removeAll(collection);
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return this.b.retainAll(collection);
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return this.b.toArray();
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.b.toArray(tArr);
    }

    /* renamed from: clone */
    public LimitQueue m0clone() {
        try {
            return (LimitQueue) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
