package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BreadthFirstSearchShortestPathTest {


    @Test
    public void testShortestPathBFS() {
        BreadthFirstSearchShortestPath bfsSP = new BreadthFirstSearchShortestPath(13);

        bfsSP.addEdge(1, 10);
        bfsSP.addEdge(10, 9);
        bfsSP.addEdge(9, 8);
        bfsSP.addEdge(8, 1);
        bfsSP.addEdge(8, 12);
        bfsSP.addEdge(12, 2);
        bfsSP.addEdge(2, 3);
        bfsSP.addEdge(3, 4);
        bfsSP.addEdge(9, 0);
        bfsSP.addEdge(0, 7);
        bfsSP.addEdge(3, 7);
        bfsSP.addEdge(0, 11);
        bfsSP.addEdge(11, 7);
        bfsSP.addEdge(7, 6);
        bfsSP.addEdge(6, 5);

        assertEquals(Stream.of(1, 10, 9, 0, 7, 6, 5).collect(Collectors.toList()), bfsSP.findShortestPath(1, 5));

    }

    /**
     * //Insert the edge (8,1) before (1,10) - we get another possible shortest path is  1,8,9,0,7,6,5  for 1 to 5.
     */
    @Test
    public void testShortestPathBFS2() {
        BreadthFirstSearchShortestPath bfsSP = new BreadthFirstSearchShortestPath(13);

        bfsSP.addEdge(8, 1);
        bfsSP.addEdge(1, 10);
        bfsSP.addEdge(10, 9);
        bfsSP.addEdge(9, 8);
        bfsSP.addEdge(8, 12);
        bfsSP.addEdge(12, 2);
        bfsSP.addEdge(2, 3);
        bfsSP.addEdge(3, 4);
        bfsSP.addEdge(9, 0);
        bfsSP.addEdge(0, 7);
        bfsSP.addEdge(3, 7);
        bfsSP.addEdge(0, 11);
        bfsSP.addEdge(11, 7);
        bfsSP.addEdge(7, 6);
        bfsSP.addEdge(6, 5);

        assertEquals(Stream.of(1, 8, 9, 0, 7, 6, 5).collect(Collectors.toList()), bfsSP.findShortestPath(1, 5));


    }
}
