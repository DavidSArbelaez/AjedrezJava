package SocketModelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port = 40000;
    private ServerSocket serverSocket;
    private BufferedReader in;
    private PrintWriter out;

    public Server(int PORT){
        this.port = PORT;
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Servidor iniciado. Esperando conexiones...");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public boolean isServerAccept(){
        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress().getHostAddress());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            return true;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public void sendDataToServer(String  message){
        out.println(message);
    }

    public String receiveDataServer(){
        try {
            // Cerrar conexiones
            System.out.println("Cliente: " + in.readLine());
            return in.readLine();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return "";
        }
    }

    public void end(){
        try {
            in.close();
            out.close();
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
