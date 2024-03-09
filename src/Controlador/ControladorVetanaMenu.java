package Controlador;

import Vista.VentanaMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVetanaMenu {

    private VentanaMenu ventanaMenu;

    public ControladorVetanaMenu(VentanaMenu ventanaMenu) {
        this.ventanaMenu = ventanaMenu;

        ventanaMenu.setBoton1Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botón 1 presionado");
                // Agrega aquí el código que quieres que se ejecute al presionar el Botón 1
            }
        });

        ventanaMenu.setBoton2Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botón 2 presionado");
                String direccionIP = abrirVentanaIngresoIP();
                System.out.println("Dirección IP ingresada: " + direccionIP);
                // Agrega aquí el código que quieres que se ejecute con la dirección IP ingresada
            }
        });
    }

    private String abrirVentanaIngresoIP() {
        JFrame frame = new JFrame("Ingresar dirección IP");

        // Mostrar el cuadro de diálogo de entrada de texto
        String direccionIP = JOptionPane.showInputDialog(frame, "Ingrese la dirección IP:");

        // Devolver el valor ingresado por el usuario
        return direccionIP;
    }
}
