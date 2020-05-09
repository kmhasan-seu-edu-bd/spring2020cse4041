package bd.edu.seu.localsearch;

import java.util.Arrays;

public class Main {
    public Main() {
        LocalSearch localSearch = new LocalSearch();
//        localSearch.steepestAscentHillClimbing(8);
        int n = 8;
        int success = 0;
        int totalTrials = 100;

        long start = System.currentTimeMillis();
        for (int i = 0; i < totalTrials; i++) {
            int board[] = localSearch.simulatedAnnealing(n);
//            int board[] = localSearch.randomRestartHillClimbing(n);
            int conflicts = localSearch.countConflicts(board);
            if (conflicts == 0)
                success++;
        }
        long stop = System.currentTimeMillis();
        System.out.printf("Time taken: %.2f seconds\n", (stop - start) / 1000.0);

        System.out.printf("%d-Queen Success %d/%d (%.2f%%)\n",
                n,
                success,
                totalTrials,
                100.0 * success / totalTrials);

//        System.out.println("Solution: " + Arrays.toString(board));
//        System.out.println("Conflicts: " + conflicts);
    }

    public static void main(String args[]) {
        new Main();
    }
}
