package Controlador;

import Vista.VentanaMenu;
import Vista.Vista;
import Modelo.Modelo;
import SocketModelo.Cliente;
import SocketModelo.Server;
import SocketModelo.Sockets;

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
	Cliente cl;
	Server sr;
	boolean estadoCliente; // Si es true es un cliente sino es un servidor

	public controlador() {
		this.ventanaMenu = new VentanaMenu();
		this.turnNum = 0;

		SwingUtilities.invokeLater(() -> {
			ventanaMenu.setVisible(true);
			vista.setVisible(false);
		});
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
		// System.out.println("Botón 1 presionado");
		estadoCliente = false;
		sr = new Server(40000);
		sr.isServerAccept();
		SwingUtilities.invokeLater(() -> {
			ventanaMenu.setVisible(false);
			vista.setVisible(true);

		});
		// Agrega aquí la lógica para iniciar el juego
	}

	// Método para manejar el clic del botón 2
	private void manejarBoton2() {
		System.out.println("Botón 2 presionado");
		String direccionIP = abrirVentanaIngresoIP();
		System.out.println("Dirección IP ingresada: " + direccionIP);
		cl = new Cliente(direccionIP, 40000);
		this.estadoCliente = true;
		SwingUtilities.invokeLater(() -> {
			ventanaMenu.setVisible(false);
			vista.setVisible(true);
		});
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
		Sockets s = new Sockets();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {


					//Cliente turno 1
					if (turnNum == 0 && estadoCliente) {
						System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
						String mensaje = cl.receiveDataServer();
						System.out.println("Soy un cliente" + mensaje);

						setTablero(s.deserializeStringArray(mensaje));
						int[] results = getCordsOponnent(modelo.getBoard());
						turn(results[1], results[0], results[3], results[2]);

						vista.updateChessBoard(getTablero());
						cl.sendDataToServer(s.serializeStringArray(getTablero()));
					}
					if (turnNum > 0 && ((estadoCliente && turnNum % 2 == 0)
							|| (!estadoCliente && turnNum % 2 == 1))) {
						System.out.println("Holaaaaaaa funcionaa");
						if (estadoCliente) {
							setTablero(s.deserializeStringArray(cl.receiveDataServer()));
							System.out.println("Cliente si es mayor a 1");
							displayBoard(getTablero());
							int[] results = getCordsOponnent(modelo.getBoard());
							turn(results[1], results[0], results[3], results[2]);
							vista.updateChessBoard(getTablero());
							cl.sendDataToServer(s.serializeStringArray(getTablero()));
							
						} else {
							// System.out.println(cl.receiveDataServer());
							setTablero(s.deserializeStringArray(sr.receiveDataServer()));
							System.out.println("Servidor si es mayor a 1");
							displayBoard(getTablero());
							int[] results = getCordsOponnent(modelo.getBoard());
							turn(results[1], results[0], results[3], results[2]);
							vista.updateChessBoard(getTablero());
							sr.sendDataToServer(s.serializeStringArray(getTablero()));
							
						}
					}
					if (vista.getMouseToMove() && ((!estadoCliente && turnNum % 2 == 0)
							|| (estadoCliente && turnNum % 2 == 1))) {

						if (!estadoCliente && turnNum%2==0) {
							
						}
						int[] rowM = vista.getRowM();
						int[] colM = vista.getColM();
						int fromRow, fromCol, toRow, toCol;
						String[][] tableroN = getTablero();
						if (tableroN[rowM[0]][colM[0]] != null
								&& tableroN[rowM[0]][colM[0]].compareToIgnoreCase("") == 0) {
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
						if (estadoCliente) {
							System.out.println("Cliente antes de envio");
							displayBoard(getTablero());
							cl.sendDataToServer(s.serializeStringArray(getTablero()));
						} else {
							System.out.println("Servidor antes de envio");
							displayBoard(getTablero());
							sr.sendDataToServer(s.serializeStringArray(getTablero()));
							
						}
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

	public String[][] getTablero() {
		return tablero;
	}

	public void setTablero(String[][] tableroN) {
		//System.out.println("Set tabbleroN");
		//displayBoard(tableroN);

		//System.out.println("Set tabblero");
		//displayBoard(this.tablero);
		this.tablero = tableroN;
		checkBoard();
		//System.out.println("Nuevo tablero");
		displayBoard(this.tablero);
	}

	private void checkBoard() {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = (tablero[i][j] == null) ? "" : tablero[i][j];
			}
		}

	}

	public int[] getCordsOponnent(String[][] tabMol) {
		// int row,col,rowM,colM;
		int[] results = new int[4];
		for (int i = 0; i < tabMol.length; i++) {
			for (int j = 0; j < tabMol[i].length; j++) {
				if (tabMol[i][j].compareToIgnoreCase(this.tablero[i][j]) != 0 && tabMol[i][j] != null) {
					results[0] = i;
					results[1] = j;
				}
				if (tabMol[i][j].compareToIgnoreCase(this.tablero[i][j]) != 0
						&& this.tablero[i][j].compareToIgnoreCase("") != 0) {
					System.out.println(tablero[i][j]);
					results[2] = i;
					results[3] = j;
				}
			}
		}
		System.out.println(results[0] + " " + results[1]);
		System.out.println(results[2] + " " + results[3]);
		return results;
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
		System.out.println("Tablero pos turno");
		this.displayBoard(tablero);
		vista.updateChessBoard(this.tablero);

	}
}
