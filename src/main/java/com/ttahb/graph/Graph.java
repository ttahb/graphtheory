package com.ttahb.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Graph with adjacency list representation.
 * @author bhattvijay69@hotmail.com
 */
public class Graph {

    private final List<ArrayList<Integer>> adjacencyList;
    private final List<Integer> traversed = new ArrayList<>();
    private final boolean[] visited;

    /**
     *
     * @param n - number of nodes.
     */
    public Graph(int n){
        // using Streams to generate a list of list [[],[],[],[],[]] based on V passed.
        adjacencyList = Stream.generate(ArrayList<Integer>::new).limit(n).toList();
        visited = new boolean[n];
    }

    /**
     * Each edge needs to be added only once.
     * @param n1 - node1
     * @param n2 - node2
     */
    public void addEdge(int n1, int n2){
        adjacencyList.get(n1).add(n2);
        adjacencyList.get(n2).add(n1);
    }

    /**
     * Recursively traverse an undirected unweighted graph
     * @param node - node to start traversal with
     */
    public void depthFirstSearchRecursive(int node){
        if(visited[node])
            return;
        visited[node] = true;
        traversed.add(node);
        List<Integer> neighbours = adjacencyList.get(node);
        neighbours.forEach(this::depthFirstSearchRecursive);
    }

    /**
     * Returns traversed list
     * @return - traversed
     */
    public List<Integer> getTraversed(){
        return traversed;
    }

}