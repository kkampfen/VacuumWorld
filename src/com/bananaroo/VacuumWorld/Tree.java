package com.bananaroo.VacuumWorld;

/**
 * Created by kkampfen on 6/26/2016.
 */

import java.util.ArrayList;

public class Tree<T> {

    //Initialize root node object
    private Node<T> root;

    //Initialize tree with root node
    public Tree(Node<T> root) {
        this.root = root;
    }

    //Depth-first list of nodes
    public ArrayList<Node<T>> depthFirstTraversal() {
        ArrayList<Node<T>> depthFirst = new ArrayList<Node<T>>();
        buildDepthFirst(root, depthFirst);
        return depthFirst;
    }

    //depthFirstTraversal() helper
    private void buildDepthFirst(Node<T> node, ArrayList<Node<T>> depthFirst) {
        depthFirst.add(node);
        for (Node<T> child : node.getChildren()) {
            buildDepthFirst(child, depthFirst);
        }
    }

    //Get number of nodes in tree for testing
    public int sizeOf() {
        return getChildrenCount(root) + 1;
    }

    //getChildrenCount() helper
    public int getChildrenCount(Node<T> node) {
        int n = node.getChildren().size();
        for (Node<T> child : node.getChildren()) {
            n += getChildrenCount(child);
        }
        return n;
    }
}
