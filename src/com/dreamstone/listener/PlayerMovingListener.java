package com.dreamstone.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerMovingListener extends KeyAdapter implements KeyListener {

	//TODO: These can be useful in the future.
	private boolean isControlPressed;
	private boolean isShiftPressed;
	private boolean isAltPressed;
	
	public boolean isMovingNorth, isMovingSouth, isMovingEast, isMovingWest;
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) isControlPressed = true;
		else if (e.getKeyCode() == KeyEvent.VK_SHIFT) isShiftPressed = true;
		else if (e.getKeyCode()==KeyEvent.VK_ALT) isAltPressed = true;
		
		setCharacterOrientation(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) isControlPressed = false;
		else if (e.getKeyCode() == KeyEvent.VK_SHIFT) isShiftPressed = false;
		else if (e.getKeyCode()==KeyEvent.VK_ALT) isAltPressed = false;
		else if (e.getKeyChar() == KeyOptions.playerMoveNorth) isMovingNorth = false;
		else if (e.getKeyChar() == KeyOptions.playerMoveSouth) isMovingSouth = false;
		else if (e.getKeyChar() == KeyOptions.playerMoveEast) isMovingEast = false;
		else if (e.getKeyChar() == KeyOptions.playerMoveWest) isMovingWest = false;
	}
	
	private void setCharacterOrientation(KeyEvent e) {
		//TODO: Add world class
		
		if (e.getKeyChar() == KeyOptions.playerMoveNorth) {
			isMovingNorth = true;
			//TODO: Add World class.
//			World.getPlayer().setOrientation(PlayerOrientation.NORTH);
			System.out.println("YAY! Key pressed: " + e.getKeyChar());
		}
		else if (e.getKeyChar() == KeyOptions.playerMoveWest) {
			isMovingWest = true;
			//TODO: Add World class.
//			World.getPlayer().setOrientation(PlayerOrientation.WEST);
			System.out.println("YAY! Key pressed: " + e.getKeyChar());
		}
		else if (e.getKeyChar() == KeyOptions.playerMoveSouth) {
			isMovingSouth = true;
			//TODO: Add World class.
//			World.getPlayer().setOrientation(PlayerOrientation.SOUTH);
			System.out.println("YAY! Key pressed: " + e.getKeyChar());
		}
		else if (e.getKeyChar() == KeyOptions.playerMoveEast) {
			isMovingEast = true;
			//TODO: Add World class.
//			World.getPlayer().setOrientation(PlayerOrientation.EAST);
			
			System.out.println("YAY! Key pressed: " + e.getKeyChar());
		}
	}
	
	public boolean isControlPressed() {
		return isControlPressed;
	}
	
	public boolean isShiftPressed() {
		return isShiftPressed;
	}
	
	public boolean isAltPressed() {
		return isAltPressed;
	}
}
