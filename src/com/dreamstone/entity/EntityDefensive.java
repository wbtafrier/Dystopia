//package com.dreamstone.entity;
//
//import java.awt.image.BufferedImage;
//
///**
// * Defensive Mobs are peaceful at first, but if they are hit or provoked by a player or other animal, they will fight.
// */
//public abstract class EntityDefensive extends EntityMob {
//
//	private boolean isProvoked = false;
//	
//	/**
//	 * Basic constructor for Defensive entites. NOTE: If this constructor is used the mob will automatically be tameable, and breedable!
//	 * @param name : The name of the mob.
//	 * @param health : How much health the mob has.
//	 * @param imgName : The image name for the mob.
//	 * @param strength : The strength of the mob.
//	 */
//	EntityDefensive(String name, int health, BufferedImage img, int strength, int sight) {
//		super(name, health, img, strength, sight);
//	}
//	
//	/**
//	 * The constructor for peaceful mobs.
//	 * @param name : The name of the mob.
//	 * @param health : How much health the mob has.
//	 * @param imgName : The image name for the mob.
//	 * @param stength : The strength of the mob.
//	 * @param tameable : Is the mob tameable?
//	 * @param breedable : Is the mob breedable? (The mob must also be tameable for this to work).
//	 */
//	EntityDefensive(String name, int health, BufferedImage img, int stength, int sight, boolean tameable, boolean breedable) {
//		super(name, health, img, stength, sight, tameable, breedable);
//	}
//	
//	/**
//	 * Returns if the mob has been provoked and in attack mode.
//	 * @return True if the mob is in attack mode.
//	 */
//	public boolean isProvoked() {
//		return this.isProvoked;
//	}
//	
//	/**
//	 * Sets isDefensive.
//	 * @param b : Boolean value if the mob fights or runs away.
//	 */
//	protected void setProvoked(boolean b) {
//		this.isProvoked = b;
//	}
//}
