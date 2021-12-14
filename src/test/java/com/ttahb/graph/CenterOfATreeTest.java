package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CenterOfATreeTest {

    @Test
    public void testFindCenterOfTree(){

        CenterOfATree centerOfATree = new CenterOfATree(10);
        centerOfATree.addEdge(0,1);
        centerOfATree.addEdge(1,2);
        centerOfATree.addEdge(2,9);
        centerOfATree.addEdge(2,6);
        centerOfATree.addEdge(2,3);
        centerOfATree.addEdge(3,4);
        centerOfATree.addEdge(3,5);
        centerOfATree.addEdge(6,7);
        centerOfATree.addEdge(6,8);

        assertEquals(Arrays.asList(2),centerOfATree.findTreeCenters());
        // 2 is the center of the tree.
    }

    @Test
    public void testFindCenterOfTree2(){

        CenterOfATree centerOfATree = new CenterOfATree(6);
        centerOfATree.addEdge(0,1);
        centerOfATree.addEdge(1,5);
        centerOfATree.addEdge(1,2);
        centerOfATree.addEdge(2,4);
        centerOfATree.addEdge(2,3);

        assertEquals(Arrays.asList(2,1),centerOfATree.findTreeCenters());
        // 2,1 are the centers of the tree.
    }
}
