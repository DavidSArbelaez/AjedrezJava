package Vista;

import Controlador.controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class Vista extends JFrame {
    private JPanel[][] chessBoard;
    private controlador controlador;

    private JLabel selectedLabel;

    private Map<String, ImageIcon> pieceImages;

    private String[][] initialBoard = {
            { "black_rock", "black_knight", "black_bishop", "black_queen", "black_king", "black_bishop", "black_knight",
                    "black_rock" },
            { "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn",
                    "black_pawn" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn",
                    "white_pawn" },
            { "white_rock", "white_knight", "white_bishop", "white_queen", "white_king", "white_bishop", "white_knight",
                    "white_rock" }
    };

    private static final int BOARD_SIZE = 8;

    public Vista() {
        initializePieceImages();
        initializeUI();
        initializeChessBoard();
        this.contCords = 0;
    }

    private void initializePieceImages() {
        pieceImages = new HashMap<>();
        String ruta = "C:\\Users\\Labing\\Downloads\\AjedrezJava-Entrega\\src\\media\\";
        pieceImages.put("white_pawn", new ImageIcon(ruta + "peon_b.png"));
        pieceImages.put("black_pawn", new ImageIcon(ruta + "peon_n.png"));
        pieceImages.put("black_king", new ImageIcon(ruta + "rey_n.png"));
        pieceImages.put("white_king", new ImageIcon(ruta + "rey_b.png"));
        pieceImages.put("black_queen", new ImageIcon(ruta + "reina_n.png"));
        pieceImages.put("white_queen", new ImageIcon(ruta + "reina_b.png"));
        pieceImages.put("black_bishop", new ImageIcon(ruta + "arfil_n.png"));
        pieceImages.put("white_bishop", new ImageIcon(ruta + "arfil_b.png"));
        pieceImages.put("black_knight", new ImageIcon(ruta + "caballo_n.png"));
        pieceImages.put("white_knight", new ImageIcon(ruta + "horse_b.png"));
        pieceImages.put("black_rock", new ImageIcon(ruta + "torre_n.png"));
        pieceImages.put("white_rock", new ImageIcon(ruta + "torre_b.png"));
    }

    private void initializeUI() {
        setTitle("Ajedrez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(8, 8));
        chessBoard = new JPanel[BOARD_SIZE][BOARD_SIZE];
        selectedLabel = null;

        // Panel izquierdo
        JPanel leftPanel = new JPanel(new GridLayout(8, 1));
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setPreferredSize(new Dimension(50, 720));

        // Panel inferior
        JPanel bottomPanel = new JPanel(new GridLayout(1, 8));
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
                panel.removeAll(); // Limpiar el contenido

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

    public void updateChessBoard(String[][] boardState) {
        // Verificar que el estado del tablero tenga el tamaño esperado
        if (boardState.length != BOARD_SIZE || boardState[0].length != BOARD_SIZE) {
            throw new IllegalArgumentException(
                    "El tamaño del estado del tablero debe ser " + BOARD_SIZE + "x" + BOARD_SIZE + ".");
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                String piece = boardState[row][col];
                piece.toLowerCase();
                JPanel panel = chessBoard[row][col];
                panel.removeAll(); // Limpiar el contenido

                // Verificar si hay una pieza en la celda
                if (piece.compareToIgnoreCase("") != 0) {
                    int index = piece.indexOf("_");
                    // Configurar la imagen de la pieza
                    //System.out.println(piece);
                    String pieceKey = piece.startsWith("W") ? "white_" + piece.substring(index + 1)
                            : "black_" + piece.substring(index + 1);
                    //System.out.println(pieceKey);
                    ImageIcon pieceIcon = pieceImages.get(pieceKey);
                    if (pieceIcon != null) {
                        JLabel pieceLabel = new JLabel(pieceIcon);
                        panel.add(pieceLabel, BorderLayout.CENTER);
                    }
                }

                // Volver a pintar el fondo
                panel.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                panel.repaint();
                panel.revalidate();
            }
        }
    }

    private int contCords;
    private int[] rowM = new int[2];
    private int[] colM = new int[2];

    public boolean getMouseToMove() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                ChessCellMouseListener mouse = (ChessCellMouseListener) chessBoard[row][col].getMouseListeners()[0];
                if (mouse.isMover()) {
                    rowM[contCords] = mouse.getRow();
                    colM[contCords] = mouse.getCol();
                    contCords++;
                    mouse.resetMover();
                } else if (contCords == 2) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getContCords() {
        return contCords;
    }

    public void setContCords(int contCords) {
        this.contCords = contCords;
    }

    public int[] getRowM() {
        return rowM;
    }


    public void resetRowM() {
        this.rowM = new int[2];
    }

    public int[] getColM() {
        return colM;
    }

    public void resetColM() {
        this.colM = new int[2];
    }

}
