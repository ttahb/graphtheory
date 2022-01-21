package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the indexed priority queue.
 * @author Vijay Bhatt - bhattvijay69@hotmail.com
 */
public class TestIndexedPriorityQueue {

    private String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

    private IndexedPriorityQueue<String> init() {
        IndexedPriorityQueue<String> pq = new IndexedPriorityQueue<>(strings.length);

        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }
        return pq;
    }

    @Test @Disabled
    public void testConstructor(){

        IndexedPriorityQueue<String> pq = init();

        /*assertEquals("best", pq.poll());
        assertEquals("it", pq.poll());*/

        for(String ki:pq){
            System.out.println(ki);
        }
    }

    @Test
    public void testDelete(){
        IndexedPriorityQueue<String> pq = init();
        System.out.println(pq.delete(5));
        System.out.println();

        for(String ki:pq){
            System.out.println(ki);
        }
    }


}
