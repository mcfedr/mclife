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
 * Deals with highlighting a cell
 * saves the currently highlighted cell
 *
 */
public class Highlighter {
	
	LifeView view;
	
	Color color;
	
	int hI, hJ;
	
	public Highlighter(LifeView l, Color c) {
		view = l;
		color = c;
	}
	public void highlight(int i, int j) {
		clearHighlight();
		hI = i;
		hJ = j;
		view.grid.drawGrid(hI, hJ, color);
		view.repaint();
	}
	public void clearHighlight() {
		if(hI > -1  && hJ > -1) {
			view.grid.drawGrid(hI, hJ);
			hI = -1;
			hJ = -1;
		}
		view.repaint();
	}
}
