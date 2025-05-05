package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import tetromino.Tetromino;
import tetromino.TetrominoL;
import tetromino.Block;

public class PlayManager {
	final int WIDTH = 360;
	final int HEIGHT = 600;
	public static int left_x;
	public static int right_x;
	public static int top_y;
	public static int bottom_y;
	
	Tetromino currentMino;
	final int MINO_START_X;
	final int MINO_START_Y;
	public static int dropInterval = 60;
	
	public PlayManager() {
		left_x = (MainFrame.WIDTH/2) - (WIDTH/2);
		right_x = left_x+WIDTH;
		top_y = 50;
		bottom_y = top_y + HEIGHT;
		
		MINO_START_X = left_x + (WIDTH/2) - Block.size;
		MINO_START_Y = top_y + Block.size;
		
		currentMino = new TetrominoL();
		currentMino.setXY(MINO_START_X, MINO_START_Y);
	}
	public void update() {
		currentMino.update();
	
	}
	public void draw(Graphics2D g2) {
		g2.setColor(Color.blue);
		g2.setStroke(new BasicStroke(4f));
		g2.fillRect(left_x-4,top_y-4, WIDTH+8,HEIGHT+8);
		
		if(currentMino != null) {
			currentMino.draw(g2);
		}
	}
}
