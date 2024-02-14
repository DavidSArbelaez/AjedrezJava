public class Square {
  protected Position cords;
  private IPiece piece;

  public Square(Position cord,IPiece piece){
    this.cords=cord;
    this.piece=null;
  }

  private void setPiece(IPiece piece){
    this.piece = piece;
    this.piece.setPosition(cords);
  }

  public void resetSquare(){
    this.piece=null;
  }


  public Boolean state(){
    if (piece != null) {
      return true;
    }else{
      return false;
    }
  }

  public Position getPosition(){
    return this.getPosition();
  }

  private Boolean isValidMove(){
    lista = this.piece.getValidMoves();

    for (Square sq : lista) {
      if(sq.getPosition().getColumna()==this.cords.getPosition().getColumna() && sq.getPosition().getRow()==this.cords.getPosition().getRow()){
        setPiece(sq.piece);
      }
    }
  }

  public String ChessNotation(){
    return "";
  }
}
