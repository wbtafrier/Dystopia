package com.dreamstone.entity;

import java.awt.image.BufferedImage;

import com.dreamstone.file.EntityImage;

public abstract class EntityMovable extends Entity {

	private float speed;
	private boolean isMoving;
	
	/**
	 * The consructor for the EntityMovale class.
	 * @param name : The name of the entity.
	 * @param health : The health of the entity.
	 * @param img : The image for the entity.
	 */
	EntityMovable(String name, EntityImage images, BufferedImage img, int health) {
		super(name, images, img, health);
		this.speed = 0;
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public boolean isMoving() {
		return this.isMoving;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
//	/**
//	 * This method is meant to be used in different ways for different objects. ie: A wandering sheep, or speeding minecart.
//	 */
//	public abstract void moving();
}
