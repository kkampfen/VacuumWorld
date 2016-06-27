package com.bananaroo.VacuumWorld;

import java.util.ArrayList;

/**
 * Created by kkampfen on 6/26/2016.
 */

public class Main {
    public static void main(String[] args) {

        int limit = 10;
        int index = 1;
        int action = 1;

        //Create agent object
        Agent agent = new Agent();

        //Create environment object
        Environment environment = new Environment();

        //Lines 25-45 create the search tree

        //Create a new Integer type node - (level, index, action)
        Node<Integer> root = new Node<Integer>(1, index, 0);
        index++;

        //Create a tree, providing the root node
        Tree<Integer> tree = new Tree<Integer>(root);

        //Add levels down to limit
        for (int level = 2; level <= limit; level++) {
            ArrayList<Node<Integer>> leafs = tree.depthFirstTraversal();
            for (Node<Integer> node : leafs) {
                if (node.getLevel() == level - 1) {
                    while (action <= 5) {
                        node.addChild(new Node<Integer>(level, index, action));
                        index++;
                        action++;
                    }
                    action = 1;
                }
            }
        }

        //Test for size of search tree and print size to console
        System.out.println("The search tree has " + tree.sizeOf() + " nodes.");
        System.out.println(" ");
        System.out.println("Initial State of Environment:");
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                if (!environment.checkSquare(x, y)) {
                    System.out.println("Square (" + x + "," + y + ") is clean.");
                } else {
                    System.out.println("Square (" + x + "," + y + ") is dirty.");
                }
            }
        }
        System.out.println(" ");
        System.out.println("Searching for Solution");
        //System.out.println(" ");

        //Create depth-first ArrayList to use for solution search
        ArrayList<Node<Integer>> nodeList = tree.depthFirstTraversal();

        //Lines 57-73 perform the solution search
        int nodesVisited = 0;
        int actions = 0;
        int attemptSuck = 0;
        int visits[][] = {{0,0},{0,0}};

        //Loop through nodes
        for (Node<Integer> node : nodeList) {

            //Count nodes visited as check to ensure all are visited
            nodesVisited++;

            //Console output line for testing purposes.  Place breakpoint here and run debug to track how agent
            //is progressing.  Commented out because output causes console overrun.
            //System.out.println("Current action is " + agent.nameAction(node.getAction()));

            //Determine if valid action.  Suck action is always valid, move action only valid if move is possible.
            if (agent.tryAction(node.getAction())) {

                //Console output line for testing purposes.  Commented out because output causes console overrun.
                //System.out.println("Agent is in square (" + agent.getX() + "," + agent.getY() + ").");

                //Count number of valid actions
                actions++;

                //If suck action, always attempt even if square is clean.
                if (node.getAction() == 1) {
                    attemptSuck++;
                    if (environment.checkSquare(agent.getX(), agent.getY())) {
                        environment.cleanSquare(agent.getX(), agent.getY());
                        System.out.println("Dirty square found and agent action is Suck. Square (" + agent.getX() + "," + agent.getY() + ") is now clean.  Cost so far is " + actions);
                    }
                } else {
                    //Count number of times a square is visited
                    visits[agent.getX()][agent.getY()]++;
                }
            }

            //Check if environment is clean and end for each loop if so
            if (!environment.checkEnvironment())
                break;
        }

        //Print results
        System.out.println(" ");
        System.out.println("Search Results");
        if (!environment.checkEnvironment()) {
            System.out.println("Solution found and environment is clean. Solution found in " + actions + " actions.");
        } else {
            System.out.println("Solution not found and environment not clean.");
            for (int x = 0; x < 2; x++) {
                for (int y = 0; y < 2; y++) {
                    if (!environment.checkSquare(x, y)) {
                        System.out.println("Square (" + x + "," + y + ") was visited " + visits[x][y] + " times and is clean.");
                    } else {
                        System.out.println("Square (" + x + "," + y + ") was visited " + visits[x][y] + " times and is still dirty.");
                    }
                }
            }
            System.out.println("Agent attempted to suck " + attemptSuck + " times.");
        }

        //Output to cross reference with number of nodes to ensure all nodes were explored
        System.out.println("Total cost: " + actions + " successful actions and " + nodesVisited + " nodes visited.");
    }
}
