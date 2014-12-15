package com.dreamstone.core;

import javax.swing.JFrame;

import com.dreamstone.graphics.DystopiaCanvas;

public class Dystopia implements Runnable {
	
	static Dystopia gameInstance;
	private static JFrame frameInstance;
	private static DystopiaCanvas canvasInstance;
	private static int ticks;
	
	private Thread gameThread;
	private boolean running;
	
	protected Dystopia() {
		ticks = 0;
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
			this.getCanvas().render();
			frames++;
			
			//Controls the frames to update the same time as the game logic (ticks).
			/*if (ticked) {
				render();
				frames++;
			}*/
		}
	}
	
	public void tick() {
		ticks++;
		System.out.println(Dystopia.getGame().getTime());
	}
	
	public int getTime() {
		return ticks;
	}
	
	public static Dystopia getGame() {
		return gameInstance;
	}
	
	public void setFrame(JFrame frame) {
		frameInstance = frame;
	}
	
	public JFrame getFrame() {
		return frameInstance;
	}
	
	public void setCanvas(DystopiaCanvas canvas) {
		canvasInstance = canvas;
	}
	
	public DystopiaCanvas getCanvas() {
		return canvasInstance;
	}
}
