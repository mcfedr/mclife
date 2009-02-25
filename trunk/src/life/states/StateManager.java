/*
 * StateManager.java
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

package life.states;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import life.controller.BoardManager;
import life.gui.LifeWindow;
import life.states.events.*;

import javax.swing.Timer;
/**
 *
 * @author mcfedr
 */
public class StateManager {
	EventState running;
	EventState stopped;
	EventState currentState;

	LifeWindow window;

	BoardManager manager;

	Timer timer;

	public StateManager() {
		running = new RunningEventState(this);
		stopped = new StoppedEventState(this);

		currentState = stopped;
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getCurrentEventState().timer();
			}
		});
	}

	public BoardManager getBoardManager() {
		return manager;
	}

	public Timer getTimer() {
		return timer;
	}

	public LifeWindow getWindow() {
		return window;
	}

	public void setBoardManager(BoardManager b) {
		manager = b;
	}

	public void setWindow(LifeWindow w) {
		window = w;
	}

	public void setStoppedState() {
		currentState = stopped;
		timer.stop();
		if(window != null) {
			window.setDispatch(currentState);
			window.setStoppedState();
		}
	}
	public void setRunningState() {
		currentState = running;
		if(window != null) {
			window.setDispatch(currentState);
			window.setRunningState();
			timer.setDelay(window.getSpeed());
		}
		timer.start();
	}
	public EventState getCurrentEventState() {
		return currentState;
	}
}
