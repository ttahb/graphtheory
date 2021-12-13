package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.ttahb.graph.RootingATree.*;
import static org.junit.jupiter.api.Assertions.*;

public class RootingATreeTest {

    @Test
    public void testRootTree() {

        //Creating the graph to be rooted.
        RootingATree rootingATree = new RootingATree(7); // initialises a list of list with 7 vertices or nodes called adjList
        rootingATree.addEdge(0, 2);  // adds an edge (u,v) and (v,u) together to the adjList
        rootingATree.addEdge(0, 1);
        rootingATree.addEdge(0, 5);
        rootingATree.addEdge(2, 3);
        rootingATree.addEdge(4, 5);
        rootingATree.addEdge(5, 6);

        // Creating Expected Output
        TreeNode treeNode = new TreeNode(0, null, null);
        TreeNode treeNode1 = new TreeNode(1, treeNode, null);
        treeNode.addChild(treeNode1);
        TreeNode treeNode2 = new TreeNode(2, treeNode, null);
        TreeNode treeNode3 = new TreeNode(3, treeNode2, null);
        treeNode2.addChild(treeNode3);
        treeNode.addChild(treeNode2);
        TreeNode treeNode5 = new TreeNode(5, treeNode, null);
        TreeNode treeNode6 = new TreeNode(6, treeNode5, null);
        treeNode5.addChild(treeNode6);
        TreeNode treeNode7 = new TreeNode(4, treeNode5, null);
        treeNode5.addChild(treeNode7);
        treeNode.addChild(treeNode5);

        //Actual output
        TreeNode rootedTree = rootingATree.rootTree(rootingATree.getAdjList(), 0);


        //comparison of two output
        assertEquals(treeNode.getChildren().size(), rootedTree.getChildren().size());
        assertEquals(treeNode.getChildren().get(2).getChildren().size(), rootedTree.getChildren().get(2).getChildren().size());
    }
}
