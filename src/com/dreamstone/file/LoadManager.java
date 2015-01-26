package com.dreamstone.file;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import com.dreamstone.tile.Tile;
import com.dreamstone.tile.TileList;
import com.dreamstone.util.DystopiaLogger;
import com.dreamstone.world.Chunk;
import com.dreamstone.world.Coordinate;
import com.dreamstone.world.Grid;
import com.dreamstone.world.World;

public class LoadManager {
	
	private static final int COORDINATE_ARGUMENTS = 6;
	private static File worldFolder;
	private static World loadedWorld;
	
	/**
	 * Creates a new World object that loads all of the properties of the specified World file.
	 * @param worldName The name of the World to load. MUST exist!
	 * @return A World object containing all of the properties of the data belonging to the specified World name.
	 */
	public static World loadWorld(String worldName) {
		
		worldFolder = FileSystem.makeFolder(DirectoryMaster.worldsFolder, worldName);
		loadedWorld = new World(worldName, loadGrid());
		
		return null;
	}

	private static Grid loadGrid() {
		File mapFolder = FileSystem.makeFolder(worldFolder, "map");
		File[] mapFiles = mapFolder.listFiles();
		
		String worldText, coordStr;
		String[] args;
		
		int tempStart, xCoord, yCoord, xDisplay, yDisplay, tileImageIndex, chunkX = 0, chunkY = 0;
		Tile tile;
		Coordinate coord;
		Grid loadedGrid = new Grid();
		Chunk currentChunk = null;
		
		//Load the quadrant files first.
		for (int quadFiles = 0; quadFiles < mapFiles.length; quadFiles++) {
			try {
				worldText = FileSystem.readTextFile(mapFiles[quadFiles]);
				tempStart = 0;
				for (int i = 0; i < worldText.length(); i++) {
					if (worldText.charAt(i) == '[') {
						tempStart = i + 1;
						continue;
					}
					else if (worldText.charAt(i) == ']' && !(tempStart >= worldText.length())) {
						
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
							tile = TileList.getTileFromString(args[4]);
							tileImageIndex = Integer.parseInt(args[5]);
							
							coord = new Coordinate(xCoord, yCoord, xDisplay, yDisplay, tile, tileImageIndex);
							Point p = Grid.getChunkFromCoordinate(coord.xCoordinate, coord.yCoordinate);
							if (currentChunk == null) {
								currentChunk = new Chunk(p.x, p.y);
								currentChunk.getCoords()[coord.yCoordinate][coord.xCoordinate] = coord;
							}
							else if (currentChunk.getCoordinateFromIndex(Chunk.CHUNK_SIZE - 1, Chunk.CHUNK_SIZE - 1).xCoordinate == coord.xCoordinate &&
									currentChunk.getCoordinateFromIndex(Chunk.CHUNK_SIZE - 1, Chunk.CHUNK_SIZE - 1).yCoordinate == coord.yCoordinate) {
								currentChunk.getCoords()[coord.yCoordinate][coord.xCoordinate] = coord;
								loadedGrid.QUADRANTS.get(currentChunk.getQuadrantNumber() - 1).replaceChunk(currentChunk);
								currentChunk = null;
							}
							else {
								currentChunk.getCoords()[coord.yCoordinate][coord.xCoordinate] = coord;
							}
						}
					}
				}
			} catch (IOException ioe) {
				DystopiaLogger.logSevere("Could not load World file. Does the World exist in save data? Is it corrupt?");
				ioe.printStackTrace();
			} catch (NumberFormatException nfe) {
				DystopiaLogger.logSevere("World file is incorrectly formatted! Has the file been tampered with?");
				nfe.printStackTrace();
			}
		}
		return loadedGrid;
	}
	
//	private static EntityPlayer loadPlayer() {
//		
//	}
}
