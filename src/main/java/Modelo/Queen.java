package Modelo;
import java.util.ArrayList;

public class Queen extends IPiece {
    public Queen(Position pos, String color) {
        super(pos, color);
    }

    @Override
    public ArrayList<Square> getValidMoves() {
        ChessBoard board = ChessBoard.getInstance();
        ArrayList<Square> validMoves = new ArrayList<>();

        int currentRow = this.currentPosition.getRow();
        int currentColumn = this.currentPosition.getColumn();

        // Direcciones posibles para la reina
        int[] rowDirections = {-1,  0,  1};
        int[] colDirections = {-1,  0,  1};

        for (int rowDirection : rowDirections) {
            for (int colDirection : colDirections) {
                if (rowDirection ==  0 && colDirection ==  0) {
                    continue; // Skip the current position
                }
                int newRow = currentRow + rowDirection;
                int newColumn = currentColumn + colDirection;

                // Verificar si la nueva posici�n es v�lida y si no hay otra pieza en esa posici�n
                while (board.isSquareValid(newRow, newColumn)) {
                    IPiece pieceAtNewPosition = board.getPieceAt(newRow, newColumn);
                    if (pieceAtNewPosition == null || !pieceAtNewPosition.color.equals(this.color)) {
                        validMoves.add(new Square(new Position(newRow, newColumn), null));
                    }
                    if (pieceAtNewPosition != null) {
                        break; // No need to check further in this direction
                    }
                    newRow += rowDirection;
                    newColumn += colDirection;
                }
            }
        }

        return validMoves;
    }

    @Override
	public IPiece clone() {
		String c = this.color;
		Position clonedPosition = new Position(currentPosition.getRow(), currentPosition.getColumn());
		Queen clonedPiece = new Queen(clonedPosition, c);
		// No es necesario clonar el ArrayList de validMoves porque no se modifica en
		// esta clase
		return clonedPiece;
	}
}
