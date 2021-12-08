package com.ttahb.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DungeonProblemUsingBFSTest {

    @Test
    public void testCreateDungeonUsingAdjMatrix(){

        int[][] dungeon = getInts();
        DungeonProblemUsingBFS dungeonProblem = new DungeonProblemUsingBFS(dungeon,5,7);

        int timeToReach = dungeonProblem.findTheTimeToReachExit(0,0); // starting cell (0,0)

        Assertions.assertEquals(9,timeToReach);

    }

    @Test
    public void testCreateDungeonUsingAdjMatrix2(){

        int[][] dungeon = getInts();
        DungeonProblemUsingBFS dungeonProblem = new DungeonProblemUsingBFS(dungeon,5,7);

        int timeToReach = dungeonProblem.findTheTimeToReachExit(2,4); // starting cell (2,4)

        Assertions.assertEquals(3,timeToReach);

    }

    /**
     *       dash -  represents empty cell
     *       # - represents cubes filled with rocks
     *       E - represents exit cube.
     *       Rest all empty cubes.
     *       sr - starting position's row
     *       sc - starting position's column
     *
     *      Creates a dungeon
     *      - - - # - - -
     *      - # - - - # -
     *      - # - - - - -
     *      - - # # - - -
     *      # - # E - # -
     *
     * @return - a dungeon of size [5][7]
     */
    private int[][] getInts() {
        int[][] dungeon = new int[5][7];

        dungeon[0][3] = '#';
        dungeon[1][1] = '#';
        dungeon[1][5] = '#';
        dungeon[2][1] = '#';
        dungeon[3][2] = '#';
        dungeon[3][3] = '#';
        dungeon[4][0] = '#';
        dungeon[4][2] = '#';
        dungeon[4][3] = 'E';
        dungeon[4][5] = '#';

        return dungeon;
    }

}
