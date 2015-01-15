package com.dreamstone.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.core.Dystopia;
import com.dreamstone.listener.PlayerMovingListener;
import com.dreamstone.world.World;

public final class DystopiaCanvas extends Canvas {

	private static final long serialVersionUID = -5025704194120253102L;
	private static final int BUFFERS = 2;
	public World testWorld;
	private static Graphics2D g = null;
	public PlayerMovingListener movingListener = new PlayerMovingListener();
	private int ticks;
	
	public DystopiaCanvas() {
		Dimension defaultSize = new Dimension((int)(DisplayCarrier.getDisplaySize().getWidth() / 2), (int)(DisplayCarrier.getDisplaySize().getHeight() / 2));
		this.setPreferredSize(defaultSize);
		this.addKeyListener(movingListener);
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
		
		testWorld = new World();
		ticks = 0;
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(DystopiaCanvas.BUFFERS);
			this.requestFocus();
			return;
		}

		g = (Graphics2D) bs.getDrawGraphics();
		
		g.setColor(new Color(0xEEEEEE));
		g.fillRect(0, 0, DisplayCarrier.getFrame().getWidth(), DisplayCarrier.getFrame().getHeight());
		
		if (Dystopia.getGame().getTickCount() > ticks) {
			if (movingListener.isMovingNorth) {
				testWorld.setYOffset((int)(testWorld.getYOffset() - testWorld.getPlayer().getSpeed()));
			}
			else if (movingListener.isMovingSouth) {
				testWorld.setYOffset((int)(testWorld.getYOffset() + testWorld.getPlayer().getSpeed()));
			}
			
			if (movingListener.isMovingEast) {
				testWorld.setXOffset((int)(testWorld.getXOffset() + testWorld.getPlayer().getSpeed()));
			}
			else if (movingListener.isMovingWest) {
				testWorld.setXOffset((int)(testWorld.getXOffset() - testWorld.getPlayer().getSpeed()));
			}
		}
	
		ticks = Dystopia.getGame().getTickCount();
	
		GridDisplay.drawGrid(g, Dystopia.getGame().grid, (int)testWorld.getXOffset(), (int)testWorld.getYOffset());
		
        g.dispose();
        bs.show();
	}
	
	@Override
	public Graphics getGraphics() {
		return g;
	}
}
