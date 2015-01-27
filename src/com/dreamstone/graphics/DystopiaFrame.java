package com.dreamstone.graphics;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.world.data.SaveManager;

public final class DystopiaFrame extends JFrame {

	private static final long serialVersionUID = 5615077115127764900L;
	private static Dimension minimumSize = new Dimension((int)DisplayCarrier.getDisplaySize().getWidth() / 4, (int)DisplayCarrier.getDisplaySize().getHeight() / 4);
	
	
	public DystopiaFrame(DystopiaCanvas dystopiaInstance) {
		
		this.setTitle("Dystopia");
		this.add(dystopiaInstance);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH | this.getExtendedState());
		this.setResizable(true);
		this.setMinimumSize(minimumSize);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				SaveManager.saveWorld();
			}
		});
		this.setVisible(true);
	}
}