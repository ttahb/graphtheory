package com.ttahb.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm to find out the articulation points in an undirected graph using DFS.
 * General Idea -
 * On the callback during DFS, if id(from-node) == low-link(to-node), then there is a cycle.
 * The indication of a cycle back to the original node implies an articulation point. Only time this
 * condition fails is when the starting node has 0 or 1 outgoing nodes. This is because the node is either
 * singleton( 0 case) or trapped in a cycle (1 case).
 * LowLink - the lowlink of a node is lowest id reachable from the node. Refer Bridges algorithm.
 * @author - Vijay Bhatt, bhattvijay69@hotmail.com
 */
public class FindArticulationPoints {

    /**
     * n - size of the graph
     * id - id of each node
     * startNodeOutgoingCount - count of outgoing edges from the starting node
     */
    private int n, id = 0, startNodeOutwardEdgeCount;

    /**
     * ids - array to hold id values for each node.
     * lowLinks - array to hold lowLink values for each node.
     */
    private int[] ids,lowLinks;

    private boolean[] visited, isArtPoint;

    /**
     * Adj List representing the undirected graph.
     */
    private List<List<Integer>> adjList;

    /**
     * Initialize an empty graph with the size provided.
     * @param n - number of vertices.
     */
    public FindArticulationPoints(int n){
        if(n == 0)
            throw new IllegalArgumentException();
        this.adjList = getEmptyGraph(n);
        this.n = n;
    }

    public void addEdge(int u, int v){
        this.adjList.get(u).add(v);
        this.adjList.get(v).add(u);
    }

    public boolean[] findArticulationPoints(){
        this.ids = new int[n];
        this.lowLinks = new int[n];
        this.visited = new boolean[n];
        this.isArtPoint = new boolean[n];


        for (int i=0; i < n; i++){
            if(!visited[i]){
                startNodeOutwardEdgeCount = 0; //reset for every connected component of the graph
                dfs(i, i, -1);
                isArtPoint[i] = (startNodeOutwardEdgeCount > 1);
            }
        }

        return isArtPoint;
    }

    private void dfs(int root, int at, int parent) {
        if(root == parent) startNodeOutwardEdgeCount++;
        visited[at] = true;
        ids[at] = lowLinks[at] = ++id;

        for(int to:adjList.get(at)){
            if(to == parent) continue;
            if(!visited[to]){
                dfs(root,to,at);
                lowLinks[at] = Math.min(lowLinks[at],lowLinks[to]);

                // art point from bridge  -> ids[at] < lowLinks[to]
                // art point of cycle formation -> ids[at] == lowLinks[to]
                // combining both conditions.
                if(ids[at] <= lowLinks[to]){
                    isArtPoint[at] = true;
                }
            } else {
                lowLinks[at] = Math.min(lowLinks[at],ids[to]);
            }
        }

    }

    private List<List<Integer>> getEmptyGraph(int n) {
        List<List<Integer>> emptyGraph = new ArrayList<>();
        for(int i=0; i < n; i++){
            emptyGraph.add(new ArrayList<>());
        }
        return emptyGraph;
    }

}
