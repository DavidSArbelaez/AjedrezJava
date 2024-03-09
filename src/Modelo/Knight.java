package Modelo;
import java.util.ArrayList;

public class Knight extends IPiece {
    
    public Knight(Position pos, String color) {
        super(pos, color); // Llama al constructor de la superclase IPiece con la posici�n y el color del caballo.
    }

    @Override
    public ArrayList<Square> getValidMoves() {
        ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
        ArrayList<Square> validMoves = new ArrayList<>(); // Inicializa una lista para almacenar los movimientos v�lidos del caballo.

        int currentRow = this.currentPosition.getRow(); // Obtiene la fila actual del caballo.
        int currentColumn = this.currentPosition.getColumn(); // Obtiene la columna actual del caballo.

        int[][] possibleMoves = { // Define una matriz que contiene todos los posibles movimientos del caballo.
            {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
            {-1, -2}, {-1, 2}, {1, -2}, {1, 2}
        };

        for (int[] move : possibleMoves) { // Itera sobre cada posible movimiento del caballo.
            int newRow = currentRow + move[0]; // Calcula la nueva fila para el movimiento.
            int newColumn = currentColumn + move[1]; // Calcula la nueva columna para el movimiento.

            if (board.isSquareValid(newRow, newColumn)) { // Verifica si la nueva posici�n est� dentro de los l�mites del tablero.
                IPiece pieceAtNewPosition = board.getPieceAt(newRow, newColumn); // Obtiene la pieza en la nueva posici�n del tablero.
                if (pieceAtNewPosition == null || !pieceAtNewPosition.color.equals(this.color)) { // Verifica si la nueva posici�n est� vac�a o tiene una pieza del color opuesto.
                    validMoves.add(new Square(new Position(newRow, newColumn), null)); // Agrega la nueva posici�n a los movimientos v�lidos del caballo.
                }
            }
        }

        return validMoves; // Devuelve la lista de movimientos v�lidos del caballo.
    }

    @Override
	public IPiece clone() {
		String c = this.color;
		Position clonedPosition = new Position(currentPosition.getRow(), currentPosition.getColumn());
		Knight clonedPiece = new Knight (clonedPosition, c);
		// No es necesario clonar el ArrayList de validMoves porque no se modifica en
		// esta clase
		return clonedPiece;
	}
}
