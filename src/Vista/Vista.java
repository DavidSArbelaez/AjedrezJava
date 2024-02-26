package Vista;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Vista extends JFrame {

    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 64;

    private Map<String, ImageIcon> pieceImages;

    public Vista() {
        initializePieceImages();
        initializeGUI();
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

    private void initializeGUI() {
        setTitle("Ajedrez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establece un tamaño específico para la ventana
        setSize((BOARD_SIZE + 2) * SQUARE_SIZE, (BOARD_SIZE + 2) * SQUARE_SIZE);  // Se añade 2 para los números

        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        JPanel topNumbersPanel = new JPanel(new GridLayout(1, BOARD_SIZE));  // Panel para los números en la parte superior
        JPanel bottomNumbersPanel = new JPanel(new GridLayout(1, BOARD_SIZE));  // Panel para los números en la parte inferior
        JPanel leftNumbersPanel = new JPanel(new GridLayout(BOARD_SIZE, 1));  // Panel para los números en la columna izquierda
        JPanel rightNumbersPanel = new JPanel(new GridLayout(BOARD_SIZE, 1));  // Panel para los números en la columna derecha

        String[][] initialBoard = {
                {"black_rook", "black_knight", "black_bishop", "black_queen", "black_king", "black_bishop", "black_knight", "black_rook"},
                {"black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn"},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn"},
                {"white_rook", "white_knight", "white_bishop", "white_queen", "white_king", "white_bishop", "white_knight", "white_rook"}
        };

        // Agrega las casillas y números al tablero
        for (int row = 0; row < BOARD_SIZE; row++) {
            // Agrega números en la columna izquierda
            JLabel leftNumberLabel = new JLabel(String.valueOf(row + 1), SwingConstants.CENTER);
            leftNumbersPanel.add(leftNumberLabel);

            for (int col = 0; col < BOARD_SIZE; col++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);

                // Agrega la pieza al cuadrado si hay una en la posición del tablero
                String piece = initialBoard[row][col];
                if (!piece.isEmpty()) {
                    square.add(new JLabel(pieceImages.get(piece), SwingConstants.CENTER));
                }

                // Agrega la etiqueta con las coordenadas
                JLabel coordinateLabel = new JLabel(String.format("%d-%d", row + 1, col + 1), SwingConstants.CENTER);
                
                // Cambia el color del texto en las casillas negras para mejorar la visibilidad
                coordinateLabel.setForeground(square.getBackground().equals(Color.BLACK) ? Color.WHITE : Color.BLACK);
                
                square.add(coordinateLabel, BorderLayout.SOUTH);

                boardPanel.add(square);
            }

            // Agrega números en la parte superior
            JLabel topNumberLabel = new JLabel(String.valueOf(row + 1), SwingConstants.CENTER);
            topNumbersPanel.add(topNumberLabel);

            // Agrega números en la parte inferior
            JLabel bottomNumberLabel = new JLabel(String.valueOf(row + 1), SwingConstants.CENTER);
            bottomNumbersPanel.add(bottomNumberLabel);

            // Agrega números en la columna derecha
            JLabel rightNumberLabel = new JLabel(String.valueOf(row + 1), SwingConstants.CENTER);
            rightNumbersPanel.add(rightNumberLabel);
        }

        // Agrega el tablero y los números al centro del JFrame
        add(boardPanel, BorderLayout.CENTER);

        // Agrega los números en la parte superior e inferior
        add(topNumbersPanel, BorderLayout.NORTH);
        add(bottomNumbersPanel, BorderLayout.SOUTH);

        // Agrega los números en la columna izquierda y derecha
        add(leftNumbersPanel, BorderLayout.WEST);
        add(rightNumbersPanel, BorderLayout.EAST);

        // Refresca la vista
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Vista chessBoardView = new Vista();
            chessBoardView.setVisible(true);
        });
    }
}
