package Modelo;

import java.util.ArrayList;

public class GameRules {

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
		ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				IPiece piece = board.getPieceAt(row, col);
				if (piece instanceof King && piece.color.equals(color)) {
					return piece.currentPosition;
				}
			}
		}
		return null;
	}

	// M�todo para verificar si una casilla est� bajo ataque
	private boolean isSquareUnderAttack(Position position, String attackerColor) {
		ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				IPiece piece = board.getPieceAt(row, col);
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
		ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
		if (!isKingInCheck(color)) {
			return false;
		}
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				IPiece piece = board.getPieceAt(row, col);
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

	// Método para verificar si hay material insuficiente en el tablero
	public boolean insufficientMaterial() {
		ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
		// Contadores para piezas que no sean reyes
		int numNonKingPiecesWhite = 0;
		int numNonKingPiecesBlack = 0;

		// Iterar sobre todas las casillas del tablero
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				IPiece piece = board.getPieceAt(row, col);
				if (piece != null && !(piece instanceof King)) {
					if (piece.color.equals("White")) {
						numNonKingPiecesWhite++;
					} else {
						numNonKingPiecesBlack++;
					}
				}
			}
		}

		// Si solo quedan reyes o un rey y una pieza no rey para ambos lados, es
		// material insuficiente
		if ((numNonKingPiecesWhite == 0 && numNonKingPiecesBlack == 0) ||
				(numNonKingPiecesWhite == 1 && numNonKingPiecesBlack == 0) ||
				(numNonKingPiecesWhite == 0 && numNonKingPiecesBlack == 1)) {
			return true;
		}

		return false;
	}

	public boolean isGameOver() {
		// Verificar si alguno de los jugadores está en jaque mate
		if (isKingInCheckmate("White") || isKingInCheckmate("Black")) {
			return true;
		}

		// Verificar si no hay suficientes piezas para dar jaque mate
		if (insufficientMaterial()) {
			return true;
		}
		return false;
	}
}
