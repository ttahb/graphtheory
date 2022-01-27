package com.ttahb.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Dijkstra's Eager Approach - In this program we try to find the shortest path from the source using an
 * Indexed Priority Queue available at com.ttahb.graph.IndexedPriorityQueue.
 * @author Vijay Bhatt  - bhattvijay69@hotmail.com
 */
public class DijkstraEagerApproach {

    private final List<ArrayList<Edge>> graph;
    private final int n;
    private Integer[] prev;
    /** Helper class to represent a directed edge u->v with weight - w */
    private static class Edge {

        int u,v;
        int w;
        public Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public DijkstraEagerApproach(int n){
        this.n = n;
        this.graph = Stream.generate(ArrayList<Edge>::new).limit(n).toList(); // creates an empty adj list with size n
    }

    public void addDirectedWeightedEdge(int u, int v, int w){
        this.graph.get(u).add(new Edge(u,v,w));
    }

    public int[] findShortestPath(int start, int end){
        // a custom indexed priority queue for log(n) dynamic updates in priority queue, find code in same package.
        IndexedPriorityQueue<Integer> ipq = new IndexedPriorityQueue<>(n);
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        boolean[] visited = new boolean[n];
        prev = new Integer[n];
        ipq.insert(start, 0); // value determines the distance.
        dist[start] = 0;

        while(!ipq.isEmpty()){

            int keyIndex  = ipq.pollMinKeyIndex();
            visited[keyIndex] = true;
            ArrayList<Edge> neighbourNodes = this.graph.get(keyIndex);

            for(Edge edge : neighbourNodes){
                if(!visited[edge.v]){
                    int new_weight = dist[edge.u]+edge.w;
                    if(new_weight < dist[edge.v]){
                        dist[edge.v] = new_weight;

                        if(!ipq.contains(edge.v)){
                            ipq.insert(edge.v,new_weight);
                        } else {
                            ipq.decreaseValue(edge.v,new_weight);
                        }

                        prev[edge.v] = edge.u;
                    }
                }
            }

            //stop if end node is reached, no point in exploring other nodes
            if(keyIndex == end)
                return dist;
        }

        return dist;
    }

    public int findShortestDistTo(int from, int to){
        return findShortestPath(from,to)[to];
    }

    public List<Integer> constructShortestPath(int from, int to){

        if(from < 0 || from >= n  || to < 0 || to  >= n){
            throw new IllegalArgumentException("invalid start or end position");
        }

        List<Integer> path = new ArrayList<>();

        int[] dist = findShortestPath(from,to);
        if(dist[to] == Integer.MAX_VALUE){
            return path;
        }

        for(Integer i=to; i != null;i = prev[i] ){
            path.add(i);
        }

        Collections.reverse(path);
        return path;
    }

}
