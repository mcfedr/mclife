/*
Copyright (c) 2009 Fred Nicollson, All Rights Reserved.

This file is part of mclife.

mclife is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

mclife is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with mclife.  If not, see <http://www.gnu.org/licenses/>.
*/

package life.model;

import java.awt.Color;

import life.gui.LifeView;

/**
 * Contains all the information about the board
 * updates the view when changes are made
 * has functions to step onward
 *
 */
public class Board {
	
	Cell[][] cells;
	int stepCount;

    public Board(int s) {
        stepCount = 0;

        cells = new Cell[s][s];
        for (int i = 0; i < s; i++)
			for (int j = 0; j < s; j++)
                cells[i][j] = new Cell(false, Color.BLACK);
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] c) {
        cells = c;
    }

    public int getSize() {
        return cells.length;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int s) {
        stepCount = s;
    }

    public void step() {
        stepCount++;
    }

}
