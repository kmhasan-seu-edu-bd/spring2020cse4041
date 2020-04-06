package bd.edu.seu.localsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class LocalSearch {
    private final static boolean DEBUG = false;

    // steepest ascent hill climbing
    public int[] steepestAscentHillClimbing(int n) {
        // TODO note to self: FIX THIS!!!
        Random random = new Random();

        int board[] = new int[n];
        for (int c = 0; c < board.length; c++)
            board[c] = random.nextInt(n);
        int conflicts = countConflicts(board);
//        System.out.println(Arrays.toString(board) + ": " + conflicts);
//

        int bestBoard[] = new int[n];
        int minimumConflicts = conflicts;

        System.arraycopy(board, 0, bestBoard, 0, n);

        int previousConflicts = conflicts;
        for (int iteration = 0; iteration < 10; iteration++) {
//            System.out.println("-----------");
//            System.out.println("Iteration #" + iteration);
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
//                            System.out.println(Arrays.toString(board) + ": " + minimumConflicts);
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
//            System.out.println("Best board");
//            System.out.println(Arrays.toString(bestBoard) + ": " + minimumConflicts);
//            System.arraycopy(bestBoard, 0, board, 0, n);
        }

        return bestBoard;
    }

    private int[] generateRandomBoard(int n) {
        Random random = new Random();

        int board[] = new int[n];
        for (int c = 0; c < board.length; c++)
            board[c] = random.nextInt(n);

        return board;
    }

    private int[] getBestNextBoard(int board[]) {
        int currentConflicts = countConflicts(board);

        int minimumConflicts = currentConflicts;
        int bestBoard[] = new int[board.length];
        System.arraycopy(board, 0, bestBoard, 0, board.length);

        for (int c = 0; c < board.length; c++) {
            int currentRow = board[c];
            for (int r = 0; r < board.length; r++) {
                if (currentRow != r) {
                    board[c] = r;
                    int newConflicts = countConflicts(board);

                    if (newConflicts < minimumConflicts) {
                        // we found a better board
                        minimumConflicts = newConflicts;
                        System.arraycopy(board, 0, bestBoard, 0, board.length);
                    }
                }
            }
            board[c] = currentRow;
        }

        if (DEBUG) {
            System.out.println("NEXT    " + Arrays.toString(bestBoard) + ": " + countConflicts(bestBoard));
        }

        return bestBoard;
    }

    public int[] randomRestartHillClimbing(int n) {
        int board[] = generateRandomBoard(n);
        int conflicts = countConflicts(board);

        int bestBoard[] = new int[n];
        System.arraycopy(board, 0, bestBoard, 0, n);
        int minimumConflicts = conflicts;

        for (int iteration = 0; iteration < 50; iteration++) {
            if (DEBUG) {
                System.out.println("-----------");
                System.out.println("Iteration #" + iteration);
                System.out.println("CURRENT " + Arrays.toString(board) + ": " + countConflicts(board));
            }

            int currentConflicts = countConflicts(board);
            if (currentConflicts == 0)
                break;

            board = getBestNextBoard(board);
            int nextBoardConflicts = countConflicts(board);

            // TODO try out sideways moves (section 4.1 page 123 of AIMA 3e)
            if (nextBoardConflicts >= currentConflicts) {
                // random restart because we couldn't do any better
                board = generateRandomBoard(n);
                currentConflicts = countConflicts(board);
                if (DEBUG) {
                    System.out.println("RANDOM  " + Arrays.toString(board) + ": " + currentConflicts);
                }
            } else {
                if (nextBoardConflicts < minimumConflicts) {
                    minimumConflicts = nextBoardConflicts;
                    System.arraycopy(board, 0, bestBoard, 0, n);
                    if (DEBUG) {
                        System.out.println("UPDATED " + Arrays.toString(bestBoard) + ": " + countConflicts(bestBoard));
                    }
                }
            }
        }

        if (DEBUG) {
            System.out.println("BEST    " + Arrays.toString(bestBoard) + ": " + countConflicts(bestBoard));
        }
        return bestBoard;
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
