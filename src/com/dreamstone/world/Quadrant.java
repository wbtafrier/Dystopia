package com.dreamstone.world;

import java.util.ArrayList;

import com.dreamstone.util.DystopiaLogger;

public class Quadrant {

	private ArrayList<ArrayList<Coordinate>> map = new ArrayList<>();
	private Chunk startingChunk = null;
	private int quadrantNum = 0;
	
	Quadrant(Chunk startChunk) {
		this.startingChunk = startChunk;
		
		Coordinate start = this.getStartingCoordinate();
		if (start.xCoordinate == 0 && start.yCoordinate == 0) {
			this.quadrantNum = 1;
		}
		else if (start.xCoordinate == -1 && start.yCoordinate == 0) {
			this.quadrantNum = 2;
		}
		else if (start.xCoordinate == 0 && start.yCoordinate == -1) {
			this.quadrantNum = 3;
		}
		else if (start.xCoordinate == -1 && start.yCoordinate == -1) {
			this.quadrantNum = 4;
		}
		else if (this.quadrantNum == 0) {
			DystopiaLogger.logWarning("Quadrant cannot be assigned a number! Is the starting coordinate >= -1 and <= 1?");
		}
		
		this.initializeMap();
	}
	
	private void initializeMap() {
		this.addChunk(this.startingChunk);
	}
	
	/**
	 * Adds a chunk to this quadrant, and fills in other chunks with empty coordinates if necessary.
	 * @param chunk The chunk to be added to the map. Must have starting coordinates evenly divisible by 8. Must not already exist in quadrant.
	 * @return true if the chunk was successfully added to this quadrant. False if not, with a message sent to console to explain what went wrong.
	 */
	public boolean growMap(Chunk chunk) {		
		if (this.map.size() == 0) {
			DystopiaLogger.logWarning("Cannot grow quadrant " + this.getQuadrantNum() + " until it is initialized.");
			return false;
		}
		
		Coordinate startCoord = chunk.getStartingCoord();
		
		if (Math.abs(startCoord.xCoordinate) % Chunk.CHUNK_SIZE != 0 || Math.abs(startCoord.yCoordinate) % Chunk.CHUNK_SIZE != 0) {
			DystopiaLogger.logWarning("Cannot grow quadrant " + this.getQuadrantNum() + ", starting coordinate (" +
					startCoord.xCoordinate + ", " + startCoord.yCoordinate + ") is not divisible by " + Chunk.CHUNK_SIZE + ".");
			return false;
		}
		
		if (this.getQuadrantNum() == 1) {
			if (startCoord.xCoordinate < this.getStartingCoordinate().xCoordinate 
					|| startCoord.yCoordinate < this.getStartingCoordinate().yCoordinate) {
				DystopiaLogger.logWarning("Cannot grow quadrant " + this.getQuadrantNum() + ", starting point (" +
					startCoord.xCoordinate + ", " + startCoord.yCoordinate + ") is outside of quadrant.");
				return false;
			}
		}
		
		for (int x = startCoord.xCoordinate; x < Chunk.CHUNK_SIZE; x++) {
			for (int y = startCoord.yCoordinate; y < Chunk.CHUNK_SIZE; y++) {
				Coordinate current = this.getCoord(chunk.getCoordinate(x, y));
				if (current != null /* current != null && current.getTile() != null */) {
					DystopiaLogger.logWarning("Cannot grow quadrant " + this.getQuadrantNum() + ", as part of quadrant has already been generated.");
					return false;
				}
			}
		}
		
		if (this.getQuadrantNum() == 1) {
//			int missingChunksX = (startCoord.xCoordinate / Chunk.CHUNK_SIZE) - (this.map.size() / Chunk.CHUNK_SIZE - 1);
//			int missingChunksY = (startCoord.yCoordinate / Chunk.CHUNK_SIZE) - (this.map.get(0).size() / Chunk.CHUNK_SIZE - 1);
//			DystopiaLogger.logDebug("Missing X chunks: " + missingChunksX + " | Missing Y chunks: " + missingChunksY +
//					"\nMap size: " + this.map.size() + " | Column size: " + this.map.get(0).size());
			
//			for (int x = 0; x < missingChunksX; x++) {
//				for (int y = 0; y < missingChunksY; y++) {
//					if (this.getCoord(this.map.size(), this.map.get(0).size()) == null) {
//						this.addChunk(new Chunk(this.map.size(), this.map.get(0).size()));
//					}
//				}
//			}
			int xSize = map.size();
			
			Coordinate temp = this.getStartingCoordinate();
			int counter = 0;
			int ySize = 0;
			
			while (this.getCoord(temp) != null) {
				counter++;
				temp = new Coordinate(temp.xCoordinate, temp.yCoordinate + counter);
			}
			
			ySize = temp.yCoordinate + counter;
			
			for (int x = xSize / Chunk.CHUNK_SIZE; x < (xSize + (startCoord.xCoordinate + Chunk.CHUNK_SIZE)) / Chunk.CHUNK_SIZE; x += Chunk.CHUNK_SIZE) {
				for (int y = ySize / Chunk.CHUNK_SIZE; y < (ySize + (startCoord.yCoordinate + Chunk.CHUNK_SIZE)) / Chunk.CHUNK_SIZE; y += Chunk.CHUNK_SIZE) {
//					if (startCoord.xCoordinate / Chunk.CHUNK_SIZE != x && startCoord.yCoordinate / Chunk.CHUNK_SIZE != y) {
					DystopiaLogger.logDebug("X CHUNK: " + x * Chunk.CHUNK_SIZE + " | Y CHUNK: " + y * Chunk.CHUNK_SIZE);
					this.addChunk(new Chunk(x * Chunk.CHUNK_SIZE, y * Chunk.CHUNK_SIZE));
//					}
//					else {
//						this.addChunk(new Chunk(startCoord.xCoordinate, startCoord.yCoordinate));
//					}
				}
			}
		}

		return true;
		
	}
	
