package com.ttahb.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

/**
 * A simple min heap based implementation of priority queue which takes non-negative integers and assuming 0 to be the
 * highest priority.
 * @author Vijay Bhatt - bhattvijay69@hotmail.com
 */
public class DomesticPQ {

    /** Taking a dynamic arraylist with default size for simplicity. */
    private final List<Integer> heap;

    public DomesticPQ(){
        heap = new ArrayList<>();
    }

    public List<Integer> getHeap(){
        return this.heap;
    }

    /** Heapify - creates an heap out of an array of Integers in O(n) time. */
    public DomesticPQ(Integer[] array){
        heap = new ArrayList<>();
        for(Integer i:array){
            heap.add(i);
        }

        //we start from last elem and start sinking each one, since half the nodes in a binary heap are leaf nodes.
        // we are able to achieve
        for(int i=heap.size()-1; i >=0; i--){
            sink(i);
        }
    }

    /** Inserts into a heap. Assuming the heap to be a min-heap.
     * E.g. insert '0' in below min-heap
     *
     *        1                 0
     *      /  \               /  \
     *     2    3    = >      2    1
     *    / \   /           / \   /  \
     *   5   7  9          5   7  9   3
     *
     */
    public void insert(Integer elem){
        if(elem == null)
            throw new IllegalArgumentException();
        heap.add(elem);
        swim(heap.size() - 1);
    }

    /** Returns element with the highest priority without deleting it. */
    public Integer peek(){
        if(heap.isEmpty())
            return  null;
        return heap.get(0);
    }

    /** Removes the element with the highest priority in the heap, basically returns the element at root and deletes it. */
    public Integer poll(){
        /*int data = heap.get(0);
        swap(0,heap.size() - 1);
        heap.remove(heap.size()-1);
        sink(0);*/
        return deleteAtIndex(0);
    }

    /** Deletes the given element by first making a linear search. */
    public boolean delete(Integer elem){
        if(elem == null) return false;
        int k = heap.indexOf(elem);
        if(k == -1){
            return false;
        } else {
          deleteAtIndex(k);
          return true;
        }
    }

    private Integer deleteAtIndex(int k) {
        if(heap.isEmpty())
            return null;
        int heapLastIndex = heap.size() - 1;
        Integer removed_data = heap.get(k);
        swap(k,heapLastIndex);
        heap.remove(heapLastIndex);
        // check if k is the last index, heap invariant isn't disturbed, no need to sink or swim
        if(k == heapLastIndex)
            return removed_data;
        heapLastIndex--;

        sink(k);
        // if sinking did not work try swimming
        if(heap.get(k).equals(removed_data)){
            swim(k);
        }
        return removed_data;
    }

    /** Helper method to sink given element at index k. */
    private void sink(Integer k) {
        while(true){
            int left_index = 2*k+1;
            // checking if element at index k is a  leaf node, we can not sink any further.
            if(left_index >= heap.size())
                break;

            int right_index = 2*k+2;

            // default to left index since heap is a complete tree, we assume left child exist before right child
            int small_index = left_index;
            if(right_index < heap.size() && less(right_index, small_index)){
                small_index = right_index;
            }

            if(less(k,small_index)) // stop if there are no children or we cannot sink
                break;

            swap(small_index,k);
            k = small_index;

        }
    }

    /** Helper method to compare if value at index i is less than that of value at index j */
    private boolean less(int i, int j){
        return heap.get(i).compareTo(heap.get(j)) < 0;
    }

    /** Method to swim or bubble up the value at given index to satisfy heap invariant. */
    private void swim(int j) {
        int parentIndex = parent(j);

        while( parentIndex >= 0 && heap.get(parentIndex) > heap.get(j)){
            swap(parentIndex, j);
            j = parentIndex;
            parentIndex = parent(parentIndex);
        }
    }


    private void swap(int i, int j) {
        int value_i = heap.get(i);
        int value_j = heap.get(j);

        heap.set(i,value_j);
        heap.set(j,value_i);
    }

    private int parent(int j) {
        return (j-1)/2;
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
