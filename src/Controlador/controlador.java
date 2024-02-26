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
		Scanner sc = new Scanner(System.in);
		boolean option = false;
		do {
			// Se obtiene los datos de la vista
			// los cuales son:
			// - Columna actual
			// - fila actual
			// - Columna nueva
			// - fila nueva
			System.out.println("Ingrese el numero de la fila de la pieza a mover:");
			int fromRow = sc.nextInt() - 1;
			System.out.println("Ingrese el numero de la columna de la pieza a mover:");
			int fromCol = sc.nextInt() - 1;
			System.out.println("Ingrese el numero de la fila a mover la pieza:");
			int toRow = sc.nextInt() - 1;
			System.out.println("Ingrese el numero de la columna a mover la pieza:");
			int toCol = sc.nextInt() - 1;

			Boolean isValidTurn = this.modelo.Turn(turnNum, fromCol, fromRow, toCol, toRow);
			
			if (isValidTurn) {
				System.out.println("Movimiento válido. La pieza se ha movido.");
				this.turnNum = this.turnNum + 1;
				option=true;
			}else{
				System.out.println("Movimiento no válido. Inténtelo de nuevo.");
			}
		} while (!option);

	}
}
