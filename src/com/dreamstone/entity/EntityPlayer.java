package com.dreamstone.entity;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class EntityPlayer extends EntityMovable {

	private static Random playerRand = new Random();
	private Color hairColor, eyeColor, skinColor;
	private int playerCoordX, playerCoordY;
	
	public EntityPlayer(String name, int health, String imgName, float speed) {
		this(name, 0, 0, health, imgName, speed, new Color(playerRand.nextInt(256), playerRand.nextInt(256), playerRand.nextInt(256)),
				new Color(playerRand.nextInt(256), playerRand.nextInt(256), playerRand.nextInt(256)),
				new Color(playerRand.nextInt(256), playerRand.nextInt(256), playerRand.nextInt(256)));
	}
	
	public EntityPlayer(String name, int health, String imgName, float speed, Color hairColor, Color eyeColor, Color skinColor) {
		this(name, 0, 0, health, imgName, speed, hairColor, eyeColor, skinColor);
	}
	
	public EntityPlayer(String name, int x, int y, int health, String imgName, float speed, Color hairColor, Color eyeColor, Color skinColor) {
		super(name, health, imgName, speed);
		this.playerCoordX = x;
		this.playerCoordY = y;
		this.hairColor = hairColor;
		this.eyeColor = eyeColor;
		this.skinColor = skinColor;
	}
	
	public void setCoords(int x, int y) {
		this.playerCoordX = x;
		this.playerCoordY = y;
	}
	
	public int getX() {
		return this.playerCoordX;
	}
	
	public int getY() {
		return this.playerCoordY;
	}
	
	public Point getCoords() {
		return new Point(this.playerCoordX, this.playerCoordY);
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
