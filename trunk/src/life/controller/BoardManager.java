/*
 * BoardManager.java
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

package life.controller;

import java.awt.Color;
import java.util.Random;
import life.gui.LifeWindow;
import life.model.*;

/**
 *
 * @author mcfedr
 */
public class BoardManager {

    LifeWindow window;
    Board board;
	Stepper stepper;

    public BoardManager() {
	}
	public void clear() {
		if(board != null) {
			board.setStepCount(0);
			Cell[][] cells = board.getCells();
			for (int i = 0; i < cells.length; i++)
				for (int j = 0; j < cells.length; j++)
					cells[i][j].setAlive(false);
			notifyView();
		}
	}

	public Board getBoard() {
		return board;
	}

	public Stepper getStepper() {
		if(stepper == null) {
			stepper = new Stepper(this);
		}
		return stepper;
	}
	public void randomise(int s) {
		if(board != null) {
			board.setStepCount(0);
			Random r = new Random();
			Cell[][] cells = new Board(s).getCells();
			for (int i = 0; i < cells.length; i++)
				for (int j = 0; j < cells.length; j++) {
					cells[i][j].setAlive(randomAlive(r));
					cells[i][j].setColor(randomColor(r));
				}
			board.setCells(cells);
			notifyView();
		}
	}

	public void setBoard(Board b) {
		board = b;
	}

	void notifyView() {
		if(window != null) window.update();
	}
	boolean randomAlive(Random r) {
		return r.nextBoolean();
	}
	Color randomColor(Random r) {
		return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), 25);
	}
	public void setView(LifeWindow w) {
		window = w;
	}
	public int getStepCount() {
		return board.getStepCount();
	}
}
