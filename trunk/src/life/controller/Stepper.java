/*
 * Stepper.java
 * 
 * Copyright (C) 2009 Fred Nicollson, All Rights Reserved.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * It is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package life.controller;

import java.awt.Color;
import life.model.*;

/**
 *
 * @author mcfedr
 */
public class Stepper {

	BoardManager manager;

    public Stepper(BoardManager m) {
		manager = m;
    }
    
    public void step() {
		if(manager.getBoard() != null) {
			Cell[][] cells = manager.getBoard().getCells();
			Cell[][] nCells = new Cell[cells.length][cells.length];

			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells.length; j++) {
					nCells[i][j] = new Cell(cells[i][j].isAlive(), cells[i][j].getRGB());
					switch(countNieghbours(i, j)) {
						case 2://change nothing
							nCells[i][j].setRGB(brighten(nCells[i][j].getRGB()));
							break;
						case 3://bring to life
							nCells[i][j].setAlive(true);
							nCells[i][j].setColor(averageColor(i, j));
							break;
						default://kill
							nCells[i][j].setAlive(false);
					}
				}

			}

			manager.getBoard().setCells(nCells);
			manager.getBoard().incStep();
			manager.notifyView();
		}
	}

    int countNieghbours(int x, int y) {
        Cell[][] cells = manager.getBoard().getCells();
		int c = 0;
		for(int i = x-1;i<=x+1;i++)
			for(int j = y-1;j<=y+1;j++) {
				int p = wrap(i);
				int q = wrap(j);
				if(!(p == x && q == y) && //so as not to count this cell
						cells[p][q].isAlive()) //cell not null
					c++;
			}
		return c;
	}
	Color averageColor(int x, int y) {
        Cell[][] cells = manager.getBoard().getCells();
		int a = 0, r = 0, g = 0, b = 0, c = 0;
		for(int i = x-1;i<=x+1;i++)
			for(int j = y-1;j<=y+1;j++) {
				int p = wrap(i);
				int q = wrap(j);
				if(!(p == x && q == y) && //so as not to count this cell
						cells[p][q].isAlive()) {//cell alive
					int pixel = cells[p][q].getRGB();
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
        int size = manager.getBoard().getSize();
		if(i >= size)
			return i-size;
		if(i < 0)
			return size+i;
		else
			return i;
	}

	int brighten(int pixel) {
		int a = (pixel >> 24) & 0xff;
		int r = (pixel >> 16) & 0xff;
		int g = (pixel >> 8) & 0xff;
		int b = (pixel) & 0xff;

		a = a > 230 ? 255 : a + 1;

		return (a << 24) | (r << 16) | (g << 8) | b;
	}
}
