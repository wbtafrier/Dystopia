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
		
		int chunkX;
		int chunkY;
		
		if (x >= 0 && y >= 0) {
			chunkX = x / Chunk.CHUNK_SIZE;
			chunkY = y / Chunk.CHUNK_SIZE;
			quad1.getChunks().get(chunkY).get(chunkX).getCoordinate(x, y).setTile(t);
		}
		else if (x < 0 && y >= 0) {
			chunkX = (Math.abs(x) - 1) / Chunk.CHUNK_SIZE;
			chunkY = y / Chunk.CHUNK_SIZE;
			quad2.getChunks().get(chunkY).get(chunkX).getCoordinate(x, y).setTile(t);
		}
		else if (x < 0 && y < 0) {
			chunkX = (Math.abs(x) - 1) / Chunk.CHUNK_SIZE;
			chunkY = (Math.abs(y) - 1) / Chunk.CHUNK_SIZE;
			quad3.getChunks().get(chunkY).get(chunkX).getCoordinate(x, y).setTile(t);
		}
		else if (x >= 0 && y < 0) {
			chunkX = x / Chunk.CHUNK_SIZE;
			chunkY = (Math.abs(y) - 1) / Chunk.CHUNK_SIZE;
			quad4.getChunks().get(chunkY).get(chunkX).getCoordinate(x, y).setTile(t);
		}
	}
	
//	public Tile getTile(int x, int y) {
//		
//	}
}
