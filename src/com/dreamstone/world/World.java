package com.dreamstone.world;

import com.dreamstone.entity.EntityPlayer;

public class World {

	private String worldName;
	private Grid worldGrid;
	private EntityPlayer player;
	private int xOffset, yOffset;
	
	public World(String name, Grid grid) {
		this.worldName = name;
		this.worldGrid = grid;
	}
	
	public void setPlayer(EntityPlayer player) {
		this.player = player;
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	public Grid getGrid() {
		return this.worldGrid;
	}
	
	public String getName() {
		return this.worldName;
	}
	
	@Override
	public String toString() {
		return this.worldName;
	}
}
