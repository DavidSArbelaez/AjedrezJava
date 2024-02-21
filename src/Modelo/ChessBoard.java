
public class ChessBoard {
  private static ChessBoard instance;
  private Square[][] board;

  public ChessBoard() {
    board = new Square[8][8];
    initBoard();
  }

  public void initBoard() {

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
