package com.dreamstone.core;

import java.awt.EventQueue;
import javax.swing.JFrame;
import com.dreamstone.component.EscapeCanvas;
import com.dreamstone.component.EscapeFrame;
import com.dreamstone.util.LaunchHandler;

public class Main {
	public static Game game;
	private static EscapeCanvas escapeCanvas;
	private static JFrame escapeFrame;
	
	public static void main(String[] args) {
		LaunchHandler.processArguments(args);
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				escapeCanvas = new EscapeCanvas();
				escapeFrame = new EscapeFrame(escapeCanvas);
				game = new Game(escapeFrame, escapeCanvas);
				game.start();
			}
		});
	}
}
