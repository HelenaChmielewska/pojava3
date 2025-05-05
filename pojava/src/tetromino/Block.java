package tetromino;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Klasa rysująca pojedynczy kwadrat z jakiego składają się klocki
 * @author Kinga Urban
 */

public class Block extends Rectangle{
	int x,y;
	static final int size = 30; //bloczek ma rozmiar 30x30 
	Color c;
	
	public Block (Color c) {
		this.c =c;
	}
	public void draw(Graphics2D g2) {
		g2.setColor(c);
		g2.fillRect(x, y, size, size);
	}
	
}
