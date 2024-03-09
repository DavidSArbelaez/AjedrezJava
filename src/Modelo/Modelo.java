package Modelo;

public class Modelo {

	/*
	 * Función que inicializa el tablero del juego
	 */
	public void startGame() {
		ChessBoard board = ChessBoard.getInstance();
		board.initBoard();
	}

	/*
	 * Función que obtiene el tablero para darselo al controlador
	 */
	public String[][] getBoard() {
		ChessBoard board = ChessBoard.getInstance();
		return board.getBoardState();
	}

	/*
	 * Metodo que gestiona los turnos
	 */
	public boolean Turn(int numTurn, int col, int row, int newCol, int newRow) {
		//Se crean las instancias necesarias para gestionar los turnos
		ChessBoard board = ChessBoard.getInstance();

		/*
		 * En la pocisión 0 se tiene al juagador actual y en el 1 al atacante
		 */
		Player [] players= playerTurn(numTurn); 

		// Se obtiene el numero del evento y si es 4 no se hace nada
		int event = checkTurn(players[0],players[1]);
		boolean moveResult;
		switch (event) {
			case 0://El rey esta en jaque

				moveResult = board.movePiece(row,col, newRow,newCol,players[0]);
				System.out.println(moveResult);
				if (!moveResult || checkTurn(players[0],players[1])!=4){
					IPiece pi = board.getPieceAt(newRow, newCol);
					pi.devolverMov(row, col);
					erracePiece(newRow, newCol);
					String mensaje = "El movimiento realizado no quita el jaque por lo que no es valido";
					System.out.println(mensaje);
					moveResult=false;
				}
				
				break;
		
			default:
				moveResult = board.movePiece(row, col, newRow, newCol,players[0]);
				System.out.println("Hola todo well");
				break;
		}
		
		return moveResult;
	}

	/*
	 * Este metodo verifica que se cumplan las reglas y los movimientos sean validos
	 * al igual que revisa hackes,movimientos especiales y cierre del juego
	 * @return 0 si esta en jaque
	 * @return 1 si esta en jaque mate
	 * @return 2 si es una promoción
	 * @return 3 si se puede matar al paso
	 * @return 4 si no hay nada y se juega normal
	 */
	private int checkTurn(Player actuPlayer,Player atPlayer){
		ChessBoard board = ChessBoard.getInstance();
		GameRules rules = new GameRules();
		if(rules.isKingInCheck(actuPlayer.getColor(),atPlayer.getColor())){
			//System.out.println("Estas en jaque,no puedes realizar ese movimiento");
			return 0;
		}if(isGameOver()){
			return 1;
		}
		/*if(){
			return 2;
		}*/
		/*if(){
			return 3;
		}*/
		
		return 4;
	}
	
	private Player [] playerTurn(int numTurn){
		Player[] players= new Player[2];
		
		//Se verifica quien esta jugando y quien es el oponente
		players[0] = new Player(numTurn % 2 == 0 ? "White" : "Black"); //actuPlayer
		players[1] = new Player(numTurn % 2 == 0 ? "Black" : "White");//atPlayer
		return players;
	}

	public void erracePiece(int row,int col) {
		ChessBoard board = ChessBoard.getInstance();
		board.erracePiece(row, col);
	}

	private boolean isGameOver(){
		GameRules rules = new GameRules();
		return rules.isGameOver();
	}
}
