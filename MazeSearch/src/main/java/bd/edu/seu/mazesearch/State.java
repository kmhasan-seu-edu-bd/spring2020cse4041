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
public class State {
    int row;
    int col;

    public State(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
        State that = (State) obj;
        return this.row == that.row 
                && this.col == that.col;
    }

    @Override
    public int hashCode() {
        return row * 900 + col;
    }
}
