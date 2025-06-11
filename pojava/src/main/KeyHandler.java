package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Klasa obługująca zdarzenia przycisków
 * @author Helena Chmielewska, Kinga Urban
 */

public class KeyHandler implements KeyListener{
	public static KeyHandler INSTANCE;
	public boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed;
	
	public KeyHandler() {
		INSTANCE = this;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_SPACE) {
			if(pausePressed) {
				pausePressed = false;
			}
			else {
				pausePressed = true;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
