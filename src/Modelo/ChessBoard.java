package Modelo;

public class ChessBoard {
	private static ChessBoard instance;
	Boolean isGameOver;
	private Square[][] board;

	public ChessBoard() {
		board = new Square[8][8];
		initBoard();
		this.isGameOver = false;
	}

	public void initBoard() {
		// Inicializar el tablero con piezas
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				// Colocar las piezas blancas en la primera fila
				
				
				//Se crea la pocisión de cada cuadrado del tablero
				Position pos = new Position(row, col);
				
				if (row == 7) {
					switch (col) {
					case 0:
						this.board[row][col] = new Square(pos, new Rock(pos,"WHITE"));
						
						break;
					case 7:
						this.board[row][col] = new Square(pos, new Rock(pos,"WHITE"));
						break;
					case 1:
						this.board[row][col] = new Square(pos, new Knight(pos,"WHITE"));
						break;
					case 6:
						this.board[row][col] = new Square(pos, new Knight(pos,"WHITE"));
						break;
					case 2:
						this.board[row][col] = new Square(pos, new Bishop(pos,"WHITE"));
						break;
					case 5:
						this.board[row][col] = new Square(pos, new Bishop(pos,"WHITE"));
						break;
					case 3:
						this.board[row][col] = new Square(pos, new Queen(pos,"WHITE"));
						break;
					case 4:
						this.board[row][col] = new Square(pos, new King(pos,"WHITE"));
						break;
					}
				}
				// Colocar las piezas negras en la s�ptima y octava fila
				else if (row == 0) {
					switch (col) {
					case 0:
						this.board[row][col] = new Square(pos, new Rock(pos,"BLACK"));
						break;
					case 7:
						this.board[row][col] = new Square(pos, new Rock(pos,"BLACK"));
						break;
					case 1:
						this.board[row][col] = new Square(pos, new Knight(pos,"BLACK"));
						break;
					case 6:
						this.board[row][col] = new Square(pos, new Knight(pos,"BLACK"));
						break;
					case 2:
						this.board[row][col] = new Square(pos, new Bishop(pos,"BLACK"));
						break;
					case 5:
						this.board[row][col] = new Square(pos, new Bishop(pos,"BLACK"));
						break;
					case 3:
						this.board[row][col] = new Square(pos, new Queen(pos,"BLACK"));
						break;
					case 4:
						this.board[row][col] = new Square(pos, new King(pos,"BLACK"));
						break;
					}
				} else if (row == 1 || row == 6) {

					this.board[row][col] = new Square(pos, new Pawn(pos, row == 6 ? "WHITE" : "BLACK"));

				}
				// Dejar las casillas vac�as en el resto
				else {
					this.board[row][col] = new Square(pos);
				}
			}
		}
	}

	public static ChessBoard getInstance() {
		if (instance == null) {
			instance = new ChessBoard();
		}
		return instance;
	}

	public boolean isSquareValid(int row, int column) {
		return row >= 0 && row <= 7 && column >= 0 && column <= 7;
	}

	public Square[][] getSquares() {
		return board;
	}
	
	private boolean isSquareFilled(int row,int col) {
		Square sq = this.board[row][col];
		System.out.println(sq.getPiece().getClass().getName());;
		if(sq.getPiece()==null) {
			return false;
		}else {
			return true;
		}
	}

	public boolean movePiece(int row, int column, int Mrow, int Mcolumn,Player player) {
		if(!isSquareFilled(row, column)) {
			System.out.println("No puedes mover una ficha que no existe,tienes que mover las fichas que siguen en juego y no un cuadrado vacio.");
			System.out.println("Vuelve a intentarlo");
			return false;
		}
		IPiece pieza = getPieceAt(row, column);
		if (player.getColor().compareToIgnoreCase(pieza.color)!=0) {
			System.out.println("Esa ficha no es de tu color,mueve una pieza de tu color");
			return false;
		}
		
		Boolean isMove = pieza.move(new Position(Mrow, Mcolumn));

		return isMove;
	}

	public IPiece getPieceAt(int row, int column) {
		if (!isSquareValid(row, column)) {
			throw new IllegalArgumentException("Coordenadas de casilla no v�lidas");
		}

		return board[row][column].getPiece();
	}

	public void resetSquare(Position pos) {
		board[pos.getRow()][pos.getColumn()].resetSquare();
	}

	/**
	 * Obtiene una representaci�n del estado actual del tablero en forma de matriz
	 * de cadenas. Cada cadena representa la pieza en la casilla correspondiente del
	 * tablero. Las cadenas siguen el formato "ColorTipo", donde: - "Color"
	 * representa el color de la pieza ("W" para blanco, "B" para negro). - "Tipo"
	 * representa el tipo de pieza (por ejemplo, "P" para pe�n, "R" para rey, etc.).
	 * Si no hay ninguna pieza en una casilla, se representa como una cadena vac�a.
	 * 
	 * @return Una matriz de cadenas que representa el estado del tablero.
	 */
	public String[][] getBoardState() {
		System.out.println("Ingreso a board state");
		String[][] boardState = new String[8][8]; // Matriz para almacenar el estado del tablero
		for (int row = 0; row < 8; row++) { // Iterar sobre las filas del tablero
			for (int col = 0; col < 8; col++) { // Iterar sobre las columnas del tablero
				
				IPiece piece = this.board[row][col].getPiece(); // Obtener la pieza en la casilla actual
				if (piece != null) { // Si hay una pieza en la casilla
					// Representar la pieza en la casilla como una cadena (por ejemplo, "WP" para un
					// pe�n blanco)
					boardState[row][col] = piece.color.charAt(0) + piece.getClass().getSimpleName().substring(0, 1);
				} else { // Si no hay una pieza en la casilla
					boardState[row][col] = ""; // Representar la casilla como una cadena vac�a
				}
			}
		}
		return boardState; // Devolver la matriz que representa el estado del tablero
	}
	
	public void erracePiece(int row,int colunm) {
		board[row][colunm].resetSquare();
	}

	public void displayBoard() {
		String[][] boardState = getBoardState(); // Obtener el estado actual del tablero
		// Imprimir el tablero fila por fila
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				// Imprimir la representaci�n de la pieza en la casilla actual
				System.out.print(boardState[row][col] + " ");
			}
			System.out.println(); // Saltar a la siguiente l�nea para imprimir la siguiente fila
		}
	}

}
