package com.ttahb.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple edge list based implementation of Bellman-Ford Algorithm. Finds the shortest path in O(E.V) time. Helps to detect negative cycles in the graph.
 *
 * @author Vijay Bhatt - bhattvija69@hotmail.com
 */
public class BellmanFordAlgorithm {

    /** Graph containing the edges. */
    private final List<Edge> graph;

    /** Count of vertices. */
    private final int V;

    /**
     * Initializes empty graph with n nodes.
     *
     * @param n - number of vertices
     */
    public BellmanFordAlgorithm(int V) {
        graph = new ArrayList<>();
        this.V = V;
    }

    /**
     * Adds a directed weighted edge to the graph. e.g.  u -> v with cost w
     * @param u - from
     * @param v - to
     * @param w - cost of the edge representing u -> v
     */
    public void addDirectedEdge(int u, int v, int w) {
        this.graph.add(new Edge(u, v, w));
    }

    /**
     * Helper class represents an edge from u -> v with cost - w
     */
    private static class Edge {
        int u, v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    /**
     * Finds the Single source shortest path using Bellman Ford Algorithm with an edge list graph rep.
     * @param start - starting/ source vertex
     * @return - an array containing cost to move to each vertex from the source vertex.
     */
    public double[] findShortestPath(int start) {
        int vertices = this.V;
        double[] cost = new double[vertices];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        cost[start] = 0;

        // a flag to stop early when there are no more updates to cost.
        boolean relaxed = true;

        for (int i = 0; i < vertices - 1  && relaxed; i++) {
            for (Edge edge : graph) {
                if (cost[edge.u] + edge.w < cost[edge.v]) {
                    cost[edge.v] = cost[edge.u] + edge.w;
                    relaxed = true;
                }
            }
        }

        // Check for negative cycles in the graph. With the relaxed flag in place,
        // works only if there was an update in the (vertices-1)th iteration above
        for (int i = 0; i < vertices - 1 && relaxed; i++) {
            relaxed = false;
            for (Edge edge : graph) {
                if (cost[edge.u] + edge.w < cost[edge.v]) {
                    cost[edge.v] = Double.NEGATIVE_INFINITY;
                    relaxed = true;
                }
            }
        }

        return cost;
    }

}

