package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DijkstraLazyUsingPQTest {

    @Test
    public void testDijkstraLazy(){

        DijkstraLazyUsingPQ graph = new DijkstraLazyUsingPQ(5);
        graph.addDirectedWeightedEdge(0,1,4);
        graph.addDirectedWeightedEdge(0,2,1);
        graph.addDirectedWeightedEdge(2,1,2);
        graph.addDirectedWeightedEdge(2,3,5);
        graph.addDirectedWeightedEdge(1,3,1);
        graph.addDirectedWeightedEdge(3,4,3);

        int[] expectedOutput = {0,3,1,4,7};
        // source node = 0
        assertArrayEquals(expectedOutput,graph.findSSShortestPathUsingDijkstra(0));

    }

    @Test
    public void testDijkstraLazy2(){

        DijkstraLazyUsingPQ graph = new DijkstraLazyUsingPQ(5);
        graph.addDirectedWeightedEdge(0,1,4);
        graph.addDirectedWeightedEdge(0,2,1);
        graph.addDirectedWeightedEdge(2,1,2);
        graph.addDirectedWeightedEdge(2,3,5);
        graph.addDirectedWeightedEdge(1,3,1);
        graph.addDirectedWeightedEdge(3,4,3);

        int[] expectedOutput = {Integer.MAX_VALUE,2,0,3,6};
        // source node = 2
        assertArrayEquals(expectedOutput,graph.findSSShortestPathUsingDijkstra(2));

    }
}
