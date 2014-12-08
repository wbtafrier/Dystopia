package com.dreamstone.component;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.dreamstone.core.Game;
import com.dreamstone.file.FileSystem;
import com.dreamstone.util.TransformImage;

public final class EscapeCanvas extends Canvas implements Runnable {

	private static final long serialVersionUID = -5025704194120253102L;
	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	private static final int DEFAULT_WIDTH = gd.getDisplayMode().getWidth() / 40;
	private static final int DEFAULT_HEIGHT = gd.getDisplayMode().getHeight() / 40;
	private static final int SCALE = 20;
	private static final int BUFFERS = 2;
	
	private Game game;
	private Thread gameThread;
	private boolean running;
	
	public EscapeCanvas() {
		Dimension preferredSize = new Dimension(DEFAULT_WIDTH * SCALE, DEFAULT_HEIGHT * SCALE);
		this.setPreferredSize(preferredSize);
		
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
		game = new Game();
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		int frames = 0;
		
		double unproccessedSeconds = 0;
		long lastTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		
		while (running) {
			long now = System.nanoTime();
			long passedTime = now - lastTime;
			lastTime = now;
			
			if (passedTime < 0) {
				passedTime = 0;
			}
			
			if (passedTime > 1_000_000_000) {
				passedTime = 1_000_000_000;
			}
			unproccessedSeconds += passedTime / 1_000_000_000.0;
			
			//boolean ticked = false;
			while (unproccessedSeconds > secondsPerTick) {
				tick();
				
				unproccessedSeconds -= secondsPerTick;
				//ticked = true;
				
				tickCount++;
				if (tickCount % 60 == 0) {
					System.out.println("Ticks: " + tickCount + ", Fps: " + frames);
					lastTime += 1000;
					frames = 0;
					tickCount = 0;
				}
			}
			
			//Updates frames independently from the game logic (ticks).
			render();
			frames++;
			
			//Controls the frames to update the same time as the game logic (ticks).
			/*if (ticked) {
				render();
				frames++;
			}*/
		}
	}
	
	private void tick() {
		game.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(BUFFERS);
			this.requestFocus();
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//System.out.println(FileSystem.getResourcesFolder().getAbsolutePath() + "\\textures\\Individual Tiles\\Grass Tiles\\grass_to_dirt_east_1.png");
		File grass = FileSystem.makeFile(FileSystem.getResourcesFolder().getAbsolutePath(), "\\textures\\Individual Tiles\\Grass Tiles\\grass_to_dirt_east_1.png");
		BufferedImage grassOriginal = null;
		
		try {
			grassOriginal = FileSystem.readImageFile(grass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedImage grassTransformation = TransformImage.flipVertically(grassOriginal);
		int xOffset = ((this.getWidth() - grassTransformation.getWidth() * SCALE) - this.getWidth() / 2) / 2;
		int yOffset = ((this.getHeight() - grassTransformation.getHeight() * SCALE)) / 2;
		
		g.drawImage(grassTransformation, xOffset, yOffset, grassTransformation.getWidth() * SCALE, grassTransformation.getHeight() * SCALE, null);
		
		grassTransformation= TransformImage.flipHorizontally(grassOriginal);
		
		int xOffset2 = ((this.getWidth() - grassTransformation.getWidth() * SCALE) + this.getWidth() / 2) / 2;
		g.drawImage(grassTransformation, xOffset2, yOffset, grassTransformation.getWidth() * SCALE, grassTransformation.getHeight() * SCALE, null);
		
        g.dispose();
        bs.show();
	}
}
