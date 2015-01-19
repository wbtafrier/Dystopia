package com.dreamstone.file;

import java.io.File;
import java.io.IOException;

import com.dreamstone.core.Dystopia;
import com.dreamstone.entity.EntityPlayer;
import com.dreamstone.util.DystopiaLogger;
import com.dreamstone.world.Chunk;
import com.dreamstone.world.Coordinate;
import com.dreamstone.world.Quadrant;
import com.dreamstone.world.World;

public class SaveManager {

	private static World world;
	private static File worldFolder;
	
	public static void saveWorld() {
		world = Dystopia.getGame().currentWorld;
		worldFolder = FileSystem.makeFolder(DirectoryMaster.worldsFolder, world.getName());
		saveGrid();
		savePlayer();
		saveWorldSettings();
	}
	
	private static void saveGrid() {
		File mapFolder = FileSystem.makeFolder(worldFolder, "map");
		
		for (Quadrant q : world.getGrid().QUADRANTS) {
			StringBuilder quadText = new StringBuilder();
			for (int y = 0; y < q.getChunks().size(); y++) {
				for (int x = 0; x < q.getChunks().get(y).size(); x++) {
					for (int yy = 0; yy < Chunk.CHUNK_SIZE; yy++) {
						for (int xx = 0; xx < Chunk.CHUNK_SIZE; xx++) {
							Coordinate c = q.getChunks().get(y).get(x).getCoordinateFromIndex(xx, yy);
							quadText.append("[" + c.xCoordinate + "," + c.yCoordinate + "," + c.getXScreenPos() + "," + c.getYScreenPos() + "," + c.getTile().getName() + "," + c.getImageIndex() + "]");
						}
					}
				}
			}
			File quadFile = FileSystem.makeFile(mapFolder, "quad" + q.getQuadrant() + ".txt");
			
			try {
				FileSystem.writeTextFile(quadFile, quadText);
			} catch (IOException e) {
				DystopiaLogger.logSevere("Could not save Quadrant " + q.getQuadrant() + " into world file. Your world file may be corrupt.");
				e.printStackTrace();
			}
		}
	}
	
	private static void savePlayer() {
		EntityPlayer player = world.getPlayer();
		File playerFile = FileSystem.makeFile(worldFolder, player.getName() + ".txt");
		StringBuilder playerText = new StringBuilder();
		playerText.append("Player Health:" + FileSystem.TAB + "[" + player.getHealth() + "]" + FileSystem.LINE_BREAK);
		playerText.append("Hair Color:" + FileSystem.TAB + "[" + player.getHairColor().getRed() + "," + player.getHairColor().getGreen() + "," + player.getHairColor().getBlue() + "]" + FileSystem.LINE_BREAK);
		playerText.append("Eye Color:" + FileSystem.TAB + "[" + player.getEyeColor().getRed() + "," + player.getEyeColor().getGreen() + "," + player.getEyeColor().getBlue() + "]" + FileSystem.LINE_BREAK);
		playerText.append("Skin Color:" + FileSystem.TAB + "[" + player.getSkinColor().getRed() + "," + player.getSkinColor().getGreen() + "," + player.getSkinColor().getBlue() + "]" + FileSystem.LINE_BREAK);
		
		try {
			FileSystem.writeTextFile(playerFile, playerText);
		} catch (IOException e) {
			DystopiaLogger.logSevere("Could not save player file. Your player file may be corrupt.");
			e.printStackTrace();
		}
	}
	
	private static void saveWorldSettings() {
		//TODO: Fill in zeh blanks
		File settingsFile = FileSystem.makeFile(worldFolder, "settings.txt");
	}
}
