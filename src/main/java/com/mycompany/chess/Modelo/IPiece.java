import java.util.ArrayList;


/*
/*Usar pojo y dto para comunicar las clases
*/
public abstract class IPiece {
    protected ArrayList<Square> validMoves;
    protected Position coords;
    protected String color;
    
    public IPiece(int row,int col,String color){
        this.coords = new Position(row,col);
        this.color = color;
    }

    public void setPosition(Position c){
        this.coords.setColumna(c.getColumna());
        this.coords.setRow(c.getRow());
    }

    public abstract ArrayList<Square> getValidMoves();
    
    public Boolean move(Position pos){
        if (validMove(pos)){
            return true;
        }else{
            return false;
        }
    }

    /*
     * Este metodo verifica si el movimiento a realizar esta dentro de los posibles movimientos,
     *  si esta en la lista,significa que es legal por lo tanto es valido
     */
    public Boolean validMove(Position pos){
        for (Position position : validMoves) {
            if (position.equals(pos)){
                return true;
            }
        }
        
        return false;
    }

}