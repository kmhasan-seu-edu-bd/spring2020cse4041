/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.mazesearch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seu
 */
public class MazeGenerationService {

    public static void generateMaze(String filename,
            int rows, int cols,
            double percentageOfBlockedCells) {

        try (RandomAccessFile outputFile = new RandomAccessFile(filename, "rw")) {
            outputFile.setLength(0);
            
            Random random = new Random();

            int startRow, startCol;
            int endRow, endCol;

            startRow = random.nextInt(rows);
            startCol = random.nextInt(cols);
            do {
                endRow = random.nextInt(rows);
                endCol = random.nextInt(cols);
            } while (startRow == endRow && startCol == endCol);

            String startEnd = String.format("%d %d %d %d %d %d\n",
                    rows, cols, startRow, startCol, endRow, endCol);
            outputFile.writeBytes(startEnd);
            double blockingFactor = percentageOfBlockedCells / 100.0;
            StringBuffer stringBuffer = new StringBuffer();
            for (int r = 0; r < rows; r++) {
                stringBuffer.setLength(0);
                for (int c = 0; c < cols; c++) {
                    if (r == startRow && c == startCol) {
                        stringBuffer.append('S');
                    } else if (r == endRow && c == endCol) {
                        stringBuffer.append('D');
                    } else {
                        double randomNumber = Math.random();
                        if (randomNumber <= blockingFactor) {
                            stringBuffer.append('#');
                        } else {
                            stringBuffer.append('.');
                        }
                    }
                }
                stringBuffer.append('\n');
                outputFile.writeBytes(stringBuffer.toString());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MazeGenerationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MazeGenerationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
