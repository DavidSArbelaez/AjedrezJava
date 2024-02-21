import java.util.ArrayList;

public class Knight extends IPiece {
    
    public Knight(int row, int col, String color) {
        super(row, col, color); // Llama al constructor de la superclase IPiece con la posición y el color del caballo.
    }

    @Override
    public ArrayList<Square> getValidMoves() {
        ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
        ArrayList<Square> validMoves = new ArrayList<>(); // Inicializa una lista para almacenar los movimientos válidos del caballo.

        int currentRow = this.currentPosition.getRow(); // Obtiene la fila actual del caballo.
        int currentColumn = this.currentPosition.getColumn(); // Obtiene la columna actual del caballo.

        int[][] possibleMoves = { // Define una matriz que contiene todos los posibles movimientos del caballo.
            {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
            {-1, -2}, {-1, 2}, {1, -2}, {1, 2}
        };

        for (int[] move : possibleMoves) { // Itera sobre cada posible movimiento del caballo.
            int newRow = currentRow + move[0]; // Calcula la nueva fila para el movimiento.
            int newColumn = currentColumn + move[1]; // Calcula la nueva columna para el movimiento.

            if (board.isSquareValid(newRow, newColumn)) { // Verifica si la nueva posición está dentro de los límites del tablero.
                IPiece pieceAtNewPosition = board.getPieceAt(newRow, newColumn); // Obtiene la pieza en la nueva posición del tablero.
                if (pieceAtNewPosition == null || !pieceAtNewPosition.color.equals(this.color)) { // Verifica si la nueva posición está vacía o tiene una pieza del color opuesto.
                    validMoves.add(new Square(new Position(newRow, newColumn), null)); // Agrega la nueva posición a los movimientos válidos del caballo.
                }
            }
        }

        return validMoves; // Devuelve la lista de movimientos válidos del caballo.
    }
}
