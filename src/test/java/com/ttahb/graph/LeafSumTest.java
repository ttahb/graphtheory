package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.ttahb.graph.LeafSum.*;

public class LeafSumTest {

    @Test
    public void testFindLeafNodesSum(){

        // Creating a Rooted Tree.
        Node node1 = new Node(2);
        Node node2 = new Node(9);
        Node node3 = new Node(1);
        node3.addChild(node1,node2);

        Node node4 = new Node(-6);

        Node node5 = new Node(4);
        node5.addChild(node3,node4);

        Node node6 = new Node(8);

        Node node7 = new Node(7);
        node7.addChild(node6);

        Node node8 = new Node(0);
        Node node9 = new Node(-4);

        Node node10 = new Node(3);
        node10.addChild(node7,node8,node9);

        Node node11 = new Node(5);
        node11.addChild(node10, node5);

        LeafSum leafSum = new LeafSum();
        //passing the root node to leafSum
        Assertions.assertEquals(9, leafSum.leafSum(node11));

    }
}