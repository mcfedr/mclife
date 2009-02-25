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

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import life.states.events.EventState;
import life.model.*;

/**
 * Concerned with displaying the board
 * uses a BufferedImage as a buffer for its drawing functions
 * has functions to update view of one cell, or all cells
 * also maintains the GridManager and Highlighter
 *
 */
public class LifeView extends JPanel {
	
	Color background = Color.WHITE;
	Color highlight = Color.BLACK;
	Color gridC = Color.LIGHT_GRAY;
	
	Dimension cellSize;
	
	BufferedImage image;
	Graphics g;
	
	Board board;
	
	GridManager grid;
	Highlighter highlighter;

	EventState dispatch;

	public LifeView() {
		grid = new GridManager(this);
		highlighter = new Highlighter(this);

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(board != null) {
					int i = e.getX() / cellSize.width;
					int j = e.getY() / cellSize.height;

					if (i < board.getSize() && j < board.getSize())
						highlighter.highlight(i, j);
					else
						highlighter.clearHighlight();
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(board != null) {
					int i = e.getX() / cellSize.width;
					int j = e.getY() / cellSize.height;

					if (i < board.getSize() && j < board.getSize()) {
						if(dispatch != null) dispatch.mouseClicked(i, j);
					}
				}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				highlighter.clearHighlight();
			}
		});


	}
	public void updateSize() {
		if(board != null) {
			int size = board.getSize();
			Dimension sz = getSize();
			cellSize = new Dimension(sz.width / size, sz.height / size);
			if(cellSize.width < 2) {
				cellSize.width = 2;
				sz.width = size*2;
			}
			if(cellSize.height < 2) {
				cellSize.height = 2;
				sz.height = size*2;
			}
			image = new BufferedImage(sz.width, sz.height, BufferedImage.TYPE_INT_ARGB);
			g = image.getGraphics();
			g.setColor(background);
			g.fillRect(0, 0, sz.width, sz.height);
			grid.drawGrid();
			updateAll();
		}
	}
	public void update(int i, int j) {
		if(board != null) {
			paintCell(board.getCell(i, j), i, j);
			repaint();
		}
	}
	public void updateAll() {
		if(board != null) {
			Cell[][] cells = board.getCells();
			for(int i  = 0;i<cells.length;i++) {
				for(int j = 0;j<cells[i].length;j++) {
					paintCell(cells[i][j], i, j);
				}
			}
			repaint();
		}
	}

	public GridManager getGridManager() {
		return grid;
	}
	void paintCell(Cell c, int i, int j) {
		g.setColor(background);
		g.fillRect(i * cellSize.width + 1, j * cellSize.height + 1, cellSize.width - 1, cellSize.height - 1);
		if(c.isAlive()) {
            g.setColor(c.getColor());
			g.fillRect(i * cellSize.width + 1, j * cellSize.height + 1, cellSize.width - 1, cellSize.height - 1);
        }
	}
    @Override
	public void paint(Graphics g) {
		if(board != null) g.drawImage(image, 0, 0, null);
	}

	void setBoard(Board b) {
		board = b;
		updateSize();
	}

	void setDispatch(EventState d) {
		dispatch = d;
	}
}
