public class modelo {
    ChessBoard board = ChessBoard.getInstance();
    private String currentPlayerColor;

    public modelo() {
        board = ChessBoard.getInstance();
    }

    public void startGame(){
        this.board.initBoard();
        currentPlayerColor = "White"; // Comienza con las blancas
    }

    public void turn(String color, IPiece piece, int newCol, int newRow) {
        // Obtener todas las piezas del jugador actual
        ArrayList<IPiece> currentPlayerPieces = board.getPiecesByColor(currentPlayerColor);
        movePiece(piece, newCol, newRow);
        
    }

    public void movePiece(IPiece piece, int newCol, int newRow){
        piece.move();

    }

}
