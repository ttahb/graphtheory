package com.ttahb.graph;

import com.ttahb.graph.HeightOfTree.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeightOfTreeTest {


    /**
     * Height of a tree with more than one node.
     */
    @Test
    public void findHeightOfTreeTest() {

        Node node1 = new Node(4);
        Node node2 = new Node(5);
        Node node3 = new Node(2);
        node3.setLeft(node1);
        node3.setRight(node2);

        Node node4 = new Node(3);
        Node node5 = new Node(1); // this is our root node.
        node5.setRight(node4);
        node5.setLeft(node3);

        HeightOfTree heightOfTree = new HeightOfTree();

        Assertions.assertEquals(2, heightOfTree.findHeight(node5));

    }

    /**
     * A tree with 0 nodes. Basically null.
     */
    @Test
    public void findHeightOfTreeWithNullNode() {
        HeightOfTree heightOfTree = new HeightOfTree();
        Assertions.assertEquals(-1, heightOfTree.findHeight(null));
    }

    /**
     * Tests the height of a tree with only one node.
     */
    @Test
    public void findHeightOfTreeWithOneNode() {
        HeightOfTree heightOfTree = new HeightOfTree();
        Assertions.assertEquals(0, heightOfTree.findHeight(new Node(5)));
    }
}
