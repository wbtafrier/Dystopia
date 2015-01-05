package com.dreamstone.entity;

public abstract class EntityCitizen extends EntityMovable {

	private EnumNPCAge age;
	private EnumNPCGender gender;
	private EnumNPCCareer career;
	private CitizenInventory inventory;
	private int eyeColor;
	private int hairColor;
	private int hairStyle;
	private int skinColor;
	
	EntityCitizen(String name, int health, String imgName, float speed, EnumNPCAge age, EnumNPCGender sex, EnumNPCCareer job) {
		super(name, health, imgName, speed);
		this.age = age;
		this.gender = sex;
		this.career = job;
		//Create new inventory for the citizen based on the type of citizen they are.
		//Randomly pick an eye color from the eye color generator
		//Randomly pick a hair color and type from the hair color generator
		//Randomly pick a skin color from the skin color generator
	}
}
