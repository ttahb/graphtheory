package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountOfConnectedComponentsGraphUsingDFSTest {

    @Test
    public void testConnectedComponents() {
        CountOfConnectedComponentsGraphUsingDFS graph = new CountOfConnectedComponentsGraphUsingDFS(18);
        //component 1
        graph.addEdge(0, 4);
        graph.addEdge(0, 13);
        graph.addEdge(0, 14);
        graph.addEdge(0, 8);
        graph.addEdge(4, 8);
        graph.addEdge(8, 14);
        graph.addEdge(14, 13);

        //component 2
        graph.addEdge(6, 7);
        graph.addEdge(7, 11);
        graph.addEdge(11, 6);

        //component 3
        graph.addEdge(1, 5);
        graph.addEdge(5, 16);
        graph.addEdge(5, 17);

        //component 4
        graph.addEdge(3, 9);
        graph.addEdge(9, 15);
        graph.addEdge(9, 2);
        graph.addEdge(15, 2);
        graph.addEdge(15, 10);

        //component 5
        graph.addEdge(12);

        int count = graph.countConnectedComponents();
        System.out.println(count);
        Assertions.assertEquals(5, count);
    }
}
