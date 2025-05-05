package tetromino;

import java.awt.Color;

public class TetrominoBar extends Tetromino{
	
	public TetrominoBar() {
		create(new Color(143,57,209));
	}
	
	public void setXY(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		b[1].x = x;
		b[1].y = y - Block.size;
		b[2].x = x;
		b[2].y = y -2* Block.size;
		b[3].x = x;
		b[3].y = y -3* Block.size;
	}
	public void updateXY (int direction) {
		
	}
	public void update() {
		
	}
}

