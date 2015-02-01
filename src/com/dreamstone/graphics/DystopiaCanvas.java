package com.dreamstone.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.core.Dystopia;
import com.dreamstone.tile.Tile;

public final class DystopiaCanvas extends Canvas {

	private static final long serialVersionUID = -5025704194120253102L;
	private static final int BUFFERS = 3;
	
	public DystopiaCanvas() {
		Dimension defaultSize = new Dimension((int)(DisplayCarrier.getDisplaySize().getWidth() / 2), (int)(DisplayCarrier.getDisplaySize().getHeight() / 2));
		this.setPreferredSize(defaultSize);
		this.addKeyListener(Dystopia.getGame().keyListener);
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(DystopiaCanvas.BUFFERS);
			this.requestFocus();
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		g.setColor(new Color(0xEEEEEE));
		g.fillRect(0, 0, DisplayCarrier.getCanvas().getWidth(), DisplayCarrier.getCanvas().getHeight());
		GridDisplay.drawGrid(g);
		Rectangle2D rect = new Rectangle2D.Double(DisplayCarrier.getCanvas().getWidth() / 2 - Tile.getTileSize(), DisplayCarrier.getCanvas().getHeight() / 2 - Tile.getTileSize(), Tile.getTileSize() * 2, Tile.getTileSize() * 2);
		g.draw(rect);
		PlayerCamera.drawPlayer(g);
		
        g.dispose();
        bs.show();
        Toolkit.getDefaultToolkit().sync();

	}
}
