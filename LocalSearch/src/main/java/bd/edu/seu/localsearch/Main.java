package bd.edu.seu.localsearch;

public class Main {
    public Main() {
        LocalSearch localSearch = new LocalSearch();
//        localSearch.steepestAscentHillClimbing(8);
        localSearch.randomRestartHillClimbing(8);
    }

    public static void main(String args[]) {
        new Main();
    }
}
