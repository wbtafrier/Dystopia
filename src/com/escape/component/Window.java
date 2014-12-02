package com.escape.component;

import javax.swing.JFrame;

import com.escape.launch.Escape;

public final class Window extends JFrame {

	private static final long serialVersionUID = 5615077115127764900L;
	public Window(Escape game) {
		this.setTitle("Escape");
		this.add(game);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}