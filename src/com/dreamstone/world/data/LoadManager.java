package com.dreamstone.world.data;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.dreamstone.entity.EntityPlayer;
import com.dreamstone.file.DirectoryMaster;
import com.dreamstone.file.FileSystem;
import com.dreamstone.tile.Tile;
import com.dreamstone.tile.TileList;
import com.dreamstone.util.DystopiaLogger;
import com.dreamstone.world.Chunk;
import com.dreamstone.world.Coordinate;
import com.dreamstone.world.Grid;
import com.dreamstone.world.World;

public class LoadManager {
	
	private static final int COORDINATE_ARGUMENTS = 6;
	
	/**
	 * Creates a new World object that loads all of the properties of the specified World file.
	 * @param worldName The name of the World to load. MUST exist!
	 * @param username The name of the player currently logged in.
	 * @return A World object containing all of the properties of the data belonging to the specified World name.
	 */
	public static World loadWorld(String worldName, String username) {
		
		File worldFolder = FileSystem.getFolder(DirectoryMaster.worldsFolder, worldName);
		World loadedWorld = new World(worldName, loadGrid(worldFolder));
		loadedWorld.setPlayer(loadPlayer(worldFolder, username));
		
		return loadedWorld;
	}

	private static Grid loadGrid(File worldFolder) {
		File mapFolder = FileSystem.getFolder(worldFolder, "map");
		File[] mapFiles = mapFolder.listFiles();
		
		String worldText, coordStr;
		String[] args;
		
		int tempStart, xCoord, yCoord, xDisplay, yDisplay, tileImageIndex;
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
					else if (worldText.charAt(i) == ']' && tempStart < worldText.length()) {
						
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
//								currentChunk.getCoords()[coord.yCoordinate][coord.xCoordinate] = coord;
//								currentChunk.setCoordinate(coord);
							}
							else if (currentChunk.getCoordinateFromIndex(Chunk.CHUNK_SIZE - 1, Chunk.CHUNK_SIZE - 1).xCoordinate == coord.xCoordinate &&
									currentChunk.getCoordinateFromIndex(Chunk.CHUNK_SIZE - 1, Chunk.CHUNK_SIZE - 1).yCoordinate == coord.yCoordinate) {
//								currentChunk.setCoordinate(coord);
								loadedGrid.QUADRANTS.get(currentChunk.getQuadrantNumber() - 1).replaceChunk(currentChunk);
								currentChunk = null;
							}
							else {
//								currentChunk.setCoordinate(coord);
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
	
	private static EntityPlayer loadPlayer(File worldFolder, String username) {
		EntityPlayer player = new EntityPlayer(username);
		File playerFolder = FileSystem.getFolder(worldFolder, "players");
		File playerFile = FileSystem.getFile(playerFolder, username + ".txt");
		String playerFileText;
		int tempStart = 0;
		ArrayList<String> args = new ArrayList<>();
		
		try {
			playerFileText = FileSystem.readTextFile(playerFile);
		
			for (int i = 0; i < playerFileText.length(); i++) {
				if (playerFileText.charAt(i) == '[') {
					tempStart = i + 1;
					continue;
				}
				else if (playerFileText.charAt(i) == ']' && tempStart < playerFileText.length()) {
					args.add(playerFileText.substring(tempStart, i));
				}
			}
			
			for (int i = 0; i < args.size(); i++) {
				String[] valueSet = args.get(i).split(",");
				//Retrieve the player data
				switch (i) {
					//Player Health
					case 0: {
						player.setHealth(Integer.parseInt(valueSet[0]));
						break;
					}
					//Player Hair Color
					case 1: {
						player.getPlayerOptions().setHairColor(new Color(Integer.parseInt(valueSet[0]), Integer.parseInt(valueSet[1]), Integer.parseInt(valueSet[2])));
						break;
					}
					//Player Eye Color
					case 2: {
						player.getPlayerOptions().setEyeColor(new Color(Integer.parseInt(valueSet[0]), Integer.parseInt(valueSet[1]), Integer.parseInt(valueSet[2])));
						break;
					}
					//Player Skin Color
					case 3: {
						player.getPlayerOptions().setSkinTone(new Color(Integer.parseInt(valueSet[0]), Integer.parseInt(valueSet[1]), Integer.parseInt(valueSet[2])));
						break;
					}
				}
			}
		} catch (IOException ioe) {
			DystopiaLogger.logSevere("Could not load player file. Is it corrupt?");
			ioe.printStackTrace();
		} catch (NumberFormatException nfe) {
			DystopiaLogger.logSevere("Player file is incorrectly formatted! Has the file been tampered with?");
			nfe.printStackTrace();
		}
		
		return player;
	}
	
	public static World createWorld(String worldName, String username) {
		World loadedWorld = new World(worldName, new Grid());
		loadedWorld.setPlayer(new EntityPlayer(username));
		return loadedWorld;
	}
	
}
