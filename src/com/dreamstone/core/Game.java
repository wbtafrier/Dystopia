package com.dreamstone.core;

import javax.swing.JFrame;
import com.dreamstone.component.EscapeCanvas;

public class Game implements Runnable {
	
	private Thread gameThread;
	private JFrame escapeFrame;
	private EscapeCanvas escapeCanvas;
	
	private int time;
	private boolean running;
	
	public Game(JFrame frame, EscapeCanvas canvas) {
		escapeFrame = frame;
		escapeCanvas = canvas;
		time = 0;
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
			escapeCanvas.render();
			frames++;
			
			//Controls the frames to update the same time as the game logic (ticks).
			/*if (ticked) {
				render();
				frames++;
			}*/
		}
	}
	
	public void tick() {
		time++;
	}
	
	public int getTime() {
		return time;
	}
	
	public JFrame getFrame() {
		return escapeFrame;
	}
	
	public EscapeCanvas getCanvas() {
		return escapeCanvas;
	}
	
	public int getWidth() {
		return escapeFrame.getWidth();
	}
	
	public int getHeight() {
		return escapeFrame.getHeight();
	}
}
