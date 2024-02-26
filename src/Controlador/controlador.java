package Controlador;

import Vista.Vista;
import java.util.Scanner;
import Modelo.Modelo;

/**
 *
 * @author Labing
 */
public class controlador {
	Modelo modelo = new Modelo();
	Vista vista = new Vista();
	String tablero[][];
	int turnNum;

	public controlador() {
		this.turnNum = 0;
	}

	public void startGame() {
		this.modelo.startGame();
		this.tablero = modelo.getBoard();

	}
	
	public void pruebas() {
		
	}

	public void turn() {

		// Se obtiene los datos de la vista
		// los cuales son:
		// - Columna actual
		// - fila actual
		// - Columna nueva
		// - fila nueva
		
		int col = 0, row = 0, colNew = 0, rowNew = 0;

		Boolean isValidTurn = this.modelo.Turn(turnNum, col, row, colNew, rowNew);
		if(isValidTurn) {
			this.turnNum = this.turnNum + 1;
		}

		
	}
}
