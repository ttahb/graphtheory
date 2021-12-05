package com.ttahb.graph;


import java.util.*;
import java.util.stream.Stream;

/**
 * In this algorithm, we use BFS to calculate shortest path from s - starting vertex to e - end vertex.
 *
 * @author - bhattvijay69@hotmail.com
 */
public class BreadthFirstSearchShortestPath {

    private final List<ArrayList<Integer>> adjList;
    /**
     * prev - keeps track of every neighbouring nodes parent or say previous node
     */
    private final int[] prev;
    private final boolean[] visited;

    public BreadthFirstSearchShortestPath(int v) {
        adjList = Stream.generate(ArrayList<Integer>::new).limit(v).toList();
        prev = new int[v];
        Arrays.fill(prev, Integer.MIN_VALUE);
        visited = new boolean[v];
    }

    /**
     * Adds edges for an undirected, unweighted graph, however this algorithm can be used for a directed graph.
     * For directed, graph comment the second line below to add edge only in one direction from v to w.
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adjList.get(v).add(w);
        adjList.get(w).add(v); // comment this for directed graph.
    }

    /**
     * Finds the shortest path in an unweighted graph.
     *
     * @param start - node to start path at  < n - number of vertices
     * @param end   - node to reach - >= 0
     * @return - It returns one of the shortest path, there could be other combinations. but number of edges will be same.
     */
    public List<Integer> findShortestPath(int start, int end) {

        if (start < adjList.size() && end >= 0) {
            int[] prev = breadthFirstSearch(start);

            return reconstructPath(prev, start, end);

        }
        return new ArrayList<>();
    }

    public List<Integer> reconstructPath(int[] prev, int start, int end) {

        List<Integer> path = new ArrayList<>();

        // iterate the loop until we reach the start node -> start node will not have a parent and so it keeps initially assigned value intact.
        for (int i = end; i != Integer.MIN_VALUE; i = prev[i]) {
            path.add(i);
        }

        Collections.reverse(path);

        // if start node is same in the path returns path, else return no path
        if (path.get(0) == start) {
            return path;
        } else {
            return new ArrayList<>();
        }
    }

    public int[] breadthFirstSearch(int start) {
        //using java.util.ArrayDeque to represent our FIFO queue

        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.remove();
            ArrayList<Integer> neighbours = adjList.get(node);
            neighbours.forEach(neighbour -> {
                if (!visited[neighbour]) {
                    queue.add(neighbour);
                    visited[neighbour] = true;
                    prev[neighbour] = node;
                }
            });
        }

        return prev;
    }

}

