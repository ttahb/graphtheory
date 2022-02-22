package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FindArticulationPointsTest {
    
    @Test
    public void testArticulationPoints(){
        FindArticulationPoints findArticulationPoints = new FindArticulationPoints(9);
        findArticulationPoints.addEdge(0,1);
        findArticulationPoints.addEdge(1,2);
        findArticulationPoints.addEdge(2,0);
        findArticulationPoints.addEdge(2,3);
        findArticulationPoints.addEdge(2,5);
        findArticulationPoints.addEdge(3,4);
        findArticulationPoints.addEdge(5,6);
        findArticulationPoints.addEdge(6,7);
        findArticulationPoints.addEdge(7,8);
        findArticulationPoints.addEdge(8,5);

        boolean[] artPoints = findArticulationPoints.findArticulationPoints();

       /* for(int i = 0; i < artPoints.length; i++){
            if(artPoints[i]) System.out.println(i);
        }*/

        Assertions.assertTrue(artPoints[2]);
        Assertions.assertTrue(artPoints[3]);
        Assertions.assertTrue(artPoints[5]);
    }
}
