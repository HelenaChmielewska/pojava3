package tetromino;

import java.awt.Graphics2D;

import main.PlayManager;
import main.KeyHandler;

import java.awt.Color;

//klasa abstrakcyjna, po której dziedziczą inne tetrominy
public class Tetromino {
	public Block b[] = new Block[4];
	public Block tempB[] = new Block[4]; //tymczasowe pozycje, do testowania chyba
	int autoDropCounter = 0;
	public int direction = 1; //4 kierunki: 1,2,3,4
	
	public void create(Color color) { //zrobiłam metodę create zamiast konstruktora bo z konstruktorem nie działało potem w dziedziczących
        for (int i = 0; i < 4; i++) {
            b[i] = new Block(color);
            tempB[i] = new Block(color);
        }
	}
	public void setXY(int x, int y) {};
	public void updateXY(int direction) {
		this.direction = direction;
		//przepisujemy do tablicy temp, żeby uniknąć kolizji - najpierw sprawdzamy czy można wykonać rotację
		for (int i = 0; i < 4; i++) {
            b[i].x = tempB[i].x;
            b[i].y = tempB[i].y;
        }
		
	}
	public void getDirection1() {};
	public void getDirection2() {};
	public void getDirection3() {};
	public void getDirection4() {};
	public void update() {
		
		//sterowanie tetrominem
		if(KeyHandler.upPressed) {
			switch(direction) {
			case 1: getDirection2(); break;
			case 2: getDirection3(); break;
			case 3: getDirection4(); break;
			case 4: getDirection1(); break;
			}
			KeyHandler.upPressed = false;
		}
		if(KeyHandler.downPressed) {
			for(int i=0; i<4; i++) {
				b[i].y +=Block.size; //przesuwamy klocek do dołu o jedno pole
			}
			autoDropCounter = 0; //gdy strzałka w dół resetujemy autodrop
			KeyHandler.downPressed = false; //po przesunięciu klocka w dół zmieniamy na false
		}
		if(KeyHandler.leftPressed) {
			for(int i=0; i<4; i++) {
				b[i].x -=Block.size; //przesuwamy klocek w lewo o jedno pole
			}
			KeyHandler.leftPressed = false;
		}
		if(KeyHandler.rightPressed) {
			for(int i=0; i<4; i++) {
				b[i].x +=Block.size; //przesuwamy klocek w prawo o jedno pole
			}
			KeyHandler.rightPressed = false;
			
		}
		
		//automatyczne spadanie
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
	}
}