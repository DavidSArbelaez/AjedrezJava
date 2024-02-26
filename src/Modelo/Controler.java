
public class Controler {

	public void startGame(Player p1) {
		ChessBoard board = ChessBoard.getInstance();
		
		//Se inicializa el tablero
		board.initBoard();
		
		
		
		
	}
	
	public Boolean Turn(Player p1,int col, int row, int newCol,int rowRow) {
		ChessBoard board = ChessBoard.getInstance();
		GameRules rules = new GameRules();
		
		
		rules.isKingInCheck(p1.getColor());
		rules.isKingInCheckmate(p1.getColor());
	
		//Se mueve la pieza
		return null;
	}
	
	
	
	
}
