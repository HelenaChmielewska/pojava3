package tetromino;

import java.awt.Graphics2D;

import main.PlayManager;

import java.awt.Color;

//klasa abstrakcyjna, po której dziedziczą inne tetrominy
public class Tetromino {
	public Block b[] = new Block[4];
	public Block tempB[] = new Block[4]; //tymczasowe pozycje, do testowania chyba
	int autoDropCounter = 0;
	
	public void create(Color color) { //zrobiłam metodę create zamiast konstruktora bo z konstruktorem nie działało potem w dziedziczących
        for (int i = 0; i < 4; i++) {
            b[i] = new Block(color);
            tempB[i] = new Block(color);
        }
	}
	public void setXY(int x, int y) {};
	public void updateXY(int direction) {};
	public void update() {
		autoDropCounter++;
		if(autoDropCounter == PlayManager.dropInterval) {
			b[0].y +=Block.size;
			b[1].y +=Block.size;
			b[2].y +=Block.size;
			b[3].y +=Block.size;
			autoDropCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		int margin = 2;
		g2.setColor(b[0].c);
		g2.fillRect(b[0].x+margin,b[0].y+margin,Block.size-(margin*2),Block.size-(margin*2));
		g2.fillRect(b[1].x+margin,b[1].y+margin,Block.size-(margin*2),Block.size-(margin*2));
		g2.fillRect(b[2].x+margin,b[2].y+margin,Block.size-(margin*2),Block.size-(margin*2));
		g2.fillRect(b[3].x+margin,b[3].y+margin,Block.size-(margin*2),Block.size-(margin*2));
		//System.out.println("run");
	}
}