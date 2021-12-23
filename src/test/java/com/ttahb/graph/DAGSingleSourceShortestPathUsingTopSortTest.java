package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.ttahb.graph.DAGSingleSourceShortestPathUsingTopSort.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAGSingleSourceShortestPathUsingTopSortTest {

    @Test
    public void testSSShortestPathUsingTopSort(){

        DAGSingleSourceShortestPathUsingTopSort graph = new DAGSingleSourceShortestPathUsingTopSort();

        Vertex vertexH = new Vertex("H");
        graph.addVertex(vertexH);

        Vertex vertexE = new Vertex("E");
        vertexE.addAdjVertexWithWeight(vertexH,9);
        graph.addVertex(vertexE);

        Vertex vertexF = new Vertex("F");
        vertexF.addAdjVertexWithWeight(vertexH,1);
        graph.addVertex(vertexF);

        Vertex vertexG = new Vertex("G");
        vertexG.addAdjVertexWithWeight(vertexH,2);
        graph.addVertex(vertexG);

        Vertex vertexD = new Vertex("D");
        vertexD.addAdjVertexWithWeight(vertexE,-4);
        vertexD.addAdjVertexWithWeight(vertexF,5);
        vertexD.addAdjVertexWithWeight(vertexG,2);
        graph.addVertex(vertexD);


        Vertex vertexC = new Vertex("C");
        vertexC.addAdjVertexWithWeight(vertexD,8);
        vertexC.addAdjVertexWithWeight(vertexG,11);
        graph.addVertex(vertexC);

        Vertex vertexB = new Vertex("B");
        vertexB.addAdjVertexWithWeight(vertexD,4);
        vertexB.addAdjVertexWithWeight(vertexE,11);
        vertexB.addAdjVertexWithWeight(vertexC,4);
        graph.addVertex(vertexB);

        Vertex vertexA = new Vertex("A");
        vertexA.addAdjVertexWithWeight(vertexB,3);
        vertexA.addAdjVertexWithWeight(vertexC,6);
        graph.addVertex(vertexA);

        // Selecting source nodes as "C"
        Map<Vertex, Integer> shortestPathMap = graph.findShortestPath("C");

        //Lets find out the shortest distacnces from VertexC to respective given nodes. Integer.MAX_VALUE represents no path.

        assertEquals(Integer.MAX_VALUE, shortestPathMap.get(vertexA));
        assertEquals(Integer.MAX_VALUE, shortestPathMap.get(vertexB));
        assertEquals(0, shortestPathMap.get(vertexC));
        assertEquals(8, shortestPathMap.get(vertexD));
        assertEquals(10, shortestPathMap.get(vertexG));
        assertEquals(4, shortestPathMap.get(vertexE));
        assertEquals(13, shortestPathMap.get(vertexF));
        assertEquals(12, shortestPathMap.get(vertexH));

    }


    @Test
    public void testSSShortestPathUsingTopSort2(){

        DAGSingleSourceShortestPathUsingTopSort graph = new DAGSingleSourceShortestPathUsingTopSort();

        Vertex vertexH = new Vertex("H");
        graph.addVertex(vertexH);

        Vertex vertexE = new Vertex("E");
        vertexE.addAdjVertexWithWeight(vertexH,9);
        graph.addVertex(vertexE);

        Vertex vertexF = new Vertex("F");
        vertexF.addAdjVertexWithWeight(vertexH,1);
        graph.addVertex(vertexF);

        Vertex vertexG = new Vertex("G");
        vertexG.addAdjVertexWithWeight(vertexH,2);
        graph.addVertex(vertexG);

        Vertex vertexD = new Vertex("D");
        vertexD.addAdjVertexWithWeight(vertexE,-4);
        vertexD.addAdjVertexWithWeight(vertexF,5);
        vertexD.addAdjVertexWithWeight(vertexG,2);
        graph.addVertex(vertexD);


        Vertex vertexC = new Vertex("C");
        vertexC.addAdjVertexWithWeight(vertexD,8);
        vertexC.addAdjVertexWithWeight(vertexG,11);
        graph.addVertex(vertexC);

        Vertex vertexB = new Vertex("B");
        vertexB.addAdjVertexWithWeight(vertexD,4);
        vertexB.addAdjVertexWithWeight(vertexE,11);
        vertexB.addAdjVertexWithWeight(vertexC,4);
        graph.addVertex(vertexB);

        Vertex vertexA = new Vertex("A");
        vertexA.addAdjVertexWithWeight(vertexB,3);
        vertexA.addAdjVertexWithWeight(vertexC,6);
        graph.addVertex(vertexA);

        // Selecting source nodes as "C"
        Map<Vertex, Integer> shortestPathMap = graph.findShortestPath("A");

        assertEquals(0, shortestPathMap.get(vertexA));
        assertEquals(3, shortestPathMap.get(vertexB));
        assertEquals(6, shortestPathMap.get(vertexC));
        assertEquals(7, shortestPathMap.get(vertexD));
        assertEquals(9, shortestPathMap.get(vertexG));
        assertEquals(3, shortestPathMap.get(vertexE));
        assertEquals(12, shortestPathMap.get(vertexF));
        assertEquals(11, shortestPathMap.get(vertexH));

    }
}
