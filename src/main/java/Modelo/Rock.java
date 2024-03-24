package Modelo;
import java.util.ArrayList;

public class Rock extends IPiece {
    
    public Rock(Position pos, String color) {
        super(pos, color); // Llama al constructor de la superclase IPiece con la posici�n y el color de la torre.
    }

    @Override
    public ArrayList<Square> getValidMoves() {
        ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
        ArrayList<Square> validMoves = new ArrayList<>(); // Inicializa una lista para almacenar los movimientos v�lidos de la torre.

        int currentRow = this.currentPosition.getRow(); // Obtiene la fila actual de la torre.
        int currentColumn = this.currentPosition.getColumn(); // Obtiene la columna actual de la torre.

        int[][] directions = { // Define las direcciones en las que la torre puede moverse (horizontal y vertical).
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        for (int[] direction : directions) { // Itera sobre cada direcci�n posible de la torre.
            int newRow = currentRow + direction[0]; // Calcula la nueva fila para el movimiento.
            int newColumn = currentColumn + direction[1]; // Calcula la nueva columna para el movimiento.

            while (board.isSquareValid(newRow, newColumn)) { // Mientras la nueva posici�n est� dentro del tablero.
                IPiece pieceAtNewPosition = board.getPieceAt(newRow, newColumn); // Obtiene la pieza en la nueva posici�n del tablero.
                if (pieceAtNewPosition == null || !pieceAtNewPosition.color.equals(this.color)) { // Verifica si la nueva posici�n est� vac�a o tiene una pieza del color opuesto.
                    validMoves.add(new Square(new Position(newRow, newColumn), null)); // Agrega la nueva posici�n a los movimientos v�lidos de la torre.
                }
                if (pieceAtNewPosition != null) { // Si hay una pieza en la nueva posici�n.
                    break; // Termina de buscar movimientos en esta direcci�n.
                }
                // Contin�a movi�ndose en la misma direcci�n.
                newRow += direction[0];
                newColumn += direction[1];
            }
        }

        return validMoves; // Devuelve la lista de movimientos v�lidos de la torre.
    }

    @Override
	public IPiece clone() {
		String c = this.color;
		Position clonedPosition = new Position(currentPosition.getRow(), currentPosition.getColumn());
		Rock clonedPiece = new Rock(clonedPosition, c);
		// No es necesario clonar el ArrayList de validMoves porque no se modifica en
		// esta clase
		return clonedPiece;
	}
}
