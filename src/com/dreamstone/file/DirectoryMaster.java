package com.dreamstone.file;

import java.io.File;

public class DirectoryMaster {
	
	//Master Directory (User Data)
	public static final File gameFolder = FileSystem.getGameFolder();
	
	// Dystopia/...
	public static final File savesFolder = FileSystem.makeFolder(gameFolder, "saves");
	public static final File logsFolder = FileSystem.makeFolder(gameFolder, "logs");
	
	
	//Master Directory (Resources)
	public static final String resourcesFolder = FileSystem.getClassLoaderResourceDirectory("resources", "");
	
	// resources/...
	public static final String texturesFolder = FileSystem.getClassLoaderResourceDirectory(resourcesFolder, "textures");
	
	// textures/...
	public static final String individualTilesFolder = FileSystem.getClassLoaderResourceDirectory(texturesFolder, "individual_tiles");
	
	// individual_tiles/...
	public static final String dirtTilesFolder = FileSystem.getClassLoaderResourceDirectory(individualTilesFolder, "dirt_tiles");
	public static final String grassTilesFolder = FileSystem.getClassLoaderResourceDirectory(individualTilesFolder, "grass_tiles");
}
