package com.dreamstone.entity;

import java.awt.Color;

import com.dreamstone.util.RandomEngine;

public class EntityCharacteristics {

	private static RandomEngine rand = new RandomEngine();
	
	private Color hairColor;
	private Color eyeColor;
	private Color skinColor;
//	private Clothing clothingType;
//	private Armor armorType;
	
	public EntityCharacteristics() {
		this(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
	}
	
	public EntityCharacteristics(Color hair) {
		this(hair, new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
	}
	
	public EntityCharacteristics(Color hair, Color eye) {
		this(hair, eye, createRandomSkinTone());
	}
	
	public EntityCharacteristics(Color hair, Color eye, Color skin) {
		this.hairColor = hair;
		this.eyeColor = eye;
		this.skinColor = skin;
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
	
	public void setSkinTone(Color c) {
		this.skinColor = c;
	}
	
	public void setSkinTone(int r, int g, int b) {
		this.skinColor = new Color(r, g, b);
	}
	
	public static Color createRandomSkinTone() {
		
		int r = rand.nextInt(255 - 30 + 1) + 30;
		
		//Red to green percentage
		double range = (1.0 - 0.75);     
		double percentage = (Math.random() * range) + 0.75;
		
		int g = (int)(r * percentage);
		
		//Red to blue percentage
		range = (1.0 - 0.70);     
		percentage = (Math.random() * range) + 0.6;
		
		int b = (int)(r * percentage);
		return new Color(r, g, b);
	}
}