	void addChunk(Chunk c) {
		for (Coordinate[] cArr : c.getCoords()) {
			ArrayList<Coordinate> col = new ArrayList<>();
			for (Coordinate coord : cArr) {
				col.add(coord);
			}
			this.map.add(c.getStartingCoord().xCoordinate, col);
		}
	}
	
	void addNullChunk(int startX, int startY) {
		if (this.quadrantNum == 1) {
			for (int x = startX; x < startX + Chunk.CHUNK_SIZE; x++) {
				ArrayList<Coordinate> col = new ArrayList<>();
				for (int y = startY; y < startY + Chunk.CHUNK_SIZE; y++) {
					col.add(null);
				}
				this.map.add(startX, col);
			}
		}
	}
	
	public Coordinate getStartingCoordinate() {
		return startingChunk.getStartingCoord();
	}
	
	public int getQuadrantNum() {
		return this.quadrantNum;
	}
	
	public Coordinate getCoord(Coordinate c) {
		return this.getCoord(c.xCoordinate, c.yCoordinate);
	}
	
	public Coordinate getCoord(int xCoord, int yCoord) {
		if (this.map.size() == 0) {
			return null;
		}
		
		Coordinate startCoord = this.getStartingCoordinate();
		
		if (this.getQuadrantNum() == 1 && (xCoord < startCoord.xCoordinate || yCoord < startCoord.yCoordinate)) {
			return null;
		}
		if (this.getQuadrantNum() == 2 && (xCoord > startCoord.xCoordinate || yCoord < startCoord.yCoordinate)) {
			return null;
		}
		if (this.getQuadrantNum() == 3 && (xCoord < startCoord.xCoordinate || yCoord > startCoord.yCoordinate)) {
			return null;
		}
		if (this.getQuadrantNum() == 4 && (xCoord > startCoord.xCoordinate || yCoord > startCoord.yCoordinate)) {
			return null;
		}
			
		for (int x = 0; x < this.map.size(); x++) {
			ArrayList<Coordinate> cArr = this.map.get(x);
			for (int y = 0; y < cArr.size(); y++) {
				Coordinate c = cArr.get(y);
				if (c.xCoordinate == xCoord && c.yCoordinate == yCoord) {
					return c;
				}
			}
		}
		return null;
	}
	
	public ArrayList<ArrayList<Coordinate>> getMap() {
		return this.map;
	}
	
}
