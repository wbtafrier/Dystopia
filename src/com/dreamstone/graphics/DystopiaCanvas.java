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
	private static final int BUFFERS = 3;
	public World testWorld;
	private static Graphics2D g = null;
	public PlayerMovingListener movingListener = new PlayerMovingListener();
	
	public DystopiaCanvas() {
		Dimension defaultSize = new Dimension((int)(DisplayCarrier.getDisplaySize().getWidth() / 2), (int)(DisplayCarrier.getDisplaySize().getHeight() / 2));
		this.setPreferredSize(defaultSize);
		this.addKeyListener(movingListener);
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
		
		testWorld = new World();
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
		
		if (movingListener.isMovingNorth) {
			testWorld.setYOffset(testWorld.getYOffset() - (int)testWorld.getPlayer().getSpeed());
		}
		else if (movingListener.isMovingSouth) {
			testWorld.setYOffset(testWorld.getYOffset() + (int)testWorld.getPlayer().getSpeed());
		}
		
		if (movingListener.isMovingEast) {
			testWorld.setXOffset(testWorld.getXOffset() + (int)testWorld.getPlayer().getSpeed());
		}
		else if (movingListener.isMovingWest) {
			testWorld.setXOffset(testWorld.getXOffset() - (int)testWorld.getPlayer().getSpeed());
		}
		
		GridDisplay.drawGrid(g, Dystopia.getGame().grid, testWorld.getXOffset(), testWorld.getYOffset());
		
        g.dispose();
        bs.show();
	}
	
	@Override
	public Graphics getGraphics() {
		return g;
	}
}
