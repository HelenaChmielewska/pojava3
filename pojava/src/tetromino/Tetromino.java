package tetromino;

import java.awt.Graphics2D;
import java.awt.Color;

//klasa abstrakcyjna, po której dziedziczą inne tetrominy
public class Tetromino {
	public Block b[] = new Block[4];
	public Block tempB[] = new Block[4]; //tymczasowe pozycje, do testowania chyba
	
	public void create(Color color) { //zrobiłam metodę create zamiast konstruktora bo z konstruktorem nie działało potem w dziedziczących
        for (int i = 0; i < 4; i++) {
            b[i] = new Block(color);
            tempB[i] = new Block(color);
        }
	}
	public void setXY(int x, int y) {};
	public void updateXY(int direction) {};
	public void update() {};
	
	public void draw(Graphics2D g2) {
		
	}
}
