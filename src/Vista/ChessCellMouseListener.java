package Vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessCellMouseListener extends MouseAdapter {
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
