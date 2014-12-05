package com.dreamstone.launch;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.dreamstone.component.EscapeComponent;
import com.dreamstone.component.EscapeFrame;
import com.dreamstone.util.ArgumentsHandler;

public class Main {
	public static EscapeComponent escapeInstance;
	public static JFrame windowInstance;
	
	public static void main(String[] args) {
		ArgumentsHandler.processArguments(args);
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				escapeInstance = new EscapeComponent();
				windowInstance = new EscapeFrame(escapeInstance);
				escapeInstance.start();
			}
		});
	}
}
