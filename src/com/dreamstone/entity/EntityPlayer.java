package com.dreamstone.entity;

import com.dreamstone.listener.PlayerOrientation;

public class EntityPlayer extends EntityMovable{
	
	private PlayerOrientation orientation;
	
	public EntityPlayer(String name, int health, String imgName, float speed) {
		super(name, health, imgName, speed);
		this.orientation = PlayerOrientation.NORTH;
	}

	public PlayerOrientation getOrientation() {
		return this.orientation;
	}
	
	public void setOrientation(PlayerOrientation op) {
		this.orientation = op;
	}
	
	@Override
	public void moving() {
		
	}
}
