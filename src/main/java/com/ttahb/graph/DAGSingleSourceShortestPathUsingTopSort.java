package com.ttahb.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Using topological sort in DAGs we can calculate the shortest and longest path from the source node to destination node in linear time O(V+E).
 * USP - > Unlike Dijkstra's shortest path, this works with negative weight edges too.
 * @author - bhattvijay69@hotmail.com
 */
public class DAGSingleSourceShortestPathUsingTopSort {

    private final HashMap<String,Vertex> graph;

    public DAGSingleSourceShortestPathUsingTopSort(){
        graph = new HashMap<>();
    }

    /** Adds vertex to the given graph. */
    public void addVertex(Vertex vertex){
        graph.put(vertex.getLabel(),vertex);
    }

    /**
     * Helper class to maintain all attributes of a particular vertex.
     */
    public static class Vertex {

        private String label;
        /** This map contains the adjacent vertex/vertices of the current vertex including the attached weight to that adjacent vertex. */
        private HashMap<Vertex,Integer> adjListWithWeight = new HashMap<>();

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        /** Property that marks if the vertex is visited */
        private boolean visited;

        public Vertex(String label){
            this.label = label;
        }

        /** Sets the adjacent vertex with the attached weight for the given vertex. */
        public void addAdjVertexWithWeight(Vertex vertex, Integer weight){
            adjListWithWeight.put(vertex,weight);
        }

        /** Returns the collection of adjacent vertices with weights for the given vertex */
        public HashMap<Vertex,Integer> getAdjListWithWeight(){
            return this.adjListWithWeight;
        }

        public String getLabel(){
            return this.label;
        }
    }

    public Map<Vertex,Integer> findShortestPath(String start){
        Map<Vertex, Integer> shortestPathMap = new HashMap<>();

        // Performing topological sort to get the required order for calculating shortest path.
        Vertex[] topOrder = topSort();
        int index = -1;

        // initializing all path to be infinity initially and finding the index of source/start vertex
        for(int i=0; i < topOrder.length; i++){
            shortestPathMap.put(topOrder[i],Integer.MAX_VALUE);
            if(topOrder[i].getLabel().equals(start)){
                index = i;
            }
        }

        // assigning source vertex as 0 distance.
        shortestPathMap.put(topOrder[index],0);

        // calculating distance to other vertices from source vertex, replacing the shorter one with each iteration.
        for(int i=index; i<topOrder.length; i++){
            Vertex currentVertex = topOrder[i];
            for(Map.Entry<Vertex,Integer> child: currentVertex.getAdjListWithWeight().entrySet()){
                Integer distance = shortestPathMap.get(currentVertex) + child.getValue();
                if(distance < shortestPathMap.get(child.getKey())){
                    shortestPathMap.put(child.getKey(),distance);
                }
            }
        }

        return  shortestPathMap;
    }

    private Vertex[] topSort(){
        int noOfVertices = this.graph.size();
        Vertex[] topOrder = new Vertex[noOfVertices];
        int index = noOfVertices - 1;
        for( Vertex vertex: this.graph.values()){
            if(!vertex.isVisited()){
                index = depthFirstSearchRecursive(vertex, topOrder, index);
            }
        }

        return topOrder;
    }

    private int depthFirstSearchRecursive(Vertex vertex, Vertex[] topOder, int index) {

        for(Vertex child: vertex.getAdjListWithWeight().keySet()){
            if(!child.isVisited()){
                child.setVisited(true);
                index =  depthFirstSearchRecursive(child, topOder, index);
            }
        }

        topOder[index--] = vertex;
        return index;
    }
}
