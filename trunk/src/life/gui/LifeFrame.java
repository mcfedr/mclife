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

package life.gui;

import life.model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * The main JFrame of the application
 * contains all the controls
 * sets them up and makes itself visible
 * contains functions to add listeners to its components
 *
 */
public class LifeFrame extends JFrame {
	
	JSlider slider;
	LifeView lifep;
	JToggleButton grid;
	JButton clear;
	JButton step;
	JButton run;
	JButton stop;
	JButton save;
	JButton load;
	JButton random;
	JLabel count;
	
	public LifeFrame(Board b) {
		super("Life");
		
		slider = new JSlider(0, 50, 1);
		slider.setOrientation(JSlider.VERTICAL);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		
		lifep = new LifeView(b);
		
		grid = new JToggleButton("Border");
		grid.setSelected(true);
		clear = new JButton("Clear");
		step = new JButton("Step");
		run = new JButton("Run");
		stop = new JButton("Stop");
		save = new JButton("Save");
		load = new JButton("Load");
		random = new JButton("Randomise");
		count = new JLabel("0");
		
		
		getRootPane().setLayout(new BorderLayout());
		getRootPane().add(lifep, BorderLayout.CENTER);
		getRootPane().add(slider, BorderLayout.EAST);
		
		JPanel p = new JPanel();
		p.add(clear);
		p.add(step);
		p.add(run);
		p.add(stop);
		p.add(grid);
		p.add(load);
		p.add(save);
		p.add(random);
		p.add(count);
		
		getRootPane().add(p, BorderLayout.SOUTH);
		setSize(700, 580);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		lifep.updateSize();
	}
	public void addActionListener(ActionListener l) {
		grid.addActionListener(l);
		clear.addActionListener(l);
		step.addActionListener(l);
		run.addActionListener(l);
		stop.addActionListener(l);
		save.addActionListener(l);
		load.addActionListener(l);
		random.addActionListener(l);
	}
	public void addChangeListener(ChangeListener l) {
		slider.addChangeListener(l);
	}
	public LifeView getView() {
		return lifep;
	}
	public JSlider getSlider() {
		return slider;
	}
	public LifeView getLifep() {
		return lifep;
	}
	public JToggleButton getGrid() {
		return grid;
	}
	public JButton getClear() {
		return clear;
	}
	public JButton getStep() {
		return step;
	}
	public JButton getRun() {
		return run;
	}
	public JButton getStop() {
		return stop;
	}
	public JLabel getCount() {
		return count;
	}
	public JButton getSave() {
		return save;
	}
	public JButton getLoad() {
		return load;
	}
	public JButton getRandom() {
		return random;
	}
}
