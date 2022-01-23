package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the indexed priority queue class.
 * @author Vijay Bhatt - bhattvijay69@hotmail.com
 */
public class TestIndexedPriorityQueue {

    /** Test sample */
    private String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

    private IndexedPriorityQueue<String> init() {
        IndexedPriorityQueue<String> pq = new IndexedPriorityQueue<>(strings.length);

        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }
        return pq;
    }

    @Test
    public void testConstructor(){
        IndexedPriorityQueue<String> pq = init();
        // prints from the iterator
        for(String ki:pq){
            System.out.println(ki);
        }
    }

    @Test
    public void testPoll(){
        IndexedPriorityQueue<String> pq = init();
        assertEquals("best",pq.poll());
        assertEquals("it",pq.poll());
        assertEquals("it",pq.poll());
        assertEquals("of",pq.poll());
        assertEquals("the",pq.poll());
        assertEquals("the",pq.poll());
        assertEquals("times",pq.poll());
        assertEquals("was",pq.poll());
        assertEquals("was",pq.poll());
        assertEquals("worst",pq.poll());
        assertThrows(NoSuchElementException.class,()->pq.poll());
    }

    @Test
    public void testPeek(){
        IndexedPriorityQueue<String> pq = init();
        assertEquals("best",pq.peek());
    }

    @Test
    public void testPeekMinIndex(){
        IndexedPriorityQueue<String> pq = init();
        assertEquals(3,pq.peekMinKeyIndex());
    }

    @Test
    public void testPollMinIndex(){
        IndexedPriorityQueue<String> pq = init();
        assertEquals(3,pq.pollMinKeyIndex());
    }

    @Test
    public void testDelete(){
        IndexedPriorityQueue<String> pq = init();
        assertEquals("times",pq.delete(5));
    }

    @Test
    public void testRandomInsert(){
        IndexedPriorityQueue<String> pq = new IndexedPriorityQueue<>(13);
        for(int i = 0; i < strings.length; i++){
            pq.insert(i,strings[i]);
        }

        pq.insert(12,"zoo");
        pq.insert(11,"zebra");

        assertEquals("zoo",pq.valueOf(12));
        assertEquals("zebra",pq.valueOf(11));

    }

    @Test
    public void testUpdate(){
        IndexedPriorityQueue<String> pq = init();
        assertEquals("times",pq.update(5,"setting"));
    }

    @Test
    public void testIncreaseValue(){
        IndexedPriorityQueue<String> pq = init();
        pq.increaseValue(5,"top");
        assertEquals("top",pq.valueOf(5));
    }

    @Test
    public void testIncreaseValueException(){
        IndexedPriorityQueue<String> pq = init();
        assertThrows(IllegalArgumentException.class,() -> pq.increaseValue(5,"this"));
    }

    @Test
    public void testDecreaseValue(){
        IndexedPriorityQueue<String> pq = init();
        pq.decreaseValue(5,"this");
        assertEquals("this",pq.valueOf(5));
    }

    @Test
    public void testDecreaseValueException(){
        IndexedPriorityQueue<String> pq = init();
        assertThrows(IllegalArgumentException.class,() -> pq.decreaseValue(5,"top"));
    }
}
