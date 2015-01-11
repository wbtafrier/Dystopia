package com.dreamstone.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.core.Dystopia;
import com.dreamstone.world.Chunk;
import com.dreamstone.world.Coordinate;
import com.dreamstone.world.Quadrant;

public final class DystopiaCanvas extends Canvas {

	private static final long serialVersionUID = -5025704194120253102L;
	private static final int BUFFERS = 3;
	
	public DystopiaCanvas() {
		Dimension defaultSize = new Dimension((int)(DisplayCarrier.getDisplaySize().getWidth() / 2), (int)(DisplayCarrier.getDisplaySize().getHeight() / 2));
		this.setPreferredSize(defaultSize);
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
		g.fillRect(0, 0, DisplayCarrier.getFrame().getWidth(), DisplayCarrier.getFrame().getHeight());
//		DystopiaRenderer.render(g, DisplayCarrier.getCanvas().getWidth() / 2, DisplayCarrier.getCanvas().getHeight() / 2, ((DataBufferInt)TileList.grass1.getImage().getRaster().getDataBuffer()).getData(), TileList.grass1.getImage().getWidth(), TileList.grass1.getImage().getHeight());
		GridDisplay.drawGrid(g, Dystopia.grid, true);
		
		
		
//		DystopiaRenderer.renderGrid(g, map);
							
        g.dispose();
        bs.show();
	}
}
