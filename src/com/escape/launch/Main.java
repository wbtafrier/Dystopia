package com.escape.launch;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.escape.component.Window;

public class Main {
	private static String[] arguments;
	public static Escape escapeInstance;
	public static JFrame windowInstance;
	
	public static void main(String[] args) {
		arguments = args;
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				escapeInstance = new Escape(arguments);
				windowInstance = new Window(escapeInstance);
				escapeInstance.start();
			}
		});
	}
}
