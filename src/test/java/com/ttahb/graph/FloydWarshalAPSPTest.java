package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FloydWarshalAPSPTest {

    @Test
    public void testInitGraph(){
        FloydWarshallAPSP floydWarshallAPSP = new FloydWarshallAPSP(4);

        //test values across diagonal
        assertEquals(0,floydWarshallAPSP.getInitialGraph()[0][0]);
        assertEquals(0,floydWarshallAPSP.getInitialGraph()[1][1]);
        assertEquals(0,floydWarshallAPSP.getInitialGraph()[2][2]);
        assertEquals(0,floydWarshallAPSP.getInitialGraph()[3][3]);

        //test other samples
        assertEquals(Double.POSITIVE_INFINITY,floydWarshallAPSP.getInitialGraph()[0][1]);
        assertEquals(Double.POSITIVE_INFINITY,floydWarshallAPSP.getInitialGraph()[2][1]);
        assertEquals(Double.POSITIVE_INFINITY,floydWarshallAPSP.getInitialGraph()[1][3]);
        assertEquals(Double.POSITIVE_INFINITY,floydWarshallAPSP.getInitialGraph()[3][0]);

    }

    @Test
    public void testAllPairShortestPath(){
        FloydWarshallAPSP floydWarshallAPSP = new FloydWarshallAPSP(4);
        //get initial graph with default 0 along diagonal assuming no self loop, all others initially marked 'positive infinity'
        double[][] graph = floydWarshallAPSP.getInitialGraph();

        // add additional values
        graph[0][1] = 3;
        graph[0][3] = 7;
        graph[1][0] = 8;
        graph[1][2] = 2;
        graph[2][0] = 5;
        graph[2][3] = 1;
        graph[3][0] = 2;

        double[][] graphAPSP = floydWarshallAPSP.getAllPairShortestPath(graph);

        //assert shortest distance from i to j
        assertEquals(3,graphAPSP[0][1]);
        assertEquals(5,graphAPSP[0][2]);
        assertEquals(6,graphAPSP[0][3]);
        assertEquals(5,graphAPSP[1][0]);
        assertEquals(2,graphAPSP[1][2]);
        assertEquals(3,graphAPSP[1][3]);
        assertEquals(3,graphAPSP[2][0]);
        assertEquals(6,graphAPSP[2][1]);
        assertEquals(1,graphAPSP[2][3]);
        assertEquals(2,graphAPSP[3][0]);
        assertEquals(5,graphAPSP[3][1]);
        assertEquals(7,graphAPSP[3][2]);

        //i to i path remains unaffected since no self loop.
        assertEquals(0,graphAPSP[0][0]);
        assertEquals(0,graphAPSP[1][1]);
        assertEquals(0,graphAPSP[2][2]);
        assertEquals(0,graphAPSP[3][3]);

        //test shortest path route
        List<Integer> path = floydWarshallAPSP.getShortestPath(1,0);
        for(Integer p: path){
            System.out.println(p);
        }
        assertEquals(Arrays.asList(1,2,3,0),path);

        List<Integer> path2 = floydWarshallAPSP.getShortestPath(0,3);
        assertEquals(Arrays.asList(0,1,2,3),path2);
    }

    @Test
    public void testAllPairShortestPath2(){
        FloydWarshallAPSP floydWarshallAPSP = new FloydWarshallAPSP(4);
        double[][] graph = floydWarshallAPSP.getInitialGraph();
        graph[0][1] = 5;
        graph[0][3] = 10;
        graph[1][2] = 3;
        graph[2][3] = 1;

        double[][] graphAPSP = floydWarshallAPSP.getAllPairShortestPath(graph);
        //check shortest distance randomly.
        assertEquals(5, graphAPSP[0][1]);
        assertEquals(4, graphAPSP[1][3]);
        assertEquals(Double.POSITIVE_INFINITY, graphAPSP[1][0]);
        assertEquals(8, graphAPSP[0][2]);
        assertEquals(9, graphAPSP[0][3]);
        assertEquals(Double.POSITIVE_INFINITY, graphAPSP[3][2]);

        // test shortest route.
        List<Integer> path = floydWarshallAPSP.getShortestPath(3,2);
        assertEquals(new ArrayList<>(),path);

        List<Integer> path2 = floydWarshallAPSP.getShortestPath(1,0);
        assertEquals(new ArrayList<>(),path2);
    }

    @Test
    public void testAllPairShortestPathNegativeCycle(){
        FloydWarshallAPSP floydWarshallAPSP = new FloydWarshallAPSP(7);
        double[][] graph = floydWarshallAPSP.getInitialGraph();

        graph[0][1] = 1;
        graph[0][2] = 1;
        graph[1][3] = 4;
        graph[2][1] = 1;
        graph[3][4] = 1;
        graph[3][5] = 1;
        graph[3][2] = -6;
        graph[4][5] = 1;
        graph[4][6] = 3;
        graph[5][6] = 1;

        double[][] graphWithNegativeCycles = floydWarshallAPSP.getAllPairShortestPath(graph);

        /*for(int i=0; i < graphWithNegativeCycles.length; i++){
            for(int j = 0; j < graphWithNegativeCycles.length; j++){
                System.out.print(graphWithNegativeCycles[i][j]+ " ");
            }
            System.out.println();
        }*/

        //asserting few cases
        assertEquals(Double.NEGATIVE_INFINITY, graphWithNegativeCycles[0][1]);
        assertEquals(Double.NEGATIVE_INFINITY, graphWithNegativeCycles[0][2]);
        assertEquals(Double.NEGATIVE_INFINITY, graphWithNegativeCycles[0][3]);
        assertEquals(Double.NEGATIVE_INFINITY, graphWithNegativeCycles[0][4]);
        assertEquals(Double.NEGATIVE_INFINITY, graphWithNegativeCycles[0][5]);
        assertEquals(Double.NEGATIVE_INFINITY, graphWithNegativeCycles[0][6]);


        List<Integer> path = floydWarshallAPSP.getShortestPath(0,6);
        assertNull(path);
    }



}
