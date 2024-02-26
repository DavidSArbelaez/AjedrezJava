package Modelo;

public class ChessBoard {
	private static ChessBoard instance;
	private Square[][] board;

	public ChessBoard() {
		board = new Square[8][8];
		initBoard();
	}

	public void initBoard() {
		// Definir los colores de las piezas
		String WHITE = "White";
		String BLACK = "Black";

		// Inicializar el tablero con piezas
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				// Colocar las piezas blancas en la primera y segunda fila
				if (row == 1 || row == 6) {
					switch (col) {
					case 0:
					case 7:
						board[row][col] = new Square(new Position(row + 1, col + 1), new Rock(row + 1, col + 1, WHITE));
						break;
					case 1:
					case 6:
						board[row][col] = new Square(new Position(row + 1, col + 1),
								new Knight(row + 1, col + 1, WHITE));
						break;
					case 2:
					case 5:
						board[row][col] = new Square(new Position(row + 1, col + 1),
								new Bishop(row + 1, col + 1, WHITE));
						break;
					case 3:
						board[row][col] = new Square(new Position(row + 1, col + 1),
								new Queen(row + 1, col + 1, WHITE));
						break;
					case 4:
						board[row][col] = new Square(new Position(row + 1, col + 1), new King(row + 1, col + 1, WHITE));
						break;
					}
				}
				// Colocar las piezas negras en la sï¿½ptima y octava fila
				else if (row == 7 || row == 0) {
					switch (col) {
					case 0:
					case 7:
						board[row][col] = new Square(new Position(row + 1, col + 1), new Rock(row + 1, col + 1, BLACK));
						break;
					case 1:
					case 6:
						board[row][col] = new Square(new Position(row + 1, col + 1),
								new Knight(row + 1, col + 1, BLACK));
						break;
					case 2:
					case 5:
						board[row][col] = new Square(new Position(row + 1, col + 1),
								new Bishop(row + 1, col + 1, BLACK));
						break;
					case 3:
						board[row][col] = new Square(new Position(row + 1, col + 1),
								new Queen(row + 1, col + 1, BLACK));
						break;
					case 4:
						board[row][col] = new Square(new Position(row + 1, col + 1), new King(row + 1, col + 1, BLACK));
						break;
					}
				}
				// Colocar los peones en las filas 2 y 7
				else if (row == 2 || row == 7) {
					board[row][col] = new Square(new Position(row + 1, col + 1),
							new Pawn(row + 1, col + 1, row == 2 ? WHITE : BLACK));
				}
				// Dejar las casillas vacï¿½as en el resto
				else {
					board[row][col] = new Square(new Position(row + 1, col + 1), null);
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
		return row >= 1 && row <= 8 && column >= 1 && column <= 8;
	}

	public Square[][] getSquares() {
		return board;
	}

	public boolean movePiece(int row, int column, int Mrow, int Mcolumn) {

		IPiece pieza = getPieceAt(row, column);

		Boolean isMove = pieza.move(new Position(Mrow, Mcolumn));

		return isMove;
	}

	public IPiece getPieceAt(int row, int column) {
		if (!isSquareValid(row, column)) {
			throw new IllegalArgumentException("Coordenadas de casilla no vï¿½lidas");
		}

		return board[row - 1][column - 1].getPiece();
	}

	public void resetSquare(Position pos) {
		board[pos.getRow() - 1][pos.getColumn() - 1].resetSquare();
	}

	/**
	 * Obtiene una representación del estado actual del tablero en forma de matriz
	 * de cadenas. Cada cadena representa la pieza en la casilla correspondiente del
	 * tablero. Las cadenas siguen el formato "ColorTipo", donde: - "Color"
	 * representa el color de la pieza ("W" para blanco, "B" para negro). - "Tipo"
	 * representa el tipo de pieza (por ejemplo, "P" para peón, "R" para rey, etc.).
	 * Si no hay ninguna pieza en una casilla, se representa como una cadena vacía.
	 * 
	 * @return Una matriz de cadenas que representa el estado del tablero.
	 */
	public String[][] getBoardState() {
		String[][] boardState = new String[8][8]; // Matriz para almacenar el estado del tablero
		for (int row = 0; row < 8; row++) { // Iterar sobre las filas del tablero
			for (int col = 0; col < 8; col++) { // Iterar sobre las columnas del tablero
				IPiece piece = board[row][col].getPiece(); // Obtener la pieza en la casilla actual
				if (piece != null) { // Si hay una pieza en la casilla
					// Representar la pieza en la casilla como una cadena (por ejemplo, "WP" para un
					// peón blanco)
					boardState[row][col] = piece.color.charAt(0) + piece.getClass().getSimpleName().substring(0, 1);
				} else { // Si no hay una pieza en la casilla
					boardState[row][col] = ""; // Representar la casilla como una cadena vacía
				}
			}
		}
		return boardState; // Devolver la matriz que representa el estado del tablero
	}

	public void displayBoard() {
		String[][] boardState = getBoardState(); // Obtener el estado actual del tablero
		// Imprimir el tablero fila por fila
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				// Imprimir la representación de la pieza en la casilla actual
				System.out.print(boardState[row][col] + " ");
			}
			System.out.println(); // Saltar a la siguiente línea para imprimir la siguiente fila
		}
	}

	public void updateBoard() {
	}

}
