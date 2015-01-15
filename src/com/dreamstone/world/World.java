package com.dreamstone.world;

import com.dreamstone.entity.EntityPlayer;

public class World {

	private float xOffset;
	private float yOffset;
	private EntityPlayer player;
	
	public World() {
		this.xOffset = 0;
		this.yOffset = 0;
		player = new EntityPlayer("Andy", 100, "terrain.png", 5.0F);
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	public float getXOffset() {
		return this.xOffset;
	}
	
	public float getYOffset() {
		return this.yOffset;
	}
	
	public void setXOffset(float x) {
		xOffset = x;
	}
	
	public void setYOffset(float y) {
		yOffset = y;
	}
}
