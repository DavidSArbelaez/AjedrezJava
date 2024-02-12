public class Square {
  Position cords;
  IPiece piece;

  public Square(Position cord,IPiece piece){
    this.cords=cord;
    this.piece=piece;
  }

  public void setPosition(Position pos){
    cords.setColumna(pos.getColumna());
    cords.setRow(pos.getRow());
  }

  public String ChessNotation(){
    return "";
  }
}
