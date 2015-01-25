package com.dreamstone.graphics;

import com.dreamstone.file.ResourceLoader;
import com.dreamstone.util.TransformImage;

public class GraphicsOptions {
	
	private static int tileSize;
	private static float scale;
	
	public static void initializeGraphicsOptions() {
		tileSize = 128;
		scale = 1F;
	}
	
	public static void updateGraphics(float s) {
		scaleImages(s);
	}
	
	private static void scaleImages(float s) {

		ResourceLoader.terrainSheet = TransformImage.scaleImage(ResourceLoader.terrainSheet, scale);
		ResourceLoader.nullImage = TransformImage.scaleImage(ResourceLoader.nullImage, scale);
		ResourceLoader.playerIdle = TransformImage.scaleImage(ResourceLoader.playerIdle, scale);
		ResourceLoader.playerNorthAnimation = TransformImage.scaleImage(ResourceLoader.playerNorthAnimation, scale);
		ResourceLoader.playerSouthAnimation = TransformImage.scaleImage(ResourceLoader.playerSouthAnimation, scale);
		ResourceLoader.playerEastAnimation = TransformImage.scaleImage(ResourceLoader.playerEastAnimation, scale);
		ResourceLoader.playerWestAnimation = TransformImage.scaleImage(ResourceLoader.playerWestAnimation, scale);
	}
	
	public static int getTileSize() {
		return tileSize;
	}
	
	public static float getScale() {
		return scale;
	}
}
