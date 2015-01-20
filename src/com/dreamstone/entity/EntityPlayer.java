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
	private boolean isNorth;
	private boolean isSouth;
	private boolean isEast;
	private boolean isWest;
	
	private int xWalkingBoundsLocation;
	private int yWalkingBoundsLocation;
	
	private Coordinate currentCoordinate;
	private EnumDirection playerDirection;
	private EntityCharacteristics playerOptions;
	
	public EntityPlayer(String name) {
		this(name, 0, 0);
	}
	
	public EntityPlayer(String name, int xWalkingScreenPos, int yWalkingScreenPos) {
		this(name, xWalkingScreenPos, yWalkingScreenPos, 100);
	}
	
	public EntityPlayer(String name, int xWalkingScreenPos, int yWalkingScreenPos, int health) {
		this(name, xWalkingScreenPos, yWalkingScreenPos, 100, new EntityCharacteristics());
	}
	
	public EntityPlayer(String name, int xWalkingScreenPos, int yWalkingScreenPos, int health, EntityCharacteristics playerType) {
		super(name, health);
		this.initializeWalking();
		this.setDirection(EnumDirection.SOUTH);
		
		this.setWalkingBoundsXPos(xWalkingScreenPos);
		this.setWalkingBoundsYPos(yWalkingScreenPos);
		this.playerOptions = playerType;
//		this.imageStorage.updateImages(this.playerOptions);
	}
	
	public void setDirection(EnumDirection dir) {
		this.playerDirection = dir;
		this.updatePlayerImage();
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
		this.isNorth = false;
		this.isSouth = true;
		this.isEast = false;
		this.isWest = false;
	}
	
	public boolean isWalking() {
		return this.isWalking;
	}
	
	public void setWalking(boolean b) {
		this.isWalking = b;
		this.updatePlayerImage();
	}
	
	public boolean isNorth() {
		return this.isNorth;
	}
	
	public void setNorth(boolean b) {
		this.isNorth = b;
		if (this.isNorth)
			this.setDirection(EnumDirection.NORTH);
	}
	
	public boolean isSouth() {
		return this.isSouth;
	}
	
	public void setSouth(boolean b) {
		this.isSouth = b;
		if(this.isSouth)
			this.setDirection(EnumDirection.SOUTH);
	}
	
	public boolean isEast() {
		return this.isEast;
	}
	
	public void setEast(boolean b) {
		this.isEast = b;
		if (this.isEast)
			this.setDirection(EnumDirection.EAST);
	}
	
	public boolean isWest() {
		return this.isWest;
	}
	
	public void setWest(boolean b) {
		this.isWest = b;
		if (this.isWest)
			this.setDirection(EnumDirection.WEST);
	}
	
	public void setCurrentCoordinate(Coordinate c) {
		this.currentCoordinate = c;
	}
	
	public Coordinate getWorldCoordinate() {
		return this.currentCoordinate;
	}
	
	public int getWalkingBoundsXPos() {
		return this.xWalkingBoundsLocation + this.getImage().getWidth() / 2;
	}
	
	public int getWalkingBoundsYPos() {
		return this.yWalkingBoundsLocation + this.currentImage.getHeight();
	}
	
	public int getPlayerOriginX() {
		return this.xWalkingBoundsLocation;
	}
	
	public int getPlayerOriginY() {
		return this.yWalkingBoundsLocation;
	}
	
	public void setWalkingBoundsXPos(int x) {
		this.xWalkingBoundsLocation = x - this.currentImage.getWidth() / 2;
	}
	
	public void setWalkingBoundsYPos(int y) {
		this.yWalkingBoundsLocation = y - this.currentImage.getHeight();
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
