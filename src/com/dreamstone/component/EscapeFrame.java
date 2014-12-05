package com.dreamstone.component;

import javax.swing.JFrame;

public final class EscapeFrame extends JFrame {

	private static final long serialVersionUID = 5615077115127764900L;
	
	public EscapeFrame(EscapeComponent escapeInstance) {
		
		this.setTitle("Escape");
		this.setIgnoreRepaint(true);
		this.add(escapeInstance);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}