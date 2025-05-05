package tetromino;

import java.awt.Color;

public class TetrominoZigZagL extends Tetromino { 
	
	public TetrominoZigZagL() {
		create(new Color(5,77,251));
	}
	
	public void setXY(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		
		b[1].x = x - Block.size;
		b[1].y = y;
		
		b[2].x = x;
		b[2].y = y - Block.size;
		
		b[3].x = x + Block.size;
		b[3].y = y - Block.size;
	}
	public void updateXY (int direction) {
		
	}
	public void update() {
		
	}
}
