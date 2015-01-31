package com.dreamstone.entity;



public abstract class EntityMovable extends Entity {

	protected float speed;
	protected boolean isMoving;
	
	/**
	 * The consructor for the EntityMovale class.
	 * @param name : The name of the entity.
	 * @param health : The health of the entity.
	 * @param img : The image for the entity.
	 */
	EntityMovable(String name, int health) {
		super(name, health);
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
	
	public abstract void setCurrentSpeed();
}
