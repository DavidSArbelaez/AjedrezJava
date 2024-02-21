import java.util.ArrayList;
public class Pawn extends IPiece{

    
    public Pawn(int row, int col, String color) {
		super(row, col, color);
		// TODO Auto-generated constructor stub
	}

	/*
     * La idea es verificar si se puede realizar la captura al paso,para accionar asi
     */
    public Boolean passant(){
        return true;
    }

    public ArrayList<Square> getValidMoves(){
        ChessBoard board = ChessBoard.getInstance();
        ArrayList<Square> validMoves= new ArrayList<>();
        
        int currentRow = this.currentPosition.getRow();
        int currentColumn = this.currentPosition.getColumn();
        
        //	Se verifica el color para saber la direcci�n en que va
        // -1 para negras y 1 para blancas
        int forwardDirection = (this.color.compareToIgnoreCase("White")==0) ? 1 : -1;
        
        validMoves=forwardMove(validMoves,board, forwardDirection, currentRow, currentColumn);
        
        validMoves=addFirstMove(validMoves,board, forwardDirection, currentRow, currentColumn);
        
        validMoves=addDiagonalCaptures(validMoves, board, forwardDirection, currentRow, currentColumn);
        
        
        return validMoves;
    }
    
    private ArrayList<Square> forwardMove(ArrayList<Square> validMoves,ChessBoard board, int forwardDirection, int currentRow, int currentColumn) {
    	// Se calcula cual es la fila delante del pe�n,basandose en su posici�n actual y la direcci�n hacia adelante
    	int forwardOneRow = currentRow + forwardDirection;
    	// Verifica si la casilla delante del pe�n es v�lida en el tablero y si est� vac�a
        if (board.isSquareValid(forwardOneRow, currentColumn) && board.getPieceAt(forwardOneRow, currentColumn) == null) {
            // Si la casilla es v�lida y est� vac�a, se agrega a la lista de movimientos v�lidos
            validMoves.add(new Square(new Position(forwardOneRow, currentColumn),null));
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
    
    /*
     * 
     * import java.util.List;
import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean enPassantAvailable;

    public Pawn(Color color, Square currentPosition) {
        super(color, currentPosition);
        enPassantAvailable = false;
    }

    public void setEnPassantAvailable(boolean enPassantAvailable) {
        this.enPassantAvailable = enPassantAvailable;
    }

    public boolean isEnPassantAvailable() {
        return enPassantAvailable;
    }

    @Override
    public List<Square> getValidMoves() {
        List<Square> validMoves = new ArrayList<>();
        ChessBoard board = ChessBoard.getInstance();

        int currentRow = currentPosition.getRow();
        int currentColumn = currentPosition.getColumn();

        // Direcci�n hacia adelante dependiendo del color
        int forwardDirection = (color == Color.WHITE) ? 1 : -1;

        // Movimientos hacia adelante
        addForwardMoves(validMoves, board, forwardDirection, currentRow, currentColumn);

        // Movimiento adicional hacia adelante para el primer movimiento
        addFirstMove(validMoves, board, forwardDirection, currentRow, currentColumn);

        // Capturas en diagonal
        addDiagonalCaptures(validMoves, board, forwardDirection, currentRow, currentColumn);

        // Captura al paso
        addEnPassantCapture(validMoves, board, forwardDirection, currentRow, currentColumn);

        return validMoves;
    }

    private void addEnPassantCapture(List<Square> validMoves, ChessBoard board, int forwardDirection, int currentRow, int currentColumn) {
        // Verificar si la captura al paso est� disponible en la columna adyacente
        int leftColumn = currentColumn - 1;
        int rightColumn = currentColumn + 1;

        // Para la captura al paso, la pieza actual debe estar en una fila espec�fica y la pieza adyacente debe ser un pe�n enemigo que acaba de realizar un movimiento de dos casillas
        if (board.isSquareValid(currentRow, leftColumn)) {
            Square leftSquare = board.getSquares()[currentRow - 1][leftColumn - 1];
            if (leftSquare.getPiece() instanceof Pawn && leftSquare.getPiece().getColor() != color) {
                Pawn leftPawn = (Pawn) leftSquare.getPiece();
                if (leftPawn.isEnPassantAvailable()) {
                    validMoves.add(new Square(currentRow + forwardDirection, leftColumn));
                }
            }
        }

        if (board.isSquareValid(currentRow, rightColumn)) {
            Square rightSquare = board.getSquares()[currentRow - 1][rightColumn - 1];
            if (rightSquare.getPiece() instanceof Pawn && rightSquare.getPiece().getColor() != color) {
                Pawn rightPawn = (Pawn) rightSquare.getPiece();
                if (rightPawn.isEnPassantAvailable()) {
                    validMoves.add(new Square(currentRow + forwardDirection, rightColumn));
                }
            }
        }
    }*/