import java.util.ArrayList;

public class GameRules {
    private ChessBoard board;

    public GameRules(ChessBoard board) {
        this.board = board;
    }

    // M�todo para verificar si el rey est� en jaque
    public boolean isKingInCheck(String color) {
        Position kingPosition = getKingPosition(color);
        if (kingPosition == null) {
            return false;
        }
        return isSquareUnderAttack(kingPosition, color);
    }

    // M�todo para obtener la posici�n del rey
    private Position getKingPosition(String color) {
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

    // M�todo para verificar si una casilla est� bajo ataque
    private boolean isSquareUnderAttack(Position position, String attackerColor) {
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

    // M�todo para verificar si el rey est� en jaque mate
    public boolean isKingInCheckmate(String color) {
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
