import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String SERVIDOR_IP = "localhost";
    private static final int PUERTO = 12345; 

    public static void main(String[] args) {
        try {
            // Conecta al servidor en el puerto especificado
            Socket socket = new Socket(SERVIDOR_IP, PUERTO);
            System.out.println("Conectado al servidor en " + SERVIDOR_IP + ":" + PUERTO);

            // Puedes agregar lógica para enviar y recibir datos desde el servidor
            // Ejemplo: enviarDatos(socket);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al conectar al servidor: " + e.getMessage());
        }
    }
}
