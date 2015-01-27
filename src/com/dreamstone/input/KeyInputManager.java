package com.dreamstone.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.dreamstone.core.Dystopia;
import com.dreamstone.world.World;

public class KeyInputManager extends KeyAdapter implements KeyListener {
	
	public static World world = Dystopia.getGame().getCurrentWorld();
	
	private final static ArrayList<Character> pressed = new ArrayList<Character>();
	public static boolean isWalking;
	public static boolean isNorth;
	public static boolean isSouth;
	public static boolean isEast;
	public static boolean isWest;
	private static boolean temp;
	
	@Override
	public void keyTyped(KeyEvent e) {
		for (int i = 0; i < pressed.size(); i++) {
			if (e.getKeyChar() == pressed.get(i)) {
				temp = true;
				break;
			}
		}
		if (!temp) {
			pressed.add(e.getKeyChar());
		}
		temp = false;
		
		if (e.getKeyChar() == KeyOptions.getKeyNorth()[0] || e.getKeyChar() == KeyOptions.getKeyNorth()[1]) {
			isNorth = true;
		}
		else if (e.getKeyChar() == KeyOptions.getKeySouth()[0] || e.getKeyChar() == KeyOptions.getKeySouth()[1]) {
			isSouth = true;
		}
		else if (e.getKeyChar() == KeyOptions.getKeyEast()[0] || e.getKeyChar() == KeyOptions.getKeyEast()[1]) {
			isEast = true;
		}
		else if (e.getKeyChar() == KeyOptions.getKeyWest()[0] || e.getKeyChar() == KeyOptions.getKeyWest()[1]) {
			isWest = true;
		}
		
		if (isNorth || isSouth || isEast || isWest) {
			isWalking = true;
		}
	}

//	@Override public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < pressed.size(); i++) {
			if (e.getKeyChar() == pressed.get(i)) {
				pressed.remove(i);
				break;
			}
		}
		
		if (e.getKeyChar() == KeyOptions.getKeyNorth()[0] || e.getKeyChar() == KeyOptions.getKeyNorth()[1]) {
			isNorth = false;
		}
		else if (e.getKeyChar() == KeyOptions.getKeySouth()[0] || e.getKeyChar() == KeyOptions.getKeySouth()[1]) {
			isSouth = false;
		}
		else if (e.getKeyChar() == KeyOptions.getKeyEast()[0] || e.getKeyChar() == KeyOptions.getKeyEast()[1]) {
			isEast = false;
		}
		else if (e.getKeyChar() == KeyOptions.getKeyWest()[0] || e.getKeyChar() == KeyOptions.getKeyWest()[1]) {
			isWest = false;
		}
		
		if (!isNorth && !isSouth && !isEast && !isWest) {
			isWalking = false;
		}
	}
	
	public static void processInput() {
		processPlayerMovement();
	}
	
	private static void processPlayerMovement() {
		Character temp = KeyOptions.getKeySouth()[0];
		
		if (isWalking) world.getPlayer().setWalking(true);
		else world.getPlayer().setWalking(false);
		
//		System.out.println("Pressed Array: " + pressed);
		
		if (pressed.size() > 1) {
            temp = (Character) pressed.get(pressed.size() - 1);
//            System.out.println("Temp: " + temp);
        }
		else if (pressed.size() == 1){
			temp = (Character) pressed.get(0);
//			System.out.println("Temp: " + temp);
		}
		
		if (temp == KeyOptions.getKeyNorth()[0] || temp == KeyOptions.getKeyNorth()[1]) {
			checkNorth();
		}
		else if (temp == KeyOptions.getKeySouth()[0] || temp == KeyOptions.getKeySouth()[1]) {
			checkSouth();
		}
		else if (temp == KeyOptions.getKeyEast()[0] || temp == KeyOptions.getKeyEast()[1]) {
			checkEast();
		}
		else {
			checkWest();
		}
	}
	
	private static void checkNorth() {
		if (isNorth) world.getPlayer().setNorth(true);
		else world.getPlayer().setNorth(false);
	}
	
	private static void checkSouth() {
		if (isSouth) world.getPlayer().setSouth(true);
		else world.getPlayer().setSouth(false);
	}
	
	private static void checkEast() {
		if (isEast) world.getPlayer().setEast(true);
		else world.getPlayer().setEast(false);
	}
	
	private static void checkWest() {
		if (isWest) world.getPlayer().setWest(true);
		else world.getPlayer().setWest(false);
	}
}
