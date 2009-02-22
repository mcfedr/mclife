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

/**
 * Passes on action events to the current state
 *
 */
public class ActionEvents implements ActionListener {

	Events events;
	
	public ActionEvents(Events e) {
		events = e;
	}
	public void actionPerformed(ActionEvent e) {
		events.getCurrentState().actionPerformed(e);
	}

}
