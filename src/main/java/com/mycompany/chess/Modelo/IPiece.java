import java.util.ArrayList;

public abstract class IPiece {

    Position coords;
    string color;
    
    public IPiece(int row,int col,string color){
        this.coords = new Position(row,col);
        this.color = color;
    }

    public abstract void validMove();
    public abstract ArrayList<Square> getValidMoves();


}