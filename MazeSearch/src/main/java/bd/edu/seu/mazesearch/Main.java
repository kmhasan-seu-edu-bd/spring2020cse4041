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
public class Main {

    public Main() {
        MazeGenerationService.generateMaze("maze.txt", 10_000, 20_000, 30);
    }
    
    public static void main(String args[]) {
        new Main();
    }
}
