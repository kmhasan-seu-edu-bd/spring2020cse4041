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
    GO_LEFT     (+0, -1),
    GO_RIGHT    (+0, +1),
    GO_UP       (-1, +0),
    GO_DOWN     (+1, +0);
    
    int dr;
    int dc;

    private Action(int dr, int dc) {
        this.dr = dr;
        this.dc = dc;
    }
}
