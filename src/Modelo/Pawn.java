package Modelo;
import java.util.ArrayList;
public class Pawn extends IPiece{

    
    public Pawn(Position pos, String color) {
		super(pos, color);
		// TODO Auto-generated constructor stub
	}

    public ArrayList<Square> getValidMoves(){
        ChessBoard board = ChessBoard.getInstance();
        ArrayList<Square> validMoves= new ArrayList<>();
        
        int currentRow = this.currentPosition.getRow();
        int currentColumn = this.currentPosition.getColumn();
        
        //	Se verifica el color para saber la direcci�n en que va
        // -1 para negras y 1 para blancas
        int forwardDirection = (this.color.compareToIgnoreCase("White")==0) ? -1 : 1;
        System.out.println(this.toString());
        validMoves.addAll(forwardMove(validMoves,board, forwardDirection, currentRow, currentColumn));
        
        validMoves.addAll(addFirstMove(validMoves,board, forwardDirection, currentRow, currentColumn));
        
        validMoves.addAll(addDiagonalCaptures(validMoves, board, forwardDirection, currentRow, currentColumn));
        
        
        return validMoves;
    }
    
    private ArrayList<Square> forwardMove(ArrayList<Square> validMoves, ChessBoard board, int forwardDirection, int currentRow, int currentColumn) {
        // Se calcula cual es la fila delante del peón, basándose en su posición actual y la dirección hacia adelante
        int forwardOneRow = currentRow + forwardDirection;
        // Verifica si la casilla delante del peón es válida en el tablero y si está vacía
        if (board.isSquareValid(forwardOneRow, currentColumn)) {
            if (board.getPieceAt(forwardOneRow, currentColumn) == null) {
                // Si la casilla está vacía, se agrega a la lista de movimientos válidos
                validMoves.add(new Square(new Position(forwardOneRow, currentColumn), null));
                // Si es el primer movimiento, verifica si puede avanzar dos casillas
                if (currentRow == (this.color.compareToIgnoreCase("WHITE") == 0 ? 6 : 1)) {
                    int forwardTwoRows = currentRow + (2 * forwardDirection);
                    if (board.isSquareValid(forwardTwoRows, currentColumn) && board.getPieceAt(forwardTwoRows, currentColumn) == null) {
                        validMoves.add(new Square(new Position(forwardTwoRows, currentColumn)));
                    }
                }
            }
        }
        return validMoves;
    }
    
    private ArrayList<Square> addFirstMove(ArrayList<Square> validMoves,ChessBoard board, int forwardDirection, int currentRow, int currentColumn) {
        //Se calcula la fila inicial del peon en base a su color
    	int initialRow = (this.color.compareToIgnoreCase("White")==0) ? 2 : 7;
        
    	//Se verifica que la fila actual sea igual que la inicial
    	if (currentRow == initialRow) {
            int forwardTwoRows = currentRow + (2 * forwardDirection);
            if (board.isSquareValid(forwardTwoRows, currentColumn) && board.getPieceAt(forwardTwoRows, currentColumn) == null) {
                validMoves.add(new Square(new Position(forwardTwoRows, currentColumn), null));
            }
        }
        return validMoves;
    }
    
    private ArrayList<Square> addDiagonalCaptures(ArrayList<Square> validMoves, ChessBoard board, int forwardDirection, int currentRow, int currentColumn) {
        int forwardOneRow = currentRow + forwardDirection;

        // Captura en diagonal izquierda
        int leftColumn = currentColumn - 1;
        if (board.isSquareValid(forwardOneRow, leftColumn)) {
            IPiece pieceLeft = board.getPieceAt(forwardOneRow, leftColumn);
            if (pieceLeft != null && pieceLeft.color != this.color) {
                validMoves.add(new Square(new Position(forwardOneRow, leftColumn),null));
            }
        }

        // Captura en diagonal derecha
        int rightColumn = currentColumn + 1;
        if (board.isSquareValid(forwardOneRow, rightColumn)) {
            IPiece pieceRight = board.getPieceAt(forwardOneRow, rightColumn);
            if (pieceRight != null && pieceRight.color != this.color) {
            	validMoves.add(new Square(new Position(forwardOneRow, rightColumn),null));
            }
        }
        return validMoves;
    }
}
    
    
