package com.ttahb.graph;

import java.util.*;
import java.util.stream.Stream;

/**
 * A lazy implementation of Dijkstra's shortest path. It is a single source shortest path algo for non-negative edge
 * weights. Time Complexity - O(E*logV).
 * For simplicity, assuming numbers as vertices starting with 0. And graph is implemented using adjacency list
 * @author - Vijay Bhatt, bhattvijay69@hotmail.com
 */
public class DijkstraLazyUsingPQ {

    private final List<ArrayList<Edge>> graph;
    /** an array to contain shortest distance of each vertex from source vertex. */
    private final int[] dist;
    /** boolean array to check if particular vertex is explored or not */
    private final boolean[] visited;
    /** Helper array to build path backwards */
    private final Integer[] prev;

    /** n - number of vertices */
    public DijkstraLazyUsingPQ(int n){
        this.graph = Stream.generate(ArrayList<Edge>::new).limit(n).toList();
        this.visited = new boolean[n];
        this.dist = new int[n];
        this.prev = new Integer[n];
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

    public int[] findSSShortestPathUsingDijkstra(int start, int end){

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
            // avoiding vertex already explored
            if(visited[currentVertex]){
                continue;
            }

            this.visited[currentVertex] = true; // mark current vertex explored if its not explored already
            List<Edge> childVerticesList = this.graph.get(currentVertex);

            for(Edge child: childVerticesList){
                if(visited[child.v]){
                    continue;
                }
                int newDist = dist[currentVertex] + child.w;
                if( newDist < dist[child.v]){
                    dist[child.v] = newDist;  // update the new shortest distance for the given child vertex.
                    this.prev[child.v] = currentVertex; // update parent node.
                    pq.add(new VertexAndDistance(child.v,dist[child.v])); // also, enqueue the vertex, and its new distance to the priority queue.
                }
            }

            // stop when end vertex is reached
            if(end == currentVertex){
                return dist;
            }
        }

        return this.dist;
    }


    public List<Integer> buildShortestPath(int start, int end){
        int noOfVertices = this.graph.size();
        List<Integer> path = new ArrayList<>();
        if(start < 0 || start >= noOfVertices  || end < 0 || end >= noOfVertices){
            throw new IllegalArgumentException("invalid start or end position");
        }

        int dist[] = findSSShortestPathUsingDijkstra(start, end);

        if(dist[end] == Integer.MAX_VALUE){
            return path;
        }

        for(Integer i= end ; i != null ; i = prev[i]){
            path.add(i);
        }
        Collections.reverse(path);
        return path;
    }

    public List<Integer> buildShortestPath(int start){
        return buildShortestPath(start, this.graph.size()-1);
    }

    public int[] findSSShortestPathUsingDijkstra(int start){
        return findSSShortestPathUsingDijkstra(start, this.graph.size()-1);
    }
}
