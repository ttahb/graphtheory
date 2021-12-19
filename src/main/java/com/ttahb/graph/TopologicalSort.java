package com.ttahb.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Topological sorting for Directed Acyclic Graph is a linear ordering of vertices such that for every directed edge u v,
 * vertex u comes before v in the ordering.Topological Sorting for a graph is not possible if the graph is not a DAG.
 * There can be more than one topological sorting for a graph.
 * @author - Vjiay Bhatt - bhattvijay69@hotmail.com
 */
public class TopologicalSort {

    private final List<ArrayList<Integer>> adjList;
    private final boolean[] visited;
    private final  int[] topOrderList;

    public TopologicalSort(int numberOfNodes) {
        adjList = Stream.generate(ArrayList<Integer>::new).limit(numberOfNodes).toList();
        visited = new boolean[numberOfNodes];
        topOrderList = new int[numberOfNodes];
        Arrays.setAll(topOrderList,i->-1);
    }

    /**
     * For every directed edge u -> v
     */
    public void addDirectedEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    public int[] sortTopologically() {
        int numberOfNodes = adjList.size();

        int topOrderIndex = numberOfNodes - 1;

        for (int i=0; i < numberOfNodes; i++) {
            if(!visited[i]){
                topOrderIndex = depthFirstSearch(i, topOrderList, topOrderIndex);
            }
        }

        return topOrderList;
    }

    private int depthFirstSearch(int vertex, int[] topOderList, int topOrderIndex) {

        visited[vertex] = true;
        for(Integer neighbour: adjList.get(vertex)){
            if(!visited[neighbour]) {
                topOrderIndex = depthFirstSearch(neighbour, topOderList, topOrderIndex);
            }
        }

        topOderList[topOrderIndex--] = vertex;
        return topOrderIndex;
    }
}
