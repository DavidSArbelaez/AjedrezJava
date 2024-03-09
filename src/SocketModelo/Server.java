package SocketModelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PUERTO = 40000;

    public static void main(String[] args) {
        try {
            // Crear el socket del servidor en el puerto 12345
            ServerSocket serverSocket = new ServerSocket(40000);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            // Esperar a que un cliente se conecte
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress().getHostAddress());

            // Crear flujos de entrada y salida para la comunicación con el cliente
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Leer y enviar mensajes de forma intercalada con el cliente
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Cliente: " + inputLine);
                if (inputLine.equals("Bye"))
                    break;
                outputLine = reader.readLine();
                out.println(outputLine);
            }

            // Cerrar conexiones
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
