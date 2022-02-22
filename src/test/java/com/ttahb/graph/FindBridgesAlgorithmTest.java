package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class FindBridgesAlgorithmTest {

    @Test
    public void testBridges(){

        FindBridgesAlgorithm findBridgesAlgorithm = new FindBridgesAlgorithm(9);
        findBridgesAlgorithm.addEdge(0,1);
        findBridgesAlgorithm.addEdge(1,2);
        findBridgesAlgorithm.addEdge(2,0);
        findBridgesAlgorithm.addEdge(2,3);
        findBridgesAlgorithm.addEdge(2,5);
        findBridgesAlgorithm.addEdge(3,4);
        findBridgesAlgorithm.addEdge(5,6);
        findBridgesAlgorithm.addEdge(6,7);
        findBridgesAlgorithm.addEdge(7,8);
        findBridgesAlgorithm.addEdge(8,5);

        List<Integer> bridges = findBridgesAlgorithm.findBridges();
        for (int i=0; i<bridges.size()/2; i++){
            System.out.println(bridges.get(2*i)+ "  "+bridges.get(2*i+1));
        }

        Assertions.assertEquals(Arrays.asList(3,4,2,3,2,5),bridges);
    }


}
