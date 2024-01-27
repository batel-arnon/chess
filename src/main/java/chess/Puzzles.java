package chess;

public class Puzzles implements ChessValidator {
	Board b;
	private int stage = 1;
	PlaceHolder[][]state = new PlaceHolder[8][8];
	public void puzzle1() {
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				state[i][j] = new PlaceHolder(false);
			}
		}
		state[0][0] = new King(true);
		state[1][7] = new Rook(false);
		state[2][2] = new King(false);
		b = new Board(state,true,this);
	}
	public static void main(String[] args) {
		new Puzzles().puzzle1();
	}
	@Override
	public boolean moveIsValid(int fromCol, int fromRow, int dstCol, int dstRow) {
		if (stage == 1) {
			if (fromCol==2 && fromRow==2 && dstCol == 1 && dstRow == 2) {
				state[2][1] = state[2][2];
				state[0][1] = state[0][0];
				state[2][2] = new PlaceHolder(false);
				state[0][0] = new PlaceHolder(false);
				stage = 2;
				b.setPieces(state);
				return true;
			}
			return false;
		}
		else if (stage == 2) {
			if (fromCol==7 && fromRow==1 && dstCol == 7 && dstRow == 0) {
				state[0][7] = state[1][7];
				state[1][7] = new PlaceHolder(false);
				stage = 3;
				b.setPieces(state);
				System.out.println("win!");
				System.exit(stage);
				return true;
			}
			return false;
		}
		return false;
	}

}
