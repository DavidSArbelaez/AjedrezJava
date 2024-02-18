import java.util.ArrayList;
public class Pawn implements IPiece{

    
    /*
     * La idea es verificar si se puede realizar la captura al paso,para accionar asi
     */
    public Boolean passant(){
        return true;
    }

    private forwardMove(){

    }

    public void createValidMoves(){
        ArrayList<Square> validMoves = new ArrayList<>();

    }

    public ArrayList<Square> getValidMoves(){
        ChessBoard tablero = ChessBoard.getInstance();
        



        return null;
    }
}