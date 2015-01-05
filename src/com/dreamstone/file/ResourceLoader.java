package com.dreamstone.file;

import java.awt.image.BufferedImage;

import com.dreamstone.core.Dystopia;
import com.dreamstone.util.DystopiaLogger;

public class ResourceLoader {

	//Image Files
	public static BufferedImage terrainSheet;
	public static BufferedImage nullImage;
	
	public static void loadAllResources() {
		if (Dystopia.getGame() == null || !Dystopia.getGame().isRunning()) {
			loadImages();
		}
		else {
			DystopiaLogger.logWarning("Resources can only be loaded before the game starts.");
		}
	}
	
	private static void loadImages() {
		terrainSheet = FileSystem.loadImageFromJar(DirectoryMaster.tilesFolder, "terrain.png");
		nullImage = FileSystem.loadImageFromJar(DirectoryMaster.tilesFolder, "null_image.png");
	}
}
