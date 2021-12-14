package com.ttahb.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Center(s) of a tree -
 * 1) Mostly this algorithm used as a subroutine in other algorithms.
 * 2) Useful in selecting a root node while trying to rooting a tree.
 * 3) Chances of more than one center, however there can't be more than two centres.
 * @author bhattvijay69@hotmail.com
 */
public class CenterOfATree {

    /** Adjacency list representing the undirected graph. */
    private final List<ArrayList<Integer>> adjList;

    /**
     * Initializes a graph for which center(s) to be detected. Represents the graph as adjacency list.
     * @param n - number of vertices
     */
    public CenterOfATree(int n){
        adjList = Stream.generate(ArrayList<Integer>::new).limit(n).toList();
    }

    /**
     * For any input u, v adds an edge (u,v) and (v,u) to the graph in form of adjList.
     */
    public void addEdge(int u, int v){
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public List<Integer> findTreeCenters(){
        int n = adjList.size();
        List<Integer> leaves = new ArrayList<>();
        int[] degrees = new int[n];

        for(int i=0;i<n;i++){
            degrees[i] = adjList.get(i).size();
            // add all independent nodes or leaf nodes.
            if(degrees[i] == 0 || degrees[i] == 1){
                leaves.add(i);
            }
        }

        int count = leaves.size();

        // Every iteration peels off one set of leaf node.
        while(count < n){
            ArrayList<Integer> new_leaves = new ArrayList<>();
            for(Integer leaf:leaves){
                for(Integer neighbour: adjList.get(leaf)){
                    // ignore already peeled off leaf nodes.
                    if(degrees[neighbour] == 0){
                        continue;
                    }
                    degrees[neighbour] = degrees[neighbour] - 1;
                    if(degrees[neighbour] == 1){
                        new_leaves.add(neighbour);
                    }
                }
                degrees[leaf] = 0;
            }
            count = count + new_leaves.size();
            leaves = new_leaves;
        }
        return leaves;
    }
}
