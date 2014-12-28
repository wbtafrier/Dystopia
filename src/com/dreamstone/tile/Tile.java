package com.dreamstone.tile;

public class Tile {
	
	private String name;
	
	public Tile(String n) {
		name = n;
	}
	
	protected void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
