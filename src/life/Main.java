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

package life;

import life.controller.BoardManager;
import life.gui.*;
import life.model.*;
import life.states.StateManager;

/**
 * Entrance point of the program
 * Sets up the board, view and listeners
 *
 */
public class Main {
	public static void main(String[] args) {
		//Sort out the size
		int size = 30;
		if(args.length > 0) {
			size = Integer.parseInt(args[0]);
			if(size < 4) {
				size = 4;
				System.out.println("Changing to minimum size of 4");
			}
		}
		Board board = new Board(size);
		BoardManager manager = new BoardManager();
		manager.setBoard(board);
		StateManager sm = new StateManager();
		sm.setBoardManager(manager);
		LifeView view = new LifeView();
		LifeWindow window = new LifeWindow();
		window.setView(view);
		window.setBoard(board);
		manager.setWindow(window);
		sm.setWindow(window);
		sm.setStoppedState();
		window.setVisible(true);
	}
}
