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
	public static final String playersFolder = FileSystem.getClassLoaderResourceDirectory(texturesFolder, "players");
	public static final String tilesFolder = FileSystem.getClassLoaderResourceDirectory(texturesFolder, "tiles");
}
