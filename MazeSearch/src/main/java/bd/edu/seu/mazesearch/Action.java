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
public enum Action {
    GO_LEFT     (+0, -1, "L"),
    GO_RIGHT    (+0, +1, "R"),
    GO_UP       (-1, +0, "U"),
    GO_DOWN     (+1, +0, "D");
    
    int dr;
    int dc;
    String shortAction;
    
    private Action(int dr, int dc, String shortAction) {
        this.dr = dr;
        this.dc = dc;
        this.shortAction = shortAction;
    }

    @Override
    public String toString() {
        return shortAction;
    }
    
    
}
