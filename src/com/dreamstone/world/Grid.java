package com.dreamstone.world;

import java.util.ArrayList;

import com.dreamstone.tile.Tile;

public class Grid {
	
	private ArrayList<Coordinate> map = null;
	private int horizontalChunks;
	private int verticalChunks;
	
	public Grid() {
		map = new ArrayList<>();
		//growMap(new Chunk(10, 0));
		growMap(new Chunk(-10, 0));
	}
	
	private void growMap(Chunk c) {
		
		for (Coordinate coord : c.getChunk()) {
			map.add(coord);
		}
		
		System.out.println(map);
	}
	
	public void setTile(int x, int y, Tile t) {
		int index = convertCoordToLinear(x, y);
	}
	
	public Tile getTile(int x, int y) {
		return map.get(convertCoordToLinear(x, y)).getTile();
	}
	
	private int getWidth() {
		return horizontalChunks * Chunk.CHUNK_SIZE;
	}
	
	private int getHeight() {
		return verticalChunks * Chunk.CHUNK_SIZE;
	}
	
	private int convertCoordToLinear(int x, int y) {
		return x + (y * this.getWidth());
	}
}
