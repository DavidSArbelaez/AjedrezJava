import java.util.ArrayList;

public abstract class IPiece {

    Position coords;
    String color;
    
    public IPiece(int row,int col,String color){
        this.coords = new Position(row,col);
        this.color = color;
    }

    public abstract void validMove();
    public abstract ArrayList<Square> getValidMoves();


}