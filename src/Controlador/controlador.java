package Controlador;

import Vista.Vista;
import java.util.InputMismatchException;
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
		iniciarEscucha();

	}

	public void displayBoard(String[][] boardState) {
	    // Imprimir la parte superior del tablero con las letras de las columnas
	    System.out.print("   ");
	    for (int col = 0; col < boardState[0].length; col++) {
	        System.out.print((char) ('A' + col) + " ");
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
	            // Ajustar el ancho de cada cuadrado para que tenga el mismo tamaño
	            System.out.printf("%-2s", boardState[row][col]);
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

	public void iniciarEscucha() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // Escuchar la variable booleana de la vista
                    if (vista.getMouseToMove()) {
                        // Llamar a la función cuando la variable cambie
                        int [] rowM = vista.getRowM();
                        int [] colM = vista.getColM();
                        int fromRow,fromCol,toRow,toCol;
                        if(tablero[rowM[0]][colM[0]].compareToIgnoreCase("")==0) {
                        	toRow=rowM[0];
                        	toCol=colM[0];
                        	fromRow=rowM[1];
                        	fromCol=colM[1];
                        }else {
                        	toRow=rowM[1];
                        	toCol=colM[1];
                        	fromRow=rowM[0];
                        	fromCol=colM[0];
                        }
                        turn(fromCol, fromRow, toCol, toRow);
                        vista.setContCords(0);
                        vista.resetRowM();
                        vista.resetColM();
                    }
                    try {
                        // Esperar un tiempo antes de volver a verificar
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
	
	public void playGame() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            try {
                //turn();
                System.out.println("¿Desea continuar jugando? (S/N)");
                String respuesta = sc.nextLine().trim().toUpperCase();
                if (!respuesta.equals("S")) {
                    continuar = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese solo letras.");
                sc.nextLine(); // Limpiar el buffer de entrada
            } catch (Exception e) {
                System.out.println("Error: Ha ocurrido un error inesperado.");
                e.printStackTrace();
            }
        }
        sc.close();
    }
	
	public void turn(int fromCol, int fromRow, int toCol, int toRow) {
		/*Scanner sc = new Scanner(System.in);
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
			int toRow = sc.nextInt() -1;
			System.out.println("Ingrese el numero de la columna a mover la pieza:");
			int toCol = sc.nextInt() - 1;

			Boolean isValidTurn = this.modelo.Turn(turnNum, fromCol, fromRow, toCol, toRow);
			
			if (isValidTurn) {
				System.out.println("Movimiento válido. La pieza se ha movido.");
				this.turnNum = this.turnNum + 1;
				modelo.erracePiece(fromRow,fromCol);
				option=true;
			}else{
				
				System.out.println("Movimiento no válido. Inténtelo de nuevo.");
			}
		} while (!option);*/
		
		
		if(toCol!=fromCol || toRow!=fromRow) {
			Boolean isValidTurn = this.modelo.Turn(turnNum, fromCol, fromRow, toCol, toRow);
			if (isValidTurn) {
				System.out.println("Movimiento válido. La pieza se ha movido.");
				this.turnNum = this.turnNum + 1;
				modelo.erracePiece(fromRow,fromCol);
				//option=true;
			}else{
				
				System.out.println("Movimiento no válido. Inténtelo de nuevo.");
			}
		}
		
		tablero = modelo.getBoard();
		this.displayBoard(tablero);
		vista.updateChessBoard(this.tablero);

	}
}
