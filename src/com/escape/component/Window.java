package com.escape.component;

import javax.swing.JFrame;

import com.escape.launch.Escape;

public final class Window {
	public Window(Escape game) {
		JFrame f = new JFrame("Escape");
		f.add(game);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
