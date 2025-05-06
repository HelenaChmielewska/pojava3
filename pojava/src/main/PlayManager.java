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
		int i = new Random().nextInt(5); //losowa liczba od 0 do 6
		
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
			
			//zamieniamy currentMino na nextMino
			currentMino = nextMino;
			currentMino.setXY(MINO_START_X, MINO_START_Y);
			nextMino = pickMino();
			nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
			
		}
		else {
			currentMino.update();
		}
	}
	
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
		
	}
}
