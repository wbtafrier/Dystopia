package com.dreamstone.component;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import com.dreamstone.launch.Game;

public final class EscapeComponent extends Canvas implements Runnable {

	private static final long serialVersionUID = -5025704194120253102L;
	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private static final int DEFAULT_WIDTH = gd.getDisplayMode().getWidth() / 2;
	private static final int DEFAULT_HEIGHT = gd.getDisplayMode().getHeight() / 2;
	
	private Game game;
	private boolean running;
	private Thread gameThread;
	private static final int BUFFERS = 2;
	
	public EscapeComponent() {
		Dimension preferredSize = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setPreferredSize(preferredSize);
		
		/* WE NEED TO LOOK INTO THIS. IT FIXES FLICKERING OF BACKGROUND WHEN RESIZED BUT IM NOT SURE IF
		* IT IS THE BEST SOLUTION. I THINK WE JUST DIDN'T IMPLEMENT DOUBLE BUFFERING CORRECTLY. */
		System.setProperty("sun.awt.noerasebackground", "true");
		
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
					//EscapeLogger.getLogger().log(Level.INFO, "fps: " + frames);
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
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.RED);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        bs.show();
	}
}
