/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.mazesearch;

/**
 *
 * @author seu
 */
public class Node {
    State state;
    int pathCost;
    Node parent;

    public Node() {
        pathCost = 0;
        parent = null;
    }

    public Node(State state) {
        this();
        this.state = state;
    }
    
    public Node(State state, int pathCost, Node parent) {
        this.state = state;
        this.pathCost = pathCost;
        this.parent = parent;
    }
}
