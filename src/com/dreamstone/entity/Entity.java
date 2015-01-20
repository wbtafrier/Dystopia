package com.dreamstone.entity;

import java.awt.image.BufferedImage;

/**
 * The superclass for all entities.
 */
public abstract class Entity {
	
	protected String entityName;
	protected BufferedImage currentImage;
	protected int health;
	
	/**
	 * The main constructor for all entities.
	 * @param name : The name of the entity.
	 * @param health : The amount of health the entity has.
	 * @param defaultImage : The BufferedImage for the entity.
	 */
	Entity(String name, int health) {
		this.entityName = name;
		this.health = health;
	}
	
	/**
	 * Returns the name of the entity.
	 * @return Entity name.
	 */
	public String getName() {
		return this.entityName;
	}
	
	/**
	 * Returns the image name of the entity.
	 * @return Entity image name.
	 */
	public BufferedImage getImage() {
		return this.currentImage;
	}
	
	/**
	 * Returns the health of the entity.
	 * @return Entity health.
	 */
	public int getHealth() {
		return this.health;
	}
	
	/**
	 * Sets the name of the entity.
	 * @param name : Name of entity.
	 */
	public void setName(String name) {
		this.entityName = name;
	}
	
	/**
	 * Sets the image for the entity.
	 * @param img
	 */
	public void setImage(BufferedImage img) {
		this.currentImage = img;
	}
	
	/**
	 * Sets the health of the entity.
	 * @param amount : The health of the entity.
	 */
	public void setHealth(int amount) {
		this.health = amount;
	}
	
	/**
	 * Returns the name of the entity.
	 */
	public String toString() {
		return this.entityName;
	}
}
