package com.dreamstone.world;

import java.util.ArrayList;

import com.dreamstone.tile.Tile;

public class Grid {
	
	private ArrayList<ArrayList<Coordinate>> map;
	public ArrayList<Quadrant> QUADRANTS;
	private Quadrant quad1;
	private Quadrant quad2;
	private Quadrant quad3;
	private Quadrant quad4;
	
	
	public Grid() {
		
		QUADRANTS = new ArrayList<>();
		initializeMap();
		
		quad1.growQuadrant(new Chunk(100, 100));
		
		/*quad2.growQuadrant(new Chunk(-2, 1));
		quad3.growQuadrant(new Chunk(-2, -1));
		quad4.growQuadrant(new Chunk(2, -2));*/
		
		System.out.println(quad1);
		System.out.println(quad2);
		System.out.println(quad3);
		System.out.println(quad4);
	}
	
	private void initializeMap() {

		//Chunk[] starting = {new Chunk(0, 0), new Chunk(-1, 1), new Chunk(-1, -1), new Chunk(1, -1)};
		
		QUADRANTS.add(new Quadrant(new Chunk(0, 0)));
		QUADRANTS.add(new Quadrant(new Chunk(-1, 1)));
		QUADRANTS.add(new Quadrant(new Chunk(-1, -1)));
		QUADRANTS.add(new Quadrant(new Chunk(1, -1)));
		
		quad1 = QUADRANTS.get(0);
		quad2 = QUADRANTS.get(1);
		quad3 = QUADRANTS.get(2);
		quad4 = QUADRANTS.get(3);
		
		map = new ArrayList<>();
	}
	
	private void addQuadrantsToMap() {
		
	}
//	
//	/**
//	 * Prints chunks for testing. Should only be used be the growMap() method.
//	 * @param chunks : Array of chunks to print.
//	 */
//	private void printChunks(Chunk... chunks) {
//		String s = "fail";
//
//		for (int i = 0; i < chunks.length; i++) {
//			for (int y = 0; y < Chunk.CHUNK_SIZE; y++) {
//				for (int x = 0; x < Chunk.CHUNK_SIZE; x++) {
//					Coordinate coords = chunks[i].getCoords()[x][y];
//
//					s = coords.xCoordinate >= 0 && coords.yCoordinate >= 0 ? "QUAD 1" :
//					coords.xCoordinate < 0 && coords.yCoordinate >= 0 ? "QUAD 2" :
//					coords.xCoordinate < 0 && coords.yCoordinate < 0 ? "QUAD 3" :
//					coords.xCoordinate >= 0 && coords.yCoordinate < 0 ? "QUAD 4" :
//						"ERROR"; 
//
//					if (y == 0 && x == 0) {
//						System.out.println("\n" + s);
//					}
//
//					System.out.print(coords);
//				}
//			}
//		}
//	}
	
	public void setTile(int x, int y, Tile t) {
		
	}
	
//	public Tile getTile(int x, int y) {
//		
//	}
	
//	private int getWidth() {
//		
//	}
	
	public ArrayList<ArrayList<Coordinate>> getMap() {
		return this.map;
	}
	
//	private int convertCoordToLinear(int x, int y) {
//		
//	}
}
