package com.dreamstone.core;

import com.dreamstone.input.KeyInputManager;
import com.dreamstone.world.World;

public class Dystopia implements Runnable {
	
	private World currentWorld;
	private String playerName;
	public KeyInputManager keyListener;
	
	static Dystopia gameInstance;
	private int ticks;
	private int frames;
	
	private int avgFPSCount;
	private int avgFPS;
	
	private Thread gameThread;
	private boolean running;
	
	protected Dystopia() {
		ticks = 0;
		frames = 0;
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
//					avgFPSCount++;
//					avgFPS += frames;
					
//					System.out.println("Ticks: " + tickCount + ", Fps: " + frames);
//					if (avgFPSCount == 5) {
//						System.out.println("=========================\nAVERAGE FPS: " + avgFPS / avgFPSCount + "\n=========================");
//						avgFPSCount = 0;
//						avgFPS = 0;
//					}
					lastTime += 1000;
					frames = 0;
					tickCount = 0;
				}
			}
			
			//Updates frames independently from the game logic (ticks).
			DisplayCarrier.getCanvas().render();
			KeyInputManager.processInput();
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
//		System.out.println(Dystopia.getGame().getTickCount());
	}
	
	public int getTickCount() {
		return ticks;
	}
	
	public int getFrameRate() {
		return frames;
	}
	
	public static Dystopia getGame() {
		return gameInstance;
	}
	
	public boolean isRunning() {
		return this.running;
	}
	
	protected void setCurrentWorld(World w) {
		this.currentWorld = w;
	}
	
	public World getCurrentWorld() {
		return this.currentWorld;
	}
	
	protected void setPlayerName(String name) {
		this.playerName = name;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
}
