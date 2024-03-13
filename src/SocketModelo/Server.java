package SocketModelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port = 40000;
    private ServerSocket serverSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Socket clientSocket;
    boolean MsgSend, MsgRec;

    public Server(int PORT) {
        this.port = PORT;
        try {
            serverSocket = new ServerSocket(this.port);
            InetAddress direccion = InetAddress.getLocalHost();
            String ip = direccion.getHostAddress();
            System.out.println("La dirección IP del PC local es: " + ip);
            System.out.println("Servidor iniciado. Esperando conexiones...");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public boolean isServerAccept() {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress().getHostAddress());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            return true;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public void sendDataToServer(String message) {

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println("Envio de info");
            out.println(message);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public String receiveDataServer() {
        try {
            if (clientSocket.isClosed()) {
                clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress().getHostAddress());
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            }
            String message = in.readLine();
            return message;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return "";
        }
    }
}
