package Launcher;
import java.util.Scanner;
import javax.swing.*;
import Vista.*;

public class main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Vista chessBoardView = new Vista();
			chessBoardView.setVisible(true);

			Scanner scanner = new Scanner(System.in);

			while (true) {
				System.out.println("Ingrese la ubicación de la pieza (formato: fila columna): ");
				int fromRow = scanner.nextInt() - 1;
				int fromCol = scanner.nextInt() - 1;

				System.out.println("Ingrese la ubicación a donde desea mover la pieza (formato: fila columna): ");
				int toRow = scanner.nextInt() - 1;
				int toCol = scanner.nextInt() - 1;

				// Validar movimiento aquí (deberás implementar la lógica)
				boolean isValidMove = validarMovimiento(fromRow, fromCol, toRow, toCol);

				if (isValidMove) {
					System.out.println("Movimiento válido. La pieza se ha movido.");
					// Actualizar la vista según sea necesario
				} else {
					System.out.println("Movimiento no válido. Inténtelo de nuevo.");
				}
			}
		});
	}

	// Método de ejemplo para validar el movimiento (debes implementar la lógica
	// real)
	private static boolean validarMovimiento(int fromRow, int fromCol, int toRow, int toCol) {
		// Aquí deberías implementar la lógica para validar si el movimiento es
		// válido
		// Por ejemplo, verificar si hay una pieza en la ubicación de inicio y si es
		// posible moverla a la ubicación deseada.

		// Devuelve siempre true para el ejemplo.
		return true;
	}
}

}
