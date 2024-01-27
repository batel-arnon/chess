package chess;

import javax.swing.JButton;

public class Button extends JButton{
	private int row;
	private int col;
	public Button(int x, int y) {
		super();
		this.row = x;
		this.col = y;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int x) {
		this.row = x;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int y) {
		this.col = y;
	}
	
	
}
