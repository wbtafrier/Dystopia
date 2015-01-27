package com.dreamstone.core;

import java.awt.EventQueue;

import com.dreamstone.file.FileSystem;
import com.dreamstone.file.ResourceLoader;
import com.dreamstone.graphics.GraphicsOptions;
import com.dreamstone.graphics.TempLoginFrame;
import com.dreamstone.input.KeyInputManager;
import com.dreamstone.tile.TileList;
import com.dreamstone.util.LaunchHandler;

public class Start {
	
	public static void main(String[] args) {
		LaunchHandler.processArguments(args);
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				createDirectories();
				initializeResources();
				
				//Create the instance of the game and call the constructor. Prepares the game loop instance variables.
				Dystopia.gameInstance = new Dystopia();
				initializeTilesAndItems();
				new TempLoginFrame();
			}
		});
	}
	
	public static void startGame() {
		initializeListeners();
		setupDisplay();
		//Start the game loop!!!!
		Dystopia.getGame().start();
	}
	
	private static void createDirectories() {
		// Creates primary game directory, where all local data will be stored (i.e. world, player data, etc.)
		boolean directoryMade = FileSystem.createGameDirectory();
		if (!directoryMade) {
			throw new RuntimeException("Game directories cannot be made: send this report to DystopiaBugs@dreamstone.com");
		}
	}
	
	private static void initializeResources() {
		//TODO: Check if graphics file exists, otherwise make one and set default options.
		//Sets all of the default graphics options (i.e. scale, render distance, etc.)
		GraphicsOptions.initializeGraphicsOptions();
		//Loads all image, sound, text, and other resources into variables.
		ResourceLoader.loadAllResources();
		GraphicsOptions.updateGraphics(GraphicsOptions.getScale());
	}
	
	private static void initializeTilesAndItems() {
		//Add all Tiles to the tile list.
		TileList.registerTiles();
	}
	
	private static void initializeListeners() {
		Dystopia.getGame().keyListener = new KeyInputManager();
	}
	
	private static void setupDisplay() {
		//Initialize Canvas and JFrame, final stage in preparing the game's start-up.
		DisplayCarrier.initializeDisplay();
	}
}
