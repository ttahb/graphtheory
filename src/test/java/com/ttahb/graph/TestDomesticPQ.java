package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests heapify, insert, poll, peek, delete in a priority queue.
 * @author - Vijay Bhatt - bhattvijay69@hotmail.com
 */
public class TestDomesticPQ {

    private static int[] array = {3,7,9,5,2,8,1};
    private static Integer[] testArray;

    @BeforeAll
    public static void initialize(){
        testArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
    }
    /**
     *               3                 1
     *             /  \               /  \
     *            7    9    = >      2    3
     *           / \   / \          / \   /  \
     *          5   2  8  10        5   7  8   9
     */
    @Test
    public void testHeapify(){
        DomesticPQ domesticPQ = new DomesticPQ(testArray);
        assertEquals(Arrays.asList(1,2,3,5,7,8,9),domesticPQ.getHeap());
    }

    @Test
    public void testInsert(){
        DomesticPQ domesticPQ = new DomesticPQ(testArray);
        domesticPQ.insert(0);
        assertEquals(0,domesticPQ.peek());

        domesticPQ.insert(10);
        assertEquals(Arrays.asList(0,1,3,2,7,8,9,5,10),domesticPQ.getHeap());
    }

    @Test
    public void testPoll(){
        DomesticPQ domesticPQ = new DomesticPQ(testArray);
        assertEquals(1,domesticPQ.poll());
        assertEquals(2,domesticPQ.poll());
        assertEquals(3,domesticPQ.poll());
        assertEquals(5,domesticPQ.poll());
        assertEquals(7,domesticPQ.poll());
        assertEquals(8,domesticPQ.poll());
        assertEquals(9,domesticPQ.poll());
        assertEquals(null,domesticPQ.poll());
    }

    @Test
    public void testDelete(){
        DomesticPQ domesticPQ = new DomesticPQ(testArray);
        assertTrue(domesticPQ.delete(3));
        assertEquals(Arrays.asList(1,2,8,5,7,9),domesticPQ.getHeap());
    }


}