package com.dreamstone.world;

import com.dreamstone.entity.EntityPlayer;

public class World {

	private int xOffset;
	private int yOffset;
	private EntityPlayer player;
	
	public World() {
		this.xOffset = 0;
		this.yOffset = 0;
		player = new EntityPlayer("Andy", 100, "terrain.png", 1.0F);
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	public int getXOffset() {
		return this.xOffset;
	}
	
	public int getYOffset() {
		return this.yOffset;
	}
	
	public void setXOffset(int x) {
		xOffset = x;
	}
	
	public void setYOffset(int y) {
		yOffset = y;
	}
}
