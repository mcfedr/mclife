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
import life.states.*;

import java.io.*;
import javax.swing.*;

/**
 * Deals with loading and saving boards
 * uses a simple text format, with 0 for dead, r for red and g for green
 * invalid files will not load and will display an error to the user
 *
 */
public class BoardFile {
	public static void load(Events e) {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(e.frame) == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(fc.getSelectedFile()));
				String x = in.readLine();
				int size = x.length();
				Cell[][] cells = new Cell[size][size];
				int i = 0;
				do {
					for (int j = 0; j < size; j++) {
						cells[i][j] = toCell(x.charAt(j));
					}
					i++;
					x = in.readLine();
				}
				while (i < size);
				e.board.setCells(cells);
				e.frame.getView().updateSize();
				e.frame.getView().updateAll();
			}
			catch (IOException ioe) {
				JOptionPane.showMessageDialog(e.frame, "Error loading file, please check it is a valid Life save", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public static void save(Events e) {
		JFileChooser fc = new JFileChooser();
		if(fc.showSaveDialog(e.frame) == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(fc.getSelectedFile()));
				Cell[][] cells = e.board.getCells();
				for(Cell[] cs : cells) {
					for(Cell c : cs)
						out.append(toChar(c));
					out.newLine();
				}
				out.close();
			}
			catch(IOException ioe) {
				JOptionPane.showMessageDialog(e.frame, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	static char toChar(Cell c) {
        return c.isAlive()?'x':' ';
		/*switch(c) {
			case RED:
				return 'r';
			case GREEN:
				return 'g';
			default:
				return '0';
		}*/
	}
	static Cell toCell(char c) {
        switch(c) {
            case 'x':
               return new Cell(true, Color.RED);
            default:
                return new Cell(false, Color.BLACK);
        } 
        /*
		switch(c) {
			case 'r':
				return Cell.RED;
			case 'g':
				return Cell.GREEN;
		 	default:
		 		return Cell.DEAD;
		 }*/
	 }
}
