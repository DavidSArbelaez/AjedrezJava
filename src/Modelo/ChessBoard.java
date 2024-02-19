import java.util.ArrayList;

public class ChessBoard {
  private static ChessBoard instance;
  private ArrayList<Square> squares;

  public ChessBoard() {
    squares = new ArrayList<>();
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

  public ArrayList<Square> getSquares() {
    return squares;
  }

  // Controlador items

  public void displayBoard() {
    
  }

  public void updateBoard() {
  }

}
