package com.dreamstone.world;

import java.util.ArrayList;
import java.util.Random;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.core.Dystopia;
import com.dreamstone.tile.TileList;

public class TestMap {
	
	public static void testWorld() {
		
		Random rand = new Random();
		ArrayList<Quadrant> quads = Dystopia.getGame().grid.QUADRANTS;
		ArrayList<ArrayList<Chunk>> chunks;
		Coordinate c;
		
		for (int i = 0; i < quads.size(); i++) {
			chunks = quads.get(i).getChunks();
			for (int y = 0; y < chunks.size(); y++) {
				for (int x = 0; x < chunks.get(y).size(); x++) {
					for (int yy = 0; yy < Chunk.CHUNK_SIZE; yy++) {
						for (int xx = 0; xx < Chunk.CHUNK_SIZE; xx++) {
							c = chunks.get(y).get(x).getCoordinateFromIndex(xx, yy);
							Dystopia.getGame().grid.setTile(c.xCoordinate, c.yCoordinate, TileList.tiles.get(rand.nextInt(TileList.tiles.size())));
						}
					}
				}
			}
		}
	}
}
