/*
 * EventDispatch.java
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

package life.states.events;

import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import life.states.StateManager;

/**
 *
 * @author mcfedr
 */
public abstract class EventState {

	StateManager sm;

	public void clear() {
	}
	public void mouseClicked(int x, int y) {
	}
	public void open() {
	}
	public void randomise() {
	}
	public void save() {
	}
	public void exit() {
		if(JOptionPane.showConfirmDialog(sm.getWindow(), "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public void run() {
	}
	public void stop() {
	}
	public void step() {
	}
	public void speed(int s) {
		sm.getTimer().setDelay(s);
	}
	public void timer() {
	}
}
