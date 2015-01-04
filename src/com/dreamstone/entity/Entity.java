package com.dreamstone.entity;

/**
 * The superclass for all entities.
 */
public abstract class Entity {
	
	private String entityName;
	private String imageName;
	private int health;
	
	Entity(String name, int health) {
		this.entityName = name;
		this.health = health;
	}
	
	/**
	 * The main constructor for all entities.
	 * @param name : The name of the entity.
	 * @param health : The amount of health the entity has.
	 * @param imgName : The name of the image for the entity.
	 */
	Entity(String name, int health, String imgName) {
		this.entityName = name;
		this.health = health;
		this.setImageName(imgName);
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
	public String getImageName() {
		return this.imageName;
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
	 * Sets the image name of the entity.
	 * @param imgName
	 */
	public void setImageName(String imgName) {
		this.imageName = imgName + ".png";
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
