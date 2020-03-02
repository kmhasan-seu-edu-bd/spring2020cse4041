/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.mazesearch;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author seu
 */
public class MazeSearchService {
    long exploredStates = 0;
    long frontierNodes = 0;
    long frontierStates = 0;
    
    public String breadthFirstSearch(Maze maze) {
        exploredStates = 0;
        frontierNodes = 0;
        frontierStates = 0;
        
        Node node = new Node(new State(maze.getStartRow(), maze.getStartCol()));
        
        if (goalTest(maze, node.state))
            return solution(node); // return the solution
        
        Queue<Node> frontier = new PriorityQueue<>();//new LinkedList<>();
        Set<State> explored = new HashSet<>();
        Set<State> frontierSet = new HashSet<>();
        
        frontier.add(node);
        frontierSet.add(node.state);
        frontierNodes = Math.max(frontierNodes, frontier.size());
        frontierStates = Math.max(frontierStates, frontierSet.size());
        
        while (true) {
            if (frontier.isEmpty())
                return solution(null); // return no solution found
            
            node = frontier.remove();
            frontierSet.remove(node.state);
            explored.add(node.state);
            exploredStates = Math.max(exploredStates, explored.size());
            
            for (Action action : Action.values()) {
                Node child = getChildNode(maze, node, action);
                
                if (child != null && !explored.contains(child.state)
                        && !frontierSet.contains(child.state)) {
                    if (goalTest(maze, child.state))
                        return solution(child); // got a solution
                    frontier.add(child);
                    frontierSet.add(child.state);
                    frontierNodes = Math.max(frontierNodes, frontier.size());
                    frontierStates = Math.max(frontierStates, frontierSet.size());
                }
            }
        }
    }
    
    private String printPath(Node node) {
        if (node == null || node.action == null)
            return "";
        String oldPath = printPath(node.parent);
        String newPath;
        if (oldPath == null || oldPath.equals(""))
            newPath = node.action.toString();
        else newPath = String.format("%s%s", oldPath, node.action);
        return newPath;
    }
    
    private String solution(Node node) {
        String statistics = String.format("Explored States: %d\nFrontier Nodes: %d\nFrontier States: %d\n", exploredStates, frontierNodes, frontierStates);
        if (node == null)
            return statistics + "\nNo solution found!";
        return statistics + "\n" + printPath(node);
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
        
        if (!maze.getBoard()[childState.row][childState.col])
            return null;
        
        Node childNode = new Node(childState, node.pathCost + 1, node, action);
        
        return childNode;
    }
    
}






