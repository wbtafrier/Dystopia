package com.dreamstone.graphics;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class DisplayManager {
	
	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private static final int BUFFERS = 3;
	
	private static int width = gd.getDisplayMode().getWidth();
	private static int height = gd.getDisplayMode().getHeight();
	private static int scale = 1;
	private static Dimension resolution;
	
	public static Dimension getScreenSize() {
		return new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
	}
	
	public static int getBuffers() {
		return DisplayManager.BUFFERS;
	}
	
	public static void setWidth(int width) {
		DisplayManager.width = width;
	}
	
	public static int getWidth() {
		return DisplayManager.width;
	}
	
	public static void setHeight(int height) {
		DisplayManager.height = height;
	}
	
	public static int getHeight() {
		return DisplayManager.height;
	}
	
	public static void setScale(int scale) {
		DisplayManager.scale = scale;
		DisplayManager.width /= scale;
		DisplayManager.height /= scale;
	}
	
	public static int getScale() {
		return DisplayManager.scale;
	}
	
	public static void setResolution(Dimension res) {
		DisplayManager.resolution = res;
	}
	
	public static Dimension getResolution() {
		return DisplayManager.resolution;
	}
}
