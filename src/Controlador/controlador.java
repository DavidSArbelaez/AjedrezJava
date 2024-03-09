package Controlador;

import Vista.VentanaMenu;
import Vista.Vista;
import Modelo.Modelo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Labing
 */
public class controlador {
	Modelo modelo = new Modelo();
	Vista vista = new Vista();
	private VentanaMenu ventanaMenu;
	String tablero[][];
	int turnNum;

	public controlador() {
        this.ventanaMenu = new VentanaMenu();
        this.turnNum = 0;

        // Manejo de eventos para el botón 1
        ventanaMenu.setBoton1Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarBoton1();
            }
        });

        // Manejo de eventos para el botón 2
        ventanaMenu.setBoton2Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarBoton2();
            }
        });
    }

    // Método para manejar el clic del botón 1
    private void manejarBoton1() {
        System.out.println("Botón 1 presionado");
        // Agrega aquí la lógica para iniciar el juego
    }

    // Método para manejar el clic del botón 2
    private void manejarBoton2() {
        System.out.println("Botón 2 presionado");
        String direccionIP = abrirVentanaIngresoIP();
        System.out.println("Dirección IP ingresada: " + direccionIP);
        // Agrega aquí la lógica para conectarse a un juego usando la dirección IP ingresada
    }

	private String abrirVentanaIngresoIP() {
		JFrame frame = new JFrame("Ingresar dirección IP");

		// Mostrar el cuadro de diálogo de entrada de texto
		String direccionIP = JOptionPane.showInputDialog(frame, "Ingrese la dirección IP:");

		// Devolver el valor ingresado por el usuario
		return direccionIP;
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
						int[] rowM = vista.getRowM();
						int[] colM = vista.getColM();
						int fromRow, fromCol, toRow, toCol;
						if (tablero[rowM[0]][colM[0]].compareToIgnoreCase("") == 0) {
							toRow = rowM[0];
							toCol = colM[0];
							fromRow = rowM[1];
							fromCol = colM[1];
						} else {
							toRow = rowM[1];
							toCol = colM[1];
							fromRow = rowM[0];
							fromCol = colM[0];
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

	public void turn(int fromCol, int fromRow, int toCol, int toRow) {

		if (toCol != fromCol || toRow != fromRow) {
			Boolean isValidTurn = this.modelo.Turn(turnNum, fromCol, fromRow, toCol, toRow);
			if (isValidTurn) {
				System.out.println("Movimiento válido. La pieza se ha movido.");
				this.turnNum = this.turnNum + 1;
				modelo.erracePiece(fromRow, fromCol);
			} else {

				System.out.println("Movimiento no válido. Inténtelo de nuevo.");
			}
		}

		tablero = modelo.getBoard();
		this.displayBoard(tablero);
		vista.updateChessBoard(this.tablero);

	}
}
