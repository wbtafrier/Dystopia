//package com.dreamstone.file;
//
//import java.io.File;
//import java.io.IOException;
//
//import com.dreamstone.core.Dystopia;
//import com.dreamstone.tile.Tile;
//import com.dreamstone.tile.TileList;
//import com.dreamstone.util.DystopiaLogger;
//import com.dreamstone.world.Coordinate;
//import com.dreamstone.world.World;
//
//public class LoadManager {
//	
//	private static final int COORDINATE_ARGUMENTS = 6;
//	private static File worldFolder;
//	public static String worldName;
//	
//	public static World loadWorld(String world) {
//		worldName = world;
//		worldFolder = FileSystem.makeFolder(DirectoryMaster.worldsFolder, Dystopia.getGame().currentWorld.getName());
//		File mapFolder = FileSystem.makeFolder(worldFolder, "map");
//		File[] mapFiles = mapFolder.listFiles();
//		String worldText, coordStr;
//		String[] args;
//		int tempStart, xCoord, yCoord, xDisplay, yDisplay, tileImageIndex, chunkX, chunkY;
//		Tile tile;
//		Coordinate coord;
//		for (int quadFiles = 0; quadFiles < mapFiles.length; quadFiles++) {
//			try {
//				worldText = FileSystem.readTextFile(mapFiles[quadFiles]);
//				tempStart = 0;
//				for (int i = 0; i < worldText.length(); i++) {
//					if (worldText.charAt(i) == '[') {
//						tempStart = i + 1;
//						continue;
//					}
//					else if (worldText.charAt(i) == ']' && !(tempStart >= worldText.length())) {
//						
//						coordStr = worldText.substring(tempStart, i);
//						args = coordStr.split(",");
//						
////						for (String s : args) {
////							System.out.print(s + " ");
////						}
////						System.out.println();
//						
//						if (args.length > COORDINATE_ARGUMENTS) {
//							DystopiaLogger.logSevere("Coordinates cannot be loaded from file: too many arguments!");
//							throw new IllegalArgumentException();
//						}
//						else {
//							xCoord = Integer.parseInt(args[0]);
//							yCoord = Integer.parseInt(args[1]);
//							xDisplay = Integer.parseInt(args[2]);
//							yDisplay = Integer.parseInt(args[3]);
//							tile = TileList.getTileFromString(args[4]);
//							tileImageIndex = Integer.parseInt(args[5]);
//							
//							coord = new Coordinate(xCoord, yCoord, xDisplay, yDisplay, tile, tileImageIndex);
//							
//							
////							if (quadFiles == 0) {
////								chunkX = (int) Dystopia.getGame().currentWorld.getGrid().getChunkFromCoordinate(coord.xCoordinate, coord.yCoordinate).getX();
////								chunkY = (int) Dystopia.getGame().currentWorld.getGrid().getChunkFromCoordinate(coord.xCoordinate, coord.yCoordinate).getY();
////								System.out.println("CHUNKS: (" + chunkX + ", " + chunkY + ")");
////							}
//							
//							//get the xCoord and yCoord from the coordinate. get which chunk they should be in. 
////							System.out.println("The chunk that: (" +  coord.xCoordinate + ", " + coord.yCoordinate + ") is in Chunk: (" + chunkX + ", " + chunkY + ")");
//							
////							Dystopia.getGame().currentWorld.getGrid().QUADRANTS.get(quadFiles).growQuadrant(new Chunk(chunkX, chunkY));
//						}
//					}
//				}
//			} catch (IOException e) {
//				DystopiaLogger.logSevere("Could not load World file. It may be corrupt.");
//				e.printStackTrace();
//			}
//		}
//		//TODO: Remove temporary fix
//		return null;
//	}
//
//}
