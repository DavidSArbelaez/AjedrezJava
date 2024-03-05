package SocketModelo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try {
            // Abre un ServerSocket en el puerto especificado
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                // Acepta la conexión entrante
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                //Creando un hilo para cada cliente
                new Thread(new ManejadorCliente(clientSocket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al abrir el ServerSocket: " + e.getMessage());
        }
    }
}
