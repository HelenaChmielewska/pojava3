package tetromino;

import java.awt.Color;

public class TetrominoLReversed extends Tetromino {
	
	public TetrominoLReversed() {
		create(new Color(255,163,0));
	}
	
	public void setXY(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		b[1].x = x;
		b[1].y = y - Block.size;
		b[2].x = x;
		b[2].y = y -2* Block.size;
		b[3].x = x - Block.size;
		b[3].y = y -2* Block.size;
	}
	public void updateXY (int direction) {
		
	}
	public void update() {
		
	}
}

