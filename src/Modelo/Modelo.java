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

		
		if(rules.isKingInCheck(player.getColor())){
			System.out.println("Estas en jaque,no puedes realizar ese movimiento");
			return false;
		}

		boolean moveResult = board.movePiece(row, col, newRow, newCol);
		return moveResult;
	}

	public boolean isGameOver(){
		GameRules rules = new GameRules();
		return rules.isGameOver();
	}
}
