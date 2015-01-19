package com.dreamstone.file;

import java.awt.image.BufferedImage;

import com.dreamstone.core.Dystopia;
import com.dreamstone.util.DystopiaLogger;

public class ResourceLoader {
	
	//Image Files
	public static BufferedImage terrainSheet;
	public static BufferedImage nullImage;
	
	//Player Files
	public static BufferedImage playerIdle;
	public static BufferedImage playerNorthAnimation;
	public static BufferedImage playerSouthAnimation;
	public static BufferedImage playerHorizontalAnimation;
	
	public static void loadAllResources() {
		if (Dystopia.getGame() == null || !Dystopia.getGame().isRunning()) {
			loadImages();
		}
		else {
			DystopiaLogger.logWarning("Resources can only be loaded before the game starts.");
		}
	}
	
	private static void loadImages() {
		loadTileSheets();
		loadIndependentTileImages();
		loadPlayerImages();
	}
	
	public static void loadTileSheets() {
		terrainSheet = FileSystem.loadImageFromJar(DirectoryMaster.tilesFolder, "terrain.png");
	}
	
	public static void loadIndependentTileImages() {
		nullImage = FileSystem.loadImageFromJar(DirectoryMaster.tilesFolder, "null_image.png");
	}
	
	public static void loadPlayerImages() {
		playerIdle = FileSystem.loadImageFromJar(DirectoryMaster.playersFolder, "player_idle.png");
		playerNorthAnimation = FileSystem.loadImageFromJar(DirectoryMaster.playersFolder, "player_walk_north.png");
		playerSouthAnimation = FileSystem.loadImageFromJar(DirectoryMaster.playersFolder, "player_walk_south.png");
		playerHorizontalAnimation = FileSystem.loadImageFromJar(DirectoryMaster.playersFolder, "player_walk_horizontal.png");
	}
	
	public static BufferedImage getTileSheet(String tileSheetName) {
		if (tileSheetName.equals("terrain.png")) {
			return terrainSheet;
		}
		else {
			DystopiaLogger.logWarning("THE TERRAIN SHEET '" + tileSheetName + "' CANNOT BE FOUND. RETURNING 'terrain.png'.");
			return terrainSheet;
		}
	}
}
