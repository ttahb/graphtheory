package com.ttahb.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds the bridges in an undirected graph with O(V+E) time complexity in the worst case.
 * General Idea - Start at any node and do a DFS traversal labelling nodes with an increasing id value.
 * Keep track of the id of each node and the smallest low-link value. During the DFS, bridges will be
 * found where the ids of the node your edge is coming from is less than the low link value of the
 * node your edge is going to.  ***********        ids[from edge] < lowkey[to edge]   ********************.
 * LowLink - the lowlink of a node is lowest id reachable from the node.
 * @author - Vijay Bhatt, bhattvijay69@hotmail.com
 */
public class FindBridgesAlgorithm {

    /** adjList representing the undirected graph */
    private List<List<Integer>> adjList;

    /** graph size */
    private int n;

    private int id = 0;

    /** ids - an array to represent the respective ids for each vertices
     * lowlink - the lowlink of a node is lowest id reachable from the node.
     */
    private int[] ids, lowLinks;

    private boolean[] visited;

    public FindBridgesAlgorithm(int n){
        if(n == 0)
            throw new IllegalArgumentException();
        this.adjList = getEmptyGraph(n);
        this.n = n;
    }

    private List<List<Integer>> getEmptyGraph(int n) {
        List<List<Integer>> emptyGraph = new ArrayList<>();
        for(int i=0; i<n;i++)
            emptyGraph.add(new ArrayList<>());
        return emptyGraph;
    }

    public void addEdge(int u, int v){
        this.adjList.get(u).add(v);
        this.adjList.get(v).add(u);
    }

    public List<Integer> findBridges(){

        ids = new int[n];
        lowLinks = new int[n];
        visited = new boolean[n];

        List<Integer> bridges = new ArrayList<>();

        for(int i=0; i<n; i++){
            if(!visited[i])
                depthFirstSearch(i, -1, bridges);
        }

        return bridges;
    }

    private void depthFirstSearch(int at, int parent, List<Integer> bridges) {
        visited[at] = true;
        ids[at] = lowLinks[at] = ++id;

        for(int to: adjList.get(at)){
            if(to == parent) continue;
            if(!visited[to]){
                depthFirstSearch(to, at, bridges);
                lowLinks[at] = Math.min(lowLinks[at],lowLinks[to]);
                if(ids[at] < lowLinks[to]){
                    bridges.add(at);
                    bridges.add(to);
                }
            } else {
                lowLinks[at] = Math.min(lowLinks[at], ids[to]);
            }
        }
    }


}
