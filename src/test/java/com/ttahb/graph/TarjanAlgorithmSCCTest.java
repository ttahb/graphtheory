package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TarjanAlgorithmSCCTest {

    @Test
    public void testTarjanAlgorithm(){

        TarjanAlgorithmSCC tarjanAlgorithmSCC = new TarjanAlgorithmSCC(8);
        tarjanAlgorithmSCC.addDirectedEdge(0,1);
        tarjanAlgorithmSCC.addDirectedEdge(1,2);
        tarjanAlgorithmSCC.addDirectedEdge(2,0);
        tarjanAlgorithmSCC.addDirectedEdge(3,4);
        tarjanAlgorithmSCC.addDirectedEdge(3,7);
        tarjanAlgorithmSCC.addDirectedEdge(4,5);
        tarjanAlgorithmSCC.addDirectedEdge(5,6);
        tarjanAlgorithmSCC.addDirectedEdge(5,0);
        tarjanAlgorithmSCC.addDirectedEdge(6,4);
        tarjanAlgorithmSCC.addDirectedEdge(6,0);
        tarjanAlgorithmSCC.addDirectedEdge(6,2);
        tarjanAlgorithmSCC.addDirectedEdge(7,3);
        tarjanAlgorithmSCC.addDirectedEdge(7,5);

        Map<Integer, List<Integer>> output = tarjanAlgorithmSCC.findSCC();

        //print sccs

        /*for(List<Integer> scc: output.values()){
            System.out.println("scc "+scc);
        }*/

        /*scc [2, 1, 0]
        scc [6, 5, 4]
        scc [7, 3]*/

        // assert 3 strongly connected components.
        assertEquals(3,output.size());
    }
}
