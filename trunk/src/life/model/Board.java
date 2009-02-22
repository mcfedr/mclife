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
	int size;
	
	LifeView view;
	
	int stepCount;
	
	public Board(int size) {
		this.size = size;
		cells = new Cell[size][size];
		init();
	}
	public void set(int i, int j, Cell c) {
		cells[i][j] = c;
		if(view != null)
			view.update(i, j);
	}
	public Cell get(int i, int j) {
		return cells[i][j];
	}
	public Cell[][] getAll() {
		return cells;
	}
    public void init() {
        init(cells);
    }
    public void init(Cell[][] cells) {
        stepCount = 0;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
                cells[i][j] = new Cell(false, Color.RED);
		if(view != null)
			view.updateAll();
    }
	public void clear() {
		stepCount = 0;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				cells[i][j].alive = false;
		if(view != null)
			view.updateAll();
	}
	public void randomise(int s) {
		stepCount = 0;
		if(s != size) {
			cells = new Cell[s][s];
			size = s;
		}
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				cells[i][j].alive = randomAlive();
				cells[i][j].color = randomColor();
			}
		if(view != null)
			view.updateSize();
	}
	boolean randomAlive() {
		return (Math.random() > 0.8);
	}
	Color randomColor() {
		int i = (int)(Math.random()*3);
		if(i < 1)
			return Color.RED;
		if(i < 2)
			return Color.GREEN;
		return Color.BLUE;
	}
	public int size() {
		return size;
	}
	public int countNieghbours(int x, int y) {
		int c = 0;
		for(int i = x-1;i<=x+1;i++)
			for(int j = y-1;j<=y+1;j++) {
				int p = wrap(i);
				int q = wrap(j);
				if(!(p == x && q == y) && //so as not to count this cell
						cells[p][q].alive) //cell not null
					c++;
			}
		return c;
	}
	Color averageColor(int x, int y) {
		int a = 0, r = 0, g = 0, b = 0, c = 0;
		for(int i = x-1;i<=x+1;i++)
			for(int j = y-1;j<=y+1;j++) {
				int p = wrap(i);
				int q = wrap(j);
				if(!(p == x && q == y) && //so as not to count this cell
						cells[p][q].alive) {//cell alive
					int pixel = cells[p][q].color.getRGB();
					a += (pixel >> 24) & 0xff;
			        r += (pixel >> 16) & 0xff;
			        g += (pixel >>  8) & 0xff;
			        b += (pixel      ) & 0xff;
			        c++;
				}
			}
		a /= c;
		r /= c;
		g /= c;
		b /= c;
		
		return new Color(r, g, b, a);
	}
	int wrap(int i) {
		if(i >= size)
			return i-size;
		if(i < 0)
			return size+i;
		else
			return i;
	}
	public void step() {
		
		Cell[][] nCells = new Cell[size][size];
        init(nCells);
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				switch(countNieghbours(i, j)) {
					case 2:
						nCells[i][j] = cells[i][j];
						break;
					case 3:
						if(!cells[i][j].alive)
							nCells[i][j].alive = true;
						nCells[i][j].color = averageColor(i, j);
						break;
					default:
						nCells[i][j].alive = false;
				}
			}
			
		}
		
		cells = nCells;
		stepCount++;
		
		if(view != null)
			view.updateAll();
	}
	public void setView(LifeView v) {
		view = v;
	}
	public int getStepCount() {
		return stepCount;
	}
}
