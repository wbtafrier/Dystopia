package com.dreamstone.tile;


public class TileList {
	public static final Tile grass = new TileGrass("grass", "terrain.png", 17);
	public static final TileGrass[] grassDirections = new TileGrass[8];
	
	public static final Tile dirt = new Tile("dirt", "terrain.png", 23);
	
//	public static final Tile test = new Tile("test", "terrain", 257);
}
