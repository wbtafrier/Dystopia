//package com.dreamstone.graphics;
//
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferInt;
//import java.util.ArrayList;
//
//import com.dreamstone.core.DisplayCarrier;
//import com.dreamstone.core.Dystopia;
//import com.dreamstone.world.Coordinate;
//
//public class DystopiaRenderer {
//	
//	//Create a system where the starting coordinates of the tile are sent in, and the pixel array of the tile.
//	//The renderer goes to the coordinates in the image and starts to draw the tile from the pixel array onto the screen.
//	
//	public static BufferedImage display = new BufferedImage((int)DisplayCarrier.getCanvas().getWidth(), (int)DisplayCarrier.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
//	private static int[] pixels;
//	private static int width;
//	private static int height;
//	private static int tileWidth = Dystopia.grid.getCoordinate(0, 0).getTile().getImage().getWidth();
//	private static int tileHeight = Dystopia.grid.getCoordinate(0, 0).getTile().getImage().getHeight();
//	private static ArrayList<Coordinate> drawTiles = new ArrayList<>();
//	
//	public static void renderGrid(Graphics2D g, ArrayList<Coordinate> map) {
//		
//		if (DisplayCarrier.getCanvas().getWidth() != width) {
//			display = new BufferedImage((int)DisplayCarrier.getCanvas().getWidth(), (int)DisplayCarrier.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
//			pixels = ((DataBufferInt)display.getRaster().getDataBuffer()).getData();
//		}
//		
//		setDimensions();
//		
//		for (int i = 0; i < map.size(); i++) {
//			if (map.get(i).getQuadrant() == 1) {
//				drawTiles.add(new Coordinate(display.getWidth() / 2 + map.get(i).xCoordinate * map.get(i).getTile().getImage().getWidth(), display.getHeight() / 2 - (map.get(i).yCoordinate + 1) * map.get(i).getTile().getImage().getHeight()));
//			}
//			else if (map.get(i).getQuadrant() == 2) {
//				drawTiles.add(new Coordinate(display.getWidth() / 2 - Math.abs(map.get(i).xCoordinate) * map.get(i).getTile().getImage().getWidth(), display.getHeight() / 2 - (map.get(i).yCoordinate + 1) * map.get(i).getTile().getImage().getWidth()));
//			}
//			else if (map.get(i).getQuadrant() == 3) {
//				drawTiles.add(new Coordinate(display.getWidth() / 2 - Math.abs(map.get(i).xCoordinate) * map.get(i).getTile().getImage().getWidth(), display.getHeight() / 2 + Math.abs(map.get(i).yCoordinate + 1) * map.get(i).getTile().getImage().getWidth()));
//			}
//			else if (map.get(i).getQuadrant() == 4) {
//				drawTiles.add(new Coordinate(display.getWidth() / 2 + map.get(i).xCoordinate * map.get(i).getTile().getImage().getWidth(), display.getHeight() / 2 + Math.abs(map.get(i).yCoordinate + 1) * map.get(i).getTile().getImage().getWidth()));
//			}
//		}
//		
//		for (int y = 0; y < height; y++) {
//			pixels[(width / 2) + (y * width)] = 1000;
//			
//			for (int x = 0; x < width; x++) {
//				
//				
//				if (y == height / 2)  pixels[x + y * width] = 1000;
//				
//				for (int i = 0; i < drawTiles.size(); i++) {
//					if (x == drawTiles.get(i).xCoordinate) {
//						
//					}
//				}
//				
//				if ((x >= sX) && x < (tileWidth + sX) && y >= sY && y < (tileHeight + sY)) {
//					pixels[x + y * width] = map.get(0).getPixels()[(x - sX) + (y - sY) * tileWidth];
//				}
//			}
//		}
//		
//		g.drawImage(display, 0, 0, null);
//	}
//	
//	private static void setDimensions() {
//		width = display.getWidth();
//		height = display.getHeight();
//	}
//}
