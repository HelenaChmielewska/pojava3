package tetromino;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Klasa rysująca pojedynczy kwadrat z jakiego składają się klocki
 * @author Kinga Urban
 */

public class Block extends Rectangle{
	public int x,y;
	public static final int size= 30; //bloczek ma rozmiar 30x30 
	public Color c;
	
	public Block (Color c) {
		this.c =c;
	}
	public void draw(Graphics2D g2) {
		int margine=2;
		g2.setColor(c);
		g2.fillRect(x+margine,y+margine,size-(margine*2),size-(margine*2));
	}
	
}
