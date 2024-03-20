package Modelo;

import java.util.ArrayList;

public class King extends IPiece {
	public King(Position pos, String color) {
		super(pos, color);
	}

	@Override
	public ArrayList<Square> getValidMoves() {
		ChessBoard board = ChessBoard.getInstance();
		ArrayList<Square> validMoves = new ArrayList<>();

		// Direcciones posibles para el rey
		int[] rowDirections = { -1, 0, 1 };
		int[] colDirections = { -1, 0, 1 };

		int currentRow = this.currentPosition.getRow();
		int currentColumn = this.currentPosition.getColumn();

		for (int rowDirection : rowDirections) {
			for (int colDirection : colDirections) {
				int newRow = currentRow + rowDirection;
				int newColumn = currentColumn + colDirection;

				// Verificar si la nueva posici�n es v�lida
				if (board.isSquareValid(newRow, newColumn)) {
					IPiece pieceAtNewPosition = board.getPieceAt(newRow, newColumn);
					// Verificar si la pieza en la nueva posici�n es de otro color o si la casilla
					// est� vac�a
					if (pieceAtNewPosition == null || pieceAtNewPosition.color != this.color) {
						validMoves.add(new Square(new Position(newRow, newColumn), null));
					}
				}
			}
		}

		return validMoves;
	}

	@Override
	public IPiece clone() {
		String c = this.color;
		Position clonedPosition = new Position(currentPosition.getRow(), currentPosition.getColumn());
		King clonedPiece = new King(clonedPosition, c);
		// No es necesario clonar el ArrayList de validMoves porque no se modifica en
		// esta clase
		return clonedPiece;
	}
}
