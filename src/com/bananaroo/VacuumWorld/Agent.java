package com.bananaroo.VacuumWorld;

/**
 * Created by kkampfen on 6/26/2016.
 */
public class Agent {

    //Agent intitial coordinates
    int xPos = 0, yPos = 0;

    //Agent actions-to-String conversion for output.  Did not end up using this method
    //because there was too much output and the console would overrun
    public String nameAction(int actionCode) {
        switch (actionCode) {
            case 0: return "No Action, Initial State";
            case 1: return "Suck";
            case 2: return "Move North";
            case 3: return "Move East";
            case 4: return "Move South";
            case 5: return "Move West";
        }
        return "No Action";
    }

    //Try agent actions based on action code in search node
    public boolean tryAction(int actionCode) {
        switch (actionCode) {
            case 1: return true;
            case 2: {
                if (yPos == 0) {
                    setY(1);
                    return true;
                }
                else return false;
            }
            case 3: {
                if (xPos == 0){
                    setX(1);
                    return true;
                }
                else return false;
            }
            case 4: {
                if (yPos == 1) {
                    setY(0);
                    return true;
                }
                else return false;
            }
            case 5: {
                if (xPos == 1) {
                    setX(0);
                    return true;
                }
                else return false;
            }
        }
        return false;
    }

    //Set agent Y and X positions
    private void setY(int y) {
        yPos = y;
    }

    private void setX(int x) {
        xPos = x;
    }

    //Get agent Y and X positions
    public int getY() {
        return yPos;
    }

    public int getX() {
        return xPos;
    }
}
