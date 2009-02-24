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

/**
 *
 * @author mcfedr
 */
public abstract class EventState {

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void open() {
		
	}
	public void save() {
	
	}
	public void exit() {
		
	}
	public void gridToggle() {
		
	}
	boolean running = false;
	public void runToggle() {
		if(running) {
			running = false;
			stop();
		}
		else {
			running = true;
			run();
		}
	}
	public void run() {

	}
	public void stop() {

	}
	public void step() {

	}
	public void speed(int s) {
		
	}
}
