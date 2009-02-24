/*
 * StoppedEvents.java
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

import javax.swing.JOptionPane;
import life.states.StateManager;

/**
 *
 * @author mcfedr
 */
public class StoppedEventState extends EventState {
	public StoppedEventState(StateManager s) {
		sm = s;
	}
	@Override
	public void run() {
		sm.setRunningState();
	}
	@Override
	public void clear() {
		sm.getBoardManager().clear();
	}
	@Override
	public void open() {
		
	}
	@Override
	public void randomise() {
		try {
				sm.getBoardManager().randomise(Integer.parseInt(JOptionPane.showInputDialog(sm.getWindow(), "Give the size of the new board:", sm.getBoardManager().getBoard().getSize())));
			}
			catch(NumberFormatException nfe) {

			}
	}
}
