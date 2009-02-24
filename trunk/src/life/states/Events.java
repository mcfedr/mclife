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

package life.states;

import javax.swing.Timer;

import life.controller.BoardManager;
import life.controller.Stepper;
import life.model.*;
import life.gui.*;

/**
 * Co-ordinates events
 * keeps current state and references to the view and model
 * changes the state of the program
 * has the timer that generates timer events
 *
 */
public class Events {
	
	State cState;
	StoppedState stopped;
	RunningState running;

	public BoardManager manager;
	public Stepper stepper;

	public Board board;
	public LifeFrame frame;
	public Highlighter highlighter;
	
	Timer timer;
	
	public Events(BoardManager m, LifeFrame f) {
		manager = m;
		frame = f;
		board = manager.getBoard();
		highlighter = frame.getView().getHighlighter();
		stepper = manager.getStepper();
		stopped = new StoppedState(this);
		running = new RunningState(this);
		timer = new Timer(1000 / frame.getSlider().getValue(), running);
		setStopped();
	}
	public void setStopped() {
		cState = stopped;
		timer.stop();
		frame.getClear().setEnabled(true);
		frame.getStep().setEnabled(true);
		frame.getRun().setText("Run");
		frame.getLoad().setEnabled(true);
		frame.getSave().setEnabled(true);
		frame.getRandom().setEnabled(true);
	}
	public void setRunning() {
		cState = running;
		timer.start();
		frame.getClear().setEnabled(false);
		frame.getStep().setEnabled(false);
		frame.getRun().setText("Pause");
		frame.getLoad().setEnabled(false);
		frame.getSave().setEnabled(false);
		frame.getRandom().setEnabled(false);
		highlighter.clearHighlight();
	}
	public State getCurrentState() {
		return cState;
	}
}
