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

import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.event.*;

/**
 * Abstract State class
 * Contains the default actions that happen regardless of current state
 * the exit button
 * border toggle
 * resizing
 * 
 */
public abstract class State implements ActionListener {
	
	Events events;
	
	public State(Events e) {
		events = e;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(events.frame.getStop())) {
			events.setStopped();
			if(JOptionPane.showConfirmDialog(events.frame, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}
		else if(e.getSource().equals(events.frame.getGrid())) {
			events.frame.getView().getGrid().setEnabled(events.frame.getGrid().isSelected());
		}
	}
	public void stateChanged(ChangeEvent e) {
		if(e.getSource().equals(events.frame.getSlider())) {
			events.timer.setDelay(2000 / (events.frame.getSlider().getValue() > 0?events.frame.getSlider().getValue():1));
		}
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void componentResized(ComponentEvent e) {
		if(e.getSource().equals(events.frame.getView()))
			events.frame.getView().updateSize();
	}
}
