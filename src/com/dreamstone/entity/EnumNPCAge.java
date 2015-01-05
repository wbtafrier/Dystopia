package com.dreamstone.entity;

/**
 * Contains the different types of citizens.
 */
public enum EnumNPCAge {
	
	//An NPC's live span lives for a total of 5 real days (120 hours). <--- RANDOMIZE THIS SO EACH NPC LIVES FOR DIFFERENT LENGTHS
	//IF A DAY LASTS 30 MINUTES LONG, AN NPC SPENDS...
	CHILD(24), 							// 12 Hours as a child
	TEEN(28),							// 14 Hours as a teen
	ADULT(44),							// 22 Hours as an adult
	ELDER(24);							// 12 Hours as an elder
	
	public final int DAYS_OLD;
	
	EnumNPCAge(int days) {
		DAYS_OLD = days;
	}
}
