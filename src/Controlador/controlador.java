package Controlador;

import Vista.Vista;
import java.util.Scanner;

import javax.swing.SwingUtilities;

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
		SwingUtilities.invokeLater(() -> {
			vista.setVisible(true);
		});

		displayBoard(tablero);

	}

	public void displayBoard(String[][] boardState) {
		// Imprimir la parte superior del tablero con las letras de las columnas
		System.out.print("   ");
		for (int col = 0; col < boardState[0].length; col++) {
			System.out.print((char) ('1' + col) + " ");
		}
		System.out.println();
	
		// Imprimir el borde superior del tablero
		System.out.print("  ");
		for (int col = 0; col < boardState[0].length; col++) {
			System.out.print("--");
		}
		System.out.println();
	
		// Imprimir el tablero y sus contenidos
		for (int row = 0; row < boardState.length; row++) {
			System.out.print((row + 1) + "| "); // Imprimir número de fila
			for (int col = 0; col < boardState[row].length; col++) {
				System.out.print(boardState[row][col] + " ");
			}
			System.out.println();
		}
	
		// Imprimir el borde inferior del tablero
		System.out.print("  ");
		for (int col = 0; col < boardState[0].length; col++) {
			System.out.print("--");
		}
		System.out.println();
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

		tablero = modelo.getBoard();

		vista.updateBoard(tablero);

	}
}
