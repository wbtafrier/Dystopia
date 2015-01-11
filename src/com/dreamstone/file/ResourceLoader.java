package com.dreamstone.file;

import java.awt.image.BufferedImage;

import com.dreamstone.core.Dystopia;
import com.dreamstone.graphics.GraphicsOptions;
import com.dreamstone.util.DystopiaLogger;
import com.dreamstone.util.TransformImage;

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
		terrainSheet = TransformImage.scaleImage(FileSystem.loadImageFromJar(DirectoryMaster.tilesFolder, "terrain.png"), GraphicsOptions.getScale());
		nullImage = TransformImage.scaleImage(FileSystem.loadImageFromJar(DirectoryMaster.tilesFolder, "null_image.png"), GraphicsOptions.getScale());
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
