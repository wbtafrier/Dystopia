package com.dreamstone.file;

import java.io.File;

public class DirectoryMaster {
	
	//Master Directory (User Data)
	public static final File gameFolder = FileSystem.getGameFolder();
	
	// Dystopia/...
	public static final File worldsFolder = FileSystem.getFolder(gameFolder, "worlds");
	public static final File logsFolder = FileSystem.getFolder(gameFolder, "logs");
	
	//Master Directory (Resources)
	public static final String resourcesFolder = FileSystem.getClassLoaderResourceDirectory("resources", "");
	
	// resources/...
	public static final String texturesFolder = FileSystem.getClassLoaderResourceDirectory(resourcesFolder, "textures");
	
	// textures/...
	public static final String playersFolder = FileSystem.getClassLoaderResourceDirectory(texturesFolder, "player");
	public static final String tilesFolder = FileSystem.getClassLoaderResourceDirectory(texturesFolder, "tiles");
}
