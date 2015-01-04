package com.dreamstone.core;

import java.awt.EventQueue;

import com.dreamstone.file.FileSystem;
import com.dreamstone.file.ResourceLoader;
import com.dreamstone.util.DystopiaLogger;
import com.dreamstone.util.LaunchHandler;

public class Main {
	
	public static void main(String[] args) {
		LaunchHandler.processArguments(args);
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				boolean directoryMade = FileSystem.createGameDirectory();
				if (!directoryMade) throw new RuntimeException("Game directories cannot be made: send this report to DystopiaBugs@dreamstone.com");
				ResourceLoader.loadAllResources();
				Dystopia.gameInstance = new Dystopia();
				DisplayCarrier.initializeDisplay();
				Dystopia.getGame().start();
			}
		});
	}
}
