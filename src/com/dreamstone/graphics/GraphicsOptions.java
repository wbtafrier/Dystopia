package com.dreamstone.graphics;

import com.dreamstone.file.ResourceLoader;
import com.dreamstone.util.TransformImage;

public class GraphicsOptions {
	
	private static float scale;
	
	public static void initializeGraphicsOptions() {
		scale = 4F;
	}
	
	public static void updateGraphics(float s) {
		scaleImages(s);
	}
	
	private static void scaleImages(float s) {
		ResourceLoader.terrainSheet = TransformImage.scaleImage(ResourceLoader.terrainSheet, s);
	}
	
	public static float getScale() {
		return scale;
	}
	
}
