package com.dreamstone.file;

import java.awt.image.BufferedImage;

import com.dreamstone.core.Dystopia;
import com.dreamstone.util.DystopiaLogger;
import com.dreamstone.util.TransformImage;

public class ResourceLoader {

	//Image Files
	public static BufferedImage terrainSheet;
	public static BufferedImage nullImage;
	
	public static void loadAllResources() {
		if (Dystopia.getGame() == null || !Dystopia.getGame().isRunning()) {
			loadImages(4F);
		}
		else {
			DystopiaLogger.logWarning("Resources can only be loaded before the game starts.");
		}
	}
	
	private static void loadImages(float scale) {
		terrainSheet = TransformImage.scaleImage(FileSystem.loadImageFromJar(DirectoryMaster.tilesFolder, "terrain.png"), scale);
		nullImage = TransformImage.scaleImage(FileSystem.loadImageFromJar(DirectoryMaster.tilesFolder, "null_image.png"), scale);
	}
	
	private static void updateGraphics(float scale) {
		loadImages(scale);
	}
}
