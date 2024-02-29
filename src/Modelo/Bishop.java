package Modelo;

import java.util.ArrayList;

public class Bishop extends IPiece {

	public Bishop(Position pos, String color) {
		super(pos, color); // Llama al constructor de la superclase IPiece con la posici�n y el color del
								// alfil.
	}

	@Override
	public ArrayList<Square> getValidMoves() {
		ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
		ArrayList<Square> validMoves = new ArrayList<>(); // Inicializa una lista para almacenar los movimientos v�lidos
															// del alfil.

		int currentRow = this.currentPosition.getRow(); // Obtiene la fila actual del alfil.
		int currentColumn = this.currentPosition.getColumn(); // Obtiene la columna actual del alfil.

		int[][] directions = { // Define las direcciones en las que el alfil puede moverse (diagonales).
				{ -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

		for (int[] direction : directions) { // Itera sobre cada direcci�n posible del alfil.
			int newRow = currentRow + direction[0]; // Calcula la nueva fila para el movimiento.
			int newColumn = currentColumn + direction[1]; // Calcula la nueva columna para el movimiento.

			while (board.isSquareValid(newRow, newColumn)) { // Mientras la nueva posici�n est� dentro del tablero.
				IPiece pieceAtNewPosition = board.getPieceAt(newRow, newColumn); // Obtiene la pieza en la nueva
																					// posici�n del tablero.
				if (pieceAtNewPosition == null || !pieceAtNewPosition.color.equals(this.color)) { // Verifica si la
																									// nueva posici�n
																									// est� vac�a o
																									// tiene una pieza
																									// del color
																									// opuesto.
					validMoves.add(new Square(new Position(newRow, newColumn), null)); // Agrega la nueva posici�n a los
																						// movimientos v�lidos del
																						// alfil.
				}
				if (pieceAtNewPosition != null) { // Si hay una pieza en la nueva posici�n.
					break; // Termina de buscar movimientos en esta direcci�n.
				}
				// Contin�a movi�ndose en la misma direcci�n.
				newRow += direction[0];
				newColumn += direction[1];
			}
		}

		return validMoves; // Devuelve la lista de movimientos v�lidos del alfil.
	}
}
