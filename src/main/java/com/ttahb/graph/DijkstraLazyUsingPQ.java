package com.ttahb.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * A lazy implementation of Dijkstra's shortest path. It is a single source shortest path algo for non-negative edge
 * weights. Time Complexity - O(E*logV).
 * For simplicity, assuming numbers as vertices starting with 0. And graph is implemented using adjacency list
 * @author - Vijay Bhatt, bhattvijay69@hotmail.com
 */
public class DijkstraLazyUsingPQ {

    private final List<ArrayList<Edge>> graph;
    private final int[] dist;

    /** n - number of vertices */
    public DijkstraLazyUsingPQ(int n){
        this.graph = Stream.generate(ArrayList<Edge>::new).limit(n).toList();
        this.dist = new int[n];
        Arrays.fill(this.dist,Integer.MAX_VALUE);
    }

    /** Helper class to build up graph adj list */
    public static class Edge {

        private final int u,v;
        private final int w;

        /** For edge u -> v with weight w */
        public Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    /** A helper class to capture vertex and its distance from the source node */
    public static class VertexAndDistance {
        private int vertex;
        private int vDist;

        public VertexAndDistance(int vertex, int vDist){
            this.vertex = vertex;
            this.vDist = vDist;
        }
    }

    public void addDirectedWeightedEdge(int u, int v, int w){
        this.graph.get(u).add(new Edge(u,v,w));
    }

    public int[] findSSShortestPathUsingDijkstra(int start){

        // Assign the source or start node distance = 0.
        dist[start] = 0;

        // A priority queue that will hold the Vertex, distance a pair of the least distance as its root node.
        // i.e. PQ spits out the pair with the minimum distance when dequeued.
        PriorityQueue<VertexAndDistance> pq = new PriorityQueue<>((a,b)->a.vDist - b.vDist);

        // initially adding source node, dist pair as the first pair in pq. i.e. (start,0)
        pq.add(new VertexAndDistance(start,dist[start]));

        // loop through priority queue until its empty

        while(!pq.isEmpty()){

            VertexAndDistance pair = pq.poll();

            // ignore the vertex pair for which dist already holds the best distance
            // -> basically for this vertex there may be another entry in the priority queue,
            // so we choose only the entry with the shortest distance.
            if(pair.vDist > dist[pair.vertex]){
                continue;
            }

            int currentVertex = pair.vertex;

            List<Edge> childVerticesList = this.graph.get(currentVertex);

            for(Edge child: childVerticesList){
                int newDist = dist[currentVertex] + child.w;
                if( newDist < dist[child.v]){
                    dist[child.v] = newDist;  // update the new shortest distance for the given child vertex.
                    pq.add(new VertexAndDistance(child.v,dist[child.v])); // also, enqueue the vertex, and its new distance to the priority queue.
                }
            }

        }

        return this.dist;
    }
    
}
