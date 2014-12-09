package com.dreamstone.core;

import java.awt.EventQueue;

import com.dreamstone.graphics.DystopiaCanvas;
import com.dreamstone.graphics.DystopiaFrame;
import com.dreamstone.util.LaunchHandler;

public class Main {
	
	private static DystopiaCanvas canvasInstance = new DystopiaCanvas();
	private static DystopiaFrame frameInstance = new DystopiaFrame(canvasInstance);
	
	public static void main(String[] args) {
		LaunchHandler.processArguments(args);
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Dystopia.gameInstance = new Dystopia();
				Dystopia.getGame().setCanvas(canvasInstance);
				Dystopia.getGame().setFrame(frameInstance);
				Dystopia.getGame().start();
			}
		});
	}
}
