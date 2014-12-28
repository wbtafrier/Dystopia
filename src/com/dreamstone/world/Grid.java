package com.dreamstone.world;

import java.util.ArrayList;

import com.dreamstone.tile.Tile;

public class Grid {
	
	private ArrayList<Coordinate> map = null;
	private int horizontalChunks;
	private int verticalChunks;
	
	public Grid() {
		map = new ArrayList<>();
		this.initializeMap();
		//growMap(new Chunk(10, 0));
		growMap(new Chunk(8, 8));
	}
	
	public ArrayList<Coordinate> getMap() {
		return this.map;
	}

	private void initializeMap() {
		if (map.size() == 0) {
			Chunk c = new Chunk(0, 0);
			for (Coordinate coord : c.getCoords()) {
				map.add(coord);
			}
		}
	}
	
	private boolean checkChunk(Chunk c) {
		if (this.map.size() == 0) {
			return true;
		}
		
		for (Coordinate coord : c.getCoords()) {
//			if (index < 0) {
//				for (int i = 0; i > index; i--) {
//					map.add(0, null);
//				}
//			}
			boolean f = coord.equals(map.get(this.convertCoordToLinear(coord.xCoordinate, coord.yCoordinate)));
//			System.out.println(f);
			if (f) {
				return false;
			}
		}
		return true;
	}
	
	private void growMap(Chunk c) {
		if (!this.checkChunk(c)) {
			return;
		}
		
		Coordinate firstCoord = map.get(0);
		Coordinate lastCoord = map.get(map.size() - 1);
		
		for (Coordinate coord : c.getCoords()) {
			map.add(this.convertCoordToLinear(coord.xCoordinate, coord.yCoordinate), coord);
		}
		
		for (int x = firstCoord.xCoordinate; x < lastCoord.xCoordinate; x++) {
			for (int y = firstCoord.yCoordinate; y < lastCoord.yCoordinate; y++) {
//				Coordinate current = new Coordinate(x, y);
				if (map.get(this.convertCoordToLinear(x, y)) == null) {
					Chunk chunk = new Chunk(x, y);
					for (Coordinate coord : chunk.getCoords()) {
						map.add(this.convertCoordToLinear(x, y), coord);
					}
					x += Chunk.CHUNK_SIZE;
					y += Chunk.CHUNK_SIZE;
				}
			}
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
