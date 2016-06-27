package com.bananaroo.VacuumWorld;

/**
 * Created by kkampfen on 6/26/2016.
 */
public class Environment {

    //Array for squares and initial condition (true means dirty)
    boolean dirty[][] = {{true,true},{true,true}};

    //Check if individual square is dirty (true means dirty)
    public boolean checkSquare(int x, int y) {
        return dirty[x][y];
    }

    //Clean square
    public void cleanSquare(int x, int y) {
        dirty[x][y] = false;
    }

    //Check all squares. Return true if environment is still dirty, false if clean
    public boolean checkEnvironment() {
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                if (dirty[x][y] == true)
                    return true;
            }
        }
        return false;
    }
}