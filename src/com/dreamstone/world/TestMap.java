package com.dreamstone.world;

import java.util.Random;

import com.dreamstone.core.Dystopia;
import com.dreamstone.tile.TileList;

public class TestMap {
	
	public static void testWorld() {
		
		Random rand = new Random();
		
		for (int y = -16; y < 16; y++) {
			for (int x = -16; x < 16; x++) {
				Dystopia.grid.setTile(x, y, TileList.tiles.get(rand.nextInt(TileList.tiles.size())));
			}
		}
	}
}
