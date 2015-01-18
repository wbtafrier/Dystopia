package com.dreamstone.file;

import java.io.File;
import java.io.IOException;

import com.dreamstone.tile.Tile;
import com.dreamstone.tile.TileList;
import com.dreamstone.util.DystopiaLogger;
import com.dreamstone.world.Chunk;
import com.dreamstone.world.Coordinate;
import com.dreamstone.world.World;

public class LoadManager {
	
	private static final int COORDINATE_ARGUMENTS = 6;
	public static String worldName;
	
	public static World loadWorld(String world) {
		worldName = world;
		File mapFolder = FileSystem.makeFolder(DirectoryMaster.worldsFolder, "map");
		File[] mapFiles = mapFolder.listFiles();
		String worldText, coordStr, tileName;
		String[] args;
		int tempStart, xCoord, yCoord, xDisplay, yDisplay, tileImageIndex;
		Tile tile;
		Chunk chunk;
		Coordinate coord;
		for (int f = 0; f < mapFiles.length; f++) {
			try {
				worldText = FileSystem.readTextFile(mapFiles[f]);
				for (int i = 0; i < worldText.length(); i++) {
					tempStart = 0;
					if (worldText.charAt(i) == '[') {
						tempStart = i + 1;
						continue;
					}
					else if (worldText.charAt(i) == ']') {
						coordStr = worldText.substring(tempStart, i);
						args = coordStr.split(",");
						if (args.length > COORDINATE_ARGUMENTS) {
							DystopiaLogger.logSevere("Coordinates cannot be loaded from file: too many arguments!");
							throw new IllegalArgumentException();
						}
						else {
							xCoord = Integer.parseInt(args[0]);
							yCoord = Integer.parseInt(args[1]);
							xDisplay = Integer.parseInt(args[2]);
							yDisplay = Integer.parseInt(args[3]);
							tileName = args[4];
							tile = TileList.getTileFromString(tileName);
							tileImageIndex = Integer.parseInt(args[5]);
							coord = new Coordinate(xCoord, yCoord, xDisplay, yDisplay, tile, tileImageIndex);
						}
					}
				}
			} catch (IOException e) {
				DystopiaLogger.logSevere("Could not load World file. It may be corrupt.");
				e.printStackTrace();
			}
		}
		//TODO: Remove temporary fix
		return null;
	}

}
