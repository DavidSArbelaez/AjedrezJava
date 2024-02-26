package Modelo;

public class Modelo {
	public void startGame(Player p1) {
		ChessBoard board = ChessBoard.getInstance();

		// Se inicializa el tablero
		board.initBoard();

	}

	public Boolean Turn(Player p1, int col, int row, int newCol, int newRow) {
		ChessBoard board = ChessBoard.getInstance();
		GameRules rules = new GameRules();

		rules.isKingInCheck(p1.getColor());
		rules.isKingInCheckmate(p1.getColor());

		boolean moveResult = board.movePiece(row, col, newRow, newCol);
		return moveResult;
	}
}
