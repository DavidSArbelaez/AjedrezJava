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
        pieceImages.put("white_pawn", new ImageIcon("AjedrezJava-Juan/src/media/peon_b.png"));
        pieceImages.put("black_pawn", new ImageIcon("AjedrezJava-Juan/src/media/peon_n.png"));
        pieceImages.put("black_king", new ImageIcon("AjedrezJava-Juan/src/media/rey_n.png"));
        pieceImages.put("white_king", new ImageIcon("AjedrezJava-Juan/src/media/rey_b.png"));
        pieceImages.put("black_queen", new ImageIcon("AjedrezJava-Juan/src/media/reina_n.png"));
        pieceImages.put("white_queen", new ImageIcon("AjedrezJava-Juan/src/media/reina_b.png"));
        pieceImages.put("black_bishop", new ImageIcon("AjedrezJava-Juan/src/media/arfil_n.png"));
        pieceImages.put("white_bishop", new ImageIcon("AjedrezJava-Juan/src/media/arfil_b.png"));
        pieceImages.put("black_knight", new ImageIcon("AjedrezJava-Juan/src/media/caballo_n.png"));
        pieceImages.put("white_knight", new ImageIcon("AjedrezJava-Juan/src/media/horse_b.png"));
        pieceImages.put("black_rook", new ImageIcon("AjedrezJava-Juan/src/media/torre_n.png"));
        pieceImages.put("white_rook", new ImageIcon("AjedrezJava-Juan/src/media/torre_b.png"));
    }

    private void initializeUI() {
        setTitle("Ajedrez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    
        JPanel mainPanel = new JPanel(new GridLayout(8, 8));
        chessBoard = new JPanel[BOARD_SIZE][BOARD_SIZE];
        selectedLabel = null;
    
        // Panel izquierdo
        JPanel leftPanel = new JPanel(new GridLayout(8, 1)); // GridLayout con 8 filas y 1 columna
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setPreferredSize(new Dimension(50, 720));
    
        // Panel inferior
        JPanel bottomPanel = new JPanel(new GridLayout(1, 8)); // GridLayout con 1 fila y 8 columnas
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.setPreferredSize(new Dimension(720, 50));
    
        // Agregar números del 1 al 8 al panel izquierdo
        for (int i = 8; i >= 1; i--) {
            JLabel label = new JLabel(Integer.toString(i));
            label.setHorizontalAlignment(JLabel.CENTER);
            leftPanel.add(label);
        }
    
        // Agregar letras del abecedario al panel inferior
        for (char c = 'a'; c <= 'h'; c++) {
            JLabel label = new JLabel(Character.toString(c));
            label.setHorizontalAlignment(JLabel.CENTER);
            bottomPanel.add(label);
        }
    
        // Configuración del tablero principal
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                chessBoard[row][col] = new JPanel(new BorderLayout());
                chessBoard[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                chessBoard[row][col].addMouseListener(new ChessCellMouseListener(row, col));
                mainPanel.add(chessBoard[row][col]);
            }
        }
    
        add(mainPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(bottomPanel, BorderLayout.SOUTH);
    
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
}
