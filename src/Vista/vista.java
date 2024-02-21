import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class vista extends JFrame {

    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 64;

    private Map<String, ImageIcon> pieceImages;

    public ChessBoardView() {
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
        pieceImages.put("white_queen", new ImageIcon("src/media/reina_n.png"));
        pieceImages.put("black_bishop", new ImageIcon("src/media/arfil_n.png"));
        pieceImages.put("white_bishop", new ImageIcon("src/media/arfil_b.png"));
        pieceImages.put("black_knight", new ImageIcon("src/media/caballo_n.png"));
        pieceImages.put("white_knight", new ImageIcon("src/media/horse_b.png"));
        pieceImages.put("black_rook", new ImageIcon("src/media/torre_n.png"));
        pieceImages.put("white_rook", new ImageIcon("src/media/torre_b.png"));
        
    }

    private void initializeGUI() {
        setTitle("Ajedrez MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crea un JLabel con la imagen de fondo del tablero
        JLabel boardLabel = new JLabel(new ImageIcon("src/media/tablero.jpg"));
        boardLabel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        // Agrega las casillas al tablero (dentro del JLabel)
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JPanel square = new JPanel();
                square.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                boardLabel.add(square);
            }
        }

        // Agrega las fichas al tablero (ajusta según la lógica del juego)
        // Por ejemplo, puedes tener un modelo de ajedrez que contenga la disposición inicial de las fichas.
        // Recorre el modelo y coloca las imágenes en las casillas correspondientes.

        // Ejemplo:
        // JPanel square = (JPanel) boardLabel.getComponent(8 * row + col);
        // square.add(new JLabel(pieceImages.get("white_pawn")));

        // Agrega el JLabel con el tablero al centro del JFrame
        add(boardLabel, BorderLayout.CENTER);

        // Refresca la vista
        revalidate();
        repaint();
    }
}
