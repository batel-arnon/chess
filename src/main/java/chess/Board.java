package chess;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Board extends JFrame {
	public static int size = 8;
	private PlaceHolder[][] board = new PlaceHolder[size][size];
	private boolean isBlackShouldPlayNow;
	private JButton[][] boardButtons;
	private int fromCol = -1;
	private int fromRow = -1;
	private int dstCol = -1;
	private int dstRow = -1;
	private ChessValidator validator;
	
	public Board() {
		this.board =new PlaceHolder[][]{{new Rook(true),new Knight(true),new Bishop(true), new King(true), new Queen(true),new Bishop(true),new Knight(true),new Rook(true),},
			{new Pawn(true), new Pawn(true),new Pawn(true),new Pawn(true),new Pawn(true),new Pawn(true),new Pawn(true),new Pawn(true)},
			{new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false)},
			{new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false)},
			{new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false)},
			{new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false)},
			{new Pawn(true), new Pawn(false),new Pawn(false),new Pawn(false),new Pawn(false),new Pawn(false),new Pawn(false),new Pawn(false)},
			{new Rook(false),new Knight(false),new Bishop(false), new King(false), new Queen(false),new Bishop(false),new Knight(false),new Rook(false)}};
		this.isBlackShouldPlayNow = false;
		setLayout(new GridLayout(size,size));
		boardButtons=new Button[size][size];
		Action ba=new BoardAction();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				boardButtons[i][j]=new Button(i,j);
				boardButtons[i][j].setAction(ba);
				this.add(boardButtons[i][j]);
			}			
		}
		setPieces(board);
		setVisible(true);
		setSize(size*200, 200*size);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public Board(PlaceHolder[][]board, boolean isBlack, ChessValidator validator) {
		this.validator=validator;
		this.board = board;
		this.isBlackShouldPlayNow = isBlack;
		setLayout(new GridLayout(size,size));
		boardButtons=new Button[size][size];
		Action ba=new BoardAction();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				boardButtons[i][j]=new Button(i,j);
				boardButtons[i][j].setBackground((i+j)%2==0?Color.WHITE:Color.DARK_GRAY);
				boardButtons[i][j].setForeground((i+j)%2==0?Color.DARK_GRAY:Color.WHITE);
				boardButtons[i][j].setAction(ba);
				this.add(boardButtons[i][j]);
			}			
		}
		setPieces(board);
		validate();
		setVisible(true);
		setSize(size*200, 200*size);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setPieces(PlaceHolder[][] board) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				//boardButtons[i][j].setIcon(new ImageIcon("King.jpeg"));
				if (board[i][j] instanceof Piece) {
					boardButtons[i][j].setText(board[i][j].getText() + ((((Piece)(board[i][j])).getIsWhite())?"- white":"- Black"));
				}
			}
		}
	}
	public void setBoard(PlaceHolder[][]board, boolean isBlack) {
		this.board = board;
		this.isBlackShouldPlayNow = isBlack;
		setPieces(board);
		validate();
		fromCol = -1;
		fromRow = -1;
		dstCol = -1;
		dstRow = -1;
	}
	public void changeBoard() {
		if(!(this.board[dstRow][dstCol] instanceof Piece)) {
			this.board[dstRow][dstCol] = this.board[fromRow][fromCol];
			this.board[fromRow][fromCol] = new PlaceHolder(false);
			isBlackShouldPlayNow = !isBlackShouldPlayNow;
			setBoard(board,isBlackShouldPlayNow);
		}
		else
			System.out.println("Error. Choose another destination.");
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Board b = new Board();
		Thread.sleep(3000);
		PlaceHolder[][] board1 = new PlaceHolder[][]{{new Rook(true),new Knight(true),new Bishop(true), new King(true), new Queen(true),new Bishop(true),new Knight(true),new Rook(true),},
			{new Pawn(true), new Pawn(true),new Pawn(true),new Pawn(true),new Pawn(true),new Pawn(true),new Pawn(true),new Pawn(true)},
			{new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false)},
			{new Rook(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false)},
			{new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false)},
			{new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false),new PlaceHolder(false)},
			{new Pawn(true), new Pawn(false),new Pawn(false),new Pawn(false),new Pawn(false),new Pawn(false),new Pawn(false),new Pawn(false)},
			{new Rook(false),new Knight(false),new Bishop(false), new King(false), new Queen(false),new Bishop(false),new Knight(false),new Rook(false)}};
		b.setBoard(board1, false);
	}
	
	
	class BoardAction implements Action{


		@Override
		public void actionPerformed(ActionEvent e) {
			Button src = ((Button)e.getSource());
			src.setBackground((src.getBackground()==Color.WHITE || src.getBackground()==Color.DARK_GRAY)?Color.YELLOW:(src.getCol()+src.getRow()%2==0?Color.WHITE:Color.DARK_GRAY));
			if (fromCol == -1 && src.getBackground()==Color.YELLOW) {
				fromCol=src.getCol();
				fromRow = src.getRow();
			}
			else if(src.getBackground()==Color.WHITE || src.getBackground()==Color.DARK_GRAY) {
				fromCol = -1;
				fromRow = -1;		
			}
			else {
				dstCol=src.getCol();
				dstRow=src.getRow();
				boardButtons[fromRow][fromCol].setBackground((fromCol+fromRow)%2==0?Color.WHITE:Color.DARK_GRAY);
				boardButtons[dstRow][dstCol].setBackground((dstCol+dstRow)%2==0?Color.WHITE:Color.DARK_GRAY);
				if (!validator.moveIsValid(fromCol,fromRow,dstCol,dstRow))
				{
					System.out.println("wrong. try again");
				}
				fromCol = -1;
				fromRow = -1;
				dstCol = -1;
				dstRow = -1;
			}
		}

		@Override
		public Object getValue(String key) {
			return null;
		}

		@Override
		public void putValue(String key, Object value) {
		}

		@Override
		public void setEnabled(boolean b) {
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		@Override
		public void addPropertyChangeListener(PropertyChangeListener listener) {
		}

		@Override
		public void removePropertyChangeListener(PropertyChangeListener listener) {
		}
	}
}
