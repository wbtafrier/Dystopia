package com.dreamstone.graphics;

import java.awt.Graphics;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.core.Dystopia;
import com.dreamstone.entity.EntityPlayer;
import com.dreamstone.tile.EnumDirection;
import com.dreamstone.tile.Tile;
import com.dreamstone.world.World;


public class PlayerCamera {
	
	public static EntityPlayer player = Dystopia.getGame().getCurrentWorld().getPlayer();
	public static World world = Dystopia.getGame().getCurrentWorld();

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
			if (boundCheck(EnumDirection.NORTH)) {
				moveWorldNorth();
			}
		}
	}
	
	private static void movePlayerSouth() {
		if (player.getYCenterOffset() <= Tile.getTileSize()) {
			player.setYCenterOffset((int)(player.getYCenterOffset() + player.getSpeed()));
			if (boundCheck(EnumDirection.SOUTH)) {
				moveWorldSouth();
			}
		}
	}
	
	private static void movePlayerEast() {
		if (player.getXCenterOffset() <= Tile.getTileSize()) {
			player.setXCenterOffset((int)(player.getXCenterOffset() + player.getSpeed()));
			if (boundCheck(EnumDirection.EAST)) {
				moveWorldEast();
			}
		}
	}
	
	private static void movePlayerWest() {
		if (player.getXCenterOffset() >= -Tile.getTileSize()) {
			player.setXCenterOffset((int)(player.getXCenterOffset() - player.getSpeed()));
			if (boundCheck(EnumDirection.WEST)) {
				moveWorldWest();
			}
		}
	}
	
	private static boolean boundCheck(EnumDirection dir) {
		
		if (dir == EnumDirection.NORTH && player.getYCenterOffset() < -Tile.getTileSize()) {
			player.setYCenterOffset(-Tile.getTileSize());
			return true;
		}
		else if (dir == EnumDirection.SOUTH && player.getYCenterOffset() > Tile.getTileSize()) {
			player.setYCenterOffset(Tile.getTileSize());
			return true;
		}
		else if (dir == EnumDirection.EAST && player.getXCenterOffset() > Tile.getTileSize()) {
			player.setXCenterOffset(Tile.getTileSize());
			return true;
		}
		else if (dir == EnumDirection.WEST && player.getXCenterOffset() < -Tile.getTileSize()) {
			player.setXCenterOffset(-Tile.getTileSize());
			return true;
		}
		else return false;
	}
}
