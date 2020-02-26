/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.mazesearch;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author seu
 */
public class MazeSearchService {
    public void breadthFirstSearch(Maze maze) {
        Node node = new Node(new State(maze.getStartRow(), maze.getStartCol()));
        
        if (goalTest(maze, node.state))
            return; // return the solution
        
        Queue<Node> frontier = new LinkedList<>();
        Set<State> explored = new HashSet<>();
        Set<State> frontierSet = new HashSet<>();
        frontier.add(node);
        frontierSet.add(node.state);
        
        while (true) {
            if (frontier.isEmpty())
                return; // return no solution found
            
            node = frontier.remove();
            explored.add(node.state);
            
            for (Action action : Action.values()) {
                Node child = getChildNode(maze, node, action);
                
                if (child != null && !explored.contains(child.state)
                        && !frontierSet.contains(child.state)) {
                    if (goalTest(maze, child.state))
                        return; // got a solution
                    frontier.add(child);
                }
            }
        }
    }
    
    public boolean goalTest(Maze maze, State state) {
        return state.row == maze.getEndRow() 
                && state.col == maze.getEndCol();
    }

    private Node getChildNode(Maze maze, Node node, Action action) {
        State childState = new State(node.state.row + action.dr, 
                node.state.col + action.dc);
        
        if (childState.row < 0 
                || childState.col < 0 
                || childState.row >= maze.getRows()
                || childState.col >= maze.getCols())
            return null;
        
        Node childNode = new Node(childState, node.pathCost + 1, node);
        
        return childNode;
    }
    
}






