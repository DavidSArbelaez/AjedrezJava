package Modelo;

import java.util.ArrayList;

/*
/*Usar pojo y dto para comunicar las clases
*/
public abstract class IPiece implements Cloneable {
	protected Position currentPosition;
	protected String color;

	public IPiece(Position pos, String color) {
		this.currentPosition = pos;
		this.color = color;
	}

	public void setPosition(Position c) {
		this.currentPosition.setColumna(c.getColumn());
		this.currentPosition.setRow(c.getRow());
	}

	public abstract ArrayList<Square> getValidMoves();

	public Boolean move(Position pos) {
		ArrayList<Square> validMoves = getValidMoves();
		if (validMove(pos, validMoves)) {
			ChessBoard board = ChessBoard.getInstance();
			board.resetSquare(this.currentPosition);
			board.getSquares()[pos.getRow()][pos.getColumn()].setPiece(this);
			return true;
		} else {
			return false;
		}
	}

	public Boolean moveCheck(Position pos) {
		ArrayList<Square> validMoves = getValidMoves();
		return validMove(pos, validMoves) ? true : false;
	}

	/*
	 * Este metodo verifica si el movimiento a realizar esta dentro de los posibles
	 * movimientos, si esta en la lista,significa que es legal por lo tanto es
	 * valido
	 */
	public Boolean validMove(Position pos, ArrayList<Square> validMoves) {
		for (Square s : validMoves) {
			if (s.getPosition().getColumn() == pos.getColumn() && s.getPosition().getRow() == pos.getRow()) {
				return true;
			}
		}

		return false;
	}

	public abstract IPiece clone();

	public void devolverMov(int Row, int Col) {
		this.currentPosition = new Position(Row, Col);
		ChessBoard board = ChessBoard.getInstance();
		board.getSquares()[this.currentPosition.getRow()][this.currentPosition.getColumn()].setPiece(this);
	}
}
