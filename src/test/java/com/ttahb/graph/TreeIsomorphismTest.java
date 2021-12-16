package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TreeIsomorphismTest {

    /**
     * A Helper class to create an undirected graph.
     */
    private static class UndirectedGraph {
           private List<ArrayList<Integer>> adjList;

           public UndirectedGraph(int n){
               adjList = Stream.generate(ArrayList<Integer>::new).limit(n).toList();
           }

           public void addEdge(int u, int v){
               adjList.get(u).add(v);
               adjList.get(v).add(u);
           }

           public List<ArrayList<Integer>> getAdjList(){
               return this.adjList;
           }
    }

    @Test
    public void testIfTheGivenTreesAreIsomorphic(){

        /*
            0     3 ——— 5
            |     |
            1 ——— 4
            |
            2
         */
        UndirectedGraph graph1 = new UndirectedGraph(6);
        graph1.addEdge(0,1);
        graph1.addEdge(1,2);
        graph1.addEdge(1,4);
        graph1.addEdge(4,3);
        graph1.addEdge(3,5);

        /*
                        5
                        |
                  3 ——— 4
                        |
            0 ——— 1 ——— 2

         */
        UndirectedGraph graph2 = new UndirectedGraph(6);
        graph2.addEdge(0,1);
        graph2.addEdge(1,2);
        graph2.addEdge(2,4);
        graph2.addEdge(4,3);
        graph2.addEdge(4,5);

        TreeIsomorphism treeIsomorphism = new TreeIsomorphism();
        boolean isIsomorphic = treeIsomorphism.findIfTreeAreIsomorphic(graph1.getAdjList(),graph2.getAdjList());
        assertTrue(isIsomorphic);
    }

    @Test
    public void testIfTheGivenTreesAreIsomorphic2(){

        /*
                        0
                        |
                        1
                        |
                        2
                     /  |  \
                   3    4    5

         */
        UndirectedGraph graph1 = new UndirectedGraph(6);
        graph1.addEdge(0,1);
        graph1.addEdge(1,2);
        graph1.addEdge(2,3);
        graph1.addEdge(2,4);
        graph1.addEdge(2,5);

        /*

            0         3
             \       /
              1 ——— 4
             /       \
           2          5

         */
        UndirectedGraph graph2 = new UndirectedGraph(6);
        graph2.addEdge(0,1);
        graph2.addEdge(1,2);
        graph2.addEdge(1,4);
        graph2.addEdge(4,3);
        graph2.addEdge(4,5);

        TreeIsomorphism treeIsomorphism = new TreeIsomorphism();
        boolean isIsomorphic = treeIsomorphism.findIfTreeAreIsomorphic(graph1.getAdjList(),graph2.getAdjList());
        // Above trees are not isomorphic
        assertFalse(isIsomorphic);
    }
}
