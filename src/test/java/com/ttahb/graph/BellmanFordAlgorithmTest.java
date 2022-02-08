package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BellmanFordAlgorithmTest {

    @Test
    public void testSSShortestPath() {
        BellmanFordAlgorithm graph = new BellmanFordAlgorithm(6);
        graph.addDirectedEdge(0, 1, 6);
        graph.addDirectedEdge(0, 2, 4);
        graph.addDirectedEdge(0, 3, 5);
        graph.addDirectedEdge(1, 4, -1);
        graph.addDirectedEdge(2, 1, -2);
        graph.addDirectedEdge(2, 4, 3);
        graph.addDirectedEdge(3, 2, -2);
        graph.addDirectedEdge(3, 5, -1);
        graph.addDirectedEdge(4, 5, 3);

        double[] expectedCost = {0.0, 1.0, 3.0, 5.0, 0.0, 3.0};
        double[] cost = graph.findShortestPath(0);
        assertArrayEquals(expectedCost, cost);

    }

    @Test
    public void testNegativeCycle() {
        BellmanFordAlgorithm graph = new BellmanFordAlgorithm(4);
        graph.addDirectedEdge(0, 1, 4);
        graph.addDirectedEdge(0, 2, 5);
        graph.addDirectedEdge(2, 1, 7);
        graph.addDirectedEdge(1, 3, 7);
        graph.addDirectedEdge(3, 2, -15);

        double[] expectedCost = {0.0, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY};
        double[] cost = graph.findShortestPath(0);
        assertArrayEquals(expectedCost, cost);
    }
}
