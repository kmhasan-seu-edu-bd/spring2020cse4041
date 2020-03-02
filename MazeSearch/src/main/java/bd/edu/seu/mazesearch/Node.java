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
public class Node implements Comparable<Node> {
    State state;
    int pathCost;
    Node parent;
    Action action;

    public Node() {
        pathCost = 0;
        parent = null;
    }

    public Node(State state) {
        this();
        this.state = state;
    }
    
    public Node(State state, int pathCost, Node parent, Action action) {
        this.state = state;
        this.pathCost = pathCost;
        this.parent = parent;
        this.action = action;
    }

    @Override
    public int compareTo(Node that) {
        int a = this.pathCost;
        int b = that.pathCost;
        return a - b;
    }
}



