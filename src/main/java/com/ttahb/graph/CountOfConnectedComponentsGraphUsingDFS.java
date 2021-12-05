package com.ttahb.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Counts the number of disconnected components in a graph with 'v' vertices. It uses a recursive DFS
 * method to travers each component.
 *
 * @author - bhattvijay69@hotmail.com
 */
public class CountOfConnectedComponentsGraphUsingDFS {

    private final List<ArrayList<Integer>> adjList;
    private final boolean[] visited;
    private static int count = 0;

    /**
     * @param v - number of total vertices.
     */
    public CountOfConnectedComponentsGraphUsingDFS(int v) {
        // using Streams to generate a list of list [[],[],[],[],[]] based on 'v' passed.
        adjList = Stream.generate(ArrayList<Integer>::new).limit(v).toList();
        visited = new boolean[v];
    }

    public void addEdge(int x, int y) {
        adjList.get(x).add(y);
        adjList.get(y).add(x);
    }

    /**
     * Dummy method to accept graph component with only 1 isolated node.
     *
     * @param x
     */
    public void addEdge(int x) {

    }

    public int countConnectedComponents() {

        for (int i = 0; i < adjList.size(); i++) {
            if (!visited[i]) {
                depthFirstSearch(i);
                count++;
            }
        }

        return count;

    }

    public void depthFirstSearch(int v) {
        if (visited[v])
            return;
        visited[v] = true;
        List<Integer> neighbours = adjList.get(v);
        neighbours.forEach(this::depthFirstSearch);
    }

}

