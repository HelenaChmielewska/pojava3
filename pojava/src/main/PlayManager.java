package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.ArrayList;

import tetromino.Tetromino;
import tetromino.TetrominoL;
import tetromino.TetrominoLReversed;
import tetromino.TetrominoT;
import tetromino.TetrominoBar;
import tetromino.TetrominoSquare;
import tetromino.TetrominoZigZag1;
import tetromino.TetrominoZigZag2;
import tetromino.Block;

public class PlayManager {
	//play area
	final int WIDTH = 360;
	final int HEIGHT = 600;
	public static int left_x;
	public static int right_x;
	public static int top_y;
	public static int bottom_y;
	
	//tetromino
	Tetromino currentMino;
	final int MINO_START_X;
	final int MINO_START_Y;
	Tetromino nextMino;
	final int NEXTMINO_X;
	final int NEXTMINO_Y;
	public static ArrayList<Block> staticBlocks = new ArrayList(); //do tej listy dodajemy nieaktywne klocki
	
	
	public static int dropInterval = 60;
	boolean gameOver;
	int score;
	int lines;
	
	public PlayManager() {
		left_x = (MainFrame.WIDTH/2) - (WIDTH/2);
		right_x = left_x+WIDTH;
		top_y = 50;
		bottom_y = top_y + HEIGHT;
		
		MINO_START_X = left_x + (WIDTH/2) - Block.size;
		MINO_START_Y = top_y + Block.size;
		
		NEXTMINO_X = right_x + 175;
		NEXTMINO_Y = top_y + 500;
		
		currentMino = pickMino();
		//currentMino = new TetrominoL();
		currentMino.setXY(MINO_START_X, MINO_START_Y);
		nextMino = pickMino();
		nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
	}
	
	private Tetromino pickMino() {
		//wybieramy losowy kształt
		Tetromino mino = null;
		int i = new Random().nextInt(7); //losowa liczba od 0 do 6
		
		switch(i) {
		case 0: mino = new TetrominoL(); break;
		case 1: mino = new TetrominoLReversed(); break;
		case 2: mino = new TetrominoT(); break;
		case 3: mino = new TetrominoBar(); break;
		case 4: mino = new TetrominoSquare(); break;
		case 5: mino = new TetrominoZigZag1(); break;
		case 6: mino = new TetrominoZigZag2(); break;
		}
		return mino;
	}
	
	public void update() {
		//sprawdzamy czy tetromino jest aktywny
		if (currentMino.active == false) {
			
			//jeśli nie jest aktywny dodajemy go do listy staticBlocks
			for (int i=0; i<4;i++) {
				staticBlocks.add(currentMino.b[i]); 
			}
			
			if(currentMino.b[0].x == MINO_START_X && currentMino.b[0].y==MINO_START_Y) {
				gameOver=true;
			}
			
			currentMino.deactivating = false;
			
			//zamieniamy currentMino na nextMino
			currentMino = nextMino;
			currentMino.setXY(MINO_START_X, MINO_START_Y);
			nextMino = pickMino();
			nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
			
			//jeśli klocek sie zatrzymał (active=false) to sprawdzamy czy mozna skosowac linie
			deleteLine();
			
		}
		else {
			currentMino.update();
		}
	}
	public void deleteLine(){
		int x = left_x;
		int y = top_y;
		int blockCounter = 0;
		int lineCounter=0;
		
		while (x < right_x && y < bottom_y) {
			
			for(int i=0; i<staticBlocks.size(); i++) {
				if(staticBlocks.get(i).x == x && staticBlocks.get(i).y == y ) {
					blockCounter++;
				}
			}
			x+=Block.size;
			
			if(x == right_x) {
				
				if(blockCounter == 12) {
				
					for(int i=staticBlocks.size()-1; i>-1; i--) {
						if(staticBlocks.get(i).y == y ) {
							staticBlocks.remove(i);
						}
					}
					lineCounter++;
					lines++;
					//co każde 5 lini klocek spada szybciej o 10 razy na sekundę
					if(lines % 5 == 0 && dropInterval > 1) {
						if(dropInterval>10) {
								dropInterval -= 10;
						}
						else {
							dropInterval -= 1;
						}
					}
					for(int i=0; i<staticBlocks.size(); i++) {
						if(staticBlocks.get(i).y < y ) {
							staticBlocks.get(i).y +=Block.size;
						}
					}
				}
				blockCounter=0;
				x = left_x;
				y+=Block.size;
			}
		}
		if (lineCounter > 0) {
		       score += lineCounter * 100;
		       System.out.println("Aktualny wynik: " + score);
		}
		
	}
	
	/* Coś tu narazie nie dziala xd
	 * public void resetGame() {
	    staticBlocks.clear();
	    currentMino = pickMino();
	    currentMino.setXY(MINO_START_X, MINO_START_Y);
	    nextMino = pickMino();
	    nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
	    score = 0;
	    lines = 0;
	    dropInterval=60;
	    gameOver = false;
	    
	} */
	public void draw(Graphics2D g2) {
		//okienko gry
		g2.setColor(new Color(0,0,153));
		g2.setStroke(new BasicStroke(4f));
		g2.fillRect(left_x-4,top_y-4, WIDTH+8,HEIGHT+8);
		
		//rysowanie tetromino spadającego
		if(currentMino != null) {
			currentMino.draw(g2);
		}
		
		//rysowanie następnego tetromino
		//nextMino.draw(g2);
		
		//rysowanie staticBlocks
		for(int i =0; i<staticBlocks.size(); i++) {
			staticBlocks.get(i).draw(g2);
		}
		
		//napis paused i gameover
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(50f));
		if(gameOver) {
			int x = left_x + 35;
			int y = top_y + 320;
			g2.drawString("GAMEOVER", x, y);
		}
		else if(KeyHandler.INSTANCE.pausePressed) {
			int x = left_x + 80;
			int y = top_y + 320;
			g2.drawString("PAUSED", x, y);	
		}
	}
}
