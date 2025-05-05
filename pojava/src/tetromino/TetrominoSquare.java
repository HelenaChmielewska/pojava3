package tetromino;

import java.awt.Color;

public class TetrominoSquare extends Tetromino{
	
	public TetrominoSquare() {
		create(new Color(29,166,234));
	}
	
	public void setXY(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		
		b[1].x = x + Block.size; 
		b[1].y = y;
		
		b[2].x = x + Block.size;
		b[2].y = y - Block.size;
		
		b[3].x = x;
		b[3].y = y -Block.size;
	}
	public void updateXY (int direction) {
		
	}
	public void update() {
		
	}
}

