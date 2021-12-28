package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DijkstraLazyUsingPQTest {

    private DijkstraLazyUsingPQ getGraph() {
        DijkstraLazyUsingPQ graph = new DijkstraLazyUsingPQ(5);
        graph.addDirectedWeightedEdge(0, 1, 4);
        graph.addDirectedWeightedEdge(0, 2, 1);
        graph.addDirectedWeightedEdge(2, 1, 2);
        graph.addDirectedWeightedEdge(2, 3, 5);
        graph.addDirectedWeightedEdge(1, 3, 1);
        graph.addDirectedWeightedEdge(3, 4, 3);
        return graph;
    }

    // testing minimum distances


    @Test
    public void testDijkstraLazyDistance(){

        DijkstraLazyUsingPQ graph = getGraph();

        int[] expectedOutput = {0,3,1,4,7};
        // source node = 0
        assertArrayEquals(expectedOutput,graph.findSSShortestPathUsingDijkstra(0));

    }

    @Test
    public void testDijkstraLazyDistance2(){

        DijkstraLazyUsingPQ graph = getGraph();

        int[] expectedOutput = {Integer.MAX_VALUE,2,0,3,6};
        // source node = 2
        assertArrayEquals(expectedOutput,graph.findSSShortestPathUsingDijkstra(2));

    }

    @Test
    public void testDijkstraLazyDistance3(){

        DijkstraLazyUsingPQ graph = getGraph();

        // path from source node = 0 to end node = 2
        assertEquals(1,graph.findSSShortestPathUsingDijkstra(0,2)[2]);

    }

    @Test
    public void testDijkstraLazyDistance4(){

        DijkstraLazyUsingPQ graph = getGraph();

        // path from source node = 1 to end node = 2  // returns Integer.MAX_VALUE since no path exists
        assertEquals(Integer.MAX_VALUE,graph.findSSShortestPathUsingDijkstra(1,2)[2]);

    }

    @Test
    public void testDijkstraLazyDistance5(){

        DijkstraLazyUsingPQ graph = getGraph();

        // path from source node = 1 to end node = 4  //
        assertEquals(4,graph.findSSShortestPathUsingDijkstra(1,4)[4]);

    }


    // testing shortest paths

    @Test
    public void findShortestPath1(){
        DijkstraLazyUsingPQ graph = getGraph();
        List<Integer> expectedShortesPath = Arrays.asList(0,2,1,3,4);
        assertEquals(expectedShortesPath,graph.buildShortestPath(0)); // default end node = 4
    }

    @Test
    public void findShortestPath2(){
        DijkstraLazyUsingPQ graph = getGraph();
        List<Integer> expectedShortesPath = Arrays.asList(2,1,3,4);
        assertEquals(expectedShortesPath,graph.buildShortestPath(2,4)); // start = 2, end = 4
    }

    @Test
    public void findShortestPath3(){
        DijkstraLazyUsingPQ graph = getGraph();
        assertEquals(new ArrayList<>(),graph.buildShortestPath(4,0)); // empty path
    }


}
