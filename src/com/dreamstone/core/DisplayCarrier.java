package com.dreamstone.core;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import com.dreamstone.graphics.DystopiaCanvas;
import com.dreamstone.graphics.DystopiaFrame;


/**
 * Stores the main canvas and frame and provides accessors/mutators to modify selected aspects of them.
 */
public class DisplayCarrier {
	private static DystopiaCanvas canvasInstance;
	private static DystopiaFrame frameInstance;
	
	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	static void initializeDisplay() {
		canvasInstance = new DystopiaCanvas();
		frameInstance = new DystopiaFrame(canvasInstance);
	}
	
	public static DystopiaCanvas getCanvas() {
		return canvasInstance;
	}
	
	public static DystopiaFrame getFrame() {
		return frameInstance;
	}
	
	public static Dimension getDisplaySize() {
		Dimension displaySize = new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
		return displaySize;
	}
	
	public static int getDisplayResolutionDPI() {
		int displayResolution = Toolkit.getDefaultToolkit().getScreenResolution();
		return displayResolution;
	}
}
