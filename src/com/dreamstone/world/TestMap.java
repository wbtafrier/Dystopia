package com.dreamstone.world;

import com.dreamstone.core.Dystopia;
import com.dreamstone.tile.TileList;
import com.dreamstone.tile.TileTransitionList;

public class TestMap {
	
	public static void testWorld() {
		
		int r;
		for (int y = -8; y < 8; y++) {
			for (int x = -8; x < 8; x++) {
				
				r = (int) (Math.random() * 6);
				
				switch (r) {
				case 0: Dystopia.grid.setTile(x, y, TileTransitionList.grassToDirtNorth1);
				break;
				case 1: Dystopia.grid.setTile(x, y, TileTransitionList.grassToDirtSouth1);
				break;
				case 2: Dystopia.grid.setTile(x, y, TileTransitionList.grassToDirtEast1);
				break;
				case 3: Dystopia.grid.setTile(x, y, TileTransitionList.grassToDirtWest1);
				break;
				case 4: Dystopia.grid.setTile(x, y, TileList.grass1);
				break;
				case 5: Dystopia.grid.setTile(x, y, TileList.dirt1);
				}
			}
		}
	}
}
