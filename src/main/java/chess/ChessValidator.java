package chess;

public interface ChessValidator {

	boolean moveIsValid(int fromCol, int fromRow, int dstCol, int dstRow);

}
