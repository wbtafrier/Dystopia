package com.dreamstone.file;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;

import com.dreamstone.core.Dystopia;
import com.dreamstone.util.DystopiaLogger;


public class ResourceLoader {

	// IMAGE FILES
	
	// TILES
	public static BufferedImage dirt1, dirt2, grass1, grass2, grassDirtEast1, grassDirtEast2, grassDirtNorth1,
	grassDirtNorth2, grassDirtWest1, grassDirtWest2;
	
	public static void loadAllResources() {
		if (Dystopia.getGame() == null || !Dystopia.getGame().isRunning()) {
			loadTileImages();
		}
		else {
			DystopiaLogger.getLogger().log(Level.WARNING, "Resources can only be loaded before the game starts.");
		}
	}
	
	private static void loadTileImages() {
		dirt1 = loadImageFromJar(
				DirectoryMaster.dirtTilesFolder
				, "dirt_1.png");
		dirt2 = loadImageFromJar(DirectoryMaster.dirtTilesFolder, "dirt_2.png");
		grass1 = loadImageFromJar(DirectoryMaster.grassTilesFolder, "grass_1.png");
		grass2 = loadImageFromJar(DirectoryMaster.grassTilesFolder, "grass_2.png");
		grassDirtEast1 = loadImageFromJar(DirectoryMaster.grassTilesFolder, "grass_to_dirt_east_1.png");
		grassDirtEast2 = loadImageFromJar(DirectoryMaster.grassTilesFolder, "grass_to_dirt_east_2.png");
		grassDirtNorth1 = loadImageFromJar(DirectoryMaster.grassTilesFolder, "grass_to_dirt_north_1.png");
		grassDirtNorth2 = loadImageFromJar(DirectoryMaster.grassTilesFolder, "grass_to_dirt_north_2.png");
		grassDirtWest1 = loadImageFromJar(DirectoryMaster.grassTilesFolder, "grass_to_dirt_west_1.png");
		grassDirtWest2 = loadImageFromJar(DirectoryMaster.grassTilesFolder, "grass_to_dirt_west_2.png");
	}
	
	private static BufferedImage loadImageFromJar(String directory, String imageNameDotExtension) {
		BufferedImage bi = null;
		
		try {
			bi = FileSystem.readImageFile(FileSystem.getClassLoaderResourceFile(directory, imageNameDotExtension));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bi;
	}
}
