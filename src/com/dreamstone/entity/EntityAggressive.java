package com.dreamstone.entity;

public abstract class EntityAggressive extends EntityMob {

	/**
	 * Basic constructor for Mob entites. NOTE: If this constructor is used the mob will automatically be tameable and breedable!
	 * @param name : The name of the mob.
	 * @param health : How much health the mob has.
	 * @param imgName : The image name for the mob.
	 * @param speed : The speed of the mob.
	 * @param strength : The strength of the mob.
	 */
	EntityAggressive(String name, int health, String imgName, float speed, int strength, int sight) {
		super(name, health, imgName, speed, strength, sight);
	}
	
	/**
	 * Full constructor for Mob entities. NOTE: For a mob to be breedable it must ALSO be tameable!
	 * @param name : The name of the mob.
	 * @param health : The health of the mob.
	 * @param imgName : The image name for the mob.
	 * @param speed : The speed of the mob.
	 * @param strength : The strength of the mob.
	 * @param tameable : Is the mob tameable?
	 * @param breedable : Is the mob breedable? (The mob must also be tameable for this to work).
	 */
	EntityAggressive(String name, int health, String imgName, float speed, int strength, int sight, boolean tameable, boolean breedable) {
		super(name, health, imgName, speed, strength, sight, tameable, breedable);
	}
}
