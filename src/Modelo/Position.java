package Modelo;

public class Position {
	private int row;
	private int columna;

	public Position(int row, int columna) {
		this.row = row;
		this.columna = columna;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}
}
