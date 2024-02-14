public class Pawn implements IPiece{
    public Boolean validMove(Position pos){
        
        
        return true;
    }

    /*
     * La idea es verificar si se puede realizar la captura al paso,para accionar asi
     */
    public Boolean passant(){
        return true;
    }

    public ArrayList<Square> getValidMoves(){

        return null;
    }

    public Boolean move(Position pos){
        if (validMove(pos)){
            return true;
        }else{
            return false;
        }
    }
}