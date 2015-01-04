package com.dreamstone.world;

import java.util.Random;

import com.dreamstone.tile.EnumDirection;
import com.dreamstone.tile.Tile;
import com.dreamstone.tile.TileGrass;
import com.dreamstone.tile.TileList;

public class WorldGeneration {
	
	public static Tile setRandomTile() {
		Random rand = new Random();
		int r = rand.nextInt(2);
		Tile t;
		
		if (r == 0) {
			t = TileList.dirt;
		}
		else {
			TileGrass tg = (TileGrass) TileList.grass;
			r = rand.nextInt(9);
//			switch (r) {
//			case 0: tg.setDirection(EnumDirection.NORTHWEST);
//				break;
//			case 1: tg.setDirection(EnumDirection.NORTH);
//			break;
//			case 2: tg.setDirection(EnumDirection.NORTHEAST);
//			break;
//			case 3: tg.setDirection(EnumDirection.WEST);
//			break;
//			case 4: tg.setDirection(EnumDirection.EAST);
//			break;
//			case 5: tg.setDirection(EnumDirection.SOUTHWEST);
//			break;
//			case 6: tg.setDirection(EnumDirection.SOUTH);
//			break;
//			case 7: tg.setDirection(EnumDirection.SOUTHEAST);
//			break;
//			}
//			System.out.println(r);
			t = tg;
		}
		return t;
	}
}
