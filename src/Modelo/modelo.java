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

    public void turn(String color) {
        // Obtener todas las piezas del jugador actual
        ArrayList<IPiece> currentPlayerPieces = board.getPiecesByColor(currentPlayerColor);

        // Realizar movimientos para cada pieza del jugador actual
        for (IPiece piece : currentPlayerPieces) {
            ArrayList<Square> validMoves = piece.getValidMoves();
            // Aquí podrías implementar la lógica para seleccionar un movimiento válido
            // y llamar al método movePiece con la pieza y la nueva posición
        }

        // Cambiar el color del jugador para el próximo turno
        currentPlayerColor = (currentPlayerColor.equals("White")) ? "Black" : "White";
    }

    public void movePiece(IPiece piece, int newCol, int newRow){

    }

}
