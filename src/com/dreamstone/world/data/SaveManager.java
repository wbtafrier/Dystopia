package com.dreamstone.world.data;

import java.io.File;
import java.io.IOException;

import com.dreamstone.core.Dystopia;
import com.dreamstone.entity.EntityPlayer;
import com.dreamstone.file.DirectoryMaster;
import com.dreamstone.file.FileSystem;
import com.dreamstone.util.DystopiaLogger;
import com.dreamstone.world.Chunk;
import com.dreamstone.world.Coordinate;
import com.dreamstone.world.Quadrant;
import com.dreamstone.world.World;

public class SaveManager {

	private static World world;
	private static File worldFolder;
	
	/**
	 * Saves the currently loaded World to the world's save data.
	 */
	public static void saveWorld() {
		world = Dystopia.getGame().getCurrentWorld();
		worldFolder = FileSystem.getFolder(DirectoryMaster.worldsFolder, world.getName());
		saveGrid();
		savePlayer();
		saveWorldProperties();
	}
	
	private static void saveGrid() {
		File mapFolder = FileSystem.getFolder(worldFolder, "map");
		
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
			File quadFile = FileSystem.getFile(mapFolder, "quad" + q.getQuadrantNumber() + ".txt");
			
			try {
				FileSystem.writeTextFile(quadFile, quadText);
			} catch (IOException e) {
				DystopiaLogger.logSevere("Could not save Quadrant " + q.getQuadrantNumber() + " into world file. Your world file may be corrupt.");
				e.printStackTrace();
			}
		}
	}
	
	protected static void savePlayer() {
		File playerFolder = FileSystem.getFolder(worldFolder, "players");
		
		EntityPlayer player = world.getPlayer();
		File playerFile = FileSystem.getFile(playerFolder, player.getName() + ".txt");
		StringBuilder playerText = new StringBuilder();
		playerText.append("Health: [" + player.getHealth() + "]" + FileSystem.LINE_BREAK);
		playerText.append("Hair Color: [" + player.getPlayerOptions().getHairColor().getRed() + "," + player.getPlayerOptions().getHairColor().getGreen() + "," + player.getPlayerOptions().getHairColor().getBlue() + "]" + FileSystem.LINE_BREAK);
		playerText.append("Eye Color: [" + player.getPlayerOptions().getEyeColor().getRed() + "," + player.getPlayerOptions().getEyeColor().getGreen() + "," + player.getPlayerOptions().getEyeColor().getBlue() + "]" + FileSystem.LINE_BREAK);
		playerText.append("Skin Color: [" + player.getPlayerOptions().getSkinColor().getRed() + "," + player.getPlayerOptions().getSkinColor().getGreen() + "," + player.getPlayerOptions().getSkinColor().getBlue() + "]" + FileSystem.LINE_BREAK);
		
		try {
			FileSystem.writeTextFile(playerFile, playerText);
		} catch (IOException e) {
			DystopiaLogger.logSevere("Could not save player file. Your player file may be corrupt.");
			e.printStackTrace();
		}
	}
	
	private static void saveWorldProperties() {
		//TODO: Fill in zeh blanks
		File propertiesFile = FileSystem.getFile(worldFolder, "worldProperties.txt");
	}
}
