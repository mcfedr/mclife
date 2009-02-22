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

package life.gui;

import java.awt.Color;

/**
 * Deals with drawing the grid on screen
 * can be enabled/disabled
 *
 */
public class GridManager {

	LifeView life;
	
	Color color;
	
	boolean enabled;
	
	public GridManager(LifeView l, Color c) {
		life = l;
		color = c;
		enabled = true;
	}
	public void drawGrid() {
		if(enabled)
			drawGrid(color);
		else
			drawGrid(life.background);
	}
	public void drawGrid(Color c) {
		for (int i = 0; i < life.size; i++)
			for(int j = 0;j < life.size; j++) 
				drawGrid(i, j, c);
	}
	public void drawGrid(int i, int j) {
		if(enabled)
			drawGrid(i, j, color);
		else
			drawGrid(i, j, life.background);
	}
	public void drawGrid(int i, int j, Color c) {
		life.g.setColor(c);
		life.g.drawRect(i * life.cellSize.width, j * life.cellSize.height, life.cellSize.width, life.cellSize.height);
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean e) {
		if(e != enabled) {
			enabled = e;
			drawGrid();
			life.repaint();
		}
	}
}
