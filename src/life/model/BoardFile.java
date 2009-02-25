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

package life.model;

import java.awt.Color;

import java.io.*;
import javax.swing.*;
import life.controller.BoardManager;
import life.gui.LifeWindow;
import life.states.StateManager;

/**
 * Deals with loading and saving boards
 * uses a simple text format, with 0 for dead, r for red and g for green
 * invalid files will not load and will display an error to the user
 *
 */
public class BoardFile {

	static JFileChooser fc;

	public static void load(StateManager sm) {
		if(fc == null) fc = new JFileChooser();
		if (fc.showOpenDialog(sm.getWindow()) == JFileChooser.APPROVE_OPTION) {
			try {
				DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fc.getSelectedFile())));
				int size = in.readInt();
				Board b = new Board(size);
				Cell[][] cells = b.getCells();
				for(int i = 0;i < size;i++) {
					for(int j = 0;j < size;j++) {
						cells[i][j].setAlive(in.readBoolean());
						cells[i][j].setColor(new Color(in.readInt()));
					}
				}
				in.close();
				if(sm.getBoardManager() != null) sm.getBoardManager().setBoard(b);
				if(sm.getWindow() != null) sm.getWindow().setBoard(b);
			}
			catch (IOException ioe) {
				JOptionPane.showMessageDialog(sm.getWindow(), "Error loading file, please check it is a valid Life save", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public static void save(StateManager sm) {
		if(sm.getBoardManager() != null && sm.getBoardManager().getBoard() != null) {
			if(fc == null) fc = new JFileChooser();
			if(fc.showSaveDialog(sm.getWindow()) == JFileChooser.APPROVE_OPTION) {
				try {
					DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fc.getSelectedFile())));
					Cell[][] cells = sm.getBoardManager().getBoard().getCells();
					out.writeInt(cells.length);
					for(Cell[] cs : cells) {
						for(Cell c : cs) {
							out.writeBoolean(c.isAlive());
							out.writeInt(c.getColor().getRGB());
						}
					}
					out.close();
				}
				catch(IOException ioe) {
					JOptionPane.showMessageDialog(sm.getWindow(), "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
