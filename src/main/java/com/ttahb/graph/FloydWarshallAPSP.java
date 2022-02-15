package com.ttahb.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * All pair shortest path - Floyd Warshall Algorithm with route or path construction.
 * @author Vijay Bhatt - bhattvijay69@hotmail.com
 */
public class FloydWarshallAPSP {

    private double[][] graph; //2d matrix which will represent our graph.
    private Integer[][] next; //2d matrix to build up shortest route taken.

    private int V; // number of vertices

    /**
     * Initiates a VxV 2D empty matrix.
     * @param V
     */
    public FloydWarshallAPSP(int V){
        this.graph = new double[V][V];
        this.next = new Integer[V][V];
        for(int i=0; i < V; i++){
            for(int j=0; j<V; j++){
                this.graph[i][j] = Double.POSITIVE_INFINITY;
                this.next[i][j] = j; // initialise
                if(i == j) {
                    this.graph[i][j] = 0;
                }
            }
        }

        this.V = V;
    }

    public double[][] getInitialGraph(){
        return this.graph;
    }


    public double[][] getAllPairShortestPath(double[][] graph) {
        //get all pair the shortest path
        for(int k=0; k < V;k++){
            for(int i=0; i<V; i++){
                for(int j=0;j<V;j++){
                    if(graph[i][k] + graph[k][j] < graph[i][j] ){
                        graph[i][j] = graph[i][k] + graph[k][j];
                        this.next[i][j] = this.next[i][k];
                    }
                }
            }
        }

        //identify all edges affected by negative cycle and assign negative infinity to
        // all vertex affected by negative cycle.
        for(int k=0; k < V;k++){
            for(int i=0; i<V; i++){
                for(int j=0;j<V;j++){
                    if(graph[i][k] + graph[k][j] < graph[i][j] ){
                        graph[i][j] = Double.NEGATIVE_INFINITY;
                        this.next[i][j] = -1; //assign -1 for negative path.
                    }
                }
            }
        }

        return graph;
    }

    public List<Integer> getShortestPath(int start, int end) {
        List<Integer> path = new ArrayList<>();
        if (this.graph[start][end] == Double.POSITIVE_INFINITY) {
            return path;
        }

        Integer at = start;
        for (;at != end; at = this.next[at][end]) {
            if (at == -1){
                return null;
            }
            path.add(at);
        }

        if(this.next[at][end] == -1)
            return null;
        path.add(end);
        return path;
    }

}
