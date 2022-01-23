package com.ttahb.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An integer key-indexed based priority queue with comparable values such as Integer, String etc. Values in this
 * priority queue determines the priority and can be updated dynamically. We can also insert, delete, poll, peek and
 * increase or decrease key. Poll takes O(1) time whereas insert, delete or update takes O(log(n)) time.
 * Key-index (ki) is within [0,heapSize)  heapSize - size of the heap.
 * @author - Vijay Bhatt - bhattvijay69@hotmail.com
 */
public class IndexedPriorityQueue<T extends Comparable<T>> implements Iterable<T> {

    /** Current number of elements in the heap. */
    private int heapSize;

    /** Maximum capacity of the heap. */
    private int maxSize;

    /** comparable values for each key - i/p - key index and o/p - value */
    private final T[] values;

    /** Position Map - i/p - key index and o/p - position of the node in the heap */
    private final int[] pm;

    /** Inverse Map - i/p - position of the node in heap  o/p - key index */
    private final int[] im;

    /**
     * Initializes an empty IPQ with maxSize as its maximum capacity of the heap.
     * @param maxSize - maximum size of the heap.
     */
    public IndexedPriorityQueue(int maxSize){
        if(maxSize < 0) throw new IllegalArgumentException();
        this.maxSize = maxSize;
        values = (T[]) new Comparable[maxSize];
        pm = new int[maxSize];
        im = new int[maxSize];

        for(int i=0; i<maxSize; i++){
            pm[i] = -1;
            im[i] = -1;
        }
    }

    /**
     * Inserts a new node. A new node is introduced at the end of the existing heap, and proceeds for swim operation if
     * until heap invariant is satisfied again.
     * @param ki - new key index, should be within [0, maxSize)
     * @param value - comparable value, which will represent priority of the new node in the heap.
     */
    public void insert(int ki, T value){
        if(contains(ki)) throw new IllegalArgumentException("key-index exists already: "+ki);
        values[ki] = value;
        pm[ki] = heapSize;
        im[heapSize] = ki;
        swim(heapSize);
        heapSize++;
    }

    /**
     * Function to delete from indexed priority queue, searches the value in O(1) time and reshuffles within O(logn) time.
     * @param ki - key index of the value to be removed.
     * @return - deleted value
     */
    public T delete(int ki){
        keyExistsOrThrow(ki);

        T deleted_value = values[ki];
        int position_index = pm[ki];
        heapSize--;
        swap(position_index,heapSize);
        values[ki] = null;
        pm[ki] = -1;
        im[heapSize] = -1;

        swim(position_index);
        sink(position_index);

        return deleted_value;
    }

    /**
     * @return - Returns key-index of the root node.
     */
    public int peekMinKeyIndex(){
        isNotEmptyOrThrow();
        return im[0];
    }

    /** Returns key-index of the polled node */
    public int pollMinKeyIndex(){
        int minKeyIndex = peekMinKeyIndex();
        delete(minKeyIndex);
        return minKeyIndex;
    }

    /** displays the value at node with the highest priority */
    public T peek(){
        isNotEmptyOrThrow();
        return values[im[0]];
    }

    /** Returns value of the polled node */
    public T poll(){
        isNotEmptyOrThrow();
        return delete(peekMinKeyIndex());
    }

    /** Updates the value at given key-index with the value parameter */
    public T update(int ki, T value){
        keyExistsOrThrow(ki);
        T oldValue = values[ki];
        values[ki] = value;
        swim(ki);
        sink(ki);
        return oldValue;
    }

    /** Increase the value associated with key-index ki to the specified value. */
    public void increaseValue(int ki, T value){
        keyExistsOrThrow(ki);
        valueNotNullOrThrow(value);
        if(values[ki].compareTo(value) == 0)
            throw new IllegalArgumentException("calling increaseValue() with value equal to specified key index.");
        if(values[ki].compareTo(value) > 0)
            throw new IllegalArgumentException("calling increaseValue() with value strictly less than value at specified key-index");
        values[ki] = value;
        sink(ki);
    }

    /** Decrease the value associated with key-index ki to the specified value. */
    public void decreaseValue(int ki, T value){
        keyExistsOrThrow(ki);
        valueNotNullOrThrow(value);
        if(values[ki].compareTo(value) == 0)
            throw new IllegalArgumentException("calling increaseValue() with value equal to specified key index.");
        if(values[ki].compareTo(value) < 0)
            throw new IllegalArgumentException("calling increaseValue() with value strictly greater than value at specified key-index");
        values[ki] = value;
        swim(ki);
    }


    private void valueNotNullOrThrow(T value) {
        if(value == null)
            throw new IllegalArgumentException("value cannot be null");
    }

    private void isNotEmptyOrThrow() {
        if (heapSize == 0) throw new NoSuchElementException("priority queue empty.");
    }

    private void keyExistsOrThrow(int ki) {
        if(!contains(ki)) throw new NoSuchElementException("key-index is not in the priority queue: "+ki);
    }

    /**
     * Tries to sink the given element at position index i to satisfy heap invariant.
     *  - position index i
     */
    private void sink(int i) {

        while(true){
            int left = 2*i+1;

            if(left >= heapSize)
                break;

            int right = 2*i+2;
            int small = left;
            if(right < heapSize && less(right,left))
                small = right;

            if(less(i,small)){
                break;
            }

            swap(i, small);
            i = small;

        }
    }

    /**
     * Tries to swim up the node at given position index to satisfy heap invariant.
     * @param i - position index i in the heap.
     */
    private void swim(int i) {
        int parent = (i-1)/2;

        // iterate until node at position index i swims to the top and satisfies heap invariant.
        while(i > 0 && less(i, parent)){
            swap(i,parent);
            i = parent;
            parent = (i-1)/2;
        }
    }

    /** i, j - position index in the heap
     * im[i] = gives key index of the ith position
     * pm[key-index] = gives position-index in the heap
     * */
    private void swap(int i, int j) {
        pm[im[i]] = j;
        pm[im[j]] = i;
        int temp = im[i];
        im[i] = im[j];
        im[j] = temp;
    }

    /**
     * Compares two nodes at given location in the heap.
     * @param i - position index i in the heap
     * @param j - position index j in the heap
     * @return - true if value at position index i is less than at j
     */
    private boolean less(int i, int j) {
        return values[im[i]].compareTo(values[im[j]]) < 0;
    }

    /**
     * Checks if given index already exists in the heap.
     * @param ki - key index passed by the user.
     * @return - true or false
     */
    private boolean contains(int ki) {
        if(ki < 0)
            throw new IllegalArgumentException("index is negative: "+ki);
        if(ki >= maxSize)
            throw new IllegalArgumentException("index is > than capacity: "+ki);

        return pm[ki] != -1;
    }

    public boolean isEmpty(){
        return heapSize == 0;
    }

    public T valueOf(int ki){
        keyExistsOrThrow(ki);
        return values[ki];
    }

    /**
     * Returns an iterator that iterates over the values in priority queue in ascending order.
     * The iterator doesn't support remove operation.
     * @return an iterator that iterates over the values in ascending order.
     */
    @Override
    public Iterator<T> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<T> {

        private IndexedPriorityQueue<T> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator(){
            copy = new IndexedPriorityQueue<>(im.length);
            for(int i=0; i< heapSize; i++){
                copy.insert(im[i], values[im[i]]);
            }
        }

        @Override
        public boolean hasNext() { return !copy.isEmpty();}
        @Override
        public void remove() { throw new UnsupportedOperationException();}

        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();
            return copy.poll();
        }

    }
}
