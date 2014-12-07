package com.dreamstone.core;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.dreamstone.component.EscapeCanvas;
import com.dreamstone.component.EscapeFrame;
import com.dreamstone.util.LaunchHandler;

public class Main {
	public static EscapeCanvas escapeInstance;
	public static JFrame windowInstance;
	
	public static void main(String[] args) {
		LaunchHandler.processArguments(args);
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				escapeInstance = new EscapeCanvas();
				windowInstance = new EscapeFrame(escapeInstance);
				escapeInstance.start();
			}
		});
	}
}
