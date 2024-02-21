import java.util.ArrayList;


/*
/*Usar pojo y dto para comunicar las clases
*/
public abstract class IPiece {
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
            if (s.getPosition().getColumna()==pos.getColumna() && s.getPosition().getRow()==pos.getRow()){
                return true;
            }
        }
        
        return false;
    }

}