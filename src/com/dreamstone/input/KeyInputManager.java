package com.dreamstone.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.dreamstone.core.Dystopia;
import com.dreamstone.entity.EntityPlayer;
import com.dreamstone.tile.EnumDirection;

public class KeyInputManager extends KeyAdapter implements KeyListener {
	
	public static EntityPlayer player = Dystopia.getGame().currentWorld.getPlayer();
	public static boolean isNorth;
	public static boolean isSouth;
	public static boolean isEast;
	public static boolean isWest;
	
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar());
		if (e.getKeyChar() == KeyOptions.getKeyNorth()[0] || e.getKeyChar() == KeyOptions.getKeyNorth()[1]) isNorth = true;
		else if (e.getKeyChar() == KeyOptions.getKeySouth()[0] || e.getKeyChar() == KeyOptions.getKeySouth()[1]) isSouth = true;
		else if (e.getKeyChar() == KeyOptions.getKeyEast()[0] || e.getKeyChar() == KeyOptions.getKeyEast()[1]) isEast = true;
		else if (e.getKeyChar() == KeyOptions.getKeyWest()[0] || e.getKeyChar() == KeyOptions.getKeyWest()[1]) isWest = true;
	}

//	@Override public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == KeyOptions.getKeyNorth()[0] || e.getKeyChar() == KeyOptions.getKeyNorth()[1]) isNorth = false;
		else if (e.getKeyChar() == KeyOptions.getKeySouth()[0] || e.getKeyChar() == KeyOptions.getKeySouth()[1]) isSouth = false;
		else if (e.getKeyChar() == KeyOptions.getKeyEast()[0] || e.getKeyChar() == KeyOptions.getKeyEast()[1]) isEast = false;
		else if (e.getKeyChar() == KeyOptions.getKeyWest()[0] || e.getKeyChar() == KeyOptions.getKeyWest()[1]) isWest = false;
	}
	
	public static void processInput() {
		processPlayerMovement();
	}
	
	private static void processPlayerMovement() {
		if (isNorth != player.isMovingNorth()) {
			player.setMovingNorth(isNorth);
			
			if (player.isMovingNorth())
				player.setDirection(EnumDirection.NORTH);
		}
		
		if (isSouth != player.isMovingSouth()) {
			player.setMovingSouth(isSouth);
			
			if (player.isMovingSouth())
				player.setDirection(EnumDirection.SOUTH);
		}
		
		if (isEast != player.isMovingEast()) {
			player.setMovingEast(isEast);
			
			if (player.isMovingEast())
				player.setDirection(EnumDirection.EAST);
		}
		
		if (isWest != player.isMovingWest()) {
			player.setMovingWest(isWest);
			
			if (player.isMovingWest())
				player.setDirection(EnumDirection.WEST);
		}
	}
}
