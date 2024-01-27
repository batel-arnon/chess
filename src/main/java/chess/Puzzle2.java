package chess;

public class Puzzle2 implements ChessValidator{
	Board b;
	private int stage = 1;
	PlaceHolder[][]state = new PlaceHolder[8][8];
	public void puzzle2() {
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				state[i][j] = new PlaceHolder(false);
			}
		}
		state[7][0] = new Rook(false);
		state[7][1] = new Knight(false);
		state[7][3] = new Rook(false);
		state[7][6] = new King(false);
		state[6][1] = new Pawn(false);
		state[6][2] = new Queen(false);
		state[6][5] = new Pawn(false);
		state[6][7] = new Pawn(false);
		state[5][0] = new Pawn(false);
		state[5][3] = new Pawn(false);
		state[5][5] = new Bishop(false);
		state[5][6] = new Pawn(false);
		state[5][7] = new Bishop(true);
		state[4][3] = new Pawn(true);
		state[3][0] = new Pawn(true);
		state[3][4] = new Queen(true);
		state[2][2] = new Pawn(true);
		state[2][3] = new Bishop(true);
		state[1][5] = new Pawn(true);
		state[1][6] = new Pawn(true);
		state[1][7] = new Pawn(true);
		state[0][2] = new Rook(true);
		state[0][4] = new Rook(true);
		state[2][6] = new King(true);
		b = new Board(state,false,this);
	}
	public static void main(String[] args) {
		new Puzzle2().puzzle2();
	}
	@Override
	public boolean moveIsValid(int fromCol, int fromRow, int dstCol, int dstRow) {
		if (stage == 1) {
			if (fromCol==4 && fromRow==3 && dstCol == 4 && dstRow == 7) {
				state[7][4] = state[3][4];
				state[7][4] = state[7][3];
				state[3][4] = new PlaceHolder(false);
				state[7][3] = new PlaceHolder(false);
				stage = 2;
				b.setPieces(state);
				return true;
			}
			return false;
		}
		else if (stage == 2) {
			if (fromCol==4 && fromRow==0 && dstCol == 4 && dstRow == 7) {
				state[7][4] = state[0][4];
				state[0][4] = new PlaceHolder(false);
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
