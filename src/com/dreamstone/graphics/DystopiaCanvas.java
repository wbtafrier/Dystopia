package com.dreamstone.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.core.Dystopia;

public final class DystopiaCanvas extends Canvas {

	private static final long serialVersionUID = -5025704194120253102L;
	private static final int BUFFERS = 3;
	private static int lol = 0, lol2 = 0;
	
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
		
		for (int i = 0; i < 70; i++) {
			lol = (int) ((Math.sin(((Dystopia.getGame().getTickCount() + i) * 50) / 4000.0 * (Math.PI)) * 75));
			lol2 = (int) ((Math.cos(((Dystopia.getGame().getTickCount() + i) * 50) / 4000.0 * (Math.PI)) * 75));
		}
		DystopiaRenderer.render(g, lol, lol2);
		
//		g.setColor(new Color(0xEEEEEE));
//		g.fillRect(0, 0, DisplayCarrier.getFrame().getWidth(), DisplayCarrier.getFrame().getHeight());
//		GridDisplay.drawGrid(g, Dystopia.grid, false);
		
        g.dispose();
        bs.show();
	}
}
