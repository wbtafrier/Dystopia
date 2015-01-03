package com.dreamstone.file;

import java.awt.image.BufferedImage;

import com.dreamstone.core.Dystopia;
import com.dreamstone.util.DystopiaLogger;


public class ResourceLoader {

	// IMAGE FILES
	public static BufferedImage terrainSheet;
	
	public static void loadAllResources() {
		if (Dystopia.getGame() == null || !Dystopia.getGame().isRunning()) {
			loadTilesheets();
		}
		else {
			DystopiaLogger.logWarning("Resources can only be loaded before the game starts.");
		}
	}
	
	private static void loadTilesheets() {
		terrainSheet = FileSystem.loadImageFromJar(DirectoryMaster.texturesFolder, "terrain.png");
	}
}
