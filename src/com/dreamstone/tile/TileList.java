package com.dreamstone.tile;

import java.util.ArrayList;


public class TileList {
	
	public static final ArrayList<Tile> tiles = new ArrayList<>();
	
	public static Tile nullTile = new TileNull("null");
	public static Tile normalGrassTile = new TileGrass("grass", "terrain.png");
	public static Tile normalDirtTile = new TileDirt("dirt", "terrain.png");
	
	public static void registerTiles() {
		tiles.add(nullTile);
		tiles.add(normalGrassTile);
		tiles.add(normalDirtTile);
	}
		
	public static Tile getTileFromString(String name) {
		for (Tile t : tiles) {
			if (t.getName().equalsIgnoreCase(name)) {
				return t;
			}
		}
		return nullTile;
	}
	
//	public static final Tile dirt1 = new Tile("dirt", "terrain.png", 23);
//	public static final Tile dirt2 = new Tile("dirt", "terrain.png", 26);
}
