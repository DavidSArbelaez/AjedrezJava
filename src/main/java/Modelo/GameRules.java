package Modelo;

import java.util.ArrayList;

public class GameRules {

	// M�todo para verificar si el rey est� en jaque

	public boolean isKingInCheck(String color, String atColor) {
		Position kingPosition = getKingPosition(color);
		if (kingPosition == null) {
			return false;
		}
		return isSquareUnderAttack(kingPosition, atColor);
	}

	// M�todo para obtener la posici�n del rey
	private Position getKingPosition(String color) {
		ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del tablero de ajedrez.
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				IPiece piece = board.getPieceAt(row, col);
				if (piece instanceof King && piece.color.compareToIgnoreCase(color) == 0 && piece != null) {
					return piece.currentPosition;
				}
			}
		}
		return null;
	}

	private boolean isSquareUnderAttack(Position position, String attackerColor) {
		ChessBoard board = ChessBoard.getInstance();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				IPiece piece = board.getPieceAt(row, col);
				if (piece != null && piece.color.compareToIgnoreCase(attackerColor) == 0) {
					ArrayList<Square> validMoves = piece.getValidMoves();
					// System.out.println("Movimientos:"+validMoves.size()+"
					// "+piece.getClass().getName()+piece.color);
					for (Square square : validMoves) {
						if (square.getPosition().getColumn() == position.getColumn()
								&& square.getPosition().getRow() == position.getRow()) {
							System.out.println("JAQUEEEEE HPTAAA");
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private IPiece getAttackingPiece(Position kingPosition, String attackerColor) {
		ChessBoard board = ChessBoard.getInstance();

		// Recorre todas las piezas del tablero
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				IPiece piece = board.getPieceAt(row, col);
				// Verifica si la pieza existe y si es del color del atacante
				if (piece != null && piece.color.equalsIgnoreCase(attackerColor)) {
					// Obtiene los movimientos válidos de la pieza
					ArrayList<Square> validMoves = piece.getValidMoves();
					// Comprueba si alguno de los movimientos válidos de la pieza ataca la posición
					// del rey
					for (Square square : validMoves) {
						if (square.getPosition().equals(kingPosition)) {
							// La pieza ataca la posición del rey
							return piece;
						}
					}
				}
			}
		}

		// Si no se encontró ninguna pieza atacando la posición del rey, devuelve null
		return null;
	}

	private ArrayList<Position> getSafeKingMoves(Position kingPosition, String attackerColor) {
		ChessBoard board = ChessBoard.getInstance();
		ArrayList<Position> safeMoves = new ArrayList<>();

		// Obtener la instancia del rey en la posición dada
		IPiece king = board.getPieceAt(kingPosition.getRow(), kingPosition.getColumn());
		if (king == null || !(king instanceof King)) {
			return safeMoves; // Si no hay un rey en la posición dada, retornar una lista vacía
		}

		// Obtener los movimientos válidos del rey
		ArrayList<Square> validMoves = king.getValidMoves();

		// Iterar sobre los movimientos válidos del rey
		for (Square square : validMoves) {
			Position destination = square.getPosition();

			// Simular el movimiento del rey a la posición de destino
			boolean capturedPiece = board.movePiece(kingPosition.getRow(), kingPosition.getColumn(),
					destination.getRow(), destination.getColumn(), new Player(king.color));
			if (capturedPiece) {
				// Verificar si el rey sigue estando en jaque después del movimiento
				boolean isInCheck = isSquareUnderAttack(destination, attackerColor);

				// Revertir el movimiento
				IPiece pi = board.getPieceAt(destination.getRow(), destination.getColumn());
				pi.devolverMov(kingPosition.getRow(), kingPosition.getColumn());
				board.erracePiece(destination.getRow(), destination.getColumn());
				// Si el rey no está en jaque después del movimiento, agregar la posición como
				// movimiento seguro
				if (!isInCheck) {
					safeMoves.add(destination);
				}
			}

		}

		return safeMoves;
	}

	public boolean isKingInCheckmate(String color, String colorat) {
		ChessBoard board = ChessBoard.getInstance(); // Obtener la instancia del tablero de ajedrez.
		if (!isKingInCheck(color, colorat)) {
			return false; // El rey no está en jaque, por lo que no puede estar en jaque mate.
		}

		// Verificar si el rey puede moverse a una posición segura
		Position kingPosition = getKingPosition(color);
		ArrayList<Position> safePositions = getSafeKingMoves(kingPosition, colorat);
		if (!safePositions.isEmpty()) {
			return false; // El rey puede escapar del jaque moviéndose a una posición segura.
		}

		// Verificar si alguna pieza aliada puede bloquear o capturar la pieza que pone
		// en jaque al rey
		ArrayList<Position> blockingPositions = getBlockingPositions(kingPosition, color, colorat);
		if (!blockingPositions.isEmpty()) {
			return false; // Existe una pieza que puede bloquear o capturar la amenaza al rey.
		}

		// Si ninguna de las condiciones anteriores se cumple, entonces el rey está en
		// jaque mate
		return true;
	}

	private ArrayList<Position> getBlockingPositions(Position kingPosition, String kingColor, String attackerColor) {
		ArrayList<Position> blockingPositions = new ArrayList<>();
		ChessBoard board = ChessBoard.getInstance();

		// Obtener la posición de la pieza que pone en jaque al rey
		IPiece attacker = getAttackingPiece(kingPosition, attackerColor);
		if (attacker == null) {
			return blockingPositions; // No hay una pieza que ponga en jaque al rey
		}

		// Obtener las posiciones de las piezas aliadas que pueden bloquear o capturar
		// al atacante
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				IPiece piece = board.getPieceAt(row, col);
				if (piece != null && piece.color.equalsIgnoreCase(kingColor)) {
					ArrayList<Square> validMoves = piece.getValidMoves();
					for (Square square : validMoves) {
						// Simular el movimiento de la pieza aliada y verificar si puede capturar o
						// bloquear al atacante
						Position originalPosition = piece.currentPosition;
						piece.setPosition(square.getPosition());
						if (isSquareUnderAttack(attacker.currentPosition, kingColor)) {
							// La pieza aliada puede bloquear o capturar al atacante
							blockingPositions.add(square.getPosition());
						}
						// Restaurar la posición original de la pieza
						piece.setPosition(originalPosition);
					}
				}
			}
		}

		return blockingPositions;
	}

	// M�todo para verificar si el rey est� en jaque mate
	/*
	 * public boolean isKingInCheckmate(String color,String colorat) {
	 * ChessBoard board = ChessBoard.getInstance(); // Obtiene la instancia del
	 * tablero de ajedrez.
	 * if (!isKingInCheck(color,colorat)) {
	 * return false;
	 * }
	 * for (int row = 0; row < 8; row++) {
	 * for (int col = 0; col < 8; col++) {
	 * IPiece piece = board.getPieceAt(row, col);
	 * if (piece != null && piece.color.compareToIgnoreCase(colorat)==0) {
	 * ArrayList<Square> validMoves = piece.getValidMoves();
	 * for (Square square : validMoves) {
	 * Position originalPosition = piece.currentPosition;
	 * piece.setPosition(square.getPosition());
	 * if (!isKingInCheck(color,colorat)) {
	 * piece.setPosition(originalPosition);
	 * return false;
	 * }
	 * piece.setPosition(originalPosition);
	 * }
	 * }
	 * }
	 * }
	 * return true;
	 * }
	 */

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
					if (piece.color.compareToIgnoreCase("White") == 0) {
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
		if (isKingInCheckmate("White", "Black") || isKingInCheckmate("Black", "White")) {
			return true;
		}

		// Verificar si no hay suficientes piezas para dar jaque mate
		if (insufficientMaterial()) {
			return true;
		}
		return false;
	}

}
