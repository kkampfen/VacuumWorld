package com.bananaroo.VacuumWorld;

/**
 * Created by kkampfen on 6/26/2016.
 */

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T level, index, action;
    private List<Node<T>> children;
    public Node<T> parent;

    //Constructor
    public Node(T level, T index, T action) {
        this.level = level;
        this.index = index;
        this.action = action;
        this.children = new ArrayList<Node<T>>();
    }

    //Add a child to selected node
    public void addChild(Node<T> child) {
        child.setParent(this);
        children.add(child);
    }

    //Get level of current node
    public T getLevel() {
        return this.level;
    }

    //Get action of current node
    public T getAction() {
        return this.action;
    }

    //Parent set method
    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    //Children get method
    public List<Node<T>> getChildren() {
        return this.children;
    }
}
