package SocketModelo;

import java.io.*;
import java.net.*;

public class Cliente {
    private String server_IP;
    private int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Cliente(String host_ip, int port) {
        this.server_IP = host_ip;
        this.port = port;
        System.out.println(server_IP + " " + this.port);
        try {
            this.socket = new Socket(host_ip, this.port);
            // Crear flujos de entrada y salida para la comunicación con el servidor
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void sendDataToServer(String message) {
        try {
            out.println(message);
            // No cierres el PrintWriter aquí
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public String receiveDataServer() {
        try {
            // Verificar si el socket está cerrado y, en caso afirmativo, volver a abrirlo
            if (socket.isClosed()) {
                this.socket = new Socket(server_IP, port);
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream(), true);
            }
            
            System.out.println("Entro a recibir el mensaje");
            String message = in.readLine();
            System.out.println("Servidor: " + message);
            return message;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return "";
        }
    }

    public void close() {
        try {
            // Cerrar conexiones
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
