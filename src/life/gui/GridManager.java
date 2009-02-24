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
	boolean enabled;
	
	public GridManager(LifeView l) {
		life = l;
		setEnabled(true);
	}
	public void drawGrid() {
		if(enabled)
			drawGrid(life.gridC);
		else
			drawGrid(life.background);
	}
	public void drawGrid(Color c) {
		if(life.board != null && life.g != null) {
			int size = life.board.getSize();
			for (int i = 0; i < size; i++)
				for(int j = 0;j < size; j++)
					drawGrid(i, j, c);
		}
	}
	public void drawGrid(int i, int j) {
		if(enabled)
			drawGrid(i, j, life.gridC);
		else
			drawGrid(i, j, life.background);
	}
	public void drawGrid(int i, int j, Color c) {
		if(life.g != null) {
			life.g.setColor(c);
			life.g.drawRect(i * life.cellSize.width, j * life.cellSize.height, life.cellSize.width, life.cellSize.height);
		}
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
