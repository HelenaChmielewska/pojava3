package tetromino;

import java.awt.Graphics2D;

import main.PlayManager;
import main.KeyHandler;
import main.PlayManager;

import java.awt.Color;

/**
 * Klasa głównego okna obługująca przełączanie między panelami
 * @author Kinga Urban
 */
public class Tetromino {
	
	
	public Block b[] = new Block[4];
	public Block tempB[] = new Block[4]; //tymczasowe pozycje, do testowania chyba
	int autoDropCounter = 0;
	public int direction = 1; //4 kierunki: 1,2,3,4
	boolean leftCollision, rightCollision, bottomCollision;
	public boolean active = true;
	public boolean deactivating;
	int deactivateCounter = 0;
	
	//zrobiłam metodę create do tworzenia klocków zamiast konstruktora bo z konstruktorem nie działała w klasach dziedziczących
	public void create(Color color) { 
        for (int i = 0; i < 4; i++) {
            b[i] = new Block(color);
            tempB[i] = new Block(color);
        }
	}
	public void setXY(int x, int y) {};
	public void updateXY(int direction) {
		checkRotationCollision();
		
		if(leftCollision==false && rightCollision==false && bottomCollision==false) {
			this.direction = direction;
			//przepisujemy do tablicy temp, żeby uniknąć kolizji - najpierw sprawdzamy czy można wykonać rotację
			for (int i = 0; i < 4; i++) {
	            b[i].x = tempB[i].x;
	            b[i].y = tempB[i].y;
	        }
		}
		
	}
	public void getDirection1() {};
	public void getDirection2() {};
	public void getDirection3() {};
	public void getDirection4() {};
	
	public void checkMovementCollision() {
		leftCollision = false; //resetujemy zmienne
		rightCollision = false;
		bottomCollision = false;
		
		//sprawdzamy kolizje z klockami
		checkStaticBlockCollision(); 
		
		//sprawdzamy kolizje z lewą ścianą
		for (int i=0; i<b.length; i++) {
			if (b[i].x ==PlayManager.left_x) {
				leftCollision =true;
			}		
		}
		//sprawdzamy kolizje z prawą ścianą
		for (int i=0; i<b.length; i++) {
			if (b[i].x + Block.size ==PlayManager.right_x) {
				rightCollision =true;
			}		
		}
		//sprawdzamy kolizje z dolną ścianą
				for (int i=0; i<b.length; i++) {
					if (b[i].y +Block.size ==PlayManager.bottom_y) {
						bottomCollision =true;
					}		
				}
		
	}
	public void checkRotationCollision() {
		leftCollision = false; //resetujemy zmienne
		rightCollision = false;
		bottomCollision = false;
		
		//sprawdzamy kolizje z klockami
		checkStaticBlockCollision(); 
		
		//sprawdzamy kolizje z lewą ścianą
		for (int i=0; i<b.length; i++) {
			if (tempB[i].x < PlayManager.left_x) {
				leftCollision =true;
			}		
		}
		//sprawdzamy kolizje z prawą ścianą
		for (int i=0; i<b.length; i++) {
			if (tempB[i].x + Block.size > PlayManager.right_x) {
				rightCollision =true;
			}		
		}
		//sprawdzamy kolizje z dolną ścianą
		for (int i=0; i<b.length; i++) {
			if (tempB[i].y +Block.size > PlayManager.bottom_y) {
				bottomCollision =true;
			}		
		}
	}
	public void checkStaticBlockCollision() {
		
		for(int i=0; i < PlayManager.staticBlocks.size(); i++) {
			int targetX = PlayManager.staticBlocks.get(i).x;
			int targetY= PlayManager.staticBlocks.get(i).y;
		
			//sprawdzamy kolizje z klockami z dołu
			for (int ii=0; ii<b.length; ii++) {
				if (b[ii].x == targetX && b[ii].y +Block.size == targetY ) {
					bottomCollision =true;
				}
			}
				
			//sprawdzamy kolizje z klockami z lewej
			for (int ii=0; ii<b.length; ii++) {
				if (b[ii].x - Block.size == targetX && b[ii].y == targetY ) {
						leftCollision =true;
					}
			}
			//sprawdzamy kolizje z klockami z prawej
			for (int ii=0; ii<b.length; ii++) {
				if (b[ii].x + Block.size == targetX && b[ii].y == targetY ) {
						rightCollision =true;
					}
			}
		}	
	}
	public void deactivating() {
		deactivateCounter++;
		
		if(deactivateCounter == 30) {
			deactivateCounter=0;
			checkMovementCollision();
			
			if(bottomCollision) {
				active=false;
			}
		}
	}
	
	public void update() {		
		synchronized(KeyHandler.INSTANCE) {
			//sterowanie tetrominem
			if(deactivating) {
				deactivating();
			}
			if(KeyHandler.INSTANCE.upPressed) {
				switch(direction) {
				case 1: getDirection2(); break;
				case 2: getDirection3(); break;
				case 3: getDirection4(); break;
				case 4: getDirection1(); break;
				}
				KeyHandler.INSTANCE.upPressed = false;
			}
			checkMovementCollision(); //zanim poruszymy klockiem sprawdzamy czy nie ma kolizji ze ścianami
			
			if(KeyHandler.INSTANCE.downPressed) {
				if (bottomCollision ==false) { //możemy poruszyć klockiem tylko gdy nie ma kolizji
				for(int i=0; i<4; i++) {
					b[i].y +=Block.size; //przesuwamy klocek do dołu o jedno pole
				}
				autoDropCounter = 0; //gdy strzałka w dół resetujemy autodrop
				}
				
				KeyHandler.INSTANCE.downPressed = false; //po przesunięciu klocka w dół zmieniamy na false
			}
			if(KeyHandler.INSTANCE.leftPressed) {
				if(leftCollision==false) {
					for(int i=0; i<4; i++) {
					b[i].x -=Block.size; //przesuwamy klocek w lewo o jedno pole
					}
				}
				KeyHandler.INSTANCE.leftPressed = false;
			}
			if(KeyHandler.INSTANCE.rightPressed) {
				if(rightCollision==false) {
					for(int i=0; i<4; i++) {
					b[i].x +=Block.size; //przesuwamy klocek w prawo o jedno pole
					}
				}
				KeyHandler.INSTANCE.rightPressed = false;
				
			}
		}
		
		if(bottomCollision) {
			deactivating = true;
		}
		else {
			//automatyczne spadanie
			autoDropCounter++;
			if(autoDropCounter >= PlayManager.dropInterval) {
				b[0].y +=Block.size;
				b[1].y +=Block.size;
				b[2].y +=Block.size;
				b[3].y +=Block.size;
				autoDropCounter = 0;
			}
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