package com.dreamstone.entity;

/**
 * Contains the different types of citizens.
 */
public enum EnumNPCAge {
	
	//TODO: CHANGE ALL THE AGE VALUES.
	CHILD(100), 
	TEEN(100),
	ADULT(100),
	ELDER(100);
	
	public final int DAYS_OLD;
	
	EnumNPCAge(int days) {
		DAYS_OLD = days;
	}
}
