//package com.dreamstone.entity;
//
//import java.awt.image.BufferedImage;
//
///**
// * Passive mobs will mind their own business, and if hit by the player or other animal, will flee for their life.
// */
//public abstract class EntityPassive extends EntityMob {
//	
//	private boolean isAlert = false;
//	
//	/**
//	 * Basic constructor for Mob entites. NOTE: If this constructor is used the mob will automatically be tameable, and breedable!
//	 * @param name : The name of the mob.
//	 * @param health : How much health the mob has.
//	 * @param imgName : The image name for the mob.
//	 * @param strength : The strength of the mob.
//	 */
//	EntityPassive(String name, int health, BufferedImage img, int strength, int sight) {
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
//	EntityPassive(String name, int health, BufferedImage img,  int stength, int sight, boolean tameable, boolean breedable) {
//		super(name, health, img, stength, sight, tameable, breedable);
//	}
//	
//	/**
//	 * Returns if the mob fights(true) or flights(false).
//	 * @return True if the mob fights when is attacked.
//	 */
//	public boolean isAlert() {
//		return this.isAlert;
//	}
//	
//	/**
//	 * Sets isAlert.
//	 * @param b : Boolean value if the mob has been hit.
//	 */
//	protected void setAlert(boolean b) {
//		this.isAlert = b;
//	}
//}
