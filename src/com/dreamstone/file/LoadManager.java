package com.dreamstone.file;

import java.io.File;
import java.io.IOException;

import com.dreamstone.tile.Tile;
import com.dreamstone.tile.TileList;
import com.dreamstone.util.DystopiaLogger;
import com.dreamstone.world.World;

public class LoadManager {
	
	private static final int COORDINATE_ARGUMENTS = 4;
	public static String worldName;
	
	public static World loadWorld(String world) {
		worldName = world;
		File mapFolder = FileSystem.makeFolder(DirectoryMaster.worldsFolder, "map");
		File[] mapFiles = mapFolder.listFiles();
		for (int f = 0; f < mapFiles.length; f++) {
			String worldText;
			try {
				worldText = FileSystem.readTextFile(mapFiles[f]);
				for (int i = 0; i < worldText.length(); i++) {
					int tempStart = 0;
					if (worldText.charAt(i) == '[') {
						tempStart = i + 1;
						continue;
					}
					else if (worldText.charAt(i) == ']') {
						String coordStr = worldText.substring(tempStart, i);
						String[] args = coordStr.split(",");
						if (args.length > COORDINATE_ARGUMENTS) {
							DystopiaLogger.logSevere("Coordinates cannot be loaded from file: too many arguments!");
							throw new IllegalArgumentException();
						}
						else {
							int xCoord = Integer.parseInt(args[0]);
							int yCoord = Integer.parseInt(args[1]);
							String tileName = args[2];
							Tile tile = TileList.getTileFromString(tileName);
							int tileImageIndex = Integer.parseInt(args[3]);
//							Coordinate coord = new Coordinate(xCoord, yCoord, tile, tileImageIndex);
						}
					}
				}
			} catch (IOException e) {
				DystopiaLogger.logSevere("Could not load World file. It may be corrupt.");
				e.printStackTrace();
			}
		}
		return null;
	}

}
