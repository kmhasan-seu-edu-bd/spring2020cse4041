package bd.edu.seu.localsearch;

import java.util.Arrays;
import java.util.Random;

public class LocalSearch {
    // steepest ascent hill climbing
    public void steepestAscentHillClimbing(int n) {
        Random random = new Random();

        int board[] = new int[n];
        for (int c = 0; c < board.length; c++)
            board[c] = random.nextInt(n);
        int conflicts = countConflicts(board);
        System.out.println(Arrays.toString(board) + ": " + conflicts);


        int bestBoard[] = new int[n];
        int minimumConflicts = conflicts;

        System.arraycopy(board, 0, bestBoard, 0, n);

        int previousConflicts = conflicts;
        for (int iteration = 0; iteration < 10; iteration++) {
            System.out.println("-----------");
            System.out.println("Iteration #" + iteration);
            for (int c = 0; c < board.length; c++) {
                int currentRow = board[c];
                for (int r = 0; r < board.length; r++) {
                    if (currentRow != r) {
                        board[c] = r;
                        int newConflicts = countConflicts(board);

                        if (newConflicts < minimumConflicts) {
                            // we found a better board
                            minimumConflicts = newConflicts;
                            System.arraycopy(board, 0, bestBoard, 0, n);
                            System.out.println(Arrays.toString(board) + ": " + minimumConflicts);
                        }
                    }
                }
                board[c] = currentRow;
            }

            if (minimumConflicts == previousConflicts) {
                break;
            }

            previousConflicts = minimumConflicts;
            // printing out the current board
            System.out.println("Best board");
            System.out.println(Arrays.toString(bestBoard) + ": " + minimumConflicts);
            System.arraycopy(bestBoard, 0, board, 0, n);
        }

    }

    public void randomRestartHillClimbing(int n) {
        Random random = new Random();

        int board[] = new int[n];
        for (int c = 0; c < board.length; c++)
            board[c] = random.nextInt(n);
        int conflicts = countConflicts(board);
        System.out.println(Arrays.toString(board) + ": " + conflicts);


        int bestBoard[] = new int[n];
        int minimumConflicts = conflicts;

        System.arraycopy(board, 0, bestBoard, 0, n);

        int previousConflicts = conflicts;
        for (int iteration = 0; iteration < 10; iteration++) {
            System.out.println("-----------");
            System.out.println("Iteration #" + iteration);
            for (int c = 0; c < board.length; c++) {
                int currentRow = board[c];
                for (int r = 0; r < board.length; r++) {
                    if (currentRow != r) {
                        board[c] = r;
                        int newConflicts = countConflicts(board);

                        if (newConflicts < minimumConflicts) {
                            // we found a better board
                            minimumConflicts = newConflicts;
                            System.arraycopy(board, 0, bestBoard, 0, n);
                            System.out.println(Arrays.toString(board) + ": " + minimumConflicts);
                        }
                    }
                }
                board[c] = currentRow;
            }

            if (minimumConflicts == previousConflicts) {
                for (int c = 0; c < board.length; c++)
                    board[c] = random.nextInt(n);
                // TODO fix this code -- maybe some issue with remembering the best conflict values
            } else {
                previousConflicts = minimumConflicts;
                // printing out the current board
                System.out.println("Best board");
                System.out.println(Arrays.toString(bestBoard) + ": " + minimumConflicts);
                System.arraycopy(bestBoard, 0, board, 0, n);
            }
        }

    }

    public int countConflicts(int[] board) {
        int conflicts = 0;
        // https://towardsdatascience.com/computing-number-of-conflicting-pairs-in-a-n-queen-board-in-linear-time-and-space-complexity-e9554c0e0645
        for (int i = 0; i < board.length; i++)
            for (int j = i + 1; j < board.length; j++) {
                // if two queens are in the same row
                if (board[i] == board[j])
                    conflicts++;
                else {
                    int dx = Math.abs(i - j);
                    int dy = Math.abs(board[i] - board[j]);
                    if (dx == dy)
                        conflicts++;
                }
            }

        return conflicts;
    }
}
