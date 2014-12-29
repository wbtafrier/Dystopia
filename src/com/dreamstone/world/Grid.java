package com.dreamstone.world;

import java.util.ArrayList;

public class Grid {
	
	private ArrayList<Quadrant> gameMap = null;
	
	public Grid() {
		gameMap = new ArrayList<>();
		this.initializeMap();
	}

	private void initializeMap() {
		gameMap.add(new Quadrant(new Chunk(0, 0)));
		gameMap.get(0).growMap(new Chunk(8, 8));
		gameMap.get(0).growMap(new Chunk(24, 24));
	}
//	
//	private void growMap(Chunk c) {
//		
////		Coordinate lastCoord = c.getCoords()[Chunk.CHUNK_SIZE * Chunk.CHUNK_SIZE - 1];
//		
//		/*if (lastCoord.xCoordinate < 0 || lastCoord.yCoordinate < 0) {
//			Coordinate firstCoord = c.getCoords()[0];
//			
//			for (int x = firstCoord.xCoordinate; x <= 0; x++) {
//				for (int y = firstCoord.yCoordinate; y <= 0; y++) {
//					
//					map.add(0, new Coordinate(firstCoord.xCoordinate - (x + 1), firstCoord.yCoordinate - (y + 1)));
//					
//					//System.out.println("(" + x + ", " + y + ")");
//				}
//			}
//			
//			for (int x = map.get(0).xCoordinate; x < 0; x++) {
//				for (int y = map.get(0).yCoordinate; y < 0; y++) {
//					
//					Chunk genChunk = new Chunk(x, y);
//					if (this.isChunkCreated(genChunk)) {
//						continue;
//					}
//					else {
//						for (Coordinate coord : genChunk.getCoords()) {
//							System.out.println(map.get(this.convertCoordToLinear(x, y)));
//							map.set(this.convertCoordToLinear(x, y), coord);
//						}
//					}
//				}
//			}
//		}
//		else {*/
//			for (int x = 0; x < lastCoord.xCoordinate; x++) {
//				for (int y = 0; y < lastCoord.yCoordinate; y++) {
//					
//					Chunk genChunk = new Chunk(x, y);
//					if (this.isChunkCreated(genChunk)) {
//						continue;
//					}
//					else {
//						for (Coordinate coord : genChunk.getCoords()) {
//							map.add(this.convertCoordToLinear(x, y), coord);
//						}
//					}
//				}
//			}
//		//}
//		System.out.println(map);
//	}
	
//	private boolean isChunkCreated(Chunk c) {
//		if (this.map.size() == 0) {
//			return false;
//		}
//		
//		for (Coordinate coord : c.getCoords()) {
//			
//			System.out.println(this.convertCoordToLinear(coord.xCoordinate, coord.yCoordinate));
//			boolean f = coord.equals(map.get(this.convertCoordToLinear(coord.xCoordinate, coord.yCoordinate)));
////			System.out.println(f);
//			if (f) {
//				return true;
//			}
//		}
//		return false;
//	}
	
//	public void setTile(int x, int y, Tile t) {
//		int index = convertCoordToLinear(x, y);
//	}
	
//	public Tile getTile(int x, int y) {
//		return map.get(convertCoordToLinear(x, y)).getTile();
//	}
//	
//	private int getWidth() {
//		return 0;
//	}
	
	public ArrayList<Quadrant> getGameMap() {
		return this.gameMap;
	}
	
//	private int convertCoordToLinear(int x, int y) {
		
		/*if (x < 0 || y < 0) {
			if (map.get(0).equals(new Coordinate(x, y))) {
				return 0;
			}
			else {
				int index = 0;
				for (Coordinate coord : map) {
					if (coord.equals(new Coordinate(x, y))) {
						return index;
					}
					else {
						index++;
					}
				}
			}
		}*/
//		return x + (y * this.getWidth()); 
//	}
}
