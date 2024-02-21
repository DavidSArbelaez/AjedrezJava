import java.util.ArrayList;

public class Bishop extends IPiece {
    
    public Bishop(int row, int col, String color) {
        super(row, col, color); // Llama al constructor de la superclase IPiece con la posición y el color del alfil.
    }

    @Override
    public ArrayList<Square> getValidMoves() {
        ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
        ArrayList<Square> validMoves = new ArrayList<>(); // Inicializa una lista para almacenar los movimientos válidos del alfil.

        int currentRow = this.currentPosition.getRow(); // Obtiene la fila actual del alfil.
        int currentColumn = this.currentPosition.getColumn(); // Obtiene la columna actual del alfil.

        int[][] directions = { // Define las direcciones en las que el alfil puede moverse (diagonales).
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        for (int[] direction : directions) { // Itera sobre cada dirección posible del alfil.
            int newRow = currentRow + direction[0]; // Calcula la nueva fila para el movimiento.
            int newColumn = currentColumn + direction[1]; // Calcula la nueva columna para el movimiento.

            while (board.isSquareValid(newRow, newColumn)) { // Mientras la nueva posición esté dentro del tablero.
                IPiece pieceAtNewPosition = board.getPieceAt(newRow, newColumn); // Obtiene la pieza en la nueva posición del tablero.
                if (pieceAtNewPosition == null || !pieceAtNewPosition.color.equals(this.color)) { // Verifica si la nueva posición está vacía o tiene una pieza del color opuesto.
                    validMoves.add(new Square(new Position(newRow, newColumn), null)); // Agrega la nueva posición a los movimientos válidos del alfil.
                }
                if (pieceAtNewPosition != null) { // Si hay una pieza en la nueva posición.
                    break; // Termina de buscar movimientos en esta dirección.
                }
                // Continúa moviéndose en la misma dirección.
                newRow += direction[0];
                newColumn += direction[1];
            }
        }

        return validMoves; // Devuelve la lista de movimientos válidos del alfil.
    }
}
