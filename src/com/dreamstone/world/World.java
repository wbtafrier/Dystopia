package com.dreamstone.world;

import com.dreamstone.entity.EntityPlayer;

public class World {

	private String worldName;
	private Grid worldGrid;
	private EntityPlayer player;
	private int xOffset;
	private int yOffset;
	
	public World(String name, Grid grid) {
		this.worldName = name;
		this.worldGrid = grid;
		this.xOffset = 0;
		this.yOffset = 0;
	}
	
	public World(String name, Grid grid, EntityPlayer player) {
		this.worldName = name;
		this.worldGrid = grid;
		this.player = player;
		this.xOffset = 0;
		this.yOffset = 0;
	}
	
	public int getXOffset() {
		return this.xOffset;
	}
	
	public void setXOffset(int x) {
		this.xOffset = x;
	}
	
	public int getYOffset() {
		return this.yOffset;
	}
	
	public void setYOffset(int y) {
		this.yOffset = y;
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
