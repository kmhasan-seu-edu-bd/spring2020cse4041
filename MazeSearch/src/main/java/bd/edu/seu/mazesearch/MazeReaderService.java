package bd.edu.seu.mazesearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MazeReaderService {
    public static Maze readMaze(String filename) {
        try {
            List<String> lineList = Files.readAllLines(Paths.get(filename));

            String[] tokens = lineList.get(0).split("\\ ");
            int rows = Integer.parseInt(tokens[0]);
            int cols = Integer.parseInt(tokens[1]);
            int startRow = Integer.parseInt(tokens[2]);
            int startCol = Integer.parseInt(tokens[3]);
            int endRow = Integer.parseInt(tokens[4]);
            int endCol = Integer.parseInt(tokens[5]);

            Maze maze = new Maze(rows, cols, startRow, startCol, endRow, endCol);

            for (int r = 0; r < rows; r++) {
                String line = lineList.get(r + 1);
                for (int c = 0; c < cols; c++)
                    maze.setCell(r, c, line.charAt(c) != '#');
            }

            return maze;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
