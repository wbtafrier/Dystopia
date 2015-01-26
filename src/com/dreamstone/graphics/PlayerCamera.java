package com.dreamstone.graphics;

import java.awt.Graphics;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.core.Dystopia;
import com.dreamstone.entity.EntityPlayer;
import com.dreamstone.world.Grid;

public class PlayerCamera {

	public static EntityPlayer player = Dystopia.getGame().getCurrentWorld().getPlayer();
	public static Grid grid = Dystopia.getGame().getCurrentWorld().getGrid();
	
	public static void displayPlayer(Graphics g) {
		
		player.setWalkingBoundsXPos((DisplayCarrier.getCanvas().getWidth() / 2));
		player.setWalkingBoundsYPos((DisplayCarrier.getCanvas().getHeight() / 2));
		player.setCurrentCoordinate(grid.getCoordinateFromDisplay(player.getWalkingBoundsXPos(), player.getWalkingBoundsYPos()));
		
		g.drawImage(player.getImage(), player.getPlayerOriginX(), player.getPlayerOriginY(), null);
	}
}
