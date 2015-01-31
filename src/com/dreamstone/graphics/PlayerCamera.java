package com.dreamstone.graphics;

import java.awt.Graphics;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.core.Dystopia;
import com.dreamstone.entity.EntityPlayer;
import com.dreamstone.tile.Tile;
import com.dreamstone.world.World;


public class PlayerCamera {
	
	public static EntityPlayer player = Dystopia.getGame().getCurrentWorld().getPlayer();
	public static World world = Dystopia.getGame().getCurrentWorld();
//	private static final int PLAYER_BOUNDS = 3;

	public static void movePlayer() {
		player.setCurrentSpeed();
		
		if (player.getDirectionBools()[0]) {
			movePlayerNorth();
		}
		if (player.getDirectionBools()[1]) {
			movePlayerSouth();
		}
		if (player.getDirectionBools()[2]) {
			movePlayerEast();
		}
		if (player.getDirectionBools()[3]) {
			movePlayerWest();
		}
		System.out.println(player.directions[0] + ", " + player.directions[1] + ", " + player.directions[2] + ", " + player.directions[3]);
	}
	
	public static void moveWorld() {
		if (player.getDirectionBools()[0]) {
			moveWorldNorth();
		}
		if (player.getDirectionBools()[1]) {
			moveWorldSouth();
		}
		if (player.getDirectionBools()[2]) {
			moveWorldEast();
		}
		if (player.getDirectionBools()[3]) {
			moveWorldWest();
		}
	}
	
	public static void drawPlayer(Graphics g) {
		g.drawImage(player.getImage(), ((DisplayCarrier.getCanvas().getWidth() - player.getImage().getWidth()) / 2) + player.getXCenterOffset(), 
				((DisplayCarrier.getCanvas().getHeight() / 2) - player.getImage().getHeight()) + player.getYCenterOffset(), null);
	}
	
	private static void moveWorldNorth() {
		world.setYOffset((int)(world.getYOffset() + player.getSpeed()));
	}
	
	private static void moveWorldSouth() {
		world.setYOffset((int)(world.getYOffset() - player.getSpeed()));
	}
	
	private static void moveWorldEast() {
		world.setXOffset((int)(world.getXOffset() - player.getSpeed()));
	}
	
	private static void moveWorldWest() {
		world.setXOffset((int)(world.getXOffset() + player.getSpeed()));
	}
	
	private static void movePlayerNorth() {
		if (player.getYCenterOffset() >= -Tile.getTileSize()) {
			player.setYCenterOffset((int)(player.getYCenterOffset() - player.getSpeed()));
			if (boundCheck()) {
				moveWorld();
			}
		}
	}
	
	private static void movePlayerSouth() {
		if (player.getYCenterOffset() <= Tile.getTileSize()) {
			player.setYCenterOffset((int)(player.getYCenterOffset() + player.getSpeed()));
			if (boundCheck()) {
				moveWorld();
			}
		}
	}
	
	private static void movePlayerEast() {
		if (player.getXCenterOffset() <= Tile.getTileSize()) {
			player.setXCenterOffset((int)(player.getXCenterOffset() + player.getSpeed()));
			if (boundCheck()) {
				moveWorld();
			}
		}
	}
	
	private static void movePlayerWest() {
		if (player.getXCenterOffset() >= -Tile.getTileSize()) {
			player.setXCenterOffset((int)(player.getXCenterOffset() - player.getSpeed()));
			if (boundCheck()) {
				moveWorld();
			}
		}
	}
	
	private static boolean boundCheck() {
		
		if (player.getYCenterOffset() < -Tile.getTileSize()) {
			player.setYCenterOffset(-Tile.getTileSize());
			return true;
		}
		
		if (player.getYCenterOffset() > Tile.getTileSize()) {
			player.setYCenterOffset(Tile.getTileSize());
			return true;
		}
		
		if (player.getXCenterOffset() > Tile.getTileSize()) {
			player.setXCenterOffset(Tile.getTileSize());
			return true;
		}
		
		if (player.getXCenterOffset() < -Tile.getTileSize()) {
			player.setXCenterOffset(-Tile.getTileSize());
			return true;
		}
		
		return false;
	}
}
