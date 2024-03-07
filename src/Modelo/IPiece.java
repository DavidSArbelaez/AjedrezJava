package Modelo;

import java.util.ArrayList;

/*
/*Usar pojo y dto para comunicar las clases
*/
public abstract class IPiece implements Cloneable{
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
			board.resetSquare(pos);
			board.getSquares()[pos.getRow()][pos.getColumn()].setPiece(this);
			return true;
		} else {
			return false;
		}
	}

	public Boolean moveCheck(Position pos){
		ArrayList<Square> validMoves = getValidMoves();
		return validMove(pso,validMoves)?true:false;
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
	
	@Override
    public Object clone() throws CloneNotSupportedException {
        IPiece clonedPiece = (IPiece) super.clone();
        // Realiza una copia profunda de los campos necesarios
        clonedPiece.currentPosition = new Position(currentPosition.getRow(), currentPosition.getColumn());
        clonedPiece.color = new String(color); // Si color es mutable, necesitarás una copia profunda también
        // No es necesario clonar el ArrayList de validMoves porque no se modifica en esta clase
        return clonedPiece;
    }
	

}
