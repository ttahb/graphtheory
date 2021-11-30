package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class JavaFeatures {

    @Test
    void testAdd(){
        List<ArrayList<Integer>> adjacencyList = Stream.generate(ArrayList<Integer>::new).limit(3).toList();
        assertEquals(new ArrayList<>(Collections.nCopies(3,new ArrayList<Integer>())),adjacencyList);
    }
}