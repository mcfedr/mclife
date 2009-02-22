/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package life.model;

import java.awt.Color;

/**
 *
 * @author mcfedr
 */
public class Cell {

    public Cell(boolean a, Color c) {
        alive = a;
        color = c;
    }
    public boolean alive;
    public Color color;
}
