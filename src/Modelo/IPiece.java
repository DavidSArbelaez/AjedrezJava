import java.util.ArrayList;


/*
/*Usar pojo y dto para comunicar las clases
*/
public abstract class IPiece {
    protected Position currentPosition;
    protected String color;
    
    public IPiece(int row,int col,String color){
        this.currentPosition = new Position(row,col);
        this.color = color;
    }

    public void setPosition(Position c){
        this.currentPosition.setColumna(c.getColumn());
        this.currentPosition.setRow(c.getRow());
    }

    public abstract ArrayList<Square> getValidMoves();
    
    public Boolean move(Position pos){
    	ArrayList<Square> validMoves = getValidMoves();
        if (validMove(pos,validMoves)){
            return true;
        }else{
            return false;
        }
    }

    /*
     * Este metodo verifica si el movimiento a realizar esta dentro de los posibles movimientos,
     *  si esta en la lista,significa que es legal por lo tanto es valido
     */
    public Boolean validMove(Position pos,ArrayList<Square> validMoves){
        for (Square s : validMoves) {
            if (s.getPosition().getColumn()==pos.getColumn() && s.getPosition().getRow()==pos.getRow()){
                return true;
            }
        }
        
        return false;
    }

}