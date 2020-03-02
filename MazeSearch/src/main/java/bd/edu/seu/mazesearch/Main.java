/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.mazesearch;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seu
 */
public class Main {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public Main() {
        long start, stop;

        logger.log(Level.INFO, "Started generating maze");
        MazeGenerationService.generateMaze("maze.txt", 10_000, 10_000, 30);
        logger.log(Level.INFO, "Done generating maze");

        logger.log(Level.INFO, "Started reading maze from file");
        Maze maze = MazeReaderService.readMaze("maze.txt");
        logger.log(Level.INFO, "Done reading maze from file");

//        System.out.println(maze);
//        System.out.println(maze.getBoardAsString());
        
        MazeSearchService mazeSearchService = new MazeSearchService();
        logger.log(Level.INFO, "Started searching through the maze");
        start = System.currentTimeMillis();
        String solution = mazeSearchService.breadthFirstSearch(maze);
        stop = System.currentTimeMillis();
        logger.log(Level.INFO, "Done searching through the maze");
        System.out.printf("Time taken: %.3f seconds\n", (stop - start) / 1000.0);
        System.out.printf(solution);
    }
    
    public static void main(String args[]) {
        new Main();
    }
}







