package Vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Vista extends JFrame {
    private JPanel[][] chessBoard;
    private JLabel selectedLabel;
    private Map<String, ImageIcon> pieceImages;

    private String[][] initialBoard = {
            {"black_rook", "black_knight", "black_bishop", "black_queen", "black_king", "black_bishop", "black_knight", "black_rook"},
            {"black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn"},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn"},
            {"white_rook", "white_knight", "white_bishop", "white_queen", "white_king", "white_bishop", "white_knight", "white_rook"}
    };

    private static final int BOARD_SIZE = 8;

    public Vista() {
        initializePieceImages();
        initializeUI();
        initializeChessBoard();
    }

    private void initializePieceImages() {
        pieceImages = new HashMap<>();
        pieceImages.put("white_pawn", new ImageIcon("src/media/peon_b.png"));
        pieceImages.put("black_pawn", new ImageIcon("src/media/peon_n.png"));
        pieceImages.put("black_king", new ImageIcon("src/media/rey_n.png"));
        pieceImages.put("white_king", new ImageIcon("src/media/rey_b.png"));
        pieceImages.put("black_queen", new ImageIcon("src/media/reina_n.png"));
        pieceImages.put("white_queen", new ImageIcon("src/media/reina_b.png"));
        pieceImages.put("black_bishop", new ImageIcon("src/media/arfil_n.png"));
        pieceImages.put("white_bishop", new ImageIcon("src/media/arfil_b.png"));
        pieceImages.put("black_knight", new ImageIcon("src/media/caballo_n.png"));
        pieceImages.put("white_knight", new ImageIcon("src/media/horse_b.png"));
        pieceImages.put("black_rook", new ImageIcon("src/media/torre_n.png"));
        pieceImages.put("white_rook", new ImageIcon("src/media/torre_b.png"));
    }

    private void initializeUI() {
        setTitle("Ajedrez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));

        chessBoard = new JPanel[BOARD_SIZE][BOARD_SIZE];
        selectedLabel = null;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                chessBoard[row][col] = new JPanel(new BorderLayout());
                chessBoard[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);

                // Agregar un MouseListener para manejar los clics del ratón
                chessBoard[row][col].addMouseListener(new ChessCellMouseListener(row, col));

                // Configurar el diseño para que las imágenes ocupen todo el espacio de la celda
                add(chessBoard[row][col]);
            }
        }

        setSize(1280, 720);
        setVisible(true);
    }

    private void initializeChessBoard() {
        // Llenar el tablero con las piezas iniciales e imágenes
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                String piece = initialBoard[row][col];
                JPanel panel = chessBoard[row][col];
                panel.removeAll();  // Limpiar el contenido

                // Verificar si hay una pieza en la celda
                if (!piece.isEmpty()) {
                    // Configurar la imagen de la pieza
                    ImageIcon pieceIcon = pieceImages.get(piece);
                    if (pieceIcon != null) {
                        JLabel pieceLabel = new JLabel(pieceIcon);
                        panel.add(pieceLabel, BorderLayout.CENTER);
                    }
                }
            }
        }
    }

    // Función para actualizar la vista con la información de las piezas
    public void updateChessBoard(String boardState) {
        // Verificar que el estado del tablero tenga la longitud esperada
        if (boardState.length() != 64) {
            throw new IllegalArgumentException("La longitud del estado del tablero debe ser 64.");
        }

        int index = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                char piece = boardState.charAt(index++);
                JPanel panel = chessBoard[row][col];
                panel.removeAll();  // Limpiar el contenido

                // Verificar si hay una pieza en la celda
                if (piece != ' ') {
                    // Configurar la imagen de la pieza
                    String pieceKey = piece == 'w' ? "white_" + Character.toLowerCase(boardState.charAt(index - 1)) : "black_" + boardState.charAt(index - 1);
                    ImageIcon pieceIcon = pieceImages.get(pieceKey);
                    if (pieceIcon != null) {
                        JLabel pieceLabel = new JLabel(pieceIcon);
                        panel.add(pieceLabel, BorderLayout.CENTER);
                    }
                }

                // Volver a pintar el fondo
                panel.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
            }
        }
    }

    // Clase interna para manejar los eventos del ratón en las celdas del tablero
    private class ChessCellMouseListener extends MouseAdapter {
        private int row;
        private int col;

        public ChessCellMouseListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Aquí puedes agregar la lógica para manejar los clics en el tablero
            System.out.println("Clic en la casilla: (" + row + ", " + col + ")");
        }
    }

    public static void main(String[] args) {
        // Crear una instancia de la clase Vista
        SwingUtilities.invokeLater(() -> {
            Vista miVista = new Vista();
        });
    }
}
