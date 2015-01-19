package com.dreamstone.graphics;

import com.dreamstone.file.ResourceLoader;
import com.dreamstone.util.TransformImage;

public class GraphicsOptions {
	
	private static float scale;
	
	public static void initializeGraphicsOptions() {
		scale = 5F;
	}
	
	public static void updateGraphics(float s) {
		scaleImages(s);
	}
	
	private static void scaleImages(float s) {

		ResourceLoader.terrainSheet = TransformImage.scaleImage(ResourceLoader.terrainSheet, scale);
		ResourceLoader.nullImage = TransformImage.scaleImage(ResourceLoader.nullImage, scale);
		ResourceLoader.playerIdle = TransformImage.scaleImage(ResourceLoader.playerIdle, scale);
	}
	
	public static float getScale() {
		return scale;
	}
}
