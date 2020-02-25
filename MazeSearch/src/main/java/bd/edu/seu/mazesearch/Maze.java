package bd.edu.seu.mazesearch;

public class Maze {
    private int rows, cols;
    private int startRow, startCol;
    private int endRow, endCol;

    private boolean board[][];

    public Maze(int rows, int cols, int startRow, int startCol, int endRow, int endCol) {
        this.rows = rows;
        this.cols = cols;
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.board = new boolean[rows][cols];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCol() {
        return endCol;
    }

    public boolean[][] getBoard() {
        return board;
    }

    public void setCell(int row, int col, boolean free) {
        board[row][col] = free;
    }

    public boolean isFree(int row, int col) {
        return board[row][col];
    }

    @Override
    public String toString() {
        return rows +
                " " + cols +
                " " + startRow +
                " " + startCol +
                " " + endRow +
                " " + endCol;
    }

    public String getBoardAsString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == startRow && c == startCol)
                    stringBuffer.append('S');
                else if (r == endRow && c == endCol)
                    stringBuffer.append('D');
                else {
                    if (board[r][c])
                        stringBuffer.append('.');
                    else stringBuffer.append('#');
                }
            }
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }
}
