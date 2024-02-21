import java.util.ArrayList;

public class Square {
  protected Position cords;
  private IPiece piece;

  public Square(Position cord,IPiece piece){
    this.cords=cord;
    this.piece=null;
  }

  public void setPiece(IPiece piece){
    this.piece = piece;
    this.piece.setPosition(cords);
  }

  public void resetSquare(){
    this.piece=null;
  }

  public Position getPosition(){
    return this.getPosition();
  }
  
  public IPiece getPiece() {
	  return this.piece;
  }

  
  /*
   * Función que verifica si la ficha a mover tinee el movimiento valido 
   */
  private void move(){
    ArrayList<Square> lista = this.piece.getValidMoves();

    for (Square sq : lista) {
      if(sq.getPosition().getColumn()==this.cords.getColumn() && sq.getPosition().getRow()==this.cords.getRow()){
        setPiece(sq.piece);
      }
    }
  }

  public String ChessNotation(){
    return "";
  }
}
