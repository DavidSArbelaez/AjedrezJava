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

  public String ChessNotation(){
    return "";
  }
}
