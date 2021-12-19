package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author - Vijay Bhatt - bhattvijay69@hotmail.com
 */
public class TopologicalSortTest {

    @Test
    public void testTopologicalSort(){

        TopologicalSort topSort = new TopologicalSort(9);
        topSort.addDirectedEdge(8,1);
        topSort.addDirectedEdge(8,3);
        topSort.addDirectedEdge(1,2);
        topSort.addDirectedEdge(3,2);
        topSort.addDirectedEdge(3,4);
        topSort.addDirectedEdge(0,4);
        topSort.addDirectedEdge(2,5);
        topSort.addDirectedEdge(4,5);
        topSort.addDirectedEdge(5,6);
        topSort.addDirectedEdge(5,7);

        int[] topOder = topSort.sortTopologically();
        int[] expectedOrder = {8,3,1,2,0,4,5,7,6};
        assertEquals(Arrays.toString(expectedOrder),Arrays.toString(topOder));
    }
}
