package com.dreamstone.entity;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.dreamstone.file.EntityImage;
import com.dreamstone.tile.EnumDirection;
import com.dreamstone.world.Coordinate;

public class EntityPlayer extends EntityMovable {
	
	private static Random rand = new Random();
	private boolean isMovingNorth;
	private boolean isMovingSouth;
	private boolean isMovingEast;
	private boolean isMovingWest;
	
	private Color hairColor;
	private Color eyeColor;
	private Color skinColor;
	
	private int xWalkingBoundsLocation;
	private int yWalkingBoundsLocation;
	
	private Coordinate currentCoordinate;
	
	public EntityPlayer(String name, EntityImage images) {
		this(name, images, images.getIdleImage(EnumDirection.SOUTH));
	}
	
	public EntityPlayer(String name, EntityImage images, BufferedImage defaultImage) {
		this(name, images, defaultImage, 0, 0);
	}
	
	public EntityPlayer(String name, EntityImage images, BufferedImage defaultImage, int xWalkingScreenPos, int yWalkingScreenPos) {
		this(name, images, defaultImage, xWalkingScreenPos, yWalkingScreenPos, 100, new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)), 
		new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)), new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
	}
	
	public EntityPlayer(String name, EntityImage images, BufferedImage defaultImage, int xWalkingScreenPos, int yWalkingScreenPos, int health, Color hairColor, Color eyeColor, Color skinColor) {
		super(name, images, defaultImage, health);
		
		this.setWalkingBoundsXPos(xWalkingScreenPos);
		this.setWalkingBoundsYPos(yWalkingScreenPos);
		this.isMovingNorth = false;
		this.isMovingSouth = false;
		this.isMovingEast = false;
		this.isMovingWest = false;
		
		this.hairColor = hairColor;
		this.eyeColor = eyeColor;
		this.skinColor = skinColor;
	}
	
	public void setDirection(EnumDirection dir) {
		switch(dir) {
		case NORTH: this.setImage(this.imageStorage.getIdleImage(dir));
		break;
		case SOUTH: this.setImage(this.imageStorage.getIdleImage(dir));
		break;
		case EAST: this.setImage(this.imageStorage.getIdleImage(dir));
		break;
		case WEST: this.setImage(this.imageStorage.getIdleImage(dir));
		break;
		default:
			break;
		}
	}
	
	public boolean isMovingNorth() {
		return this.isMovingNorth;
	}
	
	public void setMovingNorth(boolean b) {
		this.isMovingNorth = b;
	}
	
	public boolean isMovingSouth() {
		return this.isMovingSouth;
	}
	
	public void setMovingSouth(boolean b) {
		this.isMovingSouth = b;
	}
	
	public boolean isMovingEast() {
		return this.isMovingEast;
	}
	
	public void setMovingEast(boolean b) {
		this.isMovingEast = b;
	}
	
	public boolean isMovingWest() {
		return this.isMovingWest;
	}
	
	public void setMovingWest(boolean b) {
		this.isMovingWest = b;
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
		this.xWalkingBoundsLocation = x - this.getImage().getWidth() / 2;
	}
	
	public void setWalkingBoundsYPos(int y) {
		this.yWalkingBoundsLocation = y - this.currentImage.getHeight();
	}
 	
	public Color getHairColor() {
		return this.hairColor;
	}
	
	public Color getEyeColor() {
		return this.eyeColor;
	}

	public Color getSkinColor() {
		return this.skinColor;
	}
	
	public void setHairColor(Color c) {
		this.hairColor = c;
	}
	
	public void setHairColor(int r, int g, int b) {
		this.hairColor = new Color(r, g, b);
	}
	
	public void setEyeColor(Color c) {
		this.eyeColor = c;
	}
	
	public void setEyeColor(int r, int g, int b) {
		this.eyeColor = new Color(r, g, b);
	}
	
	public void setSkinColor(Color c) {
		this.skinColor = c;
	}
	
	public void setSkinColor(int r, int g, int b) {
		this.skinColor = new Color(r, g, b);
	}
}
