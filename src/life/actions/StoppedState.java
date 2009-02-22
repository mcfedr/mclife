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

package life.actions;

import life.model.*;
import life.model.Board.Cell;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.JOptionPane;

/**
 * Deals with actions in the stopped state
 * clear
 * step
 * run
 * load
 * save
 * and the mouse events, highlighting and setting of cells
 *
 */
public class StoppedState extends State {
	
	public StoppedState(Events e) {
		super(e);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(events.frame.getClear()))
			events.board.clear();
		else if(e.getSource().equals(events.frame.getStep())) {
			events.board.step();
			events.frame.getCount().setText("" + events.board.getStepCount());
		}
		else if(e.getSource().equals(events.frame.getRun()))
			events.setRunning();
		else if(e.getSource().equals(events.frame.getLoad()))
			BoardFile.load(events);
		else if(e.getSource().equals(events.frame.getSave()))
			BoardFile.save(events);
		else if(e.getSource().equals(events.frame.getRandom()))
			try {
				events.board.randomise(Integer.parseInt(JOptionPane.showInputDialog(events.frame, "Give the size of the new board:", events.board.size())));
			}
			catch(NumberFormatException nfe) {
				
			}
		else
			super.actionPerformed(e);
	}
	public void mouseClicked(MouseEvent e) {
		
		Dimension sz = events.frame.getView().getCellSize();
		int i = e.getX() / sz.width;
		int j = e.getY() / sz.height;
		
		if(i < events.board.size() && j < events.board.size())
			switch(events.board.get(i, j)) {
				case DEAD:
					events.board.set(i, j, Cell.RED);
					break;
				case RED:
					events.board.set(i, j, Cell.GREEN);
					break;
				case GREEN:
					events.board.set(i, j, Cell.DEAD);
					break;
			}
	}
	public void mouseMoved(MouseEvent e) {
		
		Dimension sz = events.frame.getView().getCellSize();
		int i = e.getX() / sz.width;
		int j = e.getY() / sz.height;
	
		if(i < events.board.size() && j < events.board.size())
			events.highlighter.highlight(i, j);
	}
	public void mouseExited(MouseEvent e) {
		events.highlighter.clearHighlight();
	}
}
