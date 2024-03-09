package SocketModelo;
import java.io.*;
import java.net.*;

public class Cliente {
    private String server_IP;
    private int port;
    private Socket socket ;
    private BufferedReader in;
    private PrintWriter out;

    public Cliente(String host_ip, int port){
        this.server_IP =  host_ip;
        this.port = port;
        System.out.println(server_IP+" "+this.port);
        try {
            this.socket = new Socket(host_ip, this.port);
            // Crear flujos de entrada y salida para la comunicación con el servidor
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());

        }
        
    }

    public void CloseClient(){
        try {
            // Cerrar conexiones
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void sendDataToServer(String  message){
        out.println(message);
    }

    public String receiveDataServer(){
        try {
            // Cerrar conexiones
            System.out.println("Servidor: " + in.readLine());
            return in.readLine();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return "";
        }
    }

    public void a() {
        try {
            // Conectar al servidor en el puerto 12345
            Socket socket = new Socket("192.168.1.16", 40000);

            // Crear flujos de entrada y salida para la comunicación con el servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Crear un lector de entrada del teclado
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Leer y enviar mensajes de forma intercalada con el servidor
            String inputLine, outputLine;
            while ((inputLine = reader.readLine()) != null) {
                out.println(inputLine);
                System.out.println("Servidor: " + in.readLine());
                if (inputLine.equals("Bye"))
                    break;
            }

            // Cerrar conexiones
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
