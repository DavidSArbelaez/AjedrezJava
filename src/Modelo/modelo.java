package Modelo;

public class Modelo {
	public void startGame() {

		ChessBoard board = ChessBoard.getInstance();

		// Se inicializa el tablero
		board.initBoard();

	}

	public String[][] getBoard() {
		ChessBoard board = ChessBoard.getInstance();
		
		return board.getBoardState();
	}

	public Boolean Turn(int numTurn, int col, int row, int newCol, int newRow) {
		ChessBoard board = ChessBoard.getInstance();
		GameRules rules = new GameRules();

		Player player;
		if (numTurn % 2 == 0) {
			player = new Player("White");
		} else {
			player = new Player("Black");
		}

		rules.isKingInCheck(player.getColor());
		rules.isKingInCheckmate(player.getColor());

		boolean moveResult = board.movePiece(row, col, newRow, newCol);
		return moveResult;
	}
}
