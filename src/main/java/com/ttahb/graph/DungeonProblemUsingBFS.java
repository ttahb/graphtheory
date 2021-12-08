package com.ttahb.graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Dungeon Problem Statement
 * - You are trapped in a 2D dungeon and need to find the quickest way out! The dungeon may or may not be filled with rock.
 *   It takes one minute to move out one unit North, South, East, West. You cannot move diagonally and the maze is surrounded
 *   by rocks on all sides. Is an escape possible, if yes, how long will it take?
 *   # - represents the cells filled with rocks
 *   E - represents the exit
 *   sr - starting position's row index
 *   sc - startting position's column index
 * @author - bhattvijay69@hotmail.com
 */
public class DungeonProblemUsingBFS {


    /** Using an adjacency matrix to represent a 2D dungeon. */
    private final int[][] adjMatrix;
    /** Total number or rows in the dungeon. */
    private final int R;

    /** Total number of column in the dungeon */
    private final int C;

    private final boolean[][] visited;
    private final Queue<Integer> xq;
    private final Queue<Integer> yq;
    private int nodes_left_in_layer = 1;
    private int nodes_in_next_layer = 0;

    /** A constructor which receives a 2D dungeon.*/
    public DungeonProblemUsingBFS(int[][] dungeon, int R, int C){
        this.adjMatrix = dungeon;
        this.R = R;
        this.C = C;
        this.visited = new boolean[R][C];
        this.xq = new ArrayDeque<>();
        this.yq = new ArrayDeque<>();
    }

    /**
     * Find the time taken to escape the dungeon from the starting point, if an escape is possible.
     * @param sr - starting row
     * @param sc - starting column
     * @return - Time taken in minutes if there exist an escape, else return -1.
     */
    public int findTheTimeToReachExit(int sr, int sc){

        boolean reached_end = false;
        if(sr < 0 && sr >= R && sc < 0 && sc >= C){
            return -1;
        }

        visited[sr][sc] = true;
        xq.add(sr);
        yq.add(sc);
        int move_count = 0;

        while (!xq.isEmpty() && !yq.isEmpty()){

            int r = xq.remove();
            int c = yq.remove();

            if(adjMatrix[r][c] == 'E'){
                reached_end = true;
                break;
            }

            exploreNeighbouringCubes(r,c);
            nodes_left_in_layer--;

            // if nodes in current layer explored, completely increment move_count
            if(nodes_left_in_layer == 0){
                move_count++;
                nodes_left_in_layer = nodes_in_next_layer; //update the current layer
                nodes_in_next_layer = 0;
            }

        }

        if(reached_end == true)
            return move_count;
        else
            return -1;
    }

    /**
     * Explores the neighbours in north, south, east and west directions.
     * @param r - row for which neighbours to be exlored
     * @param c - column for which neighbours to be explored
     */
    private void exploreNeighbouringCubes(int r, int c) {

        int[] dr = {-1,1,0,0};   // Array to represent N,S,E,W direction for a row, row can move only North(-1), South(+1) in a matrix.
        int[] dc = {0,0,1,-1};   // Similarly for a column East(+1), West(-1).

        // Iterating all four direction meanwhile avoiding already visited ones, cells with rocks.
        for(int i=0; i<4; i++){

            int rr = r + dr[i];
            int cc = c + dc[i];

            // ensure the new position is not outside dungeon boundaries
            if(rr < 0 || rr >= R || cc < 0 || cc >= C)
                continue;
            // also ensure new position is not already visited, or contain cubes with rocks.
            if(visited[rr][cc] || adjMatrix[rr][cc] == '#')
                continue;

            xq.add(rr);
            yq.add(cc);

            nodes_in_next_layer++;

        }
    }

}
