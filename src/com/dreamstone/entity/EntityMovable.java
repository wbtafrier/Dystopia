package com.dreamstone.entity;

public abstract class EntityMovable extends Entity {

	private float speed;
	private boolean isMoving;
	
	/**
	 * The consructor for the EntityMovale class.
	 * @param name : The name of the entity.
	 * @param health : The health of the entity.
	 * @param imgName : The image name of the entity.
	 * @param speed : the speed of the entity.
	 */
	EntityMovable(String name, int health, String imgName, float speed) {
		super(name, health, imgName);
		this.speed = speed;
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
	
	/**
	 * This method is meant to be used in different ways for different objects. ie: A wandering sheep, or speeding minecart.
	 */
	public abstract void moving();
}
