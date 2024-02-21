
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
	    for (int row =  0; row <  8; row++) {
	        for (int col =  0; col <  8; col++) {
	            // Colocar las piezas blancas en la primera y segunda fila
	            if (row ==  1 || row ==  6) {
	                switch (col) {
	                    case  0:
	                    case  7:
	                        board[row][col] = new Square(new Position(row +  1, col +  1), new Rock(row +  1, col +  1, WHITE));
	                        break;
	                    case  1:
	                    case  6:
	                        board[row][col] = new Square(new Position(row +  1, col +  1), new Knight(row +  1, col +  1, WHITE));
	                        break;
	                    case  2:
	                    case  5:
	                        board[row][col] = new Square(new Position(row +  1, col +  1), new Bishop(row +  1, col +  1, WHITE));
	                        break;
	                    case  3:
	                        board[row][col] = new Square(new Position(row +  1, col +  1), new Queen(row +  1, col +  1, WHITE));
	                        break;
	                    case  4:
	                        board[row][col] = new Square(new Position(row +  1, col +  1), new King(row +  1, col +  1, WHITE));
	                        break;
	                }
	            }
	            // Colocar las piezas negras en la séptima y octava fila
	            else if (row ==  7 || row ==  0) {
	                switch (col) {
	                    case  0:
	                    case  7:
	                        board[row][col] = new Square(new Position(row +  1, col +  1), new Rock(row +  1, col +  1, BLACK));
	                        break;
	                    case  1:
	                    case  6:
	                        board[row][col] = new Square(new Position(row +  1, col +  1), new Knight(row +  1, col +  1, BLACK));
	                        break;
	                    case  2:
	                    case  5:
	                        board[row][col] = new Square(new Position(row +  1, col +  1), new Bishop(row +  1, col +  1, BLACK));
	                        break;
	                    case  3:
	                        board[row][col] = new Square(new Position(row +  1, col +  1), new Queen(row +  1, col +  1, BLACK));
	                        break;
	                    case  4:
	                        board[row][col] = new Square(new Position(row +  1, col +  1), new King(row +  1, col +  1, BLACK));
	                        break;
	                }
	            }
	            // Colocar los peones en las filas  2 y  7
	            else if (row ==  2 || row ==  7) {
	                board[row][col] = new Square(new Position(row +  1, col +  1), new Pawn(row +  1, col +  1, row ==  2 ? WHITE : BLACK));
	            }
	            // Dejar las casillas vacías en el resto
	            else {
	                board[row][col] = new Square(new Position(row +  1, col +  1), null);
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
  
  public IPiece getPieceAt(int row, int column) {
	  if (!isSquareValid(row, column)) {
          throw new IllegalArgumentException("Coordenadas de casilla no válidas");
      }

      return board[row - 1][column - 1].getPiece();
	}

  // Controlador items

  public void displayBoard() {
    
  }

  public void updateBoard() {
  }

}
