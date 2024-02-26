import java.util.ArrayList;

public class GameRules {

    // Método para verificar si el rey está en jaque
    public boolean isKingInCheck(String color) {
    	
    	ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
        Position kingPosition = getKingPosition(color);
        if (kingPosition == null) {
            return false;
        }
        return isSquareUnderAttack(kingPosition, color);
    }

    // Método para obtener la posición del rey
    private Position getKingPosition(String color) {
    	ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
        for (int row =  0; row <  8; row++) {
            for (int col =  0; col <  8; col++) {
                IPiece piece = board.getPieceAt(row +  1, col +  1);
                if (piece instanceof King && piece.color.equals(color)) {
                    return piece.currentPosition;
                }
            }
        }
        return null;
    }

    // Método para verificar si una casilla está bajo ataque
    private boolean isSquareUnderAttack(Position position, String attackerColor) {
    	ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
        for (int row =  0; row <  8; row++) {
            for (int col =  0; col <  8; col++) {
                IPiece piece = board.getPieceAt(row +  1, col +  1);
                if (piece != null && !piece.color.equals(attackerColor)) {
                    ArrayList<Square> validMoves = piece.getValidMoves();
                    for (Square square : validMoves) {
                        if (square.getPosition().equals(position)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Método para verificar si el rey está en jaque mate
    public boolean isKingInCheckmate(String color) {
    	ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
        if (!isKingInCheck(color)) {
            return false;
        }
        for (int row =  0; row <  8; row++) {
            for (int col =  0; col <  8; col++) {
                IPiece piece = board.getPieceAt(row +  1, col +  1);
                if (piece != null && piece.color.equals(color)) {
                    ArrayList<Square> validMoves = piece.getValidMoves();
                    for (Square square : validMoves) {
                        Position originalPosition = piece.currentPosition;
                        piece.setPosition(square.getPosition());
                        if (!isKingInCheck(color)) {
                            piece.setPosition(originalPosition);
                            return false;
                        }
                        piece.setPosition(originalPosition);
                    }
                }
            }
        }
        return true;
    }
}
