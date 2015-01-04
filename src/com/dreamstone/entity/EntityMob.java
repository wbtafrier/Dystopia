package com.dreamstone.entity;

public abstract class EntityMob extends EntityMovable {

	private int strength;
	private int viewDistance;
	private boolean isTameable;
	private boolean isBreedable;
	
	/**
	 * Basic constructor for Mob entites. NOTE: If this constructor is used the mob will automatically be tameable and breedable!
	 * @param name : The name of the mob.
	 * @param health : How much health the mob has.
	 * @param imgName : The image name for the mob.
	 * @param speed : The speed of the mob.
	 * @param strength : The strength of the mob.
	 * @param sight : The view distance radius the mob has (IN TILES). If the player gets in the view distance, the mob will attack.
	 */
	EntityMob(String name, int health, String imgName, float speed, int strength, int sight) {
		super(name, health, imgName, speed);
		this.strength = strength;
		this.viewDistance = sight;
		this.isTameable = true;
		this.isBreedable = true;
	}
	
	/**
	 * Full constructor for Mob entities. NOTE: For a mob to be breedable it must ALSO be tameable!
	 * @param name : The name of the mob.
	 * @param health : The health of the mob.
	 * @param imgName : The image name for the mob.
	 * @param speed : The speed of the mob.
	 * @param strength : The strength of the mob.
	 * @param sight : The view distance radius the mob has (IN TILES). If the player gets in the view distance, the mob will attack.
	 * @param tameable : Is the mob tameable?
	 * @param breedable : Is the mob breedable? (The mob must also be tameable for this to work).
	 */
	EntityMob(String name, int health, String imgName, float speed, int strength, int sight, boolean tameable, boolean breedable) {
		super(name, health, imgName, speed);
		this.strength = strength;
		this.viewDistance = sight;
		this.isTameable = tameable;
		this.setBreedable(breedable);
	}
	
	/**
	 * Returns the strength of the mob.
	 * @return : The strength.
	 */
	public int getStrength() {
		return this.strength;
	}
	
	/**
	 * Gets the view distance for the mob.
	 * @return The view distance of the mob.
	 */
	public int getViewDistance() {
		return this.viewDistance;
	}
	
	/**
	 * Returns true if the mob is tameable.
	 * @return A boolean value if the mob is tameable or not.
	 */
	public boolean isTameable() {
		return this.isTameable;
	}
	
	/**
	 * Returns true if the mob is breedable.
	 * @return A boolean value if the mob is breedable or not.
	 */
	public boolean isBreedable() {
		return this.isBreedable;
	}
	
	/**
	 * Sets the strength of the mob to s.
	 * @param s : An integer value sent in.
	 */
	public void setStrength(int s) {
		this.strength = s;
	}
	
	/**
	 * Sets the view distance for the mob.
	 * @param sight : The view distance of the mob.
	 */
	public void setViewDistance(int sight) {
		this.viewDistance = sight;
	}
	
	/**
	 * Sets the boolean value of isTameable to the boolean value sent in.
	 * @param b : The boolean value sent in.
	 */
	public void setTameable(boolean b) {
		this.isTameable = b;
	}
	
	/**
	 * Checks to see if the mob is tameable before setting the breedability to true.
	 * @param b The boolean value for breeding being sent in.
	 */
	private void setBreedable(boolean b) {
		if (this.isTameable && b) {
			this.isBreedable = true;
		}
	}
}
