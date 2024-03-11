package Vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessCellMouseListener extends MouseAdapter {
    private int row;
    private int col;
    public boolean mover;
    public ChessCellMouseListener(int row, int col) {
        this.row = row;
        this.col = col;
        this.mover=false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Aquí puedes agregar la lógica para manejar los clics en el tablero
		
    	this.mover=true;
    	System.out.println("Clic en la casilla: (" + row + ", " + col + ")");
    }

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean isMover() {
		return mover;
	}

	public void resetMover() {
		this.mover = false;
	}
	
	
    
    
}
