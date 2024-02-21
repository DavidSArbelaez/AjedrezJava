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

    public void createValidMoves(){
        ArrayList<Square> validMoves = new ArrayList<>();

    }

    public ArrayList<Square> getValidMoves(){
        ChessBoard board = ChessBoard.getInstance();
        ArrayList<Square> validMoves= new ArrayList<>();
        
        int currentRow = this.coords.getRow();
        int currentColumn = this.coords.getColumna();
        
        //	Se verifica el color para saber la dirección en que va
        // -1 para negras y 1 para blancas
        int forwardDirection = (this.color.compareToIgnoreCase("White")==0) ? 1 : -1;
        
        forwardMove(validMoves,board, forwardDirection, currentRow, currentColumn);
        
        addFirstMove(validMoves,board, forwardDirection, currentRow, currentColumn);
        
        addDiagonalCaptures(validMoves, board, forwardDirection, currentRow, currentColumn);
        
        
        return null;
    }
    
    private void forwardMove(ArrayList<Square> validMoves,ChessBoard board, int forwardDirection, int currentRow, int currentColumn) {
    	// Se calcula cual es la fila delante del peón,basandose en su posición actual y la dirección hacia adelante
    	int forwardOneRow = currentRow + forwardDirection;
    	// Verifica si la casilla delante del peón es válida en el tablero y si está vacía
        if (board.isSquareValid(forwardOneRow, currentColumn) && board.getPieceAt(forwardOneRow, currentColumn) == null) {
            // Si la casilla es válida y está vacía, se agrega a la lista de movimientos válidos
            validMoves.add(new Square(new Position(forwardOneRow, currentColumn),null));
        }
    }
    
    private void addFirstMove(ArrayList<Square> validMoves,ChessBoard board, int forwardDirection, int currentRow, int currentColumn) {
        //Se calcula la fila inicial del peon en base a su color
    	int initialRow = (this.color.compareToIgnoreCase("White")==0) ? 2 : 7;
        
    	//Se verifica que la fila actual sea igual que la inicial
    	if (currentRow == initialRow) {
            int forwardTwoRows = currentRow + (2 * forwardDirection);
            if (board.isSquareValid(forwardTwoRows, currentColumn) && board.getPieceAt(forwardTwoRows, currentColumn) == null) {
                validMoves.add(new Square(new Position(forwardTwoRows, currentColumn), null));
            }
        }
    }
    
    private void addDiagonalCaptures(ArrayList<Square> validMoves, ChessBoard board, int forwardDirection, int currentRow, int currentColumn) {
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

        // Dirección hacia adelante dependiendo del color
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
        // Verificar si la captura al paso está disponible en la columna adyacente
        int leftColumn = currentColumn - 1;
        int rightColumn = currentColumn + 1;

        // Para la captura al paso, la pieza actual debe estar en una fila específica y la pieza adyacente debe ser un peón enemigo que acaba de realizar un movimiento de dos casillas
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