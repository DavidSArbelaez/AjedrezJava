import java.util.ArrayList;

public abstract class IPiece {

    Position coords;
    String color;
    
    public IPiece(int row,int col,String color){
        this.coords = new Position(row,col);
        this.color = color;
    }

    public void setPosition(Position c){
        this.coords.setColumna(c.getColumna());
        this.coords.setRow(c.getRow());
    }

    public abstract Boolean validMove(Position pos);
    public abstract ArrayList<Square> getValidMoves();
    public abstract Boolean move(Position pos);


}