package com.ttahb.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DepthFirstSearchRecursiveTest {

    @Test
    public void testDFS(){
        DepthFirstSearchRecursive graph = new DepthFirstSearchRecursive(12);

        graph.addEdge(0,1);
        graph.addEdge(0,9);
        graph.addEdge(1,8);
        graph.addEdge(9,8);
        graph.addEdge(8,7);
        graph.addEdge(7,10);
        graph.addEdge(7,11);
        graph.addEdge(10,11);
        graph.addEdge(7,3);
        graph.addEdge(7,6);
        graph.addEdge(6,5);
        graph.addEdge(5,3);
        graph.addEdge(3,2);
        graph.addEdge(3,4);

        graph.depthFirstSearchRecursive(0);
        List<Integer> traversedList = graph.getTraversed();
        traversedList.forEach(System.out::println);
        assertEquals(12, traversedList.size());

    }


}
