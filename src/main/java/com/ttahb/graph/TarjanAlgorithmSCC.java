package com.ttahb.graph;

import java.util.*;

/**
 * Find strongly connected components using the Tarjans algorithm in a directed graph.
 * @author - Vijay Bhatt, bhattvijay69@hotmail.com
 */
public class TarjanAlgorithmSCC {

    private int n;
    private List<List<Integer>> adjList;
    private boolean[] visited;
    private int[] ids,lowLinks;
    private int id = 0;
    /** Using java stack */
    private Deque<Integer> stack;
    private Map<Integer,List<Integer>> sccMap;
    private int sccCount = 0;

    public TarjanAlgorithmSCC(int n){
        if(n==0)
            throw new IllegalArgumentException();
        this.adjList = getEmptyGraph(n);
        this.n = n;
    }

    public Map<Integer,List<Integer>>  findSCC(){
        visited = new boolean[n];
        ids = new int[n];
        lowLinks = new int[n];
        stack = new ArrayDeque<>();
        sccMap = new HashMap<>();

        for(int i=0; i < n; i++){
            if(!visited[i])
                dfs(i);
        }
        return this.sccMap;
    }

    private void dfs(int at) {
        visited[at] = true;
        stack.addFirst(at);
        ids[at] = lowLinks[at] = ++id;

        for(int to:adjList.get(at)){
            if(!visited[to]){
                dfs(to);
                lowLinks[at] = Math.min(lowLinks[to], lowLinks[at]);
            } else {
                if(stack.contains(to)){
                    lowLinks[at] = Math.min(lowLinks[to],lowLinks[at]);
                }
            }
        }

        // Found Strongly connected component.
        if(lowLinks[at] == ids[at]){
            List<Integer> scc = new ArrayList<>();
            int current;
            do {
                current = stack.pop();
                scc.add(current);
            } while(current != at);

            sccMap.put(sccCount++,scc);
        }
    }

    /** Adds a directed edge u to v */
    public void addDirectedEdge(int u, int v){
        this.adjList.get(u).add(v);
    }

    private List<List<Integer>> getEmptyGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++)
            graph.add(new ArrayList<>());
        return graph;
    }
}
