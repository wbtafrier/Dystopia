package com.dreamstone.entity;

import com.dreamstone.file.ResourceLoader;
import com.dreamstone.tile.EnumDirection;
import com.dreamstone.world.Coordinate;

public final class EntityPlayer extends EntityMovable {
	
	private EntityImageStorage imageStorage = new EntityImageStorage(
		ResourceLoader.playerIdle, 
		ResourceLoader.playerNorthAnimation, 
		ResourceLoader.playerSouthAnimation, 
		ResourceLoader.playerEastAnimation,
		ResourceLoader.playerWestAnimation
	);
	
	private boolean isWalking;
	
	//[north][south][east][west]
	public boolean[] directions;
	
	private int xCenterOffset;
	private int yCenterOffset;
	
	private Coordinate currentCoordinate;
	private EnumDirection playerDirection;
	private EntityCharacteristics playerOptions;
	
	public EntityPlayer(String name) {
		this(name, 0, 0);
	}
	
	public EntityPlayer(String name, int xCenterOffset, int yCenterOffset) {
		this(name, xCenterOffset, yCenterOffset, 100);
	}
	
	public EntityPlayer(String name, int xCenterOffset, int yCenterOffset, int health) {
		this(name, xCenterOffset, yCenterOffset, 100, new EntityCharacteristics());
	}
	
	public EntityPlayer(String name, int xCenterOffset, int yCenterOffset, int health, EntityCharacteristics playerType) {
		super(name, health);
		this.directions = new boolean[4];
		this.initializeWalking();
		this.setDirection(EnumDirection.SOUTH);
		
		this.setXCenterOffset(xCenterOffset);
		this.setYCenterOffset(yCenterOffset);
		this.playerOptions = playerType;
//		this.imageStorage.updateImages(this.playerOptions);
	}
	
	public void setDirection(EnumDirection dir) {
		this.playerDirection = dir;
		this.updatePlayerImage();
	}
	
	public EnumDirection getDirection() {
		return this.playerDirection;
	}
	
	public boolean[] getDirectionBools() {
		return this.directions;
	}
	
	public void updatePlayerImage() {
		
		if (!this.isWalking) {
			this.setIdleImage();
		}
		else if (this.isWalking) {
			switch(this.playerDirection) {
			case NORTH: this.currentImage = this.imageStorage.getNorthAnimationStrip()[0];
				break;
			case SOUTH: this.currentImage = this.imageStorage.getSouthAnimationStrip()[0];
				break;
			case EAST: this.currentImage = this.imageStorage.getEastAnimationStrip()[0];
				break;
			case WEST: this.currentImage = this.imageStorage.getWestAnimationStrip()[0];
				break;
			default:
				this.setIdleImage();
			}
		}
	}
	
	private void setIdleImage() {
		this.currentImage = this.imageStorage.getIdleImage(this.playerDirection);
	}
	
	private void initializeWalking() {
		this.playerDirection = EnumDirection.SOUTH;
		this.speed = 0;
		
		for (int i = 0; i < directions.length; i++) {
			directions[i] = false;
		}
		System.out.println(directions[0] + ", " + directions[1] + ", " + directions[2] + ", " + directions[3]);
	}
	
	public boolean isWalking() {
		return this.isWalking;
	}
	
	public void setWalking(boolean b) {
		this.isWalking = b;
		this.updatePlayerImage();
	}
	
	public boolean isNorth() {
		return this.directions[0];
	}
	
	public void setNorth(boolean b) {
		this.directions[0] = b;
		if(this.directions[0])
			this.setDirection(EnumDirection.NORTH);
	}
	
	public boolean isSouth() {
		return this.directions[1];
	}
	
	public void setSouth(boolean b) {
		this.directions[1] = b;
		if(this.directions[1])
			this.setDirection(EnumDirection.SOUTH);
	}
	
	public boolean isEast() {
		return this.directions[2];
	}
	
	public void setEast(boolean b) {
		this.directions[2] = b;
		if (this.directions[2])
			this.setDirection(EnumDirection.EAST);
	}
	
	public boolean isWest() {
		return this.directions[3];
	}
	
	public void setWest(boolean b) {
		this.directions[3] = b;
		if (this.directions[3])
			this.setDirection(EnumDirection.WEST);
	}
	
	public void setCurrentCoordinate(Coordinate c) {
		this.currentCoordinate = c;
	}
	
	public Coordinate getWorldCoordinate() {
		return this.currentCoordinate;
	}
	
	public void setXCenterOffset(int x) {
		this.xCenterOffset = x;
	}
	
	public void setYCenterOffset(int y) {
		this.yCenterOffset = y;
	}
	
	public int getXCenterOffset () {
		return this.xCenterOffset;
	}
	
	public int getYCenterOffset () {
		return this.yCenterOffset;
	}
	
	public void setCurrentSpeed() {
		if (isWalking) {
			if (this.getSpeed() < 5.0F) {
				this.setSpeed(this.getSpeed() + 1.0F);
			}
		}
		/*else if (isRunning) {
			this.setSpeed(8.0F);
		}*/
		else {
			this.setSpeed(0.0F);
		}
	}

	public EntityCharacteristics getPlayerOptions() {
		return this.playerOptions;
	}

	public void setPlayerOptions(EntityCharacteristics playerType) {
		this.playerOptions = playerType;
	}
	
	public EntityImageStorage getImageStorage() {
		return this.imageStorage;
	}
}
