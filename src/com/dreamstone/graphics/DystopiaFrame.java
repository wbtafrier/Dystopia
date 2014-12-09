package com.dreamstone.graphics;

import java.awt.Dimension;
import javax.swing.JFrame;

public final class DystopiaFrame extends JFrame {

	private static final long serialVersionUID = 5615077115127764900L;
	private static Dimension minimumSize = new Dimension((int)DisplayManager.getScreenSize().getWidth() / 4, (int)DisplayManager.getScreenSize().getHeight() / 4);
	
	
	public DystopiaFrame(DystopiaCanvas DystopiaInstance) {
		
		this.setTitle("Dystopia");
		this.add(DystopiaInstance);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH | this.getExtendedState());
		this.setResizable(true);
		this.setMinimumSize(minimumSize);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}