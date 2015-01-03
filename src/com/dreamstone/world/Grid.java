package com.dreamstone.world;

import java.util.ArrayList;

import com.dreamstone.tile.Tile;

public class Grid {
	
	public ArrayList<Quadrant> QUADRANTS;
	private Quadrant quad1;
	private Quadrant quad2;
	private Quadrant quad3;
	private Quadrant quad4;
	
	public Grid() {
		
		QUADRANTS = new ArrayList<>();
		initializeMap();
		
		for (int i = 0; i < 6; i++) {
			quad1.growQuadrant(new Chunk(quad1.getChunks().size(), quad1.getChunks().size()));
			quad2.growQuadrant(new Chunk(-(quad2.getChunks().size() + 1), quad2.getChunks().size() + 1));
			quad3.growQuadrant(new Chunk(-(quad3.getChunks().size() + 1), -(quad3.getChunks().size() + 1)));
			quad4.growQuadrant(new Chunk((quad4.getChunks().size() + 1), -(quad4.getChunks().size() + 1)));
		}
		
		System.out.println(quad1);
		System.out.println(quad2);
		System.out.println(quad3);
		System.out.println(quad4);
	}
	
	private void initializeMap() {
		
		QUADRANTS.add(new Quadrant(new Chunk(0, 0)));
		QUADRANTS.add(new Quadrant(new Chunk(-1, 1)));
		QUADRANTS.add(new Quadrant(new Chunk(-1, -1)));
		QUADRANTS.add(new Quadrant(new Chunk(1, -1)));
		
		quad1 = QUADRANTS.get(0);
		quad2 = QUADRANTS.get(1);
		quad3 = QUADRANTS.get(2);
		quad4 = QUADRANTS.get(3);
	}
	
	public void setTile(int x, int y, Tile t) {
		
	}
	
//	public Tile getTile(int x, int y) {
//		
//	}
}
