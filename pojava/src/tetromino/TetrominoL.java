package tetromino;
import java.awt.Color;


public class TetrominoL extends Tetromino{
	
	public TetrominoL() {
		create(new Color(240,47,239));
	}
	
	public void setXY(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		b[1].x = b[0].x;
		b[1].y = b[0].y - Block.size;
		b[2].x = b[0].x;
		b[2].y = b[0].y + Block.size;
		b[3].x = b[0].x + Block.size;
		b[3].y = b[0].y + Block.size;
	}
}
