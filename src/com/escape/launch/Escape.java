package com.escape.launch;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;

import com.escape.util.LoggingUtil;

public final class Escape extends Canvas implements Runnable {

	private static final long serialVersionUID = -5025704194120253102L;
	private static final int BUFFERS = 2;
	private String[] arguments;
	
	Escape(String[] args) {
		this.arguments = args;
		this.processArguments(this.arguments);
	}
	
	public void processArguments(String[] args) {
		//TODO: Define arguments
	}

	private boolean isRunning = false;
	private Thread thread;
	
	private int tickCount = 0;
	
	public synchronized void start() {
		if (isRunning) {
			return;
		}
		
		this.isRunning = true;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public synchronized void stop() {
		if (!isRunning) {
			return;
		}
		
		this.isRunning = false;
	}
	
	//Game Loop method TODO: Go over this and make sure it is working properly
	public void beginGameLoop() {
		long beginTime = System.nanoTime();
		//MAX ticks per second
		final double TICK = 60.0D;
		//How many ticks there are per second
		final double NANOSECONDS = 1_000_000_000 / TICK;
		//Ticks per loop
		double delta = 0;
		//Times for printing every second
		long timer = System.currentTimeMillis();
		//Ticks per second
		int ticks = 0;
		//FPS
		int frames = 0;
		
		while (this.isRunning) {
			long currentTime = System.nanoTime();
			delta += (currentTime - beginTime) / NANOSECONDS;
			beginTime = currentTime;
			
			while (delta >= 1) {
				ticks++;
				tick();
				delta--;
			}
			
			frames++;
			this.render();
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				LoggingUtil.getLogger().log(Level.INFO, "Ticks: " + ticks + ", FPS: " + frames);
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	private void tick() {
		tickCount++;
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(BUFFERS);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		LoggingUtil.getLogger().log(Level.INFO, "Game loop is starting");
		this.beginGameLoop();
	}
}
