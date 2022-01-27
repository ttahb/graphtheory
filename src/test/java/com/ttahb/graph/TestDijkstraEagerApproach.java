package com.ttahb.graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDijkstraEagerApproach {

    private DijkstraEagerApproach getGraph() {
        DijkstraEagerApproach graph = new DijkstraEagerApproach(6);
        graph.addDirectedWeightedEdge(0, 1, 1);
        graph.addDirectedWeightedEdge(0, 3, 5);
        graph.addDirectedWeightedEdge(0, 2, 4);
        graph.addDirectedWeightedEdge(1, 2, 2);
        graph.addDirectedWeightedEdge(1, 3, 1);
        graph.addDirectedWeightedEdge(2, 3, 4);
        graph.addDirectedWeightedEdge(2, 5, 2);
        graph.addDirectedWeightedEdge(2, 4, 1);
        graph.addDirectedWeightedEdge(3, 2, 1);
        graph.addDirectedWeightedEdge(3, 5, 6);
        graph.addDirectedWeightedEdge(3, 4, 3);
        graph.addDirectedWeightedEdge(4, 5, 4);
        return graph;
    }

    @Test
    public void testDijkstraShortestDistance(){

        DijkstraEagerApproach graph = getGraph();

        int[] expectedOutput = {0,1,3,2,4,5};
        // source node = 0
        assertArrayEquals(expectedOutput,graph.findShortestPath(0,5));

        int[] expectedOutput2 = {Integer.MAX_VALUE,0,2,1,3,4};
        // source node = 0
        assertArrayEquals(expectedOutput2,graph.findShortestPath(1,5));
    }

    @Test
    public void testDijkstraShortestDistTo(){
        DijkstraEagerApproach graph = getGraph();

        int dist = graph.findShortestDistTo(2,5);
        assertEquals(2,dist);

        int dist1 = graph.findShortestDistTo(1,5);
        assertEquals(4,dist1);
    }

    @Test
    public void testDijkstraConstructShortestPath(){
        DijkstraEagerApproach graph = getGraph();
        List<Integer> expectedShortestPath = Arrays.asList(1,2,5);
        assertEquals(expectedShortestPath,graph.constructShortestPath(1,5)); // from = 1, to = 5

        List<Integer> expectedShortestPath2 = Arrays.asList(1,2,4);
        assertEquals(expectedShortestPath2,graph.constructShortestPath(1,4)); // from = 1, to = 4

        List<Integer> expectedShortestPath3 = Arrays.asList(2,5);
        assertEquals(expectedShortestPath3,graph.constructShortestPath(2,5)); // from = 2, to = 5

        assertEquals(new ArrayList<>(),graph.constructShortestPath(1,0)); // from = 1, to = 0
    }


}
