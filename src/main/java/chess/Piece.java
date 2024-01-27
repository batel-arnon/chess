package chess;

public class Piece extends PlaceHolder{
	private boolean isWhite;
	
	public Piece(boolean isW) {
		super(true);
		this.isWhite = isW;
	}
	public String getText() {
		return getClass().getSimpleName();
	}
	public boolean getIsWhite() {
		return this.isWhite;
	}
}
